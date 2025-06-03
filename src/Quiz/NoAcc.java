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

public class NoAcc extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NoAcc frame = new NoAcc();
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
	public NoAcc() {
		setTitle("KidsQuest");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
       
        //BACKGROUND IMAGE
        JPanel contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image backgroundImage = new ImageIcon("Resources\\noAccBG.png").getImage();
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                g.drawImage(backgroundImage, 0, 0, screenSize.width, screenSize.height, this);
            }
        };

        // Optional: Remove any border around the image
        contentPane.setBorder(BorderFactory.createEmptyBorder());

        // Set the content pane
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        
        JButton btnRegis = new JButton("");
        btnRegis.setIcon(new ImageIcon("Resources\\btnRegisterErr.png"));
        btnRegis.setFocusPainted(false);
        btnRegis.setContentAreaFilled(false);
        btnRegis.setBorderPainted(false);
        btnRegis.setBounds(505, 549, 483, 149); // Position the Register button
        contentPane.add(btnRegis);
        btnRegis.setVisible(true);
        
        btnRegis.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                // Display the Register frame
                Register logFrame = new Register(getName());
                logFrame.setVisible(true);
                dispose(); // Close the current frame
            }
        });
	}

}
