CREATE TABLE therapists (
  id int(10) unsigned NOT NULL auto_increment,
  email VARCHAR(128) NOT NULL,
  password VARCHAR(128) NOT NULL,
  name varchar(128) NOT NULL,
  street varchar(128) NOT NULL,
  zip varchar(64) NOT NULL,
  city varchar(128) NOT NULL,
  phone varchar(32) NOT NULL,
  iban varchar(32) NOT NULL,
  bic varchar(11) NOT NULL,
  business_identity_code varchar(32) NOT NULL,
  interest_percent int(3) NOT NULL DEFAULT 11,
  term_days int(3) NOT NULL DEFAULT 14,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE customers (
  id int(10) unsigned NOT NULL auto_increment,
  therapist_id int(10) unsigned NOT NULL,
  first_name varchar(128) NOT NULL,
  last_name varchar(128) NOT NULL,
  street varchar(128) NOT NULL,
  city varchar(128) NOT NULL,
  zip varchar(64) NOT NULL,
  price decimal(7,2) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_CUSTOMERS_TO_COMPANIES FOREIGN KEY (therapist_id) REFERENCES therapists (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE sessions (
  id int(10) unsigned NOT NULL auto_increment,
  therapist_id int(10) unsigned NOT NULL,
  customer_id int(10) unsigned NOT NULL,
  session_date datetime NOT NULL,
  start_time time NOT NULL,
  end_time time NOT NULL,
  price decimal(7,2) NOT NULL,
  name varchar(128),
  PRIMARY KEY (id),
  CONSTRAINT FK_SESSIONS_TO_COMPANIES FOREIGN KEY (therapist_id) REFERENCES therapists (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT FK_SESSIONS_TO_CUSTOMERS FOREIGN KEY (customer_id) REFERENCES customers (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE invoices (
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  invoice_number int(10) unsigned DEFAULT NULL,
  therapist_id int(10) unsigned NOT NULL,
  customer_id int(10) unsigned DEFAULT NULL,
  customer_number varchar(32) DEFAULT NULL,
  invoice_date date NOT NULL,
  due_date date NOT NULL,
  name varchar(128) NOT NULL,
  street varchar(128) NOT NULL,
  zip varchar(128) NOT NULL,
  city varchar(128) NOT NULL,
  reference_number varchar(32) DEFAULT NULL,
  term int(10) unsigned DEFAULT NULL,
  extra_text text,
  status enum('NEW','SENT','REMINDER_SENT','PAID','CREDITED','CLOSED','DEBT_COLLECTING') DEFAULT NULL,
  previous_invoice_id int(10) unsigned DEFAULT NULL,
  created datetime(3) NOT NULL,
  PRIMARY KEY (id),
  KEY customer_id (customer_id),
  KEY reference_number (reference_number),
  CONSTRAINT FK_INVOICES_TO_COMPANIES FOREIGN KEY (therapist_id) REFERENCES therapists (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT FK_INVOICES_TO_CUSTOMERS FOREIGN KEY (customer_id) REFERENCES customers (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE invoice_audits (
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  invoice_id int(10) unsigned NOT NULL,
  created datetime(3) NOT NULL,
  status enum('NEW','SENT','REMINDER_SENT','PAID','CREDITED','CLOSED','DEBT_COLLECTING') DEFAULT NULL,
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
  UNIQUE KEY session_id (session_id),
  CONSTRAINT FK_INVOICE_ROWS_TO_INVOICES
  FOREIGN KEY (invoice_id) REFERENCES invoices (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT FK_INVOICE_ROWS_TO_SESSIONS
  FOREIGN KEY (session_id) REFERENCES sessions (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE payments (
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  archive_id varchar(32) DEFAULT NULL,
  therapist_id int(10) unsigned NOT NULL,
  payment_date date NOT NULL,
  reference_number varchar(32) NOT NULL,
  amount decimal(7,2) NOT NULL,
  payer varchar(32) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY archive_id (archive_id),
  KEY reference_number (reference_number),
  KEY payment_date (payment_date),
  CONSTRAINT FK_PAYMENTS_TO_COMPANIES FOREIGN KEY (therapist_id) REFERENCES therapists (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE accounts (
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  therapist_id int(10) unsigned NOT NULL,
  name varchar(128) DEFAULT NULL,
  number int NOT NULL,
  vat int NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_ACCOUNTS_TO_COMPANIES FOREIGN KEY (therapist_id) REFERENCES therapists (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
