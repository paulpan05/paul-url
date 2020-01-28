CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS custom_url;

CREATE TABLE user {
    user_id uuid PRIMARY KEY DEFAULT uuid_generate-v1(),
    username TEXT UNIQUE NOT NULL,
    email TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL
};

CREATE TABLE custom_url {
    id serial PRIMARY KEY,
    route TEXT UNIQUE NOT NULL,
    original_url TEXT NOT NULL,
    user_id serial NOT NULL
};