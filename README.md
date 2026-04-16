<h1 align="center">Library Management System 📚</h1>

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" alt="Java">
  <img src="https://img.shields.io/badge/MySQL-000000?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL">
  <img src="https://img.shields.io/badge/JDBC-Connect-blue?style=for-the-badge" alt="JDBC">
  <img src="https://img.shields.io/badge/Ubuntu-E95420?style=for-the-badge&logo=ubuntu&logoColor=white" alt="Ubuntu">
  <img src="https://img.shields.io/badge/Pattern-Repository-green?style=for-the-badge" alt="Repository Pattern">
  <img src="https://img.shields.io/badge/Architecture-MVC-purple?style=for-the-badge" alt="MVC">
</p>

Este projeto representa o segundo estágio de evolução do **Library Management System**. Após a implementação inicial utilizando arquivos CSV, esta versão foi refatorada para utilizar o **MySQL** como motor de persistência, utilizando a API **JDBC (Java Database Connectivity)**. O foco aqui foi garantir a integridade referencial, performance em consultas e o gerenciamento de conexões.

## 📈 A Evolução: Do CSV ao SQL

A transição para um banco de dados relacional permitiu resolver limitações críticas do sistema baseado em arquivos:

| Característica | Persistência em CSV | Persistência em MySQL (JDBC) |
| :--- | :--- | :--- |
| **Busca de Dados** | Varredura sequencial em memória. | Consultas indexadas e otimizadas. |
| **Integridade** | Manual e propensa a erros de ID. | Chaves Estrangeiras e Restrições (Constraints). |
| **Concorrência** | Risco de corrupção de arquivo. | Transações seguras (Propriedades ACID). |
| **Escalabilidade** | Limitada pelo tamanho da RAM. | Suporta milhões de registros com eficiência. |

## 🏗️ Arquitetura e Padrões de Projeto

O projeto segue os princípios de **Inversão de Controle (IoC)** e **Injeção de Dependência**, garantindo que a lógica de negócio seja independente da tecnologia de banco de dados.

* **Repository Pattern:** Evolução do antigo padrão DAO, tratando a persistência como coleções de objetos de domínio.
* **Factory Pattern:** Centralização da criação de instâncias de Repositórios, desacoplando a implementação JDBC do restante do sistema.
* **Singleton:** Aplicado na gestão da conexão com o banco (`DBConnection`) para garantir uma única instância do objeto de conexão.
* **Tratamento de Exceções:** Implementação de camadas de captura para `SQLException`, convertendo-as em exceções de domínio mais claras para o utilizador.

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 
* **Persistência:** JDBC (Java Database Connectivity)
* **Banco de Dados:** MySQL
* **Driver:** MySQL Connector
* **Arquitetura:** MVC com Repository Pattern

## 🚀 Como Executar o Projeto

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/TCarvalhoLeandro/library-manager-JDBC.git

2. Configure o Banco de Dados:

    -Importe o script database.sql (disponível na pasta /sql) no seu MySQL Workbench.

    -Configure as credenciais de acesso (URL, User, Password) no arquivo db.properties ou na classe DBConnection.

3. Importe o Driver: Certifique-se de que o .jar do MySQL Connector está adicionado ao Classpath do projeto.

4. Execute: Rode a classe Main.java para iniciar o menu interativo no terminal.

👨‍💻 Autor
Leandro Carvalho

🔗 <a href="https://www.linkedin.com/in/leandrocarvalho1979" target="blank">LinkedIn</a>