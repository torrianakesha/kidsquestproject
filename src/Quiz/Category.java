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
	import java.awt.Font;
import javax.swing.JLabel;
	
	
	public class Category extends JFrame {
		
	    private static final long serialVersionUID = 1L;
	    private JPanel contentPane;
	    private String username;
	    private JButton btnAverage;
	    private JButton btnHard;

	    public static void main(String[] args) {
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                try {
	                    // Pass a dummy username for testing
	                    Category frame = new Category("testuser");
	                    frame.setVisible(true);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        });
	    }

	    public Category(String username) {
	        this.username = username;
	        setTitle("KidsQuest");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setExtendedState(JFrame.MAXIMIZED_BOTH);
	        
	    
	    
	  //BACKGROUND IMAGE
        JPanel contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image backgroundImage = new ImageIcon("Resources\\catBG.png").getImage();
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                g.drawImage(backgroundImage, 0, 0, screenSize.width, screenSize.height, this);
            }
        };

        // Optional: Remove any border around the image
        contentPane.setBorder(BorderFactory.createEmptyBorder());

        // Set the content pane
        setContentPane(contentPane);
        contentPane.setLayout(null);
	    
	        //EASY LEVEL
	        JButton btnEasy = new JButton("");
	        btnEasy.setBounds(139, 325, 401, 250);
	        btnEasy.setBorderPainted(false);
	        btnEasy.setContentAreaFilled(false);
	        btnEasy.setFont(new Font("Tahoma", Font.BOLD, 16));
	        btnEasy.setOpaque(false);
	        btnEasy.setFocusPainted(false); // To remove the focus border
	        btnEasy.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                EasyLevel easyLevelFrame = new EasyLevel(Category.this, username);
	                easyLevelFrame.setVisible(true);
	                // Close the current frame
	                dispose(); // 'dispose()' is called on the current JFrame instance
	            }
	        });
	        contentPane.setLayout(null);
	        btnEasy.setIcon(new ImageIcon("Resources//Easy.png"));
	        contentPane.add(btnEasy);

	        //AVERAGE LEVEL
	        btnAverage = new JButton("");
	        btnAverage.setBounds(573, 325, 401, 250);
	        btnAverage.setIcon(new ImageIcon("Resources//Average.png"));
	        btnAverage.setOpaque(false);
	        btnAverage.setFocusPainted(false); // To remove the focus border
	        btnAverage.setFont(new Font("Tahoma", Font.BOLD, 16));
	        btnAverage.setContentAreaFilled(false);
	        btnAverage.setBorderPainted(false);
	        btnAverage.setEnabled(false); // Initially disabled

	        btnAverage.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                AverageLevel averageLevelFrame = new AverageLevel(Category.this, username);
	                averageLevelFrame.setVisible(true);
	                // Close the current frame
	                dispose(); // 'dispose()' is called on the current JFrame instance
	            }
	        });

	        contentPane.add(btnAverage);

	        //HARD LEVEL
	        btnHard = new JButton("");
	        btnHard.setBounds(1015, 325, 401, 250);
	        btnHard.setIcon(new ImageIcon("Resources//Hard.png"));
	        btnHard.setOpaque(false);
	        btnHard.setFocusPainted(false); // To remove the focus border
	        btnHard.setFont(new Font("Tahoma", Font.BOLD, 16));
	        btnHard.setContentAreaFilled(false);
	        btnHard.setBorderPainted(false);
	        btnHard.setEnabled(false); // Initially disabled

	        btnHard.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                HardLevel hardLevelFrame = new HardLevel(username);
	                hardLevelFrame.setVisible(true);
	                // Close the current frame
	                dispose(); // 'dispose()' is called on the current JFrame instance
	            }
	        });

	        contentPane.add(btnHard);

	        JButton btnBack = new JButton("");
	        btnBack.setBounds(51, 705, 241, 119);
	        btnBack.setIcon(new ImageIcon("Resources//backBtn.png"));
	        btnBack.setOpaque(false);
	        btnBack.setFocusPainted(false); // To remove the focus border
	        btnBack.setFont(new Font("Tahoma", Font.BOLD, 16));
	        btnBack.setContentAreaFilled(false);
	        btnBack.setBorderPainted(false);
	        contentPane.add(btnBack);
	        
	        btnBack.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                // Display the Register frame
	                LearningMaterial logFrame = new LearningMaterial(username);
	                logFrame.setVisible(true);
	                dispose(); // Close the current frame
	                
	            
	            }
	        });
	        
	        JButton btnQuit = new JButton("");
	        btnQuit.setBounds(1136, 705, 340, 147);
	        btnQuit.setIcon(new ImageIcon("Resources//buttonExit.png"));
	        btnQuit.setOpaque(false);
	        btnQuit.setFocusPainted(false); // To remove the focus border
	        btnQuit.setFont(new Font("Tahoma", Font.BOLD, 16));
	        btnQuit.setContentAreaFilled(false);
	        btnQuit.setBorderPainted(false);
	        contentPane.add(btnQuit);
	        
	        btnQuit.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	            	// Display the dialogue message
	            	JLabel dlgMsg = new JLabel(new ImageIcon("Resources\\extDlgMsg.png"));
	                dlgMsg.setBounds(429, 276, 635, 490); // Position the dialogue message
	                contentPane.add(dlgMsg);
	                dlgMsg.setVisible(true); // Make sure to set the label as visible
	                
	                // Disable the other buttons
	                btnEasy.setVisible(false);
	                btnAverage.setVisible(false);
	                btnHard.setVisible(false);
	                btnBack.setEnabled(false);
	                
	                
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
		                    	
		                    	Category frame = new Category("testuser");
			                    frame.setVisible(true);
		                    	
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
	        
	        
	    
	        JLabel lblCatLogo = new JLabel("");
	        lblCatLogo.setIcon(new ImageIcon("Resources\\catLogo.png"));
	        lblCatLogo.setBounds(511, 47, 449, 252);
	        contentPane.add(lblCatLogo);
	        
	    }
	        
	    public void completeEasyLevel() {
	        btnAverage.setEnabled(true);
	    }

	    public void completeAverageLevel() {
	        btnHard.setEnabled(true);
	    }
	}
