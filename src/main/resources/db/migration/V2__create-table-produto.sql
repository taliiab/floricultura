create table produto(
  id serial primary key,
  nome varchar(100) not null,
  descricao varchar(255),
  preco decimal(10,2) not null,
  estoque int not null
);
