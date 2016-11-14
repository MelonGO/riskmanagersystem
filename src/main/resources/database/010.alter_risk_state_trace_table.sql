ALTER TABLE `rms`.`risk_state_trace` 
DROP COLUMN `name`,
CHANGE COLUMN `risk_id` `plan_risk_id` INT(11) NOT NULL ,
ADD COLUMN `state` INT NULL AFTER `plan_risk_id`;