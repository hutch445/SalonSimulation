/*
 * Authors: Zachary Bourque, Ty Hutchison
 */
public class Arrivals {

	private Customer head, tail;
	
	public Arrivals()
	{
		head = null;
		tail = null;
	}
	
	public boolean isEmpty()
	{
		return head == null;
	}
	
	public void addCustomer(int arrivalTime, int serviceTime, String name)
	{
		if (isEmpty())
		{
			head = new Customer(arrivalTime, serviceTime, name);
			tail = head;
		}
		else 
		{
			Customer temp = new Customer(arrivalTime, serviceTime, name);
			tail.setNextCustomer(temp);
			tail = temp;
		}
	}
	
	public Customer removeCustomer()
	{
		Customer temp = head;
		head = head.getNextCustomer();
		return temp;
	}
	
	public Customer nextCustomer()
	{
		return head;
	}
	
	public void displayArrivalList()
	{
		Customer curr = head;
		while (curr != null)
		{
			curr.display();
			curr = curr.getNextCustomer();
		}
	}
}
