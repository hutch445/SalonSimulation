import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

	private static Customer currentCustomer, firstUp, secondUp, thirdUp;
	private static int timeWorking = 0;
	public static void main(String[] args) {
		
		Queue <Customer> arrivalsQueue = new LinkedList<> ();
		System.out.println("How many chars would you like in the beauty salon waiting area?");
		Scanner chairScanner = new Scanner(System.in);
		int numChairs = chairScanner.nextInt();
		chairScanner.close();
		File file = new File("CustomerList");
		
		Scanner myScanner = null;
		try 
		{
			myScanner = new Scanner(file);
			System.out.println("Opening and reading Customer.txt");
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		while (myScanner.hasNextLine())
		{
			int arrivalTime = myScanner.nextInt();
			int serviceTime = myScanner.nextInt();
			String name = myScanner.nextLine();
			Customer temp = new Customer(arrivalTime, serviceTime, name);
			arrivalsQueue.add(temp);
		}
		System.out.println("Starting a beauty salon simulation with 1 hairdresser and " + numChairs + " waiting chairs: ");
		
		Queue <Customer> waitingRoomQueue = new LinkedList<> ();
		Queue <Customer> hairdresserChair = new LinkedList<> ();
		int currentTime = 0;

		while (!arrivalsQueue.isEmpty() || !waitingRoomQueue.isEmpty() || currentCustomer != null)
		{

			
			if (currentTime == 0)
			{
				System.out.println("Hairdresser's chair is empty");
				System.out.println("Waiting chairs are empty");
				System.out.println("Arrival list");
			}
			else 
			{
				Customer customer = arrivalsQueue.peek();
				if (!arrivalsQueue.isEmpty() && customer.getArrivalTime() <= currentTime)
				{
					if (waitingRoomQueue.size() == 3)
					{
						arrivalsQueue.remove();
					}
					else 
					{
						waitingRoomQueue.add(customer);
						arrivalsQueue.remove();
					}
				}
		
				if (timeWorking == 0)
				{
					if (!hairdresserChair.isEmpty())
					{
						hairdresserChair.remove();
					}
					else
					{
						if (waitingRoomQueue.isEmpty())
						{
							System.exit(0);
						}
						else 
						{
							currentCustomer = waitingRoomQueue.peek();
							waitingRoomQueue.remove();
							timeWorking = currentCustomer.getServiceTime();
						}

					}
				}
				timeWorking--;
				System.out.println("Time = " + currentTime);
				System.out.println("Hairdresser's chair: ");
				System.out.println("\t " + currentCustomer.getName() + " Arrival: " + currentCustomer.getArrivalTime() + " Service Time: " + currentCustomer.getServiceTime());
			
				if (!waitingRoomQueue.isEmpty() || !hairdresserChair.isEmpty())
				{
					firstUp = waitingRoomQueue.peek();
					System.out.println("Waiting chairs: ");
					System.out.println("\t" + firstUp.getName() + ": arrival " + firstUp.getArrivalTime() + ": service = " + firstUp.getServiceTime());
					waitingRoomQueue.remove();
					if (!waitingRoomQueue.isEmpty())
					{
						secondUp = waitingRoomQueue.peek();
						System.out.println("\t" + secondUp.getName() + ": arrival " + secondUp.getArrivalTime() + ": service = " + secondUp.getServiceTime());
						waitingRoomQueue.remove();
						if (!waitingRoomQueue.isEmpty())
						{
							thirdUp = waitingRoomQueue.peek();
							System.out.println("\t" + thirdUp.getName() + ": arrival " + thirdUp.getArrivalTime() + ": service = " + thirdUp.getServiceTime());
							waitingRoomQueue.remove();
							waitingRoomQueue.add(firstUp);
							waitingRoomQueue.add(secondUp);
							waitingRoomQueue.add(thirdUp);
						}
						else 
						{
							waitingRoomQueue.add(firstUp);
							waitingRoomQueue.add(secondUp);
						}
					}
					else
					{
						waitingRoomQueue.add(firstUp);
					}
				}
				else 
				{
					System.out.println("The waiting chairs are empty");
				}
				
				System.out.println("Arrivals List: ");
				ArrayList <Customer> customerList = new ArrayList<Customer>(arrivalsQueue);
				for (int i = 0; i < customerList.size(); i++) 
				{
					System.out.println("\t " + customerList.get(i).getName() + ": arrival " + customerList.get(i).getArrivalTime() + ": service " + customerList.get(i).getServiceTime());
				}
			}
		currentTime++;
		}		
	}
}
