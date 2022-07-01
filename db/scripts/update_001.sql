CREATE TABLE if not exists engine (
    id SERIAL PRIMARY KEY,
    name varchar
);

CREATE TABLE if not exists driver (
    id SERIAL PRIMARY KEY,
    name varchar
);

CREATE TABLE if not exists car (
  id SERIAL PRIMARY KEY,
    name varchar,
    engine_id int not null unique references engine(id)
);

CREATE TABLE if not exists history_owner (
  id SERIAL PRIMARY KEY,
    driver_id int not null references driver(id),
    car_id int not null references car(id)
);