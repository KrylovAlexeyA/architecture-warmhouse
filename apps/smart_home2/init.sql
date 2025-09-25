CREATE TABLE IF NOT EXISTS sensor (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(100) NOT NULL,
    location VARCHAR(255),
    unit VARCHAR(50),
    value NUMERIC(3, 1),
    status VARCHAR(50) DEFAULT 'inactive'
);