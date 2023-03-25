ALTER TABLE oauth_access_token ALTER COLUMN token SET DATA TYPE BLOB(104857600);
ALTER TABLE oauth_refresh_token ALTER COLUMN authentication SET DATA TYPE BLOB(104857600);
ALTER TABLE oauth_refresh_token ALTER COLUMN token SET DATA TYPE BLOB(104857600);
INSERT INTO users(id, username, password, enabled) VALUES (1, 'uadmin', '{bcrypt}$2a$12$BrhsPM5y7gxUL9bbyfnp0.IFZhbPnFwuQ1lCWVmn/tN0SZAWZIs6e', 1);
INSERT INTO authorities(username, authority) VALUES ('uadmin', 'ROLE_ADMIN');
INSERT INTO oauth_client_details(client_id, resource_ids, client_secret, scope, authorized_grant_types,web_server_redirect_uri, authorities, access_token_validity,refresh_token_validity, additional_information, autoapprove)VALUES ('dd0881b3ead6a3f53e099d2d1d60706f', '', '{bcrypt}$2a$12$ru5uI.hGmq0N3MKeksii3u329q/MgGP.M3a0UfnXwC2lVE72EdCyq', 'read,write', 'password,refresh_token,client_credentials', '', 'ROLE_ADMIN', 3600, null, null, null);