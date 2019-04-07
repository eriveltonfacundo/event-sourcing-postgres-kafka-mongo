CREATE TABLE orders (
	id bigserial NOT NULL,
	city varchar(50) NOT NULL,
	complement varchar(50) NOT NULL,
	number_street varchar(10) NOT NULL,
	street varchar(100) NOT NULL,
	zip_code varchar(10) NOT NULL,
	confirmation_date timestamp NOT NULL,
	status varchar(50) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE orders_items (
	id bigserial NOT NULL,
	description varchar(100) NOT NULL,
	order_id int8 NOT NULL,
	quantity numeric(15,3) NOT NULL,
	unit_price numeric(15,2) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (order_id) REFERENCES orders (id)
);

CREATE TABLE payments (
	id bigserial NOT NULL,
	credit_card_number varchar(16) NOT NULL,
	payment_date timestamp NOT NULL,
	price numeric(15,2) NOT NULL,
	status varchar(255) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE public.stores (
	id bigserial NOT NULL,
	name varchar(100) NOT NULL,
	city varchar(50) NOT NULL,
	complement varchar(50) NOT NULL,
	number_street varchar(10) NOT NULL,
	street varchar(100) NOT NULL,
	zip_code varchar(10) NOT NULL,
	PRIMARY KEY (id)
);