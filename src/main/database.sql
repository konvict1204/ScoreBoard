CREATE TABLE players (
    id SERIAL PRIMARY KEY,
    name VARCHAR(32) UNIQUE
);
CREATE TABLE matches (
    id SERIAL PRIMARY KEY,
    player1 INT REFERENCES players (id),
    player2 INT REFERENCES players (id),
    winner INT REFERENCES players (id)
);