-- B. Crump, A. Jarrell

-- 1. List a company’s workers by names. **WORKS** Returns Dave and ALEX for GE
SELECT name
FROM Job_company NATURAL JOIN Has_Job NATURAL JOIN Person
WHERE comp_id = 100; -- input actual comp_id

-- 2. List a company’s staff by salary in descending order. **WORKS** Returns 60,000(Dave) 45,000(Alex)
SELECT name, pay_rate
FROM Job_company NATURAL JOIN Job NATURAL JOIN Has_Job NATURAL JOIN person
WHERE pay_type = 'salary' AND comp_id = 100 -- input actual comp_id
ORDER BY pay_rate DESC;

-- 3. List companies’ labor cost (total salaries and wage rates by 1920 hours) in descending order. *WORKS** Returns a lot
WITH SALARY AS
(SELECT comp_id, SUM(pay_rate) AS worker_salary
FROM Job NATURAL JOIN job_company
WHERE pay_type = 'salary' GROUP BY comp_id),

HOURLY AS
(SELECT comp_id, SUM(pay_rate* 1920) AS worker_salary
FROM Job NATURAL JOIN job_company
WHERE pay_type = 'wage' GROUP BY comp_id),

labor_cost AS ((select * FROM salary) UNION (select * FROM hourly))

SELECT comp_id, SUM(worker_salary) AS total
FROM labor_cost
GROUP BY comp_id
ORDER BY total DESC;

-- 4. Find all the jobs a person is currently holding. **WORKS** Returns 10 For Game Developer
SELECT job_code, title
FROM Has_Job NATURAL JOIN job NATURAL JOIN job_profile
WHERE end_date IS NULL AND per_id = 2220000; -- input actual id

-- 5. List a person’s knowledge/skills in a readable format. **WORKS** Returns 4400(Public speaking) and 3300(Management)
SELECT ks_code, title
FROM Experience NATURAL JOIN Knowledge_Skill
WHERE per_id = 3330000; -- input actual id

-- 6. List the skill gap of a worker between his/her job(s) and his/her skills. **WORKS** Returns skill 1410 Missing skill is SQL
(SELECT ks_code
FROM skills NATURAL JOIN job_profile NATURAL JOIN job
WHERE job_code = (SELECT job_code FROM has_job WHERE end_date IS NULL AND per_id = 1000000))
MINUS
(SELECT ks_code
FROM Experience
WHERE per_id = 1000000);
  
 -- 7. List the required knowledge/skills of a job profile in a readable format. **WORKS** Returns 1410(Database) and 2000(Java)
SELECT ks_code, title
FROM Skills NATURAL JOIN Knowledge_Skill
WHERE pos_code = 1; -- input actual pos_code

-- 8. List a person’s missing knowledge/skills for a specific job in a readable format. **WORKS** Returns Java
WITH missing_skills AS
((SELECT ks_code FROM Skills WHERE pos_code = 1) -- input pos_code for the job
MINUS
(SELECT ks_code FROM Experience WHERE per_id = 3330000)) -- input actual id

SELECT title, ks_code
FROM Knowledge_Skill NATURAL JOIN missing_skills;

-- 9. Find the courses each of which alone can cover a given skill set. given a set of skills (i.e. more than one skill) find courses which offer each of the set of skills
--**WORKS**RETURNS 1400(C_CODE) AND BIO 6000

WITH skill_set AS(
        (SELECT ks_code
        FROM skills
        WHERE pos_code = 2))

		 SELECT distinct c_code,title
     FROM course c
		 WHERE NOT EXISTS( SELECT ks_code
                       FROM skill_set
                  MINUS
                      SELECT ks_code
                      FROM teaches t
                      WHERE t.c_code = c.c_code );


-- 10. List the courses (course id and title) that each alone teaches all the missing knowledge/skills for a person to pursue a specific job.
--**WORKS** RETURNS 1400(C_CODE) AND BIO 6000
WITH skill_set AS(
                      (SELECT ks_code
                        FROM skills
                        WHERE pos_code = 2)
                  MINUS
                      (SELECT ks_code
                      FROM experience
                      WHERE per_id = 1000000)
                      )
SELECT distinct c_code, title
FROM course c
WHERE NOT EXISTS(
                SELECT ks_code
                FROM skill_set
          MINUS
                SELECT ks_code
                FROM teaches t
                WHERE t.c_code = c.c_code);
                
-- 11. Suppose the skill gap of a worker and the requirement of a desired job can be covered BY one course. Find the “quickest” solution for this worker. Show the course and the completing date.
--**WORKS** RETURNS C_CODE 1400(ALLBIO)
WITH missing_skill AS --Missing skills the person needs for a certain job
                    ((SELECT ks_code
                      FROM skills
                      WHERE pos_code = 2)
                MINUS
                    (SELECT ks_code
                    FROM experience
                    WHERE per_id = 1000000)
                    ),
		possible_courses AS(--Courses that the person needs to take to be qualified
                      SELECT c_code
                      FROM course c
                      WHERE NOT EXISTS(
                                      (SELECT ks_code
                                       FROM missing_skill)
                                  MINUS
                                      (SELECT ks_code
                                      FROM teaches t
                                      WHERE t.c_code = c.c_code)
                                      )
                        ),
    possible_section AS (SELECT *
                          FROM possible_courses NATURAL JOIN section
                          WHERE complete_date > '20-APR-16')
SELECT c_code, sec_no,format, complete_date, year, price, semester
FROM possible_section
WHERE complete_date = (SELECT MIN(complete_date) FROM possible_section);

-- 12. If query #9 returns nothing, then find the course sets with the minimum number of courses that their combination covers the given skill set.
-- The considered course sets will not include more than three courses.
    --**WORKS But included #13 as well RETURNS C_CODE 10, C_CODE 1200, C_CODE NULL--
WITH missing_skill AS
      (
				(SELECT ks_code
				FROM skills
				WHERE pos_code = 2)
				MINUS
				(SELECT ks_code
				FROM experience
				WHERE per_id = 1000000
				)
      ),
--the relationship between course set and its covering skills
CourseSet_Skill(csetID, ks_code) AS (
SELECT csetID, ks_code
FROM CourseSet CSet JOIN teaches CS ON CSet.c_code1=CS.c_code

UNION

SELECT csetID, ks_code
FROM CourseSet CSet JOIN teaches CS ON CSet.c_code2=CS.c_code

UNION

SELECT csetID, ks_code
FROM CourseSet CSet JOIN teaches CS ON CSet.c_code3=CS.c_code
),
--use division to find those course sets that cover missing skills
Cover_CSet(csetID, sizet) AS (
SELECT csetID, sizet
FROM CourseSet CSet
WHERE NOT EXISTS (
                  SELECT ks_code
                  FROM missing_skill
              MINUS
                  SELECT ks_code
                  FROM CourseSet_Skill CSSk
                  WHERE CSSk.csetID = Cset.csetID
                 )
                            )
--to find the smallest sets
SELECT c_code1, c_code2, c_code3
FROM Cover_CSet NATURAL JOIN CourseSet
WHERE sizet = (SELECT MIN(sizet)
               FROM Cover_CSet);


-- 13. List the course sets that their combination covers all the missing knowledge/skills for a person to pursue a specific job.
-- The considered course sets will not include more than three courses.
--**TU SAID THIS IS FINE BECAUSE ITS 12 AND 13 COMBINED**--
WITH missing_skill AS
      (
				(SELECT ks_code
				FROM skills
				WHERE pos_code = 2)
				MINUS
				(SELECT ks_code
				FROM experience
				WHERE per_id = 1000000
				)
      ),
--the relationship between course set and its covering skills */
CourseSet_Skill(csetID, ks_code) AS (
SELECT csetID, ks_code
FROM CourseSet CSet JOIN teaches CS ON CSet.c_code1=CS.c_code

UNION

SELECT csetID, ks_code
FROM CourseSet CSet JOIN teaches CS ON CSet.c_code2=CS.c_code

UNION

SELECT csetID, ks_code
FROM CourseSet CSet JOIN teaches CS ON CSet.c_code3=CS.c_code
),
/* use division to find those course sets that cover missing skills */
Cover_CSet(csetID, sizet) AS (
SELECT csetID, sizet
FROM CourseSet CSet
WHERE NOT EXISTS (
                  SELECT ks_code
                  FROM missing_skill
              MINUS
                  SELECT ks_code
                  FROM CourseSet_Skill CSSk
                  WHERE CSSk.csetID = Cset.csetID
                 )
                            )
/* to find the smallest sets */
SELECT c_code1, c_code2, c_code3
FROM Cover_CSet NATURAL JOIN CourseSet
WHERE sizet = (SELECT MIN(sizet)
               FROM Cover_CSet);

-- 14. Find the cheapest course choices to make up one’s skill gap by showing the courses to take and the total cost (by section prices).
-- The considered course sets will not include more than three courses.

--**THINK IT WORKS Returns CSETID = 413 and TOTAL = 300**--
--PROBLEM IS THAT IF THE COURSE SET DOES NOT CONTAIN 3 COURSES THEN ONE OF THEM IS NULL THEN THE TOTAL PRICE WILL BE NULL
        WITH missing_skill AS
        (
          (SELECT ks_code
          FROM skills
          WHERE pos_code = 2)
          MINUS
          (SELECT ks_code
          FROM experience
          WHERE per_id = 1000000
          )
        ),
  --the relationship between course set and its covering skills */
  CourseSet_Skill(csetID, ks_code) AS (
  SELECT csetID, ks_code
  FROM CourseSet CSet JOIN teaches CS ON CSet.c_code1=CS.c_code

  UNION

  SELECT csetID, ks_code
  FROM CourseSet CSet JOIN teaches CS ON CSet.c_code2=CS.c_code

  UNION

  SELECT csetID, ks_code
  FROM CourseSet CSet JOIN teaches CS ON CSet.c_code3=CS.c_code
  ),
  /* use division to find those course sets that cover missing skills */
  Cover_CSet(csetID, sizet) AS (
  SELECT csetID, sizet
  FROM CourseSet CSet
  WHERE NOT EXISTS (
                    SELECT ks_code
                    FROM missing_skill
                MINUS
                    SELECT ks_code
                    FROM CourseSet_Skill CSSk
                    WHERE CSSk.csetID = Cset.csetID
                   )
                              ),
    total_cost AS (
		SELECT csetID, (
                     (SELECT price FROM section C1 WHERE CS.c_code1 = C1.c_code)
									 + (SELECT price FROM section C2 WHERE CS.c_code2 = C2.c_code)
									 + (SELECT price FROM section C3 WHERE CS.c_code3 = C3.c_code)
                   )AS total
		FROM CourseSet CS NATURAL JOIN Cover_CSEt
    )
/* to find the cheapest sets */
SELECT *
FROM total_cost
ORDER BY total DESC;
--FETCH FIRST 3 ROWS ONLY;

-- 15. List all the job profiles that a person is qualified for. **WORKS**
-- Returns game developer 10 and manager 3
SELECT job_profile.pos_code, job_profile.title
FROM Job_Profile job_profile
WHERE NOT EXISTS
((SELECT skills.ks_code
FROM Skills skills
WHERE job_profile.pos_code = skills.pos_code)
MINUS
(SELECT ks_code
FROM Experience
WHERE per_id = 2220000));

-- 16. Find the job with the highest pay rate for a person according to his/her skill qualification. **WORKS**
-- Returns game developer 65000
WITH jobs_qualified AS
  (SELECT pos_code, title
    FROM Job_Profile jp
    WHERE NOT EXISTS
      ((SELECT s.ks_code
      FROM Skills s
      WHERE jp.pos_code = s.pos_code)
      MINUS
      (SELECT ks_code
      FROM Experience
      WHERE per_id = 2220000))),
job_pay(job_code, amount) AS
  (SELECT job_code,
      CASE
          WHEN pay_type = 'salary' THEN pay_rate
          ELSE pay_rate * 1920
      END
    FROM job)
SELECT job_code, title, amount
FROM jobs_qualified NATURAL JOIN job_pay NATURAL JOIN job
WHERE amount = (SELECT MAX(amount) FROM jobs_qualified NATURAL JOIN job_pay NATURAL JOIN job);

-- 17. List all the names along with the emails of the persons who are qualified for a job profile. **WORKS**
-- Returns Lisa and Pujah with their emails
-- AND Sabrina
SELECT name, email
FROM Person peep
WHERE NOT EXISTS
  ((SELECT ks_code
  FROM Skills
  WHERE pos_code = 3)
  MINUS
  (SELECT ks_code
  FROM Experience ex
  WHERE ex.per_id = peep.per_id));

-- 18. When a company cannot find any qualified person for a job, a secondary solution is to find a person who is almost
-- qualified to the job. Make a “missing-one” list that lists people who miss only one skill for a specified job profile.
--**WORKS** Returns 10000000(Dave) and 8880000(Alex)
WITH skill_count(per_id, skill_count) AS (
  SELECT per_id, COUNT(ks_code)
  FROM experience NATURAL JOIN skills
  WHERE pos_code = 1
  GROUP BY per_id
)

SELECT per_id
FROM skill_count
WHERE skill_count = (SELECT COUNT(*) -1 FROM skills WHERE pos_code =1);

-- 19. List the skillID and the number of people in the missing-one list for a given job profile in the ascending order of
-- the people counts. **WORKS** RETURNS KS_CODE(1410) NUM_PERSON(2)
WITH missing_one AS(
	SELECT per_id FROM experience NATURAL JOIN skills WHERE pos_code=1
	GROUP BY(per_id)
	HAVING
	(SELECT COUNT(*)
			FROM skills
			WHERE pos_code=1 ) - COUNT(per_id) =1)
SELECT ks_code, count(*) as num_person
FROM knowledge_skill,missing_one M
WHERE ks_code= (SELECT ks_code FROM((SELECT ks_code FROM skills WHERE pos_code= 1)
				 					MINUS
        							(SELECT K.ks_code FROM experience K WHERE K.per_id = M.per_id)) )
				 					GROUP BY (ks_code) ORDER BY num_person ASC;

-- 20. Suppose there is a new job profile that has nobody qualified.
-- List the persons who miss the least number of skills and report the “least number”.
--**WORKS** RETURNS 3330000(SABRINA) NEED = 1
WITH SKILLS_NEEDED(per_id, need) AS(
(SELECT per_id, ((SELECT COUNT(*) FROM skills WHERE pos_code = 11) - COUNT(per_id)) AS NEED --Number of skills required to be the President minus the number of skills the person has. Therefore rename it as Need because its what is leftover
FROM experience NATURAL JOIN skills
WHERE pos_code = 11
GROUP BY per_id))

 SELECT per_id, need
 FROM SKILLS_NEEDED
 WHERE need= (SELECT MIN(need) FROM SKILLS_NEEDED);
              
-- 21. For a specified job profile and a given small number k, make a “missing-k” list
-- that lists the people’s IDs and the number of missing skills for the people who miss only
-- up to k skills in the ASCending order of missing skills.              
--**WORKS** RETURNS A LOT
WITH SKILLS_NEEDED(per_id, need) AS(
(SELECT per_id, ((SELECT COUNT(*)
                  FROM skills
                  WHERE pos_code = 11) - COUNT(per_id)) AS NEED --Number of skills required to be the President minus the number of skills the person has. Therefore rename it as Need because its what is leftover

                  FROM experience NATURAL JOIN skills
                  WHERE pos_code = 11
                  GROUP BY per_id))
 SELECT *
 FROM (SELECT per_id,need --People that need skills
      FROM SKILLS_NEEDED sn

        UNION

      SELECT per_id,(SELECT COUNT(*) FROM skills WHERE pos_code = 11) -- Select the number of required skills for the job
      FROM person p
      WHERE NOT EXISTS (SELECT NULL
                        FROM skills_needed s_n
                        WHERE p.per_id = s_n.per_id)) --Where person is not in skills needed

WHERE need <= 3 --k
ORDER BY need ASC;

-- 22. Given a job profile and its corresponding missing-k list specified
-- in Question 22. Find every skill that is needed BY at least one person in the given missing-k list.
-- List each skillID and the number of people who need it in the DESCending order of the people counts.
--**WORKS** KS_CODE(4400) NUM_PPL(4), KS_CODE(1510) NUM_PPL(3)
WITH SKILLS_NEEDED(per_id, need) AS(
(SELECT per_id, ((SELECT COUNT(*)
                  FROM skills
                  WHERE pos_code = 11) - COUNT(per_id)) AS NEED --Number of skills required to be the President minus the number of skills the person has. Therefore rename it as Need because its what is leftover

                  FROM experience NATURAL JOIN skills
                  WHERE pos_code = 11
                  GROUP BY per_id))

SELECT ks_code, COUNT(*) as num_ppl
FROM skills s, skills_needed sn
WHERE EXISTS  (
                  SELECT * FROM ((SELECT ks_code
                                        FROM skills
                                        WHERE pos_code = 11)

                                        MINUS

                                        (SELECT ks_code
                                        FROM experience e
                                        WHERE e.per_id = sn.per_id)
                                      )
                                      WHERE ks_code = s.ks_code
                  )
                  AND need <= 3--k
GROUP BY ks_code
ORDER BY num_ppl DESC;

-- 23. In a local or national crisis, we need to find all the people who once held a job of the special job-profile identifier.
-- Returns Pujah and Lisa **WORKS**
SELECT per_id, name, pos_code
FROM Person NATURAL JOIN Has_Job NATURAL JOIN Job
WHERE pos_code = 3 AND end_date IS NOT NULL;

-- 24. Find all the unemployed people who once held a job of the given job-profile identifier.
-- Returns Samantha **WORKS**
WITH everyone AS
  (SELECT *
  FROM has_job FULL OUTER JOIN person USING (per_id)),
unemployed AS
  ((SELECT name, per_id
  FROM everyone)
  MINUS
  (SELECT name, per_id
  FROM everyone
  WHERE start_date IS NOT NULL AND end_date IS NULL))
SELECT name, per_id
FROM job NATURAL JOIN has_job NATURAL JOIN unemployed
WHERE pos_code = 2;

-- 25. Find out the biggest employer in terms of number of employees or the total amount of salaries and wages paid to
-- employees. **WORKS**
-- Returns company 800 and 100 both containing 2 employees
WITH employed AS
(SELECT *
  FROM has_job JOIN job USING(job_code) JOIN job_company USING(job_code)
  WHERE end_date IS NULL),
count_employees AS
(SELECT comp_id, COUNT(comp_id) AS employees
FROM employed
GROUP BY comp_id)
SELECT comp_id, employees
FROM count_employees
WHERE employees = (SELECT MAX(employees) FROM count_employees);

-- 28. Find the job profiles that have the most openings due to lack of qualified workers. **WORKS**
-- Returns manager 2 openings, game developer 1 openings, bio teacher 1 opening
WITH everyone AS
  (SELECT *
  FROM has_job FULL OUTER JOIN person USING (per_id)),
unemployed AS
  ((SELECT name, per_id
  FROM everyone)
  MINUS
  (SELECT name, per_id
  FROM everyone
  WHERE start_date IS NOT NULL AND end_date IS NULL)),
openings AS
  (SELECT job_code, pos_code
   FROM
  ((SELECT job_code
      FROM job)
      MINUS
      (SELECT job_code
      FROM has_job
      WHERE end_date IS NULL)) NATURAL JOIN job),
qualified AS
  (SELECT DISTINCT name, per_id, pos_code
    FROM unemployed u, openings o
    WHERE NOT EXISTS (SELECT ks_code
                      FROM openings NATURAL JOIN skills
                      WHERE job_code = o.job_code
                      MINUS
                      SELECT ks_code
                      FROM unemployed NATURAL JOIN experience
                      WHERE per_id = u.per_id)),
count_openings AS
  (SELECT pos_code,COUNT(job_code) AS numOfOpenings
    FROM openings
    GROUP BY pos_code),
count_qualified AS
  (SELECT COUNT(pos_code) AS numOfQual, pos_code
  FROM qualified
  GROUP BY pos_code)

SELECT title, pos_code, numofopenings - coalesce(numOfQual,0) AS openings
FROM (count_qualified FULL OUTER JOIN count_openings USING (pos_code)) NATURAL JOIN job_profile
WHERE numofopenings - coalesce(numOfQual,0) > 0
ORDER BY openings DESC;

-- 29. Find the courses that can help most jobless people find a job by training them toward the job profiles that have the
-- most openings due to lack of qualified workers. **WORKS** Return c_code 220 with ks_code 3300 which is the ks_code for
-- manager which is the job with the highest openings.
WITH everyone AS
  (SELECT *
  FROM has_job FULL OUTER JOIN person USING (per_id)),
unemployed AS
  ((SELECT name, per_id
  FROM everyone)
  MINUS
  (SELECT name, per_id
  FROM everyone
  WHERE start_date IS NOT NULL AND end_date IS NULL)),
openings AS
  (SELECT job_code, pos_code
   FROM
  ((SELECT job_code
      FROM job)
      MINUS
      (SELECT job_code
      FROM has_job
      WHERE end_date IS NULL)) NATURAL JOIN job),
qualified AS
  (SELECT DISTINCT name, per_id, pos_code
    FROM unemployed u, openings o
    WHERE NOT EXISTS (SELECT ks_code
                      FROM openings NATURAL JOIN skills
                      WHERE job_code = o.job_code
                      MINUS
                      SELECT ks_code
                      FROM unemployed NATURAL JOIN experience
                      WHERE per_id = u.per_id)),
count_openings AS
  (SELECT pos_code,COUNT(job_code) AS numOfOpenings
    FROM openings
    GROUP BY pos_code),
count_qualified AS
  (SELECT COUNT(pos_code) AS numOfQual, pos_code
  FROM qualified
  GROUP BY pos_code),
max_opening AS
  (SELECT title, pos_code, numofopenings - coalesce(numOfQual,0) AS openings
    FROM (count_qualified FULL OUTER JOIN count_openings USING (pos_code)) NATURAL JOIN job_profile
    WHERE numofopenings - coalesce(numOfQual,0) = (SELECT MAX(numofopenings - coalesce(numOfQual,0))
                                                FROM (count_qualified FULL OUTER JOIN count_openings USING (pos_code)) NATURAL JOIN job_profile)),
skills_needed AS
  (SELECT ks_code
    FROM max_opening NATURAL JOIN skills)
SELECT c_code, ks_code, title, description
FROM skills_needed NATURAL JOIN teaches NATURAL JOIN course;

--  30. List all the courses, directly or indirectly required, that a person has to take in order to be qualified for a job of the
-- ￼given profile, according to his/her skills possessed and courses taken.
-- **WORKS** Returns ks_codes 1510 & 4400 for Pujah applying for president, also c_codes 1300 & 350 which teach the ks_codes
WITH missing_skills AS(
  SELECT ks_code
  FROM skills
  WHERE pos_code = 11
  MINUS
  SELECT ks_code
  FROM experience
  WHERE per_id = 1234567)

SELECT c_code, ks_code, title, description
FROM missing_skills NATURAL JOIN teaches NATURAL JOIN course;
