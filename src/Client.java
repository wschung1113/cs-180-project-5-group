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

    public boolean connect() throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", 4242);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.flush();
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());



        // Send message to the server
        oos.writeObject(message);
        oos.flush(); // ensure data is sent to the server

        //Receive
        // I don't know how to return the suitable value based on whether the user is trying to login or register. Any idea?
        boolean loginValid = (boolean) ois.readObject();
        boolean registerValid = (boolean) ois.readObject();

        //Close necessary objects
        oos.close();
        ois.close();

        return false;
    }
}
