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
WHERE pay_type = 'wage' GROUP BY comp_id)

labor_cost AS ((select * FROM salary) UNION (select * FROM hourly))

SELECT comp_id, SUM(worker_salary) AS total
FROM labor_cost
GROUP BY comp_id
ORDER BY total DESC;

-- 4. Find all the jobs a person is currently holding. **WORKS** Returns 10 For Game Developer
SELECT job_code
FROM Has_Job
WHERE end_date IS NULL AND per_id = 2220000; -- input actual id

-- 5. List a person’s knowledge/skills in a readable format. **WORKS** Returns 1110(Game Development) and 3300(Management)
SELECT ks_code, title
FROM Experience NATURAL JOIN Knowledge_Skill
WHERE per_id = 12345; -- input actual id

-- 6. List the skill gap of a worker between his/her job(s) and his/her skills. **WORKS** Returns skill 1410 Missing skill is SQL
(SELECT ks_code
  FROM Skills NATURAL JOIN job_profile NATURAL JOIN job
  WHERE job_code = (SELECT job_code
                    FROM has_job
                    WHERE end_date is null AND per_id = 1000000))
MINUS
(SELECT ks_code
  FROM Experience
  WHERE per_id = 1000000);
  
 -- 7. List the required knowledge/skills of a job profile in a readable format. **WORKS** Returns 1410(Database) and 2000(Java)
SELECT ks_code, title
FROM Skills NATURAL JOIN Knowledge_Skill
WHERE pos_code = 1; -- input actual pos_code

-- 8. List a person’s missing knowledge/skills for a specific job in a readable format. **WORKS** Returns Database and Java
WITH missing_skills AS
((SELECT ks_code FROM Skills WHERE pos_code = 1) -- input pos_code for the job
MINUS
(SELECT ks_code FROM Experience WHERE per_id = 3330000)) -- input actual id

SELECT title
FROM Knowledge_Skill NATURAL JOIN missing_skills;

-- 9. Find the courses each of which alone can cover a given skill set. given a set of skills (i.e. more than one skill) find courses which offer each of the set of skills
--**I THINK IT WORKS THE QUESTION WAS NOT CLEAR**
select distinct t.c_code
from course t
where not exists(
                (select ks_code
                from knowledge_skill
                WHERE ks_code = 3300)
          minus
            (select ks_code
            from teaches
            where t.c_code = c_code));
            
            --OR
SELECT c_code, title
FROM( WITH missing_skill AS(
        (SELECT ks_code
        FROM teaches
        WHERE ks_code = 1610))
        
 SELECT c_code,title
     FROM course c
 WHERE NOT EXISTS( SELECT ks_code
                       FROM missing_skill
                  MINUS
                      SELECT ks_code
                      FROM teaches t
                      WHERE t.c_code = c.c_code ));            
            

-- 10. List the courses (course id and title) that each alone teaches all the missing knowledge/skills for a person to pursue a specific job.
--**TOP PART WORKS, NOT THE BOTTOM** BUT IT LOOKS CORRECT, IDK
WITH missing_skill AS(
                      (SELECT ks_code
                        FROM skills
                        WHERE pos_code = 2)
                  MINUS
                      (SELECT ks_code
                      FROM experience
                      WHERE per_id = 1000000)
                      )
SELECT c_code, title
FROM course c
WHERE NOT EXISTS(
                SELECT ks_code
                FROM missing_skill
          MINUS
                SELECT ks_code
                FROM teaches t
                WHERE t.c_code = c.c_code);
                
-- 11. Suppose the skill gap of a worker and the requirement of a desired job can be covered BY one course. Find the “quickest” solution for this worker. Show the course and the completing date.
--**WORKS**
WITH missing_skill AS --Missing skills the person needs for a certain job
                    ((SELECT ks_code
                      FROM skills
                      WHERE pos_code = 3)
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
                        )
SELECT c_code, sec_no,format, complete_date, year, price, semester
FROM course NATURAL JOIN section
WHERE sec_no = (SELECT s.sec_no --Selecting a section that was in the Missing skills
                FROM missing_skill, section s
                WHERE s.complete_date = (SELECT MIN(complete_date) --Want to select the section that will complete the earliest
                                         FROM possible_courses NATURAL JOIN section
                                         WHERE complete_date > '20-APR-15')); --Make sure the section has not already occured

-- 12. If query #9 returns nothing, then find the course sets with the minimum number of courses that their combination covers the given skill set.
-- The considered course sets will not include more than three courses.

    Using Dr. Tu code provided

-- 13. List the course sets that their combination covers all the missing knowledge/skills for a person to pursue a specific job.
-- The considered course sets will not include more than three courses.

-- 14. Find the cheapest course choices to make up one’s skill gap by showing the courses to take and the total cost (by section prices).
-- The considered course sets will not include more than three courses.

-- 15. List all the job profiles that a person is qualified for. WORKS
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

-- 16. Find the job with the highest pay rate for a person according to his/her skill qualification. WORKS
-- Returns game developer 50000
WITH jobs-qualified AS
  (SELECT title, avg_pay
  FROM Job_Profile job_profile
  WHERE NOT EXISTS
    ((SELECT skills.ks_code
    FROM Skills skills
    WHERE job_profile.pos_code = skills.pos_code)
    MINUS
    (SELECT ks_code
    FROM Experience
    WHERE per_id = 2220000)))
SELECT title, avg_pay
FROM jobs-qualified
WHERE avg_pay = (SELECT MAX(avg_pay) FROM jobs-qualified);

-- 17. List all the names along with the emails of the persons who are qualified for a job profile. WORKS
-- Returns Lisa and Pujah with their emails
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
-- WORKS
SELECT per_id
FROM experience NATURAL JOIN skills
WHERE pos_code = 1
GROUP BY(per_id)
HAVING
  (SELECT COUNT (*)
  FROM skills
  WHERE pos_code = 1) - COUNT(per_id) = 1;
---------------------------------------------------
WITH skill_count(per_id, skill_count) AS (
  SELECT per_id, COUNT(ks_code)
  FROM experience NJ required_skills
  WHERE pos_code = #
  GROUP BY per_id
)

SELECT per_id
FROM skill_count
WHERE skill_count = (SELECT s)
-- Check division slide 15 ^

-- 19. List the skillID and the number of people in the missing-one list for a given job profile in the ascending order of
-- the people counts. WORKS
WITH MISSING_ONE AS(
  SELECT per_id, ks_code
  FROM experience NATURAL JOIN skills
  WHERE pos_code = 1
  GROUP BY(per_id, ks_code)
HAVING
  (SELECT COUNT (*)
  FROM skills
  WHERE pos_code = 1) - COUNT(per_id) = 1)
SELECT ks_code,COUNT(per_id) AS num_ppl
FROM MISSING_ONE
GROUP BY ks_code
ORDER BY num_ppl ASC;

-- 20. Suppose there is a new job profile that has nobody qualified.
-- List the persons who miss the least number of skills and report the “least number”.
--**WORKS**
WITH SKILLS_NEEDED(per_id, need) AS(
(SELECT per_id, ((SELECT COUNT(*)
                  FROM skills
                  WHERE pos_code = 11) - COUNT(per_id)) AS NEED --Number of skills required to be the President minus the number of skills the person has. Therefore rename it as Need because its what is leftover

                  FROM experience NATURAL JOIN skills
                  WHERE pos_code = 11
                  GROUP BY per_id))

 SELECT per_id, need
 FROM SKILLS_NEEDED
 WHERE need= (SELECT MIN(need)
              FROM SKILLS_NEEDED);
              
-- 21. For a specified job profile and a given small number k, make a “missing-k” list
-- that lists the people’s IDs and the number of missing skills for the people who miss only
-- up to k skills in the ASCending order of missing skills.              
--**WORKS BUT NEEDS TO BE CLEANED UP AND TAKE OUT NOT IN**
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
      WHERE p.per_id NOT IN (SELECT per_id FROM skills_needed) ) --Where person is not in skills needed

WHERE need <= 3 --k
ORDER BY need ASC;

-- 22. Given a job profile and its corresponding missing-k list specified
-- in Question 22. Find every skill that is needed BY at least one person in the given missing-k list.
-- List each skillID and the number of people who need it in the DESCending order of the people counts.
WITH SKILLS_NEEDED(per_id, need) AS(
(SELECT per_id, ((SELECT COUNT(*)
                  FROM skills
                  WHERE pos_code = 11) - COUNT(per_id)) AS NEED --Number of skills required to be the President minus the number of skills the person has. Therefore rename it as Need because its what is leftover

                  FROM experience NATURAL JOIN skills
                  WHERE pos_code = 11
                  GROUP BY per_id))

SELECT ks_code, COUNT(*) as num_ppl
FROM skills, skills_needed sn
WHERE ks_code in (
                  SELECT ks_code FROM ((SELECT ks_code
                                        FROM skills
                                        WHERE pos_code = 1)
                                        
                                        MINUS
                                        
                                        (SELECT ks_code
                                        FROM experience e
                                        WHERE e.per_id = sn.per_id)
                                      )
                  )
GROUP BY ks_code
ORDER BY num_ppl DESC;

-- 23. In a local or national crisis, we need to find all the people who once held a job of the special job-profile identifier.
-- Returns Pujah and Lisa WORKS
SELECT per_id, name, pos_code
FROM Person NATURAL JOIN Has_Job NATURAL JOIN Job
WHERE pos_code = 3 AND end_date IS NOT NULL;

-- 24. Find all the unemployed people who once held a job of the given job-profile identifier.
-- Returns Samantha WORKS
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
-- employees. WORKS
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

-- 28. Find the job profiles that have the most openings due to lack of qualified workers. NOT WORKING
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
  ((SELECT job_code
      FROM job)
      MINUS
      (SELECT job_code
      FROM has_job
      WHERE end_date IS NULL)),
qualified AS
  (SELECT name, per_id
    FROM unemployed NATURAL JOIN experience
    WHERE ks_code = ALL (SELECT ks_code
                          FROM openings NATURAL JOIN job NATURAL JOIN skills))
SELECT name
FROM qualified;

-- 29. Find the courses that can help most jobless people find a job by training them toward the job profiles that have the
-- most openings due to lack of qualified workers.

--  30. List all the courses, directly or indirectly required, that a person has to take in order to be qualified for a job of the
-- ￼given profile, according to his/her skills possessed and courses taken.
