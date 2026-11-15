
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private String type;
    private double amount;
    private String details;
    private String dateTime;

    public Transaction(String type, double amount,
                       String details) {

        this.type = type;
        this.amount = amount;
        this.details = details;

        dateTime = LocalDateTime.now().format(
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
        );
    }

    public String toString() {

        return dateTime + " | " + type
                + " | Rs. " + amount
                + " | " + details;
    }
}
