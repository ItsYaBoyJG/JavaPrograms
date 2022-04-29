import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        BankServer bankServer = new BankServer();

        Scanner scanner = new Scanner(System.in);



        bankServer.setCustomerName(validateStringInput("Enter customer name: ", scanner));
        bankServer.setCustomerBalance(validatePosDoubleInput("Enter "));




    }


    //Method repeatedly displays the prompt until valid positive double input is entered
    public static double validatePosDoubleInput(String prompt, Scanner in) {
        double var = 0.0;
        do {
            System.out.println(prompt);
            while (!in.hasNextDouble()) {
                System.out.println(prompt);
                in.next();
            }
            var = in.nextDouble();
        } while (var < 0);
        return var;
    }

    //Method repeatedly displays the prompt until valid String input is entered
    public static String validateStringInput(String prompt, Scanner in) {
        boolean valid = false;
        String var = "";
        do {
            System.out.println(prompt);
            while (in.hasNextDouble() || in.hasNextInt()) {
                System.out.println(prompt);
                in.nextLine();
            }
            var = in.nextLine();
            valid = true;
        } while (!valid);
        return var;
    }

}
