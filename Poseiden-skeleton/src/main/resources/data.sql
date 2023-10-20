DROP TABLE IF EXISTS BidList;
DROP TABLE IF EXISTS Trade;
DROP TABLE IF EXISTS CurvePoint;
DROP TABLE IF EXISTS Rating;
DROP TABLE IF EXISTS RuleName;
DROP TABLE IF EXISTS DBUser;

CREATE TABLE BidList (
  BidListId int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  account VARCHAR(30) NOT NULL,
  type VARCHAR(30) NOT NULL,
  bidQuantity DOUBLE,
  askQuantity DOUBLE,
  bid DOUBLE,
  ask DOUBLE,
  benchmark VARCHAR(125),
  bidListDate TIMESTAMP,
  commentary VARCHAR(125),
  security VARCHAR(125),
  status VARCHAR(10),
  trader VARCHAR(125),
  book VARCHAR(125),
  creationName VARCHAR(125),
  creationDate TIMESTAMP,
  revisionName VARCHAR(125),
  revisionDate TIMESTAMP,
  dealName VARCHAR(125),
  dealType VARCHAR(125),
  sourceListId VARCHAR(125),
  side VARCHAR(125),

  PRIMARY KEY (BidListId)
);

CREATE TABLE Trade (
  TradeId int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  account VARCHAR(30) NOT NULL,
  type VARCHAR(30) NOT NULL,
  buyQuantity DOUBLE,
  sellQuantity DOUBLE,
  buyPrice DOUBLE ,
  sellPrice DOUBLE,
  tradeDate TIMESTAMP,
  security VARCHAR(125),
  status VARCHAR(10),
  trader VARCHAR(125),
  benchmark VARCHAR(125),
  book VARCHAR(125),
  creationName VARCHAR(125),
  creationDate TIMESTAMP ,
  revisionName VARCHAR(125),
  revisionDate TIMESTAMP ,
  dealName VARCHAR(125),
  dealType VARCHAR(125),
  sourceListId VARCHAR(125),
  side VARCHAR(125),

  PRIMARY KEY (TradeId)
);

CREATE TABLE CurvePoint (
  Id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  CurveId int,
  asOfDate TIMESTAMP,
  term DOUBLE,
  CurveValue DOUBLE,
  creationDate TIMESTAMP,

  PRIMARY KEY (Id)
);

CREATE TABLE Rating (
  Id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  moodysRating VARCHAR(125),
  sandPRating VARCHAR(125),
  fitchRating VARCHAR(125),
  orderNumber int,

  PRIMARY KEY (Id)
);

CREATE TABLE RuleName (
  Id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(125),
  description VARCHAR(125),
  json VARCHAR(125),
  template VARCHAR(512),
  sqlStr VARCHAR(125),
  sqlPart VARCHAR(125),

  PRIMARY KEY (Id)
);

CREATE TABLE DBUser (
  Id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(125),
  password VARCHAR(125),
  fullname VARCHAR(125),
  role VARCHAR(125),

  PRIMARY KEY (Id)
);

insert into DBUser(id, username, password, fullname, role) values (1, 'User', '$2y$10$.qkbukzzX21D.bqbI.B2R.tvWP90o/Y16QRWVLodw51BHft7ZWbc.', 'USER', 'USER'),
(2, 'Administrator', '$2y$10$kp1V7UYDEWn17WSK16UcmOnFd1mPFVF6UkLrOOCGtf24HOYt8p1iC', 'ADMIN', 'ADMIN');
