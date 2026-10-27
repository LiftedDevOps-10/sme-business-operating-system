CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,

    username VARCHAR(100) NOT NULL UNIQUE,

    email VARCHAR(255) NOT NULL UNIQUE,

    password_hash VARCHAR(255) NOT NULL,

    role VARCHAR(20) NOT NULL,

    active BOOLEAN NOT NULL DEFAULT TRUE,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT chk_users_role
        CHECK (role IN ('OWNER', 'MANAGER', 'STAFF'))
);

CREATE INDEX idx_users_username
    ON users(username);

CREATE INDEX idx_users_email
    ON users(email);
