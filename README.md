# CP3 - DevOps and Cloud Computing

## INTEGRANTES
- RM555010 • Gustavo Matias Teixeira
- RM558763 • Eric Issamu de Lima Yoshida

## Explicação do Projeto
- Utilizaremos uma imagem do banco de dados oracle express e conectar um app spring com este banco, depois do app conectado usaremos o dbgate para visualizar o banco.

## Requisitos mínimos e recomendados para rodar a aplicação completa
- RAM: 3gb - Recomendado: 4gb
- CPU: 1vCPU - Recomendado: 2vCPU
- Disco: 20gb - Recomendado: 30gb

## Imagens utilizadas
1. Oracle Express: **container-registry.oracle.com/database/express:21.3.0-xe**
- Site: [Oracle-Express](https://container-registry.oracle.com/ords/f?p=113:4:114383781878289:::4:P4_REPOSITORY,AI_REPOSITORY,AI_REPOSITORY_NAME,P4_REPOSITORY_NAME,P4_EULA_ID,P4_BUSINESS_AREA_ID:803,803,Oracle%20Database%20Express%20Edition,Oracle%20Database%20Express%20Edition,1,0&cs=3DdcwyIjLmXFfW0MdcNYp4hHWnNkNX8W9KCbHq1pWQvrAX9AvLkREs963J3z_tlUZR3XDbYGk8NQtQtjud9KmCg)

2. Gradle 8.13: **gradle:8.13-jdk17**
- Site: [Gradle8.13](https://hub.docker.com/layers/library/gradle/8.13-jdk17/images/sha256-9f31f23bd02d8273cad77b8c9f3809085fecf42e5182735e4ee5abedabdb340c)

3. DBGate: **dbgate/dbgate**
- Site: [DBGate](https://dbgate.org/download/)

## Execução do projeto

### 🌐 1º - Entre na sua VM
- Conecte em sua VM pelo terminal via ssh

### 🛠️ 2º - Instale e configure o **Docker** e o **Git** na sua VM
- Verifique se já está instalado com os comandos
```
docker --version
```

```
git --version
```
- Se os comandos anteriores não retornaram nada, então é necessário instalar
#### Instalação **Docker**
- Para instalação do **Docker**, execute os comandos abaixo. Após isso verifique se foi instalado corretamente com ```docker --version```.
```
sudo yum install -y yum-utils -y

```

```
sudo yum-config-manager --add-repo https://download.docker.com/linux/rhel/docker-ce.repo
```

```
sudo yum install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin -y
```

```
sudo systemctl start docker
```
#### Configuração **Docker**
- Execute o seguinte comando para o usuário conseguir rodar os comandos docker sem o "sudo"
```
sudo usermod -aG docker seu_usuario
```
#### Instalação **Git**
- Para instalação do **Git**, execute o comando abaixo. Após isso verifique se foi instalado corretamente com ```git --version```.
```
sudo yum install git -y
```
### ⚙ 3º - Criação dos três containers (a partir de agora os comandos terão nomes pré-definidos para conexão e construção dos containers)
#### Crie sua rede 
```
docker network create net-dimdim
```
#### Container Banco de dados
- Iremos definir as portas e variáveis de ambiente junto com a criação do banco:
```
docker container run -d --name oracle-db --network net-dimdim -p 1521:1521 -p 5500:5500 --ulimit nofile=65535:65535 -e ORACLE_PWD=Oracle123 -v /home/admlnx/oracle_data:/opt/oracle/oradata container-registry.oracle.com/database/express:21.3.0-xe
```
- Leva em torno de 10 minutos para baixar a imagem e construir o banco de dados, então avance após o banco estar completamente criado.
- Verifique com o comando ````docker ps -a```, caso esteja UP (Healthy) na parte de Status, então o foi criado corretamente e pode prosseguir os próximos passos, caso contrário, veja o tópico de configuração do banco.

##### Configuração do Banco
- Caso tenha ficado UP (Unhealty) execute novamente a permissão (era pra estar realizando logo após a criação do container, porém está com algum erro desconhecido):
```
sudo chown 54321:54321 /home/admlnx/oracle_data
```
#### Container do App java
- Clona este projeto e já entra no App java:
```
git clone https://github.com/Gustavo295/crud-with-dockerfile.git && cd crud-with-dockerfile/cp2-java-2tds-main
```
- Buildar o Dockerfile
```
docker build -t app-spring .
```
- Agora rode o container do app (aguarde em torno de 30 segundos para poder rodar corretamente)
```
docker run -d --name app-java --network net-dimdim -p 8080:8080 app-spring
```
#### Container dbgate
- Execute o container para expôr a porta 3000 e criar o dbgate:
```
docker run -d --name dbgate --network net-dimdim -p 3000:3000 dbgate/dbgate
```
### 🔎 4º - Verificando o User do App Java
- Verifique se foi criado um usuário não root na aplicação
```
docker exec -it app-java /bin/bash
```
- Teste o comando ```whoami```

### 🧪 5º - Testando o CRUD

#### DBGate
- Conecte no DBGate através da porta 3000 ```http://ip:3000```
- Crie uma nova conexão e preencha os campos da seguinte forma:

Tipo de Banco: OracleDB
Server: oracle-db (nome do container)
Service Name: XE
User: system
Senha: Oracle123

- No banco, procure pelo usuário "system", depois pesquise na barra de busca pela tabela "POKE_MOVE"

#### Postman

**SITE**
- Entre no link e crie uma conta em "Sign Up" ou entre no botão "Workspace" caso já tenha uma para poder testar o CRUD
[Workspace](https://www.postman.com/product/workspaces/)

- Entre no seu Workspace e clica no + ao lado do Overview

- Coloque o endereço da sua aplicação e da tabela poke_move `http://ip:8080/moves`

#### TESTE
**POST**
  
```
{
  "name": "Tackle",
  "description": "Um ataque físico básico em que Bulbasaur investe contra o oponente.",
  "type": "NORMAL",
  "category": "PHYSICAL",
  "power": 40.0,
  "accuracy": 90.0,
  "ppMax": 35
}
```

![image](https://github.com/user-attachments/assets/acfa0017-3a1a-4aee-a619-c570ee111f3c)

- Clique em Send ou tecle CTRL + Enter

**GET**

- Mude o parâmetro e veja se retorna as informações que inseriu

- `http://ip:8080/moves/1` para pegar por id

**PUT**

- `http://ip:8080/moves/1` para mudar o id

```
{
  "name": "Tackle",
  "description": "Um ataque físico básico em que Bulbasaur investe contra o oponente.",
  "type": "NORMAL",
  "category": "PHYSICAL",
  "power": 40.0,
  "accuracy": 100.0,
  "ppMax": 35
}
```

- Mude o parâmetro pra PUT e veja se mudou a "accuracy" para 100.0 com o GET

**DELETE**

- `http://ip:8080/moves/1` para deletar o id
- Verfique com o GET se foi deletado

#### DBGate

- Após testar o CRUD pelo postman, vamos verificar se está salvando no banco de dados corretamente. Insira estes dados com o Método POST novamente
```
{
  "name": "Tackle",
  "description": "Um ataque físico básico em que Bulbasaur investe contra o oponente.",
  "type": "NORMAL",
  "category": "PHYSICAL",
  "power": 40.0,
  "accuracy": 100.0,
  "ppMax": 35
}
```

- Entre na tabela POKE_MOVE, caso já esteja nela tecle CTRL + R para reiniciar a página

![image](https://github.com/user-attachments/assets/7fba7c4b-7647-405a-944c-95daabb2b4b9)

### 🗑 6º - Removendo as imagens

#### Caso queira deletar as imagens após fazer os testes necessários, execute esses comandos:

```
docker stop dbgate && docker stop app-java && docker stop oracle-db
```

```
docker system prune -a -f --volumes
```

```
cd /home/seu_usuario
```

```
sudo rm -rf oracle_data
```

```
rm -rf crud-with-dockerfile
```

### 🧐7º - Considerações Finais

- Vamos rever nosso projeto
✅ Containerizamos 3 imagens, sendo uma delas personalizada
✅ Sinergia e conexão entre as imagens
✅ Criação de um banco de dados funcional
✅ CRUD completo
✅ Automatização por Dockerfile

- Obrigado por acompanhar até aqui!😉
