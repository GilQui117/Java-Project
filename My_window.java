import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;



public class My_window extends JFrame 
{
	private String Input; 
        // string of whats inputted in second window
	private JPanel contentPane;
    private Connection connection1;
    private JTable table;
    private JScrollPane scroll;

	//here components for frame is created
   
	public My_window(Connection connection2)
	{
                connection1=connection2;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Database");
		menuBar.add(mnNewMenu);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(30, 11, 874, 121);
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "SQL Statement", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(panel);
		
		JTextArea textArea = new JTextArea();
		scroll = new JScrollPane(textArea);
		textArea.setBounds(10, 21, 839, 89);
		panel.add(textArea);
		textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		
		scroll = new JScrollPane();
		
		
		scroll.setBounds(30, 215, 824, 314);
		contentPane.add(scroll);
		
	
		JButton btnNewButton = new JButton("Execute");
		//here text inputted is stored into string input
		btnNewButton.addActionListener(new ActionListener() 
		{
			@SuppressWarnings("null")
			public void actionPerformed(ActionEvent e) 
			{
                                ResultSet rs;
				Input = textArea.getText().toString();  
                            try {
                                Statement statement=connection1.createStatement();
                                if(Input.charAt(0)=='U'||Input.charAt(0)=='u'||Input.charAt(0)=='D'||Input.charAt(0)=='d'||Input.charAt(0)=='I'||Input.charAt(0)=='i'||Input.charAt(0)=='c'||Input.charAt(0)=='C'){
                                    int a=statement.executeUpdate(Input);
                                }
                                
                                else {
                                	String[] column = null;
                                	Object [][]data=null;
                                        rs=statement.executeQuery(Input);
                                         ResultSetMetaData rsmd = rs.getMetaData();
                                         int columnsNumber = rsmd.getColumnCount();
                                         column=new String[columnsNumber];
                                         for(int k=0;k <= columnsNumber-1;k++){

                                        	column[k]=rsmd.getColumnLabel(k+1);
                                         }

                                         int p=0;
                                         data=new Object[100][columnsNumber];
                                        while(rs.next())
                                        {
                                            for(int i=0;i <= columnsNumber - 1;i++){
                                                 //put the data in string 2d array
                                                     data[p][i] = rs.getObject(i+1);
                                            }
                                         
                                            p++;
                                        }
                                        contentPane.remove(scroll);
                                    
                                        table = new JTable(data,column);
                                     
                                        scroll = new JScrollPane(table);
                                		table.setFillsViewportHeight(true);
                                		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                                		scroll.setBounds(30, 215, 874, 314);
                                		contentPane.add(scroll);
                                		table.setEnabled(false);
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(My_window.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                
			}
		});

		btnNewButton.setBounds(375, 140, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Reset");
		btnNewButton_1.setMnemonic('R');
		// resets both fields
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
			}
		});

		btnNewButton_1.setBounds(425, 173, 89, 23);
		contentPane.add(btnNewButton_1);
		contentPane.setVisible(true);
		
		JButton btnNewButton_2 = new JButton("Union");
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("select * from Items where category = 'Books' And Books_Genre = 'Action'"
						+ " Union "
						+ "select * from Items where category = 'Books' and books_Genre = 'Adventure'");

				 btnNewButton.doClick();
			}
		});

		btnNewButton_2.setBounds(525, 173, 89, 23);
		contentPane.add(btnNewButton_2);
		contentPane.setVisible(true);
		
		JButton btnNewButton_3 = new JButton("Intersection");
		
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("select * from account a where length(password) < 14 AND EXISTS "
						+ "(select * from account b where NOT(regexp_like(b.password, '[0-9]') AND (regexp_like(b.password, '[a-z]') OR regexp_like(b.password, '[A-Z]'))) AND a.email = b.email)");
				 btnNewButton.doClick();
			}
		});

		btnNewButton_3.setBounds(625, 173, 89, 23);
		contentPane.add(btnNewButton_3);
		contentPane.setVisible(true);
		
		JButton btnNewButton_4 = new JButton("Difference");
	
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("select * from account a1, (select * from account a2 where a2.email NOT like '%@gmail.com')  s1 where a1.email = s1.email");
				 btnNewButton.doClick();
			}
		});

		btnNewButton_4.setBounds(725, 173, 89, 23);
		contentPane.add(btnNewButton_4);
		contentPane.setVisible(true);
		
		JButton btnNewButton_5 = new JButton("Division");
	
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("SELECT  a1.email "
						+ " FROM account  a1 "
						+ " WHERE (select count(coun1.category) from (Select s1.category, s2.email from (SELECT Distinct i3.category FROM Items  i3) s1, "
						+ " (SELECT Distinct i2.category, b2.email "
						+ " FROM  belongsTo  b2, Items  i2 "
						+ " WHERE  b2.ID = i2.ID)  s2 where s1.category = s2.category)  coun1 where a1.email = coun1.email) = 8");
				 btnNewButton.doClick();
			}
		});

		btnNewButton_5.setBounds(825, 173, 89, 23);
		contentPane.add(btnNewButton_5);
		contentPane.setVisible(true);
		
		
		JButton btnNewButton_6 = new JButton("Aggregation");
	
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("select count(*) from account");
				 btnNewButton.doClick();
			}
		});

		btnNewButton_6.setBounds(25, 173, 89, 23);
		contentPane.add(btnNewButton_6);
		contentPane.setVisible(true);
		
		JButton btnNewButton_7 = new JButton("Join");
	
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("select * from (select * from belongsTo b inner join account a ON b.email = a.email) s inner join items i ON i.ID = s.ID");
				 btnNewButton.doClick();
			}
		});

		btnNewButton_7.setBounds(125, 173, 89, 23);
		contentPane.add(btnNewButton_7);
		contentPane.setVisible(true);
		
		
		JButton btnNewButton_8 = new JButton("Create");
	
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Statement statement = null;
				try {
					statement = connection1.createStatement();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				String input="CREATE TABLE account(First_Name VARCHAR(25) NOT NULL, Last_Name varCHAR(25) NOT NULL, EMAIL VARCHAR(40) PRIMARY KEY, Address VARCHAR(40), "
						+ " Credit_Card_Number VARCHAR(40), Password VARCHAR(30) NOT NULL )";
						try {
							int a=statement.executeUpdate(input);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						input ="CREATE TABLE belongsto(ID INT PRIMARY KEY, EMAIL VARCHAR(40),"
								+ "CONSTRAINT fk_email FOREIGN KEY (EMAIL)"
								+ " REFERENCES account(EMAIL))";
						try {
							int a=statement.executeUpdate(input);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						input="CREATE TABLE items("
								+ "ID INT PRIMARY KEY, name VARCHAR(30) NOT NULL, description VARCHAR(255), time_left TIMESTAMP, "
								+ " item_condition VARCHAR(30), previous_bid FLOAT, "
								+ " current_bid FLOAT, starting_bid FLOAT, "
								+ " selling_price FLOAT, "
								+ " category VARCHAR(20), "
								+ " Sporting_Goods_Brand VARCHAR(30), "
								+ " Sporting_Goods_Weight FLOAT, Sporting_Goods_Dimension_LxWxH FLOAT, NBA VARCHAR(1)  DEFAULT 'N', "
								+ " NHL VARCHAR(1) DEFAULT 'N', "
								+ " NFL VARCHAR(1) DEFAULT 'N', MLB VARCHAR(1) DEFAULT 'N', NCAA VARCHAR(1)  DEFAULT 'N', Exercise VARCHAR(1)  DEFAULT 'N', "
								+ " Studio VARCHAR(30) DEFAULT 'N/A', Movie_Length Float, Movie_Release_Date DATE, Director VARCHAR(30) DEFAULT 'N/A', Movie_Genre VARCHAR(20)  DEFAULT 'N/A', Movie_Rating VARCHAR(5), Games_Release_Date DATE, Games_publisher VARCHAR(50)  DEFAULT 'N/A', platform VARCHAR(20)  DEFAULT 'N/A', "
								+ " Games_Genre VARCHAR(20)  DEFAULT 'N/A', Games_Rating VARCHAR(8)  DEFAULT 'N/A', "
								+ " Clothing_Weight FLOAT, "
								+ " CSize VARCHAR(10), Women VARCHAR(1)  DEFAULT 'N', Men VARCHAR(1)  DEFAULT 'N', "
								+ " Children VARCHAR(1)  DEFAULT 'N', Occasion VARCHAR(20)  DEFAULT 'N/A', "
								+ " Unisex VARCHAR(1)  DEFAULT 'N', "
								+ " Clothing_Brand VARCHAR(30)  DEFAULT 'N/A', Material VARCHAR(30)  DEFAULT 'N/A', "
								+ " Electronics_Brand VARCHAR(50)  DEFAULT 'N/A', "
								+ " Electronics_Model VARCHAR(30)  DEFAULT 'N/A', "
								+ " Processor VARCHAR(30)  DEFAULT 'N/A', RAM INT, "
								+ " hard_drive VARCHAR(30)  DEFAULT 'N/A', GPU VARCHAR(30)  DEFAULT 'N/A', Model_Number VARCHAR(30), Operating_System VARCHAR(30)  DEFAULT 'N/A', Electronics_Weight FLOAT, Elect_item_dim_LxWxH FLOAT, USB_ports INT, Music_Genre VARCHAR(20)  DEFAULT 'N/A', Music_Release_Date DATE, Music_Length FLOAT, Label VARCHAR(30)  DEFAULT 'N/A', "
								+ " Vehicle_Model VARCHAR(30)  DEFAULT 'N/A', Make VARCHAR(30), "
								+ " mileage INT, Vehicle_Year VARCHAR(15), "
								+ " Exterior_Color VARCHAR(15)  DEFAULT 'N/A', "
								+ " VCondition VARCHAR(10)  DEFAULT 'N/A', "
								+ " Number_Of_Cylinders INT, VIN VARCHAR(30), "
								+ " Vehicle_title VARCHAR(1), Drive_Type VARCHAR(20)  DEFAULT 'N/A', "
								+ " VEngine VARCHAR(20)  DEFAULT 'N/A', "
								+ " Transmission VARCHAR(15)  DEFAULT 'N/A', Books_Genre VARCHAR(15), Books_Publisher VARCHAR(30)  DEFAULT 'N/A', hardcover VARCHAR(1), "
								+ " Page_Number INT, "
								+ " ISBN VARCHAR(17), "
								+ " author VARCHAR(50)  DEFAULT 'N/A', "
								+ " CONSTRAINT fk_id FOREIGN KEY (ID) REFERENCES belongsto(ID), "
								+ " CHECK(category IN ('Books', 'Sporting Goods', 'Movie', 'Games', 'Clothing', ' Electronics', ' Misc', ' Music',  'Vehicles')), "
								+ " CHECK(ID IS NOT NULL "
								+ " AND name IS NOT NULL AND description IS NOT NULL AND time_left IS NOT NULL AND item_condition IS NOT NULL AND previous_bid IS NOT NULL AND current_bid IS NOT NULL AND starting_bid >0 "
								+ " AND selling_price >0 "
								+ " AND category IS NOT NULL), "
								+ " CHECK( ( category = 'Sporting_Goods' AND Sporting_Goods_Brand IS NOT NULL AND Sporting_Goods_Weight > 0 "
								+ " AND Sporting_Goods_Dimension_LxWxH IS NOT NULL AND NBA IS NOT NULL "
								+ " AND NHL IS NOT NULL "
								+ " AND NFL IS NOT NULL "
								+ " AND MLB IS NOT NULL AND NCAA IS NOT NULL AND Exercise IS NOT NULL "
								+ " )OR "
								+ " ( category = 'Movie' AND Studio IS NOT NULL AND Movie_Length IS NOT NULL AND Movie_Release_Date IS NOT NULL AND Director IS NOT NULL AND Movie_Genre IS NOT NULL AND Movie_Rating IN ('G', 'PG', 'PG-13', 'R', 'NC-17')) OR "
								+ " ( category = 'Games' AND Games_Release_Date IS NOT NULL AND Games_Publisher IS NOT NULL AND Platform IS NOT NULL AND Games_Genre IS NOT NULL AND Games_Rating IN ('eC', 'E', 'E-10+', 'T', 'M', 'Ao', 'RP'))OR "
								+ " ( category = 'Clothing' AND Clothing_Weight IS NOT NULL AND CSize IN('Small', 'Medium', 'Large', 'X-Large', 'XX-Large') AND Women IS NOT NULL AND Men IS NOT NULL AND Children IS NOT NULL AND Occasion IS NOT NULL AND Unisex IS NOT NULL "
								+ " AND Clothing_Brand IS NOT NULL AND Material IS NOT NULL) "
								+ " OR "
								+ " ( category = 'Electronics' "
								+ " AND Electronics_Brand IS NOT NULL AND Processor IS NOT NULL AND Electronics_Model IS NOT NULL AND RAM IS NOT NULL "
								+ " AND hard_drive IS NOT NULL AND GPU IS NOT NULL AND Model_Number IS NOT NULL AND Operating_System IS NOT NULL AND Electronics_Weight > 0 AND Elect_item_dim_LxWxH IS NOT NULL "
								+ " AND USB_ports IS NOT NULL ) "
								+ " OR "
								+ " ( category = 'Music' "
								+ " AND Label IS NOT NULL "
								+ " AND Music_Genre IS NOT NULL "
								+ " AND Music_Release_Date IS NOT NULL AND Music_Length IS NOT NULL)OR "
								+ " ( category = 'Vehicles' AND VIN IS NOT NULL AND Vehicle_Model IS NOT NULL AND Make IS NOT NULL "
								+ " AND mileage IS NOT NULL AND Vehicle_Year IS NOT NULL AND Exterior_Color IS NOT NULL AND (Number_Of_Cylinders >-1 AND Number_Of_Cylinders < 13) AND VIN IS NOT NULL AND Vehicle_title IS NOT NULL AND Drive_Type IS NOT NULL AND VEngine IS NOT NULL AND Transmission IS NOT NULL "
								+ "  AND VCondition IN ('used', 'new'))OR "
								+ " ( category = 'Books' AND Books_Genre IS NOT NULL AND Books_Publisher IS NOT NULL AND hardcover IS NOT NULL AND Page_Number > 0 AND ISBN IS NOT NULL AND author IS NOT NULL) "
								+ " ))";
						try {
							int a=statement.executeUpdate(input);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
			}
		});

		btnNewButton_8.setBounds(225, 173, 89, 23);
		contentPane.add(btnNewButton_8);
		contentPane.setVisible(true);
		
		
		JButton btnNewButton_9 = new JButton("Populate");
	
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Statement statement = null;
					try {
						statement = connection1.createStatement();
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				String input="Insert into account(First_Name, Last_Name, EMAIL, Password) values ('Aaren', 'logie', 'alogie234@gmail.com', '71') ";
						
				try {
					int a=statement.executeUpdate(input);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				input="Insert into account(First_Name, Last_Name, EMAIL, Password) values ('Aaren', 'logie', 'bytheby@gmail.com', '712A') ";
				
				try {
					int a=statement.executeUpdate(input);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				input="Insert into account(First_Name, Last_Name, EMAIL, Password) values ('Aarika', 'cowin', 'acowin249@hotmail.com', '33') ";
				try {
					int a=statement.executeUpdate(input);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				input="insert into belongsto(ID,Email) values (16,'alogie234@gmail.com')";
				try {
					int a=statement.executeUpdate(input);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				input="insert into belongsto(ID,Email) values (17,'alogie234@gmail.com')";
				try {
					int a=statement.executeUpdate(input);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				input="insert into belongsto(ID,Email) values (18,'bytheby@gmail.com')";
				try {
					int a=statement.executeUpdate(input);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				input="Insert into items(ID,name,description,time_left,item_condition,previous_bid,current_bid,starting_bid,selling_price,category,Books_Genre,Books_Publisher,hardcover,Page_Number,ISBN,author) values (16,'test item','this is only a test description',CURRENT_TIMESTAMP,'the item is a fake',0,1,100,9999,'Books','Action','Pinguin','T',117,'0135-895SAsdf','Me')";
				try {
					int a=statement.executeUpdate(input);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				input="Insert into items(ID,name,description,time_left,item_condition,previous_bid,current_bid,starting_bid,selling_price,category,Books_Genre,Books_Publisher,hardcover,Page_Number,ISBN,author) values (17,'test item','this is only a test description',CURRENT_TIMESTAMP,'the item is a fake',0,1,100,9999,'Books','Adventure','Pinguin','F',117,'0135-895SAsdf','Me')";
				try {
					int a=statement.executeUpdate(input);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				input="Insert into items(ID,name,description,time_left,item_condition,previous_bid,current_bid,starting_bid,selling_price,category,Books_Genre,Books_Publisher,hardcover,Page_Number,ISBN,author) values (18,'test item','this is only a test description',CURRENT_TIMESTAMP,'the item is a fake',0,1,100,9999,'Books','Scary','Pinguin','F',117,'0135-895SAsdf','Me')";
				try {
					int a=statement.executeUpdate(input);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnNewButton_9.setBounds(325, 173, 89, 23);
		contentPane.add(btnNewButton_9);
		contentPane.setVisible(true);
		
	}
	
}
