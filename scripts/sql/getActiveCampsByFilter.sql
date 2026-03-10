CREATE DEFINER=`cureadmin`@`%` PROCEDURE `getActiveCampsByFilter`(
    IN roleId INT,
    IN search VARCHAR(256),
    IN employeeIdCondition VARCHAR(256)
)
BEGIN
    -- Declare a variable to hold the dynamic SQL query
    if roleId=2 then
    SET @sqlQuery = CONCAT(
        'SELECT 
            b.campIdPrefix as campIdPrefix,
            b.name as campName,
            f.name AS stateName,
            e.name AS districtName,
            d.name AS talukName,
            c.name AS panchayatName,
            b.pincode,
            b.id as campId,
            b.startDate as startDate,
            b.endDate,
            b.panchayatMasterId as panchayatId,
            DATE_FORMAT(b.startDate, ''%Y-%m-%d'') as displayStartDate,
            DATE_FORMAT(b.endDate, ''%Y-%m-%d'') as displayEndDate,
            b.noCampcordinators,
            b.noDoctors,
            b.noNurses,
            b.noProgramcordinators as noProgramCoordinators,
            b.noSocialworkers as noSocialWorkers
        FROM 
            camp b 
        LEFT JOIN 
            panchayatmaster c ON c.id = b.panchayatMasterId
        LEFT JOIN 
            taluqmaster d ON d.id = c.taluqMasterId
        LEFT JOIN 
            districtmaster e ON e.id = d.districtMasterId
        LEFT JOIN 
            statemaster f ON f.id = e.stateMasterId
        WHERE 
            ', employeeIdCondition, ' -- Insert dynamic employee condition here
            AND (b.endDate >= CURRENT_DATE() OR b.endDate IS NULL)
            AND (b.name LIKE CONCAT(''%'', ?, ''%'')
            OR b.campIdPrefix LIKE CONCAT(''%'', ?, ''%''))
    ');
    else 
     SET @sqlQuery = CONCAT('SELECT 
    b.campIdPrefix as campIdPrefix,
    b.name as campName,
    f.name AS stateName,
    e.name AS districtName,
    d.name AS talukName,
    c.name AS panchayatName,
    b.pincode,
    b.id as campId,
    b.startDate  as startDate,
        b.endDate,
         b.panchayatMasterId as panchayatId,
      DATE_FORMAT(b.startDate, ''%Y-%m-%d'') as displayStartDate,
            DATE_FORMAT(b.endDate, ''%Y-%m-%d'') as displayEndDate,
                b.noCampcordinators,
            b.noDoctors,
            b.noNurses,
               b.noProgramcordinators as noProgramCoordinators,
            b.noSocialworkers as noSocialWorkers
   
FROM
    campstaff a
        LEFT JOIN
    camp b ON b.id = a.campId
        LEFT JOIN
    panchayatmaster c ON c.id = b.panchayatMasterId
        LEFT JOIN
    taluqmaster d ON d.id = c.taluqMasterId
        LEFT JOIN
    districtmaster e ON e.id = d.districtMasterId
        LEFT JOIN
    statemaster f ON f.id = e.stateMasterId
where 
 ', employeeIdCondition, ' -- Insert dynamic employee condition here
            AND (b.endDate >= CURRENT_DATE() OR b.endDate IS NULL)
            AND (b.name LIKE CONCAT(''%'', ?, ''%'')
            OR b.campIdPrefix LIKE CONCAT(''%'', ?, ''%''))


');
    end if;
    -- Prepare and execute the dynamic query
    set @search=search;
    PREPARE stmt FROM @sqlQuery;
    EXECUTE stmt USING @search, @search;  -- Bind search for both name and campIdPrefix
    DEALLOCATE PREPARE stmt;
END