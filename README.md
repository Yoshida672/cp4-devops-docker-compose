# CP4 - DevOps and Cloud Computing

## INTEGRANTES
- RM555010 • Gustavo Matias Teixeira
- RM558763 • Eric Issamu de Lima Yoshida

## Explicação do Projeto
- Utilizaremos uma imagem do banco de dados **MySQL** e conectar um **app Spring Boot** com este banco utilizando **Docker Compose**.

## Cenário
- Informações dos Pokémon de um Treinador Pokémon

## Requisitos mínimos e recomendados para rodar a aplicação completa
- **RAM**: 3 GB - Recomendado: 4 GB  
- **CPU**: 1 vCPU - Recomendado: 2 vCPU  
- **Disco**: 20 GB - Recomendado: 30 GB  

## Imagens utilizadas
1. Gradle 8.13: **gradle:8.13-jdk17**  
   - Site: [Gradle8.13](https://hub.docker.com/layers/library/gradle/8.13-jdk17/images/sha256-9f31f23bd02d8273cad77b8c9f3809085fecf42e5182735e4ee5abedabdb340c)  
2. MySQL 8.0: **mysql:8.0**  
   - Site: [MySQL 8.0](https://hub.docker.com/_/mysql)
---

## O que é Docker Compose?
O **Docker Compose** é uma ferramenta que permite **definir e gerenciar múltiplos containers** como uma aplicação única.  

A configuração é feita através de um arquivo `docker-compose.yml`, onde descrevemos os serviços (containers), suas dependências, variáveis de ambiente, volumes e redes.  

Com ele, basta um único comando para subir ou parar todos os serviços:  

- `docker compose up -d --build` → sobe os containers em segundo plano.  
- `docker compose down` → para e remove os containers criados.  

---

## Instruções de Uso

1. **Clonar o repositório**:
   ```bash
   git clone https://github.com/Yoshida672/cp4-devops-docker-compose.git
   cd cp4-devops-docker-compose
   ```

2. **Criar um arquivo .env:**:

- Crie um arquivo .env e edite com `nano .env`, após isso insira suas informações
   ```bash
   MYSQL_ROOT_PASSWORD=${MYSQL_ROOT_PASSWORD}
   MYSQL_DATABASE=${MYSQL_DATABASE}
   MYSQL_USER=${MYSQL_USER}
   MYSQL_PASSWORD=${MYSQL_PASSWORD}
   SPRING_DATASOURCE_URL=jdbc:mysql://mysql-db:3306/db-dimdim
   CONNECTIONSTRINGS=Server=mysql-db;Database=db-dimdim;User=user-dimdim;Password=senha-dimdim;
   ```

3. **Construir e subir os containers:**:
  ```bash
  docker-compose up --build -d
   ```
4. **Verificar se os containers estão rodando:**:
 ```bash
  docker ps
 ```

5. **Acessar o banco de dados MySQL (opcional):**
```bash
docker exec -it mysql-db mysql -uuser-dimdim -p
 ```

6. **Acessar a aplicação Spring Boot:**
A aplicação estará disponível em: http://localhost:8080

7. **Encerrar os serviços:**
 ```bash
docker compose down
 ```

## Execução do projeto

### Postman

**SITE**
- Entre no link e crie uma conta em "Sign Up" ou entre no botão "Workspace" caso já tenha uma para poder testar o CRUD \
[Workspace](https://www.postman.com/product/workspaces/)

- Entre no seu Workspace e clica no + ao lado do Overview

- Coloque o endereço da sua aplicação e da tabela poke_move `http://ip:8080/moves`

**JSON**

- Para realizar os nossos testes, utilizaremos esse json de exemplo

```
{
  "name": "Tackle",
  "description": "Um ataque físico básico em que Bulbasaur investe contra o oponente.",
  "type": "PSYCHIC",
  "category": "PHYSICAL",
  "power": 40.0,
  "accuracy": 90.0,
  "ppMax": 35
}
```

**Verificação no banco**
- Entre no banco de dados e execute `use db-dimdim;` e depois `select * from poke_move;` para verificar a tabela;

**POST** \
<img width="762" height="515" alt="image" src="https://github.com/user-attachments/assets/b4d9b413-35e1-4b5c-8346-2b8e78533064" /> \
<img width="969" height="209" alt="image" src="https://github.com/user-attachments/assets/912edd7f-4a46-4a41-b98e-cc1e600ff688" /> \

**PUT** \
<img width="903" height="502" alt="image" src="https://github.com/user-attachments/assets/5b954493-c19e-4b47-9ae6-245084d63f96" /> \
<img width="951" height="223" alt="image" src="https://github.com/user-attachments/assets/a87576ad-110f-4add-95b9-e8ae681bb737" /> \

**DELETE** \
<img width="498" height="390" alt="image" src="https://github.com/user-attachments/assets/68397da6-1e8f-4241-8f3b-716f27197786" /> \
<img width="266" height="76" alt="image" src="https://github.com/user-attachments/assets/f468a58a-51d0-4c23-85ac-0315007b9790" />

---
## Considerações Finais

- Vamos rever nosso projeto
* ✅ Containerizamos 2 imagens, do banco de dados e da aplicação
* ✅ Sinergia e conexão entre as imagens
* ✅ Criação de um banco de dados funcional
* ✅ CRUD completo
* ✅ Automatização por Docker-Compose
