CREATE TABLE inventory
(
  id    LONG NOT NULL,
  name  CHAR(30),
  total INTEGER,
  PRIMARY KEY (id)
);

CREATE TABLE purchase_history
(
  transaction_datetime TIMESTAMP NOT NULL,
  id                   LONG      NOT NULL
)