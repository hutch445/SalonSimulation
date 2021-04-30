/*
 * Authors: Zachary Bourque, Ty Hutchison
 */
public class Customer {

	private int arrivalTime, serviceTime;
	private String name;
	private Customer nextCustomer;
	
	public Customer(int arrivalTime, int serviceTime, String name, Customer nextCustomer)
	{
		this.arrivalTime = arrivalTime;
		this.serviceTime = serviceTime;
		this.name = name;
		this.nextCustomer = nextCustomer;
	}
	
	public Customer(int arrivalTime, int serviceTime, String name)
	{
		this.arrivalTime = arrivalTime;
		this.serviceTime = serviceTime;
		this.name = name;
	}
	
	public void setArrivalTime(int arrivalTime)
	{
		this.arrivalTime = arrivalTime;
	}
	
	public void setServiceTime(int serviceTime)
	{
		this.serviceTime = serviceTime;
	}
	
	public int getArrivalTime()
	{
		return arrivalTime;
	}
	
	public int getServiceTime()
	{
		return serviceTime;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setNextCustomer(Customer nextCustomer)
	{
		this.nextCustomer = nextCustomer;
	}

	public Customer getNextCustomer()
	{
		return nextCustomer;
	}
	
	public void display()
	{
		System.out.println(arrivalTime + "\t" + serviceTime + "\t" + name);
	}
}
