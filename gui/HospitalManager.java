package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import entity.*;
import file.*;

public class HospitalManager extends JFrame implements ActionListener
{
	Font font15 = new Font("Cambria",Font.BOLD,15);
	Font font20 = new Font("Cambria",Font.BOLD,20);
	Font font25 = new Font("Cambria",Font.BOLD,25);
	
	JLabel title, hospitalSubTitle, doctorSubTitle;
	JLabel hospitalNoLabel, hospitalNameLabel;
	JTextField hospitalNoField, hospitalNameField;
	JButton hospitalAddBtn, hospitalUpdateBtn, hospitalRemoveBtn;
	
	JLabel doctorHospitalNoLabel, doctorPositionNoLabel;
	JLabel doctorNameLabel, doctorIdLabel, doctorSalaryLabel;
	JTextField doctorHospitalNoField, doctorPositionNoField;
	JTextField doctorNameField, doctorIdField, doctorSalaryField;
	JButton doctorAddBtn, doctorUpdateBtn, doctorRemoveBtn;
	
	JTextArea screen;
	
	Hospital hospitals[] = new Hospital[100];
	
	public HospitalManager()
	{
		super("Hospital Manager Page");
		this.setSize(800,700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setIconImage(new ImageIcon("./images/icn.png").getImage());

		FileIO.loadFromFile(hospitals);  
		
		int x=10,y=10,w=150,h=30,vGap=h+10;
		
		title = createLabel("Hospital Manager",x,y,2*w,h);
		hospitalSubTitle = createLabel("Create New Hospital",x,y+=vGap,2*w,h);
		hospitalNoLabel = createLabel("Hospital No",x,y+=vGap,w,h);
		hospitalNoField = createTextField("",x+w,y,w,h);
		hospitalNameLabel = createLabel("Hospital Name",x,y+=vGap,w,h);
		hospitalNameField = createTextField("",x+w,y,w,h);
		hospitalAddBtn = createButton("Create Hospital",x,y+=vGap,2*w,h);
		hospitalAddBtn.setBackground(Color.GREEN);
		hospitalUpdateBtn = createButton("Update",x,y+=vGap,w-5,h);
		hospitalRemoveBtn = createButton("Remove",x+w+5,y,w-5,h);
		hospitalRemoveBtn.setBackground(Color.RED);
		
		doctorSubTitle = createLabel("Create Doctor",x,y+=vGap,w,h);
		doctorHospitalNoLabel = createLabel("Hospital No",x,y+=vGap,w,h);
		doctorHospitalNoField = createTextField("",x+w,y,w,h);
		
		doctorPositionNoLabel = createLabel("Position No",x,y+=vGap,w,h);
		doctorPositionNoField = createTextField("",x+w,y,w,h);
		doctorNameLabel = createLabel("Doctor Name",x,y+=vGap,w,h);
		doctorNameField = createTextField("",x+w,y,w,h);
		doctorIdLabel = createLabel("Doctor ID",x,y+=vGap,w,h);
		doctorIdField = createTextField("",x+w,y,w,h);
		doctorSalaryLabel = createLabel("Salary",x,y+=vGap,w,h);
		doctorSalaryField = createTextField("",x+w,y,w,h);
		
		doctorAddBtn = createButton("Create And Assign Doctor",x,y+=vGap,w*2,h);
		doctorAddBtn.setBackground(Color.GREEN);
		doctorUpdateBtn = createButton("Update",x,y+=vGap,w-5,h);
		doctorRemoveBtn = createButton("Remove",x+w+5,y,w-5,h);
		doctorRemoveBtn.setBackground(Color.RED);
		
		screen = new JTextArea();
		screen.setFont(font20);
		screen.setEditable(false);
		updateScreen();
		JScrollPane jsp = new JScrollPane(screen);
		jsp.setBounds(350,50,400,500);
		this.add(jsp);
		
		JLabel background = new JLabel(new ImageIcon("./images/bg.jpg"));
		background.setBounds(0,0,800,700);
		this.add(background);
		this.setVisible(true);
	}
	
	public JLabel createLabel(String text,int x,int y,int w,int h)
	{
		JLabel component = new JLabel(text);
		component.setBounds(x,y,w,h);
		component.setFont(font20);
		component.setOpaque(true);
		component.setBackground(Color.BLACK);
		component.setForeground(Color.WHITE);
		this.add(component);
		return component;
	}
	
	public JTextField createTextField(String text,int x,int y,int w,int h){
		JTextField component = new JTextField(text);
		component.setBounds(x,y,w,h);
		component.setFont(font20);
		component.setBackground(Color.WHITE);
		component.setForeground(Color.BLACK);
		this.add(component);
		return component;
	}
	
	public JButton createButton(String text,int x,int y,int w,int h)
	{
		JButton component = new JButton(text);
		component.setBounds(x,y,w,h);
		component.setFont(font20);
		component.setBackground(Color.BLUE);
		component.setForeground(Color.WHITE);
		component.addActionListener(this);
		this.add(component);
		return component;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == hospitalAddBtn)
		{
			int hospitalNo = Integer.parseInt(hospitalNoField.getText());
			String hospitalName = hospitalNameField.getText();
			
			if(hospitals[hospitalNo] == null)
			{
				hospitals[hospitalNo] = new Hospital(hospitalName,14);
				updateScreen();
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Hospital No Already Occupied");
			}
		}

		else if(e.getSource() == hospitalUpdateBtn)
		{
			int hospitalNo = Integer.parseInt(hospitalNoField.getText());
			String hospitalName = hospitalNameField.getText();

			if(hospitals[hospitalNo] != null)
			{
				hospitals[hospitalNo].setHospitalName(hospitalName);
				updateScreen();
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Hospital Not Found");
			}
		}

		
		else if(e.getSource() == hospitalRemoveBtn)
		{
			int hospitalNo = Integer.parseInt(hospitalNoField.getText());

			if(hospitals[hospitalNo] != null)
			{
				hospitals[hospitalNo] = null;
				updateScreen();
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Hospital Not Found");
			}
		}
		
		else if(e.getSource() == doctorAddBtn)
		{
			int hospitalNo = Integer.parseInt(doctorHospitalNoField.getText());
			int positionNo = Integer.parseInt(doctorPositionNoField.getText());
			String name = doctorNameField.getText();
			String id = doctorIdField.getText();
			double salary = Double.parseDouble(doctorSalaryField.getText());

			if(hospitals[hospitalNo] != null)
			{
				Doctor d = new Doctor(name,id,salary);
				hospitals[hospitalNo].insertDoctor(positionNo,d);
				FileIO.writeInFile(hospitalNo,positionNo,d);
				updateScreen();
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Hospital Not Found");
			}
		}

		else if(e.getSource() == doctorUpdateBtn)
		{
			int hospitalNo = Integer.parseInt(doctorHospitalNoField.getText());
			int positionNo = Integer.parseInt(doctorPositionNoField.getText());
			String name = doctorNameField.getText();
			String id = doctorIdField.getText();
			double salary = Double.parseDouble(doctorSalaryField.getText());

			if(hospitals[hospitalNo] != null)
			{
				if(hospitals[hospitalNo].getDoctor(positionNo) != null)
				{
					Doctor d = new Doctor(name,id,salary);
					hospitals[hospitalNo].insertDoctor(positionNo,d);
					updateScreen();
				}
				else
				{
					JOptionPane.showMessageDialog(this,"Doctor Position Not Found");
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Hospital Not Found");
			}
		}

		else if(e.getSource() == doctorRemoveBtn)
		{
			int hospitalNo = Integer.parseInt(doctorHospitalNoField.getText());
			int positionNo = Integer.parseInt(doctorPositionNoField.getText());

			if(hospitals[hospitalNo] != null)
			{
				if(hospitals[hospitalNo].getDoctor(positionNo) != null)
				{
					hospitals[hospitalNo].removeDoctor(positionNo);
					updateScreen();
				}
				else
				{
					JOptionPane.showMessageDialog(this,"Doctor Position Not Found");
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Hospital Not Found");
			}
		}
	}
		
	public void updateScreen()
	{
		String allHospitalData = "";
		for(int i=0;i<hospitals.length;i++)
		{
			if(hospitals[i]!=null)
			{
				allHospitalData+= i+"."+hospitals[i].getHospitalAsString();
			}
		}
		screen.setText(allHospitalData);
	}
}
