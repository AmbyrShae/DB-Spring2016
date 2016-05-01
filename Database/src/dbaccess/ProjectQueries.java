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
		
		queries.add("WITH missing_skill AS ((SELECT ks_code FROM skills WHERE pos_code = 2) MINUS (SELECT ks_code FROM experience WHERE per_id = 1000000)), possible_courses AS( "
				+ "SELECT c_code FROM course c WHERE NOT EXISTS((SELECT ks_code FROM missing_skill) MINUS (SELECT ks_code FROM teaches t WHERE t.c_code = c.c_code))), possible_section AS (SELECT * "
				+ "FROM possible_courses NATURAL JOIN section WHERE complete_date > '20-APR-16') SELECT c_code, sec_no,format, complete_date, year, price, semester FROM possible_section "
				+ "WHERE complete_date = (SELECT MIN(complete_date) FROM possible_section)");
		//11a
		queries.add("WITH missing_skill AS ((SELECT ks_code FROM skills WHERE pos_code = 11) MINUS (SELECT ks_code FROM experience WHERE per_id = 1000000)), "
				+ "CourseSet_Skill(csetID, ks_code) AS (SELECT csetID, ks_code FROM CourseSet CSet JOIN teaches CS ON CSet.c_code1=CS.c_code "
				+ "UNION SELECT csetID, ks_code FROM CourseSet CSet JOIN teaches CS ON CSet.c_code2=CS.c_code UNION SELECT csetID, ks_code FROM CourseSet CSet JOIN teaches CS ON CSet.c_code3=CS.c_code ), "
				+ "Cover_CSet(csetID, sizet) AS (SELECT csetID, sizet FROM CourseSet CSet WHERE NOT EXISTS (SELECT ks_code FROM missing_skill MINUS SELECT ks_code "
                + "FROM CourseSet_Skill CSSk WHERE CSSk.csetID = Cset.csetID)) SELECT c_code1, c_code2, c_code3 FROM Cover_CSet NATURAL JOIN CourseSet WHERE sizet = (SELECT MIN(sizet)  FROM Cover_CSet)");	
		//12a

		queries.add("WITH missing_skill AS ((SELECT ks_code FROM skills WHERE pos_code = 11) MINUS (SELECT ks_code FROM experience WHERE per_id = 1000000)), "
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
		
		
		queries.add("WITH jobs_qualified AS (SELECT pos_code, title FROM Job_Profile jp WHERE NOT EXISTS ((SELECT s.ks_code FROM Skills s WHERE jp.pos_code = s.pos_code) MINUS (SELECT ks_code FROM Experience "
				+ "WHERE per_id = 2220000))), job_pay(job_code, amount) AS (SELECT job_code, CASE WHEN pay_type = 'salary' THEN pay_rate ELSE pay_rate * 1920 END FROM job) SELECT job_code, title, amount "
				+ "FROM jobs_qualified NATURAL JOIN job_pay NATURAL JOIN job WHERE amount = (SELECT MAX(amount) FROM jobs_qualified NATURAL JOIN job_pay NATURAL JOIN job)");
		
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
		
		queries.add("SELECT per_id, name, pos_code FROM Person NATURAL JOIN Has_Job NATURAL JOIN Job WHERE pos_code = 3 AND end_date IS NOT NULL");
		//23a
		
		queries.add("WITH everyone AS (SELECT * FROM has_job FULL OUTER JOIN person USING (per_id)), unemployed AS ((SELECT name, per_id FROM everyone) MINUS (SELECT name, per_id FROM everyone "
				+ "WHERE start_date IS NOT NULL AND end_date IS NULL)) SELECT name, per_id FROM job NATURAL JOIN has_job NATURAL JOIN unemployed WHERE pos_code = 2");
		//24a
		
		queries.add("WITH employed AS(SELECT * FROM has_job JOIN job USING(job_code) JOIN job_company USING(job_code) WHERE end_date IS NULL),count_employees AS"
				+ "(SELECT comp_id, COUNT(comp_id) AS employees FROM employed GROUP BY comp_id) SELECT comp_id, employees FROM count_employees WHERE employees = (SELECT MAX(employees) FROM count_employees)");
		
		//25a
		
		queries.add("WITH everyone AS (SELECT * FROM has_job FULL OUTER JOIN person USING (per_id)), unemployed AS ((SELECT name, per_id FROM everyone) MINUS (SELECT name, per_id FROM everyone "
				+ "WHERE start_date IS NOT NULL AND end_date IS NULL)), openings AS (SELECT job_code, pos_code FROM ((SELECT job_code FROM job) MINUS (SELECT job_code FROM has_job WHERE end_date IS NULL)) NATURAL JOIN job), "
				+ "qualified AS (SELECT DISTINCT name, per_id, pos_code FROM unemployed u, openings o WHERE NOT EXISTS (SELECT ks_code FROM openings NATURAL JOIN skills WHERE job_code = o.job_code MINUS "
				+ "SELECT ks_code FROM unemployed NATURAL JOIN experience WHERE per_id = u.per_id)), count_openings AS (SELECT pos_code,COUNT(job_code) AS numOfOpenings FROM openings GROUP BY pos_code), "
				+ "count_qualified AS (SELECT COUNT(pos_code) AS numOfQual, pos_code FROM qualified GROUP BY pos_code) SELECT title, pos_code, numofopenings - coalesce(numOfQual,0) AS openings FROM (count_qualified "
				+ "FULL OUTER JOIN count_openings USING (pos_code)) NATURAL JOIN job_profile WHERE numofopenings - coalesce(numOfQual,0) > 0 ORDER BY openings DESC");
		//28a
		
		queries.add("WITH everyone AS (SELECT * FROM has_job FULL OUTER JOIN person USING (per_id)), unemployed AS ((SELECT name, per_id FROM everyone) MINUS (SELECT name, per_id FROM everyone "
				+ "WHERE start_date IS NOT NULL AND end_date IS NULL)), openings AS (SELECT job_code, pos_code FROM ((SELECT job_code FROM job) MINUS (SELECT job_code FROM has_job WHERE end_date IS NULL)) NATURAL JOIN job), "
				+ "qualified AS (SELECT DISTINCT name, per_id, pos_code FROM unemployed u, openings o WHERE NOT EXISTS (SELECT ks_code FROM openings NATURAL JOIN skills WHERE job_code = o.job_code MINUS SELECT ks_code "
				+ "FROM unemployed NATURAL JOIN experience WHERE per_id = u.per_id)), count_openings AS (SELECT pos_code,COUNT(job_code) AS numOfOpenings FROM openings GROUP BY pos_code), count_qualified AS (SELECT COUNT(pos_code) AS numOfQual, pos_code "
				+ "FROM qualified GROUP BY pos_code), max_opening AS (SELECT title, pos_code, numofopenings - coalesce(numOfQual,0) AS openings FROM (count_qualified FULL OUTER JOIN count_openings USING (pos_code)) NATURAL JOIN job_profile "
				+ "WHERE numofopenings - coalesce(numOfQual,0) = (SELECT MAX(numofopenings - coalesce(numOfQual,0)) FROM (count_qualified FULL OUTER JOIN count_openings USING (pos_code)) NATURAL JOIN job_profile)), skills_needed AS "
				+ "(SELECT ks_code FROM max_opening NATURAL JOIN skills) SELECT c_code, ks_code, title, description FROM skills_needed NATURAL JOIN teaches NATURAL JOIN course");
		//29a
		
		queries.add("WITH missing_skills AS(SELECT ks_code FROM skills WHERE pos_code = 11 MINUS SELECT ks_code FROM experience WHERE per_id = 1234567) SELECT c_code, ks_code, title, description FROM missing_skills NATURAL JOIN teaches NATURAL JOIN course");
		//30a	
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
