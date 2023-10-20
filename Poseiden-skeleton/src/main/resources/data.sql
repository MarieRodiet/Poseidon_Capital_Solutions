-- Drop the tables if they exist
DROP TABLE IF EXISTS bidlist;
DROP TABLE IF EXISTS trade;
DROP TABLE IF EXISTS curve_point;
DROP TABLE IF EXISTS rating;
DROP TABLE IF EXISTS rule_name;
DROP TABLE IF EXISTS db_user;

-- Create the BidList table
CREATE TABLE bidlist (
  bid_list_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  account VARCHAR(30) NOT NULL,
  type VARCHAR(30) NOT NULL,
  bid_quantity DOUBLE,
  ask_quantity DOUBLE,
  bid DOUBLE,
  ask DOUBLE,
  benchmark VARCHAR(125),
  bid_list_date TIMESTAMP,
  commentary VARCHAR(125),
  security VARCHAR(125),
  status VARCHAR(10),
  trader VARCHAR(125),
  book VARCHAR(125),
  creation_name VARCHAR(125),
  creation_date TIMESTAMP,
  revision_name VARCHAR(125),
  revision_date TIMESTAMP,
  deal_name VARCHAR(125),
  deal_type VARCHAR(125),
  source_list_id VARCHAR(125),
  side VARCHAR(125)
);

-- Create the Trade table
CREATE TABLE trade (
  trade_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  account VARCHAR(30) NOT NULL,
  type VARCHAR(30) NOT NULL,
  buy_quantity DOUBLE,
  sell_quantity DOUBLE,
  buy_price DOUBLE,
  sell_price DOUBLE,
  trade_date TIMESTAMP,
  security VARCHAR(125),
  status VARCHAR(10),
  trader VARCHAR(125),
  benchmark VARCHAR(125),
  book VARCHAR(125),
  creation_name VARCHAR(125),
  creation_date TIMESTAMP,
  revision_name VARCHAR(125),
  revision_date TIMESTAMP,
  deal_name VARCHAR(125),
  deal_type VARCHAR(125),
  source_list_id VARCHAR(125),
  side VARCHAR(125)
);

-- Create the CurvePoint table
CREATE TABLE curve_point (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  curve_id INT,
  as_of_date TIMESTAMP,
  term DOUBLE,
  curve_value DOUBLE,
  creation_date TIMESTAMP
);

-- Create the Rating table
CREATE TABLE rating (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  moodys_rating VARCHAR(125),
  sandp_rating VARCHAR(125),
  fitch_rating VARCHAR(125),
  order_number INT
);

-- Create the RuleName table
CREATE TABLE rule_name (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(125),
  description VARCHAR(125),
  json VARCHAR(125),
  template VARCHAR(512),
  sql_str VARCHAR(125),
  sql_part VARCHAR(125)
);

-- Create the DBUser table
CREATE TABLE db_user (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(125),
  password VARCHAR(125),
  fullname VARCHAR(125),
  role VARCHAR(125)
);

-- Insert sample data into the DBUser table
INSERT INTO db_user (username, password, fullname, role) VALUES
('User', '$2y$10$.qkbukzzX21D.bqbI.B2R.tvWP90o/Y16QRWVLodw51BHft7ZWbc.', 'USER', 'USER'),
('Admin', '$2y$10$kp1V7UYDEWn17WSK16UcmOnFd1mPFVF6UkLrOOCGtf24HOYt8p1iC', 'ADMIN', 'ADMIN');

-- Insert sample data into the Rating table
INSERT INTO rating (moodys_rating, sandp_rating, fitch_rating, order_number) VALUES
('moodysRating 2', 'sandPRating 2', 'fitchRating 2', 2),
('moodysRating 1', 'sandPRating 1', 'fitchRating 1', 1);
