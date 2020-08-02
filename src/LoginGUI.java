import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.io.*;


public class LoginGUI extends JComponent implements Runnable {

    JButton login; //login button
    JButton register; //Register button
    JButton enter; //Enter button
    JButton back; //Back button
    JLabel usernameLabel;
    JLabel passwordLabel;
    JLabel aliasLabel;
    JTextField username;
    JTextField password;
    JTextField alias;
    Container registerPage;
    Container loginPage;
    String[] storeInfo;
    JFrame frame;
    JFrame frame1;
    ArrayList<User> allUsersInfo;
    User accessingUser;



    public LoginGUI() {
        this.login = new JButton("Login");
        this.register = new JButton("No account? Sign up here");
        this.enter = new JButton("Enter");
        this.back = new JButton("Back");
        this.usernameLabel = new JLabel("Username:");
        this.passwordLabel = new JLabel("Password:");
        this.aliasLabel = new JLabel("Alias:");
        this.username = new JTextField(10);
        this.password = new JTextField(10);
        this.alias = new JTextField(10);
        storeInfo = new String[3];
        allUsersInfo = new ArrayList<>();
        User accessingUser;

    }

    public String[] getInfo() {
        return storeInfo;
    }

    public ArrayList<User> getAllUsersInfo() { return allUsersInfo; }
    public void setAllUsersInfo(ArrayList<User> allUsersInfo) { this.allUsersInfo = allUsersInfo; }

    public void setAccessingUser(User accessingUser) {
        this.accessingUser = accessingUser;
    }

    public User getAccessingUser() {
        return accessingUser;
    }
    public void FindSetUser(ArrayList<User> allUsersInfo, String username){
        accessingUser = new User(null,null,null);
        if (allUsersInfo != null ) {
            for (int i = 0; i < allUsersInfo.size(); i++) {
                if ((allUsersInfo.get(i).getUsername()).equals(username)) {
                    accessingUser = allUsersInfo.get(i);
                }
            }
        }
    }

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == login) {
                Client client = new Client(username.getText() + "," + password.getText());
                try {
                    if (client.connect()) { //Client connects to server, check whether user is registered
                        JOptionPane.showMessageDialog(null, "You are logged in!");
                        FindSetUser(allUsersInfo, username.getText());
                        PostGUI postGUI = new PostGUI(accessingUser);
                        postGUI.run();
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrect username or password");
                    }
                    frame.dispose();
                    frame1.dispose();
                    return;
                } catch (IOException | ClassNotFoundException ioException) {
                    ioException.printStackTrace();
                }
            }
            if (e.getSource() == enter) { //Client connects to server, check whether there is a duplicate user
                Client client = new Client(username.getText() + "," + password.getText() + "," + alias.getText());
                try {
                    if (client.connect()) {
                        JOptionPane.showMessageDialog(null, "Account is successfully created");
                        //Store user's storeInfo
                        storeInfo[0] = username.getText();
                        storeInfo[1] = password.getText();
                        storeInfo[2] = alias.getText();
                        FindSetUser(allUsersInfo, storeInfo[0]);
                        User tempUser= new User(storeInfo[0], storeInfo[1], storeInfo[2]);
                        allUsersInfo.add(tempUser);
                    } else {
                        JOptionPane.showMessageDialog(null, "Account is existed");
                    }
                    frame.dispose();
                    frame1.dispose();
                    return;
                } catch (IOException | ClassNotFoundException ioException) {
                    ioException.printStackTrace();
                }
            }
            if (e.getSource() == back) {
                frame1.setVisible(false);
                frame = new JFrame();
                frame.setSize(450, 100);
                frame.setTitle("Login page");
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                loginPage = frame.getContentPane();
                loginPage.setLayout(new BorderLayout());
                loginPage.setSize(frame.getSize());
                JPanel panel = new JPanel();
                panel.add(usernameLabel);
                panel.add(username);
                panel.add(passwordLabel);
                panel.add(password);
                JPanel panel1 = new JPanel();
                login.addActionListener(actionListener);
                register.addActionListener(actionListener);
                enter.addActionListener(actionListener);
                panel1.add(login);
                panel1.add(register);
                loginPage.add(panel, BorderLayout.NORTH);
                loginPage.add(panel1, BorderLayout.CENTER);
            }
            if (e.getSource() == register) {
                frame.setVisible(false);
                frame1 = new JFrame();
                frame1.setSize(600, 100);
                frame1.setTitle("Sign up page");
                frame1.setLocationRelativeTo(null);
                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame1.setVisible(true);
                registerPage = frame1.getContentPane();
                registerPage.setLayout(new BorderLayout());
                registerPage.setSize(frame1.getSize());
                JPanel panel2 = new JPanel();
                panel2.add(usernameLabel);
                panel2.add(username);
                panel2.add(passwordLabel);
                panel2.add(password);
                panel2.add(aliasLabel);
                panel2.add(alias);
                JPanel panel3 = new JPanel();
                back.addActionListener(actionListener);
                enter.addActionListener(actionListener);
                panel3.add(back);
                panel3.add(enter);

                registerPage.add(panel2, BorderLayout.NORTH);
                registerPage.add(panel3, BorderLayout.CENTER);

            }
        }
    };

    @Override
    public void run() {
        // main frame
        frame = new JFrame();
        frame.setSize(450, 100);
        frame.setTitle("Login page");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        loginPage = frame.getContentPane();
        loginPage.setLayout(new BorderLayout());
        loginPage.setSize(frame.getSize());
        JPanel panel = new JPanel();
        panel.add(usernameLabel);
        panel.add(username);
        panel.add(passwordLabel);
        panel.add(password);
        JPanel panel1 = new JPanel();
        login.addActionListener(actionListener);
        register.addActionListener(actionListener);
        enter.addActionListener(actionListener);
        panel1.add(login);
        panel1.add(register);
        loginPage.add(panel, BorderLayout.NORTH);
        loginPage.add(panel1, BorderLayout.CENTER);
    }

    public boolean validateUser(ArrayList<User> allUsersInfo,String username, String password){
        for (int i = 0; i <allUsersInfo.size() ; i++) {
            if(allUsersInfo.get(i).getUsername().equals(username) && allUsersInfo.get(i).getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new LoginGUI());
    }
}