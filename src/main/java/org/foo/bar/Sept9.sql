-- 2) SQL:

-- Write an SQL Script to Seed Sample Data (attached below) and answer the below queries:
-- Employees list.xlsx 7.99 KB

-- a) Write an SQL query to fetch worker names with salaries >= 50000 and <= 100000
-- b) Write an SQL query to fetch the no. of workers for each department in the descending order.
-- c) Write an SQL query to fetch intersecting records of two tables.
-- d) Write an SQL query to determine the 5th highest salary without using TOP or limit method.

select tablename from pg_tables where schemaname='public';
select column_name, data_type, character_maximum_length
from INFORMATION_SCHEMA.COLUMNS where table_name = 'worker';

select column_name, data_type, character_maximum_length
from INFORMATION_SCHEMA.COLUMNS where table_name = 'bonus';

select column_name, data_type, character_maximum_length
from INFORMATION_SCHEMA.COLUMNS where table_name = 'title';

SELECT * from worker LIMIT 2;
SELECT * from bonus LIMIT 2;
SELECT * from title LIMIT 2;

DROP TABLE IF EXISTS worker;
CREATE TABLE worker(
  WORKER_ID INT PRIMARY KEY,
  FIRST_NAME CHAR(50),
  LAST_NAME CHAR(50),
  SALARY REAL,
  JOINING_DATE TIMESTAMP(3) WITHOUT TIME ZONE,
  DEPARTMENT CHAR(50)
);
INSERT INTO worker(WORKER_ID,FIRST_NAME,LAST_NAME,SALARY,JOINING_DATE,DEPARTMENT)
SELECT 1,'Rick','Smith',100000,to_timestamp('2021-02-20 09:00:00','YYYY-MM-DD HH24:MI:SS'),'HR' UNION ALL
SELECT 2,'Sam','Williams',80000,to_timestamp('2021-06-11 09:00:00','YYYY-MM-DD HH24:MI:SS'),'Admin' UNION ALL
SELECT 3,'John','Brown',300000,to_timestamp('2021-02-20 09:00:00','YYYY-MM-DD HH24:MI:SS'),'HR' UNION ALL
SELECT 4,'Amy','Jones',500000,to_timestamp('2021-02-20 09:00:00','YYYY-MM-DD HH24:MI:SS'),'Admin' UNION ALL
SELECT 5,'Sean','Garcia',500000,to_timestamp('2021-06-11 09:00:00','YYYY-MM-DD HH24:MI:SS'),'Admin' UNION ALL
SELECT 6,'Ryan','Miller',200000,to_timestamp('2021-06-11 09:00:00','YYYY-MM-DD HH24:MI:SS'),'Account' UNION ALL
SELECT 7,'Patty','Davis',75000,to_timestamp('2021-01-20 09:00:00','YYYY-MM-DD HH24:MI:SS'),'Account' UNION ALL
SELECT 8,'Monica','Rodriguez',90000,to_timestamp('2021-04-11 09:00:00','YYYY-MM-DD HH24:MI:SS'),'Admin';

DROP TABLE IF EXISTS bonus;
CREATE TABLE bonus(
  ID SERIAL PRIMARY KEY,
  WORKER_REF_ID INT,
  BONUS_DATE TIMESTAMP(3) WITHOUT TIME ZONE,
  BONUS_AMOUNT INT
);
ALTER TABLE BONUS ADD CONSTRAINT FK_WORKER_ID FOREIGN KEY (WORKER_REF_ID) REFERENCES WORKER (WORKER_ID);
INSERT INTO bonus(WORKER_REF_ID,BONUS_DATE,BONUS_AMOUNT)
SELECT 1,to_timestamp('2021-02-20 00:00:00','YYYY-MM-DD HH24:MI:SS'),5000 UNION ALL
SELECT 2,to_timestamp('2021-06-11 00:00:00','YYYY-MM-DD HH24:MI:SS'),3000 UNION ALL
SELECT 3,to_timestamp('2021-02-20 00:00:00','YYYY-MM-DD HH24:MI:SS'),4000 UNION ALL
SELECT 1,to_timestamp('2021-02-20 00:00:00','YYYY-MM-DD HH24:MI:SS'),4500 UNION ALL
SELECT 2,to_timestamp('2021-06-11 00:00:00','YYYY-MM-DD HH24:MI:SS'),3500

DROP TABLE IF EXISTS title;
CREATE TABLE title(
  ID SERIAL PRIMARY KEY,
  WORKER_REF_ID INT,
  WORKER_TITLE CHAR(50),
  AFFECTED_FROM TIMESTAMP(3) WITHOUT TIME ZONE
);
ALTER TABLE title ADD CONSTRAINT FK_WORKER_ID FOREIGN KEY (WORKER_REF_ID) REFERENCES WORKER (WORKER_ID);
INSERT INTO title(WORKER_REF_ID,WORKER_TITLE,AFFECTED_FROM)
SELECT 1,'Manager',to_timestamp('2021-02-20 00:00:00','YYYY-MM-DD HH24:MI:SS') UNION ALL
SELECT 2,'Executive',to_timestamp('2021-06-11 00:00:00','YYYY-MM-DD HH24:MI:SS') UNION ALL
SELECT 8,'Executive',to_timestamp('2021-06-11 00:00:00','YYYY-MM-DD HH24:MI:SS') UNION ALL
SELECT 5,'Manager',to_timestamp('2021-06-11 00:00:00','YYYY-MM-DD HH24:MI:SS') UNION ALL
SELECT 4,'Asst. Manager',to_timestamp('2021-06-11 00:00:00','YYYY-MM-DD HH24:MI:SS') UNION ALL
SELECT 7,'Executive',to_timestamp('2021-06-11 00:00:00','YYYY-MM-DD HH24:MI:SS') UNION ALL
SELECT 6,'Lead',to_timestamp('2021-06-11 00:00:00','YYYY-MM-DD HH24:MI:SS') UNION ALL
SELECT 3,'Lead',to_timestamp('2021-06-11 00:00:00','YYYY-MM-DD HH24:MI:SS')

-- a) Write an SQL query to fetch worker names with salaries >= 50000 and <= 100000
SELECT FIRST_NAME || ' '|| LAST_NAME AS NAME FROM WORKER WHERE SALARY >= 50000 AND SALARY <= 100000;

-- b) Write an SQL query to fetch the no. of workers for each department in the descending order.
SELECT COUNT(DEPARTMENT) AS NUM_OF_EMPLOYEES FROM WORKER GROUP BY DEPARTMENT ORDER BY COUNT(DEPARTMENT) DESC;

-- c) Write an SQL query to fetch intersecting records of two tables.
SELECT FIRST_NAME || ' '|| LAST_NAME AS NAME, WORKER_TITLE FROM WORKER A LEFT JOIN TITLE B ON A.WORKER_ID = B.WORKER_REF_ID;
SELECT FIRST_NAME || ' '|| LAST_NAME AS NAME, BONUS_AMOUNT FROM WORKER A RIGHT JOIN BONUS B ON A.WORKER_ID = B.WORKER_REF_ID;
-- d) Write an SQL query to determine the 5th highest salary without using TOP or limit method.
-- 90000
SELECT MAX(DISTINCT SALARY) FROM WORKER WHERE SALARY < (
  SELECT MAX(DISTINCT SALARY) FROM WORKER WHERE SALARY < (
    SELECT MAX(DISTINCT SALARY) FROM WORKER WHERE SALARY < (
      SELECT MAX(DISTINCT SALARY) FROM WORKER WHERE SALARY < (
        SELECT MAX(DISTINCT SALARY) FROM WORKER
      )
    )
  )
);
