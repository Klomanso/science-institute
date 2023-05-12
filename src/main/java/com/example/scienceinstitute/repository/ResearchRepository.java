package com.example.scienceinstitute.repository;

import com.example.scienceinstitute.model.Research;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResearchRepository extends JpaRepository<Research, Integer> {

        List<Research> findAllByOrderByTitle();

        @Query(value = """
                SELECT concat(e.first_name, ' ', e.last_name) FROM employees e\s
                UNION
                SELECT r.title FROM research r""", nativeQuery = true)
        List<String> unionQuery();

        @Modifying
        @Transactional
        @Query(value = """
                CREATE OR REPLACE VIEW year_report AS
                WITH res_samples AS (
                \tSELECT
                \t\tr.res_id
                \t\t, array_agg(concat(c."name", '(', s.spec_name, ')')) research_samples
                \tFROM\s
                \t\tresearch r
                /*-----------------------------образцы-----------------------------*/
                \tINNER JOIN
                \t\tres_samples rs ON rs.res_id = r.res_id\s
                \tINNER JOIN
                \t\tcrops c ON c.brk_no = rs.brk_no
                \tINNER JOIN
                \t\tspecies s ON s.spec_no = c.spec_no
                /*-----------------------------образцы-----------------------------*/
                \tGROUP BY\s
                \t\tr.res_id
                ),
                res_team AS (
                \tSELECT
                \t\tr.res_id
                \t\t, array_agg(concat(e.first_name, ' ', e.last_name)) research_team
                \tFROM\s
                \t\tresearch r
                /*-----------------------------команда-----------------------------*/
                \tINNER JOIN
                \t\tres_team rt ON r.res_id = rt.res_id\s
                \tINNER JOIN
                \t\temployees e ON e.contract_no = rt.contract_no
                /*-----------------------------команда-----------------------------*/
                \tGROUP BY\s
                \t\tr.res_id
                )
                SELECT
                \tr.res_id\s
                \t, r.title\s
                \t, r.budget\s
                \t, concat(r.from_date, ' -- ', r.finish_date) res_period
                \t, concat(leaders.first_name, ' ', leaders.last_name) leader_name
                \t, cu.title customer_title
                \t, COALESCE(rt.research_team, '{no_team}') research_team
                \t, COALESCE(rs.research_samples, '{no_samples}') research_samples
                \t, (CASE WHEN c_result.brk_no IS NULL THEN 'UNSUCCESSFUL'
                \t\tELSE concat(cs_result.spec_name,'(',c_result."name",')') END) research_result
                FROM\s
                \tresearch r
                /*-----------------------------заказчик----------------------------*/
                LEFT JOIN\s
                \tcustomers cu ON cu.ogrn = r.ogrn
                /*-----------------------------заказчик----------------------------*/
                /*-----------------------------результат исследования--------------*/
                LEFT JOIN\s
                \tcrops c_result ON r.res_id = c_result.rsr_result
                LEFT JOIN
                \tspecies cs_result ON cs_result.spec_no = c_result.spec_no\s
                /*-----------------------------результат исследования--------------*/
                /*-----------------------------лидер-------------------------------*/
                LEFT JOIN
                \temployees leaders ON leaders.contract_no = r.lead_no
                /*-----------------------------лидер-------------------------------*/
                LEFT JOIN
                \tres_samples rs ON rs.res_id = r.res_id\s
                LEFT JOIN
                \tres_team rt ON rt.res_id = r.res_id
                /*WHERE\s
                \textract(YEAR FROM r.from_date) in (2015)*/
                ORDER BY
                \tres_id""", nativeQuery = true)
        void createViewOfResearchReport();

        @Query(value = """
                SELECT
                \t*
                FROM crosstab(
                \t'SELECT
                \t\tEXTRACT(YEAR FROM r.from_date) "year"
                \t\t, EXTRACT(MONTH FROM r.from_date) months
                \t\t, count(*) total_research
                \tFROM
                \t\tresearch r
                \tGROUP BY
                \t\tEXTRACT(MONTH FROM r.from_date), EXTRACT(YEAR FROM r.from_date)
                \tORDER BY
                \t\t1',
                  \t'select m from generate_series(1,12) m'
                ) as (
                  "year" int,
                  "Jan" int,
                  "Feb" int,
                  "Mar" int,
                  "Apr" int,
                  "May" int,
                  "Jun" int,
                  "Jul" int,
                  "Aug" int,
                  "Sep" int,
                  "Oct" int,
                  "Nov" int,
                  "Dec" int
                )""", nativeQuery = true)
        List<ResearchRepository.IResearchDynamics> crossQuery();

        interface IResearchDynamics {
                Integer getYear();

                Integer getJan();

                Integer getFeb();

                Integer getMar();

                Integer getApr();

                Integer getMay();

                Integer getJun();

                Integer getJul();

                Integer getAug();

                Integer getSep();

                Integer getOct();

                Integer getNov();

                Integer getDec();
        }
}
