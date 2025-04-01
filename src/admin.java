
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;


class admin extends JFrame implements ActionListener{
    Container c;
    JLabel label;
    Font font= new Font("Garamond", Font.BOLD, 58);
    JButton Add,Delete,Modify,Check,log,Back ,sc;
		admin(){
			this.font = new Font("Garamond", Font.BOLD, 48);
				c= getContentPane();
                   
					c.setLayout(null);
					c.setBackground(new Color(36, 51, 70, 255));

				this.setTitle(" Admin Page");
                this.setDefaultCloseOperation(EXIT_ON_CLOSE);
                this.setBounds(300,0,900,700);
                this.setResizable(false);
                
                setVisible(true);
                 ImageIcon logo=new ImageIcon("logo.PNG");
                    this.setIconImage(logo.getImage());
       
                 
                label= new JLabel ("WELCOME TO ADMIN PAGE");
                label.setBounds(100,80,700, 70);
                label.setForeground(new Color(255, 255, 255));
                label.setFont(font);
                c.add(label);

                Add= new JButton ("Add New Movie");
				Add.setFont(new Font("Garamond", Font.BOLD, 20));
                Add.setBounds(200, 240, 200, 40);
                
		Add.setBackground(new Color(222, 219, 211));
                Add.setForeground(new Color(36, 51, 70));
                Add.setFocusable(false);
                c.add(Add);
                
                Delete= new JButton ("Edit Movie");
				Delete.setFont(new Font("Garamond", Font.BOLD, 20));
                Delete.setBounds(450, 240, 200, 40);
                Delete.setBackground(new Color(225, 225, 223));
				Delete.setForeground(new Color(36, 51, 70));
                Delete.setFocusable(false);
                c.add(Delete);

                
                Check= new JButton ("Details");
				Check.setFont(new Font("Garamond", Font.BOLD, 20));
                Check.setBounds(200, 320, 200, 40);
                Check.setBackground(new Color(246, 243, 233));
				Check.setForeground(new Color(36, 51, 70));
                Check.setFocusable(false);
                c.add(Check);
                  
                   
                Modify=new JButton("Refreshment items");
				Modify.setFont(new Font("Garamond", Font.BOLD, 20));
                Modify.setBounds(450, 320, 200, 40);
                Modify.setBackground(new Color(227, 224, 216));
				Modify.setForeground(new Color(36, 51, 70));
                Modify.setFocusable(false);
                c.add(Modify);
                
                log=new JButton("Log Out");
				log.setFont(new Font("Garamond", Font.BOLD, 20));
                log.setBounds(350, 550, 150, 35);
                log.setBackground(new Color(192, 190, 184));
				log.setForeground(new Color(36, 51, 70));
                log.setFocusable(false);
                c.add(log);


            sc=new JButton("Add Screen ");
            sc.setFont(new Font("Garamond", Font.BOLD, 20));
            sc.setBounds(350, 450, 150, 35);
            sc.setBackground(new Color(231, 229, 223));
            sc.setForeground(new Color(36, 51, 70));
            sc.setFocusable(false);
            c.add(sc);
        
				Add.addActionListener(this);
				Modify.addActionListener(this);
				Check.addActionListener(this);    
				Delete.addActionListener(this);
				log.addActionListener(this);
                sc.addActionListener(this);



        }

      public void actionPerformed(ActionEvent e){
        if(e.getSource()== Add){
           setVisible(false);
           Movie v = new Movie(); }       
         if(e.getSource()==Delete){
            setVisible(false);
             new EditMovie();}
       if(e.getSource()==Check){
        setVisible(false);
         new det();}
       if(e.getSource()==log){
        setVisible(false);     
      new Mypage();
       }
		 if(e.getSource()==Modify){
        setVisible(false);
        Refreshment m= new Refreshment();
         }
             if(e.getSource()==Back){
                setVisible(false);
            System.exit(ABORT);
             }
             if(e.getSource()==sc){
                 setVisible(false);
                new Screening();

             }
    }


    public static void main(String argus[]){
        new admin().setVisible(true);
    }

}