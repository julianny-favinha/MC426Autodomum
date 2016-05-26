CREATE TABLE Usuario (
	id SERIAL PRIMARY KEY,
	nome TEXT,
	senha TEXT,
	rfid INT,
	email TEXT
);

CREATE TABLE Permissao (
	id SERIAL PRIMARY KEY,
	nome TEXT,
	sistema_externo TEXT
);

CREATE TABLE UsuarioPermissoes (
	id_usuario INT REFERENCES Usuario (id),
	id_permissao INT REFERENCES Permissao (id)
);