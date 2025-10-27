# 🌸 Sistema de Floricultura

## 📝 Descrição do Sistema

O sistema desenvolvido serve para **gerenciar vendas de uma floricultura**, permitindo que clientes realizem pedidos de produtos disponíveis no estoque e que administradores controlem produtos e pedidos.

### Entidades do Sistema

- **Pessoa** – representa usuários do sistema. Podem ser clientes (realizam pedidos) ou administradores (gerenciam produtos e pedidos). Possui informações como nome, cpf, endereço, email, senha, permissão e status ativo.
- **Produto** – representa as flores ou produtos disponíveis para venda. Possui nome, descrição, preço e quantidade em estoque.
- **Pedido** – representa a compra realizada por um cliente. Contém referência ao usuário que realizou o pedido, data, status do pedido e valor total.
- **Pedido_Produto** – tabela associativa entre pedido e produto. Armazena quantidade de cada produto e preço unitário no momento da compra.
- **Entrega** – registra informações de envio do pedido, incluindo data de entrega, status da entrega e código de rastreio.
- **Pagamento** – registra informações de pagamento de um pedido, incluindo status do pagamento (pendente, pago, cancelado).

---

## 🗃️ Relacionamentos entre Entidades

- **Pessoa 1:N Pedido** – um usuário pode realizar vários pedidos
- **Pedido N:N Produto** – um pedido pode ter vários produtos e um produto pode estar em vários pedidos (tabela associativa `Pedido_Produto`)
- **Pedido 1:1 Entrega** – cada pedido possui uma entrega associada
- **Pedido 1:1 Pagamento** – cada pedido possui um pagamento associado

---

## ⚙️ Funcionalidades do Sistema

- Cadastro e gerenciamento de clientes (Pessoa)
- Cadastro e gerenciamento de produtos
- Realização de pedidos contendo um ou mais produtos
- Atualização automática do estoque ao criar pedidos
- Registro de status de pedido (Pendente, Processando, Enviado, Entregue, Cancelado)
- Registro de status de entrega e rastreio
- Registro de status de pagamento e confirmação de pagamento

---

## 📋 Regras de Negócio

### Pessoa/Cliente
- CPF deve ser válido e único
- Email deve ser único
- Clientes podem realizar pedidos apenas se estiverem ativos

### Produto
- Nome e preço obrigatórios
- Preço e estoque não podem ser negativos

### Pedido
- Deve estar associado a um cliente válido
- Só pode ser criado com pelo menos um produto
- Quantidade de cada produto deve ser positiva
- Ao criar um pedido, estoque é atualizado automaticamente
- Não é permitido adicionar produtos além do estoque disponível

### Pagamento
- Após pagamento, não é permitido alterar o pedido

### Entrega
- Status inicial é “Aguardando”
- Pode ser atualizado para “Em Rota”, “Entregue” ou “Cancelada”

---

## 🔄 Fluxo de Uso

1. O administrador cadastra produtos da floricultura
2. O cliente se cadastra informando nome, CPF, email e endereço
3. O cliente cria um pedido selecionando produtos disponíveis
4. O sistema verifica regras de negócio (estoque, quantidade, valor total)
5. O pagamento do pedido é registrado
6. O sistema atualiza o status de entrega e estoque automaticamente
7. O pedido é concluído quando o pagamento é confirmado e a entrega realizada

---

## 🗂️ Modelos 

### Diagrama UML
<img width="600" alt="floricultura@localhost" src="https://github.com/user-attachments/assets/87d1aca9-7029-4e85-8c51-b732ededb1eb" />

### Diagram ER
<img width="700" alt="image" src="https://github.com/user-attachments/assets/08e8de07-e6ec-4ca0-bda9-7b25175d8663" />



## 📄 Documentação da API

- 🔗 **URL do Swagger UI:** `http://localhost:8081/Floricultura/swagger-ui.html`
- **Baixar o YAML da API:**
```bash
curl http://localhost:8081/floricultura/v3/api-docs -o openapi.yml
```
- 🔗 **Coleção de Endpoints:** `https://github.com/taliiab/floricultura/blob/main/Insomnia_2025-10-27.yaml`

## 👩‍💻 Dev
Nome: Talia Bosi

Disciplina: Programação Orientada a Objetos Web 2
