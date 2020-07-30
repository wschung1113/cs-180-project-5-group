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
    JPanel newPost;
    JPanel editedPost;
    ArrayList<JPanel> currentPosts = new ArrayList<JPanel>(); //contains all posts, will need to be updated by server at start

    // Text area & fields
    JTextArea contentTextArea;  // write post content here
    JTextField commentTextField;  // write comment content here

    // Combo boxes
    JComboBox<String> privatePublicComboBox;  // may choose the post to be private or public (no functions atm)

    // Labels
    JLabel nameLabel;  // displays username

    // Buttons
    JButton nameButton;  // switches to userHomeContent
    JButton postButton;  // click the button to post
    JButton commentButton;  // switches to commentsContent
    JButton homeButton;  // switches to newsFeedHomeContent
    JButton writeCommentButton;  // "writes" comment in the commentTestField
    JButton editButton;  //allows user to edit a post
    JButton editHomeButton; //allows user to edit post in user profile
    JButton deleteButton; //allows user to delete a post
    JButton deleteHomeButton; //allows user to delete post in user profile

    //for posts
    Post post; //post being written
    Poster poster; //for creating, editing, and deleting posts
    User user = new User("username", "password", "John Doe"); //user making a post

    // Icons
    Icon homeIcon = new ImageIcon("C:\\Users\\Me\\IdeaProjects\\Cs180Proj5Group\\home.png");
    Icon pencilIcon = new ImageIcon("C:\\Users\\Me\\IdeaProjects\\Cs180Proj5Group\\pencil.png");

    @Override
    public void run() {
        // main frame
        JFrame frame = new JFrame();
        frame.setSize(600, 400);
        frame.setTitle("Social Media");

        // edit button for posts
        editButton = new JButton("Edit a Post");
        deleteButton = new JButton("Delete a Post");
        editHomeButton = new JButton("Edit a Post");
        deleteHomeButton = new JButton("Delete a Post");

        // newsFeedHomeContent
        newsFeedHomeContent = new Container();
        //.setLayout(new BorderLayout());
        newsFeedHomeContent.setLayout(new BorderLayout());
        newsFeedHomeContent.setSize(frame.getSize());  // set size of the content equal to that of the frame
        //newsFeedHomeContent.add(new PostGUI(), BorderLayout.CENTER);
        newsFeedHomeContent.add(new PostGUI());

        // panel 1
        JPanel postPanel = new JPanel();
        postPanel.setLayout(new GridLayout(0, 1));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        newsFeedHomeContent.add(postPanel);
        newsFeedHomeContent.add(buttonPanel, BorderLayout.SOUTH);
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


        // userFeedHomeContent
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




//        nameLabel = new JLabel("username");  // should differ by each username
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
//        panel.add(nameLabel);
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
                }

            }
        });

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

        editButton.addActionListener(editAction);
        editHomeButton.addActionListener(editAction);

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
        deleteButton.addActionListener(deleteAction);
        deleteHomeButton.addActionListener(deleteAction);

//        commentButton = new JButton("Comments");
//        commentButton.addActionListener(new ActionListener()
//        {
//            public void actionPerformed(ActionEvent e)
//            {
//                yesNo = (int) JOptionPane.showConfirmDialog(null,
//                        "Would you like to move to comments?", null, JOptionPane.YES_NO_OPTION);
//
//                if (yesNo == YES_OPTION) {
//                    // move to comments
//                }
//            }
//        });
//        commentTextField = new JTextField(30);
//        writeCommentButton = new JButton(pencilIcon);
//        writeCommentButton.setPreferredSize(new Dimension(30, 30));
        panel.add(postButton);
//        panel.add(commentButton);
//        panel.add(commentTextField);
//        panel.add(writeCommentButton);
        postContent.add(panel, BorderLayout.SOUTH);

        frame.add(postContent);


        // final step
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    public PostGUI() {  // constructor
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                curX = e.getX();
                curY = e.getY();
            }
        });
    }

    public static void main(String[] args) {
        // connections

        SwingUtilities.invokeLater(new PostGUI());

    }
}
