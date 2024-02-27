package notepad1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.filechooser.*;

public class Notepad1 extends JFrame implements ActionListener{

    static JTextArea area;
    String text;

    Notepad1(){


        //setting the header of frame
        setTitle("Notepad");
        ImageIcon notepadIcon = new ImageIcon(ClassLoader.getSystemResource("notepad1/icon/notepad.png"));
        Image icon = notepadIcon.getImage(); //image icon object is change into image object
        setIconImage(icon);
        
        
                      //making a menubar   

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.WHITE);

                       //for file menu->

        JMenu file = new JMenu("File");
        file.setFont(new Font("AERIAL" , Font.PLAIN ,14));

        JMenuItem newdoc = new JMenuItem("New");
        newdoc.addActionListener(this);
        newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N , ActionEvent.CTRL_MASK));
        file.add(newdoc);

        
        JMenuItem open = new JMenuItem("Open");
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O , ActionEvent.CTRL_MASK));
        open.addActionListener(this);
        file.add(open);

        
        JMenuItem save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S , ActionEvent.CTRL_MASK));
        save.addActionListener(this);
        file.add(save);

        
        JMenuItem print = new JMenuItem("Print");
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P , ActionEvent.CTRL_MASK));
        print.addActionListener(this);
        file.add(print);

        
        JMenuItem exit = new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE , ActionEvent.CTRL_MASK));
        exit.addActionListener(this);
        file.add(exit);


                        //for edit menubar->

        JMenu edit = new JMenu("Edit");
        edit.setFont(new Font("AERIAL" , Font.PLAIN ,14));

        JMenuItem copy = new JMenuItem("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C , ActionEvent.CTRL_MASK));
        copy.addActionListener(this);
        edit.add(copy);

        
        JMenuItem cut = new JMenuItem("Cut");
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X , ActionEvent.CTRL_MASK));
        cut.addActionListener(this);
        edit.add(cut);

        
        JMenuItem paste = new JMenuItem("Paste");
        paste.addActionListener(this);
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V , ActionEvent.CTRL_MASK));
        edit.add(paste);

        
        JMenuItem selectAll = new JMenuItem("SelectAll");
        selectAll.addActionListener(this);
        selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A , ActionEvent.CTRL_MASK));
        edit.add(selectAll);

                        //for help menu ->

        JMenu about = new JMenu("About");
        about.setFont(new Font("AERIAL" , Font.PLAIN ,14));

        JMenuItem help = new JMenuItem("About");
        help.addActionListener(this);
        help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H , ActionEvent.CTRL_MASK));
        about.add(help);
        

        

        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(about);

        //set menubar in screen
        setJMenuBar(menuBar);


        //text area
        area = new JTextArea();
        area.setFont(new Font("SAN_SERIF" , Font.PLAIN , 18));
        area.setLineWrap(true);//for next line
        area.setWrapStyleWord(true);//go to next line for whole word not half
        

        //for scroll bar
        JScrollPane pane = new JScrollPane(area);
        pane.setBorder(BorderFactory.createEmptyBorder());//to remove the border of scroll bar
        add(pane);//we put text area in scroll bar 


        setExtendedState(JFrame.MAXIMIZED_BOTH);//for size or state of frame to set full screen

        
       

        setVisible(true);//to make frame visible 
    }
    public void actionPerformed(ActionEvent ae) {
        if(ae.getActionCommand().equals("New")){
            area.setText("");
        }else if (ae.getActionCommand().equals("Open")) {
            JFileChooser chooser = new JFileChooser();//to choose a file from manager
            chooser.setAcceptAllFileFilterUsed(false);//not to choose or select all file 
            FileNameExtensionFilter restrict  = new FileNameExtensionFilter("Only .text files" , "txt");//which file has to choose
            chooser.addChoosableFileFilter(restrict);

            int action =chooser.showOpenDialog(this);//open dialog
            if (action != JFileChooser.APPROVE_OPTION ) {
                return;
            }
            File file = chooser.getSelectedFile();
            try {
                //to read file -> bufferedreader     to write on file -> bufferedwritter
               BufferedReader reader = new BufferedReader(new FileReader(file));
               area.read(reader ,null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (ae.getActionCommand().equals("Save")) {
            JFileChooser SaveAs = new JFileChooser();
            SaveAs.setApproveButtonText("Save");//to set the text on the button
            int action =SaveAs.showOpenDialog(this);//open dialog
            if (action != JFileChooser.APPROVE_OPTION ) {
                return;
            }
            File filename = new File(SaveAs.getSelectedFile()+ ".txt");//taking file 
            //to save or write we use buffered writter
            BufferedWriter outFile = null;
            try {
                outFile = new BufferedWriter(new FileWriter(filename));
                area.write(outFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (ae.getActionCommand().equals("Print")) {
            try {
                area.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (ae.getActionCommand().equals("Exit")) {
            System.exit(0);//common program to exit a program
        }
        else if (ae.getActionCommand().equals("Copy")) {
           text = area.getSelectedText(); //it used to copy or select the text

        }
        else if (ae.getActionCommand().equals("Paste")) {
            area.insert(text , area.getCaretPosition()); //get caretPsition is used to get the location of cursor
        }
        else if (ae.getActionCommand().equals("Cut")) {
            text = area.getSelectedText();
            area.replaceRange("" , area.getSelectionStart() , area.getSelectionEnd());//used to replace the selected posrtion with an empty string
        }
        else if (ae.getActionCommand().equals("SelectAll")) {
            area.selectAll();
        }
        else if (ae.getActionCommand().equals("About")) {
            new About().setVisible(true);
        }
    }
    public static void main(String[] args) {
        new Notepad1();
    }
    
   

}
