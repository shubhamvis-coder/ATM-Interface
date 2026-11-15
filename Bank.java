
import java.util.HashMap;

public class Bank {

    private HashMap<String, Account> accounts;

    public Bank() {

        accounts = new HashMap<>();

        accounts.put("1001",
            new Account("1001", "1234", "Aarav", 10000));

        accounts.put("1002",
            new Account("1002", "2222", "Diya", 7500));
    }

    public Account getAccount(String userId) {
        return accounts.get(userId);
    }

    public boolean transfer(Account sender,
                            String receiverId,
                            double amount) {

        Account receiver = accounts.get(receiverId);

        if (receiver == null ||
            sender.getUserId().equals(receiverId) ||
            amount <= 0 ||
            amount > sender.getBalance()) {

            return false;
        }

        sender.withdraw(amount);
        receiver.deposit(amount);

        sender.addTransaction(
            new Transaction("TRANSFER", amount,
                "Sent to " + receiverId)
        );

        receiver.addTransaction(
            new Transaction("RECEIVED", amount,
                "From " + sender.getUserId())
        );

        return true;
    }
}
