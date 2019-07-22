CREATE TABLE game_system(
        system_id SERIAL NOT NULL,
        system_name VARCHAR (120) NOT NULL,
        CONSTRAINT pk_system_id PRIMARY KEY (system_id)
);

CREATE TABLE compilation(
        compilation_id SERIAL NOT NULL,
        compilation_name VARCHAR (120) NOT NULL,
        CONSTRAINT pk_compilation_id PRIMARY KEY (compilation_id)
);

CREATE TABLE series(
        series_id SERIAL NOT NULL,
        series_name VARCHAR (120) NOT NULL,
        CONSTRAINT pk_series_id PRIMARY KEY (series_id)
);

CREATE TABLE game(
        game_id SERIAL NOT NULL,
        game_name VARCHAR (120) NOT NULL,
        game_system_id INT NOT NULL,
        compilation_id INT NOT NULL,
        series_id INT NOT NULL,
        notes VARCHAR (120) NOT NULL,
        CONSTRAINT pk_game_id PRIMARY KEY (game_id),
        CONSTRAINT fk_game_system_id FOREIGN KEY (game_system_id) REFERENCES game_system,
        CONSTRAINT fk_compilation_id FOREIGN KEY (compilation_id) REFERENCES compilation,
        CONSTRAINT fk_series_id FOREIGN KEY (series_id) REFERENCES series
);

CREATE TABLE play_status(
        game_id INT NOT NULL UNIQUE,
        status VARCHAR (120) NOT NULL,
        trophies_total INT DEFAULT(0),
        trophies_earned INT NOT NULL,
        notes VARCHAR (120) NOT NULL,
        CONSTRAINT fk_game_id FOREIGN KEY (game_id) REFERENCES game
);


CREATE TABLE collectors_status(
        collection_id SERIAL NOT NULL,
        game_id INT NOT NULL,
        region VARCHAR (120) NOT NULL,
        physical_digital VARCHAR (120) NOT NULL,
        purchased_new_used VARCHAR (120) NOT NULL,
        has_manual VARCHAR (120) NOT NULL,
        collectors BOOLEAN  DEFAULT FALSE,
        edition_name VARCHAR (120) NOT NULL,
        CONSTRAINT fk_game_id FOREIGN KEY (game_id) REFERENCES game
);

INSERT INTO compilation (compilation_name) VALUES ('N/A');
INSERT INTO series (series_name) VALUES ('N/A');

INSERT INTO game_system (system_name) VALUES ('NES');