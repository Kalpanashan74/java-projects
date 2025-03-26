package ATMSimulator;

import java.util.Scanner;

public class ATMInterface {
    private ATM atm;
    private Scanner scanner;
    private String loggedInUser;

    public ATMInterface() {
        atm = new ATM();
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\n==== ATM SYSTEM ====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    System.out.println("Exiting... Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private void register() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter PIN: ");
        int pin = scanner.nextInt();
        scanner.nextLine();
        atm.registerUser(username, pin);
    }

    private void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter PIN: ");
        int pin = scanner.nextInt();
        scanner.nextLine();

        if (atm.authenticateUser(username, pin)) {
            System.out.println("Login successful!");
            loggedInUser = username;
            showUserMenu();
        } else {
            System.out.println("Invalid credentials! Try again.");
        }
    }

    private void showUserMenu() {
        while (true) {
            System.out.println("\n==== ACCOUNT MENU ====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Current Balance: " + atm.getBalance(loggedInUser));
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(loggedInUser, depositAmount);
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    atm.withdrawal(loggedInUser, withdrawAmount);
                    break;
                case 4:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
