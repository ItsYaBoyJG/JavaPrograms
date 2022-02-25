import java.io.*;
import java.net.*;

/*
 * Emulates a network exchange that occurs when a user visits a website (in this
case psu.edu) via a web browser

 */
public class simpleNetworkApp {
    public static void main(String[] args) {
        try {
/*
 * URL (Uniform Resource Locator): Scheme (https), Domain Name
(www.psu.edu), if applicable Path
 * URLs are translated to an IP addresses by a DNS (Domain Name
Server), which maps URLs (string names
 * for web resources) to IP addresses (unique numeric identifiers
for web resources)
 */
//STEP 1: Use the URL class represents a URL, in this case
//"https://www.psu.edu"
            URL url = new URL("https://www.psu.edu");

//STEP 2: Use the URLConnection class represents a connection
//between the "simpleNetworkApp" application
//and the URL; the URLConnection class can be used to read
//from/write to the resource
            URLConnection urlConnection = url.openConnection();
//STEP 3: Create an instance of the BufferedReader class to use
//to read text from a character-input
//stream, in this case the web resource ("https://www.psu.edu")
            BufferedReader  bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//STEP 4: Create a string variable to store each line that is
//read from the BufferedReader
            String urlLine;
//STEP 5: Use the BufferedReader to read lines from the web
//resource, looping until there is nothing left
//to read
            while ((urlLine = bufferedReader.readLine()) != null){
//STEP 6: Display the line that the BufferedReader read
//from the web resource, in this case the HTML
                System.out.println(urlLine);
            }

//STEP 7: Close the BufferedReader since there is nothing left to
//read
            bufferedReader.close();


        }
        catch (Exception ex) {
            System.out.println("Exception occurred");
            ex.printStackTrace();
        }
    }
}