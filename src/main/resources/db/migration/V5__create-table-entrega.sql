CREATE TYPE status_entrega AS ENUM ('Aguardando','Em Rota','Entregue','Cancelada');


CREATE TABLE Entrega (
                         id serial not null primary key,
                         pedido_id INT NOT NULL,
                         data_entrega timestamp ,
                         status status_entrega DEFAULT 'Aguardando',
                         rastreio VARCHAR(50),
                         FOREIGN KEY (pedido_id) REFERENCES Pedido(id)
);
