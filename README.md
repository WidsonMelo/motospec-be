# MotoSpec Backend API

API REST para gerenciamento de motos com autenticação JWT, desenvolvida com Spring Boot 3 e Java 21.

## 🚀 Tecnologias

- Java 21
- Spring Boot 3.2.3
- Spring Security
- Spring Data JPA
- JWT (JSON Web Token)
- H2 Database
- Swagger/OpenAPI 3
- Lombok
- Maven

## 📋 Pré-requisitos

- JDK 21
- Maven 3.6+

## 🔧 Instalação

1. Clone o repositório
2. Entre no diretório do projeto:
```bash
cd motospec-be
```

3. Execute o projeto:
```bash
./mvnw spring-boot:run
```

Ou no Windows:
```bash
mvnw.cmd spring-boot:run
```

## 🎯 Endpoints

### Autenticação

#### POST /api/auth/register
Registra um novo usuário no sistema.

**Request:**
```json
{
  "username": "novousuario",
  "password": "senha123",
  "role": "USER"
}
```

**Response:**
```json
{
  "id": 3,
  "username": "novousuario",
  "role": "ROLE_USER"
}
```

#### POST /api/auth/login
Realiza login e retorna um access_token JWT.

**Request:**
```json
{
  "username": "admin",
  "password": "admin123"
}
```

**Response:**
```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "tokenType": "Bearer",
  "expiresIn": 86400000
}
```

**Usuários de teste:**
- Username: `admin` / Password: `admin123` (ROLE_ADMIN)
- Username: `user` / Password: `user123` (ROLE_USER)

### Motos (Requer Autenticação)

#### GET /api/motos
Lista todas as motos (paginado).

**Headers:**
```
Authorization: Bearer {access_token}
```

**Query Parameters:**
- `page`: número da página (default: 0)
- `size`: tamanho da página (default: 10)
- `sort`: campo de ordenação (default: id)

**Response:**
```json
{
  "content": [
    {
      "id": 1,
      "marca": "Honda",
      "modelo": "CB 500F",
      "ano": 2023,
      "preco": 35000.00,
      "cor": "Preta",
      "cilindrada": 500,
      "descricao": "Moto naked esportiva, ideal para cidade e estrada",
      "createdAt": "2026-03-13T10:00:00",
      "updatedAt": "2026-03-13T10:00:00"
    }
  ],
  "pageable": {...},
  "totalPages": 1,
  "totalElements": 5
}
```

#### GET /api/motos/{motoId}
Retorna o detalhamento de uma moto específica.

**Headers:**
```
Authorization: Bearer {access_token}
```

**Response:**
```json
{
  "id": 1,
  "marca": "Honda",
  "modelo": "CB 500F",
  "ano": 2023,
  "preco": 35000.00,
  "cor": "Preta",
  "cilindrada": 500,
  "descricao": "Moto naked esportiva, ideal para cidade e estrada",
  "createdAt": "2026-03-13T10:00:00",
  "updatedAt": "2026-03-13T10:00:00"
}
```

## 📚 Documentação Swagger

Após iniciar a aplicação, acesse:
- Swagger UI: http://localhost:8080/swagger-ui.html
- API Docs: http://localhost:8080/api-docs

## 🗄️ Banco de Dados

O projeto usa H2 Database em memória para desenvolvimento.

**Console H2:**
- URL: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:motospecdb`
- Username: `sa`
- Password: (deixe em branco)

## 🔐 Segurança

Todos os endpoints de `/api/motos` são protegidos e requerem autenticação JWT. Para acessar esses recursos:

1. Faça login em `/api/auth/login`
2. Copie o `accessToken` da resposta
3. Adicione o header: `Authorization: Bearer {accessToken}` nas requisições

## 🧪 Testando com cURL

### Registrar novo usuário:
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username":"novousuario","password":"senha123","role":"USER"}'
```

### Login:
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"novousuario","password":"senha123"}'
```

### Listar motos:
```bash
curl -X GET http://localhost:8080/api/motos \
  -H "Authorization: Bearer {seu_token_aqui}"
```

### Buscar moto por ID:
```bash
curl -X GET http://localhost:8080/api/motos/1 \
  -H "Authorization: Bearer {seu_token_aqui}"
```

### Fluxo completo (Registrar → Login → Acessar recursos):
```bash
# 1. Registrar novo usuário
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username":"teste","password":"teste123"}'

# 2. Fazer login e capturar o token
TOKEN=$(curl -s -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"teste","password":"teste123"}' | grep -o '"accessToken":"[^"]*' | cut -d'"' -f4)

# 3. Usar o token para acessar recursos
curl -X GET http://localhost:8080/api/motos \
  -H "Authorization: Bearer $TOKEN"
```

## 📝 Licença

Este projeto é livre para uso educacional e comercial.
