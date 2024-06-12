INSERT INTO addresses (district, province, text, ward)
VALUES ('District A', 'Province X', 'Address 1', 'Ward 1'),
       ('District B', 'Province Y', 'Address 2', 'Ward 2'),
       ('District C', 'Province Z', 'Address 3', 'Ward 3');

-- Insert data into hotels table
INSERT INTO hotels (address_id, description, name)
VALUES (1, 'Description for Hotel 1', 'Hotel 1'),
       (2, 'Description for Hotel 2', 'Hotel 2'),
       (3, 'Description for Hotel 3', 'Hotel 3');

-- Insert data into room_types table
INSERT INTO room_types (base_price, total_room, hotel_id, name)
VALUES (100.00, 10, 1, 'Single Room'),
       (150.00, 20, 1, 'Double Room'),
       (120.00, 15, 2, 'Standard Room'),
       (200.00, 25, 2, 'Suite Room'),
       (90.00, 12, 3, 'Economy Room'),
       (180.00, 18, 3, 'Luxury Room');