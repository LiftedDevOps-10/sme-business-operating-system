CREATE TABLE inventory_movements (
    id BIGSERIAL PRIMARY KEY,

    product_id BIGINT NOT NULL,

    movement_type VARCHAR(30) NOT NULL,

    quantity INTEGER NOT NULL,

    reference_id BIGINT,

    created_by BIGINT NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_inventory_product
        FOREIGN KEY (product_id)
        REFERENCES products(id),

    CONSTRAINT fk_inventory_created_by
        FOREIGN KEY (created_by)
        REFERENCES users(id),

    CONSTRAINT chk_inventory_movement_type
        CHECK (
            movement_type IN (
                'STOCK_ADDITION',
                'SALE',
                'STOCK_ADJUSTMENT'
            )
        ),

    CONSTRAINT chk_inventory_quantity
        CHECK (quantity > 0)
);

CREATE INDEX idx_inventory_product
    ON inventory_movements(product_id);

CREATE INDEX idx_inventory_created_at
    ON inventory_movements(created_at);
