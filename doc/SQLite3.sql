CREATE TABLE staff(
	StaffID INT NOT NULL,
	name STRING(50),
	address VARCHAR(100),
	phone INT,
	gender STRING(10),
	dateOfBirth DATE,
	jobTitle STRING(50),
	salary DOUBLE,
	joinedDate DATE,
	PRIMARY KEY (StaffID)
	);
 
CREATE TABLE customer(
	CustomerID INT NOT NULL,
	name STRING (50),
	address VARCHAR(100),
	phone INT(10),
	email STRING(50),
	PRIMARY KEY (CustomerID)
	);

CREATE TABLE orders(
	OrderID INT NOT NULL,
	CustomerID INT,
	StaffID INT,
	date DATE,
	time TIME,
	PRIMARY KEY (OrderID)
	FOREIGN KEY (CustomerID) REFERENCES customer(CustomerID),
	FOREIGN KEY (StaffID) REFERENCES staff(StaffID)
         );
	
 
CREATE TABLE orderline(
        MealID INT NOT NULL,
        OrderID INT NOT NULL,
        quantity INT,
        PRIMARY KEY (MealID, OrderID)
        );
       /* FOREIGN KEY(MealID) REFERENCES meal(mealID),
        FOREIGN KEY(OrderID) REFERENCES orders(orderID) */
        
        
CREATE TABLE meal(
        MealID INT NOT NULL,
        name STRING(50),
        description STRING (100),
        price DOUBLE,
        totalCalories INT,
        RecipeID INT,
        PRIMARY KEY(MealID),
        FOREIGN KEY(RecipeID) REFERENCES recipe(RecipeID)
	
        );
        
CREATE TABLE mealLine(
        MealID INT NOT NULL,
        MenuID INT NOT NULL,
        quantity INT,
        PRIMARY KEY(MealID,MenuID)
        );
        /* FOREIGN KEY(MealID) REFERENCES meal(MealID),
        FOREIGN KEY(MenuID) REFERENCES menu(MenuID) */
        
        
CREATE TABLE menu(
        MenuID INT NOT NULL,
        menuName STRING(50),
        startTime TIME,
        endTime TIME,
        season STRING(100),
        PRIMARY KEY (MenuID)
        );
        
CREATE TABLE recipe(
        RecipeID INT NOT NULL,
        cookingMethod STRING(50),
        preparatingMethod STRING(50),
        PRIMARY KEY (RecipeID)
        );

CREATE TABLE recipeLine(
        RecipeID INT NOT NULL,
        IngredientID INT NOT NULL,
        quantity INT,
        PRIMARY KEY (RecipeID,IngredientID)
        );
        /* FOREIGN KEY(RecipeID) REFERENCES recipe(RecipeID),
        FOREIGN KEY(IngredientID) REFERENCES ingredient(IngredientID) */

CREATE TABLE ingredient(
        IngredientID INT NOT NULL,
        name STRING(50),
        onHand INT,
        unitOfMeasure STRING(10),
        caloriesPerUnit INT,
	reOrderPoint INT,
        PRIMARY KEY (IngredientID)
        );

CREATE TABLE bill(
        BillID INT NOT NULL,
        OrderID INT NOT NULL,
        billDate DATE,
        billTime TIME,
        amount DOUBLE,
        PRIMARY KEY (BillID),
        FOREIGN KEY (OrderID) REFERENCES orders(OrderID)
        );
