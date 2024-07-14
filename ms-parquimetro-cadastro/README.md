# Projeto de Cadastro de Carro Parquímetro

Este projeto é uma aplicação Spring Boot com um banco de dados PostgreSQL que gerencia o cadastro de carros e clientes referentes há um parquímetro. Utilizamos Docker e Docker Compose para facilitar a configuração e execução do ambiente.

## Pré-requisitos

Antes de começar, certifique-se de ter as seguintes ferramentas instaladas em seu sistema:

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)
- **Java 17**

## Estrutura do Projeto
### Padrão de pacotes

- **controller**
    - **request**
        - `ExampleRequest` (DTO)
    - **response**
        - `ExampleResponse` (DTO)
    - `Exampleontroller`
- **service**
    - **impl**
        - `ExampleerviceImpl` (Implementação)
    - `Exampleervice` (Interface)
- **repository**
    - **entity**
        - `Example`
    - **enums**
    - `ExampleRepository`
- **utils**
    - `ExampleUtils`
    - `ExampleUtils`
- **exception**
    - `ExampleException`
- **config**
    - `ExampleConfiguracao`
    - **exception**
    - `ExceptionHandler`


## Componentes Principais
- **CarroController.java**: Controlador para gerenciar as operações relacionadas aos carros.
- **ClienteController.java**: Controlador para gerenciar as operações relacionadas aos clientes.
- **docker-compose.yml**: Arquivo de configuração do Docker Compose.
- **MsParquimetroCadastroApplication.java**: Classe principal da aplicação Spring Boot.

## Configuração

O arquivo `docker-compose.yml` está configurado para definir dois serviços: o banco de dados PostgreSQL e a aplicação Spring Boot.

### Configuração do PostgreSQL

```yaml
postgresql:
  image: postgres:latest
  restart: always
  container_name: postgresql
  environment:
    POSTGRES_PASSWORD: 123456
    POSTGRES_USER: postgres
    POSTGRES_DB: parquimetro_cadastro_db
  ports:
    - "5432:5432"
  volumes:
    - ./postgres/data:/var/lib/postgresql/data
```
### Clonar o projeto
```
git clone https://github.com/vinnilmg/car-cheap-tc-fase-1.git
cd projeto-parquimetro-cadastro
```

### Buildar imagem docker da Aplicação/Banco De Dados
```
docker-compose build
```

### Iniciar Containers das imagens geradas da Aplicação/Banco De Dados
```
docker-compose up
```


### Derrubar os serviços docker
```
docker-compose down
```