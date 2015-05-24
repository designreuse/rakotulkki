ALTER TABLE invoices ADD COLUMN invoice_type enum('NORMAL', 'COMPENSATION') NOT NULL DEFAULT 'NORMAL';

ALTER TABLE customers ADD COLUMN compensation_amount decimal(7,2) DEFAULT NULL;
ALTER TABLE customers ADD COLUMN ssn varchar(11) DEFAULT NULL;
