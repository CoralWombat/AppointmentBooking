--insert into category(id, name) values(1, 'parent category');
--insert into category(id, name, parent_id) values(2, 'child category', 1);

--insert into opening_hours(category_id, day, from_time, to_time) values(2, 0, '08:00', '16:00');
--insert into opening_hours(category_id, day, from_time, to_time) values(1, 1, '08:00', '16:00');
--insert into opening_hours(category_id, day, from_time, to_time) values(1, 2, '08:00', '16:00');
--insert into opening_hours(category_id, day, from_time, to_time) values(1, 3, '08:00', '16:00');
--insert into opening_hours(category_id, day, from_time, to_time) values(2, 4, '08:00', '12:00');

--insert into reservation(id, category_id, from_time, to_time, customer_id) values(1, 2, '2021-01-01T08:00:00.000Z', '2021-01-01T08:30:00.000Z', 'Gipsz Jakab');
--insert into reservation(id, category_id, from_time, to_time, customer_id) values(2, 1, '2021-01-01T08:00:00.000Z', '2021-01-01T08:30:00.000Z', 'Gipsz Jakabné');
