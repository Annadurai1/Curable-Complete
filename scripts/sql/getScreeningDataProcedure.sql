CREATE  PROCEDURE `getScreeningDataProcedure`(
    IN hospitalId BIGINT,
    IN search VARCHAR(255),
    IN roleId INT,
    IN employeeId INT,
    IN loginId VARCHAR(255)
)
BEGIN
    -- Declare dynamic query
    DECLARE final_query TEXT;

    -- Common SELECT clause
    SET @common_select = 'SELECT a.id, a.name, a.gender, a.age, a.mobileNo, a.hospitalId AS hospitalId, a.registraionId as registrationId FROM ';

    -- Common conditions with placeholders
    SET @common_conditions = ' a.consentDate IS NULL  
                               AND a.isRecordDeleted = FALSE 
                               AND a.hospitalId = ? 
                               AND (a.name LIKE CONCAT("%", ?, "%") 
                                    OR a.registraionId LIKE CONCAT("%", ?, "%") 
                                    OR a.mobileNo LIKE CONCAT("%", ?, "%")) 
                               ORDER BY a.createdDate DESC';

    -- Build final query based on roleId
    IF roleId = 2 THEN
        -- Program Coordinator
        SET @final_query = CONCAT(@common_select, 'candidate a WHERE ', @common_conditions);

    ELSEIF roleId = 3 THEN
        -- Camp Creator
        SET @final_query = CONCAT(@common_select, 
                                  'camp b LEFT JOIN candidate a ON b.id = a.campId WHERE b.createdBy = "', 
                                  loginId, '" AND ', @common_conditions);

    ELSE
        -- Camp Staff
        SET @final_query = CONCAT(@common_select, 
                                  'campstaff b LEFT JOIN candidate a ON a.campId = b.campId 
                                   WHERE b.employeeId = ', employeeId, 
                                  ' AND ', @common_conditions);
    END IF;


    -- Prepare and execute
    set @hospitalId=hospitalId;
    set @search=search;
    PREPARE stmt FROM @final_query;
    EXECUTE stmt USING @hospitalId, @search, @search, @search;
    DEALLOCATE PREPARE stmt;

END