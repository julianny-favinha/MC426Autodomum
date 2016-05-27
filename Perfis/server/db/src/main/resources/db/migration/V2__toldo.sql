CREATE TABLE toldo(
    id serial PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE historico_toldo(
    id serial PRIMARY KEY,
    fechado boolean,
    data timestamp,
    toldo_id int REFERENCES toldo(id)
);

INSERT INTO toldo (id, name) VALUES(1, 'VARAL');
INSERT INTO toldo (id, name) VALUES(2, 'JARDIM');