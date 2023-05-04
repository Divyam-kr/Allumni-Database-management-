package alumni;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class ViewAllAlumni implements ActionListener{

	JFrame frame=null;
	JLabel label=null;
	JTable table=null;
	JButton back=null;
	int f=0;

	public ViewAllAlumni(int l) {
		// TODO Auto-generated constructor stub
		frame=new JFrame("LIST OF ALL ALUMNI");
		frame.setLayout(new BorderLayout());
		frame.setSize(1360,725);
		f = l;
		int count=0;
		try{
			Connection con=DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/java_project","root","Divyam@2021");
			Statement stmt=con.createStatement();

			ResultSet rs=stmt.executeQuery("select * from base;");
			while(rs.next()) {
				count++;
			}
			if(count!=0){

				table=new JTable();
				DefaultTableModel model=new DefaultTableModel(){
					private static final long serialVersionUID = 1L;

					public boolean isCellEditable(int row, int column){
						return false;
					}
				};
				model.setColumnIdentifiers(new String[] {"Year of Passing","Name","Contact","Works In","Work Location","Designation","Email id","Prn"});
				model.setRowCount(count);
				int row=0;
				Connection con1=DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/java_project","root","Divyam@2021");
				Statement stmt1=con1.createStatement();

				ResultSet rs1=stmt1.executeQuery("select * from base;");
				while(rs1.next()) {
					model.setValueAt(rs1.getString(1), row, 0);
					model.setValueAt(rs1.getString(2), row, 1);
					model.setValueAt(rs1.getString(3), row, 2);
					model.setValueAt(rs1.getString(4), row, 3);
					model.setValueAt(rs1.getString(5), row, 4);
					model.setValueAt(rs1.getString(6), row, 5);
					model.setValueAt(rs1.getString(7), row, 6);
					model.setValueAt(rs1.getString(8), row, 7);

					row++;
				}
				table.setModel(model);
			} else{
				label=new JLabel("Nothing to Display!!!");
				Font font=new Font("Serif", Font.ITALIC, 34);
				label.setFont(font);
				label.setBounds(550,200,320,70);
				frame.add(label);
			}

			back=new JButton("BACK");
			frame.add(new JScrollPane(table),BorderLayout.CENTER);
			frame.add(back,BorderLayout.SOUTH);
			back.addActionListener(this);


			frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		} catch(Exception e){
			JOptionPane.showMessageDialog(frame, "Some Problem Ocurred!");
			System.out.println(e);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == back) {

			if (f == 0) {

				frame.dispose();
				new AdminAlumni();

			}
			if (f == 2) {
				System.out.println(1);
				new OperatorAlumni();
				frame.dispose();
			}
		}
	}
}

