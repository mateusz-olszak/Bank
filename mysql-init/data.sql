create table customers (
	ID int unsigned primary key not null unique auto_increment,
    FIRST_NAME varchar(20),
    LAST_NAME varchar(20),
    PESEL varchar(11) not null unique
);

create table credits (
	CREDIT_ID int unsigned primary key not null unique auto_increment,
    CUSTOMER_ID int not null,
    CREDIT_NAME varchar(255),
    VALUE double
);
