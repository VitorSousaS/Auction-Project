CREATE TABLE leilao (
    id SERIAL,
    codigo INTEGER,
    descricao VARCHAR(60),
    vendedor INTEGER,
    inicioPrevisto TIMESTAMP,
    createdAt TIMESTAMP,
    updatedAt TIMESTAMP
);

ALTER TABLE leilao ADD CONSTRAINT leilao_pk PRIMARY KEY (id);

ALTER TABLE leilao ALTER COLUMN id SET DEFAULT NEXTVAL('leilao_id_seq');

ALTER TABLE leilao ALTER COLUMN descricao SET NOT NULL;

ALTER TABLE leilao ALTER COLUMN vendedor SET NOT NULL;

ALTER TABLE leilao ALTER COLUMN inicioPrevisto SET NOT NULL;

ALTER TABLE leilao ALTER COLUMN createdAt SET NOT NULL;

ALTER TABLE leilao ALTER COLUMN updatedAt SET NOT NULL;