import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static javax.swing.JOptionPane.OK_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;

public class PostGUI extends JComponent implements Runnable {
    PostGUI postGUI;  // main post window

    int yesNo;  // takes input of JOptionPane after pressing a button

    int curX;  // current mouse x coordinate for click
    int curY;  // current mouse y coordinate for click

    String postType;

    private static final String[] postPrivacyOptions = {"Public", "Private"};

    // Text area & fields
    JTextArea contentTextArea;
    JTextField commentTextField;

    // Combo boxes
    JComboBox<String> privatePublicComboBox;

    // Labels
    JLabel nameLabel;

    // Buttons
    JButton nameButton;  // simply displays the name of the user
    JButton postButton;  // click the button to post
    JButton commentButton;  // opens comment window (code this in as a separate class)
    JButton homeButton;  // brings you back to the news feed home
    JButton writeCommentButton;  // writes the comment in the commentTextField

    // Icons
    Icon homeIcon = new ImageIcon("C:\\Users\\Me\\IdeaProjects\\Cs180Proj5Group\\home.png");
    Icon pencilIcon = new ImageIcon("C:\\Users\\Me\\IdeaProjects\\Cs180Proj5Group\\pencil.png");

    @Override
    public void run() {
        JFrame frame = new JFrame();
        frame.setTitle("Create Post");

        Container content = frame.getContentPane();

        content.setLayout(new BorderLayout());
        postGUI = new PostGUI();
        content.add(postGUI, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        homeButton = new JButton(homeIcon);
        homeButton.setPreferredSize(new Dimension(30, 30));
        homeButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                // send to news feed home
                yesNo = JOptionPane.showConfirmDialog(null, "Would you like return home?",
                        null, JOptionPane.YES_NO_OPTION);
            }
        });
        panel.add(homeButton);
        nameLabel = new JLabel("username");  // should differ by each username
        panel.add(nameLabel);
        privatePublicComboBox = new JComboBox<String>(postPrivacyOptions);
        privatePublicComboBox.setVisible(true);
        panel.add(privatePublicComboBox);
        content.add(panel, BorderLayout.PAGE_START);

        panel = new JPanel();
        contentTextArea = new JTextArea(10, 40);
        panel.add(contentTextArea);
        content.add(panel, BorderLayout.CENTER);
        content.add(new JScrollPane(contentTextArea));

        panel = new JPanel();
        postButton = new JButton("Post");
        postButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                yesNo = JOptionPane.showConfirmDialog(null, "Would you like to post?",
                        null, JOptionPane.YES_NO_OPTION);

                if (yesNo == YES_OPTION) {
                    // returns text in contentTextArea
//                    return contentTextArea.getText();
                }

            }
        });
        commentButton = new JButton("Comments");
        commentButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
//                Comment cmt = new Comment();
//                cmt.setVisible(true);
                // move to comment page
                yesNo = (int) JOptionPane.showConfirmDialog(null,
                        "Would you like to move to comments?", null, JOptionPane.YES_NO_OPTION);

                if (yesNo == YES_OPTION) {
                    CommentGUI cmt = new CommentGUI();
                    cmt.setVisible(true);
                }
            }
        });
        commentTextField = new JTextField(30);
        writeCommentButton = new JButton(pencilIcon);
        writeCommentButton.setPreferredSize(new Dimension(30, 30));
        panel.add(postButton);
        panel.add(commentButton);
        panel.add(commentTextField);
        panel.add(writeCommentButton);
        content.add(panel, BorderLayout.SOUTH);

        frame.setSize(600, 400);
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
        SwingUtilities.invokeLater(new PostGUI());
    }
}
