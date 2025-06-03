package Quiz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;


public class Landing extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	/**
	 * @wbp.nonvisual location=509,304
	 */
	private final JButton btnStart = new JButton("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Landing frame = new Landing();
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
	public Landing() {
		setTitle("KidsQuest");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
       
        //BACKGROUND IMAGE
        JPanel contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image backgroundImage = new ImageIcon("Resources\\background.png").getImage();
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                g.drawImage(backgroundImage, 0, 0, screenSize.width, screenSize.height, this);
            }
        };

        // Optional: Remove any border around the image
        contentPane.setBorder(BorderFactory.createEmptyBorder());

        // Set the content pane
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        //LOGO
        JLabel logoKQ = new JLabel("");
        logoKQ.setIcon(new ImageIcon("Resources\\logo.png"));
        logoKQ.setBounds(370, 24, 754, 423);
        contentPane.add(logoKQ);
        
        //BUTTON #1 - START 
        JButton btnStartt = new JButton("");
        btnStart.setIcon(new ImageIcon("Resources\\buttonStart.png"));
        btnStart.setBounds(483, 463, 514, 205);
        btnStart.setContentAreaFilled(false); // To remove the content
        btnStart.setBorderPainted(false); // To remove the border
        btnStart.setFocusPainted(false); // To remove the focus border
        contentPane.add(btnStart);
        
        //FUNCTION
        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Display the LogIn/Register frame
                LogIn logFrame = new LogIn(getName());
                logFrame.setVisible(true);
                dispose(); // Close the current frame
            }
        });
        
        //BUTTON #2 - ABOUT 
        JButton btnAbout = new JButton("");
        btnAbout.setIcon(new ImageIcon("Resources\\buttonAbout.png"));
        btnAbout.setFocusPainted(false);
        btnAbout.setContentAreaFilled(false);
        btnAbout.setBorderPainted(false);
        btnAbout.setBounds(1263, 685, 249, 129);
        contentPane.add(btnAbout);
        
      //FUNCTION
        btnAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Display the About frame
                About aboutFrame = new About();
                aboutFrame.setVisible(true);
                dispose(); // Close the current frame
            }
        });
        
        //BUTTON #3 - EXIT
        JButton btnExit = new JButton("");
        btnExit.setIcon(new ImageIcon("Resources\\buttonExit.png"));
        btnExit.setFocusPainted(false);
        btnExit.setContentAreaFilled(false);
        btnExit.setBorderPainted(false);
        btnExit.setBounds(10, 685, 249, 129);
        contentPane.add(btnExit);
        
        JButton btnEval = new JButton("");
        btnEval.setIcon(new ImageIcon("Resources\\infoBtn.png"));
        btnEval.setBounds(40, 39, 80, 80);
        btnEval.setFocusPainted(false);
        btnEval.setContentAreaFilled(false);
        btnEval.setBorderPainted(false);
        contentPane.add(btnEval);
        btnEval.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Display the Rubrics Frame for Evaluation
                Rubrics rubricsFrame = new Rubrics();
                rubricsFrame.setVisible(true);
                dispose(); // Close the current frame
            }
        });
        
        
        //FUNCTION 
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	// Display the dialogue message
            	JLabel dlgMsg = new JLabel(new ImageIcon("Resources\\extDlgMsg.png"));
                dlgMsg.setBounds(429, 276, 635, 385); // Position the dialogue message
                contentPane.add(dlgMsg);
                dlgMsg.setVisible(true); // Make sure to set the label as visible
                
                // Disable the other buttons
                btnStart.setEnabled(false);
                btnAbout.setEnabled(false);
                btnExit.setEnabled(false);
                btnEval.setEnabled(false);
                
                //Hide the logo and start button
                btnStart.setVisible(false);
                logoKQ.setVisible(false);
                
                	// NEW BUTTON #5 - QUIT 
	                JButton btnQuit = new JButton("");
	                btnQuit.setIcon(new ImageIcon("Resources\\btnQuit.png"));
	                btnQuit.setFocusPainted(false);
	                btnQuit.setContentAreaFilled(false);
	                btnQuit.setBorderPainted(false);
	                btnQuit.setBounds(477, 549, 270, 80); // Position the Quit button
	                contentPane.add(btnQuit);
	                btnQuit.setVisible(true);
	                
	                //FUNCTION - to exit
	                btnQuit.addActionListener(new ActionListener() {
	                    public void actionPerformed(ActionEvent e) {
	                        System.exit(0); // Exit the application
	                    }
	                });
	                
	             // NEW BUTTON #6 - PLAY (continue)
	                JButton btnPlay = new JButton("");
	                btnPlay.setIcon(new ImageIcon("Resources\\btnPlay.png"));
	                btnPlay.setFocusPainted(false);
	                btnPlay.setContentAreaFilled(false);
	                btnPlay.setBorderPainted(false);
	                btnPlay.setBounds(765, 549, 270, 80); // Position the Quit button
	                contentPane.add(btnPlay);    
	                
	                //FUNCTION - continue to play 
	                btnPlay.addActionListener(new ActionListener() {
	                    public void actionPerformed(ActionEvent e) {
	                    	
	                        // Enable 
	                        btnStart.setEnabled(true);
	                        btnAbout.setEnabled(true);
	                        btnExit.setEnabled(true);
	                        
	                        // Show
	                        btnStart.setVisible(true);
	                        btnAbout.setVisible(true);
	                        btnExit.setVisible(true);
	                        logoKQ.setVisible(true);

	                        // Remove the dialogue message and new buttons
	                        dlgMsg.setVisible(false);
	                        contentPane.remove(dlgMsg);
	                        contentPane.remove(btnQuit);
	                        contentPane.remove(btnPlay);

	                        contentPane.revalidate();
	                        contentPane.repaint();
			            }
			        });
			        btnPlay.setVisible(true);
			        
			        // Set the z-order for dlgMsg, btnQuit, and btnPlay
	                contentPane.setComponentZOrder(btnQuit, 0); // then btnQuit
	                contentPane.setComponentZOrder(btnPlay, 1); // then btnPlay
	                contentPane.setComponentZOrder(dlgMsg, 2); // dlgMsg at the back  
		            }
		        });
            
	}
}
        