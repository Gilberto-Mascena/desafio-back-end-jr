[Português Brasileiro](https://github.com/Gilberto-Mascena/desafio-back-end-jr/blob/main/README-pt_br.md) | [English](https://github.com/Gilberto-Mascena/desafio-back-end-jr/blob/main/README.md)

[![Status de Manutenção](https://img.shields.io/badge/Maintained-Yes-brightgreen?style=for-the-badge)](https://GitHub.com/Gilberto-Mascena/desafio-back-end-jr)
[![GitHub Actions Status](https://img.shields.io/github/actions/workflow/status/Gilberto-Mascena/desafio-back-end-jr/build.yml?style=for-the-badge)](https://github.com/Gilberto-Mascena/desafio-back-end-jr/actions)
[![Licença](https://img.shields.io/github/license/Gilberto-Mascena/desafio-back-end-jr?style=for-the-badge)](https://github.com/Gilberto-Mascena/desafio-back-end-jr/blob/main/LICENSE.md)
[![Estrelas no GitHub](https://img.shields.io/github/stars/Gilberto-Mascena/desafio-back-end-jr?style=for-the-badge)](https://github.com/Gilberto-Mascena/desafio-back-end-jr/stargazers)
[![Problemas no GitHub](https://img.shields.io/github/issues/Gilberto-Mascena/desafio-back-end-jr?style=for-the-badge)](https://github.com/Gilberto-Mascena/desafio-back-end-jr/issues)
[![Versão do Repositório](https://img.shields.io/github/v/release/Gilberto-Mascena/desafio-back-end-jr?include_prereleases&style=for-the-badge)](https://github.com/Gilberto-Mascena/desafio-back-end-jr/releases)
![Data de Lançamento](https://img.shields.io/github/release-date/Gilberto-Mascena/desafio-back-end-jr?style=for-the-badge)
![Tamanho do Repositório](https://img.shields.io/github/repo-size/Gilberto-Mascena/desafio-back-end-jr?style=for-the-badge)

# CRUD de Tarefas (TODO List API)

Este projeto implementa uma API RESTful completa para gerenciamento de tarefas (TODOs), seguindo as melhores práticas de
desenvolvimento com Spring Boot. A API permite criar, listar, buscar por ID, atualizar e deletar tarefas, utilizando
DTOs (Data Transfer Objects) para entrada e saída de dados, e um tratamento global de exceções para respostas de erro
padronizadas.

## Tecnologias Utilizadas

* **Java 17+**
* **Spring Boot 3.5.0**
* **Spring Data JPA**
* **Hibernate**
* **H2 Database** (para desenvolvimento e testes - banco de dados em memória)
* **Jakarta Validation (Bean Validation)**
* **Maven** (gerenciador de dependências)

## Funcionalidades da API

A API oferece os seguintes endpoints:

* **`POST /todos`**: Cria uma nova tarefa.
* **`GET /todos`**: Lista todas as tarefas existentes.
* **`GET /todos/{id}`**: Busca uma tarefa específica por ID.
* **`PUT /todos/{id}`**: Atualiza uma tarefa existente por ID.
* **`DELETE /todos/{id}`**: Deleta uma tarefa por ID.

### Estrutura do Projeto

``` plantext
├── changelog.md
├── doc
│   └── imgs
│       ├── crud_api_postman_collection.json
│       └── todo-front-end.png
├── LICENSE.md
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
├── README-pt_br.md
└── src
    ├── main
    │   ├── java
    │   │   └── br
    │   │       └── com
    │   │           └── mascenadev
    │   │               └── crud
    │   │                   ├── config
    │   │                   │   ├── SwaggerConfig.java
    │   │                   │   └── WebConfig.java
    │   │                   ├── controller
    │   │                   │   └── TodoController.java
    │   │                   ├── CrudApplication.java
    │   │                   ├── domain
    │   │                   │   └── Todo.java
    │   │                   ├── dtos
    │   │                   │   ├── TodoRequestDTO.java
    │   │                   │   └── TodoResponseDTO.java
    │   │                   ├── exception
    │   │                   │   ├── ErroResponse.java
    │   │                   │   ├── GlobalExceptionHandler.java
    │   │                   │   └── TodoNaoEncontradoException.java
    │   │                   ├── repository
    │   │                   │   └── TodoRepository.java
    │   │                   └── service
    │   │                       └── TodoService.java
    │   └── resources
    │       ├── application-dev.yml
    │       ├── application-prod.yml
    │       ├── application-test.yml
    │       ├── application.yml
    │       └── META-INF
    │           └── additional-spring-configuration-metadata.json
    └── test
        └── java
            └── br
                └── com
                    └── mascenadev
                        └── crud
                            └── CrudApplicationTests.java

```

## Como Executar o Projeto

### Pré-requisitos

* Java Development Kit (JDK) 17 ou superior instalado.
* Maven instalado.
* (Opcional) Uma IDE como IntelliJ IDEA, Eclipse ou VS Code.

### Passos

1. **Clone o Repositório:**
```bash
    git clone git@github.com:Gilberto-Mascena/desafio-back-end-jr.git
    cd desafio-back-end-jr
```
2. **Instale as Dependências:**
```bash
    mvn clean install
```
   Isso irá baixar todas as dependências necessárias e compilar o projeto.

3. **Execute a Aplicação:**
```bash
    mvn spring-boot:run
```
   A aplicação será iniciada no endereço: http://localhost:8080.

## Banco de Dados H2 (Em Memória)

O projeto utiliza o H2 Database em memória por padrão para facilitar o desenvolvimento. Isso significa que os dados são
perdidos a cada reinício da aplicação.

Você pode acessar o console do H2 em: http://localhost:8080/h2-console

* **JDBC URL:** `jdbc:h2:mem:devdb`
* **User Name:** `sa`
* **Password:** (deixe em branco)

---

- Utilize o Browser ou Postman para testar a aplicação
- Acesse o endpoint: http://localhost:8080/swagger-ui/index.html, para
  conhecer as rotas mapeadas e como usa-las na documentação do Swagger

---
## Front-End da Aplicação
![front-end](/doc/imgs/todo-front-end.png)

---

## Como Usar a API (com Postman)

Para facilitar a interação com a API, você pode importar a `collection.json` fornecida no Postman.

### Importando a Collection no Postman

1. Abra o Postman.
2. Clique em `File` > `Import` (ou o botão `Import` na interface).
3. Selecione a opção `Upload Files` e escolha o arquivo [Collection.json](/doc/imgs/crud_api_postman_collection.json).
4. A collection será importada com todas as requisições prontas para uso.

### Exemplos de Requisições (HTTP)

#### 1. Criar uma Tarefa (POST)

* **URL:** `http://localhost:8080/todos`
* **Método:** `POST`
* **Headers:**
    * `Content-Type: application/json`
* **Body (raw, JSON):**
```json
{
    "titulo": "Aprender Spring Boot",
    "descricao": "Estudar a documentação oficial e praticar com projetos",
    "realizado": false,
    "prioridade": 5
}
```

#### 2. Listar Todas as Tarefas (GET)

* **URL:** `http://localhost:8080/todos`
* **Método:** `GET`

#### 3. Buscar Tarefa por ID (GET)

* **URL:** `http://localhost:8080/todos/{id_da_tarefa}` (ex: `http://localhost:8080/todos/1`)
* **Método:** `GET`

#### 4. Atualizar Tarefa por ID (PUT)

* **URL:** `http://localhost:8080/todos/{id_da_tarefa}` (ex: `http://localhost:8080/todos/1`)
* **Método:** `PUT`
* **Headers:**
    * `Content-Type: application/json`
* **Body (raw, JSON):**
```json
{
    "titulo": "Finalizar CRUD de Tarefas",
    "descricao": "Implementar e testar todos os endpoints da API com DTOs e tratamento de erros.",
    "realizado": true,
    "prioridade": 1
}
```

#### 5. Deletar Tarefa por ID (DELETE)

* **URL:** `http://localhost:8080/todos/{id_da_tarefa}` (ex: `http://localhost:8080/todos/1`)
* **Método:** `DELETE`

## Tratamento de Erros

A API possui um tratamento de erros global que padroniza as respostas em caso de falhas:

* **400 Bad Request**: Erros de validação nos campos da requisição.
```json
{
    "timestamp": "2024-06-11T10:00:00",
    "status": 400,
    "error": "Erro de validação nos campos enviados",
    "details": {
    "titulo": "Título deve ter entre 2 e 100 caracteres"
    }
}
```
* **404 Not Found**: Recurso não encontrado (ex: tarefa com ID inexistente).
```json
{
    "timestamp": "2024-06-11T10:00:00",
    "status": 404,
    "error": "Todo não encontrado",
    "details": "Todo com o ID 123 não foi encontrado"
}
```
* **500 Internal Server Error**: Erro interno inesperado no servidor.
 ```json
{
    "timestamp": "2024-06-11T10:00:00",
    "status": 500,
    "error": "Erro interno no servidor",
    "details": "Ocorreu um erro inesperado. Por favor, tente novamente mais tarde."
}
```

## Contribuição

Sinta-se à vontade para contribuir com melhorias, correções de bugs ou novas funcionalidades.

## *License*

*The* [*MIT License*](LICENSE.md) (*MIT*)

### _Gilberto | Dev 2024_