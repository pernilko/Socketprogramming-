import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class WebSocket {

    public static void main(String[] args) {
        final int PORT_NUMBER = 8080;

        try {
            ServerSocket socketServer = new ServerSocket(PORT_NUMBER);
            System.out.println("Starting server");
            Socket socket = socketServer.accept();

            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            ArrayList<String> headerLines = new ArrayList<String>();
            String line = br.readLine();
            while (line != null) {
                System.out.println("Klient input: " + line);

                if (line.equals("")) {
                    writer.println("HTTP/1.1 200 OK");
                    writer.println("Content-Type: text/html");
                    writer.println();
                    writer.println("<html><body>");
                    writer.println("<h1>Velkommen til en enkel web-tjener</h1>");
                    writer.println("Header fra klient er: ");
                    writer.println("<ul>");

                    for (int i = 0; i < headerLines.size(); i++) {
                        writer.println("<li>" + headerLines.get(i) + "</li>");
                    }

                    writer.println("</ul>");
                    writer.println("</body></html>");

                    br.close();
                    writer.close();
                    socket.close();
                    return;
                } else {
                    headerLines.add(line);
                }

                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
