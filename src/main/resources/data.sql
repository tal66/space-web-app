
INSERT INTO Customers (FIRST_NAME, LAST_NAME, user_name, email, phone)
VALUES
('user', 'lastname', 'user1', 'user@email.com', '123456'),
('Neil', 'lastname', 'user2', 'user2@email.com', '223456'),
('Admin', 'lastname', 'admin', 'admin@email.com', '323456');

--

INSERT INTO Destinations (id, name, type, radius_km, avg_orbit_distance_km , orbit_period_earth_years , mean_temperature_c , main_pic, fact_To_Display)
VALUES
(1, 'Mercury', 1, 2439.70, 57909227, 0.2409, 167, '01_mercury.png',
'Daytime Temperatures can reach 430 degrees (C), and drop to -180 degrees (C) at night. Have a nice trip.'),
(2, 'Venus', 1, 6051.8, 108209475, 0.616, 464, '02_venus.png',
'Surface temperatures on Venus are about 475 degrees (C). don''t forget your hat.)'),
(3, 'Earth', 1, 6371, 149598262, 1, 15, '03_earth.png',
'No vacations on this planet.'),
(4, 'Mars', 1, 3389.5, 227943824, 1.8808, -65, '04_mars.png',
'Hoping to see Matt Damon there?'),
(5, 'Jupiter', 1, 69911, 778340821, 11.862, -110, '05_jupiter.png',
'The largest planet in the solar system.'),
(6, 'Saturn', 1,  58232, 1426666422, 29, -140, '06_saturn.png',
'The only planet to have rings, made of chunks of ice and rock.'),
(7, 'Uranus', 1,  25362, 2870658186, 84, -195, '07_uranus.png',
'Uranus is an Ice Giant planet. It has 27 known moons, named after characters from the works of  Shakespeare and Alexander Pope. '),
(8, 'Neptune', 1,  24622, 4498396441, 165, -200, '08_neptune.png',
'Neptune is a Dark, cold and windy ice giant. Have a nice time!'),
(9, 'Earth''s Moon', 3, 1737.1, 384400, 0.0748, -20, '00_earth_moon.png',
'afraid to go too far?'),
(10, 'Titan', 3, 2574.7, 1221865, 0.0437, 0, '10_titan.jpg',
'Titan is the largest moon of Saturn.'),
(11, 'Pluto', 2, 1151, 5906440628, 248.89, -232, '09_pluto.jpg',
'The surface of Pluto is extremely cold, so it seems unlikely that life could exist there. Have a good trip.');

INSERT INTO Trips (from_dest, to_dest, trip_date, n_Tickets_max, n_Tickets_sold)
VALUES
(3, 1, '2022-08-01', 10, 0),
(3, 1, '2022-08-02', 10, 0),
(3, 1, '2022-08-03', 10, 0),
(3, 2, '2022-12-01', 10, 0),
(3, 2, '2022-02-01', 10, 0),
(3, 2, '2023-03-01', 10, 0),
(3, 4, '2023-12-01', 10, 0),
(3, 4, '2024-12-02', 10, 0),
(3, 4, '2023-12-03', 10, 0),
(3, 4, '2023-12-20', 10, 0),
(3, 4, '2030-12-21', 10, 0),
(3, 4, '2024-12-22', 10, 0),
(3, 5, '2024-11-10', 10, 0),
(3, 5, '2024-12-11', 10, 0),
(3, 5, '2024-12-12', 10, 0)
;

INSERT INTO Trips (from_dest, to_dest, trip_date, n_Tickets_max, n_Tickets_sold)
VALUES
(3, 6, '2022-12-20', 10, 0),
(3, 6, '2022-12-21', 10, 0),
(3, 6, '2022-12-22', 10, 0),
(3, 6, '2025-12-20', 10, 0),
(3, 6, '2025-12-21', 10, 0),
(3, 6, '2025-12-22', 10, 0),
(3, 7, '2022-01-10', 10, 0),
(3, 7, '2022-01-10', 10, 0),
(3, 7, '2022-01-10', 10, 0),
(3, 7, '2025-01-11', 10, 0),
(3, 7, '2025-01-12', 10, 0),
(3, 7, '2025-01-13', 10, 0),
(3, 8, '2022-11-01', 10, 0),
(3, 8, '2022-12-02', 10, 0),
(3, 8, '2022-12-03', 10, 0),
(3, 8, '2024-11-01', 10, 0),
(3, 8, '2024-12-02', 10, 0),
(3, 8, '2024-12-03', 10, 0),
(3, 9, '2022-07-01', 10, 0),
(3, 9, '2022-07-02', 10, 0),
(3, 9, '2022-07-03', 10, 0),
(3, 9, '2024-11-01', 10, 0),
(3, 9, '2024-12-02', 10, 0),
(3, 9, '2024-12-03', 10, 0),
(3, 10, '2024-11-01', 10, 0),
(3, 10, '2024-12-02', 10, 0),
(3, 10, '2024-12-03', 10, 0),
(3, 10, '2022-07-20', 10, 0),
(3, 10, '2022-07-21', 10, 0),
(3, 10, '2022-07-22', 10, 0),
(3, 11, '2025-10-10', 15, 0),
(3, 11, '2025-11-10', 15, 0),
(3, 11, '2025-12-10', 20, 0)
;


--INSERT INTO images (address, text, article_id)
INSERT INTO images (address, text, id)
VALUES
 ('\img-news\elon--mars.jpg', 'Elon', 1),
 ('\img-news\bob--galaxy.jpg', 'Bob', 2);

INSERT INTO news_articles (headline, text, date_created, id)
VALUES
 ('New trip to Mars was added in 2030', '', '2030-06-18', 1),
 ('Photos from customers:', '', '2030-06-20', 2);

INSERT INTO news_article_images (news_article_id, image_id)
VALUES
 (2, 1),
 (2, 2);

INSERT INTO reviews (id, customer_id, stars, headline, text, date_created)
VALUES
 (1, 2, 5, 'Had a great trip to the moon', '', '1969-07-20');
