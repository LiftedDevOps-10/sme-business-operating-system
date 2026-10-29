-- Correct development account password hashes.
-- Development accounts only; do not use these credentials in production.

UPDATE users
SET password_hash = '$2b$10$CpEpBn.3McV9xBq2LQucReAFU.QgHVgs0kb1ApjvU8LbwO1ZBwWza'
WHERE username = 'owner'
  AND email = 'owner@smebos.local';

UPDATE users
SET password_hash = '$2b$10$CpEpBn.3McV9xBq2LQucReAFU.QgHVgs0kb1ApjvU8LbwO1ZBwWza'
WHERE username = 'manager'
  AND email = 'manager@smebos.local';

UPDATE users
SET password_hash = '$2b$10$CpEpBn.3McV9xBq2LQucReAFU.QgHVgs0kb1ApjvU8LbwO1ZBwWza'
WHERE username = 'staff'
  AND email = 'staff@smebos.local';
