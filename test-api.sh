#!/bin/bash

echo "========================================="
echo "  MotoSpec API - Script de Teste"
echo "========================================="
echo ""

# Definir a URL base
BASE_URL="http://localhost:8080"

# Cores para output
GREEN='\033[0;32m'
BLUE='\033[0;34m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Gerar nome de usuário único
TIMESTAMP=$(date +%s)
USERNAME="testuser${TIMESTAMP}"

echo -e "${BLUE}1. Registrando novo usuário...${NC}"
REGISTER_RESPONSE=$(curl -s -X POST ${BASE_URL}/api/auth/register \
  -H "Content-Type: application/json" \
  -d "{\"username\":\"${USERNAME}\",\"password\":\"senha123\",\"role\":\"USER\"}")

echo "$REGISTER_RESPONSE" | jq .

if echo "$REGISTER_RESPONSE" | grep -q "\"id\""; then
  echo -e "${GREEN}Usuário registrado com sucesso!${NC}"
else
  echo -e "${RED}Erro ao registrar usuário!${NC}"
  echo "Response: $REGISTER_RESPONSE"
fi
echo ""

echo -e "${BLUE}2. Fazendo login com o novo usuário...${NC}"
LOGIN_RESPONSE=$(curl -s -X POST ${BASE_URL}/api/auth/login \
  -H "Content-Type: application/json" \
  -d "{\"username\":\"${USERNAME}\",\"password\":\"senha123\"}")

TOKEN=$(echo $LOGIN_RESPONSE | grep -o '"accessToken":"[^"]*' | cut -d'"' -f4)

if [ -z "$TOKEN" ]; then
  echo -e "${RED}Erro ao obter token!${NC}"
  echo "Response: $LOGIN_RESPONSE"
  exit 1
fi

echo -e "${GREEN}Token obtido com sucesso!${NC}"
echo "Token: ${TOKEN:0:50}..."
echo ""

echo -e "${BLUE}3. Listando todas as motos (página 0, tamanho 5)...${NC}"
curl -s -X GET "${BASE_URL}/api/motos?page=0&size=5" \
  -H "Authorization: Bearer $TOKEN" | jq .

echo ""
echo -e "${BLUE}4. Buscando detalhes da moto com ID 1...${NC}"
curl -s -X GET "${BASE_URL}/api/motos/1" \
  -H "Authorization: Bearer $TOKEN" | jq .

echo ""
echo -e "${GREEN}Testes concluídos!${NC}"
echo -e "${YELLOW}Usuário criado: ${USERNAME}${NC}"
