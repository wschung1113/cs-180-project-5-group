import javax.swing.*;
import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    String message;

    public Client(String message) {
        this.message = message;
    }

    public boolean connect(int option, ArrayList<User> users, Client client) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", 4242);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.flush();
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

        // Send message to the server
        if (option == 0) {
            message = client.message;
        }
        oos.writeObject(message);
        oos.flush(); // ensure data is sent to the server
        boolean loginOption = (boolean) ois.readObject();
        boolean registerOption = (boolean) ois.readObject();



        //Receive
        // I don't know how to return the suitable value based on whether the user is trying to login or register. Any idea?
        if (option == 0) {
            return loginOption;
        } else if (option == 1) {
            return registerOption;
        }

        //Close necessary objects
        oos.close();
        ois.close();

        return false;
    }
}
