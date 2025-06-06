create table livro(
	idLivro int auto_increment primary key not null,
    titulo varchar(90) not null,
    autor varchar(80) not null,
    editora varchar(90) not null,
    anoPubli date not null,
    status ENUM('Ativo', 'Baixa', 'Manutenção') DEFAULT 'Ativo'
);

CREATE TABLE livros (
    idLivro INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    titulo VARCHAR(100) NOT NULL,
    autores VARCHAR(100) NOT NULL,
    tradutores VARCHAR(100),
    edicao VARCHAR(100),
    editora VARCHAR(100) NOT NULL,
    local_publicacao VARCHAR(100),
    data_publicacao DATE,
    numero_paginas INT UNSIGNED,
    isbn VARCHAR(20) UNIQUE,
    status TINYINT NOT NULL DEFAULT 3, -- 1: Baixa, 2: Manutenção, 3: Ativo
    CONSTRAINT ck_livro_status CHECK (status IN (1, 2, 3))
);

INSERT INTO livros (titulo, autores, tradutores, edicao, editora, local_publicacao, data_publicacao, numero_paginas, isbn, status)
VALUES
('Dom Quixote', 'Miguel de Cervantes', 'Vários', '1ª Edição', 'Editora A', 'Madrid', '1605-01-01', 1000, '978-0000000000', 3),
('Cem Anos de Solidão', 'Gabriel García Márquez', NULL, 'Edição Especial', 'Editora B', 'Bogotá', '1967-05-30', 450, '978-1111111111', 3),
('1984', 'George Orwell', 'Fulano da Silva', 'Reimpressão', 'Editora C', 'Londres', '1949-06-08', 328, '978-2222222222', 3),
('O Pequeno Príncipe', 'Antoine de Saint-Exupéry', NULL, '75ª Edição', 'Editora D', 'Paris', '1943-04-06', 96, '978-3333333333', 1),
('A Arte da Guerra', 'Sun Tzu', 'Ciclano Pereira', 'Edição de Bolso', 'Editora E'livros, 'China', NULL, 120, '978-4444444444', 2);

-- Você pode inserir mais registros seguindo a mesma estrutura do VALUES
-- Certifique-se de fornecer valores para todas as colunas NOT NULL.
-- Colunas com DEFAULT terão esse valor caso você não especifique um.
-- Colunas AUTO_INCREMENT (idLivro) não precisam ser incluídas na lista de colunas.