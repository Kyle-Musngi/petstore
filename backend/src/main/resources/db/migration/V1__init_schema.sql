CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE pet_category (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  name VARCHAR(32) UNIQUE NOT NULL,
  display_label VARCHAR(64) NOT NULL,
  is_active BOOLEAN NOT NULL DEFAULT true,
  created_at TIMESTAMP NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE pet (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  name VARCHAR(80) NOT NULL,
  category_id UUID NOT NULL REFERENCES pet_category(id),
  description VARCHAR(1000) NOT NULL,
  availability_status VARCHAR(20) NOT NULL,
  price NUMERIC(10,2) NOT NULL DEFAULT 0,
  listed_at TIMESTAMP NOT NULL DEFAULT NOW(),
  created_at TIMESTAMP NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE pet_image (
  pet_id UUID NOT NULL REFERENCES pet(id),
  image_url TEXT NOT NULL,
  PRIMARY KEY (pet_id, image_url)
);

CREATE TABLE cart (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  status VARCHAR(20) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE cart_item (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  cart_id UUID NOT NULL REFERENCES cart(id),
  pet_id UUID NOT NULL REFERENCES pet(id),
  quantity INTEGER NOT NULL,
  price NUMERIC(10,2) NOT NULL
);

CREATE TABLE orders (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  cart_id UUID NOT NULL REFERENCES cart(id),
  status VARCHAR(20) NOT NULL,
  total NUMERIC(10,2) NOT NULL,
  delivery_full_name VARCHAR(120) NOT NULL,
  delivery_email VARCHAR(120) NOT NULL,
  delivery_address_line1 VARCHAR(120) NOT NULL,
  delivery_address_line2 VARCHAR(120),
  delivery_city VARCHAR(80) NOT NULL,
  delivery_state VARCHAR(40) NOT NULL,
  delivery_postal_code VARCHAR(20) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE order_item (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  order_id UUID NOT NULL REFERENCES orders(id),
  pet_id UUID NOT NULL REFERENCES pet(id),
  quantity INTEGER NOT NULL,
  price NUMERIC(10,2) NOT NULL
);
