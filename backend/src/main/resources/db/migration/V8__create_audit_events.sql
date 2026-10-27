CREATE TABLE audit_events (
    id BIGSERIAL PRIMARY KEY,

    user_id BIGINT,

    action VARCHAR(50) NOT NULL,

    resource_type VARCHAR(50) NOT NULL,

    resource_id BIGINT,

    timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    details TEXT,

    CONSTRAINT fk_audit_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
);

CREATE INDEX idx_audit_user
    ON audit_events(user_id);

CREATE INDEX idx_audit_action
    ON audit_events(action);

CREATE INDEX idx_audit_timestamp
    ON audit_events(timestamp);
