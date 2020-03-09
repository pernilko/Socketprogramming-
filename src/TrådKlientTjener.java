import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TrådKlientTjener extends Thread {

    private Socket socket;

    public TrådKlientTjener(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStreamReader readerCon = new InputStreamReader(socket.getInputStream());
            BufferedReader reader = new BufferedReader(readerCon);
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            writer.println("Contact with server");
            writer.println("Skriv inn et tall, avslutt med linjeskift");
            Integer number1;
            try {
                number1 = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e) {
                number1 = null;
            }

            writer.println("Skriv inn et tall, avslutt med linjeskift");
            Integer number2;
            try {
                number2 = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e) {
                number2 = null;
            }
            writer.println("Skriv inn en regneoperator (enten pluss eller minus), avslutt med linjeskift");
            String operator = reader.readLine();


            while (number1 != null && number2 != null && operator != null) {
                System.out.println("A client wrote: " + number1 + " " + operator + " " + number2);

                int res;
                if (operator.equals("+")) {
                    res = number1 + number2;
                    writer.println(number1 + " " + operator + " " + number2 + " = " + res +
                            " Press enter to exit, type any number to continue");
                } else if (operator.equals("-")) {
                    res = number1 - number2;
                    writer.println(number1 + " " + operator + " " + number2 + " = " + res +
                            " Press enter to exit, type any number to continue");
                } else {
                    writer.println("Operator error. Write either - or +. Press enter to exit, type any number to try again");
                }

                String skip = reader.readLine();
                writer.println("Nytt regnestykke: Skriv inn et tall, avslutt med linjeskift");
                try {
                    number1 = Integer.parseInt(reader.readLine());
                } catch (NumberFormatException e) {
                    number1 = null;
                }

                writer.println("Skriv inn et tall, avslutt med linjeskift");
                try {
                    number2 = Integer.parseInt(reader.readLine());
                } catch (NumberFormatException e) {
                    number2 = null;
                }
                writer.println("Skriv inn en regneoperator (enten pluss eller minus), avslutt med linjeskift");
                operator = reader.readLine();
            }
            reader.close();
            writer.close();

        }catch (IOException e) {
           e.printStackTrace();
        }
    }
}
