DROP TABLE IF EXISTS custom_url;

CREATE TABLE custom_url
(
    id           serial PRIMARY KEY,
    route        TEXT UNIQUE NOT NULL,
    original_url TEXT        NOT NULL,
    user_id      serial      NOT NULL
);