CREATE TABLE pessoa (
                        id SERIAL NOT NULL PRIMARY KEY,
                        uuid UUID DEFAULT gen_random_uuid(),
                        nome VARCHAR(50) NOT NULL,
                        cpf VARCHAR(14) NOT NULL,
                        cep VARCHAR(9) NOT NULL,
                        rua VARCHAR(100) NOT NULL,
                        complemento VARCHAR(100),
                        numero VARCHAR(20) NOT NULL,
                        email VARCHAR(100) NOT NULL UNIQUE,
                        senha VARCHAR(255) NOT NULL,
                        permissao VARCHAR(50) NOT NULL DEFAULT 'ROLE_USER',
                        ativo BOOLEAN NOT NULL DEFAULT TRUE
);
