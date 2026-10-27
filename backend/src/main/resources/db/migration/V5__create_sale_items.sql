CREATE TABLE sale_items (
    id BIGSERIAL PRIMARY KEY,

    sale_id BIGINT NOT NULL,

    product_id BIGINT NOT NULL,

    quantity INTEGER NOT NULL,

    unit_price NUMERIC(14,2) NOT NULL,

    subtotal NUMERIC(14,2) NOT NULL,

    CONSTRAINT fk_sale_items_sale
        FOREIGN KEY (sale_id)
        REFERENCES sales(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_sale_items_product
        FOREIGN KEY (product_id)
        REFERENCES products(id),

    CONSTRAINT chk_sale_items_quantity
        CHECK (quantity > 0),

    CONSTRAINT chk_sale_items_unit_price
        CHECK (unit_price >= 0),

    CONSTRAINT chk_sale_items_subtotal
        CHECK (subtotal >= 0)
);

CREATE INDEX idx_sale_items_sale
    ON sale_items(sale_id);

CREATE INDEX idx_sale_items_product
    ON sale_items(product_id);
