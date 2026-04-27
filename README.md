<h1 align="center">📚 LeBooks - Library System</h1>

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" alt="Java">
  <img src="https://img.shields.io/badge/apachemaven-C71A36.svg?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Apache_Maven">
  <img src="https://img.shields.io/badge/JDBC-Connect-blue?style=for-the-badge" alt="JDBC">
  <img src="https://img.shields.io/badge/MySQL-000000?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL">
  <img src="https://img.shields.io/badge/Ubuntu-E95420?style=for-the-badge&logo=ubuntu&logoColor=white" alt="Ubuntu">
  <img src="https://img.shields.io/badge/Pattern-Repository-green?style=for-the-badge" alt="Repository Pattern">
  <img src="https://img.shields.io/badge/Architecture-MVC-purple?style=for-the-badge" alt="MVC">
</p>

Este projeto representa o segundo estágio de evolução do **Library Management System**. Após a implementação inicial utilizando arquivos CSV, esta versão foi refatorada para utilizar o **MySQL** como motor de persistência, utilizando a API **JDBC (Java Database Connectivity)**. O foco aqui foi garantir a integridade referencial, performance em consultas e o gerenciamento de conexões.

## 🚀 Evolução Técnica

A transição para um banco de dados relacional permitiu resolver limitações críticas do sistema baseado em arquivos:

| Característica | Persistência em CSV | Persistência em MySQL (JDBC) |
| :--- | :--- | :--- |
| **Busca de Dados** | Varredura sequencial em memória. | Consultas indexadas e otimizadas. |
| **Integridade** | Manual e propensa a erros de ID. | Chaves Estrangeiras e Restrições (Constraints). |
| **Concorrência** | Risco de corrupção de arquivo. | Transações seguras (Propriedades ACID). |
| **Escalabilidade** | Limitada pelo tamanho da RAM. | Suporta milhões de registros com eficiência. |

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 
* **Persistência:** JDBC (Java Database Connectivity)
* **Banco de Dados:** MySQL
* **Driver:** MySQL Connector
* **Arquitetura:** MVC com Repository Pattern

## 🏗️ Arquitetura e Padrões de Projeto

O projeto segue os princípios de **Inversão de Controle (IoC)** e **Injeção de Dependência**, garantindo que a lógica de negócio seja independente da tecnologia de banco de dados.

* **Repository Pattern:** Evolução do antigo padrão DAO, tratando a persistência como coleções de objetos de domínio.
* **Factory Pattern:** Centralização da criação de instâncias de Repositórios, desacoplando a implementação JDBC do restante do sistema.
* **Singleton:** Aplicado na gestão da conexão com o banco (`DBConnection`) para garantir uma única instância do objeto de conexão.
* **Tratamento de Exceções:** Implementação de camadas de captura para `SQLException`, convertendo-as em exceções de domínio mais claras para o utilizador.



## ⚙️ Como Executar o Projeto

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/TCarvalhoLeandro/library-manager-JDBC.git

2. **Configure o Banco de Dados:**

    Abra o seu banco de dados (MySQL Workbench/DBeaver).

   Execute o script `database/cria_e_popula.sql` para criar o banco e inserir os dados de teste.

    Configure as credenciais de acesso (URL, User, Password) no arquivo db.properties.

4. **Importe o Driver:**

    Certifique-se de que o .jar do MySQL Connector está adicionado ao Classpath do projeto.

5. **Execute:** 

    Rode a classe Main.java para iniciar o menu interativo no terminal.

---

## 💡 Aprendizado

### 🗄️ Migração para Banco de Dados Relacional (MySQL)

Nesta etapa, o armazenamento em arquivos `.csv` foi substituído por um banco de dados relacional, elevando a confiabilidade, segurança e capacidade de consulta da aplicação. Os principais aprendizados técnicos foram:

* **Gerenciamento de Dependências (Maven):**
  * Transição de um projeto Java simples para um projeto Maven.
  * Utilização do arquivo `pom.xml` para gerenciar o **MySQL Connector/J**, eliminando a necessidade de baixar e configurar arquivos `.jar` manualmente no *classpath*.

* **Java Database Connectivity (JDBC):**
  * **Conexão Segura:** Gerenciamento do ciclo de vida de conexões com o banco de dados.
  * **Prevenção contra SQL Injection:** Substituição de concatenação de strings em queries pelo uso de `PreparedStatement`, garantindo a sanitização dos dados de entrada.
  * **Mapeamento Objeto-Relacional Manual:** Uso do `ResultSet` para traduzir os registros retornados pelas tabelas do banco de dados (linhas e colunas) de volta para instâncias das nossas classes de domínio (Objetos Java).

* **Modelagem e Ferramentas de Banco de Dados:**
  * Uso do **MySQL Workbench** para o design do esquema do banco de dados, criação de tabelas (DDL) e testes de consultas (DML) localmente.

* **A Prova Prática da Arquitetura (O Poder do DAO):**
  * A troca da tecnologia de persistência (do CSV para o MySQL) foi facilitada pelo uso prévio das interfaces **DAO**. Criamos novas implementações focadas no banco de dados, enquanto a camada de serviços (`.services`) permaneceu intacta, comprovando o sucesso do baixo acoplamento.
  
---

## 💻 Resultado


---

## 📌 Sugestões
Fique a vontade para entrar em contato:

[![LinkedIn](https://img.shields.io/badge/linkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/leandrocarvalho1979)

---

Leandro Carvalho
