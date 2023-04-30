package com.example.scienceinstitute.repository;

import com.example.scienceinstitute.model.Employee;
import com.example.scienceinstitute.model.Research;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

        List<Employee> findAllByOrderByLastName();

        @Query("from Employee e left join e.research r where r.id is not null")
        List<Employee> conditionalQuery();

        @Query("from Employee e inner join fetch e.leadResearch lr where lr in :researchList")
        List<Employee> parameterizedQuery(@Param("researchList") List<Research> research);
}
