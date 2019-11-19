

USE warehouseSchema;


SET autocommit = 0;

START TRANSACTION;

INSERT INTO `ORDER` 
(`BUYER_EMAIL`, `CREATION_DATE`) 
VALUES 
('brad.pitt@famouspeople.com', '2019-11-01 13:02:54'),
('angelina.jolie@famouspeople.com', '2019-11-02 14:03:55'),
('matt.damon@famouspeople.com', '2019-11-03 15:04:56'),
('will.smith@famouspeople.com', '2019-11-04 16:05:57'),
('mel.gibson@famouspeople.com', '2019-11-05 17:06:58'),
('scarlett.johanson@famouspeople.com', '2019-11-06 18:07:59'),
('charlize.theron@famouspeople.com', '2019-11-07 19:08:00'),
('jennifer.aniston@famouspeople.com', '2019-11-08 20:09:01'),
('megan.fox@famouspeople.com', '2019-11-09 21:00:02'),
('jessica.alba@famouspeople.com', '2019-11-10 22:01:03'),
('leonardo.dicaprio@famouspeople.com', '2019-11-11 23:02:04'),
('johny.deep@famouspeople.com', '2019-11-12 00:03:05'),
('tom.cruise@famouspeople.com', '2019-11-13 01:04:06');


COMMIT;

SET autocommit = 1;