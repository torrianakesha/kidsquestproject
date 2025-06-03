package Quiz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class Register extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel userBackground;
	private JLabel passBackground;
	private JPasswordField passwordField;
	private String username;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public Register(String username) {
		this.username = username; // Store the username
		setVisible(true);
        setTitle("KidsQuest");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
     // Create the content pane
        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image backgroundImage = new ImageIcon("Resources\\regisBG.png").getImage();
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                g.drawImage(backgroundImage, 0, 0, screenSize.width, screenSize.height, this);
            }
        };

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel registerLbl = new JLabel(new ImageIcon("Resources\\regisLblBG.png"));
		registerLbl.setBounds(586, 316, 321, 60);
	    contentPane.add(registerLbl);
	    
		// text field area
		JLabel userBackground = new JLabel(new ImageIcon("Resources\\usernameBG.png"));
	    userBackground.setBounds(461, 417, 570, 77);
	    contentPane.add(userBackground);
	        
	    // Create the transparent text field
	    JTextField userTextField = new JTextField();
	    userTextField.setOpaque(false); // Make it transparent
	    userTextField.setBounds(0, 0, 597, 93);
	    userBackground.add(userTextField);
	    // Set the text alignment (optional)
	    userTextField.setHorizontalAlignment(JTextField.CENTER);
	        
	    Font rebellionFont = new Font("Rebellion Squad", Font.BOLD, 33);
	    Color textColor = Color.WHITE; 
	    //decode("#B500DE");
	        
	    userTextField.setFont(rebellionFont);
	    userTextField.setForeground(textColor);
	    userTextField.setBorder(null); // Remove any border

	  

	    // ActionListener to handle user input
	    userTextField.addActionListener(new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent evt) {
	             String username = userTextField.getText(); // Get the entered username
	             // More function (e.g., display it, validate it, etc.)
	                
	             System.out.println(username);
	            }
	        });
	    
	    // Label
	    JLabel lblUsername = new JLabel("");
		lblUsername.setIcon(new ImageIcon("Resources\\usernameLbl.png"));
		lblUsername.setBounds(487, 494, 120, 28);
		contentPane.add(lblUsername);
		
		
	    // Password Area
	    JLabel passBackground = new JLabel(new ImageIcon("Resources\\passBG.png"));
		passBackground.setBounds(461, 535, 570, 77);
	    contentPane.add(passBackground);
		
		JPasswordField getPassField = new JPasswordField();
		getPassField.setOpaque(false);
		getPassField.setBounds(0, 0, 597, 93);
		passBackground.add(getPassField);
		getPassField.setBorder(null); // Remove any border
		getPassField.setHorizontalAlignment(JPasswordField.LEFT);
		getPassField.setHorizontalAlignment(JPasswordField.CENTER);
		getPassField.setFont(rebellionFont);
		
		//Label Password
		JLabel lblPass = new JLabel("");
		lblPass.setIcon(new ImageIcon("Resources\\passLbl.png"));
		lblPass.setBounds(487,614, 112, 28);
		contentPane.add(lblPass);
		
		
		// Button Clear
        JButton btnClear = new JButton("");
        btnClear.setIcon(new ImageIcon("Resources\\buttonClear.png"));
        btnClear.setFocusPainted(false);
        btnClear.setContentAreaFilled(false);
        btnClear.setBorderPainted(false);
        btnClear.setBounds(526, 726, 126, 60);
        contentPane.add(btnClear);
        btnClear.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		userTextField.setText("");
                getPassField.setText("");
        	}
        });
        
        
        
        // Button Register
        JButton btnRegister = new JButton("");
        btnRegister.setIcon(new ImageIcon("Resources\\btnReg.png"));
        btnRegister.setFocusPainted(false);
        btnRegister.setContentAreaFilled(false);
        btnRegister.setBorderPainted(false);
        btnRegister.setBounds(759, 732, 214, 60);
        contentPane.add(btnRegister);
        
        // Function to register
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = userTextField.getText();
                String password = String.valueOf(getPassField.getPassword());
                String SUrl = "jdbc:mysql://localhost:3306/quizdb";
                String SUser = "root";
                String Spass = "";

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(SUrl, SUser, Spass);

                    if (username.equals("") || password.equals("")) {
                    	// Redirect to empty reg frame
                    	emptyReg empFrame = new emptyReg();
                    	empFrame.setVisible(true);
                        dispose(); // Close the current frame
                        //JOptionPane.showMessageDialog(new JFrame(), "Please fill in both the username and password to complete your registration.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        // Check if username already exists
                        String query = "SELECT * FROM kquser WHERE username = ?";
                        PreparedStatement pstCheckUser = con.prepareStatement(query);
                        pstCheckUser.setString(1, username);
                        ResultSet rs = pstCheckUser.executeQuery();

                        if (rs.next()) {
                            // Username taken
                        	userTaken userFrame = new userTaken();
                            userFrame.setVisible(true);
                            dispose(); // Close the current frame
                            //JOptionPane.showMessageDialog(new JFrame(), "Sorry, that username is already taken. Please choose a different one.", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            // Insert new user into kquser
                            query = "INSERT INTO kquser (username, password) VALUES (?, ?)";
                            PreparedStatement pstInsertUser = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                            pstInsertUser.setString(1, username);
                            pstInsertUser.setString(2, password);
                            pstInsertUser.executeUpdate();

                            // Initialize user progress in admin_track
                            String insertProgressQuery = "INSERT INTO admin_track (username, easy_score, average_score, hard_score, evaluation) VALUES (?, 0, 0, 0, '')";
                            PreparedStatement pstInsertProgress = con.prepareStatement(insertProgressQuery);
                            pstInsertProgress.setString(1, username);
                            pstInsertProgress.executeUpdate();

                            userTextField.setText("");
                            getPassField.setText("");
                           //Show frame after successfully registering
                            RegSuccess regFrame = new RegSuccess(username);
                            regFrame.setVisible(true);
                            dispose(); // Close the current frame
                            //JOptionPane.showMessageDialog(null, "You have successfully registered. Game?");
                        }

                        rs.close();
                        pstCheckUser.close();
                    }
                    con.close();
                } catch (Exception err) {
                    System.out.println("Error! " + err.getMessage());
                }
            }
        });


    

        JButton btnBack = new JButton("");
        btnBack.setIcon(new ImageIcon("Resources\\buttonBack.png"));
        btnBack.setFocusPainted(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setBorderPainted(false);
        btnBack.setBounds(40, 689, 239, 110);
        contentPane.add(btnBack);
        
        // Button function
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Display the Login frame
                LogIn logFrame = new LogIn(username);
                logFrame.setVisible(true);
                dispose(); // Close the current frame
            }
        });
		
        JLabel containerRegis = new JLabel("");
		containerRegis.setIcon(new ImageIcon("Resources\\containerReg.png"));
		containerRegis.setBounds(418, 26, 784, 925);
		contentPane.add(containerRegis);
		
		
	}
}
