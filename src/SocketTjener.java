import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTjener {

    public static void main(String[]args){
        final int PORT = 1250;
        Socket con = null;

        try {
            ServerSocket server = new ServerSocket(PORT);
            while (true) {
                 con = server.accept();
                Thread threadClient = new TrådKlientTjener(con);
                threadClient.start();
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                con.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }

}
