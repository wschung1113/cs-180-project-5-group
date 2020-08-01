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
    String message;

    public Client(String message) {
        this.message = message;
    }

    public boolean connect() throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", 4242);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream());

        writer.write(message);
        writer.println();
        writer.flush(); // ensure data is sent to the server

        //Receive
        int c;
        StringBuilder response = new StringBuilder();
        while ((c = reader.read()) != -1) {
            response.append((char) c);
        }

//        System.out.println(response);

        if (response.toString().contains("Correct") || response.toString().contains("Registered")) {
            return true;
        }
        if (response.toString().contains("Incorrect") || response.toString().contains("Existed")) {
            return false;
        }

        writer.close();
        reader.close();
        return false;
    }
}
