# Customer Loyalty Marketplace
This is a project implemented as a part of our Database Management Systems course.

We have implemented the project using **Oracle Database** and **Java**.



# Instructions to execute the code:

1. Login/ssh to remote.eos.ncsu.edu:
2. Upload Marketplace.jar, Setup.sql, Populate.sql to your unity account. You can put it in a directory of your choice.
3. Navigate to the directory you have copied everything in.
4. Add Oracle to your environment  add oracle12
5. Run command "sqlplus".
6. Create all the tables, triggers, constraints, procedures using the command: "@Setup.sql" and press Enter.
7. Similarly import all data to the database using: "@Populate.sql" and press Enter.
8. Exit from the sqlplus using: "exit" and press Enter.
9. Run the jar by the command: java -jar Marketplace.jar
10. When you run the jar, you can input the credentials for the account you just ran the scripts on. 
Or, you can just press enter when you are prompted for the input. This will take the default credentials which will point to the database on one of our team member's accounts.
11. You will be prompted if you were logged in successfully, or enter the correct credentials to the database. Or program connects to: "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl01". 
12. Welcome to the marketplace.

# ER Diagram:

![ER Diagram](https://user-images.githubusercontent.com/40118578/146626502-bd250e88-694d-4a40-a45a-e6344b32c4ae.jpg)



# Functionality implemented using Triggers:
## 1.	Update the Tier of the User based on the points earned after each activity
For every tier, a lower bound for the number of points required to be in that tier is being set by the brand. Initially the user is present in one of the Tier, now based on the activities that the user performs, the user earns the points. If the total points earned by the user is greater than the lower bound of the tier ,then the user is updated to the new tier. This functionality is updated using the Trigger TIER_UPDATE_TRIGGER. This trigger is called after insertion of each transaction in the database.
## 2.	Insert into the wallet table, if it is the first transaction of the customer.
The trigger will insert the customer details in the wallet table, if it is the first transaction of the customer. Customer details such as its ID, the loyalty program in which the customer is enrolled and the points received on the transaction. The trigger executed is INSERT_IF_NOT_EXISTS_IN_WALLET. This trigger is executed before the insertion of each transaction in the database.
## 3.	 Update the point for the customer in the wallet based on the transaction done.
This trigger will update the points of the customer in the wallet table. Whenever a customer performs a transaction, the points for the transaction will be updated in the wallet table using this trigger. The trigger executed is  UPDATE_IF_EXISTS_IN_WALLET.
This trigger is executed before the insertion of each transaction in the database.
## 4.	Generate Unique Ids for new activities.
As new activities are created by admin other than mentioned activities like Purchase, Review, Refer, Unique ids for these activities are generated using INSERT_TO_OTHER_ACTVITY trigger which is executed after insertion of new.
## 5.	Generate a unique id for the primary key of WALLET_REWARD_TRANSACTIONS table.
Trigger REWARD_TRANSACTION_ID is generating and assigning new id before insertion of every row in the table.
## 6.	Decrease quantity of particular reward after customer redeem points for that reward.
UPDATE_REWARD_QTY trigger will be executed before insert on the WALLET_REWARD_TRANSACTIONS table.

# Constraints Handled through Database:
1.	CHECK(QUANTITY >= 0) on LP_REWARDS 
To check if the reward quantity entered by the brand is greater than or equal to 0. 
2.	CHECK (AMOUNT>0) on PURCHASE
To check if the purchase amount entered by user is greater than or equal to 0.
3.	CHECK (POINTS BETWEEN 0 AND 99999999) ON WALLET
 To check that the points gained by the customer should be between 0 and 99999999.
4.	Other Primary Key  Constraints:
E.g. Check if a brand has maximum 1 loyalty program.
