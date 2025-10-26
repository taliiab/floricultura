CREATE TYPE status_pedido AS ENUM ('Pendente','Processando','Enviado','Entregue','Cancelado');

CREATE TABLE pedido
(
    id serial not null primary key,
    usuario_id  int not null,
    data_pedido timestamp not null,
    status status_pedido default 'Pendente',
    valor_total decimal(10, 2) not null,
    foreign key (usuario_id) references pessoa(id)
);
