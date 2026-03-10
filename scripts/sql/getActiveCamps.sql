DELIMITER $$
CREATE PROCEDURE `getActiveCamps`(in  userId int,in search varchar(256) )
BEGIN
SELECT 
    b.campIdPrefix as campIdPrefix,
    b.name as campName,
    f.name AS stateName,
    e.name AS districtName,
    d.name AS talukName,
    c.name AS panchayatName,
    b.pincode,
    b.id as campId
FROM
    hospitalemployeemap a
        LEFT JOIN
    camp b ON b.hospitalId = a.hospitalId
        LEFT JOIN
    panchayatmaster c ON c.id = b.panchayatMasterId
        LEFT JOIN
    taluqmaster d ON d.id = c.taluqMasterId
        LEFT JOIN
    districtmaster e ON e.id = d.districtMasterId
        LEFT JOIN
    statemaster f ON f.id = e.stateMasterId
WHERE
    a.employeeId = userId and
     b.endDate>=current_date()
        AND (b.name like concat('%',search,'%')
        OR b.campIdPrefix like concat('%',search,'%'));

END$$
DELIMITER ;
