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

public class EmptyLog extends JFrame {

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
					EmptyLog frame = new EmptyLog();
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
	public EmptyLog() {
		setTitle("KidsQuest");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
       
        //BACKGROUND IMAGE
        JPanel contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image backgroundImage = new ImageIcon("Resources\\emptyDlgMsg.png").getImage();
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                g.drawImage(backgroundImage, 0, 0, screenSize.width, screenSize.height, this);
            }
        };

        // Optional: Remove any border around the image
        contentPane.setBorder(BorderFactory.createEmptyBorder());

        // Set the content pane
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        
        JButton btnOkay = new JButton("");
        btnOkay.setIcon(new ImageIcon("Resources\\btnOk.png"));
        btnOkay.setFocusPainted(false);
        btnOkay.setContentAreaFilled(false);
        btnOkay.setBorderPainted(false);
        btnOkay.setBounds(505, 550, 483, 165); // Position the Okay button
        contentPane.add(btnOkay);
        btnOkay.setVisible(true);
        
        btnOkay.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            // Display the Landing frame
            LogIn logFrame = new LogIn(username);
            logFrame.setVisible(true);
            dispose(); // Close the current frame
        }
    });
        
	}

}
