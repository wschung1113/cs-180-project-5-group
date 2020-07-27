import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import static javax.swing.JOptionPane.YES_OPTION;

public class CommentGUI extends JComponent implements Runnable {
    CommentGUI commentGUI;  // main comment window

    ArrayList<String> commentsList = new ArrayList<>();  // contains all comments

    int yesNo;  // takes input of JOptionPane after pressing a button

    int curX;  // current mouse x coordinate for click
    int curY;  // current mouse y coordinate for click

    // Text fields
    JTextField commentTextField;

    // Labels
    JLabel usernameLabel;
    JLabel commentLabel;

    // JScrollPane
    JScrollPane commentsScroller;

    // Buttons
    JButton nameButton;  // simply displays the name of the user
    JComboBox<String> privatePublicComboBox;
    JButton postButton;  // click the button to post
    JButton commentButton;  // opens comment window (code this in as a separate class)
    JButton homeButton;  // brings you back to the news feed home
    JButton writeCommentButton;  // writes the comment in the commentTextField
    JDialog commentDialog;

    // Icons
    Icon homeIcon = new ImageIcon("C:\\Users\\Me\\IdeaProjects\\Cs180Proj5Group\\home.png");
    Icon pencilIcon = new ImageIcon("C:\\Users\\Me\\IdeaProjects\\Cs180Proj5Group\\pencil.png");
    Icon commentIcon = new ImageIcon("C:\\Users\\Me\\IdeaProjects\\Cs180Proj5Group\\return.png");

    @Override
    public void run() {
        JFrame frame = new JFrame();
        frame.setSize(500, 450);
        frame.setTitle("Comments");

        Container content = frame.getContentPane();

        content.setLayout(new GridLayout(0, 1));
//        comment = new Comment();
//        content.add(comment);

        for (int i = 0; i < 100; i++) {
            commentsList.add("username: It's a wonderful day!" + i);
        }

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.setSize(frame.getSize());
        panel.setSize(content.getSize());

        for (int j = 0; j < commentsList.size(); j++) {
//            panel = new JPanel(new GridLayout(0, 2));
            commentLabel = new JLabel(commentsList.get(j));
//            panel.add(usernameLabel);
            panel.add(commentLabel);
            commentButton = new JButton(commentIcon);
            commentButton.setPreferredSize(new Dimension(30, 30));
            panel.add(commentButton);

//            content.add(usernameLabel, RIGHT_ALIGNMENT);
//            content.add(commentLabel, LEFT_ALIGNMENT);
        }

        content.add(new JScrollPane(panel));


        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    public CommentGUI() {  // constructor
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                curX = e.getX();
                curY = e.getY();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new CommentGUI());
    }
}
