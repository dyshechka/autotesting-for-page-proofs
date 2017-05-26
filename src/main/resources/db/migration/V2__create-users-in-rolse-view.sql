CREATE VIEW users_in_roles AS
  SELECT
    u.login,
    u.password,
    TRUE        AS 'enabled',
    r.ROLE_CODE AS 'role'
  FROM `user_info` u
    JOIN `user_roles` r ON u.ROLE_ID = r.ID;