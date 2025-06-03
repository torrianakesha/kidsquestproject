package Quiz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@SuppressWarnings("serial")
public class AverageLevel extends JFrame {

    private String username;
    private Category parentFrame;
    private JPanel panel;
    private JTextArea txtQuestion;
    private JButton[] optionButtons;
    private int currentQuestionIndex;
    private int score;
    private String[] questions = {
            "What is the main benefit of blockchain technology?",
            "How are new blocks added to the blockchain?",
            "What does it mean if information is decentralized in a blockchain?",
            "What kind of digital money uses blockchain?",
            "Why is it hard to change information once it's on the blockchain?",
            "Blockchain technology can be easily hacked.",
            "All cryptocurrencies use blockchain technology.",
            "Decentralization means that no single entity controls the blockchain.",
            "Information on a blockchain can be altered without leaving a trace.",
            "Blockchain technology is used only for digital currencies."
    };
    private String[][] options = {
            {"It’s fun to play with", "Keeps information secure and transparent", "It makes a lot of noise", "It changes colors"},
            {"By typing fast", "Through a process called mining", "By drawing them", "By magic"},
            {"It’s all kept in one place", "It’s spread out across many places", "It’s written on paper", "It’s thrown away"},
            {"Toy dollars", "Gold coins", "Cryptocurrency", "Candy coins"},
            {"Because it's protected by superheroes", "Because everyone can see and verify it", "Because it’s made of metal", "Because it’s hidden under a rock"},
            {"True", "False"},
            {"True", "False"},
            {"True", "False"},
            {"True", "False"},
            {"True", "False"}
    };
    private int[] correctAnswers = {1, 1, 1, 2, 1, 1, 0, 0, 1, 1}; // Index of correct answer for each question
    private int[] userAnswers; // Store user's answers

    // Constructor with username parameter
    public AverageLevel(Category parentFrame, String username) {
        this.username = username;
        this.parentFrame = parentFrame;
        getContentPane().setMaximumSize(new Dimension(800, 500));
        setTitle("Easy Level");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the window
        setLocationRelativeTo(null); // Center the JFrame on the screen

        // BACKGROUND IMAGE
        JPanel contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image backgroundImage = new ImageIcon("Resources\\aveBG.png").getImage();
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                g.drawImage(backgroundImage, 0, 0, screenSize.width, screenSize.height, this);
            }
        };

        // Optional: Remove any border around the image
        contentPane.setBorder(BorderFactory.createEmptyBorder());

        // Set the content pane
        setContentPane(contentPane);
        contentPane.setLayout(null);

        txtQuestion = new JTextArea();
        txtQuestion.setBackground(Color.RED);
        txtQuestion.setFont(new Font("Cooper Std Black", Font.BOLD, 30));
        txtQuestion.setForeground(Color.WHITE);
        txtQuestion.setWrapStyleWord(true);
        txtQuestion.setLineWrap(true);
        txtQuestion.setEditable(false);
        txtQuestion.setOpaque(false);
        txtQuestion.setBounds(283, 320, 954, 108); // Set bounds
        contentPane.add(txtQuestion);

        JPanel optionsPanel = new JPanel(new GridLayout(2, 2, 50, 20));
        optionsPanel.setOpaque(false); // Make the options panel transparent
        optionsPanel.setBounds(283, 450, 954, 200); // Set bounds for options panel
        contentPane.add(optionsPanel);

        optionButtons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JButton();
            optionButtons[i].setFont(new Font("Cooper Std Black", Font.PLAIN, 16));
            optionButtons[i].setForeground(Color.WHITE);
            optionButtons[i].setBackground(new Color(75, 0, 130));
            optionButtons[i].setFocusPainted(false);

            // Update border color to #4100C8
            optionButtons[i].setBorder(BorderFactory.createLineBorder(Color.decode("#F7AB4A"), 5));

            // Set preferred size for each button
            optionButtons[i].setPreferredSize(new Dimension(600, 80)); // Adjust dimensions as needed

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
        score = 0;
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
        dialog.setLocationRelativeTo(this); // Center dialog relative to AverageLevel frame
        return dialog;
    }

    private void displayQuestion() {
        txtQuestion.setText("Question " + (currentQuestionIndex + 1) + ": " + questions[currentQuestionIndex]);
        for (int i = 0; i < 4; i++) {
            if (i < options[currentQuestionIndex].length) {
                optionButtons[i].setText(options[currentQuestionIndex][i]);
                optionButtons[i].setVisible(true);
            } else {
                optionButtons[i].setVisible(false);
            }
        }
    }

    private void processAnswer(int selectedOptionIndex) {
        // Store the user's answer
        userAnswers[currentQuestionIndex] = selectedOptionIndex;

        // Check if the selected answer is correct
        if (selectedOptionIndex == correctAnswers[currentQuestionIndex]) {
            score++;
        }
        currentQuestionIndex++;
        if (currentQuestionIndex < questions.length) {
            loadNextQuestion(); // Load next question in background
        } else {
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

        // Ask the user if they would like to try again or proceed to the Hard Level
        String finalMessage = "Would you like to try again or proceed to the Hard Level?";
        int choice = JOptionPane.showOptionDialog(this, finalMessage, "Quiz Completed",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new String[]{"Try Again", "Proceed to Hard Level"}, "Try Again");

        if (choice == JOptionPane.YES_OPTION) {
            // Reset quiz
            currentQuestionIndex = 0;
            score = 0;
            loadNextQuestion();
        } else {
            parentFrame.completeAverageLevel(); // Notify the Category frame
            parentFrame.setVisible(true); // Show the Category frame
            updateScore(username, "average_score", score); // Update average score in the database
            dispose(); // Close the frame when all questions are answered
        }
    }

    private void updateScore(String username, String column, int score) {
        String SUrl = "jdbc:mysql://localhost:3306/quizdb";
        String SUser = "root";
        String Spass = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SUrl, SUser, Spass);
            String query = "UPDATE admin_track SET " + column + " = ? WHERE username = ?";
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
}
