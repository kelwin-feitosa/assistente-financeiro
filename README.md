# Assistente Financeiro

API de um assistente financeiro desenvolvido como projeto de conclusão do **Bootcamp Itaú - Java com Inteligência Artificial**, da [Digital Innovation One (DIO)](https://www.dio.me/).

O projeto tem como objetivo aplicar conceitos de **Java, Spring Boot, Spring AI e Inteligência Artificial** na construção de um assistente capaz de interpretar solicitações relacionadas a finanças e executar operações sobre transações financeiras.

## 🎯 Desafio

Este projeto é baseado no desafio de conclusão do bootcamp **Itaú - Java com Inteligência Artificial**, que propõe a evolução de uma API de controle financeiro utilizando recursos de Inteligência Artificial.

A proposta envolve a construção de um fluxo no qual o usuário pode interagir com o sistema por meio de linguagem natural e voz, permitindo que a IA interprete sua intenção e utilize as funcionalidades disponíveis na aplicação.

Entre as funcionalidades implementadas estão:

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

As ferramentas utilizadas pelo modelo de linguagem funcionam como uma ponte entre a IA e as funcionalidades da aplicação, reutilizando os serviços existentes.

O fluxo de interação por voz é composto pelas seguintes etapas:

```text
Áudio
  ↓
Whisper (Speech-to-Text)
  ↓
Texto
  ↓
Google Gemini
  ↓
Tool Calling
  ↓
Serviços da aplicação
  ↓
MySQL
  ↓
Resposta textual
  ↓
Piper (Text-to-Speech)
  ↓
Áudio
```

O processamento de Speech-to-Text e Text-to-Speech é realizado localmente, utilizando **Whisper.cpp** e **Piper**, enquanto a interpretação das solicitações e o Tool Calling são realizados pelo Google Gemini através do Spring AI.

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
- Google Gemini
- Whisper.cpp
- Piper
- JUnit 5
- Mockito
- Git e GitHub
- GitHub Actions

## 🤖 Inteligência Artificial

A integração com o modelo de linguagem é realizada utilizando o **Spring AI** e a API do **Google Gemini**.

O modelo utilizado atualmente é:

```text
gemini-3.6-flash
```

A chave de API é configurada por meio da variável de ambiente `GEMINI_API_KEY` e não é armazenada no repositório.

### Tool Calling

O assistente utiliza **Tool Calling** para permitir que o modelo de linguagem execute operações específicas da aplicação.

Atualmente, estão disponíveis ferramentas para:

- Criar transações financeiras;
- Consultar transações por tipo (`RECEITA` ou `DESPESA`).

As ferramentas recebem parâmetros estruturados e delegam as operações aos serviços da aplicação.

### Speech-to-Text

A conversão de áudio para texto é realizada localmente utilizando **Whisper.cpp**.

O projeto Java executa o `whisper-cli` como um processo externo, mantendo o modelo e os arquivos do Whisper fora do repositório.

Os caminhos utilizados pelo Whisper são configurados por meio de variáveis de ambiente:

```text
WHISPER_CLI_PATH
WHISPER_MODEL_PATH
```

### Text-to-Speech

A conversão da resposta textual da IA para áudio é realizada localmente utilizando **Piper**.

Assim como no Whisper, o projeto Java executa o Piper como um processo externo, enquanto o executável e o modelo de voz permanecem fora do repositório.

Os caminhos utilizados pelo Piper são configurados por meio de variáveis de ambiente:

```text
PIPER_CLI_PATH
PIPER_MODEL_PATH
```

## 📊 Funcionalidades implementadas

A aplicação possui:

- Cadastro de transações financeiras;
- Listagem de transações;
- Consulta de transação por identificador;
- Consulta de transações por tipo;
- Exclusão de transações;
- Persistência utilizando Spring Data JPA;
- Validação dos dados de entrada;
- Tratamento global de exceções;
- Documentação da API utilizando Swagger/OpenAPI;
- Integração com Google Gemini através do Spring AI;
- Interpretação de solicitações financeiras em linguagem natural;
- **Tool Calling** para criação de transações;
- **Tool Calling** para consulta de transações por tipo;
- **Speech-to-Text** utilizando Whisper.cpp;
- **Text-to-Speech** utilizando Piper;
- Fluxo completo de interação por voz;
- Testes unitários com JUnit 5 e Mockito;
- Teste de carregamento do contexto da aplicação;
- Integração contínua com GitHub Actions.

## 🧪 Testes

O projeto possui testes automatizados utilizando **JUnit 5** e **Mockito**.

Os testes contemplam principalmente:

- Serviços da aplicação;
- Ferramentas utilizadas pelo modelo de IA;
- Criação de transações;
- Consulta de transações;
- Validação do comportamento dos serviços;
- Carregamento do contexto da aplicação.

O projeto utiliza um ambiente de testes separado por meio do perfil `test`.

No GitHub Actions, o CI executa os testes utilizando uma instância temporária do **MySQL 8.4**, garantindo que o ambiente de integração possua o mesmo banco utilizado pela aplicação.

## 🔄 Integração Contínua

O projeto utiliza **GitHub Actions** para executar automaticamente os testes a cada push ou pull request para a branch `main`.

O pipeline realiza:

1. Checkout do projeto;
2. Configuração do Java 21;
3. Inicialização de um MySQL 8.4;
4. Configuração do Maven;
5. Execução dos testes automatizados.

## 🎙️ Configuração de voz

Os componentes de voz são executados localmente e não fazem parte do repositório.

Para utilizar o Speech-to-Text, é necessário instalar o **Whisper.cpp** e possuir um modelo compatível.

Para utilizar o Text-to-Speech, é necessário instalar o **Piper** e possuir um modelo de voz compatível.

As configurações devem ser fornecidas por variáveis de ambiente:

```text
WHISPER_CLI_PATH=/caminho/para/whisper-cli
WHISPER_MODEL_PATH=/caminho/para/modelo-do-whisper

PIPER_CLI_PATH=/caminho/para/piper
PIPER_MODEL_PATH=/caminho/para/modelo-do-piper
```

Esses arquivos e caminhos não são versionados no Git.

## 🔎 Limitações conhecidas

Expressões temporais em linguagem natural, como `"ontem"` ou `"semana passada"`, ainda não são convertidas automaticamente para `LocalDateTime`.

Quando nenhuma data é informada, o sistema utiliza automaticamente a data e hora atuais.

## 📌 Status

**Concluído.**

O projeto foi desenvolvido como projeto de conclusão do **Bootcamp Itaú - Java com Inteligência Artificial**, da Digital Innovation One (DIO).

A estrutura principal da aplicação, o gerenciamento de transações e a integração com Inteligência Artificial foram implementados.

A aplicação possui integração funcional com **Spring AI e Google Gemini**, incluindo **Tool Calling** para criação e consulta de transações financeiras.

O fluxo de voz também foi implementado, utilizando **Whisper.cpp** para Speech-to-Text e **Piper** para Text-to-Speech.

Além disso, o projeto possui testes automatizados e pipeline de **CI com GitHub Actions e MySQL**.

As limitações descritas neste README representam funcionalidades que não fazem parte do escopo atual do projeto e que podem ser consideradas em futuras evoluções.

## 📚 Referência

Projeto desenvolvido a partir do desafio proposto pela **Digital Innovation One (DIO)** no Bootcamp Itaú - Java com Inteligência Artificial.

Repositório de referência:

[dio-spring-boot-learning-track](https://github.com/digitalinnovationone/dio-spring-boot-learning-track)

---

**Projeto desenvolvido por Kelwin Ribeiro Feitosa.**