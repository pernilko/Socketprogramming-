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
            System.out.println("Starter server: ");
            Socket socket = socketServer.accept();

            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

            ArrayList<String> headerLines = new ArrayList<String>();
            String aLine = bufferedReader.readLine();
            while (aLine != null) {
                System.out.println("Klient skrev: " + aLine);

                if (aLine.equals("")) {
                    printWriter.println("HTTP/1.1 200 OK");
                    printWriter.println("Content-Type: text/html");
                    printWriter.println();
                    printWriter.println("<html><body>");
                    printWriter.println("<h1>Hei og velkommen til en enkel webtjener</h1>");
                    printWriter.println("Header fra klient er: ");
                    printWriter.println("<ul>");

                    for (int i = 0; i < headerLines.size(); i++) {
                        printWriter.println("<li>" + headerLines.get(i) + "</li>");
                    }

                    printWriter.println("</ul>");
                    printWriter.println("</body></html>");

                    bufferedReader.close();
                    printWriter.close();
                    socket.close();
                    return;
                } else {
                    headerLines.add(aLine);
                }

                aLine = bufferedReader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
