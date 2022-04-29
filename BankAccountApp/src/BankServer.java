public class BankServer {


    private String accountNumber = "";
    private double customerBalance;
    private String customerName = "";
    private String customerEmail = "";
    private String customerPhoneNumber = "";


    public BankServer(){}

    public BankServer(String uAccountNumber, double uCustomerBalance, String uCustomerName, String uCustomerEmail, String uCustomerPhoneNumber) {
       accountNumber = uAccountNumber;
       customerBalance = uCustomerBalance;
       customerName = uCustomerName;
       customerEmail = uCustomerEmail;
       customerPhoneNumber = uCustomerPhoneNumber;
    }

    public String getAccountNumber(){return accountNumber;}

    public void setAccountNumber(){this.accountNumber = accountNumber; }

    public double getCustomerBalance(){return customerBalance;}

    public void setCustomerBalance(double b){this.customerBalance = customerBalance;}

    public String getCustomerName(){return customerName;}

    public void setCustomerName(String name){this.customerName = customerName;}

    public String getCustomerEmail(){return customerEmail;}

    public void setCustomerEmail(){this.customerEmail = customerEmail;}

    public String getCustomerPhoneNumber(){return customerPhoneNumber;}

    public void setCustomerPhoneNumber(){this.customerPhoneNumber = customerPhoneNumber;}


    public void deposit(double depositAmount) {
        this.customerBalance += depositAmount;
        System.out.println("New Balance is: " + depositAmount);
    }

    public void withdrawal(double withdrawalAmount){
        if(this.customerBalance >=0 ){
            this.customerBalance -= withdrawalAmount;
            System.out.println("New balance is:" + withdrawalAmount);
        }

    }
}
