import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

import static javax.swing.JOptionPane.OK_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;

public class PostGUI extends JComponent implements Runnable {
    PostGUI postGUI;  // main post window

    int yesNo;  // takes input of JOptionPane after pressing a button

    int curX;  // current mouse x coordinate for click
    int curY;  // current mouse y coordinate for click

    String postType;  // whether the post is "Private" or "Public"
    private static final String[] postPrivacyOptions = {"Public", "Private"};

    // Contents
    Container postContent;
    Container newsFeedHomeContent;
    Container userHomeContent;

    // Panels
    JPanel panel;

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

    // Icons
    Icon homeIcon = new ImageIcon("C:\\Users\\Me\\IdeaProjects\\Cs180Proj5Group\\home.png");
    Icon pencilIcon = new ImageIcon("C:\\Users\\Me\\IdeaProjects\\Cs180Proj5Group\\pencil.png");

    @Override
    public void run() {
        // main frame
        JFrame frame = new JFrame();
        frame.setSize(600, 400);
        frame.setTitle("Social Media");


        // newsFeedHomeContent
        newsFeedHomeContent = new Container();
        newsFeedHomeContent.setLayout(new BorderLayout());
        newsFeedHomeContent.setSize(frame.getSize());  // set size of the content equal to that of the frame
        newsFeedHomeContent.add(new PostGUI(), BorderLayout.CENTER);

        // panel 1
        panel = new JPanel();
        newsFeedHomeContent.add(panel, BorderLayout.CENTER);


        // userFeedHomeContent
        userHomeContent = new Container();
        userHomeContent.setLayout(new BorderLayout());
        userHomeContent.setSize(frame.getSize());  // set size of the content equal to that of the frame
        userHomeContent.add(new PostGUI(), BorderLayout.CENTER);

        // panel 1
        panel = new JPanel();
        userHomeContent.add(panel, BorderLayout.CENTER);


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
                }

            }
        });
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
