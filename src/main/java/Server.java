import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        ServerSocket socket_server = null;
        Socket client = null;
        try {
            // Tạo một ServerSocket lắng nghe trên cổng 3333
            socket_server = new ServerSocket(3333);
            System.out.println("Server is listening on port 3333");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            // Chấp nhận kết nối từ client
            System.out.println("Server is waiting to accept user...");
            client = socket_server.accept();
            System.out.println("Connection from " + client.getInetAddress());

            // Tạo các luồng vào và ra
            InputStream read = client.getInputStream();
            OutputStream write = client.getOutputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(read));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(write));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String mess_Server, mess_Client=null;
            while (true) {
                if(stdIn.equals("END")){
                    break;
                }
                mess_Client= bufferedReader.readLine();
                System.out.println("Client:" + mess_Client);
                mess_Server = stdIn.readLine();
                bufferedWriter.write(mess_Server);
                bufferedWriter.flush();
                System.out.println("Server:" + mess_Server);
                if (mess_Server.equals("END")) {
                    socket_server.close();
                }
            }

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }

    }
}
