BEGIN TRANSACTION;
CREATE TABLE Continents (id INTEGER PRIMARY KEY, name TEXT, file TEXT);
INSERT INTO Continents VALUES(1,'North America','north_america');
INSERT INTO Continents VALUES(2,'Caribbean','map_caribbean_central_america');
INSERT INTO Continents VALUES(3,'Central America','map_caribbean_central_america');
CREATE TABLE Countries (id INTEGER PRIMARY KEY, name TEXT, continentId NUMERIC, continentName TEXT);
INSERT INTO Countries VALUES(1,'Bermuda',1,'North America');
INSERT INTO Countries VALUES(2,'Canada',1,'North America');
INSERT INTO Countries VALUES(3,'Greenland',1,'North America');
INSERT INTO Countries VALUES(4,'Mexico',1,'North America');
INSERT INTO Countries VALUES(5,'Saint Pierre and Miquelon',1,'North America');
INSERT INTO Countries VALUES(6,'United States',1,'North America');
INSERT INTO Countries VALUES(7,'Anguilla',2,'Caribbean');
INSERT INTO Countries VALUES(8,'Antigua and Barbuda',2,'Caribbean');
INSERT INTO Countries VALUES(9,'Aruba',2,'Caribbean');
INSERT INTO Countries VALUES(10,'Bahamas',2,'Caribbean');
INSERT INTO Countries VALUES(11,'Barbados',2,'Caribbean');
INSERT INTO Countries VALUES(12,'Bonaire',2,'Caribbean');
INSERT INTO Countries VALUES(13,'British Virgin Islands',2,'Caribbean');
INSERT INTO Countries VALUES(14,'Cayman Islands',2,'Caribbean');
INSERT INTO Countries VALUES(15,'Clipperton Island ',2,'Caribbean');
INSERT INTO Countries VALUES(16,'Cuba',2,'Caribbean');
INSERT INTO Countries VALUES(17,'Curaçao',2,'Caribbean');
INSERT INTO Countries VALUES(18,'Dominica',2,'Caribbean');
INSERT INTO Countries VALUES(19,'Guadeloupe',2,'Caribbean');
INSERT INTO Countries VALUES(20,'Haiti',2,'Caribbean');
INSERT INTO Countries VALUES(21,'Jamaica',2,'Caribbean');
INSERT INTO Countries VALUES(22,'Martinique',2,'Caribbean');
INSERT INTO Countries VALUES(23,'Montserrat',2,'Caribbean');
INSERT INTO Countries VALUES(24,'Navassa Island',2,'Caribbean');
INSERT INTO Countries VALUES(25,'Puerto Rico',2,'Caribbean');
INSERT INTO Countries VALUES(26,'Saba',2,'Caribbean');
INSERT INTO Countries VALUES(27,'Saint Barthélemy',2,'Caribbean');
INSERT INTO Countries VALUES(28,'Saint Kitts and Nevis',2,'Caribbean');
INSERT INTO Countries VALUES(29,'Saint Lucia',2,'Caribbean');
INSERT INTO Countries VALUES(30,'Saint Martin',2,'Caribbean');
INSERT INTO Countries VALUES(31,'Saint Vincent and the Grenadines',2,'Caribbean');
INSERT INTO Countries VALUES(32,'Sint Eustatius',2,'Caribbean');
INSERT INTO Countries VALUES(33,'Sint Maarten',2,'Caribbean');
INSERT INTO Countries VALUES(34,'Trinidad and Tobago',2,'Caribbean');
INSERT INTO Countries VALUES(35,'Turks and Caicos Islands',2,'Caribbean');
INSERT INTO Countries VALUES(36,'United States Virgin Islands',2,'Caribbean');
INSERT INTO Countries VALUES(37,'Belize',3,'Caribbean');
INSERT INTO Countries VALUES(38,'Costa Rica',3,'Caribbean');
INSERT INTO Countries VALUES(39,'El Salvador',3,'Caribbean');
INSERT INTO Countries VALUES(40,'Guatemala',3,'Caribbean');
INSERT INTO Countries VALUES(41,'Honduras',3,'Caribbean');
INSERT INTO Countries VALUES(42,'Nicaragua',3,'Caribbean');
INSERT INTO Countries VALUES(43,'Panama',3,'Caribbean');
CREATE TABLE Regions (id INTEGER PRIMARY KEY, name TEXT, countryId NUMERIC, surface NUMERIC, points TEXT, countryName TEXT, continentName TEXT);
INSERT INTO Regions VALUES(1,'Bermuda',1,53,'1216,771;1220,768;1222,771;1216,773','Bermuda','North America');
INSERT INTO Regions VALUES(2,'Alberta',2,640081,NULL,'Canada','North America');
INSERT INTO Regions VALUES(3,'British Columbia',2,925186,NULL,'Canada','North America');
INSERT INTO Regions VALUES(4,'Nunavut',2,18877867,'1004,431;1024,397;949,347;963,331;995,331;996,327;1059,328;1152,240;1203,235;1304,220;1330,215;1407,208;1516,206;1532,212;1443,234;1394,250;1333,281;1341,292;1387,322;1416,362;1354,421;1307,408;1252,401;1225,417;1193,412;1170,391;1085,431','Canada','North America');
INSERT INTO Regions VALUES(5,'Northwesr Territories',2,1183085,'826,336;811,353;823,353;838,434;1004,431;1024,397;949,347;963,331;995,331;996,327;1059,328;1152,240;1043,262;993,282;918,319;856,327','Canada','North America');
INSERT INTO Regions VALUES(6,'Yukon',2,474391,'803,322;667,431;838,431;823,353;811,353;826,336;818,328','Canada','North America');
INSERT INTO Regions VALUES(7,'Ontario',2,917741,NULL,'Canada','North America');
INSERT INTO Regions VALUES(8,'Manitoba',2,548360,NULL,'Canada','North America');
INSERT INTO Regions VALUES(9,'Saskatechewan',2,591670,NULL,'Canada','North America');
INSERT INTO Regions VALUES(10,'New Brunswick',2,71450,NULL,'Canada','North America');
INSERT INTO Regions VALUES(11,'Nova Scotia',2,53338,NULL,'Canada','North America');
INSERT INTO Regions VALUES(12,'Prince Edward Island',2,5660,NULL,'Canada','North America');
INSERT INTO Regions VALUES(13,'New Foundland nad Labrador',2,373872,NULL,'Canada','North America');
INSERT INTO Regions VALUES(14,'Quebec',NULL,NULL,NULL,'Canada','North America');
COMMIT;
