import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    ArrayList<User> users;

    public Client(ArrayList<User> users) {
        this.users = users;
    }

    public Client() {
        this.users = new ArrayList<>();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public User findUser(User user) {
        for (User user1: users) {
            if (user1.getUsername().equals(user.getUsername()) &&
            user1.getPassword().equals(user.getPassword())) {
                return user1;
            }
        }
        return null;
    }

    public User registerlogin(ArrayList<String> list) {
        if (list != null) {
            if (list.size() == 3) {
                User user = new User(list.get(0), list.get(1), list.get(2));
                users.add(user);
                return user;
            } else if (list.size() == 2) {
                User user = new User(list.get(0), list.get(1));
                if (findUser(user) != null && findUser(user) == user) {
                    return findUser(user);
                }
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Client client = new Client();
        LoginGUI loginGUI = new LoginGUI();
        loginGUI.guicaller(loginGUI);
        ArrayList<String> list = loginGUI.getInfo();
        System.out.println(list);
        User user = client.registerlogin(list);
        Socket socket = new Socket("localhost", 4242);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        String s = user.getUsername() + "," + user.getPassword() + "," + user.getAlias();
        writer.write(s + "\n");
        writer.println();
        writer.flush(); // ensure data is sent to the server

        //Receive
        int c;
        String response = "";
        while ((c = reader.read()) != -1) {
            response += (char) c;
        }
        System.out.println(response);
        writer.close();
        reader.close();
    }
}
