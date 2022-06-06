package chitchat;

import javax.swing.*;
import java.awt.*;
import java.awt.Event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.*;
import java.io.*;

public class Server extends JFrame implements ActionListener{
    
    JPanel p1;
    JTextField t1;
    JButton b1;
    static JTextArea a1;
    static ServerSocket skt;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;
    
   Server(){
       p1=new JPanel();
       p1.setLayout(null);
       p1.setBackground(new Color(103,57,183));
       p1.setBounds(0,0,450,80);
       add(p1);
       
       ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("CHitCHat/icons/back.png"));
       Image i2=i1.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
       ImageIcon i3= new ImageIcon(i2);
       JLabel l1=new JLabel(i3);
       l1.setBounds(5,25,30,30);
       p1.add(l1);
       
       l1.addMouseListener(new MouseAdapter(){
       public void mouseClicked(MouseEvent ae){
           System.exit(0);
       }
   });
       
       ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("CHitCHat/icons/dp1.png"));
       Image i5=i4.getImage().getScaledInstance(60,60,Image.SCALE_DEFAULT);
       ImageIcon i6= new ImageIcon(i5);
       JLabel l2=new JLabel(i6);
       l2.setBounds(40,12,60,60);
       p1.add(l2);
       
       JLabel l3=new JLabel("Chandler");
       l3.setBounds(110,20,100,20);
       l3.setFont(new Font("SAN_SERIF",Font.BOLD,20));
       l3.setForeground(Color.white);
       p1.add(l3);
              
       JLabel l4=new JLabel("Active Now");
       l4.setBounds(110,42,100,20);
       l4.setFont(new Font("SAN_SERIF",Font.PLAIN,14));
       l4.setForeground(Color.white);
       p1.add(l4);
       
       ImageIcon i7=new ImageIcon(ClassLoader.getSystemResource("CHitCHat/icons/3icon.png"));
       Image i8=i7.getImage().getScaledInstance(12,25,Image.SCALE_DEFAULT);
       ImageIcon i9= new ImageIcon(i8);
       JLabel l1_1=new JLabel(i9);
       l1_1.setBounds(360,30,12,25);
       p1.add(l1_1);
       
       a1=new JTextArea();
       a1.setBounds(0,80,400,475);
       a1.setBackground(new Color(145,143,144));
       a1.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
       a1.setEditable(false);
       //a1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
       a1.setLineWrap(true);
       a1.setWrapStyleWord(true);
       add(a1);
       
       t1=new JTextField();
       t1.setBounds(5,560,295,40);
       t1.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
       add(t1);
       
       b1=new JButton("Send");
       b1.setBounds(300,560,100,39);
       b1.setBackground(new Color(103,57,183));
       b1.setForeground(Color.white);
       b1.setFont(new Font("SAN_SERIF",Font.BOLD,15));
       b1.addActionListener(this);
       add(b1);
       
       setLayout(null);
       setSize(400,600);
       setLocation(50,50);
       setUndecorated(true);
       setVisible(true);
   } 
   
       @Override
    public void actionPerformed(ActionEvent ae) {
        String out;
        try{
            out = t1.getText();
            if(!out.equals("")){
            a1.setText(a1.getText()+"\n\t\t\t"+out+""); 
            dout.writeUTF(out);
            }
            t1.setText("");
        }catch(Exception e){
            
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        
    }
   
    public static void main(String args[]){
       new Server().setVisible(true);
       String msgInput="";
       try{
           skt=new ServerSocket(6001);
           s=skt.accept();
           din=new DataInputStream(s.getInputStream());
           dout=new DataOutputStream(s.getOutputStream());
           
           msgInput=din.readUTF();
           a1.setText(a1.getText()+"\n  "+msgInput+"");
           skt.close();
           s.close();
           
       }catch(Exception e){
           
       }
   }
}
