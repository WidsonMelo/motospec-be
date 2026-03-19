# Estrutura do Projeto MotoSpec Backend

## 📁 Estrutura de Diretórios

```
motospec-be/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/motospec/
│   │   │       ├── config/              # Configurações da aplicação
│   │   │       │   ├── DataInitializer.java
│   │   │       │   ├── OpenApiConfig.java
│   │   │       │   └── SecurityConfig.java
│   │   │       ├── controller/          # Controladores REST
│   │   │       │   ├── AuthController.java
│   │   │       │   └── MotoController.java
│   │   │       ├── dto/                 # Data Transfer Objects
│   │   │       │   ├── AuthResponse.java
│   │   │       │   ├── LoginRequest.java
│   │   │       │   └── MotoResponse.java
│   │   │       ├── entity/              # Entidades JPA
│   │   │       │   ├── Moto.java
│   │   │       │   └── User.java
│   │   │       ├── repository/          # Repositórios Spring Data
│   │   │       │   ├── MotoRepository.java
│   │   │       │   └── UserRepository.java
│   │   │       ├── security/            # Componentes de segurança
│   │   │       │   ├── JwtAuthenticationFilter.java
│   │   │       │   └── JwtTokenProvider.java
│   │   │       ├── service/             # Lógica de negócio
│   │   │       │   ├── CustomUserDetailsService.java
│   │   │       │   └── MotoService.java
│   │   │       └── MotospecApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/                            # Testes (a implementar)
├── .gitignore
├── pom.xml
├── README.md
├── test-api.sh                          # Script de teste da API
├── postman-collection.json              # Collection Postman
└── insomnia-collection.json             # Collection Insomnia
```

## 🔧 Componentes Principais

### Controllers (Controladores)

#### AuthController
- **Responsabilidade**: Autenticação de usuários
- **Endpoints**:
  - `POST /api/auth/login`: Login e geração de JWT token

#### MotoController
- **Responsabilidade**: Gerenciamento de motos
- **Endpoints**:
  - `GET /api/motos`: Lista todas as motos (paginado)
  - `GET /api/motos/{id}`: Busca moto por ID
- **Segurança**: Requer JWT token válido

### Entities (Entidades)

#### Moto
Representa uma motocicleta no sistema.
- **Campos**: id, marca, modelo, ano, preco, cor, cilindrada, descricao, createdAt, updatedAt

#### User
Representa um usuário do sistema.
- **Campos**: id, username, password (hash), role

### Security (Segurança)

#### JwtTokenProvider
- Geração de tokens JWT
- Validação de tokens
- Extração de informações do token

#### JwtAuthenticationFilter
- Intercepta requisições HTTP
- Valida tokens JWT
- Configura contexto de segurança do Spring

#### SecurityConfig
- Configuração central do Spring Security
- Define endpoints públicos e protegidos
- Configura autenticação stateless com JWT

### Services (Serviços)

#### MotoService
- Lógica de negócio para motos
- Conversão de entidades para DTOs
- Operações CRUD

#### CustomUserDetailsService
- Carrega dados de usuários do banco
- Integração com Spring Security

### Configuration (Configuração)

#### DataInitializer
- Inicializa dados de exemplo
- Cria usuários padrão
- Popula banco com motos de teste

#### OpenApiConfig
- Configuração do Swagger/OpenAPI
- Definição de esquema de autenticação
- Metadados da API

## 🔐 Fluxo de Autenticação

1. Cliente envia credenciais para `/api/auth/login`
2. `AuthController` valida credenciais usando `AuthenticationManager`
3. Se válido, `JwtTokenProvider` gera um token JWT
4. Token é retornado ao cliente
5. Cliente usa token em requisições subsequentes no header `Authorization: Bearer {token}`
6. `JwtAuthenticationFilter` intercepta requisições e valida o token
7. Se válido, configura o contexto de segurança do Spring
8. Requisição é processada normalmente

## 🗄️ Modelo de Dados

```
User
├── id (Long, PK)
├── username (String, unique)
├── password (String, hash)
└── role (String)

Moto
├── id (Long, PK)
├── marca (String)
├── modelo (String)
├── ano (Integer)
├── preco (BigDecimal)
├── cor (String)
├── cilindrada (Integer)
├── descricao (String)
├── createdAt (LocalDateTime)
└── updatedAt (LocalDateTime)
```

## 🚀 Como Executar

### Desenvolvimento
```bash
./mvnw spring-boot:run
```

### Build
```bash
./mvnw clean package
```

### Executar JAR
```bash
java -jar target/motospec-be-0.0.1-SNAPSHOT.jar
```

## 🧪 Testes

### Script de Teste Bash
```bash
./test-api.sh
```

### Postman
Importe o arquivo `postman-collection.json` no Postman.

### Insomnia
Importe o arquivo `insomnia-collection.json` no Insomnia.

### Swagger UI
Acesse: http://localhost:8080/swagger-ui.html

## 📝 Próximos Passos

- [ ] Implementar testes unitários
- [ ] Implementar testes de integração
- [ ] Adicionar endpoints de CRUD completo para motos
- [ ] Implementar tratamento de exceções global
- [ ] Adicionar logging estruturado
- [ ] Configurar profiles (dev, prod)
- [ ] Adicionar cache para consultas
- [ ] Implementar refresh token
- [ ] Adicionar rate limiting
- [ ] Documentar com Javadoc
