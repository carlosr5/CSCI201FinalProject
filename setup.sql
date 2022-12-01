CREATE SCHEMA `recipemanager` ;
CREATE TABLE `recipemanager`.`recipes` (
  `SerialRecipes` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NULL,
  `Description` VARCHAR(45) NULL,
  `Type` VARCHAR(45) NULL,
  `PrepTime` INT NULL,
  `CookTime` INT NULL,
  `Ingredients` VARCHAR(255) NULL,
  `Steps` VARCHAR(255) NULL,
  `ID` INT NULL,
  `image` VARCHAR(45) NULL,
  PRIMARY KEY (`SerialRecipes`));
  CREATE TABLE `recipemanager`.`users` (
  `userID` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(45) NULL,
  `lastName` VARCHAR(45) NULL,
  `displayName` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `hashPass` VARCHAR(45) NULL,
  PRIMARY KEY (`userID`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE);

ALTER TABLE `recipemanager`.`recipes` 
ADD INDEX `userID_idx` (`ID` ASC) VISIBLE;
;
ALTER TABLE `recipemanager`.`recipes` 
ADD CONSTRAINT `userID`
  FOREIGN KEY (`ID`)
  REFERENCES `recipemanager`.`users` (`userID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
