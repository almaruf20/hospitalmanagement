package file;
import java.io.*;
import java.util.Scanner;
import entity.*;
public class FileIO{
	public static void loadFromFile(Hospital hospitals[])
	{		
		try
		{
			File hospitalFile = new File("./file/Hospitals.txt");
			Scanner hospitalSc = new Scanner(hospitalFile);
			
			while(hospitalSc.hasNextLine())
			{
				String line = hospitalSc.nextLine();
				String data[] = line.split(";");
				int hospitalNo = Integer.parseInt(data[0]);
				String hospitalName = data[1];
				
				hospitals[hospitalNo] = new Hospital(hospitalName,14);
			}
			hospitalSc.close();
			
			File doctorFile = new File("./file/Doctors.txt");
			Scanner doctorSc = new Scanner(doctorFile);
			while(doctorSc.hasNextLine())
			{
				String line = doctorSc.nextLine();
				String data[] = line.split(";");
				int hospitalNo = Integer.parseInt(data[0]);
				int posNo = Integer.parseInt(data[1]);
				String doctorName = data[2];
				String doctorId = data[3];
				double salary = Double.parseDouble(data[4]);
				
			
				if(hospitals[hospitalNo] == null)
				{
					System.out.println("Skipping doctor – hospital "+hospitalNo+" not found.");
					continue;
				}
				
				Doctor d = new Doctor(doctorName,doctorId,salary);
				hospitals[hospitalNo].insertDoctor(posNo,d);
			}
			doctorSc.close();
		}
		catch(FileNotFoundException ex)
		{
			System.out.println("Cannot Read The File.");
		}
	}
	
	public static void writeInFile(int hospitalNo, int posNo, Doctor d)
	{
		try
		{
			File doctorFile = new File("./file/Doctors.txt");
			FileWriter writer = new FileWriter(doctorFile,true);
			
			String line = hospitalNo+";"+posNo+";"+d.getDoctorName()+";"+d.getDoctorId()+";"+d.getSalary()+"\n";
			writer.write(line);
			writer.flush();
			writer.close();
		}
		catch(IOException ex)
		{
			System.out.println("Cannot Write in File.");
		}
	}
}
