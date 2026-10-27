CREATE TABLE expenses (
    id BIGSERIAL PRIMARY KEY,

    category VARCHAR(50) NOT NULL,

    description TEXT,

    amount NUMERIC(14,2) NOT NULL,

    expense_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    recorded_by BIGINT NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_expenses_recorded_by
        FOREIGN KEY (recorded_by)
        REFERENCES users(id),

    CONSTRAINT chk_expenses_amount
        CHECK (amount > 0),

    CONSTRAINT chk_expenses_category
        CHECK (
            category IN (
                'RENT',
                'UTILITIES',
                'SALARIES',
                'TRANSPORT',
                'MARKETING',
                'SUPPLIES',
                'MAINTENANCE',
                'OTHER'
            )
        )
);

CREATE INDEX idx_expenses_category
    ON expenses(category);

CREATE INDEX idx_expenses_date
    ON expenses(expense_date);

CREATE INDEX idx_expenses_recorded_by
    ON expenses(recorded_by);
