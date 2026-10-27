CREATE TABLE customers (
    id BIGSERIAL PRIMARY KEY,

    full_name VARCHAR(200) NOT NULL,

    phone VARCHAR(30),

    email VARCHAR(255),

    address TEXT,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_customers_name
    ON customers(full_name);

CREATE INDEX idx_customers_phone
    ON customers(phone);

CREATE INDEX idx_customers_email
    ON customers(email);
