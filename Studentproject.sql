create schema studentproject;

CREATE TABLE `studentproject`.`studenttbl` (
  `name` CHAR(20) NOT NULL,
  `id` CHAR(20) NOT NULL,
  `hp` CHAR(20) NOT NULL,
  `kor` INT NOT NULL,
  `math` INT NOT NULL,
  `eng` INT NOT NULL,
  `total` INT NOT NULL,
  `avr` DOUBLE NOT NULL,
  `grade` CHAR(10) NOT NULL,
  PRIMARY KEY (`id`));
  
  select * from studentproject.studenttbl;


