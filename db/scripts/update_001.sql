CREATE TABLE if not exists users (
    id SERIAL PRIMARY KEY,
    name varchar unique,
    password varchar
);

CREATE TABLE if not exists engine (
    id SERIAL PRIMARY KEY,
    name varchar
);

CREATE TABLE if not exists driver (
    id SERIAL PRIMARY KEY,
    name varchar
);

CREATE TABLE if not exists brand (
    id SERIAL PRIMARY KEY,
    name varchar
);

CREATE TABLE if not exists car (
  id SERIAL PRIMARY KEY,
    name varchar,
    engine_id int not null references engine(id),
    brand_id int not null references brand(id)
);

CREATE TABLE if not exists history_owner (
  id SERIAL PRIMARY KEY,
    driver_id int not null references driver(id),
    car_id int not null references car(id)
);

CREATE TABLE if not exists advertisement (
  id SERIAL PRIMARY KEY,
  name varchar,
  description varchar,
  car_id int not null references car(id),
  sold boolean,
  created TIMESTAMP,
  user_id int not null references users(id)
);

CREATE TABLE if not exists photo (
  id SERIAL PRIMARY KEY,
  photo BYTEA,
  ad_id int not null references advertisement(id)
);