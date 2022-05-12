INSERT INTO supplier (name, description) VALUES ('Rubber factory', 'Everything from rubber!');
INSERT INTO supplier (name, description) VALUES ('QuackQuack', 'The duck in need!');

-- INSERT INTO category (name, department, description) VALUES ('Best selling ducks', 'Duck', 'Best of the best');
INSERT INTO category (name, department, description) VALUES ('One-colour rubber ducks', 'Duck', 'Simple colored rubber ducks, all-time favorites.');
INSERT INTO category (name, department, description) VALUES ('Star Wars collection', 'Duck', 'Enjoy the magic corporation of QuackOut X Star Wars.');
INSERT INTO category (name, department, description) VALUES ('Rock Stars collection', 'Duck', 'Famous rock stars, like never seen before.');

INSERT INTO product (name, price, currency, description, category_id, supplier_id) VALUES ('Yellow', 3.9, 'USD', 'Best quality, lowest price', 1, 1);
INSERT INTO product (name, price, currency, description, category_id, supplier_id) VALUES ('Blue', 3.9, 'USD', 'Best quality, lowest price', 1, 1);
INSERT INTO product (name, price, currency, description, category_id, supplier_id) VALUES ('Green', 3.9, 'USD', 'Best quality, lowest price', 1, 1);
INSERT INTO product (name, price, currency, description, category_id, supplier_id) VALUES ('Purple', 3.9, 'USD', 'Best quality, lowest price', 1, 1);

INSERT INTO product (name, price, currency, description, category_id, supplier_id) VALUES ('DuckMaul', 5.9, 'USD', 'Darth Maul rubber duck', 2, 1);
INSERT INTO product (name, price, currency, description, category_id, supplier_id) VALUES ('PrincessDuck', 5.9, 'USD', 'Princess Lila rubber duck', 2, 1);
INSERT INTO product (name, price, currency, description, category_id, supplier_id) VALUES ('Chewquackka', 5.9, 'USD', 'Chewbacca rubber duck', 2, 2);

INSERT INTO product (name, price, currency, description, category_id, supplier_id) VALUES ('ElvisDuck', 5.9, 'USD', 'Elvis Presley rubber duck', 3, 2);
INSERT INTO product (name, price, currency, description, category_id, supplier_id) VALUES ('EltonDuck', 5.9, 'USD', 'Elton John rubber duck', 3, 2);

-- pass: alma hashelni
INSERT INTO quack_user (password, first_name, last_name, email, phone_number, street, city, state, zip)
VALUES ('[-19, 90, 24, 95, 90, 61, -126, 54, 72, -110, -97, -76, -85, -26, -66, 60]', 'Donald', 'Duck', 'big_duck@code.cool', '3690667776', 'Quack Street', 'New York', 'New York', 10001);

INSERT INTO user_products (user_id, product_id) VALUES (1, 1);
INSERT INTO user_products (user_id, product_id) VALUES (1, 4);
INSERT INTO user_products (user_id, product_id) VALUES (1, 3);
INSERT INTO user_products (user_id, product_id) VALUES (1, 6);

-- INSERT INTO product_supplier (product_id, supplier_id) VALUES (1,1);
-- INSERT INTO product_supplier (product_id, supplier_id) VALUES (2,1);
-- INSERT INTO product_supplier (product_id, supplier_id) VALUES (3,1);
-- INSERT INTO product_supplier (product_id, supplier_id) VALUES (4,1);
-- INSERT INTO product_supplier (product_id, supplier_id) VALUES (5,1);
-- INSERT INTO product_supplier (product_id, supplier_id) VALUES (6,2);
-- INSERT INTO product_supplier (product_id, supplier_id) VALUES (7,2);
-- INSERT INTO product_supplier (product_id, supplier_id) VALUES (8,2);
-- INSERT INTO product_supplier (product_id, supplier_id) VALUES (9,2);
--
-- INSERT INTO product_category (product_id, category_id) VALUES (1,2);
-- INSERT INTO product_category (product_id, category_id) VALUES (2,2);
-- INSERT INTO product_category (product_id, category_id) VALUES (3,2);
-- INSERT INTO product_category (product_id, category_id) VALUES (4,2);
--
-- INSERT INTO product_category (product_id, category_id) VALUES (5,3);
-- INSERT INTO product_category (product_id, category_id) VALUES (6,3);
-- INSERT INTO product_category (product_id, category_id) VALUES (7,3);
--
-- INSERT INTO product_category (product_id, category_id) VALUES (8,4);
-- INSERT INTO product_category (product_id, category_id) VALUES (9,4);
--
-- INSERT INTO product_category (product_id, category_id) VALUES (1,1);
-- INSERT INTO product_category (product_id, category_id) VALUES (5,1);
-- INSERT INTO product_category (product_id, category_id) VALUES (8,1);

