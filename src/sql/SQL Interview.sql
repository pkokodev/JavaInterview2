
id	state	month	amount
1	Jharkhand	Jan	200
2	Jharkhand	Feb	200
3	Jharkhand	Mar	100
4	Bihar	Jan	50
5	Bihar	Feb	300
6	Bihar	Mar	50
7	Maharastra	Jan	150
8	Maharastra	Feb	100
9	Maharastra	Mar	200
11	Odisha	Feb	300
12	Odisha	Mar	500
16	Odisha	Jan	100


--Q Duplicates records
Select first_name, COUNT(first_name) as freq  from patient group by first_name having COUNT(first_name) > 1

--partition by used to add non-aggregated columns into the aggregated result(group by only include aggregated columns)
--RANK() OVER (ORDER BY column ASC|DESC)
--Q Max Sales in each month along with the states which had max sales
select month, state, amount from (Select *, Rank()  over (partition by month order by amount desc)  rnk from Sales) a where rnk = 1

month	state	amount
Feb	Bihar	300
Feb	Odisha	300
Jan	Jharkhand	200
Mar	Odisha	500


--Q Number of states with sales greater than 100 in each month

Select month, count(state) as NoOfStates from Sales where amount > 100 group by month
month	NoOfStates
Feb	3
Jan	2
Mar	2

--Q For Amount range 0-100, 100-250, more than 250 fint no of states

select amount_range, count(state)from (select state, case when amount > 0 and amount < 100 then '0-100' when amount > 99 and amount < 251 then '100-250' else '> 250' end as amount_range from Sales)  temp group by amount_range

amount_range	(No column name)
> 250	3
0-100	2
100-250	7


-------------HR Database---------------

+-------------+-------------+-------------+-----------------------------------+--------------+------------+--------+----------+------------+---------------+
| employee_id | first_name  | last_name   | email                             | phone_number | hire_date  | job_id | salary   | manager_id | department_id |
+-------------+-------------+-------------+-----------------------------------+--------------+------------+--------+----------+------------+---------------+
|         100 | Steven      | King        | steven.king@sqltutorial.org       | 515.123.4567 | 1987-06-17 |      4 | 24000.00 |       NULL |             9 |
|         101 | Neena       | Kochhar     | neena.kochhar@sqltutorial.org     | 515.123.4568 | 1989-09-21 |      5 | 17000.00 |        100 |             9 |
|         102 | Lex         | De Haan     | lex.de haan@sqltutorial.org       | 515.123.4569 | 1993-01-13 |      5 | 17000.00 |        100 |             9 |
|         103 | Alexander   | Hunold      | alexander.hunold@sqltutorial.org  | 590.423.4567 | 1990-01-03 |      9 |  9000.00 |        102 |             6 |
|         104 | Bruce       | Ernst       | bruce.ernst@sqltutorial.org       | 590.423.4568 | 1991-05-21 |      9 |  6000.00 |        103 |             6 |
|         105 | David       | Austin      | david.austin@sqltutorial.org      | 590.423.4569 | 1997-06-25 |      9 |  4800.00 |        103 |             6 |
|         106 | Valli       | Pataballa   | valli.pataballa@sqltutorial.org   | 590.423.4560 | 1998-02-05 |      9 |  4800.00 |        103 |             6 |
|         107 | Diana       | Lorentz     | diana.lorentz@sqltutorial.org     | 590.423.5567 | 1999-02-07 |      9 |  4200.00 |        103 |             6 |
|         108 | Nancy       | Greenberg   | nancy.greenberg@sqltutorial.org   | 515.124.4569 | 1994-08-17 |      7 | 12000.00 |        101 |            10 |
...

=============Over Clause=====================
ROW_NUMBER(),RANK(), DENSE_RANK()
partition by used to add non-aggregated columns into the aggregated result(group by only include aggregated columns)
The OVER clause combined with PARTITION BY is used to break up data into partitions. 
Syntax : function (...) OVER (PARTITION BY col1, Col2, ...)

The specified function operates for each partition.

For example : 
COUNT(Gender) OVER (PARTITION BY Gender) will partition the data by GENDER i.e there will 2 partitions (Male and Female) and then the COUNT() function is applied over each partition.

Any of the following functions can be used. Please note this is not the complete list.
COUNT(), AVG(), SUM(), MIN(), MAX(), ROW_NUMBER(), RANK(), DENSE_RANK() etc.

=============Ranking Functoins===============
Ranking functions:- RANK(), DENSE_RANK(), ROW_NUMBER()
are used to give ranking within you ordered partition. 
always used with OVER() clause.
lways assign rank on basis of ORDER BY clause.
The rank is assigned to rows in a sequential manner.
The assignment of rank to rows always start with 1 for every new partition.

Comparision:- RANK() vs DENSE_RANK()
RANK():- So, if you have 3 items at rank 2, the next rank listed would be ranked 5.
DENSE_RANK():- No ranks are skipped if there are ranks with multiple items.

DENSE_RANK()
24000.00	1  
17000.00	2
17000.00	2
14000.00	3

RANK()
24000.00	1
17000.00	2
17000.00	2
14000.00	4
    Simple query without partition clause:


select
    amount,
    RANK() over(order by amount desc) as Rank,
        DENSE_RANK() over(order by amount desc) as DenseRank,
        ROW_NUMBER() over(order by amount desc) as RowNumber
from Sales
    Output:

    --------|-------|-----------|----------
    sal     |Rank   |DenseRank  |RowNumber
    --------|-------|-----------|----------
    5000    |1      |1          |1
    3000    |2      |2          |2
    3000    |2      |2          |3
    2975    |4      |3          |4
    2850    |5      |4          |5
    --------|-------|-----------|----------


1. 2nd Highest Salary 

Solution 1:- Using Subquery, DENSE_RANK(), And Over Clause

Select first_name, Salary from
(Select first_name,Salary, DENSE_RANK() Over(Order By salary DESC) as rnk from employees) tbl
Where rnk = 2

Or With Common Table Expression

WITH T AS
(
SELECT first_name,Salary, DENSE_RANK() OVER (ORDER BY Salary Desc) AS Rnk
FROM employees
)
SELECT first_name, Salary
FROM T
WHERE Rnk=2;

Solution 2:-

Select Max(Salary) from employees
Where Salary Not In (Select Max(Salary) from employees)

=====================RANK() vs DENSE_RANK() vs ROW_NUMBER()=================

WITH T(StyleID, ID)
     AS (SELECT 1,1 UNION ALL
         SELECT 1,1 UNION ALL
         SELECT 1,1 UNION ALL
         SELECT 1,2)
SELECT *,
       RANK() OVER(PARTITION BY StyleID ORDER BY ID)       AS [RANK],
       ROW_NUMBER() OVER(PARTITION BY StyleID ORDER BY ID) AS [ROW_NUMBER],
       DENSE_RANK() OVER(PARTITION BY StyleID ORDER BY ID) AS [DENSE_RANK]
FROM   T  
Returns

StyleID     ID       RANK      ROW_NUMBER      DENSE_RANK
----------- -------- --------- --------------- ----------
1           1        1         1               1
1           1        1         2               1
1           1        1         3               1
1           2        4         4               2

2. Delete duplicate records with employee_id
As will not work in having

Select first_name, COUNT(*) From employees Group By first_name Having cnt > 1

OR

With tblCTE As
(
	SELECT *, ROW_NUMBER() Over(Partition By first_name Order By first_name DESC) as rowNumber from employees
)
Select * From tblCTE Where rowNumber >  1

Or

DELETE tmpTable from
(Select *, ROW_NUMBER() over (partition by state order by id) as rowNumber from Sales) tmpTable
Where rowNumber > 1

select  *
from Sales;

3. Department wise highest Salary

Select department_id, MAX(salary) From employees Group By department_id


4. No of employeees Department wise

Select department_id, Count(*) From employees Group By department_id

5. Pattern matching 
case insensitive
StartsWith A ->'A%' Contains A -> '%A%'....A at 2nd position -> '_A%'
Select * From employees Where first_name Like 'a%r'


===================Correlated Nested Queries===============

Correlated nested queries are for row-by-row processing.

Each nested sub-query is executed once for every row of the outer query.

A correlated nested query reads every row in a table and compares values in each row against related data.
Query

6. Find Employees With Salary Higher Than Their Department Average

Select * From employees Inner Join  
(Select department_id, AVG(salary) As avgSal From employees Group By department_id) deptAvgTbl

On employees.department_id = deptAvgTbl.department_id

Where salary > avgSal

Or Using SubQuery

SELECT EmployeeID, DepartmentID, Salary
FROM tblEmployeeInfo AS T1
  WHERE Salary > 
  ( SELECT Avg(Salary) 
    FROM tblEmployeeInfo as T2
    WHERE T1.DepartmentID=T2.DepartmentID )

===================SQL EXISTS vs IN vs JOIN Performance Comparison=============
JOIN are best in production.

Select Id, Name, Description
from tblProducts
where ID IN
    (
    Select ProductId from tblProductSales
    )

Select distinct tblProducts.Id, Name, Description
from tblProducts
         inner join tblProductSales
                    on tblProducts.Id = tblProductSales.ProductId

Select Id, Name, [Description]
from tblProducts
where Not Exists(Select * from tblProductSales where ProductId = tblProducts.Id)

Select tblProducts.Id, Name, [Description]
from tblProducts
    left join tblProductSales
on tblProducts.Id = tblProductSales.ProductId
where tblProductSales.ProductId IS NULL

In general joins work faster than sub-queries, but in reality it all depends on the execution plan that is generated by SQL Server.
It does not matter how we have written the query, SQL Server will always transform it on an execution plan. If sql server generates the same plan from both queries,
we will get the same result.


==============SQL Perfoemance Tuning=============
Query Optimizer:-
It is SQL Server engine used to determine most efficient execition plan of the queries.
It does this by optimizing each SQL statements based on the statistics.

Execution plan:-
    The steps of the process performed at the time of query execution by the database engine.
    Read from Right to Left Top to Bottom

Index:
Its Principle purpose is to speed up the data retrieval. Created on tables and views.
If table doesnt have any index then query engine checks every record from begining to end cales as Scan.
If it has index then BST O(logn) calles Seek.

Clustered Index:-
    It determines the physical order of the data in the table based on  clustered create index. By defult created on primary key
    Once we create a clustered index into the table then it sort the data into B-Tree according to clustered key column.

    Non Clustered Index


7. Employee Manager Hierarchy - Self Join

Select Emp. FirstName + '' +  Emp. LastName as Employee,  Mgr.FirstName + '' + Mgr.LastName as Manager
from dbo.Employees Emp
INNER JOIN dbo. Employees Mgr
ON Emp. ManagerID = Mgr. EmployeeID