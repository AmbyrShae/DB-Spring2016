CREATE TABLE Job_Profile
(
  avg_pay NUMERIC(10,2) NOT NULL,
  description VARCHAR(40) NOT NULL,
  title VARCHAR(20) NOT NULL,
  pos_code NUMERIC(10) NOT NULL,
  PRIMARY KEY (pos_code)
);

CREATE TABLE Job
(
  job_code NUMERIC(10) NOT NULL,
  type VARCHAR(20) NOT NULL,
  pay_rate NUMERIC(10,2) NOT NULL,
  pay_type VARCHAR(10) NOT NULL,
  pos_code NUMERIC(10) NOT NULL,
  PRIMARY KEY (job_code),
  FOREIGN KEY (pos_code) REFERENCES Job_Profile(pos_code)
);

CREATE TABLE Company
(
  comp_id NUMERIC(10) NOT NULL,
  state VARCHAR(20),
  city VARCHAR(20),
  street VARCHAR(20),
  zip_code NUMERIC(5),
  primary_sector VARCHAR(40) NOT NULL,
  website VARCHAR(40),
  PRIMARY KEY (comp_id)
);

CREATE TABLE Person
(
  per_id NUMERIC(10) NOT NULL,
  name VARCHAR(30),
  state VARCHAR(20),
  city VARCHAR(20),
  street VARCHAR(20),
  zip_code NUMERIC(5),
  email VARCHAR(40),
  gender VARCHAR(10),
  PRIMARY KEY (per_id)
);

CREATE TABLE Job_company
(
  job_code NUMERIC(10) NOT NULL,
  comp_id NUMERIC(10) NOT NULL,
  PRIMARY KEY (job_code, comp_id),
  FOREIGN KEY (job_code) REFERENCES Job(job_code),
  FOREIGN KEY (comp_id) REFERENCES Company(comp_id)
);

CREATE TABLE Company_speciality
(
  speciality VARCHAR(30) NOT NULL,
  comp_id NUMERIC(10) NOT NULL,
  PRIMARY KEY (speciality, comp_id),
  FOREIGN KEY (comp_id) REFERENCES Company(comp_id)
);

CREATE TABLE Person_phone
(
  phone NUMERIC(10) NOT NULL,
  per_id NUMERIC(10) NOT NULL,
  PRIMARY KEY (phone, per_id),
  FOREIGN KEY (per_id) REFERENCES Person(per_id)
);

CREATE TABLE Has_Job
(
  per_id NUMERIC(9) NOT NULL,
  job_code NUMERIC(10) NOT NULL,
  start_date DATE,
  end_date DATE,
  PRIMARY KEY (per_id, job_code),
  FOREIGN KEY (per_id) REFERENCES Person(per_id),
  FOREIGN KEY (job_code) REFERENCES Job(job_code)
);

CREATE TABLE Course
(
  lvl VARCHAR(20) NOT NULL,
  status VARCHAR(10) NOT NULL,
  description VARCHAR(40),
  title VARCHAR(30),
  c_code NUMERIC(10) NOT NULL,
  retail_price NUMERIC(5,2),
  PRIMARY KEY (c_code)
);

CREATE TABLE Knowledge_Skill
(
  ks_code NUMERIC(10) NOT NULL,
  title VARCHAR(20),
  description VARCHAR(40),
  lvl VARCHAR(20) NOT NULL,
  PRIMARY KEY (ks_code)
);

CREATE TABLE Teaches
(
  ks_code NUMERIC(10) NOT NULL,
  c_code NUMERIC(10) NOT NULL,
  PRIMARY KEY (ks_code, c_code),
  FOREIGN KEY (ks_code) REFERENCES Knowledge_Skill(ks_code),
  FOREIGN KEY (c_code) REFERENCES Course(c_code)
);
CREATE TABLE Skills
(
  ks_code NUMERIC(10) NOT NULL,
  pos_code NUMERIC(10) NOT NULL,
  PRIMARY KEY (ks_code, pos_code),
  FOREIGN KEY (ks_code) REFERENCES Knowledge_Skill(ks_code),
  FOREIGN KEY (pos_code) REFERENCES Job_Profile(pos_code)
);

CREATE TABLE Experience
(
  per_id NUMERIC(9) NOT NULL,
  ks_code NUMERIC(10) NOT NULL,
  PRIMARY KEY (per_id, ks_code),
  FOREIGN KEY (per_id) REFERENCES Person(per_id),
  FOREIGN KEY (ks_code) REFERENCES Knowledge_Skill(ks_code)
);

CREATE TABLE Section
(
  format VARCHAR(20),
  complete_date DATE,
  year NUMERIC(4) NOT NULL,
  sec_no NUMERIC(10) NOT NULL,
  price NUMERIC(5,2),
  semester VARCHAR(10) NOT NULL,
  c_code NUMERIC(10) NOT NULL,
  PRIMARY KEY (year, sec_no, semester, c_code),
  FOREIGN KEY (c_code) REFERENCES Course(c_code)
);

CREATE TABLE Offers
(
  comp_id NUMERIC(10) NOT NULL,
  year NUMERIC(4) NOT NULL,
  sec_no NUMERIC(10) NOT NULL,
  c_code NUMERIC(10) NOT NULL,
  semester VARCHAR(10) NOT NULL,
  PRIMARY KEY (comp_id, year, sec_no, c_code, semester),
  FOREIGN KEY (comp_id) REFERENCES Company(comp_id),
  FOREIGN KEY (year, sec_no, c_code, semester) REFERENCES Section(year, sec_no, c_code, semester)
);

CREATE TABLE Takes
(
  per_id NUMERIC(9) NOT NULL,
  year NUMERIC(4) NOT NULL,
  sec_no NUMERIC(10) NOT NULL,
  c_code NUMERIC(10) NOT NULL,
  semester VARCHAR(10) NOT NULL,
  PRIMARY KEY (per_id, year, sec_no, c_code, semester),
  FOREIGN KEY (per_id) REFERENCES Person(per_id),
  FOREIGN KEY (year, sec_no, c_code, semester) REFERENCES Section(year, sec_no, c_code, semester)
);

CREATE TABLE CourseSet
(
  csetID NUMBER(8, 0) PRIMARY KEY,
  c_code1 NUMBER(6, 0), c_code2 NUMBER(6, 0), c_code3 NUMBER(6, 0), sizet NUMBER(2, 0) /* number of courses */
);

CREATE SEQUENCE CourseSet_seq
START WITH 1 INCREMENT BY 1 MAXVALUE 999999 NOCYCLE;
