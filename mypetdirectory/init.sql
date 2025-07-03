-- Habilitar extensión para UUIDs
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- Tabla de usuarios
CREATE TABLE users (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(150) UNIQUE NOT NULL,
                       password_hash TEXT NOT NULL,
                       phone_number VARCHAR(30),
                       email_verified BOOLEAN DEFAULT FALSE,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Verificación de email
CREATE TABLE email_verification_tokens (
                                           id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                           user_id UUID REFERENCES users(id) ON DELETE CASCADE,
                                           token VARCHAR(255) NOT NULL,
                                           expires_at TIMESTAMP NOT NULL,
                                           used BOOLEAN DEFAULT FALSE
);

-- Ciudades (ID autoincremental)
CREATE TABLE cities (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(100) NOT NULL UNIQUE
);

-- Localidades (ID autoincremental, con FK a ciudad)
CREATE TABLE localities (
                            id SERIAL PRIMARY KEY,
                            city_id INT REFERENCES cities(id) ON DELETE CASCADE,
                            name VARCHAR(100) NOT NULL,
                            UNIQUE(city_id, name)
);

-- Colores (ID autoincremental)
CREATE TABLE colors (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(50) NOT NULL UNIQUE
);

-- Mascotas (mantiene UUID)
CREATE TABLE pets (
                      id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                      user_id UUID REFERENCES users(id) ON DELETE CASCADE,
                      locality_id INT REFERENCES localities(id) ON DELETE RESTRICT,
                      name_on_tag VARCHAR(100),
                      type VARCHAR(30) NOT NULL CHECK (type IN ('DOG', 'CAT', 'OTHER')),
                      size VARCHAR(30) CHECK (size IN ('SMALL', 'MEDIUM', 'LARGE')),
                      description TEXT,
                      found_date DATE NOT NULL,
                      delivered BOOLEAN DEFAULT FALSE,
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Relación many-to-many entre mascotas y colores
CREATE TABLE pet_colors (
                            pet_id UUID REFERENCES pets(id) ON DELETE CASCADE,
                            color_id INT REFERENCES colors(id) ON DELETE RESTRICT,
                            PRIMARY KEY (pet_id, color_id)
);

-- Medios (fotos Cloudinary / videos YouTube)
CREATE TABLE pet_media (
                           id SERIAL PRIMARY KEY,
                           pet_id UUID REFERENCES pets(id) ON DELETE CASCADE,
                           url TEXT NOT NULL,
                           type VARCHAR(10) NOT NULL CHECK (type IN ('IMAGE', 'VIDEO')),
                           uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


-- Insertar Bogotá (ID autoincremental será 1)
INSERT INTO cities (name) VALUES ('Bogotá');
INSERT INTO cities (id, name) VALUES (DEFAULT, 'Medellín');

-- Asegúrate que Bogotá tenga id = 1, o ajústalo si es distinto
INSERT INTO localities (city_id, name) VALUES
                                           (1, 'Suba'),
                                           (1, 'Chapinero'),
                                           (1, 'Engativá'),
                                           (1, 'Kennedy'),
                                           (1, 'Fontibón'),
                                           (1, 'Teusaquillo'),
                                           (1, 'Usaquén'),
                                           (1, 'San Cristóbal'),
                                           (1, 'Puente Aranda'),
                                           (1, 'Bosa'),
                                           (1, 'Usme'),
                                           (1, 'Santa Fe'),
                                           (1, 'Rafael Uribe Uribe'),
                                           (1, 'Antonio Nariño'),
                                           (1, 'La Candelaria'),
                                           (1, 'Tunjuelito'),
                                           (1, 'Ciudad Bolívar'),
                                           (1, 'Los Mártires'),
                                           (1, 'Barrios Unidos');


INSERT INTO localities (id, name, city_id) VALUES
                                             (DEFAULT, 'La Candelaria (Centro)', 2),
                                             (DEFAULT, 'El Poblado', 2),
                                             (DEFAULT, 'Laureles-Estadio', 2),
                                             (DEFAULT, 'Belén', 2),
                                             (DEFAULT, 'Robledo', 2),
                                             (DEFAULT, 'Castilla', 2),
                                             (DEFAULT, 'Aranjuez', 2),
                                             (DEFAULT, 'Buenos Aires', 2),
                                             (DEFAULT, 'Manrique', 2),
                                             (DEFAULT, 'San Javier', 2),
                                             (DEFAULT, 'Villa Hermosa', 2),
                                             (DEFAULT, 'Doce de Octubre', 2),
                                             (DEFAULT, 'Guayabal', 2),
                                             (DEFAULT, 'Popular', 2),
                                             (DEFAULT, 'Santa Cruz', 2),
                                             (DEFAULT, 'San Cristóbal', 2),
                                             (DEFAULT, 'San Antonio de Prado', 2),
                                             (DEFAULT, 'Altavista', 2),
                                             (DEFAULT, 'Palmitas', 2);

INSERT INTO colors (name) VALUES
                              ('Blanco'),
                              ('Negro'),
                              ('Café'),
                              ('Gris'),
                              ('Dorado'),
                              ('Manchas negras'),
                              ('Manchas cafés'),
                              ('Manchas blancas'),
                              ('Atigrado'),
                              ('Tricolor'),
                              ('Beige'),
                              ('Crema'),
                              ('Amarillo');



