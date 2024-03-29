-- Usuários
INSERT INTO user (email, name, password)
VALUES
    ('professor1@example.com', 'Professor 1', 'password1'),
    ('professor2@example.com', 'Professor 2', 'password2'),
    ('professor3@example.com', 'Professor 3', 'password3'),
    ('aluno1@example.com', 'Aluno 1', 'password4'),
    ('aluno2@example.com', 'Aluno 2', 'password5'),
    ('aluno3@example.com', 'Aluno 3', 'password6');

-- Professores
INSERT INTO teacher (employee_id, hours_taught, overall_rating, user_id)
VALUES
    ('EMP001', 500, 4.5, 1),
    ('EMP002', 300, 4.2, 2),
    ('EMP003', 700, 4.8, 3);

-- Relacionamento entre professores e usuários
UPDATE teacher SET user_id = 1 WHERE id = 1;
UPDATE teacher SET user_id = 2 WHERE id = 2;
UPDATE teacher SET user_id = 3 WHERE id = 3;

-- Alunos
INSERT INTO student (hours_watched, number_of_courses_subscribed, user_id)
VALUES
    (50, 2, 4),
    (30, 1, 5),
    (20, 3, 6);

-- Relacionamento entre alunos e usuários
UPDATE student SET user_id = 4 WHERE id = 1;
UPDATE student SET user_id = 5 WHERE id = 2;
UPDATE student SET user_id = 6 WHERE id = 3;

-- Categorias de cursos
INSERT INTO course_category (description, name)
VALUES
    ('Desenvolvimento Web', 'Web Development'),
    ('Ciência de Dados', 'Data Science'),
    ('Design Gráfico', 'Graphic Design');

-- Cursos
INSERT INTO course (description, duration_hours, image_url, language, link, title, teacher_id)
VALUES
    ('Aprenda HTML, CSS e JavaScript para criar sites incríveis', 30, 'html_css_js.jpg', 'Português', 'link1', 'Desenvolvimento Web do Zero', 1),
    ('Introdução ao Python para Análise de Dados', 20, 'python_data_analysis.jpg', 'Português', 'link2', 'Python para Ciência de Dados', 2),
    ('Photoshop: Fundamentos e Técnicas Avançadas', 25, 'photoshop.jpg', 'Português', 'link3', 'Domine o Photoshop', 3);

-- Relacionamento entre cursos e categorias
INSERT INTO course_course_category (course_id, category_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3);

-- Módulos
INSERT INTO course_module (description, sequence_number, title, course_id)
VALUES
    ('Introdução ao HTML', 1, 'HTML Básico', 1),
    ('CSS: Estilizando seu site', 2, 'CSS Intermediário', 1),
    ('JavaScript: Adicionando interatividade', 3, 'JavaScript Avançado', 1),
    ('Introdução ao Python', 1, 'Python Básico', 2),
    ('Análise de Dados com Pandas', 2, 'Pandas', 2),
    ('Visualização de Dados com Matplotlib', 3, 'Matplotlib', 2),
    ('Introdução ao Photoshop', 1, 'Conhecendo a Interface', 3),
    ('Ferramentas e Técnicas de Edição', 2, 'Edição de Imagens', 3),
    ('Efeitos Especiais e Manipulação Avançada', 3, 'Photoshop Avançado', 3);

-- Lições
INSERT INTO lesson (duration_minutes, title, video_url, module_id)
VALUES
    (30, 'Tags HTML Básicas', 'video1.mp4', 1),
    (25, 'Seletores CSS', 'video2.mp4', 2),
    (40, 'Eventos JavaScript', 'video3.mp4', 3),
    (20, 'Variáveis em Python', 'video4.mp4', 4),
    (35, 'Análise de Dados com Pandas', 'video5.mp4', 5),
    (30, 'Gráficos com Matplotlib', 'video6.mp4', 6),
    (30, 'Interface do Photoshop', 'video7.mp4', 7),
    (45, 'Edição de Imagens', 'video8.mp4', 8),
    (50, 'Efeitos Especiais', 'video9.mp4', 9);

-- Insira as avaliações dos cursos pelos alunos
INSERT INTO review (comment, course_id, student_id, teacher_id)
VALUES
    ('Ótimo curso para iniciantes!', 1, 1, 1),
    ('Conteúdo muito bom, mas poderia ter mais exemplos práticos', 2, 2, 2),
    ('Excelente professor, explica de forma clara e objetiva', 3, 3, 3);


-- Áreas de expertise dos professores
INSERT INTO teacher_areas_of_expertise (teacher_id, areas_of_expertise)
VALUES
    (1, 'HTML, CSS, JavaScript'),
    (2, 'Python, Data Analysis'),
    (3, 'Photoshop, Design Gráfico');

-- Insira os dados de inscrição dos alunos nos cursos
INSERT INTO student_course (student_id, course_id)
VALUES
    (4, 1),
    (5, 2),
    (6, 3);
