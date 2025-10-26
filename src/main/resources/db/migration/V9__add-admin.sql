INSERT INTO pessoa (
    nome, cpf, cep, rua, complemento, numero, email, senha, permissao, ativo
) VALUES (
             'Administrador',
             '00000000000',
             '00000-000',
             'Rua Principal',
             'S/N',
             '0',
             'admin@floricultura.com',
             '$2a$10$kjUOazT/jtgqHMryxok/IOMXZU7iIW2Fy/skiLvbuC4IMKF24nwra', --admin
             'ROLE_ADMIN',
             TRUE
         );
