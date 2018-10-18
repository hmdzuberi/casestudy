create table hz_insurance (
	insuranceNo		number	primary key,
	customerName	varchar2(20),
	carNo			varchar2(20),
	age				number,
	maxCoverage		float

);

insert into hz_insurance values (1234, "Hamaad", "UP81AC7331", 8, 0.75);

create table hz_employeeLogin (
	username	varchar2(20)	primary key,
	pass		varchar2(20)

);

create table hz_customerDetails (
	customerNo		number	primary key,
	customerName	varchar2(20),
	phoneNo			float,
	address			varchar2(20)
);

create table hz_carDetails (
	carNo		varchar2(20)	primary key,
	carModel	varchar2(20),
	customerNo	number	references hz_customerDetails(customerNo)

);

create table hz_services (
	serviceId	number	primary key,
	serviceName	varchar2(20),
	amount		float
);

create table hz_serviceLog (
	serviceLogId		number	primary key,
	carNo				varchar2(20),
	serviceName			varchar2(20),
	dateGiven			varchar2(20),
	deliveryDate		varchar2(20),
	insuranceCoverage	float,
	netAmount			float
);

