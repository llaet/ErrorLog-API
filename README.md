# API Central de Erros (Error Central API)

## API centralizadora de logs de erros para aplicações (An error log centralizer API to applications)

###### Tecnologias / Technologies
**Desenvolvimento / Deployment**
 - Java 1.8
 - Git 2.9.0
 - PostgreSQL 12
 - Swagger 2.9.2
 - Maven project
 - Spring boot 2.3.1

**Teste / test**
 - H2 database
 - JUnit
 
###### Como clonar o projeto / How to clone the project
	git clone https://github.com/llaet/ErrorLog-API
	
###### O que é uma API?
 - [O que é? - pt-br](https://www.redhat.com/pt-br/topics/api/what-are-application-programming-interfaces)
 - [What is? - en](https://www.redhat.com/en/topics/api/what-are-application-programming-interfaces)

###### Desenvolvido como projeto prático do programa de aceleração AceleraDev Java pela Codenation
[Codenation](https://www.codenation.dev/)

###### Verbos Http para requisições na API:
	Swagger doc		| URL	| /swagger-ui.html
	Acces token		| POST	| /oauth/token
	New user		| POST	| /user
	Check e-mail		| GET	| /user/:email
	New error log		| POST	| /log
	Get all logs		| GET	| /log
	Get log by id		| GET	| /log/:id
	Get log by level	| GET	| /log/level/:level
	Get all logs with query	| GET	| /log/:column/:sort-:argument

###### About me:
  - [Linkedin](https://www.linkedin.com/in/lucas-laet-b47452187/)
  - :e-mail: lucas.laetlira@gmail.com

###### Feel free to contribute in this project!
