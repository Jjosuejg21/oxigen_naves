CREATE TABLE naveEspacial (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           nombre VARCHAR(255) NOT NULL,
                           serie VARCHAR(255),
                           pelicula VARCHAR(255)
);

INSERT INTO naveEspacial (nombre, serie, pelicula) VALUES ('Estrella de la muerte', 'Star Wars', 'Star Wars');
INSERT INTO naveEspacial (nombre, serie, pelicula) VALUES ('USS Enterprise', 'Star Trek', 'Star Trek');
INSERT INTO naveEspacial (nombre, serie, pelicula) VALUES ('Millennium Falcon', 'Star Wars', 'Star Wars');
INSERT INTO naveEspacial (nombre, serie, pelicula) VALUES ('Galactica', 'Battlestar Galactica', 'Battlestar Galactica');
INSERT INTO naveEspacial (nombre, serie, pelicula) VALUES ('Serenity', 'Firefly', 'Firefly');
