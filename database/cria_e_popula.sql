-- 0. Cria o banco de dados se ele ainda não existir
CREATE DATABASE IF NOT EXISTS lebooks;

-- 1. Garante que estamos no banco certo
USE lebooks;

-- 2. Desativa a segurança para permitir apagar e recriar tudo sem erro
SET FOREIGN_KEY_CHECKS = 0;

-- 3. Limpa a casa (Apaga as tabelas antigas se existirem, na ordem correta)
DROP TABLE IF EXISTS tb_emprestimo;
DROP TABLE IF EXISTS tb_livro;
DROP TABLE IF EXISTS tb_leitor;
DROP TABLE IF EXISTS tb_autor;

-- 4. Cria a tabela AUTOR 
CREATE TABLE tb_autor (
  id int NOT NULL AUTO_INCREMENT,
  nome varchar(255) NOT NULL,
  nacionalidade varchar(100) DEFAULT NULL,
  PRIMARY KEY (id)
);

-- 5. Cria a tabela LEITOR 
CREATE TABLE tb_leitor (
  id int NOT NULL AUTO_INCREMENT,
  nome varchar(255) NOT NULL,
  cpf varchar(20) NOT NULL,
  email varchar(100) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY cpf (cpf)
);

-- 6. Cria a tabela LIVRO 
CREATE TABLE tb_livro (
  id int NOT NULL AUTO_INCREMENT,
  titulo varchar(255) NOT NULL,
  ano int NOT NULL,
  disponivel tinyint(1) DEFAULT '1',
  autor_id int NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (autor_id) REFERENCES tb_autor (id)
);

-- 7. Cria a tabela EMPRESTIMO 
CREATE TABLE tb_emprestimo (
  id int NOT NULL AUTO_INCREMENT,
  dataEmprestimo date NOT NULL,
  dataDevolucao date DEFAULT NULL,
  dataDevolucaoEfetiva date DEFAULT NULL,
  leitor_id int NOT NULL,
  livro_id int NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (leitor_id) REFERENCES tb_leitor (id),
  FOREIGN KEY (livro_id) REFERENCES tb_livro (id)
);

-- 8. Inserção de dados na tabela tb_autor
INSERT INTO tb_autor (id, nome, nacionalidade) VALUES
(1, 'James Stwart', 'Americano'),
(2, 'Machado de Assis', 'Brasileiro'),
(3, 'Clarice Lispector', 'Brasileira'),
(4, 'Nelson Rodrigues', 'Brasileiro'),
(5, 'Guimarães Rosa', 'Brasileiro'),
(6, 'Charles H. Lehmann', 'Inglês'),
(7, 'Graciliano Ramos', 'Brasileiro'),
(8, 'Jorge Amado', 'Brasileiro'),
(9, 'João Guimarães Rosa', 'Brasileiro'),
(10, 'Miguel de Cervantes', 'Espanhol'),
(11, 'Mary Shelley', 'Britânica'),
(12, 'Fiódor Dostoiévsky', 'Russo'),
(13, 'George Orwell', 'Britânico'),
(14, 'Dante Alighieri', 'Italiano');

-- 9. Insere os LEITORES
INSERT INTO tb_leitor (id, nome, cpf, email) VALUES 
(1,'Ana Beatriz Silva','123.456.789-00','ana.bea@gmail.com'),
(2,'Carlos Eduardo Souza','234.567.890-11','cadu.souza@hotmail.com'),
(3,'Fernanda Oliveira','345.678.901-22','nanda.oli@yahoo.com.br'),
(4,'Gabriel Santos','456.789.012-33','gabriel.santos@gmail.com'),
(5,'Juliana Lima','567.890.123-44','ju.lima@outlook.com'),
(6,'Lucas Pereira','678.901.234-55','lucas.dev@techmail.com'),
(7,'Mariana Costa','789.012.345-66','mari.costa@gmail.com'),
(8,'Paulo Roberto Alves','890.123.456-77','paulo.beto@uol.com.br'),
(9,'Renata Martins','901.234.567-88','renata.m@bol.com.br'),
(10,'Thiago Ferreira','012.345.678-99','thiago.ferreira@gmail.com'),
(21,'Greg Black','123.456.789-15','greg@gmail.com');

-- 10. Insere os LIVROS
INSERT INTO tb_livro (titulo, ano, disponivel, autor_id) VALUES
('O Alienista', 1882, 1, 2), 
('Memórias Póstumas de Brás Cubas', 1881, 1, 2), 
('Capitães de Areia', 1937, 1, 8), 
('Grande Sertão: Veredas', 1956, 1, 9), 
('A Hora da Estrela', 1977, 1, 3), 
('Dom Quixote', 1605, 1, 10), 
('Frankenstein', 1818, 1, 11), 
('Crime e Castigo', 1866, 1, 12), 
('1984', 1949, 1, 13), 
('A Divina Comédia', 1321, 1, 14); 

-- 11. Reativa a segurança
SET FOREIGN_KEY_CHECKS = 1;