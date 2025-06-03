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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class LearningMaterial extends JFrame {

	 private static final long serialVersionUID = 1L;
	    private JPanel contentPane;
	    private String username;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LearningMaterial frame = new LearningMaterial("testuser");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	//Accepting usernmae as parameter and store for later use
	public LearningMaterial(String username) {
        this.username = username; // Store the username

        setTitle("Learning Material");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
       
        //BACKGROUND IMAGE
        JPanel contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image backgroundImage = new ImageIcon("Resources\\mainInsBg.png").getImage();
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                g.drawImage(backgroundImage, 0, 0, screenSize.width, screenSize.height, this);
            }
        };

        // Optional: Remove any border around the image
        contentPane.setBorder(BorderFactory.createEmptyBorder());

        // Set the content pane
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JButton btnLessons = new JButton("");
        btnLessons.setIcon(new ImageIcon("Resources\\btnLess.png"));
        btnLessons.setBounds(1067, 720, 340, 127);
        btnLessons.setFocusPainted(false);
        btnLessons.setContentAreaFilled(false);
        btnLessons.setBorderPainted(false);
        contentPane.add(btnLessons);
        
        //FUNCTION TO PROCEED TO LESSONS
        btnLessons.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Display the Lesson1 frame
                Lesson1 l1Frame = new Lesson1(username);
                l1Frame.setVisible(true);
                dispose(); // Close the current frame
            }
        });
        
	}
}
