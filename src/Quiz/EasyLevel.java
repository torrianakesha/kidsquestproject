package Quiz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@SuppressWarnings("serial")
public class EasyLevel extends JFrame {

    private String username;
    private Category parentFrame;
    private JPanel panel;
    private JLabel lblQuestion;
    private JButton[] optionButtons;
    private int currentQuestionIndex;
    private int score; // Track user's score
    private String[] questions = {
        "What is a blockchain?",
        "What do you call each piece of data in a blockchain?",
        "What is one thing that blockchain helps to keep safe?",
        "Who can add information to a blockchain?",
        "What shape is used to describe a blockchain?"
    };
    private String[][] options = {
        {"A chain of blocks", "A type of video game", "A kind of book", "A new kind of toy"},
        {"Block", "Ball", "Book", "Box"},
        {"Toys", "Information", "Food", "Clothes"},
        {"Only the boss", "Anyone in the network", "Only kids", "Only teachers"},
        {"Circle", "Square", "Chain", "Triangle"}
    };
    private int[] correctAnswers = {0, 0, 1, 1, 2}; // Index of correct answer for each question
    private int[] userAnswers; // Store user's answers

    // Constructor with username parameter
    public EasyLevel(Category parentFrame, String username) {
        this.username = username;
        this.parentFrame = parentFrame;
        getContentPane().setMaximumSize(new Dimension(500, 500));
        setTitle("Easy Level");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the window
        setLocationRelativeTo(null); // Center the JFrame on the screen

        // Set custom content pane with background image
        String imagePath = "Resources//easyBG.png";
        setContentPane(new BackgroundPanel(imagePath));
        getContentPane().setLayout(new BorderLayout());

        BorderLayout bl_panel = new BorderLayout();
        panel = new JPanel(bl_panel);
        panel.setOpaque(false); // Make the panel transparent
        getContentPane().add(panel);

        JPanel centerPanel = new JPanel(new GridBagLayout()); // Center panel with GridBagLayout
        centerPanel.setOpaque(false); // Make the center panel transparent
        panel.add(centerPanel, BorderLayout.CENTER);

        lblQuestion = new JLabel();
        lblQuestion.setBackground(Color.RED);
        lblQuestion.setHorizontalAlignment(SwingConstants.CENTER);
        lblQuestion.setHorizontalTextPosition(SwingConstants.CENTER);
        lblQuestion.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblQuestion.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        lblQuestion.setFont(new Font("Cooper Std Black", Font.BOLD, 30));
        lblQuestion.setForeground(Color.WHITE);
        GridBagConstraints gbcLbl = new GridBagConstraints();
        gbcLbl.gridx = 0;
        gbcLbl.gridy = 0;
        gbcLbl.insets = new Insets(230, 10, 80, 10); // Add padding
        gbcLbl.anchor = GridBagConstraints.CENTER;
        centerPanel.add(lblQuestion, gbcLbl);

        JPanel optionsPanel = new JPanel(new GridLayout(2, 2, 10, 20));
        optionsPanel.setOpaque(false); // Make the options panel transparent
        GridBagConstraints gbcOptions = new GridBagConstraints();
        gbcOptions.gridx = 0;
        gbcOptions.gridy = 1;
        gbcOptions.insets = new Insets(3, 3, 100, 10); // Add padding
        centerPanel.add(optionsPanel, gbcOptions);

        optionButtons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JButton();
            optionButtons[i].setFont(new Font("Cooper Std Black", Font.PLAIN, 20));
            optionButtons[i].setForeground(Color.WHITE);
            optionButtons[i].setBackground(new Color(75, 0, 130));
            optionButtons[i].setFocusPainted(false);
            optionButtons[i].setBorder(BorderFactory.createLineBorder(Color.decode("#F7AB4A"), 5)); // Border color
            optionButtons[i].setPreferredSize(new Dimension(500, 80)); // Set preferred size for each button

            optionsPanel.add(optionButtons[i]);

            final int optionIndex = i;
            optionButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    processAnswer(optionIndex);
                }
            });
        }

        currentQuestionIndex = 0;
        score = 0; // Initialize score
        userAnswers = new int[questions.length]; // Initialize userAnswers array
        loadNextQuestion();
    }

    private void loadNextQuestion() {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            private JDialog loadingDialog;

            @Override
            protected Void doInBackground() throws Exception {
                // Display loading dialog
                loadingDialog = createLoadingDialog();
                SwingUtilities.invokeLater(() -> loadingDialog.setVisible(true));

                // Simulate loading time or heavy processing (replace with your actual logic)
                Thread.sleep(1000); // Example: Simulate loading delay

                return null;
            }

            @Override
            protected void done() {
                try {
                    get(); // Retrieve any exceptions thrown during doInBackground()
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (loadingDialog != null) {
                        loadingDialog.dispose();
                    }
                    displayQuestion();
                }
            }
        };

        worker.execute(); // Start the SwingWorker
    }

    private JDialog createLoadingDialog() {
        JDialog dialog = new JDialog(this, "Loading", true);
        JLabel label = new JLabel("Loading next question...");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        dialog.getContentPane().add(label, BorderLayout.CENTER);
        dialog.setSize(300, 100);
        dialog.setLocationRelativeTo(this); // Center dialog relative to EasyLevel frame
        return dialog;
    }

    private void displayQuestion() {
        lblQuestion.setText("Question " + (currentQuestionIndex + 1) + ": " + questions[currentQuestionIndex]);
        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText(options[currentQuestionIndex][i]);
        }
    }

    private void processAnswer(int selectedOptionIndex) {
        // Store the user's answer
        userAnswers[currentQuestionIndex] = selectedOptionIndex;

        // Check if the selected answer is correct
        if (selectedOptionIndex == correctAnswers[currentQuestionIndex]) {
            score++;
        }

        // Always move to the next question
        currentQuestionIndex++;

        // Check if all questions are answered
        if (currentQuestionIndex < questions.length) {
            loadNextQuestion(); // Load next question in background
        } else {
            // Show score and completion message
            showFinalScore();
        }
    }

    private void showFinalScore() {
        int totalQuestions = questions.length;
        String message = "You answered " + score + " out of " + totalQuestions + " questions correctly!";
        JOptionPane.showMessageDialog(this, message);

        // Display the incorrect answers and the correct answers
        StringBuilder answersMessage = new StringBuilder("Here are the questions you got wrong:");
        boolean hasWrongAnswers = false;
        for (int i = 0; i < totalQuestions; i++) {
            if (userAnswers[i] != correctAnswers[i]) {
                hasWrongAnswers = true;
                answersMessage.append("\nQuestion ").append(i + 1).append(": ").append(questions[i]);
                answersMessage.append("\nYour answer: ").append(options[i][userAnswers[i]]);
                answersMessage.append("\nCorrect answer: ").append(options[i][correctAnswers[i]]).append("\n");
            }
        }

        if (hasWrongAnswers) {
            JOptionPane.showMessageDialog(this, answersMessage.toString());
        } else {
            JOptionPane.showMessageDialog(this, "Congratulations! You got all the questions correct!");
        }

        // Ask the user if they would like to try again or proceed to the Average Level
        String finalMessage = "Would you like to try again or proceed to the Average Level?";
        int choice = JOptionPane.showOptionDialog(this, finalMessage, "Quiz Completed",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new String[]{"Try Again", "Proceed to Average Level"}, "Try Again");

        if (choice == JOptionPane.YES_OPTION) {
            // Reset quiz for retry
            currentQuestionIndex = 0;
            score = 0;
            loadNextQuestion();
        } else {
            updateScore(username, "easy_score", score); // Save the score in the database
            parentFrame.completeEasyLevel(); // Notify the Category frame
            parentFrame.setVisible(true); // Show the Category frame
            dispose(); // Close the frame when all questions are answered
        }
    }

    private void updateScore(String username, String level, int score) {
        String SUrl = "jdbc:mysql://localhost:3306/quizdb";
        String SUser = "root";
        String Spass = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SUrl, SUser, Spass);
            String query = "UPDATE admin_track SET " + level + " = ? WHERE username = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, score);
            ps.setString(2, username);
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Custom JPanel to paint the background image
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            try {
                backgroundImage = new ImageIcon(imagePath).getImage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}
