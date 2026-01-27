# 📚 Library Manager JDBC: De Arquivos .CSV para MySQL (JDBC)

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-000000?style=for-the-badge&logo=mysql&logoColor=white)
![Design Pattern](https://img.shields.io/badge/Pattern-DAO-blue?style=for-the-badge)

---

## 🚀 Sobre o Projeto

O objetivo principal deste projeto foi estudar a evolução da persistência de dados. O projeto nasceu salvando informações em arquivos de texto (`.csv`) e, nesta versão atual, foi refatorado para utilizar um banco de dados relacional **MySQL**, garantindo maior integridade e performance.

A arquitetura foi desenhada utilizando o padrão **DAO (Data Access Object)**, o que permitiu que a lógica de negócio permanecesse a mesma, alterando apenas a camada de conexão com os dados.

---

## 💡 Do Sistema de Arquivos para o Banco de Dados

Durante o desenvolvimento, enfrentei desafios distintos em cada abordagem:

| Característica | (Arquivos .csv) | (MySQL JDBC) |
| :--- | :--- | :--- |
| **Persistência** | Manipulação direta de I/O (File Reader/Writer). | Consultas SQL via JDBC (Connection/PreparedStatement). |
| **Busca de Dados** | Necessário carregar todo o arquivo em memória (Listas). | Filtros otimizados diretamente na query (`WHERE`). |
| **Relacionamentos** | Complexo (IDs manuais e cruzamento de listas). | Nativo (Chaves Estrangeiras entre `Emprestimo` e `Leitor`). |
| **Concorrência** | Risco de corromper o arquivo se aberto 2x. | Gerenciado pelo SGBD (Transações ACID). |
