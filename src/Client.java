import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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

    public User registerlogin(Scanner scanner) {
        while (true) {
            String welcomePrompt = "Welcome to the post app! Would you like to register or login to an account?\n" +
                    "Press 1 to login, press 2 to register";
            System.out.println(welcomePrompt);
            int option = 0;
            do {
                option = scanner.nextInt();
                scanner.nextLine();
                if (option < 1 || option > 2) {
                    System.out.println("Invalid input. Please press 1 to login and 2 to register");
                }
            } while (option < 1 || option > 2);
            if (option == 2) {
                System.out.println("Please enter a username for your account:");
                String username = scanner.nextLine();
                System.out.println("Please enter a password for your account:");
                String password = scanner.nextLine();
                System.out.println("Please enter an alias for your account:");
                String alias = scanner.nextLine();
                User user = new User(username, password, alias);
                users.add(user);
                System.out.println("You are now logged in with the created account");
                return user;
            } else {
                System.out.println("login Username?");
                String username = scanner.nextLine();
                System.out.println("Login Password?");
                String password = scanner.nextLine();
                if (findUser(new User(username, password)) != null) {
                    System.out.println("You have successfully logged in");
                    return findUser(new User(username, password));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Client client = new Client();
        Scanner scanner = new Scanner(System.in);
        User user = client.registerlogin(scanner);
        Socket socket = new Socket("localhost", 4243);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        ArrayList<String> str = handleInput();
        String s = "";
        for (String st : str) {
            s += st + ',';
        }
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
    private static ArrayList<String> handleInput() {
        Scanner s = new Scanner(System.in);
        ArrayList<String> items = new ArrayList<>();
        boolean addMore = true;

        // inventory available
        String[] nikeProducts = {"Nike Boilermakers Tee", "Nike Purdue Shorts", "Nike Swoosh Flex Cap", "Nike Visor"};
        String[] championProducts = {"Purdue Friends Tee", "Champion Hooded Sweatshirt", "Champion Gold Stitched " +
                "Sweatshirt"};
        String[] miscProducts = {"Purdue Wool Socks", "Purdue Ankle Socks", "Purdue Bumper Sticker"};


        do {
            System.out.println("\nSelect brand:\n1) Nike\n2) Champion\n3) Misc");
            int brand = Integer.parseInt(s.nextLine());

            String[] brandArr;
            switch (brand) {
                case 1:
                    brandArr = nikeProducts;
                    break;
                case 2:
                    brandArr = championProducts;
                    break;
                case 3:
                    brandArr = miscProducts;
                    break;
                default:
                    System.out.println("\nThat is not a valid brand!");
                    continue;
            }

            System.out.println("\nChoose a product to add to your order:\n");
            for (int i = 0; i < brandArr.length; i++) {
                String productOption = i + 1 + ") " + brandArr[i];
                System.out.println(productOption);
            }
            int product = Integer.parseInt(s.nextLine()) - 1;

            // check if valid choice
            if (product >= brandArr.length || product < 0) {
                System.out.println("\nThat is not a valid product!");
                continue;
            } else {
                items.add(brandArr[product]);
            }


            System.out.println("\nAdd more items to this order? (yes/no)");
            addMore = s.nextLine().toLowerCase().equals("yes");
        } while (addMore);


        return items;
    }

}
