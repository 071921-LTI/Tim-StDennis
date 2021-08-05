drop table payment_Information;
drop table users;

create table if not exists users (
	user_ID SERIAL primary key,
	first_Name VARCHAR(20),
	last_Name VARCHAR(20),
	user_Role VARCHAR(20),
	acct_userName VARCHAR(20),
	acct_password VARCHAR(20)
);

create table if not exists payment_Information (
	payment_ID INT primary key,
	owner_Of_Acct SERIAL references users(user_ID),
	account_Number VARCHAR(20),
	exp_Month VARCHAR(2),
	exp_Year VARCHAR(2)
);

create table if not exists bank (
	bank_ID SERIAL primary key,
	account_Number VARCHAR(20),
	exp_Month VARCHAR(2),
	exp_Year VARCHAR(2),
	account_SecurityCode VARCHAR(3),
	account_Funds Money
);

create table if not exists Items (
	item_ID INT primary key,
	item_Name VARCHAR(20),
	item_Description text,
	item_OTPrice Money,
	item_WkPrice Money
);

create table if not exists item_Offers (
	offer_ID SERIAL primary key,
	for_ItemID INT,
	for_ItemName VARCHAR(20),
	offer_From VARCHAR(20),
	offering_To_Pay Money,
	is_Weekly boolean,
	accept_Offer boolean,
	date_Of_Offer DATE,
	date_Of_Accept DATE
);

create table if not exists user_Inventory (
	inventory_ID SERIAL primary key,
	item_ID INT,
	item_Name VARCHAR(20),
	needs_payments boolean,
	payment_Owed Money
)

create view payment_History as
	select 
		offer_ID as "Transaction ID",
		for_ItemName as "Item Name",
		offering_to_Pay as "Payment",
		date_Of_Accept as "Date Paid/Start Of Payment",
		is_Weekly as "Weekly?",
		offer_From as "Payer"
	from 
		item_offers
	where
		accept_Offer = 'ACCEPTED';
	
create view all_Payment_History as
	select 
		offer_ID as "Transaction ID",
		for_ItemName as "Item Name",
		offering_to_Pay as "Payment",
		date_Of_Accept as "Date Paid/Start Of Payment",
		is_Weekly as "Weekly?",
		offer_From as "Payer"
	from 
		item_offers
	where
		accept_Offer = 'ACCEPTED' OR
		accept_Offer = 'REJECTED';
		

