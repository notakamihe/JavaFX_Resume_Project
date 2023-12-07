-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema resume
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema resume
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `resume` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `resume` ;

-- -----------------------------------------------------
-- Table `resume`.`resume`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `resume`.`resume` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `profession` VARCHAR(255) NOT NULL,
  `address` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `phone` VARCHAR(255) NOT NULL,
  `summary` TEXT NOT NULL,
  `skills` TEXT NOT NULL,
  `technical_skills` TEXT NOT NULL,
  `avatar` MEDIUMBLOB NULL DEFAULT NULL,
  `template` INT NOT NULL,
  `snapshot` MEDIUMBLOB NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `resume`.`education`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `resume`.`education` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `resume_id` INT NOT NULL,
  `start` VARCHAR(255) NOT NULL,
  `end` VARCHAR(255) NOT NULL,
  `credential` VARCHAR(255) NOT NULL,
  `field_of_study` VARCHAR(255) NOT NULL,
  `institution` VARCHAR(255) NOT NULL,
  `location` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `resume_fk_idx` (`resume_id` ASC) VISIBLE,
  CONSTRAINT `fk_resume_education`
    FOREIGN KEY (`resume_id`)
    REFERENCES `resume`.`resume` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `resume`.`work`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `resume`.`work` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `resume_id` INT NOT NULL,
  `start` VARCHAR(255) NOT NULL,
  `end` VARCHAR(255) NOT NULL,
  `company` VARCHAR(255) NOT NULL,
  `position` VARCHAR(255) NOT NULL,
  `location` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `resume_id_idx` (`resume_id` ASC) VISIBLE,
  CONSTRAINT `fk_resume_work`
    FOREIGN KEY (`resume_id`)
    REFERENCES `resume`.`resume` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `resume`.`objective`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `resume`.`objective` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `work_id` INT NOT NULL,
  `text` TEXT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_work_objective_idx` (`work_id` ASC) VISIBLE,
  CONSTRAINT `fk_work_objective`
    FOREIGN KEY (`work_id`)
    REFERENCES `resume`.`work` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `resume`.`style_settings`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `resume`.`style_settings` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `resume_id` INT NOT NULL,
  `font` VARCHAR(255) NOT NULL,
  `color1` VARCHAR(45) NOT NULL,
  `color2` VARCHAR(45) NOT NULL,
  `color3` VARCHAR(45) NOT NULL,
  `color4` VARCHAR(45) NOT NULL,
  `color5` VARCHAR(45) NOT NULL,
  `color6` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `resume_id_UNIQUE` (`resume_id` ASC) VISIBLE,
  CONSTRAINT `fk_resume_style_settings`
    FOREIGN KEY (`resume_id`)
    REFERENCES `resume`.`resume` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
