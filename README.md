# Forum Hub API

## Sobre o Projeto

Este aplicativo, desenvolvido com Java Spring 3, simula um fórum de mensagens onde os usuários podem criar tópicos, responder a tópicos e interagir entre si. O sistema é robusto e utiliza um banco de dados MySQL para o armazenamento eficiente das informações. A seguir, uma descrição detalhada de suas funcionalidades e características.

Este projeto foi desenvolvido durante o programa "Oracle Next Education" (Especialização em Back-End), em parceria com a Alura.

## Tecnologias Utilizadas

- **Java** (JDK 21 "21.0.2")
- **Spring Framework**
- **Maven**
- **MySQL**

## Funcionalidades

Nossa API se concentrará especificamente nos tópicos e permitirá aos usuários:

- Criar um novo tópico
- Listar todos os tópicos
- Visualizar um tópico específico
- Atualizar um tópico
- Excluir um tópico

## Objetivo
O objetivo deste projeto é implementar uma API REST com as seguintes capacidades:

- Rotas construídas seguindo as melhores práticas do modelo REST
- Validações conforme as regras de negócio
- Banco de dados relacional para persistência das informações
- Serviço de autenticação e autorização para restringir o acesso aos dados

## Autenticando & Acessando as rotas
  1. Faça uma requisição do tipo **POST** para **/login/cadastrar**.
  2. O corpo da requisição **(JSON)** deve conter "nome", "email", "senha", no qual o email e senha será usado para autenticar-se posteriormente.
     ```json
     {
       "nome": "Nome",
       "email": "email@gmail.com",
       "senha": "senha"
     }
     ```
  4. Após receber a confirmação de cadastro da API (código 201), faça uma requisição do tipo **POST** para **/login**, contendo "email" e "senha".
  5. O retorno da API será um objeto contendo o atributo *"token"*, que é um **JWT** (JSON Web Token) que permitirá autenticar suas requisições na API, liberando assim acesso para outras rotas da API.
     ```
     {
       "token": "your_jwt_token"
     }
     ```
  7. Para autenticar-se ao acessar as rotas mais privadas da API *(ex: /topicos)* é necessário que adicione seu token no **header** da requisição
     *Header:*
     ```
     Authorization: Bearer <your_jwt_token>
     ```
