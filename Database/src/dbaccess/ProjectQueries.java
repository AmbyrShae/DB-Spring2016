package dbaccess;

import java.util.ArrayList;
import java.util.HashMap;

public class ProjectQueries {
	private ArrayList<String> questions;
	private ArrayList<String> queries;
	HashMap<String,String> map;
	public ProjectQueries()
	{
		this.questions=new ArrayList();
		this.queries=new ArrayList();
		this.map=new HashMap();
		addQuestions();
		addQueries();
		createMap();
	}
	
	public void addQuestions()
	{
		questions.add("1a. List a company's workers by names.");
		questions.add("2a. List a company's staff by salary in descending order.");
		questions.add("3a. List companies' labor cost (total salaries and wage rates by 1920 hours) in descending order.");
		questions.add("4a. Find all the jobs a person is currently holding.");
		questions.add("5a. List a person's knowledge/skills in a readable format.");
		questions.add("6a. List the skill gap of a worker between his/her job(s) and his/her skills.");
		questions.add("7a. List the required knowledge/skills of a job profile in a readable format.");
		questions.add("8a. List a person's missing knowledge/skills for a specific job in a readable format.");
		questions.add("9a. Find the courses each of which alone can cover a given skill set.");
		questions.add("10a. List the courses (course id and title) that each alone teaches all the missing knowledge/skills for a person to pursue a specific job.");
		questions.add("11a. Suppose the skill gap of a worker and the requirement of a desired job can be covered by one course. Find the 'quickest' solution for this worker. Show the course and the completing date.");
		questions.add("12a. If query #10 returns nothing, then find the course sets with the minimum number of courses that their combination covers the given skill set. The considered course sets will not include more than three courses.");
		questions.add("13a. List the course sets that their combinations cover all the missing knowledge/skills for a person to pursue a specific job. The considered course sets will not include more than three courses.");
		questions.add("14a. Find the cheapest course choices to make up one's skill gap by showing the courses to take and the total cost. The considered course sets will not include more than three courses.");
		questions.add("15a. List all the job profiles that a person is qualified.");
		questions.add("16a. Find the job with the highest pay rate for a person according to his/her skill qualification.");
		questions.add("17a. List all the names along with the emails of the persons who are qualified for a job profile.");
		questions.add("18a. When a company cannot find any qualified person for a job, a secondary solution is to find a person who is almost qualified to the job. Make a 'missing-one' list that lists people who miss only one skill for a specified job profile.");
		questions.add("19a. List the skillID and the number of people in the missing-one list for a given job profile in the ascending order of the people counts.");
		questions.add("20a. Suppose there is a new job profile that has nobody qualified. List the persons who miss the least number of skills and report the 'least number'.");
		questions.add("21a. For a specified job profile and a given small number k, make a 'missing-k' list that lists the people's IDs and the number of missing skills for the people who miss only up to k skills in the ascending order of missing skills.");
		questions.add("22a. Given a job profile and its corresponding missing-k list specified in Question 22. Find every skill that is needed by at least one person in the given missing-k list. List each skillID and the number of people who need it in the descending order of the people counts.");
		questions.add("23a. In a local or national crisis, we need to find all the people who once held a job of the special job-profile identifier.");
		questions.add("24a. Find all the unemployed people who once held a job of the given job-profile identifier.");
		questions.add("25a. Find out the biggest employer in terms of number of employees or the total amount of salaries and wages paid to employees.");
		questions.add("28a. Find the job profiles that have the most openings due to lack of qualified workers. If there are many opening jobs of a job profile but at the same time there are many qualified jobless people. Then training cannot help fill up this type of job. What we want to find is such a job profile that has the largest difference between vacancies (the unfilled jobs of this job profile) and the number of jobless people who are qualified for this job profile.");
		questions.add("29a. Find the courses that can help most jobless people find a job by training them toward the job profiles that have the most openings due to lack of qualified workers..");
		questions.add("30a. List all the courses, directly or indirectly required, that a person has to take in order to be qualified for a job of the given profile, according to his/her skills possessed and courses taken.");


//		questions.add("1b. List a company's workers by names.");
//		questions.add("2b. List a company's staff by salary in descending order.");
//		questions.add("3b. List companies' labor cost (total salaries and wage rates by 1920 hours) in descending order.");
//		questions.add("4b. Find all the jobs a person is currently holding.");
//		questions.add("5b. List all the workers who are working for a specific project.");
//		questions.add("6b. List a person's knowledge/skills in a readable format.");
//		questions.add("7b. List the skill gap of a worker between his/her job(s) and his/her skills.");
//		questions.add("8b. List the required knowledge/skills of a job profile in a readable format.");
//		questions.add("9b. List a person's missing knowledge/skills for a specific job in a readable format.");
//		questions.add("10b. Find the courses each of which alone can cover a given skill set.");
//		questions.add("11b. List the courses (course id and title) that each alone teaches all the missing knowledge/skills for a person to pursue a specific job.");
//		questions.add("12b. Suppose the skill gap of a worker and the requirement of a desired job can be covered by one course. Find the 'quickest' solution for this worker. Show the course and the completing date.");
//		questions.add("13b. If query #10 returns nothing, then find the course sets with the minimum number of courses that their combination covers the given skill set. The considered course sets will not include more than three courses.");
//		questions.add("14b. List the course sets that their combinations cover all the missing knowledge/skills for a person to pursue a specific job. The considered course sets will not include more than three courses.");
//		questions.add("15b. Find the cheapest course choices to make up one's skill gap by showing the courses to take and the total cost. The considered course sets will not include more than three courses.");
//		questions.add("16b. List all the job profiles that a person is qualified.");
//		questions.add("17b. Find the job with the highest pay rate for a person according to his/her skill qualification.");
//		questions.add("18b. List all the names along with the emails of the persons who are qualified for a job profile.");
//		questions.add("19b. When a company cannot find any qualified person for a job, a secondary solution is to find a person who is almost qualified to the job. Make a 'missing-one' list that lists people who miss only one skill for a specified job profile.");
//		questions.add("20b. List the skillID and the number of people in the missing-one list for a given job profile in the ascending order of the people counts.");
//		questions.add("21b. Suppose there is a new job profile that has nobody qualified. List the persons who miss the least number of skills and report the 'least number'.");
//		questions.add("22b. For a specified job profile and a given small number k, make a 'missing-k' list that lists the people's IDs and the number of missing skills for the people who miss only up to k skills in the ascending order of missing skills.");
//		questions.add("23b. Given a job profile and its corresponding missing-k list specified in Question 22. Find every skill that is needed by at least one person in the given missing-k list. List each skillID and the number of people who need it in the descending order of the people counts.");
//		questions.add("24b. In a local or national crisis, we need to find all the people who once held a job of the special job-profile identifier.");
//		questions.add("25b. Find all the unemployed people who once held a job of the given job-profile identifier.");
//		questions.add("26b. Find out the biggest employer in terms of number of employees or the total amount of salaries and wages paid to employees.");

		
	}
	public void addQueries()
	{
		queries.add("SELECT name FROM Job_company NATURAL JOIN Has_Job NATURAL JOIN Person WHERE comp_id = 100");
		//1a
		queries.add("SELECT name, pay_rate FROM Job_company NATURAL JOIN Job NATURAL JOIN Has_Job NATURAL JOIN person "
				+ "WHERE pay_type = 'salary' AND comp_id = 100 ORDER BY pay_rate DESC");
		//2a
		queries.add("WITH SALARY AS (SELECT comp_id, SUM(pay_rate) AS worker_salary FROM Job NATURAL JOIN job_company WHERE pay_type = 'salary' GROUP BY comp_id), HOURLY AS (SELECT comp_id, SUM(pay_rate* 1920) AS worker_salary " 
				+"FROM Job NATURAL JOIN job_company WHERE pay_type = 'wage' GROUP BY comp_id), labor_cost AS( (select * FROM salary) UNION (select * FROM hourly) )"
				+ "SELECT comp_id, SUM(worker_salary) AS total FROM labor_cost GROUP BY comp_id ORDER BY total DESC");
		//3a
		queries.add("SELECT job_code FROM Has_Job WHERE end_date IS NULL AND per_id = 2220000");
		//4a
		queries.add("SELECT ks_code, title FROM Experience NATURAL JOIN Knowledge_Skill WHERE per_id = 3330000");
		//5a
		queries.add("(SELECT ks_code FROM Skills NATURAL JOIN job_profile NATURAL JOIN job WHERE job_code = (SELECT job_code FROM has_job WHERE end_date is null AND per_id = 1000000))"
				+ "MINUS (SELECT ks_code FROM Experience WHERE per_id = 1000000)");
		//6a
		queries.add("SELECT ks_code, title FROM Skills NATURAL JOIN Knowledge_Skill WHERE pos_code = 1");
		//7a
		queries.add("WITH missing_skills AS ((SELECT ks_code FROM Skills WHERE pos_code = 1) MINUS (SELECT ks_code FROM Experience WHERE per_id = 3330000))"
				+ "SELECT title FROM Knowledge_Skill NATURAL JOIN missing_skills");
		//8a
		queries.add("WITH skill_set AS((SELECT ks_code FROM skills WHERE pos_code = 2)) SELECT distinct c_code,title FROM course c " 
				+ "WHERE NOT EXISTS( SELECT ks_code FROM skill_set MINUS SELECT ks_code FROM teaches t WHERE t.c_code = c.c_code )");
		//9a
		queries.add("WITH skill_set AS((SELECT ks_code FROM skills WHERE pos_code = 2) MINUS (SELECT ks_code FROM experience WHERE per_id = 1000000)) "
				+ "SELECT distinct c_code, title FROM course c WHERE NOT EXISTS(SELECT ks_code FROM skill_set MINUS SELECT ks_code FROM teaches t WHERE t.c_code = c.c_code)");
		//10a
		
		queries.add("WITH missing_skill AS ((SELECT ks_code FROM skills WHERE pos_code = 2) MINUS (SELECT ks_code FROM experience WHERE per_id = 1000000)), "
				+ "possible_courses AS(SELECT distinct c_code FROM course c WHERE NOT EXISTS((SELECT ks_code FROM missing_skill) MINUS (SELECT ks_code "
                + "FROM teaches t WHERE t.c_code = c.c_code))) SELECT distinct c_code, sec_no,format, complete_date, year, price, semester FROM course NATURAL JOIN section " 
                + "WHERE sec_no = (SELECT distinct s.sec_no FROM missing_skill, section s WHERE s.complete_date = (SELECT MIN(complete_date) FROM possible_courses NATURAL JOIN section "
                + "WHERE complete_date > '20-APR-16'))");
		//11a
		queries.add("WITH missing_skill AS ((SELECT ks_code FROM skills WHERE pos_code = 2) MINUS (SELECT ks_code FROM experience WHERE per_id = 1000000)), "
				+ "CourseSet_Skill(csetID, ks_code) AS (SELECT csetID, ks_code FROM CourseSet CSet JOIN teaches CS ON CSet.c_code1=CS.c_code "
				+ "UNION SELECT csetID, ks_code FROM CourseSet CSet JOIN teaches CS ON CSet.c_code2=CS.c_code UNION SELECT csetID, ks_code FROM CourseSet CSet JOIN teaches CS ON CSet.c_code3=CS.c_code ), "
				+ "Cover_CSet(csetID, sizet) AS (SELECT csetID, sizet FROM CourseSet CSet WHERE NOT EXISTS (SELECT ks_code FROM missing_skill MINUS SELECT ks_code "
                + "FROM CourseSet_Skill CSSk WHERE CSSk.csetID = Cset.csetID)) SELECT c_code1, c_code2, c_code3 FROM Cover_CSet NATURAL JOIN CourseSet WHERE sizet = (SELECT MIN(sizet)  FROM Cover_CSet)");	
		//12a

		queries.add("WITH missing_skill AS ((SELECT ks_code FROM skills WHERE pos_code = 2) MINUS (SELECT ks_code FROM experience WHERE per_id = 1000000)), "
				+ "CourseSet_Skill(csetID, ks_code) AS (SELECT csetID, ks_code FROM CourseSet CSet JOIN teaches CS ON CSet.c_code1=CS.c_code "
				+ "UNION SELECT csetID, ks_code FROM CourseSet CSet JOIN teaches CS ON CSet.c_code2=CS.c_code UNION SELECT csetID, ks_code FROM CourseSet CSet JOIN teaches CS ON CSet.c_code3=CS.c_code ), "
				+ "Cover_CSet(csetID, sizet) AS (SELECT csetID, sizet FROM CourseSet CSet WHERE NOT EXISTS (SELECT ks_code FROM missing_skill MINUS SELECT ks_code "
                + "FROM CourseSet_Skill CSSk WHERE CSSk.csetID = Cset.csetID)) SELECT c_code1, c_code2, c_code3 FROM Cover_CSet NATURAL JOIN CourseSet WHERE sizet = (SELECT MIN(sizet)  FROM Cover_CSet)");
		//13a
		queries.add("WITH missing_skill AS((SELECT ks_code FROM skills WHERE pos_code = 2) MINUS (SELECT ks_code FROM experience WHERE per_id = 1000000)), "
				+"CourseSet_Skill(csetID, ks_code) AS (SELECT csetID, ks_code FROM CourseSet CSet JOIN teaches CS ON CSet.c_code1=CS.c_code UNION SELECT csetID, ks_code "
				+"FROM CourseSet CSet JOIN teaches CS ON CSet.c_code2=CS.c_code UNION SELECT csetID, ks_code FROM CourseSet CSet JOIN teaches CS ON CSet.c_code3=CS.c_code), "
				+"Cover_CSet(csetID, sizet) AS (SELECT csetID, sizet FROM CourseSet CSet WHERE NOT EXISTS (SELECT ks_code FROM missing_skill MINUS SELECT ks_code FROM CourseSet_Skill CSSk "
				+"WHERE CSSk.csetID = Cset.csetID)), total_cost AS (SELECT csetID, ((SELECT price FROM section C1 WHERE CS.c_code1 = C1.c_code) + (SELECT price FROM section C2 WHERE CS.c_code2 = C2.c_code) "
				+ "+ (SELECT price FROM section C3 WHERE CS.c_code3 = C3.c_code))AS total FROM CourseSet CS NATURAL JOIN Cover_CSEt) SELECT * FROM total_cost ORDER BY total DESC");
		
		//14a
		
		
		queries.add("SELECT job_profile.pos_code, job_profile.title FROM Job_Profile job_profile WHERE NOT EXISTS ((SELECT skills.ks_code FROM Skills skills WHERE job_profile.pos_code = skills.pos_code)"
				+"MINUS (SELECT ks_code FROM Experience WHERE per_id = 2220000))");
		
		//15a
		
		
		queries.add("WITH jobs-qualified AS (SELECT title, avg_pay FROM Job_Profile job_profile WHERE NOT EXISTS ((SELECT skills.ks_code FROM Skills skills "
				+"WHERE job_profile.pos_code = skills.pos_code) MINUS (SELECT ks_code FROM Experience WHERE per_id = 2220000))) SELECT title, avg_pay "
				+"FROM jobs-qualified WHERE avg_pay = (SELECT MAX(avg_pay) FROM jobs-qualified)");
		
		//16a
		
		queries.add("SELECT name, email FROM Person peep WHERE NOT EXISTS ((SELECT ks_code FROM Skills WHERE pos_code = 3)"
				+"MINUS (SELECT ks_code FROM Experience ex WHERE ex.per_id = peep.per_id))");
		
		//17a
		
		queries.add("SELECT per_id FROM experience NATURAL JOIN skills WHERE pos_code = 1 GROUP BY(per_id) HAVING (SELECT COUNT (*) FROM skills WHERE pos_code = 1) - COUNT(per_id) = 1");
		
		//18a
		
		queries.add("WITH missing_one AS(SELECT per_id FROM experience NATURAL JOIN skills WHERE pos_code=1 GROUP BY(per_id) HAVING (SELECT COUNT(*) FROM skills WHERE pos_code=1  ) "
				+" - COUNT(per_id) =1) select ks_code, count(*) as num_person from knowledge_skill,missing_one M WHERE ks_code= (Select ks_code from(  (select ks_code from skills where pos_code= 1) "
				+ "minus ( select K.ks_code from experience K where K.per_id = M.per_id)) ) GROUP BY (ks_code) order by num_person asc");
		
		//19a
		
		queries.add("WITH SKILLS_NEEDED(per_id, need) AS((SELECT per_id, ((SELECT COUNT(*) FROM skills WHERE pos_code = 11) - COUNT(per_id)) AS NEED "
				+"FROM experience NATURAL JOIN skills WHERE pos_code = 11 GROUP BY per_id)) SELECT per_id, need FROM SKILLS_NEEDED WHERE need= (SELECT MIN(need) "
				+"FROM SKILLS_NEEDED)");
		//20a
		
		queries.add("WITH SKILLS_NEEDED(per_id, need) AS((SELECT per_id, ((SELECT COUNT(*) FROM skills WHERE pos_code = 11) - COUNT(per_id)) AS NEED "
				+"FROM experience NATURAL JOIN skills WHERE pos_code = 11 GROUP BY per_id)) SELECT * FROM (SELECT per_id,need FROM SKILLS_NEEDED sn "
				+"UNION SELECT per_id,(SELECT COUNT(*) FROM skills WHERE pos_code = 11) FROM person p WHERE NOT EXISTS (SELECT NULL FROM skills_needed s_n "
				+"WHERE p.per_id = s_n.per_id)) --Where person is not in skills needed WHERE need <= 3 ORDER BY need ASC");

		//21a
		
		queries.add("WITH SKILLS_NEEDED(per_id, need) AS((SELECT per_id, ((SELECT COUNT(*) FROM skills WHERE pos_code = 11) - COUNT(per_id)) AS NEED "
				+"FROM experience NATURAL JOIN skills WHERE pos_code = 11 GROUP BY per_id)) SELECT ks_code, COUNT(*) as num_ppl FROM skills s, skills_needed sn "
				+"WHERE EXISTS (SELECT * FROM ((SELECT ks_code FROM skills WHERE pos_code = 11) MINUS (SELECT ks_code FROM experience e WHERE e.per_id = sn.per_id)) "
				+"WHERE ks_code = s.ks_code) AND need <= 3 GROUP BY ks_code ORDER BY num_ppl DESC");
		
		//22a
		
		queries.add("SELECT per_id, name, job_code, end_date FROM Person NATURAL JOIN Has_Job WHERE job_code = 3 AND end_date IS NOT NULL");
		//23a
		
		queries.add("WITH unemployed as((select name from has_job natural join person where end_date IS NOT NULL) MINUS (select name from has_job natural join person "
				+"where end_date IS NULL)) SELECT name FROM unemployed");
		//24a
		
		queries.add("WITH employed AS(SELECT * FROM has_job JOIN job USING(job_code) JOIN job_company USING(job_code) WHERE end_date IS NULL),count_employees AS"
				+ "(SELECT comp_id, COUNT(comp_id) AS employees FROM employed GROUP BY comp_id) SELECT comp_id, employees FROM count_employees WHERE employees = (SELECT MAX(employees) FROM count_employees)");
		
		//25a
		
//=======
//		//23
//		queries.add("SELECT person_id,name FROM job_profile NATURAL JOIN job JOIN person USING(person_id) WHERE job_title= 'virus analyst'");
//		//24
//		queries.add("SELECT person_id,name FROM person NATURAL JOIN job JOIN job_profile using(pos_code) WHERE end_date <= to_date('08-NOV-15') AND job_title ='virus analyst'");
//		//25
//>>>>>>> dont remember
//		queries.add("WITH number_jobs(company_id,job_count) AS (SELECT company_id, COUNT(*) FROM job GROUP BY (company_id) ) SELECT company_id FROM number_jobs WHERE job_count = (SELECT MAX(job_count)  FROM number_jobs)");
//		//26a
//
//		queries.add("SELECT DISTINCT name FROM person NATURAL JOIN job WHERE company_id = 3");
//		//1b
//		queries.add("SELECT name, job_title, salary FROM person NATURAL JOIN job NATURAL JOIN job_profile "
//				+ "WHERE company_id=3 AND job_type='full-time' ORDER BY salary DESC");
//		//2b
//		queries.add("SELECT company_name,(SUM(salary)+SUM(wage_rate*1920)) AS total "
//				+ "FROM company NATURAL JOIN job GROUP BY (company_name)");
//		//3b
//		queries.add("SELECT name, job_title FROM person NATURAL JOIN job NATURAL JOIN job_profile WHERE person_id = 10");
//		//4b
//		queries.add("SELECT name FROM work_on NATURAL JOIN person NATURAL JOIN job WHERE project_id = 2");
//		//5b
//		queries.add("SELECT name, skill_title, description FROM person NATURAL JOIN knows_skill NATURAL JOIN skill "
//				+ "WHERE person_id = 10");
//		//6b
//		queries.add("SELECT skill_id FROM "
//				+ "(SELECT skill_id FROM job_profile NATURAL JOIN job NATURAL JOIN skill_require "
//				+ "WHERE job_id = (SELECT job_id FROM job WHERE person_id = 13) )"
//				+ "MINUS (SELECT skill_id FROM knows_skill WHERE person_id = 13)");
//		//7b
//		queries.add("SELECT skill_title FROM job NATURAL JOIN skill_require NATURAL JOIN skill WHERE job_id = 2");
//		//8b
//		queries.add("SELECT skill_id FROM "
//				+ "(SELECT skill_id FROM skill_require WHERE pos_code = 2) "
//				+ "MINUS "
//				+ "(SELECT skill_id FROM knows_skill WHERE person_id = 11)");
//		//9b
//		
//		queries.add("SELECT course_id, course_title FROM( "
//				+ "WITH missing_skill AS( "
//				+ "(SELECT skill_id FROM skill_require WHERE pos_code = 4) )"
//				+ "SELECT course_id, course_title FROM course c "
//				+ "WHERE NOT EXISTS( SELECT skill_id FROM missing_skill "
//				+ "MINUS "
//				+ "SELECT skill_id FROM course_skill cs "
//				+ "WHERE cs.course_id = c.course_id ))");
//		//10b
//		
//		queries.add("SELECT course_id, course_title FROM( "
//				+ "WITH missing_skill AS( "
//				+ "(SELECT skill_id FROM skill_require WHERE pos_code = 3) "
//				+ "MINUS "
//				+ "(SELECT skill_id FROM knows_skill WHERE person_id = 11) ) "
//				+ "SELECT course_id, course_title FROM course c "
//				+ "WHERE NOT EXISTS( SELECT skill_id FROM missing_skill "
//				+ "MINUS "
//				+ "SELECT skill_id FROM course_skill cs "
//				+ "WHERE cs.course_id = c.course_id ))");
//		
//		
//		//11b
//		queries.add("SELECT course_id, section_id, end_date FROM ( "
//				+ "WITH missing_skill AS ( "
//				+ "(SELECT skill_id FROM skill_require WHERE pos_code = 3)"
//				+ " MINUS"
//				+ "(SELECT skill_id FROM knows_skill WHERE person_id = 11 ) ), "
//				+ "possible_courses AS ( "
//				+ "SELECT course_id FROM course c "
//				+ "WHERE NOT EXISTS( "
//				+ "(SELECT skill_id FROM missing_skill) "
//				+ "MINUS "
//				+ "(SELECT skill_id FROM course_skill cs "
//				+ "WHERE cs.course_id = c.course_id) ) ) "
//				+ "SELECT course_id, section_id, end_date "
//				+ "FROM possible_courses NATURAL JOIN section "
//				+ "ORDER BY end_date ASC "
//				+ "FETCH FIRST 1 ROWS ONLY )");	
//		//12b
//
//		queries.add("SELECT course_id1, course_id2, course_id3 FROM "
//				+ "(WITH missing_skill AS (  "
//				+ "(SELECT skill_id FROM skill_require WHERE pos_code = 3) "
//				+ "MINUS  "
//				+ "(SELECT skill_id FROM knows_skill WHERE person_id = 11 ) ), "
//				+ "CourseSet_Skill(csetID, skill_id) AS ( "
//				+ "SELECT csetID, skill_id "
//				+ "FROM CourseSet CSet JOIN course_skill CS ON CSet.course_id1=CS.course_id "
//				+ "UNION "
//				+ "SELECT csetID, skill_id "
//				+ "FROM CourseSet CSet JOIN course_skill CS ON CSet.course_id2=CS.course_id "
//				+ "UNION "
//				+ "SELECT csetID, skill_id "
//				+ "FROM CourseSet CSet JOIN course_skill CS ON CSet.course_id3=CS.course_id ), "
//				+ "/* use division to find those course sets that cover missing skills */ "
//				+ "Cover_CSet(csetID, siz) AS ( "
//				+ "SELECT csetID, siz FROM CourseSet CSet "
//				+ "WHERE NOT EXISTS ( "
//				+ "SELECT skill_id FROM Missing_Skill "
//				+ "MINUS "
//				+ "SELECT skill_id FROM CourseSet_Skill CSSk WHERE CSSk.csetID = Cset.csetID ) ) "
//				+ "/* to find the smallest sets */ "
//				+ "SELECT course_id1, course_id2, course_id3 "
//				+ "FROM Cover_CSet NATURAL JOIN CourseSet "
//				+ "WHERE siz = (SELECT MIN(siz) FROM Cover_CSet) )");
//			//13b
//		queries.add("SELECT course_id1, course_id2, course_id3 FROM "
//				+ "(WITH missing_skill AS ( "
//				+ "(SELECT skill_id FROM skill_require WHERE pos_code = 3) "
//				+ "MINUS"
//				+ "(SELECT skill_id FROM knows_skill WHERE person_id = 11 ) ), "
//				+ "CourseSet_Skill(csetID, skill_id) AS ( "
//				+ "SELECT csetID, skill_id "
//				+ "FROM CourseSet CSet JOIN course_skill CS ON CSet.course_id1=CS.course_id "
//				+ "UNION "
//				+ "SELECT csetID, skill_id "
//				+ "FROM CourseSet CSet JOIN course_skill CS ON CSet.course_id2=CS.course_id "
//				+ "UNION SELECT csetID, skill_id "
//				+ "FROM CourseSet CSet JOIN course_skill CS ON CSet.course_id3=CS.course_id ), "
//				+ "/* use division to find those course sets that cover missing skills */ "
//				+ "Cover_CSet(csetID, siz) AS ( "
//				+ "SELECT csetID, siz FROM CourseSet CSet WHERE NOT EXISTS "
//				+ "( SELECT skill_id FROM Missing_Skill "
//				+ "MINUS "
//				+ "SELECT skill_id FROM CourseSet_Skill CSSk "
//				+ "WHERE CSSk.csetID = Cset.csetID ) ) "
//				+ "/* to find the  sets */ "
//				+ "SELECT course_id1, course_id2, course_id3 "
//				+ "FROM Cover_CSet NATURAL JOIN CourseSet) ");
//		//14b
//		
//		
//		queries.add("SELECT * FROM CourseSet NATURAL JOIN (SELECT csetID, total FROM "
//				+ "(WITH missing_skill AS ((SELECT skill_id FROM skill_require WHERE pos_code = 3) "
//				+ "MINUS (SELECT skill_id FROM knows_skill WHERE person_id = 11 ) ), "
//				+ "CourseSet_Skill(csetID, skill_id) AS ( SELECT csetID, skill_id FROM "
//				+ "CourseSet CSet JOIN course_skill CS ON CSet.course_id1=CS.course_id "
//				+ "UNION SELECT csetID, skill_id FROM CourseSet CSet JOIN course_skill CS "
//				+ "ON CSet.course_id2=CS.course_id UNION SELECT csetID, skill_id "
//				+ "FROM CourseSet CSet JOIN course_skill CS ON CSet.course_id3=CS.course_id ), "
//				+ "/* use division to find those course sets that cover missing skills "
//				+ "*/ Cover_CSet(csetID, siz) AS ( SELECT csetID, siz FROM CourseSet CSet WHERE NOT EXISTS "
//				+ "( SELECT skill_id FROM Missing_Skill MINUS SELECT skill_id FROM CourseSet_Skill CSSk "
//				+ "WHERE CSSk.csetID = Cset.csetID ) ), total_cost AS ( SELECT csetID, ( "
//				+ "(SELECT coalesce(retail_price,0) "
//				+ "FROM Course C1 WHERE coalesce(CS.course_id1,0) = coalesce(C1.course_id,0)) "
//				+ "+ (SELECT coalesce(retail_price,0)  FROM Course C2 WHERE coalesce(CS.course_id2,0) "
//				+ "= coalesce(C2.course_id,0)) "
//				+ "+ (SELECT coalesce(retail_price,0)  FROM Course C3 WHERE coalesce(CS.course_id3,0) "
//				+ "= coalesce(C3.course_id,0) ) )AS total FROM CourseSet CS NATURAL JOIN Cover_CSEt )  "
//				+ "/* to find the cheapest sets */ "
//				+ "SELECT * FROM total_cost ORDER BY total ASC FETCH FIRST 3 ROWS ONLY))");
////15b
//		
//				
//		
//		queries.add("SELECT j.pos_code, j.job_title "
//				+ "FROM job_profile j WHERE NOT EXISTS (   "
//				+ "( SELECT R.skill_id  FROM skill_require R  WHERE R.pos_code=J.pos_code )"
//				+ " MINUS "
//				+ "( SELECT skill_id  FROM  knows_skill WHERE person_id=13 ) )");
//		//16b
//		queries.add("SELECT job_id, salary FROM job NATURAL JOIN (SELECT j.job_id FROM job j WHERE NOT EXISTS "
//				+ "( ( SELECT R.skill_id  FROM skill_require R  WHERE J.pos_code=R.pos_code) "
//				+ "MINUS "
//				+ "( SELECT skill_id FROM  person NATURAL JOIN knows_skill WHERE name = 'Gary King' ) ) ) "
//				+ "ORDER BY (SALARY) DESC FETCH FIRST 1 ROWS ONLY");
////17b
//		queries.add("SELECT name,email FROM person P  WHERE NOT EXISTS( "
//				+ "( SELECT skill_id FROM skill_require WHERE pos_code=3 ) "
//				+ "MINUS ( SELECT skill_id FROM knows_skill K WHERE P.person_id = K.person_id) )");
//		//18b
//		queries.add("SELECT person_id FROM knows_skill NATURAL JOIN skill_require WHERE pos_code=5 "
//				+ "GROUP BY(person_id) HAVING  (SELECT COUNT(*) FROM skill_require Where pos_code=5)-"
//				+ " COUNT(person_id)=1");
//		//19b
//		queries.add("WITH missing_one AS( SELECT person_id FROM knows_skill "
//				+ "NATURAL JOIN skill_require WHERE pos_code=12 "
//				+ "GROUP BY(person_id) HAVING  "
//				+ "(SELECT COUNT(*) FROM skill_require WHERE pos_code=12  )- COUNT(person_id) =1)  "
//				+ "select skill_id, count(*) as num_person from skill,missing_one M "
//				+ "WHERE skill_id= (Select skill_id from(  (select skill_id from skill_require where pos_code=12) "
//				+ "minus ( select K.skill_id  from knows_skill K where K.person_id = M.person_id)) ) "
//				+ "GROUP BY (skill_id) order by num_person asc");
//		//20b
//		queries.add("WITH number_needs(person_id,needs) AS (  "
//				+ "(SELECT person_id,((SELECT COUNT(*) FROM skill_require WHERE pos_code=14)-count(person_id)) "
//				+ "as needs FROM knows_skill natural join skill_require WHERE pos_code = 14 GROUP BY (person_id) ) ) "
//				+ "SELECT person_id,needs FROM  number_needs WHERE needs= (SELECT MIN(needs)  FROM number_needs)");
//
//		//21b
//		queries.add("WITH number_needs(person_id,needs) AS (  "
//				+ "(SELECT person_id,((SELECT COUNT(*) FROM skill_require WHERE pos_code=13)"
//				+ "-count(person_id)) as needs FROM knows_skill NATURAL JOIN skill_require WHERE pos_code=13 "
//				+ "GROUP BY (person_id) ) ) SELECT * FROM(SELECT person_id,needs FROM number_needs N "
//				+ "UNION SELECT person_id, (SELECT count(*) FROM skill_require WHERE pos_code=13) "
//				+ "FROM person P WHERE P.person_id not in (SELECT person_id FROM number_needs) ) WHERE needs<=6 "
//				+ "ORDER BY needs ASC");
//
//		//22b
//		queries.add("WITH number_needs(person_id,needs) AS (  (SELECT person_id,((SELECT COUNT(*) "
//				+ "FROM skill_require WHERE pos_code=2)-count(person_id)) as needs FROM knows_skill "
//				+ "NATURAL JOIN skill_require WHERE pos_code=2 GROUP BY (person_id) )) "
//				+ "SELECT skill_id, count(*) as num_person FROM skill, number_needs M "
//				+ "WHERE skill_id in (SELECT skill_id FROM (  (SELECT skill_id FROM skill_require WHERE pos_code=2) "
//				+ "minus "
//				+ "( SELECT K.skill_id  FROM knows_skill K WHERE K.person_id = M.person_id)) ) "
//				+ "GROUP BY (skill_id) ORDER BY num_person DESC");
//		//23b
//		queries.add("SELECT person_id,name FROM job_profile NATURAL JOIN job JOIN person "
//				+ "USING(person_id) WHERE job_title= 'Web Developer II'");
//		//24b
//		queries.add("SELECT person_id,name FROM person NATURAL JOIN job JOIN job_profile "
//				+ "using(pos_code) WHERE job_title ='Web Developer II' AND end_date <= to_date('08-NOV-15') ");
//		//25b
//		queries.add("WITH number_jobs(company_id,job_count) AS "
//				+ "(SELECT company_id, COUNT(*) FROM job GROUP BY (company_id) ) "
//				+ "SELECT company_id FROM number_jobs WHERE job_count = "
//				+ "(SELECT MAX(job_count)  FROM number_jobs)");
//		//26b
//
//		
		
	}
	public void createMap()
	{
		for(int i =0; i<queries.size();i++)
		{
			this.map.put(questions.get(i), queries.get(i));
		}
	}
	public String[] getQuestionList()
	{
		String[] ary=new String[1];
		ary=(String[]) this.questions.toArray(ary);
		return ary;
	}
	public String getQuery(String question)
	{
		return (String)map.get(question);
	}
}
