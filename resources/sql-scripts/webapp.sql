CREATE DATABASE IF NOT EXISTS `webapp`;
USE `webapp`;

DROP TABLE IF EXISTS `courses`;
DROP TABLE IF EXISTS `registrations`;
DROP TABLE IF EXISTS `grades`;
DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;

create table users (
    username varchar(50) not null primary key ,
    password varchar(100) not null,
    enabled boolean not null
);

create table authorities (
    username varchar(50) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key(username) references users(username)
);

create unique index ix_auth_username on authorities (username,authority);

create table courses(
    course_id int unsigned auto_increment,
    course_name varchar(30) not null,
    syllabus varchar(150) not null,
    year int not null,
    semester int not null,
    description varchar(500),
    prof_username varchar(50),
    primary key(course_id),
    foreign key(prof_username) references users(username) on delete cascade
);

create table registrations(
    student_id int, 
    course_id int unsigned,
    student_name varchar(100) not null,
    year_of_reg int not null,
    cur_semester int not null,
    foreign key(course_id) references courses(course_id) on delete cascade,
    primary key(student_id,course_id)
);

create table grades(
    student_id int not null, 
    course_id int unsigned not null,
    project_grade int,
    final_exam_grade int,
    overall_grade double default 0,
    foreign key(student_id,course_id) references registrations(student_id,course_id) on delete cascade,
    primary key(student_id,course_id)
);

insert into users(username, password, enabled)values('teacherOne','{noop}teacherOne',true);
insert into users(username, password, enabled)values('teacherTwo','{noop}teacherTwo',true);
insert into users(username, password, enabled)values('nionios','{noop}nionios',true);
insert into users(username, password, enabled)values('nikol','{noop}nikol',true);
insert into users(username, password, enabled)values('billy','{noop}billy',true);
 
insert into authorities(username,authority)values('teacherOne','ROLE_ADMIN');
insert into authorities(username,authority)values('teacherTwo','ROLE_USER');
insert into authorities(username,authority)values('nionios','ROLE_ADMIN');
insert into authorities(username,authority)values('nikol','ROLE_ADMIN');
insert into authorities(username,authority)values('billy','ROLE_ADMIN');

INSERT INTO `courses` VALUES 
	(1,"MYZ100","Linear Algrebra 1", 1, 1,"Maths","teacherOne"),
	(2,"MYX101", "Python", 1, 2,"Python","teacherOne"),
	(3,"MYY400","Software Development", 2, 3,"Software dev","teacherTwo"),
	(4,"MYY644","Ethics", 5, 10,"Ethics in AI","nionios"),
	(5,"MYY800","Software Engineering",4,8,"software lifecycle","teacherOne");
	
INSERT INTO `registrations` VALUES 
	(4435,1,'Niki',2022,8),(4424,3,'Marios',2021,5),(4444,5,'Goofy',2000,15);
	
INSERT INTO `grades` VALUES (4435,1,5,5,0),(4424,3,5,6,0);