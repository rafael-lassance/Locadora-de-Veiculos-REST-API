# Locadora de Veículos REST API

## Sobre o projeto


Este é um projeto de uma API REST de uma locadora de veículos contendo 3 endpoints distintos:
```
/usuarios
/veiculos
/locacoes
```
A API feita em **Java** utilizando **Spring Boot**, possui segurança implementada com **Spring Security**, autenticação por **JWT**, dois tipos de usuários (`ADMIN` e `CLIENTE`), documentação com **Swagger UI** e utiliza banco de dados em memória **H2**.

No endpoint `/usuarios` é possível criar, atualizar e deletar usuários, listar todos os usuários ou buscar por um usuário específico. E analogamente nos endpoints de `/veiculos` e `/locacoes`.


### Regras de Negócio:

Somente os usuários do tipo `CLIENTE` podem criar locações. Cada cliente somente pode atualizar, buscar e deletar a sua própria locação.

Ao receber uma nova locação ou ao atualizar uma locação existente, o sistema deve calcular o valor total da locação com base nas diárias.
Se o cliente realizar a locação com mais de 1 semana o sistema deve conceder um desconto de 10% no valor final da locação.


### Endpoints:
```
Usuários:          http://localhost:8080/usuarios
Veículos:          http://localhost:8080/veiculos
Locações:          http://localhost:8080/locacoes
Swagger UI:        http://localhost:8080/swagger-ui/index.html
Banco de dados H2: http://localhost:8080/h2-console/login.jsp
```
### Modelagem:

Diagrama de Classes:

![Diagrama de Classes](https://raw.githubusercontent.com/rafael-lassance/Locadora-de-Veiculos-REST-API/main/assets/classDiagramLocadoraDeVeiculos.png "Diagrama de Classes: Locadora de Veículos")



### Banco de dados H2:
Para se conectar ao banco de dados H2 durante os testes:
```
endpoint: http://localhost:8080/h2-console/login.jsp
JDBC URL: jdbc:h2:file:./data/locacoes
Username: sa
Password: sa
```
### Tecnologias utilizadas:

- Java 17
- Spring Boot
- Spring Security
- Autenticação JWT (Json Web Token)
- Swagger UI
- Lombok
- Mapstruct
- Spring Data JPA
- Spring Boot Validation
- Maven
- Banco de dados em memória H2


### Autor:
Rafael Lassance de O. A. Martinez

[Linkedin](https://www.linkedin.com/in/rafael-loamartinez/)