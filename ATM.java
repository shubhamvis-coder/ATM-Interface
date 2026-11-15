
import java.util.Scanner;

public class ATM {

    private Bank bank;
    private Scanner sc;

    public ATM() {
        bank = new Bank();
        sc = new Scanner(System.in);
    }

    public void start() {

        System.out.println("===== WELCOME TO ATM =====");

        Account account = login();

        if (account == null) {
            System.out.println("Access Denied!");
            return;
        }

        System.out.println("Welcome " + account.getHolderName());

        boolean running = true;

        while (running) {

            System.out.println("\n===== ATM MENU =====");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Balance Check");
            System.out.println("6. Quit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    account.showTransactionHistory();
                    break;

                case 2:
                    withdraw(account);
                    break;

                case 3:
                    deposit(account);
                    break;

                case 4:
                    transfer(account);
                    break;

                case 5:
                    System.out.println("Balance: Rs. "
                            + account.getBalance());
                    break;

                case 6:
                    System.out.println("Thank you!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    private Account login() {

        for (int i = 1; i <= 3; i++) {

            System.out.print("Enter User ID: ");
            String id = sc.next();

            System.out.print("Enter PIN: ");
            String pin = sc.next();

            Account account = bank.getAccount(id);

            if (account != null && account.checkPin(pin)) {
                return account;
            }

            System.out.println("Incorrect details. Attempts left: "
                    + (3 - i));
        }

        return null;
    }

    private void withdraw(Account account) {

        System.out.print("Enter amount: ");
        double amount = sc.nextDouble();

        if (account.withdraw(amount)) {

            account.addTransaction(
                new Transaction("WITHDRAW", amount, "Cash withdrawal")
            );

            System.out.println("Withdrawal Successful!");

        } else {
            System.out.println("Invalid amount or Insufficient Funds!");
        }
    }

    private void deposit(Account account) {

        System.out.print("Enter amount: ");
        double amount = sc.nextDouble();

        if (account.deposit(amount)) {

            account.addTransaction(
                new Transaction("DEPOSIT", amount, "Cash deposit")
            );

            System.out.println("Deposit Successful!");

        } else {
            System.out.println("Invalid Amount!");
        }
    }

    private void transfer(Account account) {

        System.out.print("Enter Receiver ID: ");
        String receiverId = sc.next();

        System.out.print("Enter amount: ");
        double amount = sc.nextDouble();

        if (bank.transfer(account, receiverId, amount)) {
            System.out.println("Transfer Successful!");
        } else {
            System.out.println("Transfer Failed!");
        }
    }
}
