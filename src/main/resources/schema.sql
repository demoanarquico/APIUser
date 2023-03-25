
DROP TABLE IF EXISTS user;  

CREATE TABLE user (  
id int AUTO_INCREMENT NOT NULL,  
name varchar(50) NOT NULL,
email varchar(100) NOT NULL,  
password varchar(10) NOT NULL,
created timestamp NOT NULL,
modified timestamp NULL,
last_login timestamp NOT NULL,
token varchar(500) NOT NULL,
isactive varchar(1) NOT NULL,
CONSTRAINT id_user_pk PRIMARY KEY (id)
);

DROP TABLE IF EXISTS phone; 

CREATE TABLE phone (  
id int AUTO_INCREMENT PRIMARY KEY,
id_user int NOT NULL,  
number varchar(7) NOT NULL,
citycode varchar(1) NOT NULL,  
countrycode varchar(2) NOT NULL,
CONSTRAINT id_phone_pk PRIMARY KEY (id),
CONSTRAINT item_id_user_fk FOREIGN KEY (id_user) REFERENCES user(id)
);
  