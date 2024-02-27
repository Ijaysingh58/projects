package chatting;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.*;
import java.net.*;
//server socket for making server it stores int net 





public class Server implements ActionListener {
    //ACTION LISTENER IS USED TO MAKE ACTION 
     
    JTextField text;//we have to declare it globle to access in action performed class or other class
    JPanel a1;//for globle access
    static Box vertical = Box.createVerticalBox(); //to set the messege or text vertically and align one after another
    static JFrame  f = new JFrame(); 
    static DataOutputStream dout;

    Server(){
        // SET THE LAYOUT OF FRAME 
        f.setLayout(null);
        //here we nullify the set layout so you have to tell were to set the panel


                //MAKING A PANEL-> 1ST

        //to do on frame we use jframe to divide to do the thing
        JPanel p1 = new JPanel();
        //to set the background of pannel we use setBackground
        p1.setBackground(new Color(54, 69, 79));
        //when set layout is null then you have to tell where to set the panel in frame
        //so we use the setBounds with cordinates to set panel

        //we have to pass the cordinates to make th panel visible ;
        //setBounds are help to use to make the panel visible at the coordinateds
        p1.setBounds(0 , 0 , 450  , 70);
        //to make the image set on the panel not on the frame we have to make layout of panel null
        p1.setLayout(null);
        //to set panel on frame but not visible 
        f.add(p1);


                //ADDING IMAGE ON FRAME OR PANEL  ->

        //to set the image from on panel or frame we use imageicon calls which takes the image from the 
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));//class loader is used to take image from a location (url)
        //we have to scale the image for perfectly pixed and scale
        Image i2 = i1.getImage().getScaledInstance(25 ,25,Image.SCALE_DEFAULT);
        // we cannot directly pass this image in jlabel we have to make it imageicon to load in jlabel
        ImageIcon i3 = new ImageIcon(i2);
        //we cannot set the image icon directly on the panel so we have to use jlabel
        JLabel back = new JLabel(i3);
        //to set the dimensions of image on panel we setbounds the image
        back.setBounds(5,20,25,25);
        //to add the image on panel we have to add it on panel so specify to add on panel not on frame
        p1.add(back);


                    //TO ADD ACTION EVENT ->

        //need to have an action on click on mouse botton
        back.addMouseListener(new MouseAdapter() {
            //mouse Adapter has many function to access on mouse 
            public void mouseClicked(MouseEvent ae){
                System.exit(0);
            }
        });

                //to set the image from on panel or frame we use imageicon calls which takes the image from the 
                ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/1.png"));//class loader is used to take image from a location (url)
                //we have to scale the image for perfectly pixed and scale
                Image i5 = i4.getImage().getScaledInstance(50 ,50,Image.SCALE_DEFAULT);
                // we cannot directly pass this image in jlabel we have to make it imageicon to load in jlabel
                ImageIcon i6 = new ImageIcon(i5);
                //we cannot set the image icon directly on the panel so we have to use jlabel
                JLabel profile = new JLabel(i6);
                //to set the dimensions of image on panel we setbounds the image
                profile.setBounds(40,10,50,50);
                //to add the image on panel we have to add it on panel so specify to add on panel not on frame
                p1.add(profile);

                
                ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
                Image i8 = i7.getImage().getScaledInstance(30 ,30,Image.SCALE_DEFAULT);
                ImageIcon i9 = new ImageIcon(i8);
                JLabel video = new JLabel(i9);
                video.setBounds(300,20,30,30);
                p1.add(video);

                ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
                Image i11 = i10.getImage().getScaledInstance(25 ,40,Image.SCALE_DEFAULT);
                ImageIcon i12 = new ImageIcon(i11);
                JLabel phone = new JLabel(i12);
                phone.setBounds(362,15,25,40);
                p1.add(phone);

                ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
                Image i14 = i13.getImage().getScaledInstance(10 ,25,Image.SCALE_DEFAULT);
                ImageIcon i15 = new ImageIcon(i14);
                JLabel morevert = new JLabel(i15);
                morevert.setBounds(420,20,10,25);
                p1.add(morevert);


                            //TO ADD TEXT ON THE PANEL ->

                //to write any text on the frame or panel we use Jlabel 
                JLabel name = new JLabel("Gaitonde");
                name.setBounds(110 , 15 , 100 , 18);
                name.setForeground(Color.WHITE);//for color of text
                name.setFont(new Font("SAN_SERIF" , Font.BOLD , 18));//to set the font type size 
                p1.add(name);

                JLabel status = new JLabel("Active Now");
                status.setBounds(110 , 35 , 100 , 14);
                status.setForeground(Color.WHITE);//for color of text
                status.setFont(new Font("SAN_SERIF" , Font.BOLD , 14));//to set the font type size 
                p1.add(status);


                //TO ADD NEW PANEL FOR WORK -> 2ND

        //new panel is make for the space where we sent the messege
         a1 = new JPanel();
         a1.setBounds(5, 75 , 440 ,570 );
         a1.setBackground(new Color(211, 211, 211));
         f.add(a1);

                    //ADDING WRITEING AREA OR BLOCK->

         //to add text writting area we uses jTectField 
        text = new JTextField();
         text.setBounds(5 , 655 , 310 , 40);
         text.setFont(new Font("SAN_SERIF" , Font.PLAIN , 16));
         text.setBackground(new Color(229, 228, 226));
         text.addActionListener(this);
         f.add(text);


                //FOR BUTTON ->

         //to add a button we use jbutton
         JButton send = new JButton("send");
         send.setBounds(320 , 655 , 123 , 40);
         send.setBackground(new Color(192, 192, 192));
         send.setForeground(Color.BLACK);
         send.addActionListener(this);//to make an action event for button and the details are written on the action performed function
         send.addMouseListener(new MouseAdapter(){
            public void mouseEntered(MouseEvent e) {
                send.setBackground(new Color(54, 69, 79));
            }
            public void mouseExited(MouseEvent e) {
                send.setBackground(new Color(192, 192, 192));
            }
            
         });
         send.setFont(new Font("SAN_SERIF" ,Font.PLAIN , 16));
         f.add(send);

                    //FOR FRAME ->
        //set size or the dimensions of the frame or window
        f.setSize(450 , 700);
        //set the location of the window from origin
        f.setLocation(200 , 50);
        //to remove the undecoreted part or the hader 
        f.setUndecorated(true);
        f.setShape(new RoundRectangle2D.Double(0, 0, 450, 700, 20, 20)); //making corner round
        //set the background of frame 
        //getContentpane() -> is used to access the whole frame
        //setBackground is used to set the backround color of the frame 
        f.getContentPane().setBackground(Color.WHITE);
        //color is an additional class which is imported from awt 
        //)

        //to make frame visible we use setVisible(true);
        f.setVisible(true);
    }

public void actionPerformed(ActionEvent ae){
    try {
        String out = text.getText();//we can retrive that is written in this function
    //we have to append this on 2nd panel

    JPanel p2 = formatLabel(out);
      

    a1.setLayout(new BorderLayout());//we have to set layout for this we also can pass null in this 
    //border layout can set left right bottom and or on border

    JPanel right = new JPanel(new BorderLayout());//set it on right 
    right.add(p2 , BorderLayout.LINE_END);//it doen't take string and it set the text to the left side 
    //from above to line the message is align on right side and end of line

    vertical.add(right);// from this message is set on the vertical 
    vertical.add(Box.createVerticalStrut(15));//to make the distance between the messeges vertically

    a1.add(vertical ,BorderLayout.PAGE_START); //set on the a1 panel and start from starting

    
        dout.writeUTF(out);
   //to send message

    text.setText(""); //to empty the writing area


    f.repaint();
    f.invalidate();// these three function are use to repaint or refresh the plane or panel so the new things or changes can be seen
    f.validate();
 } catch (Exception e) {
        
    e.printStackTrace();
}
}

public static JPanel formatLabel(String out){
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel , BoxLayout.Y_AXIS));

    JLabel output = new JLabel("<html><p style =\"width : 150px\">" + out + "</p></html>");
    output.setFont(new Font("Tahoma" , Font.PLAIN ,16));
    output.setBackground(new Color(37 , 211 ,102));
    output.setOpaque(true );//to make background visible 
    output.setBorder(new EmptyBorder(15 , 15 , 15 ,50));
    
    Calendar cal = Calendar.getInstance();//to add date and time 
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

    JLabel time = new JLabel();
    time.setText(sdf.format(cal.getTime()));//to set the time or date dynamically
    panel.add(time);

    panel.add(output);
    return panel;
}



    public static void main(String[] args) {
       new Server();

       try {
        try (ServerSocket skt = new ServerSocket(6001)) {
            while (true) {
               Socket s = skt.accept();
               DataInputStream din = new DataInputStream(s.getInputStream()); //to take input 
               dout = new DataOutputStream(s.getOutputStream()); //for  output
            
               while (true) {
                String msg = din.readUTF();
                //to read the recived message 

                JPanel panel = formatLabel(msg);

                JPanel left = new JPanel(new BorderLayout());// recived messege so display on left
                left.add(panel , BorderLayout.LINE_START);
                vertical.add(left);
                f.validate();
            }
            }
        } 
       } catch (Exception e) {
        e.printStackTrace();
       }
    }
}
