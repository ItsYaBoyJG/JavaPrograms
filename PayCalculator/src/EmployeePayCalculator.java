import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class EmployeePayCalculator {
    static class Employee {

        String readEmployeeFile(String id,double hours)throws IOException {

            String inLine="";

            String fileOutput="";

            double regularHours=40;
            double regularPay=0;
            double overTimeHours=0;
            double overTimePay=0;
            double totalPay=0;

            FileReader fileReader=new FileReader("Employees.txt");
            BufferedReader bufferedReader=new BufferedReader(fileReader);

            while((inLine=bufferedReader.readLine())!=null) {

                StringTokenizer stringTokenizer=new StringTokenizer(inLine,",");

                while(stringTokenizer.hasMoreTokens()) {

                    String empId=stringTokenizer.nextToken();
                    String lastName=stringTokenizer.nextToken();
                    String firstName=stringTokenizer.nextToken();

                    double payRate=Double.parseDouble(stringTokenizer.nextToken());

                    if(empId.equals(id)) {

                        if(hours > regularHours) {

                            regularPay = (payRate * regularHours);

                            overTimeHours = hours - regularHours;

                            overTimePay=overTimeHours * payRate * 1.5;

                            totalPay = regularPay + overTimePay;
                        }
                        else{
                            totalPay =( hours * payRate);
                        }
                        fileOutput = empId+"\t"+firstName+ " " +lastName+"\t"+regularHours+"\t"+overTimeHours+"\t"
                                +regularPay+"\t"+overTimePay+"\t"+totalPay;
                    }
                }
                return fileOutput;
            }
            return fileOutput;
        }
    }

    public static class EmployeePay{
        static double hours=0;
        static String outPut="";
        private static JTextArea jtArea;

        public static void main(String[] args)throws IOException {
            // creates JFrame instance
            JFrame calculatorFrame = new JFrame("Employee Pay Calculator");
            calculatorFrame.setSize(700,600);
            calculatorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // creates panel inside the calculator frame and adds it to the frame
            JPanel panel = new JPanel();
            calculatorFrame.add(panel);

            // defined method for adding components
            createGuiComponents(panel);

            // Setting the frame visibility to true
            calculatorFrame.setVisible(true);

        }

        private static void createGuiComponents(JPanel panel){
            panel.setLayout(null);


            // creates employee label, sets bounds and adds to the panel
            JLabel employeeLabel = new JLabel("Employee: ");
            employeeLabel.setBounds(10,20,80,25);
            panel.add(employeeLabel);

           /* //error label
            JLabel error = new JLabel("Error");
            error.setBounds(200,400,100,100);
            panel.add(error); */


            // creates employee text field, sets bounds and adds to panel
            JTextField employeeId = new JTextField(20);
            employeeId.setBounds(100,20,165,25);
            panel.add(employeeId);

            // creates pay period label, sets bounds and adds to panel
            JLabel payPeriodLabel = new JLabel("Pay-Period: ");
            payPeriodLabel.setBounds(10,50,80,25);
            panel.add(payPeriodLabel);

            // creates pay period text field, sets bounds and adds to panel
            JTextField payPeriod = new JTextField(20);
            payPeriod.setBounds(100,50,165,25);
            panel.add(payPeriod);

            // creates hours worked label, sets bound and adds to panel
            JLabel hoursWorkedLabel = new JLabel("Hours Worked: ");
            hoursWorkedLabel.setBounds(10,80,80,25);
            panel.add(hoursWorkedLabel);

            // creates hours worked text field, sets bounds and adds to panel
            JTextField hoursId = new JTextField(20);
            hoursId.setBounds(100,80,165,25);
            panel.add(hoursId);

            // creates the clear button for panel, bounds and adds to panel
            JButton clearButton = new JButton("Clear");
            clearButton.setBounds(200,110,100,40);
            panel.add(clearButton);

            clearButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    employeeId.setText("");
                    payPeriod.setText("");
                    hoursId.setText("");
                    panel.repaint();
                }
            });

            // creates the calculate button for panel, sets bounds and adds
            JButton calculateButton = new JButton("Calculate");
            calculateButton.setBounds(10, 110, 100, 40);
            panel.add(calculateButton);

            // creates area for calculation output to be displayed
            jtArea = new JTextArea();
            jtArea.setBounds(10,150,650,400);
            panel.add(jtArea);


            // calculate on press action
            calculateButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    String empId = employeeId.getText().toString();
                    String pay = payPeriod.getText().toString();
                    String hrs = hoursId.getText().toString();
                    hours=Double.parseDouble(hrs);

                    try{

                        Employee obj= new Employee();

                        outPut = obj.readEmployeeFile(empId,hours);

                    }catch(Exception e1){}

                    //System.out.println("----"+empId+"--"+Paytime+"---"+hours+"---"+outPut);

                    jtArea.append("\nID#\tName\tRegular Hours\tOT Hours\tRegular Pay\tOT Pay\tTotal Pay\n" + outPut);
                }
            });
        }
    }
}
