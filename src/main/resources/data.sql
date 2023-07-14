INSERT INTO Users(first_name,last_name,email,password) VALUES('charan','c','Charan@gmail.com','$2a$10$I6kd94zfIB.GJOUUmbBFUuDMHjtOp.UHCjThN632Zh7KNNY8gjO4S');
INSERT INTO Users(first_name,last_name,email,password) VALUES('vihaan','H','Vihaan@gmail.com','$2a$10$I6kd94zfIB.GJOUUmbBFUuDMHjtOp.UHCjThN632Zh7KNNY8gjO4S');

INSERT INTO Roles(id,role_type)VALUES ('1','ROLE_ADMIN');
INSERT INTO Roles(id,role_type)VALUES ('2','ROLE_USER');

INSERT INTO Users_Role(user_id,role_id)VALUES ('1','1');
INSERT INTO Users_Role(user_id,role_id)VALUES ('2','2');

INSERT INTO school (clg_ID, clg_Name, clg_Location, course, clg_Fees, cutoff_10th, cutoff_12th) VALUES
(1, 'ABC College', 'City A', 'Engineering', 350000, 90.0, 85.0),
(2, 'XYZ College', 'City B', 'Medical', 950000, 95.0, 92.0),
(3, 'PQR College', 'City C', 'Commerce', 200000, 80.0, 75.0),
(4, 'DEF College', 'City D', 'Arts', 150000, 70.0, 65.0),
(5, 'MNO College', 'City E', 'Engineering', 800000, 92.0, 88.0),
(6, 'RST College', 'City F', 'Commerce', 450000, 85.0, 80.0),
(7, 'UVW College', 'City G', 'Medical', 750000, 93.0, 90.0),
(8, 'GHI College', 'City H', 'Arts', 120000, 75.0, 70.0),
(9, 'JKL College', 'City I', 'Engineering', 600000, 88.0, 84.0),
(10, 'EFG College', 'City J', 'Commerce', 300000, 78.0, 73.0),
(11, 'LMN College', 'City K', 'Medical', 550000, 87.0, 82.0),
(12, 'OPQ College', 'City L', 'Engineering', 900000, 96.0, 91.0),
(13, 'STU College', 'City M', 'Commerce', 400000, 83.0, 78.0),
(14, 'VWX College', 'City N', 'Arts', 110000, 72.0, 67.0),
(15, 'YZA College', 'City O', 'Engineering', 700000, 89.0, 84.0),
(16, 'BCD College', 'City P', 'Commerce', 250000, 77.0, 72.0),
(17, 'EFG College', 'City Q', 'Medical', 650000, 90.0, 85.0),
(18, 'HIJ College', 'City R', 'Arts', 130000, 74.0, 69.0),
(19, 'KLM College', 'City S', 'Engineering', 550000, 86.0, 81.0),
(20, 'NOP College', 'City T', 'Commerce', 350000, 76.0, 71.0),
(21, 'QRS College', 'City U', 'Medical', 800000, 92.0, 87.0),
(22, 'TUV College', 'City V', 'Arts', 100000, 73.0, 68.0),
(23, 'WXY College', 'City W', 'Engineering', 750000, 85.0, 80.0),
(24, 'ZAB College', 'City X', 'Commerce', 300000, 75.0, 70.0),
(25, 'CDE College', 'City Y', 'Medical', 700000, 91.0, 86.0),
(26, 'FGH College', 'City Z', 'Arts', 140000, 76.0, 71.0),
(27, 'IJK College', 'City A1', 'Engineering', 500000, 85.0, 80.0),
(28, 'LMN College', 'City B1', 'Commerce', 180000, 73.0, 68.0),
(29, 'OPQ College', 'City C1', 'Medical', 450000, 89.0, 84.0),
(30, 'RST College', 'City D1', 'Arts', 90000, 72.0, 67.0),
(31, 'UVW College', 'City E1', 'Engineering', 650000, 88.0, 83.0),
(32, 'XYZ College', 'City F1', 'Commerce', 250000, 78.0, 73.0),
(33, 'ABC College', 'City G1', 'Medical', 550000, 93.0, 88.0),
(34, 'DEF College', 'City H1', 'Arts', 170000, 75.0, 70.0),
(35, 'GHI College', 'City I1', 'Engineering', 750000, 87.0, 82.0),
(36, 'JKL College', 'City J1', 'Commerce', 400000, 83.0, 78.0),
(37, 'MNO College', 'City K1', 'Medical', 800000, 90.0, 85.0),
(38, 'PQR College', 'City L1', 'Arts', 100000, 74.0, 69.0),
(39, 'RST College', 'City M1', 'Engineering', 550000, 86.0, 81.0),
(40, 'UVW College', 'City N1', 'Commerce', 350000, 76.0, 71.0);