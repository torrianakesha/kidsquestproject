package Quiz;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextField;
import java.awt.TextField;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFormattedTextField;
import java.awt.Cursor;
import javax.swing.JButton;

public class LogIn extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField userTextField;
    private String username;
    

    public LogIn(String username) {
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
                Image backgroundImage = new ImageIcon("Resources\\reglogBG.png").getImage();
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                g.drawImage(backgroundImage, 0, 0, screenSize.width, screenSize.height, this);
            }
        };
        
       
        // Set the content pane
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        // Create the background label with the image
        JLabel userBackground = new JLabel(new ImageIcon("Resources\\usernameBG.png"));
        userBackground.setBounds(112, 346, 570, 98);
        contentPane.add(userBackground);
        
        // Create the transparent text field
        JTextField userTextField = new JTextField();
        userTextField.setOpaque(false); // Make it transparent
        userTextField.setBounds(0, 0, 597, 93);
        userBackground.add(userTextField);
        
        Font rebellionFont = new Font("Rebellion Squad", Font.BOLD, 33);
        Color textColor = Color.WHITE; 
        //decode("#B500DE");
        

        userTextField.setFont(rebellionFont);
        userTextField.setForeground(textColor);
        userTextField.setBorder(null); // Remove any border

        // Set the text alignment (optional)
        userTextField.setHorizontalAlignment(JTextField.CENTER);

        // ActionListener to handle user input
        userTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String username = userTextField.getText(); // Get the entered username
                // Do something with the username (e.g., display it, validate it, etc.)
                System.out.println(username);
            }
        });
        
        // Label for username
        JLabel usernameLabel = new JLabel("");
        usernameLabel.setIcon(new ImageIcon("Resources\\usernameLbl.png"));
        usernameLabel.setBounds(138, 444, 120, 28);
        contentPane.add(usernameLabel);
        
        // Password Area
	    JLabel passBackground = new JLabel(new ImageIcon("Resources\\passBG.png"));
		passBackground.setBounds(112, 476, 570, 99);
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
		lblPass.setBounds(138, 570, 112, 28);
		contentPane.add(lblPass);
        
        JButton btnRegister = new JButton("");
        btnRegister.setIcon(new ImageIcon("Resources\\btnReg.png"));
        btnRegister.setFocusPainted(false);
        btnRegister.setContentAreaFilled(false);
        btnRegister.setBorderPainted(false);
        btnRegister.setBounds(114, 590, 240, 98);
        contentPane.add(btnRegister);
        
        // Button Function 
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Display the Register frame
                Register logFrame = new Register(getName());
                logFrame.setVisible(true);
                dispose(); // Close the current frame
            }
        });
        
        
        
     // Button Back
        JButton btnBack = new JButton("");
        btnBack.setIcon(new ImageIcon("Resources\\buttonBack.png"));
        btnBack.setFocusPainted(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setBorderPainted(false);
        btnBack.setBounds(40, 706, 217, 127);
        contentPane.add(btnBack);
        
     // Button function
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Display the Landing frame
                Landing logFrame = new Landing();
                logFrame.setVisible(true);
                dispose(); // Close the current frame
            }
        });
        
     // Button Login
        JButton btnLogin = new JButton("");
        btnLogin.setIcon(new ImageIcon("Resources\\btnLog.png"));
        btnLogin.setFocusPainted(false);
        btnLogin.setContentAreaFilled(false);
        btnLogin.setBorderPainted(false);
        btnLogin.setBounds(461, 590, 204, 80);
        contentPane.add(btnLogin);
        

        // Button function - request from database
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = userTextField.getText();
                String password = String.valueOf(getPassField.getPassword());
                String SUrl = "jdbc:mysql://localhost:3306/quizdb";
                String SUser = "root";
                String Spass = "";

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(SUrl, SUser, Spass);
                    String query = "SELECT * FROM kquser WHERE username = ? AND password = ?";
                    PreparedStatement pst = con.prepareStatement(query);
                    pst.setString(1, username);
                    pst.setString(2, password);
                    ResultSet rs = pst.executeQuery();

                    if (rs.next()) {
                        // Login successful
                    	
                    	// Show error message if either field is empty
    	                // Display the EmptyLog frame
    	                LogSuccess logFrame = new LogSuccess(username);
    	                logFrame.setVisible(true);
    	                dispose(); // Close the current frame
                    	//JOptionPane.showMessageDialog(null, "Login successful!");

                        // Retrieve or initialize user progress in admin_track
                        String queryProgress = "SELECT * FROM admin_track WHERE username = ?";
                        PreparedStatement pstProgress = con.prepareStatement(queryProgress);
                        pstProgress.setString(1, username);
                        ResultSet rsProgress = pstProgress.executeQuery();

                        if (!rsProgress.next()) {
                            // If user's progress doesn't exist, create a new entry
                            String insertProgressQuery = "INSERT INTO admin_track (username, easy_score, average_score, hard_score, evaluation) VALUES (?, 0, 0, 0, '')";
                            PreparedStatement pstInsertProgress = con.prepareStatement(insertProgressQuery);
                            pstInsertProgress.setString(1, username);
                            pstInsertProgress.executeUpdate();
                        }

//                        // Redirect to the Learning Material frame
//                        LearningMaterial learnFrame = new LearningMaterial(username);
//                        learnFrame.setVisible(true);
//                        dispose(); // Close the current frame
                       
                    	//JOptionPane.showMessageDialog(null, "Login successful!");
                        
                    } else if (username.equals("") || password.equals("")) {
                    	// Show error message if either field is empty
    	                // Display the EmptyLog frame
    	                EmptyLog ElogFrame = new EmptyLog();
    	                ElogFrame.setVisible(true);
    	                dispose(); // Close the current frame
    	                
    	                
    	                //JOptionPane.showMessageDialog(new JFrame(), "Valid username and password are required", "Error", JOptionPane.ERROR_MESSAGE);
                    } else { 
                        // Login failed
                    	// Show error message if either field is empty
    	                // Display the EmptyLog frame
    	                NoAcc accFrame = new NoAcc();
    	                accFrame.setVisible(true);
    	                dispose(); // Close the current frame
                    	
                        //JOptionPane.showMessageDialog(new JFrame(), "Account not found, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    rs.close();
                    pst.close();
                    con.close();
                } catch (Exception err) {
                    System.out.println("Error! " + err.getMessage());
                }
            }
        });
    
    }
}