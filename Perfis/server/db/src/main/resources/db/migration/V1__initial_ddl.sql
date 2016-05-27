CREATE TABLE usuario (
	username VARCHAR(25) PRIMARY KEY,
	nome TEXT,
	senha TEXT,
	rfid INT
);

CREATE TABLE permissao (
	id SERIAL PRIMARY KEY,
	nome TEXT,
	sistema_externo TEXT
);

CREATE TABLE usuario_permissoes (
	username_usuario VARCHAR(25) REFERENCES usuario (username),
	id_permissao INT REFERENCES permissao (id)
);