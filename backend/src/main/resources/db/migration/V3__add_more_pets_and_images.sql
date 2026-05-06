UPDATE pet SET price = 8500.00 WHERE name = 'Bella';
UPDATE pet SET price = 6200.00 WHERE name = 'Milo';
UPDATE pet SET price = 1800.00 WHERE name = 'Kiwi';
UPDATE pet SET price = 3500.00 WHERE name = 'Rex';
UPDATE pet SET price = 450.00 WHERE name = 'Bubbles';

INSERT INTO pet (name, category_id, description, availability_status, price)
SELECT 'Luna', c.id, 'Gentle golden retriever puppy who loves walks and kids.', 'AVAILABLE', 9200.00
FROM pet_category c
WHERE c.name = 'dogs'
  AND NOT EXISTS (SELECT 1 FROM pet p WHERE p.name = 'Luna');

INSERT INTO pet (name, category_id, description, availability_status, price)
SELECT 'Max', c.id, 'Alert corgi with a big personality and tidy house manners.', 'AVAILABLE', 7800.00
FROM pet_category c
WHERE c.name = 'dogs'
  AND NOT EXISTS (SELECT 1 FROM pet p WHERE p.name = 'Max');

INSERT INTO pet (name, category_id, description, availability_status, price)
SELECT 'Nala', c.id, 'Affectionate tabby cat who enjoys sunny windows and quiet laps.', 'AVAILABLE', 5400.00
FROM pet_category c
WHERE c.name = 'cats'
  AND NOT EXISTS (SELECT 1 FROM pet p WHERE p.name = 'Nala');

INSERT INTO pet (name, category_id, description, availability_status, price)
SELECT 'Shadow', c.id, 'Curious black kitten with calm energy and bright eyes.', 'AVAILABLE', 5100.00
FROM pet_category c
WHERE c.name = 'cats'
  AND NOT EXISTS (SELECT 1 FROM pet p WHERE p.name = 'Shadow');

INSERT INTO pet (name, category_id, description, availability_status, price)
SELECT 'Sunny', c.id, 'Cheerful cockatiel who whistles and warms up quickly.', 'AVAILABLE', 2400.00
FROM pet_category c
WHERE c.name = 'birds'
  AND NOT EXISTS (SELECT 1 FROM pet p WHERE p.name = 'Sunny');

INSERT INTO pet (name, category_id, description, availability_status, price)
SELECT 'Spike', c.id, 'Healthy bearded dragon with an easygoing temperament.', 'AVAILABLE', 4200.00
FROM pet_category c
WHERE c.name = 'reptiles'
  AND NOT EXISTS (SELECT 1 FROM pet p WHERE p.name = 'Spike');

INSERT INTO pet (name, category_id, description, availability_status, price)
SELECT 'Coral', c.id, 'Small school of neon tetras with vivid color and active movement.', 'AVAILABLE', 700.00
FROM pet_category c
WHERE c.name = 'fish'
  AND NOT EXISTS (SELECT 1 FROM pet p WHERE p.name = 'Coral');

INSERT INTO pet_image (pet_id, image_url)
SELECT p.id, image_url
FROM (
  VALUES
    ('Bella', 'https://images.unsplash.com/photo-1552053831-71594a27632d?auto=format&fit=crop&w=900&q=80'),
    ('Milo', 'https://images.unsplash.com/photo-1514888286974-6c03e2ca1dba?auto=format&fit=crop&w=900&q=80'),
    ('Kiwi', 'https://images.unsplash.com/photo-1522926193341-e9ffd686c60f?auto=format&fit=crop&w=900&q=80'),
    ('Rex', 'https://images.unsplash.com/photo-1504450874802-0ba2bcd9b5ae?auto=format&fit=crop&w=900&q=80'),
    ('Bubbles', 'https://images.unsplash.com/photo-1524704654690-b56c05c78a00?auto=format&fit=crop&w=900&q=80'),
    ('Luna', 'https://images.unsplash.com/photo-1537151625747-768eb6cf92b2?auto=format&fit=crop&w=900&q=80'),
    ('Max', 'https://images.unsplash.com/photo-1557973557-ddfa9ee8c5b6?auto=format&fit=crop&w=900&q=80'),
    ('Nala', 'https://images.unsplash.com/photo-1574158622682-e40e69881006?auto=format&fit=crop&w=900&q=80'),
    ('Shadow', 'https://images.unsplash.com/photo-1592194996308-7b43878e84a6?auto=format&fit=crop&w=900&q=80'),
    ('Sunny', 'https://images.unsplash.com/photo-1552728089-57bdde30beb3?auto=format&fit=crop&w=900&q=80'),
    ('Spike', 'https://images.unsplash.com/photo-1610926597998-fc7f2c1b89b0?auto=format&fit=crop&w=900&q=80'),
    ('Coral', 'https://images.unsplash.com/photo-1544551763-46a013bb70d5?auto=format&fit=crop&w=900&q=80')
) AS images(name, image_url)
JOIN pet p ON p.name = images.name
ON CONFLICT DO NOTHING;
