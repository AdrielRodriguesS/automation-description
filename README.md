# Automation Description App

[![NPM](https://img.shields.io/npm/l/react)](https://github.com/AdrielRodriguesS/automation-description/blob/main/LICENCE)

# Sobre o projeto

Sofware para controle de acionamentos elétricos, pneumáticos e instrumentação.
As informações ficam disponíveis para documentação e controle dos itens de elétrica e automação de máquinas e sistemas industriais.

Mesmo sendo com fins estudantis, surgiu da experiência de um problema real.

## Layout da aba das especificações do Projeto
![Aba Principal](https://github.com/AdrielRodriguesS/automation-description/blob/main/assets/Main_Tab.png)

## Layout aba dos Acionamentos Pneumáticos
![Aba Acionamentos Pneumaticos](https://github.com/AdrielRodriguesS/automation-description/blob/main/assets/Pneumatic_tab.png)

## Layout da Aba dos Acinamentos Elétricos
![Aba Acionamentos Elétricos](https://github.com/AdrielRodriguesS/automation-description/blob/main/assets/Motor_tab.png)

# Tecnologias utilizadas
## Back end
- Java
- JDBC
- MySQL
- JavaFX

## Competências
- Java OO
- MVC
- CRUD
- Tratamento de Exceções
- Telas de interface

## Features
- CRUD completo para todos os itens

# Como executar o projeto

## Pré Requisitos
- Eclipse IDE
- Java 8
- MySql

## clonar o repositório
git clone https://github.com/AdrielRodriguesS/automation-description

## Configurações MySql
- Configurar dados de acesso do mySQL conforme arquivo db.properties
- Rodar o arquivo Script_automation-description.sql para popular o banco de dados

## Configurações do Eclipse
- importar os ".jars" da pasta "libs" para as libraries do projeto;
- Incluir em VM arguments (em Run Configurations / Java Application): --module-path "seu local\libs\javafx-sdk-18.0.2\lib" --add-modules=javafx.fxml,javafx.controls

## Recuperar um Item do Banco
- Rodar a classe main como java application
- Incluir no campo Seria Number o "Client1_001" e clicar em Load;
- ENJOY

# Autor

Adriel Rodrigues de Sales

https://www.linkedin.com/in/adriel-sales-70706551/
