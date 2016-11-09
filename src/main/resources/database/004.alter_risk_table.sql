ALTER TABLE `rms`.`risk` 
ADD COLUMN `project_id` INT NOT NULL AFTER `id`;

ALTER TABLE `rms`.`risk` 
CHANGE COLUMN `name` `type` VARCHAR(64) NOT NULL ;