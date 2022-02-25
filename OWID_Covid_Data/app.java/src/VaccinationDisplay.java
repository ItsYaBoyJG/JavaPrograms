import java.awt.event.*;
import java.io.*;
import javax.swing.*;



public class VaccinationDisplay {
    private static JTextArea displayTextArea;
    static String[] codes = {
            "AFG","ALB","AND","AGO","AIA","ARG","AUS","AUT","AZE","BHS","BHR","BGD","BRD","BLR","BEL",
            "BLZ","BMU","BOL","BRA","BGR","KHM","CAN","CYM","CHL","CHN","COL","CRI","CIV","HRV","CYP",
            "CZE", "DNK", "DMA", "DOM ","ECU ","SLV ", "GNQ", "EST","FRO","FIN","DEU","GHA","GIB","GRC",
            "GRD","GTM","GGY","GUY","HND","HKG","HUN","ISL","IND","IRL","IMN","ISR","ITA","JAM","JPN",
            "JEY","JOR","KAZ","KEN","LVA","LBN","LTU","LUX","MAC","MWI","MYS","MDV","MTL","MEX","MDA",
            "MCO","MNG","MNE","MSR","MAR","MOZ","NLD","NZL","MKD","NOR","OMN","PAN","PRY","PER","PHL",
            "POL","PRT","QAT","ROU","RUS:94","RWA:95","SHN:96","KNA:97","LCA:98","SMR:99","SAU:100","SEN:101","SRB:102","SYC:103","SGP:104",
            "SVK:105","SVN:106","ZAF:107","KOR:108","SSD:109","ESP:110","LKA:111","SUR:112","SWE:113","CHE:114","THA:115","TUN:116","TUR:117","UGA:118","UKR:119","ARE:120","GBR:121",":USA:122","URY:123","VNM:124","ZWE:125"};
            static String outPut = " ";
            /**
             * 
             *  run program here 
             * 
             * 
             *
             * 
             */
            public static void main(String[] args)throws IOException {
                // creates JFrame instance
                JFrame frame = new JFrame("Country Vaccination");
                frame.setSize(1500,900);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
                frame.addWindowListener(new WindowAdapter(){
                    public void windowClosing(WindowEvent windowEvent){
                        System.exit(0);
                    }
                });
    
    
                // creates panel
                JPanel panel = new JPanel();
                frame.add(panel);
    
                // creates area for calculation output to be displayed
                JScrollPane scrollPane = new JScrollPane(displayTextArea);
                //panel.add(scrollPane, BorderLayout.WEST);
                displayTextArea = new JTextArea();
                displayTextArea.setBounds(10,350,1500,500);
                displayTextArea.setEditable(false);
                panel.add(displayTextArea);
    
                // defined method for adding components
                createGuiComponents(panel);
    
                // Setting the frame visibility to true
                frame.setVisible(true);
    
            }
            private static void createGuiComponents(JPanel panel){
                panel.setLayout(null);

                JComboBox comboBox = new JComboBox<>(codes);
                comboBox.setBounds(15, 15, 100, 100);
                panel.add(comboBox);
    
    
                // creates employee label, sets bounds and adds to the panel
                JLabel countryLabel = new JLabel("Country ISO Code: ");
                countryLabel.setBounds(10,10,180,55);
                panel.add(countryLabel);
    


                JTextField countryCodeTextField = new JTextField();
                countryCodeTextField.setBounds(140,20,120,20);
                panel.add(countryCodeTextField);
    
    
                // creates the clear button for panel, bounds and adds to panel
                JButton clearButton = new JButton("Clear");
                clearButton.setBounds(300,210,100,40);
                panel.add(clearButton);
                clearButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panel.repaint();
                        displayTextArea.setText("");
                    }
                });
    
                // creates the display button
                JButton displayButton = new JButton("Display");
                displayButton.setBounds(100, 210, 100, 40);
                panel.add(displayButton);
    
                // display data on press action
                displayButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        int n = Integer.parseInt(countryCodeTextField.getText().trim()); 
                                     
                        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("owid.txt"))){
                            for(int i = 0; i < n +1; i++)
                                bufferedReader.readLine();
                            outPut = bufferedReader.readLine();
                            System.out.println(outPut);
                            displayTextArea.append("||ISO Code||Continent||Location||LastUpdated||Total Vaccinations||People Vaccinated||People fully vaccinated||New Vaccinations||Total Vaccinations Per Hundred||People Vaccinated Per Hundred||People Fully Vaccinated Per Hundred||Brand Name\n" + outPut+" \t ");
                            
                            
                        } catch(Exception e1){
    
                        }
                    }
                });
            }
}
