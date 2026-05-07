-- Update images for dogs: Bella (real dog photo)
INSERT INTO pet_image (pet_id, image_url)
SELECT p.id, 'https://images.unsplash.com/photo-1633722715463-d30628519e23?w=500&h=500&fit=crop' 
FROM pet p WHERE p.name = 'Bella' AND EXISTS (SELECT 1 FROM pet_category c WHERE c.id = p.category_id AND c.name = 'dogs')
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
