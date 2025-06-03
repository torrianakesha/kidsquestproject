package Quiz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HardLevel extends JFrame {

    private String username;
    private JPanel mainPanel;
    private JButton[] termButtons;
    private JButton[] definitionButtons;
    private JButton doneButton;
    private String[] terms = {
            "Blockchain", "Miner", "Cryptocurrency", "Bitcoin", "Decentralization"
    };
    private String[] definitions = {
            "A type of technology that keeps information safe and transparent.",
            "Someone who helps add new blocks to the blockchain by solving complex problems.",
            "A digital currency that uses blockchain technology to operate.",
            "A type of digital money that relies on blockchain to keep transactions safe and transparent.",
            "A key feature of blockchain technology that spreads data across multiple locations."
    };
    private int[] termIndices;
    private int[] definitionIndices;
    private int[] chosenPairs;
    private int selectedTermIndex = -1;
    private int selectedDefinitionIndex = -1;
    private int score = 0;

    public HardLevel(String username) {
        this.username = username; // Store the username
        setTitle("Hard Level");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the window
        setLocationRelativeTo(null);

        // Set custom content pane with background image
        String imagePath = "Resources//hardBack.png";
        setContentPane(new BackgroundPanel(imagePath));
        getContentPane().setLayout(new BorderLayout());

        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(230, 100, 50, 100));
        mainPanel.setOpaque(false); // Make panel transparent
        add(mainPanel, BorderLayout.CENTER);

        termButtons = new JButton[terms.length];
        definitionButtons = new JButton[definitions.length];

        termIndices = new int[terms.length];
        definitionIndices = new int[definitions.length];
        chosenPairs = new int[terms.length];
        for (int i = 0; i < terms.length; i++) {
            termIndices[i] = i;
            definitionIndices[i] = i;
            chosenPairs[i] = -1; // Initialize with -1 to indicate no pair selected
        }

        // Shuffle the arrays
        shuffleArray(termIndices);
        shuffleArray(definitionIndices);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;

        for (int i = 0; i < terms.length; i++) {
            termButtons[i] = createButton(terms[termIndices[i]], new TermButtonListener(i));
            gbc.gridx = i;
            gbc.gridy = 0;
            mainPanel.add(termButtons[i], gbc);
        }

        for (int i = 0; i < definitions.length; i++) {
            definitionButtons[i] = createButton("<html>" + definitions[definitionIndices[i]] + "</html>", new DefinitionButtonListener(i));
            definitionButtons[i].setHorizontalAlignment(SwingConstants.CENTER); // Center text
            gbc.gridx = i;
            gbc.gridy = 1;
            mainPanel.add(definitionButtons[i], gbc);
        }

        doneButton = new JButton("Done");
        doneButton.addActionListener(e -> showResults());
        doneButton.setEnabled(false); // Disable the "Done" button initially
        JPanel buttonPanel = createPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(doneButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setBounds(50, 20, 1406, 700); // Set the size of the window
    }

    private JButton createButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.setFont(new Font("Cooper Std Black", Font.PLAIN, 20)); // Font "Cooper Std Black" size 20
        button.setForeground(Color.WHITE); // White foreground color
        button.setBackground(new Color(75, 0, 130)); // Background color RGB(75, 0, 130)
        button.setFocusPainted(false); // Remove focus border 
        button.setBorder(BorderFactory.createLineBorder(Color.decode("#F7AB4A"), 5)); // Initial border color

        button.setPreferredSize(new Dimension(200, 100)); // Set preferred size for consistency

        button.addActionListener(listener);
        return button;
    }

    private JPanel createPanel(LayoutManager layout) {
        JPanel panel = new JPanel(layout);
        panel.setOpaque(false); // Make panel transparent
        return panel;
    }

    private class TermButtonListener implements ActionListener {
        private final int index;

        public TermButtonListener(int index) {
            this.index = index;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (selectedTermIndex != -1) {
                // Deselect previously selected button
                termButtons[selectedTermIndex].setBorder(BorderFactory.createLineBorder(Color.decode("#F7AB4A"), 5));
            }

            if (selectedTermIndex == index) {
                // Same button clicked again, deselect
                selectedTermIndex = -1;
            } else {
                // Set selected button border color
                selectedTermIndex = index;
                termButtons[selectedTermIndex].setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.GREEN, 5),
                        BorderFactory.createLineBorder(Color.YELLOW, 2))); // Gray stroke for reselection
            }

            checkMatch();
        }
    }

    private class DefinitionButtonListener implements ActionListener {
        private final int index;

        public DefinitionButtonListener(int index) {
            this.index = index;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (selectedDefinitionIndex != -1) {
                // Deselect previously selected button
                definitionButtons[selectedDefinitionIndex].setBorder(BorderFactory.createLineBorder(Color.decode("#F7AB4A"), 5));
            }

            if (selectedDefinitionIndex == index) {
                // Same button clicked again, deselect
                selectedDefinitionIndex = -1;
            } else {
                // Set selected button border color
                selectedDefinitionIndex = index;
                definitionButtons[selectedDefinitionIndex].setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.GREEN, 5),
                        BorderFactory.createLineBorder(Color.YELLOW, 2))); // Gray stroke for reselection
            }

            checkMatch();
        }
    }

    private void checkMatch() {
        if (selectedTermIndex != -1 && selectedDefinitionIndex != -1) {
            chosenPairs[selectedTermIndex] = selectedDefinitionIndex;
            selectedTermIndex = -1;
            selectedDefinitionIndex = -1;

            // Check if all pairs have been matched
            if (allPairsMatched()) {
                doneButton.setEnabled(true); // Enable the "Done" button
            }
        }
    }

    private boolean allPairsMatched() {
        for (int chosenPair : chosenPairs) {
            if (chosenPair == -1) { // Check if any pair is not selected
                return false;
            }
        }
        return true;
    }

    private void showResults() {
        // Use SwingWorker to calculate the score and show results
        SwingWorker<Integer, Void> scoreWorker = new SwingWorker<>() {
            @Override
            protected Integer doInBackground() throws Exception {
                return calculateScore(chosenPairs);
            }

            @Override
            protected void done() {
                try {
                    score = get();
                    JOptionPane.showMessageDialog(HardLevel.this, "You Scored: " + score + " out of " + terms.length);

                    // Show correct pairs
                    StringBuilder correctPairsMessage = new StringBuilder("<html>Correct Pairs:<br>");
                    StringBuilder incorrectPairsMessage = new StringBuilder("<html>Incorrect Pairs:<br>");
                    boolean hasWrongAnswers = false;
                    for (int i = 0; i < terms.length; i++) {
                        if (termIndices[i] == definitionIndices[chosenPairs[i]]) {
                            correctPairsMessage.append(terms[i]).append(" - ").append(definitions[i]).append("<br>");
                        } else {
                            hasWrongAnswers = true;
                            incorrectPairsMessage.append(terms[termIndices[i]]).append(" - ").append(definitions[definitionIndices[chosenPairs[i]]]).append("<br>");
                            incorrectPairsMessage.append("Correct Pair: ").append(terms[termIndices[i]]).append(" - ").append(definitions[termIndices[i]]).append("<br>");
                        }
                    }
                    correctPairsMessage.append("</html>");
                    incorrectPairsMessage.append("</html>");

                    JOptionPane.showMessageDialog(HardLevel.this, correctPairsMessage.toString());
                    if (hasWrongAnswers) {
                        JOptionPane.showMessageDialog(HardLevel.this, incorrectPairsMessage.toString());
                    } else {
                        JOptionPane.showMessageDialog(HardLevel.this, "Congratulations! You got all the pairs correct!");
                    }

                    // Disable all buttons
                    for (JButton button : termButtons) {
                        button.setEnabled(false);
                    }
                    for (JButton button : definitionButtons) {
                        button.setEnabled(false);
                    }

                    // Update the score in the database
                    updateScore(username, "hard_score", score);

                    // Add Try Again and Return buttons
                    int option = JOptionPane.showOptionDialog(HardLevel.this,
                            "Would you like to try again or return to the category selection?",
                            "Choose an option",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            new String[]{"Try Again", "Return to Category"},
                            "Try Again");

                    if (option == JOptionPane.YES_OPTION) {
                        resetGame();
                    } else {
                        Category catFrame = new Category(username);
                        catFrame.setVisible(true);
                        dispose(); // Close the current frame
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        scoreWorker.execute();
    }

    private void resetGame() {
        shuffleArray(termIndices);
        shuffleArray(definitionIndices);
        for (int i = 0; i < terms.length; i++) {
            chosenPairs[i] = -1;
            termButtons[i].setText(terms[termIndices[i]]);
            termButtons[i].setBorder(BorderFactory.createLineBorder(Color.decode("#F7AB4A"), 5));
            termButtons[i].setEnabled(true);
            definitionButtons[i].setText("<html>" + definitions[definitionIndices[i]] + "</html>");
            definitionButtons[i].setBorder(BorderFactory.createLineBorder(Color.decode("#F7AB4A"), 5));
            definitionButtons[i].setEnabled(true);
        }
        doneButton.setEnabled(false);
    }

    private void shuffleArray(int[] array) {
        List<Integer> list = new ArrayList<>();
        for (int value : array) {
            list.add(value);
        }
        Collections.shuffle(list);
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
    }

    private int calculateScore(int[] chosenPairs) {
        int score = 0;
        for (int i = 0; i < chosenPairs.length; i++) {
            if (termIndices[i] == definitionIndices[chosenPairs[i]]) {
                score++;
            }
        }
        return score;
    }

    private void updateScore(String username, String level, int score) {
        String SUrl = "jdbc:mysql://localhost:3306/quizdb";
        String SUser = "root";
        String Spass = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SUrl, SUser, Spass);

            // Update the specific level score
            String query = "UPDATE admin_track SET " + level + " = ? WHERE username = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, score);
            ps.setString(2, username);
            ps.executeUpdate();

            // Retrieve the updated scores
            query = "SELECT easy_score, average_score, hard_score FROM admin_track WHERE username = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            int easyScore = 0, averageScore = 0, hardScore = 0;
            if (rs.next()) {
                easyScore = rs.getInt("easy_score");
                averageScore = rs.getInt("average_score");
                hardScore = rs.getInt("hard_score");
            }

            // Calculate the total score and determine the evaluation
            int totalScore = easyScore + averageScore + hardScore;
            String evaluation = calculateEvaluation(totalScore);

            // Update the evaluation in the database
            query = "UPDATE admin_track SET evaluation = ? WHERE username = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, evaluation);
            ps.setString(2, username);
            ps.executeUpdate();

            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String calculateEvaluation(int totalScore) {
        if (totalScore >= 0 && totalScore <= 5) {
            return "Novice";
        } else if (totalScore >= 6 && totalScore <= 10) {
            return "Beginner";
        } else if (totalScore >= 11 && totalScore <= 15) {
            return "Intermediate";
        } else if (totalScore >= 16 && totalScore <= 20) {
            return "Advanced";
        } else {
            return "Unknown";
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
