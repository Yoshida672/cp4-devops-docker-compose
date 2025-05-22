# CP3 - DevOps

## INTEGRANTES
- RM555010 • Gustavo Matias Teixeira
- RM558763 • Eric Issamu de Lima Yoshida

## Explicação do Projeto
Utilizaremos uma imagem do banco de dados oracle express e conectar um app spring com este banco, depois do app conectado usaremos o dbgate para visualizar o banco.

## Imagens utilizadas
Oracle Express: **container-registry.oracle.com/database/express:21.3.0-xe**

Site: https://container-registry.oracle.com/ords/f?p=113:4:114383781878289:::4:P4_REPOSITORY,AI_REPOSITORY,AI_REPOSITORY_NAME,P4_REPOSITORY_NAME,P4_EULA_ID,P4_BUSINESS_AREA_ID:803,803,Oracle%20Database%20Express%20Edition,Oracle%20Database%20Express%20Edition,1,0&cs=3DdcwyIjLmXFfW0MdcNYp4hHWnNkNX8W9KCbHq1pWQvrAX9AvLkREs963J3z_tlUZR3XDbYGk8NQtQtjud9KmCg

Gradle 8.13: **gradle:8.13-jdk17**

Site: https://hub.docker.com/layers/library/gradle/8.13-jdk17/images/sha256-9f31f23bd02d8273cad77b8c9f3809085fecf42e5182735e4ee5abedabdb340c

DBGate: **dbgate/dbgate**

Site: https://dbgate.org/download/

## Execução do projeto

### 1º - Entre na sua VM
- Conecte em sua VM pelo terminal via ssh

### 2º - Instale e configure o **Docker** e o **Git** na sua VM
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
```sudo yum install git -y```
### 3º - Criação dos três containers (a partir de agora os comandos terão nomes pré-definidos para conexão e construção dos containers)
#### Crie sua rede 
```
docker network create net-dimdim
```
