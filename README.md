# Morais Library

## Descrição:
  O presente sistema deve permitir uma gestão de acervos híbridos. Isso significa que a biblioteca poderá, na mesma ferramenta, ter acesso ao seu acervo físico e digital. Além disso, será possível configurar perfis de acessos dos operadores, a fim de melhorar o controle na execução de atividades específicas na biblioteca, como por exemplo, quem pode alterar políticas de empréstimo, quem pode fazer a circulação do acervo, entre outras. Um ponto marcante das duas soluções será a presença de estatísticas do acervo e da circulação que poderão ser facilmente acessadas dentro da ferramenta, permitindo que a equipe da biblioteca realize ações de incentivo à leitura. </center>

### Tecnologias

Morais Library usa alguams das tecnlogias mais recentes do mercado:

* [**JAVA**] - Linguagem multiplataforma de alto desempenho;
* [**SRPING BOOT**] - Framework para desenvolvimento de aplicações web/coporativas;
* [**MYSQL**] - Banco de dados relacional mantido pela oracle;
* [**REACT**] - Framework para criar UI web com JavaScript;
* [**HEROKU**] - Plataforma em nuvem para manter as aplicações remotamente;

E além de tudo, Morais Library é open-source.

### API

endpoit: https://morais-library.herokuapp.com

Path's funcionando com os verbos HTTP (**GET, POST, PUT E DELETE**):
* /usuarios
* /cursos
* /departamentos
* /livros
* /autores
* /categorias
* /editoras

Para rodar localmente, é necessário fazer configuração do application.yml ou properties, como for de preferência;

    Configurar um username, password e url para o banco a ser usado, como a seguir:
    
        application.properties:
            spring.datasource.url=jdbc:mysql://{url_path_databse}:{port_to_access}/{schema_name}
            spring.datasource.username={user_name}
            spring.datasource.password={passowrd_name}
            
        application.yml:
            spring:
              datasource:
                url: jdbc:mysql://{url_path_databse}:{port_to_access}/{schema_name}
                username: {user_name}
                password: {passowrd_name}
                
---

Maiores detalhes podem ser encontrados na documentação:      [spring-boot-documentação](https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html)

        
    

### INTERFACE 

### TODOS
* API
    - Desenvolver mais testes;
    - Adicionar segurança;
    - Desenvolver uma response para "/";
    - Adicionar mais features;

License
----

MIT


**Free Software!**

[//]: 
 PROJETO UNIVERSITÁRIO DESENVOLVIDO POR IGOR FELIPE E GABRIEL MOREIRA.
 
 GitHub:
 https://github.com/Lacenlot
 https://github.com/igorfelipes 
