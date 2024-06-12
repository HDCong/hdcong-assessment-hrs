CREATE TABLE users
(
    id           BIGSERIAL,
    email        VARCHAR(255),
    name         VARCHAR(255),
    phone_number VARCHAR(255),
    username     VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE addresses
(
    id       BIGSERIAL,
    district VARCHAR(255),
    province VARCHAR(255),
    text     VARCHAR(255),
    ward     VARCHAR(255),
    PRIMARY KEY (id)
);


CREATE TABLE hotels
(
    address_id  BIGINT unique,
    id          BIGSERIAL,
    description VARCHAR(255),
    name        VARCHAR(255),
    PRIMARY KEY (id)
);
ALTER TABLE IF EXISTS hotels ADD CONSTRAINT fk_hotels_address_id FOREIGN KEY (address_id) REFERENCES addresses;

CREATE TABLE room_types
(
    base_price NUMERIC(38, 2),
    total_room INTEGER,
    hotel_id   BIGINT NOT NULL,
    id         BIGSERIAL,
    name       VARCHAR(255),
    PRIMARY KEY (id)
);
ALTER TABLE IF EXISTS room_types ADD CONSTRAINT fk_room_types_hotel_id FOREIGN KEY (hotel_id) REFERENCES hotels;

CREATE TABLE bookings
(
    deleted          BIT,
    end_date         DATE,
    start_date       DATE,
    total_base_price NUMERIC(38, 2),
    created_at       TIMESTAMP,
    id               BIGSERIAL,
    updated_at       TIMESTAMP,
    user_id          BIGINT NOT NULL,
    created_by       VARCHAR(255),
    updated_by       VARCHAR(255),
    status           VARCHAR(20),
    PRIMARY KEY (id)
);
ALTER TABLE IF EXISTS bookings ADD CONSTRAINT fk_bookings_user_id FOREIGN KEY (user_id) REFERENCES users;

CREATE TABLE booking_room_type
(
    booking_id   BIGINT NOT NULL,
    room_type_id BIGINT NOT NULL
);
ALTER TABLE IF EXISTS booking_room_type ADD CONSTRAINT fk_booking_room_type_room_type_id FOREIGN KEY (room_type_id) REFERENCES room_types;
ALTER TABLE IF EXISTS booking_room_type ADD CONSTRAINT fk_booking_room_type_booking_id FOREIGN KEY (booking_id) REFERENCES bookings;

CREATE TABLE room_availability_by_date
(
    date         DATE,
    room_remains INTEGER,
    id           BIGSERIAL,
    room_type_id BIGINT NOT NULL,
    PRIMARY KEY (id)
);
ALTER TABLE IF EXISTS room_availability_by_date ADD CONSTRAINT fk_room_availability_by_date_room_type_id foreign key (room_type_id) REFERENCES room_types;

CREATE TABLE images
(
    id           BIGSERIAL,
    room_type_id BIGINT NOT NULL,
    url          VARCHAR(255),
    PRIMARY KEY (id)
);
ALTER TABLE IF EXISTS images ADD CONSTRAINT fk_images_room_type_id FOREIGN KEY (room_type_id) REFERENCES room_types;
