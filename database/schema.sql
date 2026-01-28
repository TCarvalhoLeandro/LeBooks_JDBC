-- 1. Garante que estamos no banco certo
USE aprendizadojpa;

-- 2. Desativa a segurança para permitir apagar e recriar tudo sem erro
SET FOREIGN_KEY_CHECKS = 0;

-- 3. Limpa a casa (Apaga as tabelas antigas se existirem)
DROP TABLE IF EXISTS emprestimo;
DROP TABLE IF EXISTS livro;
DROP TABLE IF EXISTS leitor;

-- 4. Cria a tabela LEITOR (Independente)
CREATE TABLE leitor (
  id int NOT NULL AUTO_INCREMENT,
  nome varchar(255) NOT NULL,
  cpf varchar(20) NOT NULL,
  email varchar(100) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY cpf (cpf)
);

-- 5. Cria a tabela LIVRO (Independente)
CREATE TABLE livro (
  id int NOT NULL AUTO_INCREMENT,
  titulo varchar(255) NOT NULL,
  autor varchar(255) NOT NULL,
  ano int NOT NULL,
  disponivel tinyint(1) DEFAULT '1',
  PRIMARY KEY (id)
);

-- 6. Cria a tabela EMPRESTIMO (Depende das outras duas)
CREATE TABLE emprestimo (
  id int NOT NULL AUTO_INCREMENT,
  data_emprestimo date NOT NULL,
  data_devolucao date DEFAULT NULL,
  devolvido tinyint(1) DEFAULT '0',
  id_leitor int NOT NULL,
  id_livro int NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id_leitor) REFERENCES leitor (id),
  FOREIGN KEY (id_livro) REFERENCES livro (id)
);

-- 7. Insere os LEITORES
INSERT INTO leitor (id, nome, cpf, email) VALUES 
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

-- 8. Insere os LIVROS
INSERT INTO livro (id, titulo, autor, ano, disponivel) VALUES 
(1,'Dom Casmurro','Machado de Assis',1899,1),
(2,'Memórias Póstumas de Brás Cubas','Machado de Assis',1881,1),
(3,'O Senhor dos Anéis: A Sociedade do Anel','J.R.R. Tolkien',1954,1),
(4,'1984','George Orwell',1949,1),
(5,'Vidas Secas','Graciliano Ramos',1938,1),
(6,'A Hora da Estrela','Clarice Lispector',1977,1),
(7,'O Pequeno Príncipe','Antoine de Saint-Exupéry',1943,1),
(8,'Capitães da Areia','Jorge Amado',1937,1),
(9,'O Cortiço','Aluísio Azevedo',1890,1),
(10,'Harry Potter e a Pedra Filosofal','J.K. Rowling',1997,1),
(11,'O Juri','John Grishan',1998,1);

-- 9. Insere os EMPRESTIMOS
INSERT INTO emprestimo (id, data_emprestimo, data_devolucao, devolvido, id_leitor, id_livro) VALUES 
(1,'2026-01-23','2026-01-23',1,3,2),
(2,'2026-01-23',NULL,0,2,3),
(3,'2026-01-23','2026-01-30',0,5,1),
(4,'2026-01-23','2026-01-23',1,5,7),
(5,'2026-01-24','2026-01-27',1,6,8);

-- 10. Reativa a segurança
SET FOREIGN_KEY_CHECKS = 1;