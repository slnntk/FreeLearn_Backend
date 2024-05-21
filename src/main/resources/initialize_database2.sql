-- Reset AUTO_INCREMENT for all tables
ALTER TABLE user
    AUTO_INCREMENT = 1;
ALTER TABLE teacher
    AUTO_INCREMENT = 1;
ALTER TABLE student
    AUTO_INCREMENT = 1;
ALTER TABLE course_category
    AUTO_INCREMENT = 1;
ALTER TABLE course
    AUTO_INCREMENT = 1;
ALTER TABLE course_course_category
    AUTO_INCREMENT = 1;
ALTER TABLE teacher_areas_of_expertise
    AUTO_INCREMENT = 1;
ALTER TABLE course_module
    AUTO_INCREMENT = 1;
ALTER TABLE lesson
    AUTO_INCREMENT = 1;
ALTER TABLE review
    AUTO_INCREMENT = 1;
ALTER TABLE student_course
    AUTO_INCREMENT = 1;

-- Insert users
INSERT INTO user (id, email, name, password, role)
VALUES (1, 'gustavo@guanabara.com', 'Gustavo Guanabara', 'guanabara123', 2),
       (2, 'maria.silva@example.com', 'Maria Silva', 'maria123', 1),
       (3, 'joao.souza@example.com', 'João Souza', 'joao123', 1),
       (4, 'ana.pereira@example.com', 'Ana Pereira', 'ana123', 2),
       (5, 'carlos.oliveira@example.com', 'Carlos Oliveira', 'carlos123', 2),
       (6, 'fernanda.alves@example.com', 'Fernanda Alves', 'fernanda123', 1),
       (7, 'lucas.lima@example.com', 'Lucas Lima', 'lucas123', 1),
       (8, 'juliana.martins@example.com', 'Juliana Martins', 'juliana123', 1),
       (9, 'marcos.rocha@example.com', 'Marcos Rocha', 'marcos123', 1),
       (10, 'beatriz.ferreira@example.com', 'Beatriz Ferreira', 'beatriz123', 1),
       (11, 'roberto.costa@example.com', 'Roberto Costa', 'roberto123', 1),
       (12, 'patricia.melo@example.com', 'Patrícia Melo', 'patricia123', 1),
       (13, 'rafael.santos@example.com', 'Rafael Santos', 'rafael123', 1),
       (14, 'admin@admin.com', 'Adminstrador', 'admin', 0);

-- Insert teachers
INSERT INTO teacher (id, hours_taught, overall_rating, user_id, employee_id)
VALUES (1, 500, 4.9, 1, 'EMP001'),
       (2, 300, 4.8, 4, 'EMP002'),
       (3, 400, 4.7, 5, 'EMP003');

-- Insert students
INSERT INTO student (id, hours_watched, number_of_courses_subscribed, user_id)
VALUES (1, 150, 3, 2),
       (2, 200, 5, 3),
       (3, 100, 2, 6),
       (4, 50, 1, 7),
       (5, 75, 3, 8),
       (6, 60, 2, 9),
       (7, 90, 4, 10),
       (8, 110, 3, 11),
       (9, 130, 4, 12),
       (10, 120, 5, 13);

-- Insert course categories
INSERT INTO course_category (id, description, name)
VALUES (1, 'Programação e Desenvolvimento', 'Programação'),
       (2, 'Tecnologia da Informação', 'TI'),
       (3, 'Design e Multimídia', 'Design');

-- Insert courses
INSERT INTO course (id, duration_hours, teacher_id, description, image_url, language, link, title)
VALUES (1, 20, 1, 'Curso de Python para Iniciantes', 'python.jpg', 'Português', 'http://curso.python.com',
        'Python para Iniciantes'),
       (2, 25, 1, 'Curso de Algoritmos e Lógica de Programação', 'algoritmos.jpg', 'Português',
        'http://curso.algoritmos.com', 'Algoritmos e Lógica de Programação'),
       (3, 30, 2, 'Curso de Redes de Computadores', 'redes.jpg', 'Português', 'http://curso.redes.com',
        'Redes de Computadores'),
       (4, 35, 3, 'Curso de Design Gráfico', 'design.jpg', 'Português', 'http://curso.design.com', 'Design Gráfico');

-- Insert course categories mapping
INSERT INTO course_course_category (id, course_id, category_id)
VALUES (1, 1, 1),
       (2, 2, 1),
       (3, 3, 2),
       (4, 4, 3);

-- Insert teacher areas of expertise
INSERT INTO teacher_areas_of_expertise (teacher_id, areas_of_expertise)
VALUES (1, 'Programação'),
       (1, 'Lógica de Programação'),
       (2, 'Redes de Computadores'),
       (3, 'Design Gráfico');

-- Insert course modules
INSERT INTO course_module (id, sequence_number, course_id, description, title)
VALUES
    -- Python Course Modules
    (1, 1, 1, 'Introdução ao Python', 'Módulo 1'),
    (2, 2, 1, 'Estruturas Condicionais', 'Módulo 2'),
    (3, 3, 1, 'Estruturas de Repetição', 'Módulo 3'),
    (4, 4, 1, 'Funções em Python', 'Módulo 4'),
    (5, 5, 1, 'Manipulação de Arquivos', 'Módulo 5'),
    (6, 6, 1, 'Listas e Tuplas', 'Módulo 6'),
    (7, 7, 1, 'Dicionários', 'Módulo 7'),
    (8, 8, 1, 'Tratamento de Erros', 'Módulo 8'),
    (9, 9, 1, 'Programação Orientada a Objetos', 'Módulo 9'),
    (10, 10, 1, 'Módulos e Pacotes', 'Módulo 10'),

    -- Algorithms Course Modules
    (11, 1, 2, 'Conceitos de Algoritmos', 'Módulo 1'),
    (12, 2, 2, 'Estruturas de Repetição', 'Módulo 2'),
    (13, 3, 2, 'Variáveis e Tipos de Dados', 'Módulo 3'),
    (14, 4, 2, 'Funções e Procedimentos', 'Módulo 4'),
    (15, 5, 2, 'Estruturas de Dados', 'Módulo 5'),
    (16, 6, 2, 'Recursividade', 'Módulo 6'),
    (17, 7, 2, 'Ordenação e Busca', 'Módulo 7'),
    (18, 8, 2, 'Algoritmos Gulosos', 'Módulo 8'),
    (19, 9, 2, 'Divisão e Conquista', 'Módulo 9'),
    (20, 10, 2, 'Programação Dinâmica', 'Módulo 10'),

    -- Networking Course Modules
    (21, 1, 3, 'Introdução às Redes', 'Módulo 1'),
    (22, 2, 3, 'Modelos OSI e TCP/IP', 'Módulo 2'),
    (23, 3, 3, 'Camada de Rede', 'Módulo 3'),
    (24, 4, 3, 'Camada de Transporte', 'Módulo 4'),
    (25, 5, 3, 'Configuração de Roteadores', 'Módulo 5'),
    (26, 6, 3, 'Redes Sem Fio', 'Módulo 6'),
    (27, 7, 3, 'Segurança de Redes', 'Módulo 7'),
    (28, 8, 3, 'VPNs e Tunneling', 'Módulo 8'),
    (29, 9, 3, 'Monitoramento de Redes', 'Módulo 9'),
    (30, 10, 3, 'Diagnóstico e Solução de Problemas', 'Módulo 10'),

    -- Design Course Modules
    (31, 1, 4, 'Introdução ao Design Gráfico', 'Módulo 1'),
    (32, 2, 4, 'Teoria das Cores', 'Módulo 2'),
    (33, 3, 4, 'Tipografia', 'Módulo 3'),
    (34, 4, 4, 'Ferramentas de Design', 'Módulo 4'),
    (35, 5, 4, 'Design de Logotipos', 'Módulo 5'),
    (36, 6, 4, 'Design de Publicações', 'Módulo 6'),
    (37, 7, 4, 'Design de Embalagens', 'Módulo 7'),
    (38, 8, 4, 'Design de Interfaces', 'Módulo 8'),
    (39, 9, 4, 'Design para Mídias Sociais', 'Módulo 9'),
    (40, 10, 4, 'Projeto Final', 'Módulo 10');

-- Insert lessons
-- Insert lessons
INSERT INTO lesson (id, duration_minutes, module_id, title, video_url)
VALUES
    -- Python Lessons
    (1, 30, 1, 'O que é Python?', 'video1.mp4'),
    (2, 45, 2, 'Estruturas Condicionais em Python', 'video2.mp4'),
    (3, 60, 3, 'Estruturas de Repetição em Python', 'video3.mp4'),
    (4, 50, 4, 'Funções em Python', 'video4.mp4'),
    (5, 55, 5, 'Manipulação de Arquivos em Python', 'video5.mp4'),
    (6, 40, 1, 'Instalação e Configuração', 'video6.mp4'),
    (7, 50, 1, 'Variáveis e Tipos de Dados', 'video7.mp4'),
    (8, 35, 1, 'Operadores e Expressões', 'video8.mp4'),
    (9, 45, 2, 'Estruturas de Decisão', 'video9.mp4'),
    (10, 60, 2, 'Estruturas Condicionais Aninhadas', 'video10.mp4'),
    (11, 50, 2, 'Switch Case', 'video11.mp4'),
    (12, 45, 2, 'Estruturas de Seleção Múltipla', 'video12.mp4'),
    (13, 60, 3, 'Laços de Repetição', 'video13.mp4'),
    (14, 55, 3, 'Laços While e For', 'video14.mp4'),
    (15, 50, 3, 'Uso de Break e Continue', 'video15.mp4'),
    (16, 60, 3, 'Aplicações Práticas de Laços', 'video16.mp4'),
    (17, 45, 4, 'Definição de Funções', 'video17.mp4'),
    (18, 50, 4, 'Parâmetros e Argumentos', 'video18.mp4'),
    (19, 55, 4, 'Funções Recursivas', 'video19.mp4'),
    (20, 60, 4, 'Funções Lambda', 'video20.mp4'),

    -- Algorithms Lessons
    (21, 35, 11, 'Introdução a Algoritmos', 'video21.mp4'),
    (22, 40, 12, 'Estruturas de Repetição em Algoritmos', 'video22.mp4'),
    (23, 45, 13, 'Variáveis e Tipos de Dados', 'video23.mp4'),
    (24, 50, 14, 'Funções e Procedimentos', 'video24.mp4'),
    (25, 60, 15, 'Estruturas de Dados', 'video25.mp4'),
    (26, 35, 11, 'Operadores Lógicos', 'video26.mp4'),
    (27, 40, 11, 'Pseudocódigo e Fluxogramas', 'video27.mp4'),
    (28, 50, 11, 'Algoritmos de Busca e Ordenação', 'video28.mp4'),
    (29, 45, 12, 'Laços de Repetição', 'video29.mp4'),
    (30, 60, 12, 'Laços While e For', 'video30.mp4'),
    (31, 55, 12, 'Estruturas de Controle', 'video31.mp4'),
    (32, 50, 12, 'Aplicações Práticas de Laços', 'video32.mp4'),
    (33, 60, 13, 'Manipulação de Variáveis', 'video33.mp4'),
    (34, 55, 13, 'Tipos de Dados', 'video34.mp4'),
    (35, 50, 13, 'Operações com Variáveis', 'video35.mp4'),
    (36, 45, 13, 'Conversão de Tipos', 'video36.mp4'),
    (37, 60, 14, 'Criação de Funções', 'video37.mp4'),
    (38, 55, 14, 'Funções Recursivas', 'video38.mp4'),
    (39, 50, 14, 'Funções Aninhadas', 'video39.mp4'),
    (40, 60, 14, 'Parâmetros e Argumentos', 'video40.mp4'),

    -- Networking Lessons
    (41, 30, 21, 'Fundamentos de Redes', 'video41.mp4'),
    (42, 45, 22, 'Modelo OSI', 'video42.mp4'),
    (43, 60, 23, 'Camada de Rede', 'video43.mp4'),
    (44, 50, 24, 'Camada de Transporte', 'video44.mp4'),
    (45, 55, 25, 'Configuração de Roteadores', 'video45.mp4'),
    (46, 40, 21, 'Tipos de Redes', 'video46.mp4'),
    (47, 50, 21, 'Topologias de Rede', 'video47.mp4'),
    (48, 35, 21, 'Protocolos de Rede', 'video48.mp4'),
    (49, 45, 22, 'Modelo TCP/IP', 'video49.mp4'),
    (50, 60, 22, 'Comparação OSI e TCP/IP', 'video50.mp4'),
    (51, 50, 22, 'Camadas do Modelo OSI', 'video51.mp4'),
    (52, 45, 22, 'Camadas do Modelo TCP/IP', 'video52.mp4'),
    (53, 60, 23, 'IP e Endereçamento', 'video53.mp4'),
    (54, 55, 23, 'Sub-redes', 'video54.mp4'),
    (55, 50, 23, 'Roteamento', 'video55.mp4'),
    (56, 45, 23, 'IPv4 e IPv6', 'video56.mp4'),
    (57, 60, 24, 'TCP e UDP', 'video57.mp4'),
    (58, 55, 24, 'Portas e Sockets', 'video58.mp4'),
    (59, 50, 24, 'Controle de Fluxo', 'video59.mp4'),
    (60, 45, 24, 'Controle de Congestionamento', 'video60.mp4'),

    -- Design Lessons
    (61, 35, 31, 'O que é Design Gráfico?', 'video61.mp4'),
    (62, 40, 32, 'Fundamentos da Teoria das Cores', 'video62.mp4'),
    (63, 45, 33, 'Introdução à Tipografia', 'video63.mp4'),
    (64, 50, 34, 'Principais Ferramentas de Design', 'video64.mp4'),
    (65, 60, 35, 'Design de Logotipos', 'video65.mp4'),
    (66, 35, 31, 'História do Design', 'video66.mp4'),
    (67, 40, 31, 'Princípios do Design', 'video67.mp4'),
    (68, 50, 31, 'Elementos Visuais', 'video68.mp4'),
    (69, 45, 32, 'Psicologia das Cores', 'video69.mp4'),
    (70, 60, 32, 'Combinações de Cores', 'video70.mp4'),
    (71, 55, 32, 'Cores e Emoções', 'video71.mp4'),
    (72, 50, 32, 'Uso de Cores em Marcas', 'video72.mp4'),
    (73, 60, 33, 'Tipos de Fontes', 'video73.mp4'),
    (74, 55, 33, 'Escolha de Tipografia', 'video74.mp4'),
    (75, 50, 33, 'Combinação de Fontes', 'video75.mp4'),
    (76, 45, 33, 'Legibilidade e Acessibilidade', 'video76.mp4'),
    (77, 60, 34, 'Adobe Photoshop', 'video77.mp4'),
    (78, 55, 34, 'Adobe Illustrator', 'video78.mp4'),
    (79, 50, 34, 'Ferramentas Online', 'video79.mp4'),
    (80, 45, 34, 'Softwares de Design Gráfico', 'video80.mp4');

-- Insert reviews
INSERT INTO review (id, comment, course_id, student_id, teacher_id, rating, comment)
VALUES (1, 5, 'Excelente curso! Aprendi muito.', 1, 1),
       (2, 4, 'Muito bom, mas poderia ter mais exemplos práticos.', 1, 2),
       (3, 5, 'Ótimo conteúdo e didática do professor.', 2, 1),
       (4, 3, 'Bom curso, mas poderia ser mais detalhado.', 2, 3),
       (5, 4, 'Gostei bastante, recomendo!', 3, 4),
       (6, 5, 'Curso excelente, muito bem explicado.', 3, 5),
       (7, 4, 'Bom curso de design, aprendi bastante.', 4, 6),
       (8, 5, 'Material muito completo e didático.', 4, 7);

-- Insert student course subscriptions
INSERT INTO student_course (id, course_id, student_id)
VALUES (1, 1, 1),
       (2, 2, 1),
       (3, 3, 1),
       (4, 1, 2),
       (5, 2, 2),
       (6, 1, 3),
       (7, 3, 3),
       (8, 4, 3),
       (9, 3, 4),
       (10, 4, 4);


-- Insert student course subscriptions
INSERT INTO student_course (id, course_id, student_id)
VALUES (1, 1, 1),
       (2, 2, 1),
       (3, 3, 1),
       (4, 4, 1),
       (5, 1, 2),
       (6, 2, 2),
       (7, 3, 2),
       (8, 4, 2),
       (9, 1, 3),
       (10, 3, 3),
       (11, 4, 3),
       (12, 1, 4),
       (13, 2, 4),
       (14, 1, 5),
       (15, 2, 5),
       (16, 1, 6),
       (17, 3, 6),
       (18, 2, 7),
       (19, 3, 7),
       (20, 1, 8),
       (21, 2, 8),
       (22, 1, 9),
       (23, 3, 9),
       (24, 1, 10),
       (25, 2, 10);

-- Insert reviews
INSERT INTO review (id, course_id, student_id, teacher_id, comment)
VALUES (1, 1, 1, 1, 'Ótimo curso, aprendi muito!'),
       (2, 2, 1, 1, 'Excelente conteúdo, recomendo!'),
       (3, 3, 1, 2, 'Professor muito didático, aprendi bastante.'),
       (4, 4, 1, 3, 'Curso de design muito completo, vale a pena.'),
       (5, 1, 2, 1, 'Aulas muito bem explicadas, adorei!'),
       (6, 2, 2, 1, 'Curso desafiador, ótimo para aprender.'),
       (7, 3, 2, 2, 'Conteúdo sobre redes muito interessante.'),
       (8, 4, 2, 3, 'Aprendi técnicas de design que não conhecia.'),
       (9, 1, 3, 1, 'Curso de Python muito bom para iniciantes.'),
       (10, 3, 3, 1, 'Redes de computadores são minha paixão!');


