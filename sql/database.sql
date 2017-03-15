DROP DATABASE studentattendencedb;
DROP TABLE course CASCADE;
DROP TABLE student;
CREATE DATABASE studentAttendenceDB;


CREATE TABLE course
(
course_id varchar(255),
lecturer_id varchar(10),
PRIMARY KEY (course_id),
FOREIGN KEY (lecturer_id) REFERENCES lecturer(lecturer_id)
);

CREATE TABLE student
(
student_id varchar(255) UNIQUE,
name varchar(255),
course_id varchar(255),
acc_year integer(10),
email varchar(255),
PRIMARY KEY (student_id),
FOREIGN KEY (course_id) REFERENCES course(course_id)
);

CREATE TABLE classList
(
student_id varchar(255),
class_id varchar(255)
);

CREATE TABLE lecturer
(
lecturer_id varchar(255) UNIQUE,
lecturerEmail varchar(255),
lecturerName varchar(255),
PRIMARY KEY (lecturer_id)
);


x

CREATE TABLE class
(
class_id varchar(255),
module_id varchar(255),
lecturer_id varchar(255),
FOREIGN KEY (module_id) REFERENCES module(module_id),
FOREIGN KEY (lecturer_id) REFERENCES lecturer(lecturer_id)
);

CREATE TABLE AttendanceRecord
(
student_id varchar(255),
lecture_progress integer(10),
date date,
attendance integer(10) NOT NULL,
class_id integer(10)
);
select module_id, lecturer_id
FROM module
WHERE module_id = 'AOOP';


INSERT INTO course (course_id, lecturer_id)
VALUES ('COM', 'L123');

INSERT INTO course (course_id, lecturer_id)
VALUES ('DCOM', 'L353');

INSERT INTO course (course_id, lecturer_id)
VALUES ('DNET', 'L889');


INSERT INTO student (student_id, name, course_id, acc_year, email)
VALUES('R003', 'Mike Smith', 'COM', '1', 'Mike@cit.ie');

INSERT INTO student (student_id, name, course_id, acc_year, email)
VALUES ('R546', 'David O Connor', 'DNET', '2nd', 'David@cit.ie');


INSERT INTO classList (student_id, class_id)
VALUES ('R003', 'C011');

INSERT INTO classList (student_id, class_id)
VALUES ('R546', 'C789');


INSERT INTO lecturer(lecturer_id, lecturerName, lecturerEmail)
VALUES ('L123', 'Molly Smith', 'Molly@cit.ie');

INSERT INTO lecturer (lecturer_id, lecturerName, lecturerEmail)
VALUES ('L889', 'Aaron Rodgers', 'Aaron@cit.ie');


INSERT INTO module1 (module_id, course_id, lecturer_id)
VALUES ('MATH6', 'COM', 'L123');

INSERT INTO module1 (module_id, course_id, lecturer_id)
VALUES ('SOFT4', 'DCOM', 'L889');


INSERT INTO class1 (class_id, module_id, title, lecturer_id)
VALUES ('C011', 'SOFT4', 'TITLE1' 'L889');

INSERT INTO class1 (class_id, module_id, title, lecturer_id)
VALUES ('C789', 'MATH6', 'TITLE2', 'L123');


INSERT INTO AttendanceRecord (student_id, lecture_progress, date, attendance, class_id)
VALUES ('R003', '5', '13/10/16', '30', 'C011');

INSERT INTO AttendanceRecord (student_id, lecture_progress, date, attendance, class_id)
VALUES ('R546', '8', '25/09/2016', '70', 'C011');

