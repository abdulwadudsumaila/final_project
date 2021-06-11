import java.awt.*;
  import java.awt.event.*;
  import javax.swing.*;

  public class main extends JPanel
  {
      //declare GUI elements
      private JLabel subjectLabel, cHoursLabel, gradeLabel;       
      private JTextField subject, cHours;
      private JButton addSubjectButton, calcGPAButton, clearAllButton;
      private JTextArea tArea;
      private JComboBox grade;

      //declare array to store and collect user input value
      String[] subjectArray = new String[6]; 
      String[] ScoreArray = new String[6];
      String[] gradeArray = new String[6];
      int[] cHoursArray = new int[6];
      double[] gradeValue = { 5.00, 4.50, 4.49, 3.50, 3.49, 2.50, 2.49, 2.00, 1.99};
      String[] gradeLetter= { "A+",  "A", "B+", "B",  "C+", "C", "D+",  "D",  "F"};                  
      private JTextField Score;
      private JLabel scoreLabel;
      private JScrollPane scrollPane;


      public main()
      {   
          setLayout (null);
          setPreferredSize (new Dimension(500, 500));
          setBackground (Color.orange); 

          //Properties of GUI elements
          subjectLabel = new JLabel ("Subject Name: ");                       
          subjectLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
          subject = new JTextField (33);
          subject.addActionListener (new TempListener());

          gradeLabel = new JLabel ("Grade: ");
          gradeLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
          grade = new JComboBox (gradeLetter); 
          grade.addActionListener (new TempListener());

          cHoursLabel = new JLabel ("Credit Hours: ");
          cHoursLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
          cHours = new JTextField (1);
          cHours.addActionListener (new TempListener());

          addSubjectButton = new JButton("Add Another Subject");
          addSubjectButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
          addSubjectButton.addActionListener(new TempListener());
          calcGPAButton = new JButton("Calculate GPA");
          calcGPAButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
          calcGPAButton.addActionListener(new TempListener());
          clearAllButton = new JButton("Clear All");
          clearAllButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
          clearAllButton.addActionListener(new TempListener());

          add (subjectLabel);
          add (subject);
          add (gradeLabel);
          add (grade);
          add (cHoursLabel);
          add (cHours);
          add (addSubjectButton);
          add (calcGPAButton);
          add (clearAllButton);
          
          scrollPane = new JScrollPane();
          scrollPane.setBounds(20, 198, 450, 250);
          add(scrollPane);
          
                    tArea = new JTextArea(5, 5);                
                    scrollPane.setViewportView(tArea);
                    tArea.setEditable(false);     

          //Position of GUI elements
          subjectLabel.setBounds      (20, 20, 150, 20);              
          subject.setBounds           (120, 20, 350, 20);
          gradeLabel.setBounds        (20, 112, 50, 20);
          grade.setBounds             (120, 112, 50, 20);
          cHoursLabel.setBounds       (20, 80, 100, 20);
          cHours.setBounds            (120, 81, 50, 20);
          addSubjectButton.setBounds  (20, 157, 200, 30);
          calcGPAButton.setBounds     (295, 459, 175, 30);
          clearAllButton.setBounds    (20, 459, 120, 30);
          
          Score = new JTextField(1);
          Score.setBounds(120, 51, 50, 20);
          add(Score);
          
          scoreLabel = new JLabel("Score(%)");
          scoreLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
          scoreLabel.setBounds(20, 55, 76, 16);
          add(scoreLabel);
      }


      private class TempListener implements ActionListener
      {
        //---------------------------------------------------------------------------
        //  Performs the conversion when the enter key is pressed in the text field.
        //---------------------------------------------------------------------------
          double tCrPoints = 0.00, tCrHours = 0.00, tGPA = 0.00;  
          String status; 

          public void actionPerformed(ActionEvent event)
          {
              if (event.getSource() == addSubjectButton)                   
              {
                  for (int i=0; i<6; i++)
                  {
                      subjectArray[i] = subject.getText();
                      ScoreArray[i] = Score.getText();
                      cHoursArray[i] = Integer.parseInt(cHours.getText());
                      gradeArray[i] = (String) grade.getSelectedItem();
                  }

                  tArea.append (subject.getText() + "\t" +
                                cHours.getText() + "\t\t" +
                		        Score.getText() + "\t" +
                                grade.getSelectedItem() + "\n"); 
                  subject.setText("");
                  cHours.setText(""); 
              }


              if (event.getSource() == calcGPAButton)   
              {
                  for (int i=0 ; i<gradeArray.length; i++)
                  {
                      for (int j=0; j<gradeLetter.length; j++)
                      {
                          if(gradeArray[i].equals(gradeLetter[j]))
                          {
                              tCrHours += cHoursArray[i]; 
                              tCrPoints += gradeValue[j] * cHoursArray[i];
                          }
                      }
                  }

                  tGPA = tCrPoints/tCrHours;

                  if (tGPA >= 2)
                      status = ("Pass");
                  else
                      status = ("Fail"); 

                  //Output for text area 
                  tArea.setText("Total Credit Points : " + tCrPoints + "\n" +
                                "Total Credit Hours : " + tCrHours + "\n\n" + 
                                "Grade Point Average (GPA) : " + tGPA + "\n" +
                                "Status : " + status); 
              } 


              if (event.getSource() == clearAllButton)  
              {
                  tArea.setText("");
                  cHours.setText("");
                  grade.setSelectedIndex(0);
                  tCrHours = 0.00;
                  tCrPoints = 0.00;
              }


         }





      }
  }
