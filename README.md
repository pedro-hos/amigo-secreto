# amigo-secreto

> Obs: Todos os testes foram feitos em uma máquina linux/mac. Acredito que não encontrará dificuldade se rodar no outro sistema operacional!

Sistema para sorteio de Amigo secreto, usando para BackEnd:

* Java 7;
* Hibernate, 
* Spring, 
* Sprint boot (tomcat), 
* Spring Data e 
* Spring MVC 

Para o FrontEnd:

* Bootstrap;
* HTML5
* CSS3
* AngularJS e
* Bower

# Como executar?

* Será necessário ter o java 7 instalado;
* Python 2.7 instalado (foi a versão que usei) para poder rodar o servidorzinho python par ao angular;
* Ter baixado na sua máquina o fonte: https://github.com/pedro-hos/amigo-secreto.git
* Ter feito o download do FakeSMTP: https://nilhcem.github.io/FakeSMTP/index.html

###### Legal, feito tudo isso vamos começar a colocar as coisas para rodar, começando pelo nosso servidor:

Primeiro abra o terminal para digitarmos umas coisinhas, depois acesse nosso projeto e em seguida a pasta **server**. Feito isso digite o seguinte comando no console: ```mvn install```. 

Quando ele terminar o processo de build algo o resultado abaixo aparecerá na tela do console:

```
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```
Legal, agora vamos avançar mais uma pasta, para onde foi criado nosso _jar_, para isso acesse a pasta **target**, dentro da pasta digite o comando ```java -jar amigo-secreto-0.0.1-SNAPSHOT.jar```

Se tudo der certo você verá a seguinte mensagem nas ultimas linhas:

```
2015-04-20 17:44:10.628  INFO 21280 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8080 (http)
2015-04-20 17:44:10.632  INFO 21280 --- [           main] br.com.amigo.secreto.SpringBootRun       : Started SpringBootRun in 12.848 seconds (JVM running for 14.492)
```

Pronto olha que maravilha, nosso servidor está no ar.

Agora vamos iniciar nosso servidorzinho python para rodar o angular. Abra uma nova janela do console, vá até nosso projeto e depois na pasta **social/app**. Ali dentro tem um arquivinho ```run.sh``` bastar rodar ele, no mac ```sh run.sh``` e no ubuntu ```.\run.sh```

Se enxergar algo como:

```
Serving HTTP on 0.0.0.0 port 9001 ...

```

Então está beleza, está de pé. Agora só acessar a url: http://localhost:9001/#/ e pronto nosso sistema está no ar. Tanto o server quanto o social.

Agora, só falta subir o fakeSMTP para brincar de enviar email. Acesse a pasta pelo terminal onde fez o dowload e rode:

```
java -jar fakeSMTP-1.13.jar
```

Quando ele terminar de carregar, mude a porta para **2525**, é essa que esta cadastrada.

**obs: Essa é a versão que estou rodando, se estiver com outra versão, só mudar o nome do jar**

Pronto. O sistema esta de pé. Só ir cadastrando as pessoas. Colocando email e nome valido etc. O Sorteio só é relizado quando houver 2 ou mais pessoas no sorteio (dããã).

É isso, qualquer dúvida só mandar para pedro-hos@outlook.com ou pedrospsjc@gmail.com

