ATM Simulator
A simple console-based ATM simulation system built with Java. This project allows users to register, login, deposit, withdraw, check balance, and more.

Features
User Registration and Login

Deposit and Withdrawal functionality

Check Balance

Interaction with a MySQL database for storing user data

PIN-based authentication

Technologies Used
Java (JDK 8+)

MySQL

JDBC for database connection

Setup Instructions
Clone the repository:

bash
Copy
Edit
git clone https://github.com/Kalpanashan74/java-projects.git
Set up MySQL Database:

Create a new database ATMSystem.

Create a users table with columns: id (INT), username (VARCHAR), pin (INT), balance (DOUBLE).

Update the connection details in ATM.java:

Edit the DriverManager.getConnection URL to match your local MySQL setup.

Run the project:

Compile and run the Main.java file:

bash
Copy
Edit
javac Main.java
java Main
Folder Structure
pgsql
Copy
Edit
/ATMSimulator
│
├── ATM.java
├── ATMInterface.java
├── Main.java
└── (SQL schema file)

