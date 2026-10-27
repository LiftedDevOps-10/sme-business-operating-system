CREATE TABLE products (
    id BIGSERIAL PRIMARY KEY,

    name VARCHAR(200) NOT NULL,

    description TEXT,

    category VARCHAR(100),

    purchase_price NUMERIC(14,2) NOT NULL,

    selling_price NUMERIC(14,2) NOT NULL,

    stock_quantity INTEGER NOT NULL DEFAULT 0,

    reorder_level INTEGER NOT NULL DEFAULT 0,

    supplier VARCHAR(200),

    active BOOLEAN NOT NULL DEFAULT TRUE,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT chk_products_purchase_price
        CHECK (purchase_price >= 0),

    CONSTRAINT chk_products_selling_price
        CHECK (selling_price >= 0),

    CONSTRAINT chk_products_stock
        CHECK (stock_quantity >= 0),

    CONSTRAINT chk_products_reorder_level
        CHECK (reorder_level >= 0)
);

CREATE INDEX idx_products_name
    ON products(name);

CREATE INDEX idx_products_category
    ON products(category);

CREATE INDEX idx_products_active
    ON products(active);
