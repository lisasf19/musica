# Tutorial para executar e testar a aplicação

Essa é uma aplicação Java REST (WEB SERVICE) com CRUD de duas entidades (Artista e Album com relacionamento 1:N). 
Pré-requisitos:
*	Java instalado e configurado na máquina; 
Acesse: https://www.devmedia.com.br/preparacao-do-ambiente-para-desenvolvimento-em-java/25188
*	Softwares instalados: PostgreSQL, STS instalados e Postman;


## Download e instalação do PostgreSQL ##

*	Para download do PostgreSQL, acesse: https://www.postgresql.org/download/ e 
*	selecione a alternativa de sistema operacional, arquitetura (32 ou 64 bits) e versão desejada. 

Instalei a versão 10.10 para Windows x86-64: https://www.enterprisedb.com/downloads/postgres-postgresql-downloads

A instalação é bastante intuitiva, mas se necessário veja o seguinte tutorial: http://www.postgresqltutorial.com/install-postgresql/

## Criando e povoando tabelas ##

*	Depois de terminar a instalação, no Menu Iniciar do Windows, digite “pgAdmin” e selecione a interface. 
*	No pgAdmin, vamos criar as tabelas dentro do banco de dados postgres* (já existente).
*	Vá em Servers > PostgreSQL 10 > Databases > postgres > Schemas. 
*	Clique com o botão direito no schema “public” e depois em CREATE Script.
*	Na janela que abrir, apague oque aparecer, cole o script de criação e povoamento das tabelas abaixo e execute:



```javascript

CREATE TABLE public."artista"
(
    id serial NOT NULL,
    nome character varying NOT NULL,
    PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public."artista"
    OWNER to postgres;

CREATE TABLE public."album"
(
    id serial NOT NULL,
    titulo character varying NOT NULL,
    id_artista integer NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT id_artista FOREIGN KEY (id_artista)
        REFERENCES public."artista" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public."album"
    OWNER to postgres;
	
--Povoando a tabela Artista

INSERT INTO public."artista"(nome) VALUES ('Serj Tankian');
INSERT INTO public."artista"(nome) VALUES ('Mike Shinoda');
INSERT INTO public."artista"(nome) VALUES ('Michel Teló');
INSERT INTO public."artista"(nome) VALUES ('Guns N Roses');

--Povoando a tabela Album:

INSERT INTO public."album"(titulo, id_artista)VALUES ('Harakiri', 1);
INSERT INTO public."album"(titulo, id_artista)VALUES ('Black Blooms', 1);
INSERT INTO public."album"(titulo, id_artista)VALUES ('The Rough Dog', 1);
INSERT INTO public."album"(titulo, id_artista)VALUES ('The Rising Tied', 2);
INSERT INTO public."album"(titulo, id_artista)VALUES ('Post Traumatic', 2);
INSERT INTO public."album"(titulo, id_artista)VALUES ('Post Traumatic EP', 2);
INSERT INTO public."album"(titulo, id_artista)VALUES ('Where d You Go', 2);
INSERT INTO public."album"(titulo, id_artista)VALUES ('Bem Sertanejo', 3);
INSERT INTO public."album"(titulo, id_artista)VALUES ('Bem Sertanejo - O Show (Ao vivo)', 3);
INSERT INTO public."album"(titulo, id_artista)VALUES ('Bem Sertanejo - (1ª Temporada) - EP', 3);
INSERT INTO public."album"(titulo, id_artista)VALUES ('Use Your IIIlusion I', 4);
INSERT INTO public."album"(titulo, id_artista)VALUES ('Use Your IIIlusion II', 4);
INSERT INTO public."album"(titulo, id_artista)VALUES ('Greatest Hits', 4);

```

## Instruções para baixar o repositório do projeto e executar localmente ## 

*	Faça download do STS em https://spring.io/tools 
*	Baixe o projeto público do GitHub na workspace do seu STS; 
*	Execute o STS informando a workspace onde você colocou o projeto; 
*	Execute o projeto “BackEndMusica”: botão direito encima do projeto > Run as> Spring Boot App;
 
Com isso, o projeto estará executando localmente e aguardando requisições;

## Instruções para fazer requisições aos serviços ## 

Para testar a aplicação vamos utilizar o software Postman, que pode ser baixado em: https://www.getpostman.com/downloads/

Desenvolvi um pequeno relatório descrevendo os serviços, acesse: https://docs.google.com/document/d/1PfSDoM9HfQ7vi87NrCuEsUeKFWhz-XZGf-2t9-4loaE/edit?usp=sharing

Tutorial de funcionamento do Postman:
https://medium.com/@thi_carva/testando-servi%C3%A7os-web-api-com-postman-874ac81b20a3

