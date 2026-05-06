-- Remove old placeholder images
DELETE FROM pet_image WHERE image_url LIKE 'https://example.com%';

-- Add real Unsplash images for Bella and Buddy (replacing removed placeholders)
INSERT INTO pet_image (pet_id, image_url)
SELECT p.id, 'https://images.unsplash.com/photo-1552053831-71594a27632d?auto=format&fit=crop&w=900&q=80'
FROM pet p WHERE p.name = 'Bella' AND NOT EXISTS (SELECT 1 FROM pet_image pi WHERE pi.pet_id = p.id AND pi.image_url LIKE 'https://images.unsplash.com%');

INSERT INTO pet_image (pet_id, image_url)
SELECT p.id, 'https://images.unsplash.com/photo-1633722692292-e154fc6379e4?w=500&h=500&fit=crop'
FROM pet p WHERE p.name = 'Buddy' AND NOT EXISTS (SELECT 1 FROM pet_image pi WHERE pi.pet_id = p.id AND pi.image_url LIKE 'https://images.unsplash.com%');

