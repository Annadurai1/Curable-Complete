CREATE PROCEDURE `CampsReport`()
BEGIN
SELECT 
    c.id AS campId,
    c.campIdPrefix,
    c.name AS campName,
    DATE_FORMAT(c.startDate, '%Y-%m-%d') AS StartDate,
    DATE_FORMAT(c.endDate, '%Y-%m-%d') AS EndDate,
    p.name AS panjayathName,
    t.name AS talukName,
    d.name AS districtName,
    s.name AS stateName,
    c.noCampcordinators,
    c.noDoctors,
    c.noNurses,
    c.noProgramcordinators,
    c.noSocialworkers,
    GROUP_CONCAT(CASE
            WHEN e.employeeRoleMasterId = 5 THEN e.name
        END) AS Doctor,
    GROUP_CONCAT(CASE
            WHEN e.employeeRoleMasterId = 2 THEN e.name
        END) AS Program_Coordinator,
    GROUP_CONCAT(CASE
            WHEN e.employeeRoleMasterId = 3 THEN e.name
        END) AS Camp_Coordinator,
    GROUP_CONCAT(CASE
            WHEN e.employeeRoleMasterId = 4 THEN e.name
        END) AS Social_Worker,
    GROUP_CONCAT(CASE
            WHEN e.employeeRoleMasterId = 6 THEN e.name
        END) AS Nurse
FROM
    camp c
        LEFT JOIN
    campstaff cs ON cs.campId = c.id
        AND cs.isRecordDeleted = 0
        LEFT JOIN
    employee e ON e.id = cs.employeeId
        AND e.isRecordDeleted = 0
        LEFT JOIN
    employeerolemaster erm ON erm.id = e.employeeRoleMasterId
        AND erm.isRecordDeleted = 0
        LEFT JOIN
    panchayatmaster p ON p.id = c.panchayatMasterId
        LEFT JOIN
    taluqmaster t ON t.id = p.taluqMasterId
        LEFT JOIN
    districtmaster d ON d.id = t.districtMasterId
        LEFT JOIN
    statemaster s ON s.id = d.stateMasterId
WHERE
    c.isRecordDeleted = 0
GROUP BY c.id;

END