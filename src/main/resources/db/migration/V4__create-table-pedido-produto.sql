CREATE TABLE Pedido_Produto (
                                pedido_id INT NOT NULL,
                                produto_id INT NOT NULL,
                                quantidade INT NOT NULL,
                                preco_unitario DECIMAL(10,2) NOT NULL,
                                PRIMARY KEY (pedido_id, produto_id),
                                FOREIGN KEY (pedido_id) REFERENCES Pedido(id),
                                FOREIGN KEY (produto_id) REFERENCES Produto(id)
);
