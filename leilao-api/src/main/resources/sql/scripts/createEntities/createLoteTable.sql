CREATE TABLE lote (
    id SERIAL,
    numeroLote INTEGER,
    descricao VARCHAR(60),
    quantidade NUMERIC,
    valorInicial NUMERIC,
    unidade VARCHAR(128),
    leilao INTEGER,
    createdAt TIMESTAMP,
    updatedAt TIMESTAMP
);


ALTER TABLE lote ADD CONSTRAINT lote_pk PRIMARY KEY (id);

ALTER TABLE lote ALTER COLUMN id SET DEFAULT NEXTVAL('lote_id_seq');

ALTER TABLE lote ALTER COLUMN descricao SET NOT NULL;

ALTER TABLE lote ALTER COLUMN quantidade SET NOT NULL;

ALTER TABLE lote ALTER COLUMN unidade SET NOT NULL;

ALTER TABLE lote ALTER COLUMN leilao SET NOT NULL;

ALTER TABLE lote ALTER COLUMN createdAt SET NOT NULL;

ALTER TABLE lote ALTER COLUMN updatedAt SET NOT NULL;

ALTER TABLE lote ADD CONSTRAINT leilao_lote_fk FOREIGN KEY (leilao) REFERENCES leilao(id);