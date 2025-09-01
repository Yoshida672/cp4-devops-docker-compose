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

- `docker-compose up -d` → sobe os containers em segundo plano.  
- `docker-compose down` → para e remove os containers criados.  

---

## Instruções de Uso

1. **Clonar o repositório**:
   ```bash
   git clone <URL_DO_REPOSITORIO>
   cd <PASTA_DO_PROJETO>
   ```

2. **Construir e subir os containers:**:
  ```bash
  docker-compose up --build -d
   ```
3. **Verificar se os containers estão rodando:**:
 ```bash
  docker ps
 ```

4. **Acessar o banco de dados MySQL (opcional):**
```bash
docker exec -it <nome_do_container_mysql> mysql -u root -p
 ```

5. **Acessar a aplicação Spring Boot:**
A aplicação estará disponível em: http://localhost:8080

6. **Encerrar os serviços:**
 ```bash
docker-compose down
 ```

## Execução do projeto

### Considerações Finais

- Vamos rever nosso projeto
* ✅ Containerizamos 2 imagens, do banco de dados e da aplicação
* ✅ Sinergia e conexão entre as imagens
* ✅ Criação de um banco de dados funcional
* ✅ CRUD completo
* ✅ Automatização por Docker-Compose
