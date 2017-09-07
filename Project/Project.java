package Project;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Project extends JFrame{

    private JLabel instructions, instuctions2, firstWordLabel, secWordLabel;
    private JTextField firstWordText, secWordText;
    private JButton anaButton, palButton;
    private JPanel topPanel, textPanel, buttonPanel;
    private boolean anagram, pFirst, pSecond;

    public Project() {

        setTitle("Anagram and Palindrome Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        instructions = new JLabel("Enter two words to test if they are anagrams of each other.");
        instuctions2 = new JLabel("Enter a word in either or both to see if they are palindromes.");

        instructions.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
        instuctions2.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));

        topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.PAGE_AXIS));
        topPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        topPanel.add(instructions);
        topPanel.add(instuctions2);
        topPanel.add(new JSeparator(SwingConstants.HORIZONTAL));

        buildTextPanel();
        buildButtonPanel();

        add(topPanel,BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        pack();

        setVisible(true);
    }
    //-----------------------------------------------------------------------------------------------------------
    private void buildTextPanel() {

        textPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 5, 0, 5);

        firstWordLabel = new JLabel("Enter First Word:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        textPanel.add(firstWordLabel, c);

        firstWordText = new JTextField(30);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        textPanel.add(firstWordText, c);

        secWordLabel = new JLabel("Enter Second Word:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        textPanel.add(secWordLabel, c);

        secWordText = new JTextField(30);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        textPanel.add(secWordText, c);
    }
    //------------------------------------------------------------------------------------------------------------
    private void buildButtonPanel() {

        buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        anaButton = new JButton("Test if Anagrams");
        palButton = new JButton("Test if Either Are Palindromes");

        anaButton.addActionListener(new anagramButtonListener());
        palButton.addActionListener(new palinromButtonListener());

        buttonPanel.add(anaButton);
        buttonPanel.add(palButton);

    }
    //------------------------------------------------------------------------------------------------------------
    private class anagramButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            //get text from both text fields
            String word1 = firstWordText.getText();
            String word2 = secWordText.getText();

            Anagram checkAnagram = new Anagram();
            anagram = checkAnagram.Compare(word1, word2);       //check if they are anagrams

            //make sure both fields aren't left blank
            if(word1.equals("") || word2.equals(""))
                JOptionPane.showMessageDialog(null, "Please enter words " +
                        "in both text fields", "Blank Fields", JOptionPane.ERROR_MESSAGE);

            //both are anagrams
            else if(anagram == true)
                JOptionPane.showMessageDialog(null, word1 + " and " + word2 +
                        " are anagrams of each other!","Anagrams Found", JOptionPane.PLAIN_MESSAGE);

            //not found
            else
                JOptionPane.showMessageDialog(null, word1 + " and " + word2 + " are not " +
                                "anagrams of each other","No Anagram Found", JOptionPane.WARNING_MESSAGE);
        }
    }
    //-------------------------------------------------------------------------------------------------------------
    private class palinromButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            String word1 = firstWordText.getText();
            String word2 = secWordText.getText();

            Anagram checkPalindrome = new Anagram();
            pFirst = checkPalindrome.Palindrome(word1);
            pSecond = checkPalindrome.Palindrome(word2);

            //make sure both fields aren't left blank
            if(word1.equals("") && word2.equals(""))
                JOptionPane.showMessageDialog(null, "Please enter words " +
                        "in at least one of the text fields", "Blank Fields", JOptionPane.ERROR_MESSAGE);

            //if both are palindromes
            else if(pFirst == true && pSecond == true)
                JOptionPane.showMessageDialog(null, word1 + " and " + word2 +
                        " are Palindromes!","Palindromes Found", JOptionPane.PLAIN_MESSAGE);

            //if the first is a palindrome but the second textfield is blank, or the 2nd word is not a palindrome
            else if(pFirst == true && (word2.equals("") || pSecond == false))
                JOptionPane.showMessageDialog(null, word1 + " is a Palindrome!",
                        "Palindrome Found", JOptionPane.PLAIN_MESSAGE);

                //if the second is a palindrome but the first textfield is blank, or the 1st word is not a palindrome
            else if((word1.equals("") || pFirst == false) && pSecond == true)
                JOptionPane.showMessageDialog(null, word2 + " is a Palindrome!",
                        "Palindrome Found", JOptionPane.PLAIN_MESSAGE);

            //nothing found
            else
                JOptionPane.showMessageDialog(null, "Neither are Palindromes",
                        "No Palindrome Found", JOptionPane.WARNING_MESSAGE);
        }
    }
    public static void main(String[] args) {

        new Project();
    }
}
