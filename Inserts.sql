-- added data to make DB more verbose ARJ
insert into person values(1234567, 'Pujah', 'Louisiana','New Orleans','Canal', 70113, 'pujah@yahoo.com', 'female');
insert into experience values(1234567, 3300);
insert into experience values(1234567, 1110);
insert into has_job values(1234567, 15, '10-JAN-2010', '01-MAY-2014');
insert into has_job values(1234567, 13, '10-MAY-2014', null);
insert into job values(11, 'part-time', 30.00, 'wage', 2);
insert into job values(12, 'part-time', 40.00, 'wage', 2);
insert into person values(1000050, 'Henry', 'Louisiana','New Orleans','Cherry', 70113, 'henry@yahoo.com', 'male');
insert into person values(1710000, 'Alice', 'Louisiana','Harvey','Adonis', 70009, 'alice@yahoo.com', 'female');
insert into experience values(1000050, 1100);
insert into job values(13, 'full-time', 65000.00, 'salary', 10);
insert into job values(14, 'full-time', 60000.00, 'salary', 10);
insert into job values(15, 'full-time', 40000.00, 'salary', 3);
insert into job_company values(11, 150);
insert into job_company values(12, 150);
insert into job_company values(13, 800);
insert into job_company values(14, 800);
insert into job_company values(15, 200);
insert into person values(9990000, 'Dew', 'Florida','Orlando','Mickey', 82009, 'dew@yahoo.com', 'male');
insert into experience values(9990000, 1100);
insert into experience values(9990000, 1210);
insert into experience values(9990000, 1310);
--Tu's
INSERT INTO CourseSet
SELECT CourseSet_seq.NEXTVAL, C1.c_code, C2.c_code, null, 2 FROM Course C1, Course C2
WHERE C1.c_code < C2.c_code;

INSERT INTO CourseSet
SELECT CourseSet_seq.NEXTVAL, C1.c_code, C2.c_code, C3.c_code, 3 FROM Course C1, Course C2, Course C3
WHERE C1.c_code < C2.c_code AND C2.c_code < C3.c_code;


--added to Bre's database
insert into job_profile values(90000.00, 'a job', 'president', 11); --**No one qualifies for
insert into course values('medium', 'active', 'sql', 'sql 101', 1200, 200.00); --NEW
insert into knowledge_skill values(1410, 'database', 'database lab', 'medium'); --NEW
insert into teaches values(1410, 1200); -- database taught in sql 101 --NEW
insert into section values('online-selfpaced', '20-OCT-2014', 2014, 13, 100.00,'Fall', 1200); --Section for sql 101 --NEW
insert into offers values(100, 2014, 13, 1200, 'Fall'); --Sql 101 section offered at GE in 2014 --NEW
insert into skills values(1410, 1); -- Database is required for backend developer  --NEW
insert into experience values(8880000, 2000); --Alex has Java experience --NEW2
insert into takes values(8880000, 2013, 1, 10, 'Spring'); --Alex took Data structures at GE in 2013 --NEW2
insert into job values(11, 'full-time', 100000.00, 'salary', 11); --NEW3
insert into company values(900, 'DC','Washington','Pennsylvania Ave.', 20500, 'whiteHouse', 'whiteHouse.com'); --NEW3
insert into job_company values(11, 900); --President at White House --New3
insert into company_speciality values('power',900); --power at White House --NEW3
insert into course values('advance', 'active', 'politics', 'politics 500', 1300, 900.00); --NEW3
insert into knowledge_skill values(1510, 'rule the US', 'rule the US', 'advance'); --NEW3
insert into teaches values(1510, 1300); -- rule the US taught in politics 500 --NEW3
insert into skills values(1510, 11); -- Rule the US is required for president  --NEW3
insert into section values('correspondence', '20-NOV-2015', 2015, 14, 900.00,'Fall', 1300); --Section for rule the US --NEW3
insert into offers values(900, 2015, 14, 1300, 'Fall'); --politics 500 section offered at White House in 2015 --NEW3
insert into knowledge_skill values(1610, 'master in bio', 'degree in bio', 'advance'); --NEW4
insert into teaches values(1610, 300); -- taxonomy needed for masters in bio --NEW4 -NOTE: I DONT HAVE A JOB_PROFILE THAT NEEDS MASTERS IN BIO YET
insert into teaches values(1610, 1000); -- laboratory needed for masters in bio --NEW4
insert into teaches values(1610, 1100); -- micro bio needed for masters in bio --NEW4
insert into skills values(3300, 11); -- Management is required for president  --NEW4
insert into skills values(4400, 11); -- Public Speaking is required for president  --NEW4
insert into experience values(3330000, 3300); --Sabrina has management experience --New4
insert into takes values(3330000, 2000, 3, 220, 'Spring'); --Sabrina took management 101 at Quiznos in 2000 --NEW4
insert into section values('classroom', '20-SEP-2016', 2016, 15, 50.00,'Fall', 220); --Section for management 101 --NEW5 Second section for management 101
insert into offers values(200, 2016, 15, 220, 'Fall'); --Management 101 section offered at Quiznos in 2016 --NEW5
insert into course values('advance', 'active', 'allBio', 'bio 6000', 1400, 999.00); --NEW6 --Added to make a course cover a skill set
insert into teaches values(1100, 1400); -- Taxonomy taught in allBio --NEW6
insert into teaches values(1310, 1400); -- micro bio taught in allBio --New6
insert into teaches values(1210, 1400); -- laboratory taught in allBio --New6
insert into section values('classroom', '20-OCT-2016', 2016, 16, 100.00,'Fall', 1400); --Section for allBio --NEW6 Section for a course that covers all the skills needed for bio teacher
insert into offers values(150, 2016, 16, 1400, 'Fall'); --allBio section offered at Orange High School in 2016 --New6


--job_profile(avg_pay, description, title, pos_code)
insert into job_profile values(70000.00, 'app developer', 'backend developer', 1);
insert into job_profile values(40.00, 'high school teacher', 'bio teacher', 2); --**Requires 3 skills
insert into job_profile values(35000.00, 'a job', 'manager', 3);
insert into job_profile values(75.00, 'a job', 'motivational speaker', 4);
insert into job_profile values(75000.00, 'a job', 'construction manager', 5);
insert into job_profile values(500.00, 'a job', 'tv host', 6);
insert into job_profile values(65000.00, 'a job', 'hotel GM', 7);
insert into job_profile values(60000.00, 'a job', 'truck driver', 8);
insert into job_profile values(47000.00, 'a job', 'quality analyst', 9); --**Works at same company as 1
insert into job_profile values(50000.00, 'a job', 'game developer', 10);





--job(job_code, type, pay_rate, pay_type, pos_code)
insert into job values(1, 'full-time', 60000.00, 'salary', 1); --NEED TO UPDATE THIS ROW IN SQL
insert into job values(2, 'part-time', 35.00, 'wage', 2);
insert into job values(3, 'full-time', 30000.00, 'salary', 3);
insert into job values(4, 'full-time', 50.00, 'wage', 4);
insert into job values(5, 'full-time', 70000.00, 'salary', 5);
insert into job values(6, 'part-time', 500.00, 'wage', 6);
insert into job values(7, 'full-time', 70000.00, 'salary', 7);
insert into job values(8, 'full-time', 65000.00, 'salary', 8);
insert into job values(9, 'full-time', 45000.00, 'salary', 9);
insert into job values(10, 'full-time', 55000.00, 'salary', 10);





--company(comp_id, state, city, street, zip_code, primary_sector, website)
insert into company values(100, 'Louisiana','New Orleans','Common', 70005, 'technology', 'GE.com');
insert into company values(150, 'Florida','Miami','Orange Dr.', 70390, 'education', 'OrangeHighSchool.com');
insert into company values(200, 'Nevada','Las Vegas','Jackson', 74580, 'service industry', 'Quiznos.com');
insert into company values(300, 'Missouri','Springfield','Knight Dr.', 70540, 'public', 'publicHouse.com');
insert into company values(400, 'Texas','El Paso','Paso Dr.', 72520, 'construction', 'constructionInc.com');
insert into company values(500, 'California','Los Angeles','Sunshine Dr.', 73420, 'entertainment', 'EntertainmentTonight.com');
insert into company values(600, 'Louisiana','New Orleans','Canal', 70112, 'tourism', 'ACHotel.com');
insert into company values(700, 'Alabama','Mobile','Rose Dr.', 72112, 'supply goods', 'SupplyDemand.com');
insert into company values(800, 'Nevada','Las Vegas','David Dr.', 70125, 'gameLoft', 'gameLoft.com');



--person(per_id, name, state, city, street, zip_code, email, gender)
insert into person values(1000000, 'Dave', 'Louisiana','New Orleans','Cherry', 70113, 'dave@yahoo.com', 'male');
insert into person values(1110000, 'Samantha', 'Louisiana','Harvey','Adonis', 70009, 'sam@yahoo.com', 'female');
insert into person values(2220000, 'Lisa', 'Las Vegas','Springway','Spring Dr.', 74560, 'lisa@yahoo.com', 'female'); --** Had 2 jobs, which gives her more skills
insert into person values(3330000, 'Sabrina', 'Missouri','Springfield','Boardwalk', 72210, 'sabrina@yahoo.com', 'female');
insert into person values(4440000, 'Mark', 'Texas','El Paso','Lake', 72510, 'mark@yahoo.com', 'male');
insert into person values(5550000, 'Darren', 'California','Los Angeles','Pico', 73510, 'darren@yahoo.com', 'male');
insert into person values(6660000, 'Erika', 'Louisiana','New Orleans', 'Iris', 70125, 'erika@yahoo.com', 'female');
insert into person values(7770000, 'Josh', 'Mississippi','Jackson', 'Palm', 72425, 'josh@yahoo.com', 'male');
insert into person values(8880000, 'Alex', 'Louisiana','New Orleans','Pine', 70113, 'alex@yahoo.com', 'female');



--job_company(job_code, comp_id)
insert into job_company values(1, 100); --Developer at GE
insert into job_company values(2, 150); --Teacher at Orange High School
insert into job_company values(3, 200); --Manager at Quiznos
insert into job_company values(4, 300); --Motivational speaker at Public House
insert into job_company values(5, 400); --Construction manager at Construction Inc.
insert into job_company values(6, 500); --TV host at Entertainment Tonight
insert into job_company values(7, 600); --Hotel GM at AC Hotel
insert into job_company values(8, 700); --Truck Driver at SupplyDemand
insert into job_company values(9, 100); --Quality Analyst at GE
insert into job_company values(10, 800); --Game Developer at Game Loft




--company_speciality(speciality, comp_id)
insert into company_speciality values('software development',100); --Software development at GE
insert into company_speciality values('special education',150); --Special education at Orange High School
insert into company_speciality values('fast food',200); --Fast food at Quiznos
insert into company_speciality values('self help',300); --self help at Public House
insert into company_speciality values('construction',400); --construction at Construction Inc.
insert into company_speciality values('news',500); --news at Entertainment Tonight
insert into company_speciality values('travel',600); --travel at Ac Hotel
insert into company_speciality values('logistics service',700); --logistics service at SupplyDemand
insert into company_speciality values('technology',800); --technology at GameLoft




--person_phone(per_id, phone)
insert into person_phone values(5042500009, 1000000); --Dave phone number
insert into person_phone values(5045220089, 1110000); --Samantha phone number
insert into person_phone values(3937681189, 2220000); --Lisa phone number
insert into person_phone values(4703281189, 3330000); --Sabrina phone number
insert into person_phone values(4233281249, 4440000); --Mark phone number
insert into person_phone values(3733281270, 5550000); --Darren phone number
insert into person_phone values(5047845330, 6660000); --Erika phone number
insert into person_phone values(2317835631, 7770000); --Josh phone number
insert into person_phone values(5047825461, 8880000); --Alex phone number




--has_job(per_id, job_code, start_date, end_date)
insert into has_job values(1000000, 1, '03-APR-2015', null); --Dave currently works at GE
insert into has_job values(1110000, 2, '10-MAR-2011', '01-OCT-2015'); --Samantha worked at Orange High school from 10-MAR-2011 to 01-OCT-2015
insert into has_job values(2220000, 3, '10-JAN-2000', '01-MAR-2010'); --Lisa worked at Quiznos from 10-JAN-2000 to 01-MAR-2010
insert into has_job values(3330000, 4, '10-JUL-2013', null); --Sabrina currently work at Public House
insert into has_job values(4440000, 5, '10-JUL-2015', null); --Mark currently work at Construction Inc.
insert into has_job values(5550000, 6, '05-JAN-2016', null); --Darren currently work at Entertainment Tonight
insert into has_job values(6660000, 7, '01-JAN-2014', null); --Erika currently work at AC Hotel
insert into has_job values(7770000, 8, '09-FEB-2015', '20-DEC-2015'); --Josh worked at Supply Demand from 09-FEB-2015 to 20-DEC-2015
insert into has_job values(8880000, 9, '13-FEB-2015', null); --Alex currently works at GE
insert into has_job values(2220000, 10, '10-JAN-2011', null); --Lisa currently works for GameLoft



--course(lvl, status, description, title, c_code, retail_price)
insert into course values('advance', 'active', 'advance programming', 'data structures', 10, 200.00);
insert into course values('medium', 'active', 'advance biology', 'bio 3000', 300, 100.00);
insert into course values('beginner', 'expired', 'intro to management', 'management 101', 220, 50.00);
insert into course values('advance', 'active', 'intro to public speaking', 'public speaking 101', 350, 300.00);
insert into course values('advance', 'active', 'intro to bulldozer', 'bulldozer 101', 400, 500.00);
insert into course values('medium', 'active', 'intro to hosting', 'hosting 101', 500, 600.00);
insert into course values('advance', 'active', 'intro to gm', 'gm 101', 600, 400.00);
insert into course values('beginner', 'expired', 'intro to truck driving', 'truck driving 101', 700, 700.00);
insert into course values('medium', 'active', 'intro to quality assurance', 'qa 101', 800, 250.00);
insert into course values('advance', 'active', 'intro to game development', 'game development 101', 900, 300.00);
insert into course values('beginner', 'active', 'intro biology', 'bio 1000', 1000, 100.00);
insert into course values('advance', 'active', 'master biology', 'bio 4000', 1100, 100.00);





--knowledge_skill(ks_code, title, description, lvl)
insert into knowledge_skill values(2000, 'java', 'programming', 'advance');
insert into knowledge_skill values(1100, 'taxonomy', 'identification', 'medium');
insert into knowledge_skill values(3300, 'management', 'leadership', 'beginner');
insert into knowledge_skill values(4400, 'public speaking', 'speaking', 'advance');
insert into knowledge_skill values(5500, 'bulldozer management', 'bulldozer', 'advance');
insert into knowledge_skill values(6600, 'hosting', 'hosting tv show', 'medium');
insert into knowledge_skill values(7700, 'general manager', 'hotel general manager', 'advance');
insert into knowledge_skill values(8800, 'truck driving', 'truck driving extreme', 'beginner');
insert into knowledge_skill values(9900, 'quality assurance', 'software quality', 'medium');
insert into knowledge_skill values(1110, 'game development', 'game programming', 'advance');
insert into knowledge_skill values(1210, 'laboratory', 'bio laboratory', 'beginner');
insert into knowledge_skill values(1310, 'micro bio', 'microbiology lab', 'advance');




--teaches(ks_code, c_code)
insert into teaches values(2000, 10); -- Java taught in data structures
insert into teaches values(1100, 300); -- Taxonomy taught in bio 3000
insert into teaches values(3300, 220); -- Management taught in management 101
insert into teaches values(4400, 350); -- Public Speaking taught in public speaking 101
insert into teaches values(5500, 400); -- Bulldozer management taught in bulldozer 101
insert into teaches values(6600, 500); -- hosting taught in hosting 101
insert into teaches values(7700, 600); -- general manager taught in gm 101
insert into teaches values(8800, 700); -- truck driving taught in truck driving 101
insert into teaches values(9900, 800); -- quality assurance taught in qa 101
insert into teaches values(1110, 900); -- game development taught in game development 101
insert into teaches values(1210, 1000); -- laboratory taught in bio 1000
insert into teaches values(1310, 1100); -- micro bio taught in bio 4000




--skills(ks_code, pos_code)
insert into skills values(2000, 1); -- Java is required for backend developer
insert into skills values(1100, 2); -- Taxonomy is required for bio teacher
insert into skills values(3300, 3); -- Management is required for manager
insert into skills values(4400, 4); -- Public Speaking is required for motivational speaker
insert into skills values(5500, 5); -- Bulldozer Management is required for construction manager
insert into skills values(6600, 6); -- Hosting is required for tv host
insert into skills values(7700, 7); -- General Manager is required for hotel GM
insert into skills values(8800, 8); -- Truck Driving is required for truck driver
insert into skills values(9900, 9); -- Quality assurance is required for quality analyst
insert into skills values(1110, 10); -- Game Development is required for game developer
insert into skills values(1210, 2); -- Laboratory is required for bio teacher
insert into skills values(1310, 2); -- Micro Bio is required for bio teacher



--experience(per_id, ks_code)
insert into experience values(1000000, 2000); --Dave has Java experience
insert into experience values(1110000, 1100); --Samantha has Taxonomy experience
insert into experience values(2220000, 3300); --Lisa has Management experience
insert into experience values(3330000, 4400); --Sabrina has Public speaking experience
insert into experience values(4440000, 5500); --Mark has Bulldozer management experience
insert into experience values(5550000, 6600); --Darren has hosting experience
insert into experience values(6660000, 7700); --Erika has general manager experience
insert into experience values(7770000, 8800); --Josh has truck driving experience
insert into experience values(8880000, 9900); --Alex has quality assurance experience
insert into experience values(2220000, 1110); --Lisa has Game Development experience
insert into experience values(1110000, 1210); --Samantha has Laboratory experience
insert into experience values(1110000, 1310); --Samantha has Microbiology experience




--section(format, complete_date, year, sec_no, price, semester, c_code)
insert into section values('classroom', '13-FEB-2013', 2013, 1, 150.00, 'Spring', 10); --Section for data structures
insert into section values('online-sync', '20-JAN-2012', 2012, 2, 100.00, 'Spring', 300); --Section for bio 3000
insert into section values('classroom', '20-JAN-2000', 2000, 3, 50.00,'Spring', 220); --Section for management 101
insert into section values('classroom', '20-JAN-2014', 2014, 4, 300.00,'Spring', 350); --Section for public speaking 101
insert into section values('classroom', '20-JAN-2015', 2015, 5, 500.00,'Spring', 400); --Section for bulldozer 101
insert into section values('correspondence', '01-JAN-2016', 2016, 6, 600.00,'Spring', 500); --Section for hosting 101
insert into section values('online-selfpaced', '20-DEC-2015', 2015, 7, 350.00,'Fall', 600); --Section for gm 101
insert into section values('classroom', '20-JAN-2015', 2015, 8, 650.00, 'Spring', 700); --Section for truck driving 101
insert into section values('classroom', '08-JAN-2013', 2013, 9, 250.00, 'Spring',800); --Section for qa 101
insert into section values('online-sync', '11-MAR-2013', 2013, 10, 3000.00,'Spring', 900); --Section for game development 101
insert into section values('online-sync', '20-JAN-2012', 2012, 11, 100.00,'Spring', 1000); --Section for bio 1000
insert into section values('online-sync', '20-JAN-2012', 2012, 12, 100.00,'Spring', 1100); --Section for bio 4000



--offers(comp_id, year, sec_no, c_code, semester)
insert into offers values(100, 2013, 1, 10, 'Spring'); --Data structures section offered at GE in 2013
insert into offers values(150, 2012, 2, 300, 'Spring'); --Bio 3000 section offered at Orange High School in 2012
insert into offers values(200, 2000, 3, 220, 'Spring'); --Management 101 section offered at Quiznos in 2000
insert into offers values(300, 2014, 4, 350, 'Spring'); --Public speaking 101 section offered at Public House in 2014
insert into offers values(400, 2015, 5, 400, 'Spring'); --Bulldozer Management section offered at Construction Inc. in 2015
insert into offers values(500, 2016, 6, 500, 'Spring'); --Hosting section offered at Entertainment Tonight in 2016
insert into offers values(600, 2015, 7, 600, 'Fall'); --General Manager section offered at AC Hotel in 2015
insert into offers values(700, 2015, 8, 700, 'Spring'); --Truck Driving section offered at SupplyDemand in 2015
insert into offers values(100, 2013, 9, 800, 'Spring'); --Quality Assurance section offered at GE in 2013
insert into offers values(800, 2013, 10, 900, 'Spring'); --Game Development section offered at Game Loft in 2013
insert into offers values(150, 2012, 11, 1000, 'Spring'); --Bio 1000 section offered at Orange High School in 2012
insert into offers values(150, 2012, 12, 1100, 'Spring'); --Bio 4000 section offered at Orange High School in 2012


--takes(per_id, year, sec_no, c_code, semester)
insert into takes values(1000000, 2013, 1, 10, 'Spring'); --Dave took Data structures at GE in 2013
insert into takes values(1110000, 2012, 2, 300, 'Spring'); --Samantha took bio 3000 at Orange High School in 2013
insert into takes values(2220000, 2000, 3, 220, 'Spring'); --Lisa took management 101 at Quiznos in 2000
insert into takes values(3330000, 2014, 4, 350, 'Spring'); --Sabrina took public speaking 101 at Public House in 2014
insert into takes values(4440000, 2015, 5, 400, 'Spring'); --Mark took bulldozer management at Construction Inc. in 2015
insert into takes values(5550000, 2016, 6, 500, 'Spring'); --Darren took hosting at Entertainment Tonight in 2016
insert into takes values(6660000, 2015, 7, 600, 'Fall'); --Erika took general manager at AC Hotel in 2015
insert into takes values(7770000, 2015, 8, 700, 'Spring'); --Josh took truck driving at SupplyDemand in 2015
insert into takes values(8880000, 2013, 9, 800, 'Spring'); --Alex took quality assurance at GE in 2013
insert into takes values(2220000, 2013, 10, 900, 'Spring'); --Lisa took game development at GameLoft in 2013
insert into takes values(1110000, 2012, 11, 1000, 'Spring'); --Samantha took bio 1000 at Orange High School in 2013
insert into takes values(1110000, 2012, 12, 1100, 'Spring'); --Samantha took bio 4000 at Orange High School in 2013
