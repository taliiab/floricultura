CREATE TYPE status_pagamento AS ENUM ('Pendente','Pago','Cancelado');

CREATE TABLE Pagamento (
                           id serial not null primary key,
                           pedido_id INT NOT NULL,
                           status status_pagamento DEFAULT 'Pendente',
                           FOREIGN KEY (pedido_id) REFERENCES Pedido(id)
);
