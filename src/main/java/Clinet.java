import java.io.*;
import java.net.Socket;

public class Clinet {
    public static void main(String[] args) {
        Socket socket=null;
        try{
            // Kết nối tới server đang lắng nghe trên cổng 3333
            socket = new Socket("localhost",3333);
            System.out.println("Connected to server");

            // Tạo các luồng vào và ra
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(input));
            BufferedWriter write = new BufferedWriter(new OutputStreamWriter(output));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            String mess_Server, mess_Client=null;
            while(true){
                // Gửi dữ liệu tới server
                System.out.print("Client: ");
                String clientMessage= stdIn.readLine(); // nhập dữ liệu
                write.write(clientMessage);
                write.newLine();
                write.flush();
                if (clientMessage.equals("END")) {
                    System.out.println("Client has terminated the connection.");
                    break;
                }

                // phan hoi tu server
                mess_Server=read.readLine();
                if (mess_Server.equals("END")) {
                    System.out.println("Server has terminated the connection.");
                    break;
                }
                System.out.println("Server: " + mess_Server);

            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
