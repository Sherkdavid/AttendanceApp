use db;

show tables;

DESC lecturer;
show create table lecturer;

SELECT * FROM module;

Select * from class;

DROP table module;

SHOW create table module;

SELECT * FROM course;
truncate table course;
truncate table module;
alter table class drop foreign key class_ibfk_1;

show create table module;

DELETE FROM course;


DELETE FROM lecturer WHERE lecturer_id = null;

set foreign_key_checks = 0;

INSERT INTO course (course_id, lecturer_id)
VALUES ('COM', 'L123');

SELECT * FROM Course INNER JOIN lecturer;
INSERT INTO module (module_id, course_id, lecturer_id)
VALUES ('OOSSP', 'COM', 'L123');

SELECT * from Lecturer;

INSERT INTO class (class_id, title, module_id, lecturer_id)
VALUES ('OOSSP1', )

load local infile 'C:\students.txt' into table module fields terminated by '|' lines terminated by '\r\n';

set foreign_key_checks = 1;
