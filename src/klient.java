import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class klient {

    public static void main(String[]args) throws IOException {

        final int PORT = 1250;

        Scanner readInput = new Scanner(System.in);
        String server = "Asus-Zenbook-PK";

        //con to server
        Socket con = new Socket(server, PORT);
        System.out.println("Connection established");

        //Open con for communication with server
        InputStreamReader readCon = new InputStreamReader(con.getInputStream());
        BufferedReader reader = new BufferedReader(readCon);
        PrintWriter writer = new PrintWriter(con.getOutputStream(), true);

        String in1 = reader.readLine();
        String in2 = reader.readLine();
        System.out.println(in1 + "\n" + in2);

        String line = readInput.nextLine();
        while (!line.equals("")){
            writer.println(line);
            String res = reader.readLine();
            System.out.println("From server: " + res);
            line = readInput.nextLine();
        }

        reader.close();
        writer.close();
        con.close();




    }
}
