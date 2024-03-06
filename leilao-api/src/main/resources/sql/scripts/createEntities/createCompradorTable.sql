CREATE TABLE comprador (
    empresa INTEGER,
    leilao INTEGER
);

ALTER TABLE comprador ADD CONSTRAINT empresa_comp_fk FOREIGN KEY (empresa) REFERENCES empresa(id);

ALTER TABLE comprador ADD CONSTRAINT leilao_comp_fk FOREIGN KEY (leilao) REFERENCES leilao(id);

ALTER TABLE comprador ADD CONSTRAINT comprador_pk PRIMARY KEY (empresa, leilao);