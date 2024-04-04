# NLW-Unite-Java

### projeto realizado no evento nlw que ocorreu nos dias 01/04 até 04/04

# Requisitos:

### Foi utilizadas as seguintes tecnologias

- IDE Eclipse

- jdk 17

- Spring Boot 3.2.4

- Banco de dados em memoria HSQLDB

- Realizados testes dos end points utilizando Insomnia (Deixei para testes da Api o arquivo JSon do insomnia)

### como realizar o import para testes dos end points:

  Basta realizar o download gratuito do Insomnia , ao realizar a instalação clique em "create" ,em seguida clique em "import" , você pode arrastar e soltar o arquivo ou localizar o diretorio, em seguida clique em "Scan" , vai ser importado a workspace configurada".
  
  

# Sobre o projeto

> O pass.in é uma aplicação de **gestão de participantes em eventos presenciais**.
> 
- A ferramenta permite que o organizador cadastre um evento e abra uma página pública de inscrição.
- Os participantes inscritos podem emitir uma credencial para check-in no dia do evento.
- O sistema fará um scan da credencial do participante para permitir a entrada no evento.

## Requisitos

### Requisitos funcionais

- O organizador deve poder cadastrar um novo evento;
- O organizador deve poder visualizar dados de um evento;
- O organizador deve poder visualizar a lista de participantes;
1. O participante deve poder se inscrever em um evento;
- O participante deve poder visualizar seu crachá de inscrição;
- O participante deve poder realizar check-in no evento;

### Regras de negócio

- O participante só pode se inscrever em um evento uma única vez;
- O participante só pode se inscrever em eventos com vagas disponíveis;
- O participante só pode realizar check-in em um evento uma única vez;

### Requisitos não-funcionais

- O check-in no evento será realizado através de um QRCode;