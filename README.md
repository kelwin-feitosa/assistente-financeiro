# Assistente Financeiro

API de um assistente financeiro desenvolvido como projeto de conclusão do **Bootcamp Itaú - Java com Inteligência Artificial**, da [Digital Innovation One (DIO)](https://www.dio.me/).

O projeto tem como objetivo aplicar conceitos de **Java, Spring Boot, Spring AI e Inteligência Artificial** na construção de um assistente capaz de interpretar solicitações relacionadas a finanças e executar operações sobre transações financeiras.

## 🎯 Desafio

Este projeto é baseado no desafio de conclusão do bootcamp **Itaú - Java com Inteligência Artificial**, que propõe a evolução de uma API de controle financeiro utilizando recursos de Inteligência Artificial.

A proposta envolve a construção de um fluxo no qual o usuário poderá interagir com o sistema por meio de linguagem natural, permitindo que a IA interprete sua intenção e utilize as funcionalidades disponíveis na aplicação.

Entre as funcionalidades previstas estão:

- Cadastro de transações financeiras;
- Consulta de transações;
- Interpretação de solicitações em linguagem natural;
- Uso de **Tool Calling** para execução de operações da aplicação;
- Conversão de áudio para texto (Speech-to-Text);
- Conversão de texto para áudio (Text-to-Speech).

## 🏗️ Arquitetura

O projeto utiliza uma **arquitetura em camadas**, separando responsabilidades entre apresentação, aplicação, domínio e infraestrutura.

```text
src/main/java/com/kelwin/assistente_financeiro/

├── domain/
│   ├── model/
│   └── repository/
│
├── application/
│   └── service/
│
├── infrastructure/
│   └── ai/
│       └── tools/
│
└── presentation/
    ├── controller/
    ├── dto/
    └── exception/
```

A integração com Inteligência Artificial é tratada como uma camada de infraestrutura, enquanto as operações da aplicação são centralizadas nos serviços da aplicação.

## 🛠️ Tecnologias

- Java 21
- Spring Boot
- Spring AI
- Spring Data JPA
- Jakarta Bean Validation
- MySQL
- Maven
- Docker
- Swagger / OpenAPI
- Groq
- Git e GitHub

## 🤖 Inteligência Artificial

A integração com o modelo de linguagem é realizada utilizando o **Spring AI**, com a API compatível com OpenAI disponibilizada pela **Groq**.

O modelo utilizado atualmente é:

```text
openai/gpt-oss-120b
```

A chave de API é configurada por meio da variável de ambiente `GROQ_API_KEY` e não é armazenada no repositório.

## 📊 Funcionalidades implementadas

Atualmente, a aplicação possui:

- Cadastro de transações financeiras;
- Listagem de transações;
- Consulta de transação por identificador;
- Exclusão de transações;
- Persistência utilizando Spring Data JPA;
- Validação dos dados de entrada;
- Tratamento global de exceções;
- Documentação da API utilizando Swagger/OpenAPI.

## 🚧 Próximas etapas

As próximas etapas do projeto envolvem:

- Integração completa do assistente com o modelo de linguagem;
- Implementação de **Tool Calling**;
- Interpretação de solicitações financeiras em linguagem natural;
- Integração de **Speech-to-Text**;
- Integração de **Text-to-Speech**;
- Evolução dos testes e demais funcionalidades da aplicação.

## 📌 Status

**Em desenvolvimento.**

A estrutura principal da aplicação e o fluxo básico de gerenciamento de transações financeiras já estão implementados. A integração com o modelo de linguagem utilizando Spring AI e Groq também já foi validada.

O projeto está sendo desenvolvido incrementalmente, com a implementação das funcionalidades de IA como próxima etapa.

## 📚 Referência

Projeto desenvolvido a partir do desafio proposto pela **Digital Innovation One (DIO)** no Bootcamp Itaú - Java com Inteligência Artificial.

Repositório de referência:

[dio-spring-boot-learning-track](https://github.com/digitalinnovationone/dio-spring-boot-learning-track)

---

**Projeto desenvolvido por Kelwin Ribeiro Feitosa.**