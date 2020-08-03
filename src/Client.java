import javax.swing.*;
import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {

    public Client() {

    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", 4242);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.flush();
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        LoginGUI loginGUI = new LoginGUI();

        //Close necessary objects
        oos.close();
        ois.close();

        loginGUI.run();


    }
}
