import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class socialPostingserver implements Runnable {
    private Socket socket;
    private int id;
    ArrayList<User> allUsersInfo;
    User accessingUser;

    public socialPostingserver(Socket socket, int id) {
        this.socket = socket;
        this.id = id;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        int currentClient = 1;

        //Handle Multiple Clients Simultaneously
        while (true) {

            //Listen for client connection
            System.out.println("Waiting for the client to connect...");
            ServerSocket serverSocket = new ServerSocket(4242);
            Socket socket = serverSocket.accept();

            socialPostingserver server = new socialPostingserver(socket, currentClient);
            //Call {@code run}
            new Thread(server).start();
            System.out.printf("Client %d has connected\n", currentClient);
            currentClient++;
        }


    }

    public boolean validateUser(ArrayList<User> allUsersInfo, String username, String password) {
        for (User user : allUsersInfo) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean validateRegister(ArrayList<User> allUsersInfo,String username, String password, String alias) {
        for (User user : allUsersInfo) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password) && user.getAlias().equals(alias)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();

            ObjectInputStream ois = new ObjectInputStream(inputStream);

            OutputStream outputStream = socket.getOutputStream();

            ObjectOutputStream oos = new ObjectOutputStream(outputStream);

            LoginGUI loginGUI = new LoginGUI();

            // Read message from client
            String serverMessage = (String) ois.readObject();
            String[] userInfoArray = serverMessage.split(",");

            // Store username and password into an array

            //Return response to client
            // validate username and password (return a boolean type to decide to run the PostGUI)
            ArrayList<User> users = loginGUI.readUserInfo();

            boolean loginValid = validateUser(users, userInfoArray[0], userInfoArray[1]);
            boolean registerValid = validateRegister(users, userInfoArray[0], userInfoArray[1], userInfoArray[2]);

            // Write message to client
            oos.writeObject(loginValid);
            oos.writeObject(registerValid);
            oos.flush();


            //Close necessary objects
            ois.close();
            oos.close();



        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
