CREATE TABLE orders (
	id bigserial NOT NULL,
	address varchar(100) NOT NULL,
	confirmation_date timestamp,
	payment_id bigint,
	status varchar(50) DEFAULT 'PENDING',
	PRIMARY KEY (id),
	CONSTRAINT status_values CHECK(status = 'CONFIRMED' OR status = 'REFUNDED' OR status = 'PENDING')
);

CREATE TABLE orders_items (
	id bigserial NOT NULL,
	description varchar(100) NOT NULL,
	status varchar(50) DEFAULT 'CONFIRMED',
	order_id bigint REFERENCES orders(id),
	quantity numeric(15,3) NOT NULL,
	unit_price numeric(15,2) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (order_id) REFERENCES orders (id),
  CONSTRAINT status_values CHECK(status = 'CONFIRMED' OR status = 'REFUNDED' OR status = 'PENDING')
);

CREATE TABLE payments (
	id bigserial NOT NULL,
	credit_card_number varchar(16) NOT NULL,
	payment_date timestamp NOT NULL,
	order_id bigint REFERENCES orders(id),
	price numeric(15,2) NOT NULL,
	status varchar(255) DEFAULT 'CONFIRMED',
	PRIMARY KEY (id),
	CONSTRAINT status_values CHECK(status = 'CONFIRMED' OR status = 'REFUNDED')
);

CREATE TABLE public.stores (
	id bigserial NOT NULL,
	name varchar(100) NOT NULL,
	address varchar(100) NOT NULL,
	PRIMARY KEY (id)
);