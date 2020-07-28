import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocialPostingServer implements Runnable {
    private Socket socket;
    private int id;

    public SocialPostingServer(Socket socket, int id) {
        this.socket = socket;
        this.id = id;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(4242);
        int currentClient = 1;

        //Handle Multiple Clients Simultaneously
        while (true) {

            //Listen for client connection
            System.out.println("Waiting for the client to connect...");
            Socket socket = serverSocket.accept();

            SocialPostingServer server = new SocialPostingServer(socket, currentClient);
            //Call {@code run}
            new Thread(server).start();
            System.out.printf("Client %d has connected\n", currentClient);
            currentClient++;
        }


    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();

            ObjectInputStream ois = new ObjectInputStream(inputStream);

            OutputStream outputStream = socket.getOutputStream();

            ObjectOutputStream oos = new ObjectOutputStream(outputStream);

            //Read request from client



            oos.flush();

            //Close necessary objects
            ois.close();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

