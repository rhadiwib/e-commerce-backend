-- Insert initial data into category table
INSERT INTO category (id, description, name) VALUES
(nextval('category_seq'), 'Tires and Wheels for various vehicles', 'Tires & Wheels'),
(nextval('category_seq'), 'Engine components and parts', 'Engines'),
(nextval('category_seq'), 'Electrical systems and accessories', 'Electrical'),
(nextval('category_seq'), 'Brake systems and components', 'Brakes'),
(nextval('category_seq'), 'Suspension and steering parts', 'Suspension & Steering'),
(nextval('category_seq'), 'Interior accessories and parts', 'Interior Accessories'),
(nextval('category_seq'), 'Exterior accessories and body parts', 'Exterior Accessories'),
(nextval('category_seq'), 'Lighting solutions and bulbs', 'Lighting'),
(nextval('category_seq'), 'Motorcycle specific parts and gear', 'Motorcycle Parts & Gear'),
(nextval('category_seq'), 'Tools and diagnostic equipment', 'Tools & Diagnostics'),
(nextval('category_seq'), 'Performance and tuning parts', 'Performance Parts');

-- Assume already have a sequence named 'product_seq'
-- Insert initial data into product table
INSERT INTO product (id, available_quantity, description, name, price, category_id) VALUES
(nextval('product_seq'), 150.0, 'All-season radial tires suitable for cars and light trucks',
 'All-Season Radials', 89.99, (SELECT id FROM category WHERE name = 'Tires & Wheels')),
(nextval('product_seq'), 75.0, 'High-performance V8 engine for sports cars', 'V8 Performance Engine',
 4999.99, (SELECT id FROM category WHERE name = 'Engines')),
(nextval('product_seq'), 200.0, 'Advanced car battery with longer lifespan', 'Long-Life Car Battery',
 129.99, (SELECT id FROM category WHERE name = 'Electrical')),
(nextval('product_seq'), 120.0, 'Disc brake pads for enhanced stopping power', 'High-Performance Brake Pads',
 59.99, (SELECT id FROM category WHERE name = 'Brakes')),
(nextval('product_seq'), 90.0, 'Adjustable suspension kit for improved handling', 'Adjustable Suspension Kit',
 299.99, (SELECT id FROM category WHERE name = 'Suspension & Steering')),
(nextval('product_seq'), 180.0, 'Leather steering wheel cover for added comfort', 'Leather Steering Cover',
 49.99, (SELECT id FROM category WHERE name = 'Interior Accessories')),
(nextval('product_seq'), 160.0, 'Carbon fiber body kit for sports cars', 'Carbon Fiber Body Kit',
 799.99, (SELECT id FROM category WHERE name = 'Exterior Accessories')),
(nextval('product_seq'), 220.0, 'LED headlight bulbs with high brightness', 'LED Headlight Bulbs',
 39.99, (SELECT id FROM category WHERE name = 'Lighting')),
(nextval('product_seq'), 130.0, 'Motorcycle helmet with integrated Bluetooth', 'Bluetooth Motorcycle Helmet',
 199.99, (SELECT id FROM category WHERE name = 'Motorcycle Parts & Gear')),
(nextval('product_seq'), 300.0, 'Comprehensive OBD-II diagnostic scanner', 'OBD-II Diagnostic Scanner',
 89.99, (SELECT id FROM category WHERE name = 'Tools & Diagnostics')),
(nextval('product_seq'), 85.0, 'Turbocharger kit for increased engine performance', 'Turbocharger Kit',
 699.99, (SELECT id FROM category WHERE name = 'Performance Parts'));


