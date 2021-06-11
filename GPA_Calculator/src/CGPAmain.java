import javax.swing.*;

public class CGPAmain extends JFrame
{
  public static void main(String[] args)
  {
    JFrame frame = new JFrame("GPA Calculation");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    main panel = new main();
    frame.getContentPane().add(panel);

    frame.pack();
    frame.setVisible(true);
  }
}
