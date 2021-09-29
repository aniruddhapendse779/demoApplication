CREATE TABLE `packages` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `package` VARCHAR(45) NOT NULL,
  `to_do_list` VARCHAR(200) NOT NULL,
  `price` VARCHAR(45) NOT NULL,
  `button` VARCHAR(45) NOT NULL,
  `features` VARCHAR(5000) NOT NULL,
  `is_active` SMALLINT(2) NOT NULL,
  `valid_from` TIMESTAMP NULL,
  `valid_to` VARCHAR(45) NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`));
  
ALTER TABLE `packages` 
CHANGE COLUMN `valid_to` `valid_to` TIMESTAMP NULL DEFAULT NULL ;
  
  
ALTER TABLE `packages` 
CHANGE COLUMN `updated_at` `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ;

CREATE TABLE `pricing_info` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `version` VARCHAR(100) NOT NULL,
  `configuration` JSON NOT NULL,
  `type` ENUM('PRICING_INFO') NOT NULL,
  `state` ENUM('INACTIVE', 'ACTIVE') NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uq_version_type` (`version` ASC, `type` ASC)
);


ALTER TABLE `pricing_info` 
DROP COLUMN `type`,
DROP INDEX `uq_version_type` ,
ADD UNIQUE INDEX `uq_version_type` (`version` ASC);

ALTER TABLE `pricing_info` 
DROP INDEX `uq_version_type` ;