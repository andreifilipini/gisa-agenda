INSERT INTO gisaagendadb.schedule (resource_id, date)
VALUES
('RS_1', '13/06/2022'),
('RS_1', '14/06/2022'),
('RS_1', '15/06/2022'),
('RS_2', '13/06/2022'),
('RS_3', '13/06/2022');

INSERT INTO gisaagendadb.time_schedule  (schedule_id, time, available)
VALUES
((SELECT id FROM gisaagendadb.schedule WHERE resource_id = 'RS_1' and date = '13/06/2022'), '08:00', true),
((SELECT id FROM gisaagendadb.schedule WHERE resource_id = 'RS_1' and date = '13/06/2022'), '09:00', true),
((SELECT id FROM gisaagendadb.schedule WHERE resource_id = 'RS_1' and date = '13/06/2022'), '10:00', true),
((SELECT id FROM gisaagendadb.schedule WHERE resource_id = 'RS_1' and date = '14/06/2022'), '08:00', true),
((SELECT id FROM gisaagendadb.schedule WHERE resource_id = 'RS_1' and date = '14/06/2022'), '09:00', true),
((SELECT id FROM gisaagendadb.schedule WHERE resource_id = 'RS_1' and date = '14/06/2022'), '10:00', true),
((SELECT id FROM gisaagendadb.schedule WHERE resource_id = 'RS_1' and date = '15/06/2022'), '08:00', true),
((SELECT id FROM gisaagendadb.schedule WHERE resource_id = 'RS_1' and date = '15/06/2022'), '09:00', true),
((SELECT id FROM gisaagendadb.schedule WHERE resource_id = 'RS_1' and date = '15/06/2022'), '10:00', true),
((SELECT id FROM gisaagendadb.schedule WHERE resource_id = 'RS_2' and date = '13/06/2022'), '08:00', true),
((SELECT id FROM gisaagendadb.schedule WHERE resource_id = 'RS_2' and date = '13/06/2022'), '09:00', true),
((SELECT id FROM gisaagendadb.schedule WHERE resource_id = 'RS_3' and date = '13/06/2022'), '08:00', true),
((SELECT id FROM gisaagendadb.schedule WHERE resource_id = 'RS_3' and date = '13/06/2022'), '10:00', true);