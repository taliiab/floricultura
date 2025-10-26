INSERT INTO pessoa (nome, cpf, cep, rua, complemento, numero, email, senha, permissao, ativo)
VALUES
    ('Maria Silva', '123.456.789-00', '97010-123', 'Rua das Flores', 'Apto 101', '45', 'maria.silva@email.com', '$2a$10$7qDg7yRbW4jDKNxzXg9w8O', 'ROLE_USER', TRUE),
    ('João Pereira', '987.654.321-00', '97020-456', 'Av. Central', NULL, '120', 'joao.pereira@email.com', '$2a$10$7qDg7yRbW4jDKNxzXg9w8O', 'ROLE_USER', TRUE),
    ('Ana Costa', '111.222.333-44', '97030-789', 'Rua dos Cravos', 'Casa 2', '88', 'ana.costa@email.com', '$2a$10$7qDg7yRbW4jDKNxzXg9w8O', 'ROLE_USER', TRUE),
    ('Carlos Almeida', '555.666.777-88', '97040-555', 'Rua Primavera', NULL, '15', 'carlos.almeida@email.com', '$2a$10$7qDg7yRbW4jDKNxzXg9w8O', 'ROLE_USER', TRUE),
    ('Fernanda Souza', '999.888.777-66', '97050-999', 'Av. Getúlio Vargas', 'Bloco B', '300', 'fernanda.souza@email.com', '$2a$10$7qDg7yRbW4jDKNxzXg9w8O', 'ROLE_USER', TRUE),
    ('Ricardo Lima', '444.333.222-11', '97060-222', 'Rua das Rosas', 'Fundos', '102', 'ricardo.lima@email.com', '$2a$10$7qDg7yRbW4jDKNxzXg9w8O', 'ROLE_USER', TRUE),
    ('Juliana Rocha', '222.333.444-55', '97070-333', 'Rua das Margaridas', NULL, '56', 'juliana.rocha@email.com', '$2a$10$7qDg7yRbW4jDKNxzXg9w8O', 'ROLE_USER', TRUE),
    ('Pedro Henrique', '666.777.888-99', '97080-444', 'Av. Independência', 'Apto 203', '901', 'pedro.henrique@email.com', '$2a$10$7qDg7yRbW4jDKNxzXg9w8O', 'ROLE_USER', TRUE),
    ('Luciana Santos', '333.222.111-00', '97090-555', 'Rua Dom Bosco', NULL, '12', 'luciana.santos@email.com', '$2a$10$7qDg7yRbW4jDKNxzXg9w8O', 'ROLE_USER', TRUE),
    ('Marcos Oliveira', '888.999.000-11', '97100-666', 'Rua XV de Novembro', 'Casa 1', '77', 'marcos.oliveira@email.com', '$2a$10$7qDg7yRbW4jDKNxzXg9w8O', 'ROLE_USER', TRUE);

-- Tabela Produto
INSERT INTO produto (nome, descricao, preco, estoque)
VALUES
    ('Rosa Vermelha', 'Rosa vermelha em vaso 15cm', 12.50, 50),
    ('Orquídea Branca', 'Orquídea branca elegante em vaso 20cm', 35.00, 30),
    ('Girassol', 'Girassol amarelo em vaso 18cm', 15.00, 40),
    ('Margarida', 'Margarida colorida em vaso 12cm', 8.00, 100),
    ('Vaso Cerâmica Azul', 'Vaso de cerâmica azul decorativo', 20.00, 25),
    ('Jasmim', 'Jasmim perfumado em vaso 15cm', 18.00, 60),
    ('Tulipa Vermelha', 'Tulipa vermelha em vaso 10cm', 10.00, 80),
    ('Lírio Branco', 'Lírio branco em vaso 20cm', 28.00, 35),
    ('Hortênsia Rosa', 'Hortênsia rosa em vaso 18cm', 22.50, 45),
    ('Violeta', 'Violeta roxa em vaso 12cm', 9.50, 90);

-- Tabela Pedido
INSERT INTO pedido (usuario_id, data_pedido, status, valor_total)
VALUES
    (1, '2025-10-01 10:15:00', 'Pendente', 75.00),
    (2, '2025-10-02 14:30:00', 'Processando', 120.50),
    (3, '2025-10-03 09:45:00', 'Enviado', 58.00),
    (4, '2025-10-04 16:20:00', 'Entregue', 200.00),
    (5, '2025-10-05 11:10:00', 'Cancelado', 45.50),
    (6, '2025-10-06 13:00:00', 'Pendente', 89.90),
    (7, '2025-10-07 15:35:00', 'Processando', 150.00),
    (8, '2025-10-08 10:50:00', 'Enviado', 75.50),
    (9, '2025-10-09 12:25:00', 'Entregue', 230.00),
    (10, '2025-10-10 14:00:00', 'Pendente', 60.00);

-- Tabela Pedido_Produto
INSERT INTO pedido_produto (pedido_id, produto_id, quantidade, preco_unitario)
VALUES
    (1, 1, 3, 12.50),
    (1, 5, 1, 20.00),
    (2, 2, 2, 35.00),
    (2, 4, 5, 8.00),
    (3, 3, 4, 15.00),
    (4, 6, 2, 18.00),
    (4, 7, 3, 10.00),
    (5, 1, 1, 12.50),
    (6, 8, 2, 28.00),
    (7, 9, 3, 22.50),
    (8, 10, 5, 9.50),
    (9, 2, 1, 35.00),
    (9, 5, 2, 20.00),
    (10, 3, 2, 15.00);

-- Tabela Entrega
INSERT INTO entrega (pedido_id, data_entrega, status, rastreio)
VALUES
    (1, '2025-10-05 14:00:00', 'Aguardando', 'BR1234567890'),
    (2, '2025-10-06 16:30:00', 'Em Rota', 'BR1234567891'),
    (3, '2025-10-07 10:15:00', 'Entregue', 'BR1234567892'),
    (4, '2025-10-08 13:45:00', 'Entregue', 'BR1234567893'),
    (5, '2025-10-09 09:00:00', 'Cancelada', 'BR1234567894'),
    (6, '2025-10-10 11:20:00', 'Aguardando', 'BR1234567895'),
    (7, '2025-10-11 15:10:00', 'Em Rota', 'BR1234567896'),
    (8, '2025-10-12 12:40:00', 'Entregue', 'BR1234567897'),
    (9, '2025-10-13 14:50:00', 'Entregue', 'BR1234567898'),
    (10, '2025-10-14 10:30:00', 'Aguardando', 'BR1234567899');

-- Tabela Pagamento
INSERT INTO pagamento (pedido_id, status)
VALUES
    (1, 'Pendente'),
    (2, 'Pago'),
    (3, 'Pago'),
    (4, 'Pago'),
    (5, 'Cancelado'),
    (6, 'Pendente'),
    (7, 'Pago'),
    (8, 'Pago'),
    (9, 'Pago'),
    (10, 'Pendente');