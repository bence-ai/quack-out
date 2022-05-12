DROP TABLE IF EXISTS user_products;
-- DROP TABLE IF EXISTS product_supplier;
-- DROP TABLE IF EXISTS product_category;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS quack_user;
DROP TABLE IF EXISTS "category";
DROP TABLE IF EXISTS supplier;

CREATE TABLE "category" (
    id SERIAL PRIMARY KEY,
    name VARCHAR,
    department VARCHAR,
    description VARCHAR
);

CREATE TABLE supplier (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR,
                          description VARCHAR
);

CREATE TABLE quack_user (
    id SERIAL PRIMARY KEY,
    email VARCHAR,
    password VARCHAR,
    first_name VARCHAR,
    last_name VARCHAR,
    phone_number VARCHAR,
    street VARCHAR,
    city VARCHAR,
    state VARCHAR,
    zip INT
);

-- CREATE TABLE product_category (
--     product_id INT REFERENCES product(id),
--     category_id INT REFERENCES category(id)
-- );



CREATE TABLE product (
     id SERIAL PRIMARY KEY,
     name VARCHAR,
     price float,
     currency VARCHAR,
     description VARCHAR,
     category_id INT REFERENCES category(id),
     supplier_id INT REFERENCES supplier(id)
);

CREATE TABLE user_products (
    user_id INT REFERENCES quack_user(id),
    product_id INT REFERENCES product(id)
);

-- CREATE TABLE product_supplier (
--     product_id INT REFERENCES product(id),
--     supplier_id INT REFERENCES supplier(id)
-- );