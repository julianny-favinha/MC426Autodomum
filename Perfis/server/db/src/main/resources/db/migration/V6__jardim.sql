CREATE TABLE estado_chuva(
    id serial PRIMARY KEY,
    name VARCHAR(255)
);

create table historico_jardim (
    id serial PRIMARY KEY,
    temperatura float,
    data timestamp,
    estado_chuva_id int REFERENCES estado_chuva(id)
);

INSERT INTO estado_chuva (id, name) VALUES(1, 'SECO');
INSERT INTO estado_chuva (id, name) VALUES(2, 'CHUVA_FRACA');
INSERT INTO estado_chuva (id, name) VALUES(3, 'CHUVA_FORTE');