import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.time.*;

import static javax.swing.JOptionPane.OK_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;

import java.util.ArrayList;
/// I pushed this class

public class PostGUI extends JComponent implements Runnable {
    public PostGUI(User user) {  // constructor
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
        this.user = user;
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
    int likes = 0;

    // Panels
    JPanel panel;
    JPanel panel1;
    JPanel newPost;
    JPanel editedPost;
    ArrayList<JPanel> currentPosts = new ArrayList<JPanel>(); //contains all posts, will need to be updated by server at start
    JPanel postPanel;
    JPanel buttonPanel;
    JPanel createPostPanel;
    JPanel buttonHomePanel;
    JPanel createPostHomePanel;
    JLabel label1;

    int numcom = 0;
    ArrayList<Comment> commentsOnPostList = new ArrayList<>();
    ArrayList<JButton> commentLikeButton = new ArrayList<>();
    ArrayList<JButton> commentEditButton = new ArrayList<>();
    ArrayList<JButton> commentDeleteButton = new ArrayList<>();


    ArrayList<String> likeOnComments = new ArrayList<>();
    //int i=0;
    JButton templikeButton;
    JButton tempeditButton;
    JButton tempdeleteButton;
    JButton combutton;

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
    JButton commentButton;
    JButton commentHomeButton;

    //for posts
    User user = new User("default", "default", "default"); //default
    //User user;
    Post post; //post being written
    Poster poster; //for creating, editing, and deleting posts

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
    JTextField comfield;
    Container registerPage;
    Container loginPage;
    ArrayList<String> info;
    JFrame loginFrame;
    JFrame frame1;
    JFrame frame;

    int numlikes = 0;
    ArrayList<Post> allPosts = new ArrayList<>();
    ArrayList<Post> userPosts = new ArrayList<>();

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
        frame = new JFrame();
        frame.setSize(600, 400);
        frame.setTitle("Social Media");
        poster = new Poster(user);

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
        comfield = new JTextField();
        commentButton = new JButton("Comment");

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
        createPostPanel.add(comfield);
        createPostPanel.add(createPostButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(commentButton);
        newsFeedHomeContent.add(createPostPanel, BorderLayout.NORTH);
        newsFeedHomeContent.add(postPanel);  // CENTER
        newsFeedHomeContent.add(buttonPanel, BorderLayout.SOUTH);


        allPosts = poster.readAll();

        if (allPosts != null) {

            for (Post post : allPosts) {
                post.setPanelLoc(currentPosts.size());

                JPanel panel = new JPanel();
                LocalDateTime time0 = post.getTime0();
                String timeString = post.getTime();
                newPost = new JPanel();
                newPost.setLayout(new BorderLayout());
                User user1 = post.getUser();
                if (user1.getUsername().equals(user.getUsername())) {
                    userPosts.add(post);
                }
                poster.writeToFile(userPosts);

                String title = user1.getUsername() + ":" + user1.getAlias() + timeString;
                Border bor = BorderFactory.createTitledBorder(title);
                JLabel label = new JLabel(post.getPostString());


                JButton combutton = new JButton("Comment");
                int likes;
                combutton.setPreferredSize(new Dimension(100, 20));
                //combutton.addActionListener(combuttonActionListener);
                newPost.setBorder(bor);
                newPost.add(label);
                JPanel commentPanel = new JPanel(new GridLayout(0, 1));
                JPanel newCom = new JPanel();
                newCom.setLayout(new FlowLayout());
                newCom.add(comfield);
                newCom.add(combutton);
                commentPanel.add(newCom);
                postPanel.add(newPost);
                currentPosts.add(newPost);

                newPost.add(commentPanel, BorderLayout.SOUTH);
                //add existing comments here instead
            }
            poster.writeToFile(userPosts);
            user.setPosts(userPosts);

            newsFeedHomeContent.add(postPanel);
        } else {
            allPosts = new ArrayList<Post>();
        }
        frame.add(newsFeedHomeContent);  // first content shown after login is newsFeedHomeContent


        ActionListener commentAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                yesNo = JOptionPane.showConfirmDialog(null, "Comment on post?",
                        null, JOptionPane.YES_NO_OPTION);
                if (yesNo == JOptionPane.YES_OPTION) {
                    userPosts = poster.readFromFile(user);
                    allPosts = poster.readAll();

                    if (allPosts == null || allPosts.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Error! No posts available for commenting on.",
                                null, JOptionPane.ERROR_MESSAGE);
                    } else {
                        String[] options = new String[allPosts.size()];
                        int j = 1;
                        for (Post post : allPosts) {
                            options[j - 1] = j + ": " + post.getPostString();
                            j++;
                        }
                        String whichPost = (String) JOptionPane.showInputDialog(null, "Which post would you like to comment on?",
                                "Comment on post", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

                        if (whichPost != null) {
                            postPanel.removeAll();
                            repaint();
                            frame.getContentPane().revalidate();

                            String option = whichPost.substring(3, whichPost.length());
                            int loc = poster.findPost(user, option, 0);
                            int loc1 = poster.findPost(user, option, 1);
                            post = user.getPosts().get(loc); //userPosts.get(loc);

                            //post = poster.editPost(user, userPosts.get(loc));
                            editedPost = new JPanel();
                            editedPost.setLayout(new BorderLayout());
                            String commentString = JOptionPane.showInputDialog(null, "Enter your Comment.",
                                    "Comment", JOptionPane.QUESTION_MESSAGE);
                            JLabel commentlabel = new JLabel(user.getAlias() + ":" + commentString);

                            editedPost = new JPanel();
                            editedPost.setLayout(new BorderLayout());
                            LocalDateTime time0 = post.getTime0();
                            String timeString = time0.toString();
                            String[] timeArray = timeString.split("T");
                            String date = timeArray[0];
                            String[] time1 = timeArray[1].split(":");
                            String hour = time1[0];
                            String minute = time1[1];
                            time = date + " " + hour + ":" + minute;



                            JLabel templabel = new JLabel();
                            String title = post.getUser().getUsername() + ":" + post.getUser().getAlias() + ":" + time;
                            Border bor = BorderFactory.createTitledBorder(title);
                            JLabel label = new JLabel(post.getPostString());
                            tempeditButton = new JButton("Edit");
                            editedPost.setBorder(bor);
                            editedPost.add(label);
                            if (!post.getAllComments().isEmpty()) {
                                for (int i = 0; i < post.getAllComments().size(); i++) {
                                    templabel.setText(post.getAllComments().get(i).getCommentername() + ": " + post.getAllComments().get(i).getComtext());
                                    editedPost.add(templabel);
                                }
                            }
                            Comment tempcomment = new Comment(user.getAlias(), commentString, 0, time, 0);
                            ArrayList<Comment> tempCommentArrayList = new ArrayList<>();
                            tempCommentArrayList = post.getAllComments();
                            tempCommentArrayList.add(tempcomment);
                            post.setAllComments(tempCommentArrayList);
                            editedPost.add(commentlabel, BorderLayout.SOUTH);
                            //editedPost.add(tempeditButton);

                            currentPosts.set(post.getPanelLoc(), editedPost);
                            userPosts.set(loc, post);
                            allPosts.set(loc1, post);
                            poster.writeAll(allPosts);
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
            }

        };

        ActionListener editCommentAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                yesNo = JOptionPane.showConfirmDialog(null, "Edit Comment?",
                        null, JOptionPane.YES_NO_OPTION);
                if (yesNo == JOptionPane.YES_OPTION) {
                    userPosts = poster.readFromFile(user);

                    if (userPosts.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Error! No posts available for editing",
                                null, JOptionPane.ERROR_MESSAGE);
                    } else {
                        String[] options = new String[userPosts.size()];
                        int j = 1;
                        for (Post post : userPosts) {
                            options[j - 1] = j + ": " + post.getPostString();
                            j++;
                        }
                        String whichPost = (String) JOptionPane.showInputDialog(null, "Which post's comment would you like to edit?",
                                "Edit Post's comment", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                        if (whichPost != null) {
                            postPanel.removeAll();
                            repaint();
                            frame.getContentPane().revalidate();

                            String option = whichPost.substring(3, whichPost.length());
                            int loc = poster.findPost(user, option, 0);
                            int loc1 = poster.findPost(user, option, 1);
                            post = user.getPosts().get(loc);
                            if (post.getAllComments().size() == 0) {
                                JOptionPane.showMessageDialog(null, "Error! There are no comments on this post to edit.",
                                        null, JOptionPane.ERROR_MESSAGE);
                            } else {
                                String[] options1 = new String[post.getAllComments().size()];
                                //int k = 1;
                                for (int k = 1; k < post.getAllComments().size(); k++) {
                                    options1[k - 1] = k + ": " + post.getAllComments().get(k - 1).getCommentername();
                                    k++;
                                }
                                String whichComment = (String) JOptionPane.showInputDialog(null, "Which comment would you like to edit?",
                                        "Edit Post", JOptionPane.PLAIN_MESSAGE, null, options1, options1[0]);

                                int indexOfComment = 77;
                                boolean trueUser = true;
                                for (int i = 0; i < post.getAllComments().size(); i++) {
                                    if (post.getAllComments().get(i).getComtext().equals(whichComment)) {
                                        if (!user.getAlias().equals(post.getAllComments().get(i).getCommentername())) {
                                            trueUser = false;

                                        } else {
                                            indexOfComment = i;
                                        }

                                    }
                                }
                                if (trueUser) {
                                    String newString = (String) JOptionPane.showInputDialog(null, "Enter edited String",
                                            "Edit Post", JOptionPane.PLAIN_MESSAGE, null, options1, options1[0]);
                                    post.getAllComments().get(indexOfComment).setComtext(newString);

//                                    currentPosts.set(post.getPanelLoc(), editedPost);
//                                    userPosts.set(loc, post);
//                                    allPosts.set(loc1, post);
                                    poster.writeAll(allPosts);
                                    JPanel currentPanel = new JPanel();
                                    currentPanel.setLayout(new GridLayout(0, 1));
                                    for (JPanel panel : currentPosts) {
                                        currentPanel.add(panel);
                                    }
                                    postPanel.add(currentPanel);
                                    frame.getContentPane().revalidate();
                                    repaint();
                                } else {
                                    JOptionPane.showMessageDialog(null, "This Comment is not available for editing!",
                                            "Error", JOptionPane.ERROR_MESSAGE);
                                }

                            }

                        }
                    }

                }
            }


        };
        ActionListener deleteCommentAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                yesNo = JOptionPane.showConfirmDialog(null, "Delete Comment?",
                        null, JOptionPane.YES_NO_OPTION);
                if (yesNo == JOptionPane.YES_OPTION) {
                    userPosts = poster.readFromFile(user);

                    if (userPosts.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Error! No posts available for editing",
                                null, JOptionPane.ERROR_MESSAGE);
                    } else {
                        String[] options = new String[userPosts.size()];
                        int j = 1;
                        for (Post post : userPosts) {
                            options[j - 1] = j + ": " + post.getPostString();
                            j++;
                        }
                        String whichPost = (String) JOptionPane.showInputDialog(null, "Which post's comment would you like to delete?",
                                "Edit Post's comment", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                        if (whichPost != null) {
                            postPanel.removeAll();
                            repaint();
                            frame.getContentPane().revalidate();

                            String option = whichPost.substring(3, whichPost.length());
                            int loc = poster.findPost(user, option, 0);
                            int loc1 = poster.findPost(user, option, 1);
                            post = user.getPosts().get(loc);
                            if (post.getAllComments().size() == 0) {
                                JOptionPane.showMessageDialog(null, "Error! There are no comments on this post to edit.",
                                        null, JOptionPane.ERROR_MESSAGE);
                            } else {
                                String[] options1 = new String[post.getAllComments().size()];
                                //int k = 1;
                                for (int k = 1; k < post.getAllComments().size(); k++) {
                                    options1[k - 1] = k + ": " + post.getAllComments().get(k - 1).getCommentername();
                                    k++;
                                }
                                String whichComment = (String) JOptionPane.showInputDialog(null, "Which comment would you like to edit?",
                                        "Edit Post", JOptionPane.PLAIN_MESSAGE, null, options1, options1[0]);

                                int indexOfComment = 77;
                                boolean trueUser = true;
                                for (int i = 0; i < post.getAllComments().size(); i++) {
                                    if (post.getAllComments().get(i).getComtext().equals(whichComment)) {
                                        if (!user.getAlias().equals(post.getAllComments().get(i).getCommentername())) {
                                            trueUser = false;

                                        } else {
                                            indexOfComment = i;
                                        }

                                    }
                                }
                                if (trueUser) {

                                    post.getAllComments().remove(indexOfComment);

//                                    currentPosts.set(post.getPanelLoc(), editedPost);
//                                    userPosts.set(loc, post);
//                                    allPosts.set(loc1, post);
                                    poster.writeAll(allPosts);
                                    JPanel currentPanel = new JPanel();
                                    currentPanel.setLayout(new GridLayout(0, 1));
                                    for (JPanel panel : currentPosts) {
                                        currentPanel.add(panel);
                                    }
                                    postPanel.add(currentPanel);
                                    frame.getContentPane().revalidate();
                                    repaint();
                                } else {
                                    JOptionPane.showMessageDialog(null, "This Comment is not available for deletion!",
                                            "Error", JOptionPane.ERROR_MESSAGE);
                                }

                            }

                        }
                    }

                }
            }


        };

        ActionListener editAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                yesNo = JOptionPane.showConfirmDialog(null, "Edit post?",
                        null, JOptionPane.YES_NO_OPTION);
                if (yesNo == JOptionPane.YES_OPTION) {
                    userPosts = poster.readFromFile(user);

                    if (userPosts == null || userPosts.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Error! No posts available for editing",
                                null, JOptionPane.ERROR_MESSAGE);
                    } else {
                        String[] options = new String[userPosts.size()];
                        int j = 1;
                        for (Post post : userPosts) {
                            options[j - 1] = j + ": " + post.getPostString();
                            j++;
                        }
                        String whichPost = (String) JOptionPane.showInputDialog(null, "Which post would you like to edit?",
                                "Edit Post", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

                        if (whichPost != null) {
                            postPanel.removeAll();
                            repaint();
                            frame.getContentPane().revalidate();

                            String option = whichPost.substring(3, whichPost.length());
                            int loc = poster.findPost(user, option, 0);
                            int loc1 = poster.findPost(user, option, 1);

                            if (loc >= userPosts.size()) {
                                JOptionPane.showMessageDialog(null, "This post is not available for editing!",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                                post = null;
                            } else {
                                post = poster.editPost(user, userPosts.get(loc));
                            }

                            if (post != null) {

                                userPosts.set(loc, post);
                                allPosts.set(loc1, post);
                                poster.writeAll(allPosts);
                                ArrayList<JPanel> resetPosts = new ArrayList<JPanel>();
                                for (Post post : allPosts) {
                                    String timeString = post.getTime();
                                    newPost = new JPanel();
                                    newPost.setLayout(new BorderLayout());
                                    User user1 = post.getUser();
                                    String title = user1.getUsername() + ":" + user1.getAlias() + timeString;
                                    Border bor = BorderFactory.createTitledBorder(title);
                                    JLabel label = new JLabel(post.getPostString());
                                    //JTextField comfield = new JTextField();
                                   // comfield.setPreferredSize(new Dimension(350, 20));
                                   // JButton combutton = new JButton("Comment");
                                    //int likes;
                                   // combutton.setPreferredSize(new Dimension(100, 20));
                                    //combutton.addActionListener(combuttonActionListener);
                                    newPost.setBorder(bor);
                                    newPost.add(label);
                                    //JPanel commentPanel = new JPanel(new GridLayout(0,1));
                                   // JPanel newCom = new JPanel();
                                    //newCom.setLayout(new FlowLayout());
                                    //newCom.add(comfield);
                                    //newCom.add(combutton);
                                   // commentPanel.add(newCom);
                                    postPanel.add(newPost);

                                    //newPost.add(commentPanel, BorderLayout.SOUTH);
                                    resetPosts.add(newPost);
                                }
                                currentPosts = resetPosts;
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
                }
            }
        };

        ActionListener deleteAction = new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                yesNo = JOptionPane.showConfirmDialog(null, "Delete post?",
                        null, JOptionPane.YES_NO_OPTION);

                if (yesNo == YES_OPTION) {

                    userPosts = poster.readFromFile(user);

                    if (userPosts == null || userPosts.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Error! No posts available for deleting",
                                null, JOptionPane.ERROR_MESSAGE);
                    } else {
                        String[] options = new String[userPosts.size()];
                        int j = 1;
                        for (Post post : userPosts) {
                            options[j - 1] = j + ": " + post.getPostString();
                            j++;
                        }
                        String whichPost = (String) JOptionPane.showInputDialog(null, "Which post would you like to delete?",
                                "Delete Post", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                        if (whichPost != null) {
                            String option = whichPost.substring(3, whichPost.length());
                            int loc = poster.findPost(user, option, 0);
                            int loc1 = poster.findPost(user, option, 1);
                            if (loc >= userPosts.size()) {
                                JOptionPane.showMessageDialog(null, "This post is not available for deleting!",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                postPanel.removeAll();
                                userPosts.remove(loc);
                                allPosts.remove(loc1);
                                poster.writeAll(allPosts);
                                poster.writeToFile(userPosts);
                                ArrayList<JPanel> resetPosts = new ArrayList<JPanel>();
                                for (Post post : allPosts) {
                                    LocalDateTime time0 = post.getTime0();
                                    String timeString = post.getTime();
                                    newPost = new JPanel();
                                    newPost.setLayout(new BorderLayout());
                                    User user1 = post.getUser();
                                    String title = user1.getUsername() + ":" + user1.getAlias() + timeString;
                                    Border bor = BorderFactory.createTitledBorder(title);
                                    JLabel label = new JLabel(post.getPostString());
                                   // JTextField comfield = new JTextField();
                                    //comfield.setPreferredSize(new Dimension(350, 20));
                                    //JButton combutton = new JButton("Comment");
                                    //int likes;
                                   // combutton.setPreferredSize(new Dimension(100, 20));
                                    //combutton.addActionListener(combuttonActionListener);
                                    newPost.setBorder(bor);
                                    newPost.add(label);
                                  //  JPanel commentPanel = new JPanel(new GridLayout(0,1));
                                    //JPanel newCom = new JPanel();
                                    //newCom.setLayout(new FlowLayout());
                                    //newCom.add(comfield);
                                    //newCom.add(combutton);
                                   // commentPanel.add(newCom);
                                    postPanel.add(newPost);

                                    //newPost.add(commentPanel, BorderLayout.SOUTH);
                                    resetPosts.add(newPost);
                                }
                                currentPosts = resetPosts;

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
                }
            }
        };

                editButton.addActionListener(editAction);
                editHomeButton.addActionListener(editAction);
                deleteButton.addActionListener(deleteAction);
                deleteHomeButton.addActionListener(deleteAction);
                commentButton.addActionListener(commentAction);
                //commentHomeButton.addActionListener(commentAction);
                createPostButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.getContentPane().removeAll();  // or removeAll();

                        frame.getContentPane().add(postContent);

                        contentTextArea.setText("");

                        frame.repaint();

                        frame.revalidate();
                    }
                });
                createPostHomeButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.getContentPane().removeAll();  // or removeAll();

                        frame.getContentPane().add(postContent);

                        contentTextArea.setText("");

                        frame.repaint();

                        frame.revalidate();
                    }
                });


                // userHomeContent
                userHomeContent = new Container();
                userHomeContent.setLayout(new BorderLayout());
                userHomeContent.setSize(frame.getSize());  // set size of the content equal to that of the frame
                userHomeContent.add(new PostGUI(), BorderLayout.CENTER);

                // panel 1
                buttonHomePanel = new JPanel();
                buttonHomePanel.setLayout(new GridLayout(1, 2));
                buttonHomePanel.add(editHomeButton);
                buttonHomePanel.add(deleteHomeButton);
                userHomeContent.add(buttonHomePanel, BorderLayout.SOUTH);

                JPanel createPostHomePanel = new JPanel();
                createPostHomePanel.setLayout(new GridLayout(1, 8));
                for (int i = 0; i < 8; i++) {  // add empty labels to display createPostButton on the top-right corner
                    createPostHomePanel.add(new JLabel());
                }
                createPostHomePanel.add(createPostHomeButton);
                userHomeContent.add(createPostHomePanel, BorderLayout.NORTH);


                // postContent
                postContent = new Container();
                postContent.setLayout(new BorderLayout());
                postContent.setSize(frame.getSize());  // set size of the content equal to that of the frame
                postContent.add(new PostGUI(), BorderLayout.CENTER);

                // panel 1
                panel = new JPanel();
                homeButton = new JButton(homeIcon);
                homeButton.setPreferredSize(new Dimension(30, 30));
                homeButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
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
                nameButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // send to news feed home
                        yesNo = JOptionPane.showConfirmDialog(null, "Go to User Home?",
                                null, JOptionPane.YES_NO_OPTION);

                        if (yesNo == YES_OPTION) {
                            JPanel postGridLayoutPanel = new JPanel();
                            postGridLayoutPanel.setLayout(new GridLayout(0, 1));

                            frame.getContentPane().removeAll();  // or removeAll();

                            userPosts = poster.readFromFile(user);

                            panel = new JPanel();
//                    userHomeContent.setLayout(new GridLayout(0, 1));

                            if (userPosts != null) {
                                for (Post post : userPosts) {
                                    JPanel newPost = new JPanel();
                                    newPost.setLayout(new BorderLayout());
                                    String title = user.getUsername() + ":" + user.getAlias() + post.getTime();
                                    Border bor = BorderFactory.createTitledBorder(title);
                                    JLabel label = new JLabel(post.getPostString());
                                    JTextField comfield = new JTextField();
                                    comfield.setPreferredSize(new Dimension(350, 20));
                                    JButton combutton = new JButton("Comment");
                                    //combutton.addActionListener(combuttonActionListener);
                                    int likes;
                                    combutton.setPreferredSize(new Dimension(100, 20));
                                    newPost.setBorder(bor);
                                    newPost.add(label);
                                    postGridLayoutPanel.add(newPost);
                                }
                            }

//                    userHomeContent.add(panel);


                            userHomeContent.add(new JScrollPane(postGridLayoutPanel));

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
                postButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        yesNo = JOptionPane.showConfirmDialog(null, "Would you like to post?",
                                null, JOptionPane.YES_NO_OPTION);

                        if (yesNo == YES_OPTION) {
                            poster = new Poster(user);
                            LocalDateTime time0 = LocalDateTime.now();
                            String timeString = time0.toString();
                            String[] timeArray = timeString.split("T");
                            String date = timeArray[0];

                            String[] time1 = timeArray[1].split(":");
                            String hour = time1[0];
                            String minute = time1[1];
                            time = " " + date + " " + hour + ":" + minute;
                            post = poster.createPost(user, contentTextArea.getText(), time0, time, currentPosts.size());

                            newPost = new JPanel();
                            newPost.setLayout(new BorderLayout());

                            String title = user.getUsername() + ":" + user.getAlias() + time;
                            Border bor = BorderFactory.createTitledBorder(title);
                            JLabel label = new JLabel(post.getPostString());
                            //JTextField comfield = new JTextField();
                            //comfield.setPreferredSize(new Dimension(350, 20));
                            //JButton combutton = new JButton("Comment");
                            int likes;
                            //combutton.setPreferredSize(new Dimension(100, 20));
                            //combutton.addActionListener(combuttonActionListener);
                            newPost.setBorder(bor);
                            newPost.add(label);


                            ArrayList<JPanel> panel = new ArrayList<>();


                            postPanel.add(newPost);
                            currentPosts.add(newPost);

                            allPosts.add(post);

                            poster.writeAll(allPosts);

                            label1 = new JLabel();
                            JPanel temp = new JPanel();

                            //combutton.addActionListener(combuttonActionListener);

                            currentPosts.add(newPost);

                            frame.getContentPane().removeAll();  // or removeAll();

                            frame.getContentPane().add(new JScrollPane(newsFeedHomeContent));

                            frame.repaint();

                            frame.revalidate();
                        }

                    }
                });

                panel.add(postButton);
                postContent.add(panel, BorderLayout.SOUTH);


                // final step
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setVisible(true);  // so that loginPage is default
            }


            public static void main(String[] args) {
                // connections

                SwingUtilities.invokeLater(new PostGUI());

            }
        }