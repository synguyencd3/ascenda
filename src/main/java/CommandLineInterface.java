import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CommandLineInterface {

    String dateString;

     public void ReadInput()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a date (yyyy-mm-dd): ");
        try {
            // Parse the input string to a LocalDate object
            LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE);
            System.out.println("Input Date: " + date);
        } catch (Exception e) {
            System.out.println("Invalid date format. Please enter a date in the format yyyy-mm-dd.");
        }
        scanner.close();
    }

    public void Run()
    {

    }
}
