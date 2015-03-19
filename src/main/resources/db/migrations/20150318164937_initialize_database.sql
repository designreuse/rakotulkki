CREATE TABLE customers (
  id int(10) unsigned NOT NULL auto_increment,
  first_name varchar(128) NOT NULL,
  last_name varchar(128) NOT NULL,
  street varchar(128) NOT NULL,
  city varchar(128) NOT NULL,
  zip varchar(64) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE sessions (
  id int(10) unsigned NOT NULL auto_increment,
  customer_id int(10) unsigned NOT NULL,
  start_time datetime NOT NULL,
  end_time datetime NOT NULL,
  price decimal(7,2) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_CUSTOMERS FOREIGN KEY (customer_id) REFERENCES customers (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE invoices (
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  invoice_number int(10) unsigned DEFAULT NULL,
  customer_id int(10) unsigned DEFAULT NULL,
  invoice_date date NOT NULL,
  due date NOT NULL,
  name varchar(128) NOT NULL,
  street varchar(128) NOT NULL,
  zip varchar(128) NOT NULL,
  city varchar(128) NOT NULL,
  reference_number varchar(32) DEFAULT NULL,
  term int(10) unsigned DEFAULT NULL,
  extra_text text,
  status enum('NEW','OPEN','PAID','CREDITED','CLOSED','DEBT_COLLECTING','DISPUTE','CREDIT_LOSS') DEFAULT NULL,
  substatus enum('SENT','REMINDER_SENT') DEFAULT NULL,
  status_message varchar(512) DEFAULT NULL,
  previous_invoice_id int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (id),
  KEY customer_id (customer_id),
  KEY reference_number (reference_number)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE invoice_status (
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  invoice_id int(10) unsigned NOT NULL,
  created datetime(3) NOT NULL,
  status_code enum('NEW','OPEN','PAID','CREDITED','CLOSED','DEBT_COLLECTING','DISPUTE','CREDIT_LOSS') DEFAULT NULL,
  substatus_code enum('SENT','REMINDER_SENT') DEFAULT NULL,
  status_message varchar(512) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY invoice_id (invoice_id),
  CONSTRAINT FK_INVOICE_STATUS
  FOREIGN KEY (invoice_id) REFERENCES invoices (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE invoice_rows (
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  invoice_id int(10) unsigned DEFAULT NULL,
  session_id int(10) unsigned DEFAULT NULL,
  title varchar(255) NOT NULL,
  price decimal(7,2) NOT NULL,
  vat int(2) NOT NULL,
  created datetime(3) NOT NULL,
  PRIMARY KEY (id),
  KEY invoice_id (invoice_id),
  KEY customer_id (customer_id),
  CONSTRAINT FK_INVOICE_ROWS_INVOICE
  FOREIGN KEY (invoice_id) REFERENCES invoices (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT FK_INVOICE_ROWS_SESSION
  FOREIGN KEY (session_id) REFERENCES sessions (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE payments (
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  archive_identity varchar(48) DEFAULT NULL,
  date date NOT NULL,
  reference_number varchar(32) NOT NULL,
  amount decimal(7,2) NOT NULL,
  payer varchar(32) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY archive_identity (archive_identity),
  KEY reference_number (reference_number),
  KEY date (date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
