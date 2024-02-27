package notepad1;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class About extends JFrame implements ActionListener{

    JButton b1;

    About(){
        
        setBounds(400, 100, 700,600);
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("notepad1/icon/windows.png"));
        Image i2 = i1.getImage().getScaledInstance(400, 80, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel headerIcon = new JLabel(i3);
        headerIcon.setBounds(150, 40, 400, 80);
        add(headerIcon);
        
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("notepad1/icon/notepad.png"));
        Image i5 = i4.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel Icon = new JLabel(i6);
        Icon.setBounds(50, 180, 70, 70);
        add(Icon);
        
        
        JLabel text = new JLabel("<html>Code for Interview<br>Youtube Channel Version 2021<br>2021 Code for Interview. All rights reserved<br><br>Notepad is a word processing program, <br>which allows changing of text in a computer file.<br>Notepad is a simple text editor for basic text-editing program<br> which enables computer users to create documents. </html>");
        text.setFont(new Font("SAN_SERIF", Font.PLAIN, 18));
        text.setBounds(150, 130, 500, 300);
        add(text);
        
        b1 = new JButton("OK");
        b1.setBounds(580, 500, 80, 25);
        b1.addActionListener(this);
        add(b1);
       
        setVisible(true);
    }
 public static void main(String[] args) {
    new About();
 }
public void actionPerformed(ActionEvent e) {
    this.setVisible(false);
}
}
