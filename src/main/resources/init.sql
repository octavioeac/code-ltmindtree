CREATE SCHEMA IF NOT EXISTS dbo;


CREATE TABLE IF NOT EXISTS DogPicture (
    id INT AUTO_INCREMENT PRIMARY KEY,
    url VARCHAR(255),
    favoriteCount INT,
    breed_id INT,
    FOREIGN KEY (breed_id) REFERENCES DogBreed(id)
);

CREATE TABLE IF NOT EXISTS DogBreed (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);