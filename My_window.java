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
    
   
	//runs the window
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					My_window frame = new My_window();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	//here components for frame is created
	public My_window(Connection connection2)
	{
                connection1=connection2;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Database");
		menuBar.add(mnNewMenu);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(30, 11, 824, 156);
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "SQL Statement", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(panel);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 21, 789, 124);
		panel.add(textArea);
		textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		
		scroll = new JScrollPane();
		//panel_1.setLayout(null);
		//panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Result", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		scroll.setBounds(30, 215, 824, 314);
		contentPane.add(scroll);
		
		//JScrollPane scrollPane = new JScrollPane();
		//scrollPane.setBounds(30, 30, 784, 260);
		//panel_1.add(scrollPane);
		
		//JTextArea textArea_1 = new JTextArea();
		//textArea_1.setEditable(false);
		//scrollPane.setViewportView(textArea_1);
		
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
                                if(Input.charAt(0)=='U'||Input.charAt(0)=='u'||Input.charAt(0)=='D'||Input.charAt(0)=='d'||Input.charAt(0)=='I'||Input.charAt(0)=='i'){
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
                                      //not sure if this is how to add to bottom of screen im pretty
                                        //sure the data is in the string arrays though
                                        table = new JTable(data,column);
                                        //table.setModel(new DefaultTableModel(data,column));
                                        //table.setPreferredScrollableViewportSize(new Dimension(1000,1000));
                                        //table.setFillsViewportHeight(true);
                                        //contentPane.add(table);
                                        scroll = new JScrollPane(table);
                                		table.setFillsViewportHeight(true);
                                		scroll.setBounds(30, 215, 824, 314);
                                		contentPane.add(scroll);
                                		table.setEnabled(false);
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(My_window.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                
			}
		});

		btnNewButton.setBounds(326, 173, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Reset");
		// resets both fields
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
			}
		});

		btnNewButton_1.setBounds(425, 173, 89, 23);
		contentPane.add(btnNewButton_1);
		contentPane.setVisible(true);

	}
	
}
