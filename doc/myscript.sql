CREATE SCHEMA restaurant_schema

CREATE TABLE restaurant_schema.customer(
	CustomerID INT NOT NULL,
	firstName VARCHAR(255),
	lastName VARCHAR(255),
	customerAdress VARCHAR(255),
	customerPhone VARCHAR(255),
	customerEmail VARCHAR(255),
	PRIMARY KEY (CustomerID)
	);

CREATE TABLE restaurant_schema.manager(
	ManagerID INT NOT NULL,
	annualCarAllowence FLOAT,
	monthlyBonus FLOAT,
	PRIMARY KEY (ManagerID)
	);

CREATE TABLE restaurant_schema.nextOfKin(
	StaffID INT NOT NULL,
	firstName VARCHAR(255),
	lastName VARCHAR(255),
	address VARCHAR(255),
	phone INT,
	relationshipToStaff VARCHAR(255),
	PRIMARY KEY (StaffID)
	);

CREATE TABLE restaurant_schema.staff(
	StaffID INT NOT NULL,
	firstName VARCHAR(255),
	lastName VARCHAR(255),
	address VARCHAR(255),
	phone INT,
	gender VARCHAR(255),
	dateOfBirth DATE,
	jobTitle VARCHAR(255),
	salary FLOAT,
	joinedDate DATE,
	PRIMARY KEY (StaffID)
	);

CREATE TABLE restaurant_schema.orders(
	OrderID INT NOT NULL,
	CustomerID_FK INT NOT NULL,
	StaffID_FK INT NOT NULL,
	orderDate DATE,
	orderTime TIME,
	PRIMARY KEY (OrderID),
	FOREIGN KEY (CustomerID_FK) REFERENCES restaurant_schema.customer(CustomerID),
	FOREIGN KEY (StaffID_FK) REFERENCES restaurant_schema.staff(StaffID)
	);

CREATE TABLE restaurant_schema.orderline(
);

CREATE TABLE restaurant_schema.meal(
	MealID INT NOT NULL, 
	RecipeID_FK INT NOT NULL,
	name VARCHAR(255),
	description VARCHAR(255),
	price FLOAT,
	totalCalories INT,
	PRIMARY KEY (MealID),
	FOREIGN KEY (RecipeID_FK) REFERENCES restaurant_schema.recipe(RecipeID)
	);
	
CREATE TABLE restaurant_schema.recipe(
	RecipeID INT NOT NULL,
	cookingMethod VARCHAR(255),
	preparationMethod VARCHAR(255),
	PRIMARY KEY (RecipeID)
	);

CREATE TABLE restaurant_schema.bill(
	BillID INT NOT NULL,
	OrderID_FK INT NOT NULL,
	billDate DATE,
	billTime TIME,
	amount FLOAT,
	size INT,
	PRIMARY KEY (BillID),
	FOREIGN KEY (OrderID_FK) REFERENCES restaurant_schema.orders(OrderID)
	);
	
