package soup;


import java.awt.Toolkit;
import java.time.LocalDate;
import javax.swing.*;
import java.awt.*;


public class Notification {

	
	
	public static void showNotification(String title, String message,LocalDate curr) {
        // Create a small undecorated frame
        JFrame frame = new JFrame();
        frame.setUndecorated(true);
        frame.setAlwaysOnTop(true);
        frame.setLayout(new BorderLayout());

        // Panel for content
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        panel.setBackground(Color.WHITE);

        // Title label
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));

        // Message label
        
        JTextArea messageLabel = new JTextArea(message);
        messageLabel.setEditable(false);
  


        messageLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
        

        // Close button
        JButton closeButton = new JButton("×");
        closeButton.setMargin(new Insets(0, 5, 0, 5));
        closeButton.setFocusPainted(false);
        closeButton.setBorderPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setFont(new Font("Arial", Font.BOLD, 16));
        closeButton.setForeground(Color.RED);
        closeButton.addActionListener(e -> frame.dispose());
        
        
        //Left Button
        JButton leftButton = new JButton("<");
        leftButton.addActionListener(e ->   Notification.showNotification(ScoreParser.fixFormat(ScoreParser.yesterday(curr)), ScoreParser.pullScores(ScoreParser.yesterday(curr)),ScoreParser.convertString(ScoreParser.yesterday(curr))));
        leftButton.setMargin(new Insets(0, 5, 0, 5));
        leftButton.setFocusPainted(false);
        leftButton.setBorderPainted(false);
        leftButton.setContentAreaFilled(false);
        leftButton.setFont(new Font("Arial", Font.BOLD, 16));
        leftButton.setForeground(Color.BLUE);
        leftButton.addActionListener(e ->  frame.dispose());
        
        //Right Button
        JButton rightButton = new JButton(">");
        rightButton.addActionListener(e ->   Notification.showNotification(ScoreParser.fixFormat(ScoreParser.tomorrow(curr)), ScoreParser.pullScores(ScoreParser.tomorrow(curr)),ScoreParser.convertString(ScoreParser.tomorrow(curr))));
        rightButton.setMargin(new Insets(0, 5, 0, 5));
        rightButton.setFocusPainted(false);
        rightButton.setBorderPainted(false);
        rightButton.setContentAreaFilled(false);
        rightButton.setFont(new Font("Arial", Font.BOLD, 16));
        rightButton.setForeground(Color.BLUE);
        rightButton.addActionListener(e ->  frame.dispose());

        // Top bar with title and close button
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(Color.LIGHT_GRAY);
        topBar.add(titleLabel, BorderLayout.WEST);
        topBar.add(closeButton, BorderLayout.EAST);
        
        //Bottom with day arrows
        JPanel bottomBar = new JPanel(new BorderLayout());
        bottomBar.setBackground(Color.LIGHT_GRAY);
       bottomBar.add(leftButton,BorderLayout.WEST);
       bottomBar.add(rightButton,BorderLayout.EAST);
       
        panel.add(bottomBar,BorderLayout.SOUTH);
        panel.add(topBar, BorderLayout.NORTH);
        panel.add(messageLabel, BorderLayout.CENTER);

        frame.add(panel);

        // Set size and position (bottom-right corner)
        JScrollPane scrollPane = new JScrollPane(messageLabel);
        scrollPane.setPreferredSize(new Dimension(250, 1000)); // fixed size
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        panel.add(scrollPane, BorderLayout.CENTER);
        
        frame.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - messageLabel.getWidth());
        int y = (screenSize.height - screenSize.height);
        frame.setLocation(x, y);

        frame.setVisible(true);
    }
}
