# Autodomum
Projeto de automação residencial desenvolvido na disciplina de Engenharia de Software da Unicamp

# Instruções para Ambiente de Desenvolvimento

O projeto usa maven para controle de dependências e build. Para fazer o build do projeto use o comando:

```mvn clean install```

Para subir o servidor local, basta rodar a main da classe com.autodomum.aplicacao.server.Sever.
Você precisa de um banco de dados postgres para subir a aplicação.
Se configurá-lo localmente, você deve subir o servidor com o parâmetro -Dspring.profiles.active=dev, caso contrário, use o ambiente de staging com o parâmetro -Dspring.profiles.active=staging
Isso subirá um servidor em http://localhost:8080.
Você pode usar uma ide de sua preferência para subir o servidor ou rodar o próprio jar gerado no build do projeto. Se usar uma ide diferente de Eclipse e Intellij não se esqueça de adicionar os arquivos apropriados no gitignore

## Banco de Dados
Neste projeto utilizamos o banco de dados postgres. Se for utilizar o banco local crie um usuário com nome ```postgres``` e senha ```postgres```, depois crie o banco de dados:

```
CREATE DATABASE autodomum;
ALTER DATABASE autodomum OWNER TO postgres
```

Para rodar os scripts de banco de dados, vá para o diretório db e execute

```mvn flyway:migrate```

Esse comando usará por padrão os dados de conexão que estão no arquivo flyway.properties, para rodar os scripts sql em ambiente de staging use o arquivo staging.properties

```mvn flyway:migrate -Dflyway.configFile=staging.properties```

Para passar dados de outro banco, ou crie o seu properties ou execute o seguinte comando

```mvn flyway:migrate -Dflyway.url=... -Dflyway.user=... -Dflyway.password=...```

#Rodando Testes de Integração
Para rodar os testes de integração que sobem um postgres embarcado use o comando

```mvn clean install -Pintegration-test```