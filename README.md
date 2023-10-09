# Hey Dev Rest! 💬

## Agradecimentos
_Gostaria de expressar minha gratidão a algumas pessoas que desempenharam um papel significativo na realização deste projeto._

_Embora a Hey Dev seja, em essência, um projeto pessoal de currículo, ela possui um valor considerável para mim, pois foi criada com dedicação e, o mais importante, foi um esforço coletivo. Não teria sido possível desenvolver este projeto fullstack sem a colaboração de talentosos desenvolvedores que também são grandes amigos._

_Agradeço especialmente ao [Bruno Cardoso](https://www.github.com/BrunoCardoso02), um excepcional desenvolvedor front-end com habilidades em React, que desempenhou um papel fundamental no desenvolvimento da interface da Hey Dev e na integração com a API REST que desenvolvi. A dedicação e a expertise do Bruno foram essenciais para o sucesso deste projeto._

## Visão geral
_O Hey Dev Rest é a API Rest oficial do aplicativo. Esta API foi criada integralmente utilizando a linguagem Java em conjunto com o framework Spring. Inicialmente, o projeto adotou a arquitetura monolítica, mas à medida que a API crescer em complexidade e detalhamento, estou planejando uma transição gradual para a arquitetura de microserviços._

_Essa abordagem nos permite manter a flexibilidade necessária para atender às necessidades em constante evolução do nosso aplicativo, à medida que continuamos aprimorando e expandindo os recursos oferecidos aos nossos usuários._

## Tecnologias utilizadas
- [Linguagem Java.](https://www.java.com/pt-BR/)
- [Maven.]()
- [Spring Boot.](https://spring.io/projects/spring-boot)
- [Docker.]()
- [MySQL.]()
- [H2 Database.]()

## Dependências do projeto
1. Spring Web.
2. Spring Data JPA.
3. DevTools.
4. Lombok.
5. Spring Boot Tests.
6. H2 Driver.
7. MySQL Driver.
8. Spring Boot Validation.


## Boas práticas usadas
1. Estrutura do projeto organizada.
2. Versionamento de API com o git flow.
3. Uso dos verbos HTTP corretos.
4. Uso dos status codes adequados.
5. Uso de DTO's.
6. Tratamento de erros constantes 
7. Testes unitários e automatizados.
8. Padrões de retorno.
9. SRP e bom acoplamento das classes
10. Injeção de dependência.

## Pré-requisitos
- JDK 17 instalado.
- Docker instalado.
- Docker Compose instalado.
- Porta 8080 disponível para uso.

## Instalação e uso (Docker)
_Utilizando o Docker a API irá se conectar com o banco MySQL._

Basta entrar na raiz do projeto e rodar o seguinte comando no terminal:

<small>Rodar a API em background</small>
```bash
docker-compose up -d
```
<small>Rodar a API com os logs no terminal</small>
```bash
docker-compose up
```

## Instalação e uso (Local)
_Rodando localmente a API irá se conectar com o banco em memória H2._

Entre na raiz do projeto e abra o terminal.

<small>Empacotar o projeto em um `.jar` se quiser rodar o projeto com a JVM</small>
````bash
.\mvnw clean package
````

<small>Compilar o projeto usando o maven</small>
````bash
.\mvnw clean compile
````


<small>Rodar o projeto spring usando o maven</small>
````bash
.\mvnw spring-boot:run
````

<small>Rodar o projeto usando a JVM (precisa ter empacotado o projeto em um `.jar` primeiro)</small>

<small>Você precisa ter também o JDK instalado e configurado nas variavies de ambiente.</small>
````bash
java -jar .\target\{nome_do_jar}.jar
````

## Endpoint para Documentação Swagger

Se desejar obter detalhes mais completos sobre os endpoints, incluindo informações sobre os atributos dos objetos em cada requisição, você pode acessar a documentação Swagger.

Basta abrir em seu navegador o seguinte endpoint: `/swagger-ui.html`

<small>Observação: A API deve estar em execução para acessar a documentação Swagger.</small>


## Endpoints para Recursos de Conta de Usuário
Aqui estão os endpoints disponíveis para recursos relacionados à conta de usuário:

1. Registrar uma conta de usuário
   - Método: **POST**
   - Endpoint: `/useraccount/create`
   - Possíveis Status: 201 (Criado), 400 (Requisição Inválida)

   
2. Listar todas as contas **[Função ADMIN]**
   - Método: **GET**
   - Endpoint: `/useraccount/list`
   - Possíveis Status: 200 (Sucesso), 400 (Requisição Inválida)

   
3. Atualizar username
   - Método: **PUT**
   - Endpoint: `/useraccount/update/{accountId}?username={username}`
   - Possíveis Status: 200 (Sucesso), 400 (Requisição Inválida)


4. Detalhes da conta de usuário
   - Método: **GET**
   - Endpoint: `/useraccount/details/{accountId}`
   - Possíveis Status: 200 (Sucesso), 400 (Requisição Inválida)


5. Excluir conta de usuário
   - Método: **DELETE**
   - Endpoint: `/useraccount/delete/{accountId}`
   - Possíveis Status: 204 (Sem Conteúdo), 400 (Requisição Inválida)



## Endpoints para Recursos de Perfil de Usuário
Aqui estão os endpoints disponíveis para recursos relacionados ao perfil de usuário:

1. Detalhes do perfil de usuário por id da conta
   - Método: **GET**
   - Endpoint: `/userprofile/details/{accountId}`
   - Possíveis Status: 200 (Sucesso), 400 (Requisição Inválida)


2. Atualizar o nome social do perfil por id da conta
   - Método: **PUT**
   - Endpoint: `/userprofile/update/{accountId}?socialName={socialName}`
   - Possíveis Status: 200 (Sucesso), 400 (Requisição Inválida)



## Endpoints para Recursos de Imagem de Perfil
Aqui estão os endpoints disponíveis para recursos relacionados a imagem de perfil do usuário:

ps: Ao criar uma conta no endpoint `/useraccount/create` a api já cria um perfil e já atribui uma foto de perfil default a ela, então não existe o recurso de "criar foto de perfil", apenas os de visualização, download e update. Ao remover a foto perfil ela voltará a ser a foto default.

1. Visualizar a foto de perfil
   - Método: **GET**
   - Endpoint: `/userprofile/image/show/{accountId}`
   - Possíveis Status: 200 (Sucesso), 400 (Requisição Inválida)


2. Baixar foto de perfil
   - Método: **GET**
   - Endpoint: `/userprofile/image/download/{accountId}`
   - Possíveis Status: 200 (Sucesso), 400 (Requisição Inválida)


3. Alterar foto de perfil
   - Método: **PATCH**
   - Endpoint: `/userprofile/image/update/{accountId}`
   - Possíveis Status: 200 (Sucesso), 400 (Requisição Inválida)


4. Remover foto de perfil
   - Método: **PATCH**
   - Endpoint: `/userprofile/image/remove/{accountId}`
   - Possíveis Status: 200 (Sucesso), 400 (Requisição Inválida)


## Endpoints para Recursos de Postagem
Aqui estão os endpoints disponíveis para recursos relacionados a postagem:

1. Criar postagem
   - Método: **POST**
   - Endpoint: `/post/create`
   - Possíveis Status: 201 (Criado), 400 (Requisição Inválida)


2. Detalhar dados de uma postagem
   - Método: **GET**
   - Endpoint: `/post/details/{postId}`
   - Possíveis Status: 200 (Sucesso), 400 (Requisição Inválida)


3. Alterar conteúdo de uma postagem
   - Método: **PUT**
   - Endpoint: `/post/update/{postId}`
   - Possíveis Status: 200 (Sucesso), 400 (Requisição Inválida)


4. Deletar uma postagem
   - Método: **DELETE**
   - Endpoint: `/post/delete/{postId}`
   - Possíveis Status: 204 (Sem conteudo), 400 (Requisição Inválida)



## Endpoints para Recursos de Comentário de uma Postagem
Aqui estão os endpoints disponíveis para recursos relacionados ao comentário de uma  postagem:

1. Criar Comentário
   - Método: **POST**
   - Endpoint: `/post/comment/create`
   - Possíveis Status: 201 (Criado), 400 (Requisição Inválida)


2. Editar um comentário
   - Método: **PATCH**
   - Endpoint: `/post/comment/update/{commentId}`
   - Possíveis Status: 200 (Sucesso), 400 (Requisição Inválida)


3. Deletar um comentário
   - Método: **DELETE**
   - Endpoint: `/post/comment/delete/{commentId}`
   - Possíveis Status: 204 (Sem conteudo), 400 (Requisição Inválida)



## Endpoints para Recursos de Curtida de uma Postagem
Aqui estão os endpoints disponíveis para recursos relacionados a curtida de uma postagem:

1. Curtir uma Postagem
   - Método: **POST**
   - Endpoint: `/post/like/create`
   - Possíveis Status: 201 (Criado), 400 (Requisição Inválida)


2. Remover uma Curtida de uma Postagem
   - Método: **DELETE**
   - Endpoint: `/post/like/delete/{likeId}`
   - Possíveis Status: 200 (Sucesso), 400 (Requisição Inválida)



## Endpoints para Recursos de Curtida de um Comentário
Aqui estão os endpoints disponíveis para recursos relacionados a curtida de um comentário:

1. Curtir um Comentário
   - Método: **POST**
   - Endpoint: `/comment/like/create`
   - Possíveis Status: 201 (Criado), 400 (Requisição Inválida)


2. Remover uma Curtida de um Comentário
   - Método: **DELETE**
   - Endpoint: `/comment/like/delete/{likeId}`
   - Possíveis Status: 200 (Sucesso), 400 (Requisição Inválida)
