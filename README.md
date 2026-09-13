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
- MySQL
- Maven
- Docker
- Groq
- Git e GitHub

## 🤖 Inteligência Artificial

A integração com o modelo de linguagem é realizada utilizando o **Spring AI**, com a API compatível com OpenAI disponibilizada pela **Groq**.

O modelo utilizado atualmente é:

```text
openai/gpt-oss-120b
```

A chave de API é configurada por meio da variável de ambiente `GROQ_API_KEY` e não é armazenada no repositório.

## 📌 Status

**Em desenvolvimento.**

Neste momento, o projeto possui a estrutura inicial da aplicação e a integração básica com o modelo de linguagem já foi validada.

As próximas etapas envolvem a implementação das transações financeiras, persistência, ferramentas de Tool Calling e posteriormente o fluxo de interação por áudio.

## 📚 Referência

Projeto desenvolvido a partir do desafio proposto pela **Digital Innovation One (DIO)** no Bootcamp Itaú - Java com Inteligência Artificial.

Repositório de referência:

[dio-spring-boot-learning-track](https://github.com/digitalinnovationone/dio-spring-boot-learning-track)

---

**Projeto desenvolvido por Kelwin Ribeiro Feitosa.**