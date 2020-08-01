import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

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

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            LoginGUI loginGUI = new LoginGUI();

            User user = new User("username", "password"); //

            PostGUI postGUI = new PostGUI();
            String serverMessage;

            // Read message from client

            try {
                String userVerify = reader.readLine();
                // Store username and password into an array
                String[] userLogin = userVerify.split(",");
                //Return response to client
                // validate username and password (return a boolean type to decide to run the PostGUI)
                if (userLogin[0].equals(loginGUI.storeInfo[0]) && userLogin[1].equals(loginGUI.storeInfo[1])) {
                    serverMessage = "Correct\n";
                } else {
                    serverMessage = "Incorrect\n";
                }

                if (userLogin[0].equals(loginGUI.storeInfo[0]) && userLogin[1].equals(loginGUI.storeInfo[1]) && userLogin[2].equals(loginGUI.storeInfo[2])) {
                    serverMessage = "Existed\n";
                } else {
                    serverMessage = "Registered\n";
                }

                // Write message to client
                writer.write(serverMessage);
                writer.flush();
                
            } catch (SocketException e) {
                e.printStackTrace();
            }



           //Close necessary objects
            writer.close();
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
