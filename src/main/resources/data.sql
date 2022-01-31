INSERT INTO SCHEDULE (id, resource_id, date)
VALUES
(1, 'RS_1', '13/06/2022'),
(2, 'RS_1', '14/06/2022'),
(3, 'RS_1', '15/06/2022'),
(4, 'RS_2', '13/06/2022'),
(5, 'RS_3', '13/06/2022');

INSERT INTO TIME_SCHEDULE (id, schedule_id, time, available)
VALUES
(1, 1, '08:00', true),
(2, 1, '09:00', true),
(3, 1, '10:00', true),
(4, 2, '08:00', true),
(5, 2, '09:00', true),
(6, 2, '10:00', true),
(7, 3, '08:00', true),
(8, 3, '09:00', true),
(9, 3, '10:00', true),
(10, 4, '08:00', true),
(11, 4, '09:00', true),
(12, 5, '08:00', true),
(13, 5, '10:00', true);