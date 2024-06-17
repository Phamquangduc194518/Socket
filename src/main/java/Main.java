public class Main {
    public static void main(String[] args) {
        // Khởi chạy Server
        Thread serverThread = new Thread(() -> {
            Server.main(new String[]{});
        });
        serverThread.start();

        // Khởi chạy Client
        Thread clientThread = new Thread(() -> {
            Clinet.main(new String[]{});
        });
        clientThread.start();
    }
}
