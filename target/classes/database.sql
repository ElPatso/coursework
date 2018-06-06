-- Table: users
CREATE TABLE users (
  id       INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(50) NOT NULL,
  enabled BIT NOT NULL
)
  ENGINE = InnoDB;

-- Table: roles
CREATE TABLE roles (
  id   INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL
)
  ENGINE = InnoDB;

-- Table for mapping user and roles: user_roles
CREATE TABLE user_roles (
  user_id INT NOT NULL,
  role_id INT NOT NULL,

  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (role_id) REFERENCES roles (id),

  UNIQUE (user_id, role_id)
)
  ENGINE = InnoDB;
-- Table verification
CREATE TABLE verificationtoken (
  id   INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  token VARCHAR(100),
  expiryDate DATE
)
  ENGINE = InnoDB;
-- table for products
CREATE table products(
  id INT                not null auto_increment primary key,
  name VARCHAR(50)     NOT NULL ,
  category VARCHAR(50) NOT NULL ,
  price DOUBLE         NOT NULL ,
  description TEXT     NOT NULL

)
  ENGINE = InnoDB;
-- table for comments
CREATE table comments(
  id INT                not null auto_increment primary key,
  product_id int NOT NULL ,
  name VARCHAR(50) NOT NULL ,
  comment TEXT NOT NULL,
  FOREIGN KEY (product_id) REFERENCES products (id)
)
  ENGINE = InnoDB;
-- table for category
CREATE table category(
  id INT                not null auto_increment primary key,
  parent_id int ,
  name VARCHAR(50) NOT NULL ,
  FOREIGN KEY (parent_id) REFERENCES  category(id)
)
  ENGINE = InnoDB;

INSERT INTO users VALUES (1, 'admin', '$2a$11$uSXS6rLJ91WjgOHhEGDx..VGs7MkKZV68Lv5r1uwFu7HgtRn3dcXG','ostapmail',true);
INSERT INTO roles VALUES (1, 'ADMIN');
INSERT INTO verificationtoken VALUES (1,'asdasdasd','2017-04-30');
INSERT INTO user_roles VALUES (1, 2);

