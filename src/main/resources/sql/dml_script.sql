
-- ALTER TABLE research ADD CHECK (((age(finish_date, from_date) >= '3 mons'::interval)
--    	AND (age(finish_date, from_date) <= '1 year'::interval)));
-- ALTER TABLE research ADD CHECK (budget > 0);
-- ALTER TABLE "procedures" ADD CHECK (duration <= '7 days');
-- ALTER TABLE employees ADD CHECK (age(now(), birth_date) >= '18 years');

TRUNCATE TABLE 
	employees
	, research
	, res_team
	, crops
	, res_samples
	, res_procedures
	, titles
	, education 
	, species 
	, customers 
	, "procedures" 
	;

ALTER SEQUENCE procedures_proc_no_seq RESTART;
ALTER SEQUENCE research_res_id_seq RESTART;
ALTER SEQUENCE education_edu_no_seq RESTART;
ALTER SEQUENCE titles_title_no_seq RESTART;
ALTER SEQUENCE species_spec_no_seq RESTART;

INSERT INTO
	species (spec_name)
VALUES
	('яблоня')
	, ('черешня')
	, ('смородина')
	, ('слива')
	, ('персик')
	, ('груша')
	, ('вишня')
	, ('алыча')
	, ('айва')
	, ('абрикос')
	, ('черемуха')
	;
	
INSERT INTO
	titles (title_name)
VALUES
	('ученый биолог')
	, ('специалист генной инженерии')
	, ('технолог пищевой промышленности')
	, ('эксперт по качеству')
	, ('эколог')
	, ('нутрициолог')
	, ('биохимик')
	, ('биотехнолог')
	, ('генетик')
	, ('инженер технолог')
	;

INSERT INTO
	education (edu_type)
VALUES
	('среднее')
	, ('среднее специальное')
	, ('неоконченное высшее')
	, ('высшее')
	, ('бакалавр')
	, ('магистр')
	, ('кандидат наук')
	, ('доктор наук')
	;

INSERT INTO
	customers (ogrn, title, email, phone_number)
VALUES
	('1027739642281', 'фермерское хозяйство ГАСПАДАР', 'gaspadar@mail.by', '+375298572323')
	, ('1935729642891', 'Зара-Агро ЗАО', 'zara.agro@mail.by', '+375294562623')
	, ('1867819241701', 'фермерское хозяйство КРЫНИЦА', 'krunica@mail.by', '+375290172092')
	, ('1044553943101', 'Городея ОАО', 'gorodia@mail.by', '+375298572323')
	, ('1072337844141', 'МилкСервисПлюс ОАО', 'milkservice@mail.by', '+375292572733')
	, ('1125139845201', 'Наномир фермерское хозяйство', 'nanomir@mail.by', '+375455723239')
	, ('1028933646301', 'Крестьянское хозяйство ЗАО', 'kres.grodno@mail.io', '+375298571234')
	, ('1621131446411', 'Гродно КУЛЬТХОЗ', 'kultxoz@mail.by', '+375673542312')
	, ('1991232648581', 'Алесин сад ОАО', 'aleci.sad@gmail.com', '+375298796323')
	;

INSERT INTO
	"procedures" (proc_name, description, duration)
VALUES
	('Высадка образца в грунт и адаптация в условиях','Приготовить почвенную смесь, 
		насыпать почвенную смесь в бюксы для прогревания, 
	 	заполнить горшки почвенной смесью опустить микрорастение в слабый раствор перманганата калия,
	 	полить почвенную смесь слабым раствором перманганата калия, 
		сделать углубление в почве','3 hours')
	, ('Проведения анализа ПЦР продуктов в агарозном геле','Биомолекулы разделяются под действием электрического поля.
		Для перемещения заряженных молекул через матрицу агарозы, и биомолекулы разделяются по размеру в матрице геля агарозы.
		Большинство используемых гелей агарозы растворены на 0,7–2% в подходящем буфере для электрофореза.','1 hour 20 mins')
	, ('Оценка засухоустойчивости','Определить физиологические изменения параметров водного режима
		растений в условиях засухи','3 days')
	, ('Определение PH солевой вытяжки по методу цинао в почве участка','Упорядочить процесс агрохимического мониторинга почвы опытного
		участка, занятого БРК','4 hours')
	, ('Определение содержания углерода орг. соединений в почве участка','Упорядочить процесс агрохимического мониторинга почвы опытного
		участка, занятого БРК','30 min')
	, ('Фенотипическая оценка растений','Фенотипическая оценка растений по способности к вегетативному
		размножению','45 min')
	, ('Оценка ценности для технологической обработки','Определить пригодность плодов для производства варенья','1 hour 20 min')
	, ('Выделение ДНК из листьев','Лаборатория биохимической генетики','6 days 12 hours')
	, ('Ускорение микропобегов','Стимулировать корнеобразование у микропобега','2 hour 30 min')
	;

INSERT INTO 
	crops (brk_no, name, spec_no, winter_hardiness, pd_resistance, yields, rsr_result, notes)
VALUES
	('0001', 'Абориген',1,TRUE,FALSE,TRUE,NULL,'Летний сорт, выведен в Дальневосточном научно-исследовательском институте сельского хозяйства от скрещивания
	сортов Августовское дальневосточное и Ребристое. Автор сорта А.В. Болоняев. Включен в Госреестр в 1974 году по Дальневосточному региону.')
	, ('0002', 'Аделина',2,TRUE,TRUE,TRUE,NULL,'Оригинатор – Всероссийский НИИ генетики и селекции плодовых растений и Всероссийский НИИ селекции плодовых культур.')
	, ('0003', 'Алексий',3,TRUE,FALSE,TRUE,NULL,'Сорт получен во Всероссийском селекционно-технологическом институте садоводства и питомниководства из семян от свободного опыления сорта Занятная.')
	, ('0004', 'Гринсборо',4,FALSE,FALSE,FALSE,NULL,' Дерево сильнорослое, крона раскидистая. Цветки розовидные.')
	, ('0005', 'Августинка',5,TRUE,TRUE,FALSE,NULL,'Побеги серовато-коричневые, толстые, коленчатые. Почки не прижатые, средней величины. Листья средней величины, зеленые. Листовая пластинка гладкая с волнистостью, изогнутая по центральной жилке.')
	, ('0006', 'Аляевская',6,FALSE,FALSE,TRUE,NULL,'Поздняя розовая х Полжир. Оригинатор – Татарский НИИСХ. Авторы: Л.А. Севастьянова, В.А. Наумов. В госсортоиспытании с 1994 г.')
	, ('0007', 'Анастасия',7,FALSE,TRUE,FALSE,NULL,' Дерево сильнорослое. Крона широкопирамидальная, раскидистая, средней густоты.')
	, ('0008', 'Аврора',8,TRUE,TRUE,FALSE,NULL,'Сорт выведен в Северо-Кавказском зональном научно-исследовательском институте садоводства и виноградарства (г. Краснодар)')
	, ('0009', 'Айсберг',9,FALSE,TRUE,TRUE,NULL,' Деревья небольших размеров (3 м), сила роста умеренная. Однолетние побеги сильно ветвистые. Цветки крупные 3,5-4 см, белые.')
	, ('0010', 'Мавра',10,FALSE,FALSE,TRUE,NULL,'Гибрид от скрещивания формы № 1-5-13 черемухи виргинской с формой № 5-28-10 черемухи кистевой. Оригинатор – Центральный сибирский ботанический сад')
	;

INSERT INTO 
	employees (contract_no, first_name, last_name, birth_date, hire_date, title, education)
VALUES
	('1194561', 'Георгий', 'Белов', '1986-09-12','2003-12-26', 1, 1)
	, ('1115692', 'Мирослава', 'Бородина', '1986-09-12','2003-11-24', 2, 2)
	, ('1010693', 'Георгий', 'Гришин', '1985-09-12','2005-10-29', 3, 3)
	, ('1323694', 'Ксения', 'Петрова', '1984-09-12','2004-09-12', 4, 4)
	, ('1239695', 'Арина', 'Тихомирова', '1983-09-12','2017-08-11', 5, 5)
	, ('1423696', 'Павел', 'Козловский', '1982-09-12','2017-07-10', 6, 6)
	, ('1946697', 'Георгий', 'Филиппов', '1986-09-12','2017-06-09', 7, 7)
	, ('1954698', 'Артём', 'Фирсов', '1986-09-12','2016-05-04', 8, 8)
	, ('1974699', 'Мия', 'Крылова', '1987-09-12','2009-04-04', 9, 4)
	, ('1903695', 'Варвара', 'Трифонова', '1988-09-12','2010-03-08', 10, 5)
	, ('1994694', 'Владимир', 'Кудрявцев', '1989-09-12','2016-02-07', 1, 6)
	, ('1003693', 'Милана', 'Сорокина', '1990-09-12','2015-01-18', 2, 5)
	, ('1042692', 'Георгий', 'Исаев', '1991-09-12','2014-01-17', 3, 4)
	, ('1093691', 'Александр', 'Смирнов', '1995-09-12','2018-01-16', 4, 6)
	, ('1004697', 'Таисия', 'Черкасова', '1999-09-12','2019-12-15', 5, 7)
	, ('1075698', 'Нелли', 'Воробьева', '1975-09-12','2004-12-14', 6, 8)
	, ('1020699', 'Владимир', 'Исаев', '1965-09-12','2002-04-04', 7, 5)
	, ('1874694', 'Вероника', 'Белова', '1973-09-12','2001-04-03', 8, 6)
	, ('1750693', 'Владимир', 'Белов', '1974-09-12','2000-05-02', 9, 7)
	, ('1650691', 'Полина', 'Булатова', '2001-09-12','2020-05-01', 10, 4)
	;

INSERT INTO
	research (title, ogrn, from_date, finish_date, budget, lead_no)
VALUES
	('Выведение позднезимнего сорта яблока','1027739642281', '2022-04-03', '2022-09-08', 5510, '1750693')
	, ('Выведение летнего сорта груши','1935729642891', '2021-12-23', '2022-03-24', 3501, '1954698')
	, ('Селекция среднерослых культур','1867819241701', '2020-11-11', '2021-11-04', 7500, '1423696')
	, ('Исследование морозостойкости персиковых культур','1044553943101', '2019-10-12', '2020-09-09', 8500, '1423696')
	, ('Селекция сеянцев абрикоса','1125139845201', '2018-09-12', '2019-05-27', 6220, '1020699')
	, ('Исследование урожайности степных сортов вишни','1028933646301', '2017-08-13', '2018-06-03', 9512, '1994694')
	, ('Исследование устойчивости к вредителям садовых культур','1028933646301', '2016-07-17', '2017-02-17', 5500, '1994694')
	, ('Селекция урожайных сортов персика','1621131446411', '2015-06-18', '2015-12-18', 5500, '1020699')
	, ('Выведение крупных сортов сливы','1991232648581', '2014-05-19', '2014-10-20', 3298, '1954698')
	, ('Выведение сортов смородины позднего срока созревания','1125139845201', '2013-04-20', '2013-09-10', 3456, '1750693')
	;
	
INSERT INTO
	res_team (res_id, contract_no)
VALUES
	(1,'1323694'), (1,'1042692'), (1,'1093691'), (1,'1650691')
	, (2,'1323694'), (2,'1042692'), (2,'1093691')
	, (3,'1239695'), (3,'1003693'), (3,'1004697')
	, (4,'1239695'), (4,'1003693'), (4,'1004697')
	, (5,'1423696'), (5,'1994694'), (5,'1075698')
	, (6,'1423696'), (6,'1994694'), (6,'1075698')
	, (7,'1946697'), (7,'1903695'), (7,'1020699')
	, (8,'1946697'), (8,'1903695'), (8,'1020699')
	, (9,'1954698'), (9,'1974699'), (9,'1874694')
	, (10,'1954698'), (10,'1974699'), (10,'1750693')
	;
	
INSERT INTO
	res_samples (brk_no, res_id)
VALUES
	('0002',1), ('0001',1)
	, ('0003',2), ('0004',2)
	, ('0005',4), ('0008',5)
	, ('0008',6), ('0007',8)
	, ('0010',9), ('0007',9), ('0009',10)
	;
	
INSERT INTO
	res_procedures (res_id, proc_no)
VALUES
	(1,3), (2,2), (2,1)
	, (3,9), (3,8), (3,7)
	, (4,6), (4,5), (4,4)
	, (5,3), (5,1), (6,9)
	, (7,8), (7,7), (8,6)
	, (8,5), (9,4), (10,3)
	, (10,2), (10,1)
	;
	
	
	
	
	









