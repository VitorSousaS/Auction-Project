DROP TABLE IF EXISTS empresa CASCADE;
DROP SEQUENCE IF EXISTS seq_empresa;

CREATE SEQUENCE seq_empresa INCREMENT 1 MINVALUE 1 MAXVALUE 9999999 START 1 CACHE 1;
CREATE TABLE empresa (
    id INTEGER,
    razaoSocial VARCHAR(64),
    cnpj VARCHAR(32),
    logradouro VARCHAR(64),
    municipio VARCHAR(64),
    numero VARCHAR(10),
    complemento VARCHAR(64),
    bairro VARCHAR(64),
    cep VARCHAR(16),
    telefone VARCHAR(32),
    email VARCHAR(254),
    site VARCHAR(254),
    usuario VARCHAR(20),
    senha VARCHAR(128),
    createdAt TIMESTAMP,
    updatedAt TIMESTAMP
);

-----------------------------------------------------------------------------------

ALTER TABLE empresa ADD CONSTRAINT empresa_pk PRIMARY KEY (id);

ALTER TABLE empresa ALTER COLUMN razaoSocial SET NOT NULL;

ALTER TABLE empresa ALTER COLUMN cnpj SET NOT NULL;

ALTER TABLE empresa ALTER COLUMN email SET NOT NULL;

ALTER TABLE empresa ALTER COLUMN usuario SET NOT NULL;

ALTER TABLE empresa ALTER COLUMN senha SET NOT NULL;

ALTER TABLE empresa ALTER COLUMN createdAt SET NOT NULL;

ALTER TABLE empresa ALTER COLUMN updatedAt SET NOT NULL;

ALTER TABLE empresa ADD CONSTRAINT empresa_cnpj_uk UNIQUE (cnpj);

ALTER TABLE empresa ADD CONSTRAINT empresa_usuario_uk UNIQUE (usuario);