ALTER TABLE user AUTO_INCREMENT = 1;
ALTER TABLE teacher AUTO_INCREMENT = 1;
ALTER TABLE student AUTO_INCREMENT = 1;
ALTER TABLE course_category AUTO_INCREMENT = 1;
ALTER TABLE course AUTO_INCREMENT = 1;
ALTER TABLE course_course_category AUTO_INCREMENT = 1;
ALTER TABLE teacher_areas_of_expertise AUTO_INCREMENT = 1;
ALTER TABLE course_module AUTO_INCREMENT = 1;
ALTER TABLE lesson AUTO_INCREMENT = 1;
ALTER TABLE review AUTO_INCREMENT = 1;



INSERT INTO user (email, name, password)
VALUES ('email1@example.com', 'User1', 'password1'),
       ('email2@example.com', 'User2', 'password2'),
       ('email3@example.com', 'User3', 'password3');

INSERT INTO teacher (hours_taught, overall_rating, user_id, employee_id)
VALUES (50, 4.5, 1, 'EMP001'),
       (30, 4.0, 2, 'EMP002'),
       (40, 4.2, 3, 'EMP003');

INSERT INTO student (hours_watched, number_of_courses_subscribed, user_id)
VALUES (100, 5, 1),
       (80, 4, 2),
       (120, 6, 3);


INSERT INTO course_category (description, name)
VALUES ('Category 1 description', 'Category 1'),
       ('Category 2 description', 'Category 2'),
       ('Category 3 description', 'Category 3');

INSERT INTO course (duration_hours, teacher_id, description, image_url, language, link, title)
VALUES (10, 1, 'Course 1 description', 'image1.jpg', 'English', 'http://course1.com', 'Course 1'),
       (15, 2, 'Course 2 description', 'image2.jpg', 'English', 'http://course2.com', 'Course 2'),
       (20, 3, 'Course 3 description', 'image3.jpg', 'English', 'http://course3.com', 'Course 3');

INSERT INTO course_course_category (course_id, category_id)
VALUES (1, 1),
       (2, 2),
       (3, 3);


INSERT INTO teacher_areas_of_expertise (teacher_id, areas_of_expertise)
VALUES (1, 'Mathematics'),
       (2, 'Science'),
       (3, 'History');


INSERT INTO course_module (sequence_number, course_id, description, title)
VALUES (1, 1, 'Module 1 description', 'Module 1'),
       (2, 2, 'Module 2 description', 'Module 2'),
       (3, 3, 'Module 3 description', 'Module 3');


INSERT INTO lesson (duration_minutes, module_id, title, video_url)
VALUES (30, 1, 'Lesson 1', 'video1.mp4'),
       (45, 2, 'Lesson 2', 'video2.mp4'),
       (60, 3, 'Lesson 3', 'video3.mp4');


INSERT INTO student_course (id, course_id, student_id)
VALUES (1, 1, 1),
       (2, 2, 2),
       (3, 3, 3);


INSERT INTO review (course_id, student_id, teacher_id, comment)
VALUES (1, 1, 1, 'Great course!'),
       (2, 2, 2, 'Excellent content'),
       (3, 3, 3, 'Very informative');

