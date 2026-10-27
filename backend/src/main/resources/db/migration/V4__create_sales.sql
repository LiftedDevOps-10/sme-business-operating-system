CREATE TABLE sales (
    id BIGSERIAL PRIMARY KEY,

    customer_id BIGINT,

    created_by BIGINT NOT NULL,

    total_amount NUMERIC(14,2) NOT NULL,

    payment_status VARCHAR(20) NOT NULL,

    sale_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_sales_customer
        FOREIGN KEY (customer_id)
        REFERENCES customers(id),

    CONSTRAINT fk_sales_created_by
        FOREIGN KEY (created_by)
        REFERENCES users(id),

    CONSTRAINT chk_sales_total
        CHECK (total_amount >= 0),

    CONSTRAINT chk_sales_payment_status
        CHECK (
            payment_status IN (
                'PENDING',
                'PAID',
                'PARTIAL',
                'CANCELLED'
            )
        )
);

CREATE INDEX idx_sales_customer
    ON sales(customer_id);

CREATE INDEX idx_sales_created_by
    ON sales(created_by);

CREATE INDEX idx_sales_date
    ON sales(sale_date);
