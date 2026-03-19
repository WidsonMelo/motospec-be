# 🚀 Guia Rápido - Endpoint de Registro de Usuário

## Endpoint: POST /api/auth/register

### 📝 Descrição
Este endpoint permite criar novos usuários no sistema. Após o registro, você pode usar as credenciais para fazer login e obter um access_token JWT.

---

## 📋 Request Body

```json
{
  "username": "string",    // Obrigatório, 3-50 caracteres, único
  "password": "string",    // Obrigatório, mínimo 6 caracteres
  "role": "string"        // Opcional, padrão: "USER" (pode ser "USER" ou "ADMIN")
}
```

### Validações:
- **username**: obrigatório, entre 3 e 50 caracteres, deve ser único
- **password**: obrigatório, mínimo 6 caracteres
- **role**: opcional, valores aceitos: "USER", "ADMIN" (será convertido para ROLE_USER, ROLE_ADMIN automaticamente)

---

## ✅ Resposta de Sucesso (201 Created)

```json
{
  "id": 3,
  "username": "novousuario",
  "role": "ROLE_USER"
}
```

---

## ❌ Possíveis Erros

### 400 Bad Request - Validação
```json
{
  "status": 400,
  "errors": {
    "username": "Username é obrigatório",
    "password": "Password deve ter no mínimo 6 caracteres"
  },
  "timestamp": "2026-03-13T17:30:00"
}
```

### 400 Bad Request - Username já existe
```json
{
  "status": 400,
  "message": "Username já existe",
  "timestamp": "2026-03-13T17:30:00"
}
```

---

## 🔧 Exemplos de Uso

### cURL - Registrar usuário comum
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "joao",
    "password": "senha123"
  }'
```

### cURL - Registrar usuário admin
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin2",
    "password": "admin456",
    "role": "ADMIN"
  }'
```

### cURL - Fluxo completo: Registrar → Login → Acessar API
```bash
# 1. Registrar novo usuário
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username":"maria","password":"senha123"}'

# 2. Fazer login e capturar o token
TOKEN=$(curl -s -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"maria","password":"senha123"}' \
  | grep -o '"accessToken":"[^"]*' | cut -d'"' -f4)

# 3. Usar o token para acessar recursos protegidos
curl -X GET http://localhost:8080/api/motos \
  -H "Authorization: Bearer $TOKEN"

# 4. Buscar uma moto específica
curl -X GET http://localhost:8080/api/motos/1 \
  -H "Authorization: Bearer $TOKEN"
```

### JavaScript (Fetch API)
```javascript
// Registrar usuário
const registerUser = async (username, password, role = 'USER') => {
  const response = await fetch('http://localhost:8080/api/auth/register', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({ username, password, role })
  });
  
  if (!response.ok) {
    const error = await response.json();
    throw new Error(error.message || 'Erro ao registrar usuário');
  }
  
  return await response.json();
};

// Fazer login
const login = async (username, password) => {
  const response = await fetch('http://localhost:8080/api/auth/login', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({ username, password })
  });
  
  if (!response.ok) {
    throw new Error('Credenciais inválidas');
  }
  
  const data = await response.json();
  return data.accessToken;
};

// Uso
try {
  // Registrar
  const user = await registerUser('pedro', 'senha123');
  console.log('Usuário criado:', user);
  
  // Login
  const token = await login('pedro', 'senha123');
  console.log('Token:', token);
  
  // Acessar API
  const motos = await fetch('http://localhost:8080/api/motos', {
    headers: {
      'Authorization': `Bearer ${token}`
    }
  }).then(res => res.json());
  
  console.log('Motos:', motos);
} catch (error) {
  console.error('Erro:', error.message);
}
```

### Python (requests)
```python
import requests

base_url = "http://localhost:8080"

# Registrar usuário
def register_user(username, password, role="USER"):
    response = requests.post(
        f"{base_url}/api/auth/register",
        json={
            "username": username,
            "password": password,
            "role": role
        }
    )
    return response.json()

# Fazer login
def login(username, password):
    response = requests.post(
        f"{base_url}/api/auth/login",
        json={
            "username": username,
            "password": password
        }
    )
    return response.json()["accessToken"]

# Usar
try:
    # Registrar
    user = register_user("carlos", "senha123")
    print(f"Usuário criado: {user}")
    
    # Login
    token = login("carlos", "senha123")
    print(f"Token: {token}")
    
    # Acessar API
    headers = {"Authorization": f"Bearer {token}"}
    motos = requests.get(f"{base_url}/api/motos", headers=headers)
    print(f"Motos: {motos.json()}")
except Exception as e:
    print(f"Erro: {e}")
```

---

## 🔐 Notas de Segurança

1. **Senha**: A senha é armazenada com hash BCrypt, nunca em texto plano
2. **Role**: Se não especificado, o role padrão é "ROLE_USER"
3. **Username único**: Cada username deve ser único no sistema
4. **Token JWT**: Após o login, o token é válido por 24 horas (configurável)

---

## 🧪 Testando no Swagger

1. Inicie a aplicação: `./mvnw spring-boot:run`
2. Acesse: http://localhost:8080/swagger-ui.html
3. Expanda a seção "Authentication"
4. Clique em "POST /api/auth/register"
5. Clique em "Try it out"
6. Preencha o JSON de exemplo
7. Clique em "Execute"

---

## 📝 Dicas

- Use o script `./test-api.sh` para testar o fluxo completo automaticamente
- Importe as collections do Postman ou Insomnia para testar facilmente
- Verifique o console H2 (http://localhost:8080/h2-console) para ver os usuários criados
- O primeiro usuário admin (`admin`/`admin123`) é criado automaticamente ao iniciar a aplicação
