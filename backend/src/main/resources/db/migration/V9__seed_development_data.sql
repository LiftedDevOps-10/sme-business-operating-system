-- ============================================================
-- SME-BOS DEVELOPMENT SEED DATA
-- ============================================================

-- Development password for all seeded users:
-- Password123!
--
-- IMPORTANT:
-- These are development-only accounts.
-- Do not use these credentials in production.

INSERT INTO users (
    username,
    email,
    password_hash,
    role,
    active
)
VALUES
(
    'owner',
    'owner@smebos.local',
    '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy',
    'OWNER',
    TRUE
),
(
    'manager',
    'manager@smebos.local',
    '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy',
    'MANAGER',
    TRUE
),
(
    'staff',
    'staff@smebos.local',
    '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy',
    'STAFF',
    TRUE
);
-- ============================================================
-- DEVELOPMENT PRODUCTS
-- ============================================================

INSERT INTO products (
    name,
    description,
    category,
    purchase_price,
    selling_price,
    stock_quantity,
    reorder_level,
    supplier,
    active
)
VALUES
(
    'Business Laptop',
    '14-inch business laptop',
    'COMPUTERS',
    450000.00,
    550000.00,
    10,
    3,
    'Lifted Tech Supplies',
    TRUE
),
(
    'Wireless Mouse',
    'Wireless optical mouse',
    'ACCESSORIES',
    5000.00,
    8500.00,
    20,
    5,
    'Lifted Tech Supplies',
    TRUE
),
(
    'USB Keyboard',
    'Full-size USB keyboard',
    'ACCESSORIES',
    7000.00,
    12000.00,
    15,
    5,
    'Lifted Tech Supplies',
    TRUE
),
(
    '24-inch Monitor',
    '24-inch LED business monitor',
    'MONITORS',
    85000.00,
    120000.00,
    8,
    3,
    'Lifted Tech Supplies',
    TRUE
),
(
    'USB-C Cable',
    'USB-C charging and data cable',
    'ACCESSORIES',
    2500.00,
    4500.00,
    30,
    10,
    'Lifted Tech Supplies',
    TRUE
),
(
    'Wireless Headset',
    'Bluetooth wireless headset',
    'AUDIO',
    18000.00,
    28000.00,
    4,
    5,
    'Lifted Tech Supplies',
    TRUE
);
-- ============================================================
-- DEVELOPMENT CUSTOMERS
-- ============================================================

INSERT INTO customers (
    full_name,
    phone,
    email,
    address
)
VALUES
(
    'John Doe',
    '08030000001',
    'john@example.local',
    'Lagos, Nigeria'
),
(
    'Sarah Johnson',
    '08030000002',
    'sarah@example.local',
    'Abuja, Nigeria'
),
(
    'Michael Brown',
    '08030000003',
    'michael@example.local',
    'Port Harcourt, Nigeria'
);
-- ============================================================
-- INITIAL INVENTORY MOVEMENTS
-- ============================================================

INSERT INTO inventory_movements (
    product_id,
    movement_type,
    quantity,
    reference_id,
    created_by
)
SELECT
    p.id,
    'STOCK_ADDITION',
    p.stock_quantity,
    NULL,
    u.id
FROM products p
CROSS JOIN users u
WHERE u.username = 'owner';
