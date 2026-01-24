-- Script para limpar o banco de dados e reiniciar os IDs
-- Use com cuidado! Isso apaga TODOS os dados.

SET FOREIGN_KEY_CHECKS = 0; -- Desativa a verificação de chaves estrangeiras

TRUNCATE TABLE emprestimo;  -- Limpa a tabela filha primeiro
TRUNCATE TABLE livro;       -- Limpa livros
TRUNCATE TABLE leitor;      -- Limpa leitores

SET FOREIGN_KEY_CHECKS = 1; -- Reativa a verificação

