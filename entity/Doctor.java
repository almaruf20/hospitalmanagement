package entity;
public class Doctor
{
	private String doctorName;
	private String doctorId;
	private double salary;
	
	public Doctor()
	{
		System.out.println("E-Doctor Created.");
	}
	public Doctor(String doctorName, String doctorId, double salary)
	{
		System.out.println("P-Doctor Created.");
		setDoctorName(doctorName);
		setDoctorId(doctorId);
		setSalary(salary);
	}
	
	public void setDoctorName(String doctorName)
	{
		this.doctorName = doctorName;
	}
	public String getDoctorName()
	{
		return doctorName;
	}
	
	public void setDoctorId(String doctorId)
	{
		this.doctorId = doctorId;
	}
	public String getDoctorId()
	{ return doctorId; }
	
	public void setSalary(double salary)
	{
		this.salary = salary;
	}
	public double getSalary()
	{
		return salary;
	}
	
	public void showDoctor()
	{
		System.out.println("Doctor Name: Dr. "+doctorName);
		System.out.println("Doctor ID: "+doctorId);
		System.out.println("Salary: "+salary+" BDT");
	}
	
	public String toString(){
		return "Doctor Name: Dr. "+doctorName+"\n"+
				"Doctor ID: "+doctorId+"\n"+
				"Salary: "+salary+" BDT"+"\n";
	}
	
}
