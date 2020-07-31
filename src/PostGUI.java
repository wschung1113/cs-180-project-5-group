import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import static javax.swing.JOptionPane.OK_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;
import java.util.ArrayList;

public class PostGUI extends JComponent implements Runnable {
    PostGUI postGUI;  // main post window

    int yesNo;  // takes input of JOptionPane after pressing a button

    int curX;  // current mouse x coordinate for click
    int curY;  // current mouse y coordinate for click

    String postType;  // whether the post is "Private" or "Public"
    String time; //time post was made
    private static final String[] postPrivacyOptions = {"Public", "Private"};

    // Contents
    Container postContent;
    Container newsFeedHomeContent;
    Container userHomeContent;

    // Panels
    JPanel panel;
    JPanel panel1;
    JPanel newPost;
    JPanel editedPost;
    ArrayList<JPanel> currentPosts = new ArrayList<JPanel>(); //contains all posts, will need to be updated by server at start
    JPanel postPanel;
    JPanel buttonPanel;
    JPanel createPostPanel;

    // Text area & fields
    JTextArea contentTextArea;  // write post content here

    // Combo boxes
    JComboBox<String> privatePublicComboBox;  // may choose the post to be private or public (no functions atm)

    // Buttons
    JButton nameButton;  // switches to userHomeContent
    JButton postButton;  // click the button to post
    JButton homeButton;  // switches to newsFeedHomeContent
    JButton editButton;  //allows user to edit a post
    JButton editHomeButton; //allows user to edit post in user profile
    JButton deleteButton; //allows user to delete a post
    JButton deleteHomeButton; //allows user to delete post in user profile
    JButton createPostButton;
    JButton createPostHomeButton;

    //for posts
    Post post; //post being written
    Poster poster; //for creating, editing, and deleting posts
    User user = new User("username", "password", "John Doe"); //user making a post

    // Icons
    Icon homeIcon = new ImageIcon("C:\\Users\\Me\\IdeaProjects\\Cs180Proj5Group\\home.png");
    Icon pencilIcon = new ImageIcon("C:\\Users\\Me\\IdeaProjects\\Cs180Proj5Group\\pencil.png");

    // LoginGUI stuff merged into PostGUI
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
    ArrayList<String> info;
    JFrame loginFrame;
    JFrame frame1;

    ActionListener loginActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == login) {
                info.add(username.getText());
                info.add(password.getText());
            }
            if (e.getSource() == enter) {
                info.add(username.getText());
                info.add(password.getText());
                info.add(alias.getText());
            }
            if (e.getSource() == back) {
                frame1.setVisible(false);
                frame1.dispose();
                run();
            }
            if (e.getSource() == register) {
                loginFrame.setVisible(false);
                loginFrame.dispose();
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
                back.addActionListener(loginActionListener);
                enter.addActionListener(loginActionListener);
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
        JFrame frame = new JFrame();
        frame.setSize(600, 400);
        frame.setTitle("Social Media");

        // buttons for posts
        editButton = new JButton("Edit a Post");
        deleteButton = new JButton("Delete a Post");
        editHomeButton = new JButton("Edit a Post");
        deleteHomeButton = new JButton("Delete a Post");
        createPostButton = new JButton("Post");
        createPostHomeButton = new JButton("Post");

        // newsFeedHomeContent
        newsFeedHomeContent = new Container();
        newsFeedHomeContent.setLayout(new BorderLayout());
        newsFeedHomeContent.setSize(frame.getSize());  // set size of the content equal to that of the frame
        newsFeedHomeContent.add(new PostGUI());

        // add panels to the newsFeedHomeContent
        postPanel = new JPanel();
        postPanel.setLayout(new GridLayout(0, 1));
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        createPostPanel = new JPanel();
        createPostPanel.setLayout(new GridLayout(1, 8));
        for (int i = 0; i < 8; i++) {  // add empty labels to display createPostButton on the top-right corner
            createPostPanel.add(new JLabel());
        }
        createPostPanel.add(createPostButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        newsFeedHomeContent.add(createPostPanel, BorderLayout.NORTH);
        newsFeedHomeContent.add(postPanel);  // CENTER
        newsFeedHomeContent.add(buttonPanel, BorderLayout.SOUTH);

        // add actionListeners to the buttons in newsFeedHomeContent
        ActionListener editAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                yesNo = JOptionPane.showConfirmDialog(null, "Edit post?",
                        null, JOptionPane.YES_NO_OPTION);
                if (yesNo == JOptionPane.YES_OPTION) {

                    postPanel.removeAll();
                    repaint();
                    frame.getContentPane().revalidate();

                    ArrayList<Post> userPosts = poster.readFromFile(user);
                    String[] options = new String[userPosts.size()];
                    int j = 1;
                    for (Post post : userPosts) {
                        options[j - 1] = j + ": " + post.getPostString();
                        j++;
                    }
                    String whichPost =   (String) JOptionPane.showInputDialog(null, "Which post would you like to edit?",
                            "Edit Post", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

                    String option = whichPost.substring(3, whichPost.length());
                    int loc = poster.findPost(user, option);
                    if (loc >= userPosts.size()) {
                        JOptionPane.showMessageDialog(null, "This post is not available for editing!",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        post = null;
                    } else {
                        post = poster.editPost(user, userPosts.get(loc));
                    }
                    if (post != null) {


                        editedPost = new JPanel();
                        editedPost.setLayout(new BorderLayout());

                        LocalDateTime time0 = LocalDateTime.now();
                        String timeString = time0.toString();
                        String[] timeArray = timeString.split("T");
                        String date = timeArray[0];

                        String[] time1 = timeArray[1].split(":");
                        String hour = time1[0];
                        String minute = time1[1];
                        time = date + " " + hour + ":" + minute;

                        String title = user.getUsername() + ":" + user.getAlias() + ":" + time;
                        Border bor = BorderFactory.createTitledBorder(title);
                        JLabel label = new JLabel(post.getPostString());

                        editedPost.setBorder(bor);
                        editedPost.add(label);

                        currentPosts.set(post.getPanelLoc(), editedPost);
                        JPanel currentPanel = new JPanel();
                        currentPanel.setLayout(new GridLayout(0, 1));
                        for (JPanel panel : currentPosts) {
                            currentPanel.add(panel);
                        }
                        postPanel.add(currentPanel);
                        frame.getContentPane().revalidate();
                        repaint();
                    }

                }
            }
        };
        ActionListener deleteAction = new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                yesNo = JOptionPane.showConfirmDialog(null, "Delete post?",
                        null, JOptionPane.YES_NO_OPTION);
                if (yesNo == YES_OPTION) {
                    postPanel.removeAll();
                    ArrayList<Post> userPosts= poster.readFromFile(user);
                    JButton editPostButton;
                    String[] options = new String[userPosts.size()];
                    int j = 1;
                    for (Post post : userPosts) {
                        options[j - 1] = j + ": " + post.getPostString();
                        j++;
                    }
                    String whichPost =   (String) JOptionPane.showInputDialog(null, "Which post would you like to delete?",
                            "Delete Post", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                    String option = whichPost.substring(3, whichPost.length());
                    int loc = poster.findPost(user, option);
                    if (loc >= userPosts.size()) {
                        JOptionPane.showMessageDialog(null, "This post is not available for editing!",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    } else {


                        currentPosts.remove(userPosts.get(loc).getPanelLoc());
                        userPosts.remove(loc);
                        JPanel currentPanel = new JPanel();
                        currentPanel.setLayout(new GridLayout(0, 1));
                        for (JPanel panel : currentPosts) {
                            currentPanel.add(panel);
                        }
                        poster.writeToFile(userPosts);
                        postPanel.add(currentPanel);
                        frame.getContentPane().revalidate();
                        repaint();
                    }

                }
            }
        };
        editButton.addActionListener(editAction);
        editHomeButton.addActionListener(editAction);
        deleteButton.addActionListener(deleteAction);
        deleteHomeButton.addActionListener(deleteAction);
        createPostButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                frame.getContentPane().removeAll();  // or removeAll();

                frame.getContentPane().add(postContent);

                contentTextArea.setText("");

                frame.repaint();

                frame.revalidate();
            }
        });
        createPostHomeButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                frame.getContentPane().removeAll();  // or removeAll();

                frame.getContentPane().add(postContent);

                contentTextArea.setText("");

                frame.repaint();

                frame.revalidate();
            }
        });

        //retrieving previous posts from file

        //get current users here (client)

        /**
         ArrayList<Post> allPosts = new ArrayList<Post>;
         for (User user : users) {
         userPosts = user.getPosts();
         for (Post post : userPosts) {
         allPosts.add(post);
         }
         }
         */

        //add posts to newsfeed here
        frame.add(newsFeedHomeContent);  // first content shown after login is newsFeedHomeContent


        // userHomeContent
        userHomeContent = new Container();
        userHomeContent.setLayout(new BorderLayout());
        userHomeContent.setSize(frame.getSize());  // set size of the content equal to that of the frame
        userHomeContent.add(new PostGUI(), BorderLayout.CENTER);

        // panel 1
        panel = new JPanel();
        userHomeContent.add(panel, BorderLayout.CENTER);
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,2));
        buttonPanel.add(editHomeButton);
        buttonPanel.add(deleteHomeButton);
        createPostPanel = new JPanel();
        createPostPanel.setLayout(new GridLayout(1, 8));
        for (int i = 0; i < 8; i++) {  // add empty labels to display createPostButton on the top-right corner
            createPostPanel.add(new JLabel());
        }
        createPostPanel.add(createPostHomeButton);
        userHomeContent.add(createPostPanel, BorderLayout.NORTH);
        userHomeContent.add(buttonPanel, BorderLayout.SOUTH);


        // postContent
        postContent = new Container();
        postContent.setLayout(new BorderLayout());
        postContent.setSize(frame.getSize());  // set size of the content equal to that of the frame
        postContent.add(new PostGUI(), BorderLayout.CENTER);

        // panel 1
        panel = new JPanel();
        homeButton = new JButton(homeIcon);
        homeButton.setPreferredSize(new Dimension(30, 30));
        homeButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                // send to news feed home
                yesNo = JOptionPane.showConfirmDialog(null, "Go to News Feed Home?",
                        null, JOptionPane.YES_NO_OPTION);

                if (yesNo == YES_OPTION) {
                    frame.getContentPane().removeAll();  // or removeAll();

                    frame.getContentPane().add(newsFeedHomeContent);

                    frame.repaint();

                    frame.revalidate();
                }
            }
        });

        nameButton = new JButton("username");
        nameButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                // send to news feed home
                yesNo = JOptionPane.showConfirmDialog(null, "Go to User Home?",
                        null, JOptionPane.YES_NO_OPTION);

                if (yesNo == YES_OPTION) {
                    frame.getContentPane().removeAll();  // or removeAll();

                    frame.getContentPane().add(userHomeContent);

                    frame.repaint();

                    frame.revalidate();
                }
            }
        });
        privatePublicComboBox = new JComboBox<String>(postPrivacyOptions);
        privatePublicComboBox.setVisible(true);

        panel.add(homeButton);
        panel.add(nameButton);
        panel.add(privatePublicComboBox);
        postContent.add(panel, BorderLayout.PAGE_START);

        // panel 2
        panel = new JPanel();
        contentTextArea = new JTextArea(15, 40);
        panel.add(new JScrollPane(contentTextArea));
        postContent.add(panel, BorderLayout.CENTER);

        // panel 3
        panel = new JPanel();
        postButton = new JButton("Post");
        postButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                yesNo = JOptionPane.showConfirmDialog(null, "Would you like to post?",
                        null, JOptionPane.YES_NO_OPTION);

                if (yesNo == YES_OPTION) {
                    // returns text to somewhere in contentTextArea

                    //this will be commented out until we can get access to user variable
                    poster = new Poster(user);
                    LocalDateTime time0 = LocalDateTime.now();
                    String timeString = time0.toString();
                    String[] timeArray = timeString.split("T");
                    String date = timeArray[0];

                    String[] time1 = timeArray[1].split(":");
                    String hour = time1[0];
                    String minute = time1[1];
                    time = date + " " + hour + ":" + minute;

                    post = poster.createPost(user, contentTextArea.getText(), time, currentPosts.size());
                    newPost = new JPanel();
                    newPost.setLayout(new BorderLayout());

                    String title = user.getUsername() + ":" + user.getAlias() + time;
                    Border bor = BorderFactory.createTitledBorder(title);
                    JLabel label = new JLabel(post.getPostString());
                    newPost.setBorder(bor);
                    newPost.add(label);

                    postPanel.add(newPost);
                    currentPosts.add(newPost);

                    frame.getContentPane().removeAll();  // or removeAll();

                    frame.getContentPane().add(newsFeedHomeContent);

                    frame.repaint();

                    frame.revalidate();
                }

            }
        });

        panel.add(postButton);
        postContent.add(panel, BorderLayout.SOUTH);


        // loginFrame
        loginFrame = new JFrame();
        loginFrame.setSize(450, 100);
        loginFrame.setTitle("Login page");
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        loginFrame.setVisible(true);
        loginPage = loginFrame.getContentPane();
        loginPage.setLayout(new BorderLayout());
        loginPage.setSize(loginFrame.getSize());
        panel = new JPanel();
        panel.add(usernameLabel);
        panel.add(username);
        panel.add(passwordLabel);
        panel.add(password);
        panel1 = new JPanel();
        login.addActionListener(loginActionListener);
        register.addActionListener(loginActionListener);
        enter.addActionListener(loginActionListener);
        panel1.add(login);
        panel1.add(register);
        loginPage.add(panel, BorderLayout.NORTH);
        loginPage.add(panel1, BorderLayout.CENTER);


        // final step
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);  // so that loginPage is default
    }

    public PostGUI() {  // constructor
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                curX = e.getX();
                curY = e.getY();
            }
        });

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
        info = new ArrayList<>();
    }

    public static void main(String[] args) {
        // connections

        SwingUtilities.invokeLater(new PostGUI());

    }
}