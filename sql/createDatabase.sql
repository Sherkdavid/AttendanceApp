-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema db
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `db` ;

-- -----------------------------------------------------
-- Schema db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db` DEFAULT CHARACTER SET utf8 ;
USE `db` ;

-- -----------------------------------------------------
-- Table `db`.`department`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db`.`department` ;

CREATE TABLE IF NOT EXISTS `db`.`department` (
  `name` VARCHAR(255) NOT NULL,
  `h_o_d` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`name`),
  INDEX `head of department_idx` (`h_o_d` ASC),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  CONSTRAINT `head of department`
    FOREIGN KEY (`h_o_d`)
    REFERENCES `db`.`faculty` (`faculty_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `db`.`faculty`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db`.`faculty` ;

CREATE TABLE IF NOT EXISTS `db`.`faculty` (
  `faculty_id` VARCHAR(255) NOT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `department` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`faculty_id`),
  UNIQUE INDEX `lecturer_id` (`faculty_id` ASC),
  INDEX `department_idx` (`department` ASC),
  CONSTRAINT `department`
    FOREIGN KEY (`department`)
    REFERENCES `db`.`department` (`name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `db`.`course`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db`.`course` ;

CREATE TABLE IF NOT EXISTS `db`.`course` (
  `course_id` VARCHAR(255) NOT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `department` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`course_id`),
  UNIQUE INDEX `course_id_UNIQUE` (`course_id` ASC),
  CONSTRAINT `course_department`
    FOREIGN KEY (`department`)
    REFERENCES `db`.`department` (`name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `db`.`module`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db`.`module` ;

CREATE TABLE IF NOT EXISTS `db`.`module` (
  `module_id` VARCHAR(255) NOT NULL,
  `title` VARCHAR(255) NULL DEFAULT NULL,
  `course_id` VARCHAR(255) NULL DEFAULT NULL,
  `faculty_id` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`module_id`),
  UNIQUE INDEX `module_id` (`module_id` ASC),
  INDEX `course` (`course_id` ASC),
  CONSTRAINT `course`
    FOREIGN KEY (`course_id`)
    REFERENCES `db`.`course` (`course_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `db`.`class`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db`.`class` ;

CREATE TABLE IF NOT EXISTS `db`.`class` (
  `class_id` VARCHAR(255) NULL DEFAULT NULL,
  `title` VARCHAR(255) NOT NULL,
  `module_id` VARCHAR(255) NULL DEFAULT NULL,
  `lecturer_id` VARCHAR(255) NULL DEFAULT NULL,
  INDEX `module_id` (`module_id` ASC),
  INDEX `lecturer_id` (`lecturer_id` ASC),
  INDEX `class_id` (`class_id` ASC),
  CONSTRAINT `lecturer`
    FOREIGN KEY (`lecturer_id`)
    REFERENCES `db`.`faculty` (`faculty_id`),
  CONSTRAINT `module`
    FOREIGN KEY (`module_id`)
    REFERENCES `db`.`module` (`module_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `db`.`student`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db`.`student` ;

CREATE TABLE IF NOT EXISTS `db`.`student` (
  `student_id` VARCHAR(255) NOT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `course_id` VARCHAR(255) NULL DEFAULT NULL,
  `year` INT(10) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`student_id`),
  UNIQUE INDEX `student_id` (`student_id` ASC),
  INDEX `course_id` (`course_id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `db`.`enrolment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db`.`enrolment` ;

CREATE TABLE IF NOT EXISTS `db`.`enrolment` (
  `student_id` VARCHAR(255) NULL DEFAULT NULL,
  `class_id` VARCHAR(255) NULL DEFAULT NULL,
  INDEX `student_idx` (`student_id` ASC),
  INDEX `class_idx` (`class_id` ASC),
  CONSTRAINT `student`
    FOREIGN KEY (`student_id`)
    REFERENCES `db`.`student` (`student_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `class`
    FOREIGN KEY (`class_id`)
    REFERENCES `db`.`class` (`class_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
