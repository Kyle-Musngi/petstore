INSERT INTO pet_category (name, display_label, is_active)
VALUES
  ('dogs', 'Dogs', true),
  ('cats', 'Cats', true),
  ('birds', 'Birds', true),
  ('reptiles', 'Reptiles', true),
  ('fish', 'Fish', true)
ON CONFLICT (name) DO NOTHING;

INSERT INTO pet (name, category_id, description, availability_status, price)
SELECT 'Bella', c.id, 'Friendly and playful dog', 'AVAILABLE', 250.00
FROM pet_category c WHERE c.name = 'dogs'
ON CONFLICT DO NOTHING;

INSERT INTO pet (name, category_id, description, availability_status, price)
SELECT 'Milo', c.id, 'Calm and curious cat', 'AVAILABLE', 180.00
FROM pet_category c WHERE c.name = 'cats'
ON CONFLICT DO NOTHING;

INSERT INTO pet (name, category_id, description, availability_status, price)
SELECT 'Kiwi', c.id, 'Bright green bird', 'AVAILABLE', 90.00
FROM pet_category c WHERE c.name = 'birds'
ON CONFLICT DO NOTHING;

INSERT INTO pet (name, category_id, description, availability_status, price)
SELECT 'Rex', c.id, 'Gentle leopard gecko', 'AVAILABLE', 120.00
FROM pet_category c WHERE c.name = 'reptiles'
ON CONFLICT DO NOTHING;

INSERT INTO pet (name, category_id, description, availability_status, price)
SELECT 'Bubbles', c.id, 'Colorful betta fish', 'AVAILABLE', 35.00
FROM pet_category c WHERE c.name = 'fish'
ON CONFLICT DO NOTHING;

INSERT INTO pet_image (pet_id, image_url)
SELECT p.id, 'https://example.com/pets/bella.jpg' FROM pet p WHERE p.name = 'Bella'
ON CONFLICT DO NOTHING;
