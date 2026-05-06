-- Add Buddy as a dog if not exists
INSERT INTO pet (name, category_id, description, availability_status, price)
SELECT 'Buddy', c.id, 'Sweet and calm Beagle with friendly nature', 'AVAILABLE', 220.00
FROM pet_category c
WHERE c.name = 'dogs'
  AND NOT EXISTS (SELECT 1 FROM pet p WHERE p.name = 'Buddy');

-- Update images for dogs: Bella, Max, Buddy (real dog photos)
INSERT INTO pet_image (pet_id, image_url)
SELECT p.id, 'https://images.unsplash.com/photo-1633722715463-d30628519e23?w=500&h=500&fit=crop' 
FROM pet p WHERE p.name = 'Bella' AND EXISTS (SELECT 1 FROM pet_category c WHERE c.id = p.category_id AND c.name = 'dogs')
ON CONFLICT DO NOTHING;

INSERT INTO pet_image (pet_id, image_url)
SELECT p.id, 'https://images.unsplash.com/photo-1633722730976-3c28ee0e88a1?w=500&h=500&fit=crop' 
FROM pet p WHERE p.name = 'Max' AND EXISTS (SELECT 1 FROM pet_category c WHERE c.id = p.category_id AND c.name = 'dogs')
ON CONFLICT DO NOTHING;

INSERT INTO pet_image (pet_id, image_url)
SELECT p.id, 'https://images.unsplash.com/photo-1633722692292-e154fc6379e4?w=500&h=500&fit=crop' 
FROM pet p WHERE p.name = 'Buddy' 
ON CONFLICT DO NOTHING;

-- Update reptile image (real leopard gecko, not dragon!)
INSERT INTO pet_image (pet_id, image_url)
SELECT p.id, 'https://images.unsplash.com/photo-1591165946662-c3fdfab469c3?w=500&h=500&fit=crop' 
FROM pet p WHERE p.name = 'Rex'
ON CONFLICT DO NOTHING;

-- Update fish image (real fish, not human!)
INSERT INTO pet_image (pet_id, image_url)
SELECT p.id, 'https://images.unsplash.com/photo-1567010007612-9a15ecd064c0?w=500&h=500&fit=crop' 
FROM pet p WHERE p.name = 'Bubbles'
ON CONFLICT DO NOTHING;
