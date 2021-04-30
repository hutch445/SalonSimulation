import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
 * Authors: Zachary Bourque, Ty Hutchison
 */
public class TestArrivals {

	private static Customer currentCustomer, firstUp, secondUp, thirdUp;
	private static int timeWorking = 0;
	// TODO make while loop, if a chair is open, pull from the waiting room to the open seat.
	public static void main(String[] args)
	{
		
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
		while (!arrivalsQueue.isEmpty() || !waitingRoomQueue.isEmpty() || !hairdresserChair.isEmpty()) 
		{
			Customer customer = arrivalsQueue.peek();
			if (!arrivalsQueue.isEmpty())
			{
				if (customer.getArrivalTime() == currentTime) 
				{
					if (waitingRoomQueue.size() == numChairs)
					{
						currentTime++;
					}
					else 
					{
						arrivalsQueue.poll();
						waitingRoomQueue.add(customer);
						System.out.println("Added " + customer.getName() + " to the waiting room queue");
					}
				}
			}
			
			
			System.out.println("Time = "+ currentTime);
			if (currentTime == 0)
			{
				// For first time through
				System.out.println("Hairdresser's chair is empty");
				System.out.println("Waiting chairs are empty");
				System.out.println("Arrivals List");
			}
			else 
			{
				if (hairdresserChair.isEmpty())
				{
//					// Current Customer Stuff
					currentCustomer = waitingRoomQueue.peek();
					hairdresserChair.add(currentCustomer);
					timeWorking = currentCustomer.getServiceTime()-1;
//					
//					// Queue Management
					waitingRoomQueue.remove();
					
					// Printing
					System.out.println("Hairdresser's chair:");
					System.out.println("\t " + currentCustomer.getName() + ": arrival = " + currentCustomer.getArrivalTime() + ": service = " + currentCustomer.getServiceTime());
					if (waitingRoomQueue.isEmpty())
					{
						System.out.println("Waiting chairs are empty");
						System.out.println("Arrivals List");
					}
					else
					{
						Queue <Customer> tempWaitingRoom = waitingRoomQueue;
						for (int i = 0; i <= numChairs; i++)
						{
							if (!tempWaitingRoom.isEmpty())
							{
								Customer nextCustomer = tempWaitingRoom.peek();
								tempWaitingRoom.remove();
								System.out.println("Waiting chairs: ");
								System.out.println("\t" + nextCustomer.getName() + ": arrival = " + nextCustomer.getArrivalTime() + ": service = " + nextCustomer.getServiceTime());
							}	
						}
						System.out.println("Arrivals List");
					}
					
				}
				else 
				{
					System.out.println("Time working is " + timeWorking);
					if (timeWorking == 0)
					{
						if (!hairdresserChair.isEmpty())
						{
							hairdresserChair.remove();
						}
						currentCustomer = waitingRoomQueue.poll();
						timeWorking = currentCustomer.getServiceTime();
						System.out.println("New service time is " + timeWorking + " which equals " + currentCustomer.getServiceTime());
						System.out.println("Hairdresser's chair: ");
						System.out.println("\t " + currentCustomer.getName() + ": arrival = " + currentCustomer.getArrivalTime() + ": service = " + currentCustomer.getServiceTime());
						timeWorking--;
						if (!waitingRoomQueue.isEmpty())
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
							System.out.println("Arrivals List");
						}
					}
					else 
					{
						System.out.println("Hairdresser's chair: " + currentCustomer.getName());
						System.out.println("\t " + currentCustomer.getName() + ": arrival = " + currentCustomer.getArrivalTime() + ": service = " + currentCustomer.getServiceTime());
						timeWorking--;
						if (!waitingRoomQueue.isEmpty())
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
							System.out.println("Arrivals List");
						}


					}
				}
			}
		currentTime++;
		System.out.println("The size of waiting room is" + waitingRoomQueue.size());
		}
	}
}
