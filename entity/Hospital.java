package entity;
public class Hospital
{
	private String hospitalName;
	private Doctor[] doctors;
	
	public Hospital()
	{
		hospitalName = "No Name";
		doctors = new Doctor[15];
	}
	public Hospital(String hospitalName, int size)
	{
		this.hospitalName = hospitalName;
		doctors = new Doctor[size];
	}
	
	public void setHospitalName(String hospitalName)
	{
		this.hospitalName = hospitalName;
	}
	public String getHospitalName()
	{
		return hospitalName;
	}
	
	public void insertDoctor(int dNo, Doctor d)
	{
		if(dNo>=0 && dNo<doctors.length)
		{
			doctors[dNo] = d;
		}
	}
	public void removeDoctor(int dNo)
	{
		if(dNo>=0 && dNo<doctors.length)
		{
			doctors[dNo] = null;
		}
	}
	public Doctor getDoctor(int dNo)
	{
		if(dNo>=0 && dNo<doctors.length)
		{
			return doctors[dNo];
		}
		return null;
	}
	
	public void showHospital()
	{
		System.out.println("=========== "+hospitalName+" ==============");
		double totalSalary = 0;
		for(int i=0;i<doctors.length;i++)
		{
			if(doctors[i]!=null){
				totalSalary += doctors[i].getSalary();
				doctors[i].showDoctor();
				System.out.println("---------------------");
			}
		}
		System.out.println("---------------------");
		System.out.println("Total Salary Cost: "+totalSalary+" BDT");
		System.out.println("---------------------\n\n");
	}
	
	public String getHospitalAsString()
	{
		String data = "";
		data+="=========== "+hospitalName+" ==============\n";
		double totalSalary = 0;
		int doctorCount = 0;
		for(int i=0;i<doctors.length;i++)
		{
			if(doctors[i]!=null){
				totalSalary += doctors[i].getSalary();
				doctorCount++;
				data += "~~~~~~~"+i+"~~~~~~~~\n";
				data += doctors[i].toString();
				data += "---------------------\n";
			}
		}
		data+= "---------------------\n";
		data+= "Total Salary Cost: "+totalSalary+" BDT\n";
		data+= "Total Doctor Count: "+doctorCount+"\n";
		data+= "---------------------\n\n";
		return data;
	}
	
}
