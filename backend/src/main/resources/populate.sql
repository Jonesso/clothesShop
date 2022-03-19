# Filling the database to show working prototype
# -- ----------------------------
# -- Records of item_category
# -- ----------------------------
INSERT INTO item_category VALUES (1000000000, 'Tops', 0, '2020-08-09 13:03:26', '2021-03-10 20:15:27');
INSERT INTO item_category VALUES (1000000001, 'Trousers & Shorts', 1, '2021-05-10 20:15:27', '2021-05-11 10:15:27');
INSERT INTO item_category VALUES (1000000002, 'Shoes', 2, '2020-01-14 01:01:09', '2020-04-15 03:01:09');
#
#
# -- ----------------------------
# -- Records of item_info
# -- ----------------------------
INSERT INTO item_info VALUES ('T0001', 0, '2018-03-10 10:37:39', 'CLUB GIV "DAKU" STEALTH WINTER JACKET - MULTIPLE COLORS', 'https://cdn.shopify.com/s/files/1/1748/2773/products/product-image-1135218789_900x.jpg?v=1575763186', 'Winter Jacket', 139.99, 0, 5, '2018-03-10 19:42:02');
INSERT INTO item_info VALUES ('T0002', 0, '2018-03-10 12:12:46', 'CLUB GIV "JULIAN" BOMBER JACKET', 'https://cdn.shopify.com/s/files/1/1748/2773/products/product-image-53176349_900x.jpg?v=1514010350', 'Winter Jacket', 124.99, 1, 0, '2018-03-10 12:12:46');
INSERT INTO item_info VALUES ('T0003', 0, '2018-03-10 06:51:03', 'CLUB GIV "BOW-TIE BEAR" KNIT SWEATER - MULTIPLE COLORS', 'https://cdn.shopify.com/s/files/1/1748/2773/products/product-image-1499441268_900x.jpg?v=1618790611', 'Sweater', 79.99, 0, 100, '2018-03-10 12:04:13');
INSERT INTO item_info VALUES ('T0004', 0, '2018-03-10 10:35:43', 'CLUB GIV "OBLIVION" BOMBER JACKET V2', 'https://cdn.shopify.com/s/files/1/1748/2773/products/product-image-823487686_360x.jpg?v=1551910630', 'Bomber Jacket', 99.99, 0, 27, '2018-03-10 10:35:43');
INSERT INTO item_info VALUES ('B0001', 1, '2018-03-10 12:09:41', 'CLUB GIV "ETERNITY" STRAIGHT LEG JEANS', 'https://cdn.shopify.com/s/files/1/1748/2773/products/product-image-961425885_900x.jpg?v=1559054292', 'Jeans', 84.99, 0, 69, '2018-03-10 12:09:41');
INSERT INTO item_info VALUES ('B0002', 1, '2018-03-10 12:11:51', 'CLUB GIV "DAKU" TACTICAL UTILITY JOGGERS', 'https://cdn.shopify.com/s/files/1/1748/2773/products/product-image-1135567975_360x.jpg?v=1571948142', 'Joggers', 99.99, 0, 48, '2018-03-10 12:11:51');
INSERT INTO item_info VALUES ('B0003', 1, '2018-03-10 06:44:25', 'CLUB GIV "VOLT" TACTICAL UTILITY JOGGERS', 'https://cdn.shopify.com/s/files/1/1748/2773/products/product-image-952510175_360x.jpg?v=1559949883', 'Joggers', 79.99, 0, 96, '2018-03-10 06:44:25');
INSERT INTO item_info VALUES ('B0004', 1, '2018-03-10 10:39:29', 'CLUB GIV "BUSHIDO" CARGO UTILITY JOGGERS', 'https://cdn.shopify.com/s/files/1/1748/2773/products/product-image-956705301_900x.jpg?v=1557478434', 'Joggers', 79.99, 0, 60, '2018-03-10 10:39:32');
INSERT INTO item_info VALUES ('S0001', 2, '2018-03-10 10:40:35', 'CLUB GIV "ROSA" HIGH TOP ATHLETIC TRAINERS', 'https://cdn.shopify.com/s/files/1/1748/2773/products/product-image-972666613_360x.jpg?v=1559854090', 'Trainers', 69.99, 0, 199, '2018-03-10 10:40:35');
INSERT INTO item_info VALUES ('S0002', 2, '2018-03-10 12:08:17', 'CLUB GIV "G-TREKK" TECHNICAL TRAINERS - MULTIPLE COLORS', 'https://cdn.shopify.com/s/files/1/1748/2773/products/product-image-897664213_360x.jpg?v=1552973782', 'Trainers', 89.99, 1, 0, '2018-03-10 12:08:17');
INSERT INTO item_info VALUES ('S0003', 2, '2018-03-10 12:15:05', 'CLUB GIV "MARCO" CHELSEA BOOTS - MULTIPLE COLORS', 'https://cdn.shopify.com/s/files/1/1748/2773/products/product-image-131119331_360x.jpg?v=1514010377', 'Boots', 99.99, 0, 57, '2018-03-10 12:15:10');
INSERT INTO item_info VALUES ('S0004', 2, '2018-03-10 12:16:44', 'CLUB GIV "LE FLAME" SOCKS', 'https://cdn.shopify.com/s/files/1/1748/2773/products/product-image-227644819_900x.jpg?v=1559844471', 'Socks', 14.99, 0, 22, '2018-03-10 12:16:44');
#
#
# -- ----------------------------
# -- Records of users
# -- ----------------------------
INSERT INTO users VALUES (2147483641, 1, 'Pushkina St.', 'customer1@email.com', 'customer', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', '123456789', 'ROLE_CUSTOMER');
INSERT INTO users VALUES (2147483642, 1, 'Wall St.', 'manager1@email.com', 'manager', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', '987654321', 'ROLE_MANAGER');
INSERT INTO users VALUES (2147483643, 1, '78 Vernadskogo ave. ', 'employee1@email.com', 'employee', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', '123123122', 'ROLE_EMPLOYEE');

# -- ----------------------------
# -- Records of cart
# -- ----------------------------
INSERT INTO cart VALUES (2147483641);
INSERT INTO cart VALUES (2147483642);




