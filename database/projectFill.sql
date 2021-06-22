insert into users (user_email, user_password, user_role, user_status, user_name, user_surname, user_patronymic, user_balance)
values ("admin@gmail.com", "58b5444cf1b6253a4317fe12daff411a78bda0a95279b1d5768ebf5ca60829e78da944e8a9160a0b6d428cb213e813525a72650dac67b88879394ff624da482f", "ADMIN", "ENABLE", 
"Nastya", "Golubeva", "Dmitrievna", 0);
insert into users (user_email, user_password, user_role, user_status, user_name, user_surname, user_patronymic, user_balance)
values ("user@gmail.com", "e531286ad0aca20c9cd144a03979090a9d41955970f3654e231a9a49bce7dfab5275313cea083cc642e9d5519ab2e45ce5fd8cc518ad920d1486a4aa0c5f0f4f", "USER", "ENABLE",
"Olga", "Petkun", "Stanislavovna", 20);

insert into images (image_name) values ("bac4ee48-8a79-45c7-9c9f-a0e0f7e9e3bd");
insert into images (image_name) values ("29942f96-44a0-4cc2-a4e0-59dea7b9ce8d");
insert into images (image_name) values ("a8e08e93-4733-4ae8-ac78-ca37d2627813");
insert into images (image_name) values ("f775d326-b776-4584-a141-3376ec291a3f");
insert into images (image_name) values ("6f429a4c-c5c7-48d2-940c-475310bb93fd");
insert into images (image_name) values ("65731a8c-487b-4811-babf-288f25c73318");

insert into products (product_title, product_price, product_description, image_id_fk) values ("Iphone 12", 1199.99, "5G goes Pro. A14 Bionic rockets past every other smartphone chip. The Pro camera system takes low-light photography to the next level — with an even bigger jump on iPhone 12 Pro Max. And Ceramic Shield delivers four times better drop performance. Let’s see what this thing can do.",
1);
insert into products (product_title, product_price, product_description, image_id_fk) values ("Airpods 2", 129.99, "AirPods deliver effortless, all-day audio on the go. And AirPods Pro bring Active Noise Cancellation to an in-ear headphone — with a customizable fit.",
2);
insert into products (product_title, product_price, product_description, image_id_fk) values ("Imac", 2599.99, "The all-in-one for all. If you can dream it, you can do it on iMac. It’s beautifully designed, incredibly intuitive, and packed with powerful tools that let you take any idea to the next level. And the new 27-inch model elevates the experience in every way, with faster processors and graphics, expanded memory and storage, enhanced audio and video capabilities, and an even more stunning Retina 5K display. It’s the desktop that does it all — better and faster than ever.",
3);
insert into products (product_title, product_price, product_description, image_id_fk) values ("Macbook pro", 1799.99, "The Apple M1 chip gives the 13‑inch MacBook Pro speed and power beyond belief. With up to 2.8x CPU performance. Up to 5x the graphics speed. Our most advanced Neural Engine for up to 11x faster machine learning. And up to 20 hours of battery life — the longest of any Mac ever. It’s our most popular pro notebook, taken to a whole new level.",
4);
insert into products (product_title, product_price, product_description, image_id_fk) values ("Apple watch 6", 1299.99, "Measure your blood oxygen level with a revolutionary new sensor and app. Take an ECG anytime, anywhere. See your fitness metrics at a glance with the enhanced Always-On Retina display. With Apple Watch Series 6 on your wrist, a healthier, more active, more connected life is within reach.",
5);
insert into products (product_title, product_price, product_description, image_id_fk) values ("MagSafe", 799.99, "MagSafe is a new ecosystem of accessories for easy attachment and faster wireless charging. With endless combinations, there is a mix to match any style.",
6);

insert into basket (user_id_fk, product_id_fk) values (2, 1);
insert into basket (user_id_fk, product_id_fk) values (2, 3);
insert into basket (user_id_fk, product_id_fk) values (2, 4);

insert into orders (order_creation_date, order_closing_date, order_status, user_id_fk) values (1602201600000, 1602374400000, "PRODUCED", 2);
insert into orders (order_creation_date, order_closing_date, order_status, user_id_fk) values (1603065600000, 1603411200000, "DENIED", 2);
insert into orders (order_creation_date, order_closing_date, order_status, user_id_fk) values (1603929600000, 1603929600000, "UNDER_CONSIDERATION", 2);

insert into order_item (product_id_fk, order_id_fk) values (3, 1);
insert into order_item (product_id_fk, order_id_fk) values (1, 2);
insert into order_item (product_id_fk, order_id_fk) values (2, 2);
insert into order_item (product_id_fk, order_id_fk) values (3, 2);
insert into order_item (product_id_fk, order_id_fk) values (4, 2);
insert into order_item (product_id_fk, order_id_fk) values (5, 2);
insert into order_item (product_id_fk, order_id_fk) values (6, 3);
insert into order_item (product_id_fk, order_id_fk) values (3, 3);
insert into order_item (product_id_fk, order_id_fk) values (1, 3);