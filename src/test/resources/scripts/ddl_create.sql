CREATE TABLE IF NOT EXISTS accounts (
	id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	id_conta VARCHAR(100) NOT NULL,
	saldo DECIMAL(10,2) NOT NULL
);