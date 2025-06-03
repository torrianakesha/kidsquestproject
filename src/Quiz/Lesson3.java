package Quiz;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Lesson3 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String username;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public Lesson3(String username) {
        this.username = username; // Store the username
		setTitle("KidsQuest");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
       
        //BACKGROUND IMAGE
        JPanel contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image backgroundImage = new ImageIcon("Resources\\Lesson3BG.png").getImage();
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                g.drawImage(backgroundImage, 0, 0, screenSize.width, screenSize.height, this);
            }
        };

        // Optional: Remove any border around the image
        contentPane.setBorder(BorderFactory.createEmptyBorder());

        // Set the content pane
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
      //LESSON 1
        JButton btnLess1 = new JButton("");
        btnLess1.setIcon(new ImageIcon("Resources\\lesson1.png"));
        btnLess1.setBounds(127, 230, 371, 89);
        btnLess1.setFocusPainted(false);
        btnLess1.setContentAreaFilled(false);
        btnLess1.setBorderPainted(false);
        contentPane.add(btnLess1);
        btnLess1.setEnabled(false);
        
//        //FUNCTION TO LEARNING MATERIALS
//        btnLess1.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                // Display the Landing frame
//                Lesson1 l1Frame = new Lesson1(username);
//                l1Frame.setVisible(true);
//                dispose(); // Close the current frame
//            }
//        });
//        
      //LESSON 2
        JButton btnLess2 = new JButton("");
        btnLess2.setIcon(new ImageIcon("Resources\\lesson2.png"));
        btnLess2.setBounds(575, 230, 344, 89);
        btnLess2.setFocusPainted(false);
        btnLess2.setContentAreaFilled(false);
        btnLess2.setBorderPainted(false);
        contentPane.add(btnLess2);
        btnLess2.setEnabled(false);
        
//        btnLess2.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                // Display the Register frame
//                Lesson2 l2Frame = new Lesson2(username);
//                l2Frame.setVisible(true);
//                dispose(); // Close the current frame
//            }
//        });
//        
        //LESSON 3
        JButton btnLess3 = new JButton("");
        btnLess3.setIcon(new ImageIcon("Resources\\lesson3.png"));
        btnLess3.setBounds(993, 230, 357, 88);
        btnLess3.setFocusPainted(false);
        btnLess3.setContentAreaFilled(false);
        btnLess3.setBorderPainted(false);
        contentPane.add(btnLess3);
        btnLess3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Display the Register frame
            	Lesson3 l3Frame = new Lesson3(username);
            	l3Frame.setVisible(true);
                dispose(); // Close the current frame
            }
        });
        
        JButton btnBack = new JButton("");
        btnBack.setIcon(new ImageIcon("Resources\\learnBack.png"));
        btnBack.setBounds(150, 720, 335, 109);
        btnBack.setFocusPainted(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setBorderPainted(false);
        contentPane.add(btnBack);
        
        //FUNCTION TO PROCEED TO LESSONS
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Display the Lesson1 frame
                Lesson2 l2Frame = new Lesson2(username);
                l2Frame.setVisible(true);
                dispose(); // Close the current frame
            }
        });
        
        JButton btnPlay = new JButton("");
        btnPlay.setIcon(new ImageIcon("Resources\\playBtn.png"));
        btnPlay.setBounds(1151, 710, 303, 140);
        btnPlay.setFocusPainted(false);
        btnPlay.setContentAreaFilled(false);
        btnPlay.setBorderPainted(false);
        contentPane.add(btnPlay);
        
        btnPlay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//            	JOptionPane.showMessageDialog(null, "Login successful!");
                // Redirect to the category/quiz game
                Category catFrame = new Category(username);
                catFrame.setVisible(true);
                dispose(); // Close the current frame
            }
        });
        
	}

}
