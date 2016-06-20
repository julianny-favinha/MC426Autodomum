CREATE TABLE preferencias (
	id SERIAL PRIMARY KEY,
	username_usuario VARCHAR(25) REFERENCES usuario (username),
	artista TEXT
);