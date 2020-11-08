package company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

import company.Carrier.Shift;
import receiver.*;
import sender.*;
import shipment.*;


public class ShippingCompany {
	
	private static ArrayList<Carrier> carriers = new ArrayList<>();
	private static ArrayList<Shipment> shipments= new ArrayList<>();
	private static ArrayList<Sender> senders = new ArrayList<>();
	private static ArrayList<Receiver> receivers= new ArrayList<>();
	private static Queue<Shipment> updatedShipments = new LinkedList<>();
	
	
	/**TODO
	 *	[]clean the code
	 *	[]documnetion
	 *	[]trace
	 *	[]for shipment , insert:
	 *		[]toString() which prints the history and the full details of the shipment
	 *		[]edit .setStatus(Status status) ===> .setStatus(Status status,int hour)
	 *		[]add a new enum in Status RETURNED_TO_DEPOSITORY
	 * 	[]let the simultions 1,2 stops at each iteration to wait for the user to press Enter...
	 */
	
	
	
	
	public static void main(String args[]) {
		//simulation of the two phases

		System.out.println("Shipping Company");

//		ask number of shipments , days
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Number of Carriers : ");
		int numberOfCarriers= scanner.nextInt();
		System.out.println("Enter Number of Days : ");
		int numberOfDays= scanner.nextInt();
		System.out.println("Enter Number of limit of generated shipments per hour : ");
		int hourlyLimit= scanner.nextInt();
		
		
		System.out.println("===================================================[Phase#1]======================================================= ");
		simulatePhase1(numberOfCarriers,numberOfDays,hourlyLimit);
		System.out.println("=====================================================[End]========================================================= ");
		System.out.println("===================================================[Phase#2]======================================================= ");
		simulatePhase2(numberOfCarriers,numberOfDays,hourlyLimit);
		System.out.println("=====================================================[End]========================================================= ");

			
		
		
		/*
		 * ask the user for number of shipmments , employee
		 * simulate phase1();
		 * simulate phase2();
		 */
	}
	
	
	
	
	
	
	
	//================================================================[Simulation processing]=========================================================================
	/** simulates two different phases :
	 * 
	 *		 1.First Phase: 
	 *			where the problem clearly illustrated , number of failed delevered shipment is high, and urgent shipments may face delay
	 *		
 	 * 		2.Secound Phase : 
 	 *			the impact of the problem is beeing reduced by providing solutions as introducing preferred Undelayble shipments
 	 *			providing preferred delivery time for  receivers and shipments
 	 *
 	 *General process : 
 	 *	1)daily loop iterate for the 24 hour
 	 *	2)randomly recieve shipments at random hours
 	 *	3)deliver shipments at each hour
 	 *	4)print hourly updates
 	 *	5)at the end of the day print a full report
 	 *
 	 *
 	 */
	
	
	
	
	
	
	private static void simulatePhase1(int numberOfCarriers,int numberOfDays,int hourlyLimit) {
		/*
		 * generate carriers , shipments,assign to carriers
		 * and start delivering process
		 */
		
		
		
		init();
		generateCarries(numberOfCarriers);
		Random rnd=new Random();
		
		
		for(int day=0;day<numberOfDays;day++) {
			assignToCarrier(1);						   //this will assign the shippmets in repo from prev days before start receiving new ones
		for (int hour=0;hour<24;hour++) {
			
			if(rnd.nextBoolean()) {
				generateShipments(rnd.nextInt(hourlyLimit)+1,hour,1);     //recieve random number of shipments at random hour
			}
			
		deliver(hour);          
		printUpdates();		             //print hourly updates	
		}
		printDailyReport(day); 			//print the Daily Report
		dailyCleanUp();
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static void simulatePhase2(int numberOfCarriers,int numberOfDays,int hourlyLimit) {
		/*
		 * generate carriers through the method generateCarriers()
		 * generateShipments  
		 * startDelivering
		 */
		

		init();
		generateCarries(numberOfCarriers);
		Random rnd=new Random();
		
		
		for(int day=0;day<numberOfDays;day++) {
			assignToCarrier(1);						   //this will assign the shippmets in repo from prev days before start receiving new ones
		for (int hour=0;hour<24;hour++) {
			
			if(rnd.nextBoolean()) {
				generateShipments(rnd.nextInt(hourlyLimit)+1,hour,2);     //recieve random number of shipments at random hour
			}
			
		deliver(hour);          
		printUpdates();		             //print hourly updates	
		}
		printDailyReport(day); 			//print the Daily Report
		dailyCleanUp();
		}
	}
	
	
	
	
	
	
	
	
	
	private static void init() {
		/*
		 * initialize to start simulating another phase
		 * clear all data related to previously simulated phase
		 */
		carriers.clear();
		senders.clear();
		shipments.clear();
		receivers.clear();
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * daily clean up the data , it will keep the non-delivered shipments , and will reset the assignedShipment for each carrier
	 */
	
	
	private static void dailyCleanUp() {
		
		ArrayList<Shipment> DeliveredShipments = new ArrayList<>();
		
		for (Shipment shipment:shipments) {
			if(shipment.getStatus()==Status.DELIVERED) {
				DeliveredShipments.add(shipment);
			}
		}
		
		
		shipments.removeAll(DeliveredShipments);
		
		
		for(Carrier carrier: carriers) {
			carrier.dailyCleanUp();
		}
		
	}
	
	

	

	
	
	
	
	
	
	
	
	private static void printDailyReport(int day) {
		/*
		 * TODO :
		 * 	[] loop through all the shippments in shpiments and print its history  (shipment.getHistory().printHistory())  or simply ( shipment )  -> toString
		 * 	[] inserts speratiors with labels 
		 *  [] ask for adding a History.printHistory()
		 *  [] ask initailly to comment the RecievedTime, and other times except the deiveryTime in History class 
		 */
		
		
		System.out.printf("===================================================[Day#%d Report]=======================================================\n",day);
		System.out.printf("Total Recieved Shipments :");
		System.out.printf("Total Delivered Shipments :");
		System.out.printf("Total Delivered Shipments :");
		

		for(Shipment shipment:shipments) {
			System.out.println(shipment.getStatus());
		}

		System.out.println("==================================================[End of Report]======================================================= ");

		
		/*
		 * TODO : 
		 *  []implement the 24 recieving deliviring at same time 
		 */
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * hourly updated report of shipments changed their status
	 */
	
	private static void printUpdates() {      
		while(!updatedShipments.isEmpty()) {
		System.out.println(updatedShipments.poll());
		}
	}
	
	
	
	
	
	
	

	/**
	 * assign the shipments in repository from previous days 
	 * @param simulatedPhase
	 */
	
	private static void assignToCarrier(int simulatedPhase) {
		if(shipments.isEmpty()) return;
		
		for(Shipment shipment : shipments) {
		assignToCarrier(shipment,0,simulatedPhase);
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	//================================================================[Receiving processing]=========================================================================
		/** in this stage : 
		 * the shipments will be received from the senders starting stored in depository , then will be assigned to carrier based on the phase,
		 * in phase one the carrier will accept the shipment as he has an empty slot and during his working hours
		 * in the secound phase the carrier will give the undelayble shipments the highest priority and will consider the preffered delivery time
		 * based on intersection of the preffered times of the shipment and the receiver during his workin hours.
		 */
	
	
	
	private static void receive(Shipment shipment , Sender sender,Receiver receiver,int hour,int simulatedPhase) {
		 
		/*
		 * Receives the shipment from the sender
		 * now the shipment is in depositoray
		 * record receiver,sender,shipment into company's records
		 * will pass the shipment to assignToCarrier 
		 * 
		 */
		
		
		
		
		
		shipments.add(shipment);
		receivers.add(receiver);
		senders.add(sender);
		
		
		
		//shipment is now received by the depositoray 
		shipment.setStatus(Status.IN_DEPOSITORY,hour);
		updatedShipments.add(shipment);
		
		
		// will try assigning the shipment to a carrier (Out for delivery) on the preffered time
		assignToCarrier(shipment,hour,simulatedPhase);
	}
	
	
	
	
	
	
	
	private static void assignToCarrier(Shipment shipment,int hour,int simulatedPhase) {
		
		/*
		 * loops through all the carriers call carrier.assignShipment see if accepted
		 * returns true if carrier accepts shipment
		 * if undelayable hasn't been assigned call forceAssign 
		 */
		
		
		
		
		//================================================================Phase1=========================================================================

		
		if(simulatedPhase==1) {
			// as it is on the carrier shift's he will accept it with no priorty
			
			for (Carrier carrier : carriers) {
				if(carrier.assignShipment(shipment,hour,1)) {
					shipment.setStatus(Status.OUT_FOR_DELIVERY,hour);
					updatedShipments.add(shipment);
					return;
				}
			}
			
			
		}
		
		
		
		//================================================================Phase2=========================================================================

		
		else if(simulatedPhase==2) {
			
			
		for (Carrier carrier : carriers) {
			if(carrier.assignShipment(shipment,hour,2)) {
				shipment.setStatus(Status.OUT_FOR_DELIVERY,hour);
				updatedShipments.add(shipment);
				return;
			}
		}
		
		
		
		//force assign undelayable shipments
		if( shipment instanceof Undelayable ) {
			forceAssignToCarrier((Undelayable)shipment,hour);
		}
		
		}
	}
	
	
	
	
	

	
	
	private static void forceAssignToCarrier(Undelayable undelayableShipment,int hour) {
	
		
		/*
		 * loop through the shipments array to find a normal shipment at the preferred time of the undelayable shipment
		 * then access its carrier + registeredDeliveryTime 
		 * remove it from shipment array , carrier assignedShipment 
		 * assign this shipment to the carrier, and add it shipments array , sender ,receiver details
		 */
		
		
		
		
		
		for (Shipment shipment : shipments) {
			
		
			//the shipment will be droped to the depository must be normal shipment & equipping a time block 
			if(!(shipment instanceof Undelayable)  && shipment.getStatus()==Status.OUT_FOR_DELIVERY ) {
				
				
				/**
				 * checks if the normal shipment equipies a prefered time block for the Undelayble 
				 * a preffered delivery time is intersection between the preffered delivery time for the shipmment 
				 * and the preffered deliverey time for the receiver
				 * then we can delay the normal shipment & instead deliver the undelayble
				 */
				
				boolean isPrefferedForShipment = ((Shipment)undelayableShipment).getPrefferedDeliveryTime()[shipment.getRegisteredDeliveryTime()[0]]==1;
				boolean isPrefferedForRecevier = ((Shipment)undelayableShipment).getReciever().getPreferredDeliveryTime()[shipment.getRegisteredDeliveryTime()[0]]==1;
				
				
				if(isPrefferedForShipment && isPrefferedForRecevier ) {
						
					//dropping the normal shipment to the depository
					shipment.getCarrier().dropShipment(shipment);
					shipment.setStatus(Status.RETURNED_TO_DEPOSITORY,hour);
					updatedShipments.add(shipment);
					
					//assigning the undelayble shipment to the carrier 
					shipment.getCarrier().assignShipment((Shipment)undelayableShipment,hour,2);
					((Shipment)undelayableShipment).setStatus(Status.OUT_FOR_DELIVERY,hour);
					updatedShipments.add(shipment);
					
					return;
					
				}
				
				
				
			}
		
		}
		
	}

	
	
	
	
	//================================================================[Initializing process]=========================================================================
       /** generation of the carriers and shipments based on given number of carriers and shipments
        * in phase two the diffrent recievers and diffrent shipments have diffrent treatment based on either a shipment is 
        * Undelayable or a normal shipment , a receiver is avalible at what time , what is the recomended deleivery time
        * The carriers have three shifts : Morning (08:00-16:00), Evening (16:00-00:00), Night (00:00-08:00) working shifts.
        * 
        */
	
	private static void generateCarries(int numberOfCarriers) {
		/*
		*loop to generate carrier
		*carriers divided equally into the three shifts
		*/
		
		
		
		//distribute the carriers equally to the 3 shifts , considering non-divisible by 3 numberOfCarriers case
		int morningCarriers=(int) Math.ceil((double)numberOfCarriers/3);
		int eveningCarriers=(int) Math.floor((double)numberOfCarriers/3);
		int nightCarriers=(int) numberOfCarriers-(morningCarriers + eveningCarriers);

		
		
	
		//morning shift carriers
		for (int i=0;i<morningCarriers;i++) {
			carriers.add(new Carrier(Shift.MORNING_SHIFT));
		}
		
		
		
		
		//evening shift carriers
		for (int i=0;i<eveningCarriers;i++) {
			carriers.add(new Carrier(Shift.EVENING_SHIFT));
		}
		
		
		
		
		
		//night shift carriers
		for (int i=0;i<nightCarriers;i++) {
			carriers.add(new Carrier(Shift.NIGHT_SHIFT));
		}
		
	}
	
	
	
	
	
	
	
	private static void generateShipments(int numberOfShipments,int hour,int simulatedPhase) {
		/*
		*loop to receive(  shipment ,  sender, receiver)
		*/
		
		
		
		
		
		//randomly generate diffrent shipments 
		Random rnd = new Random();
		Sender sender;
		Receiver receiver;
		Shipment shipment;
		
		for ( int i=0;i<numberOfShipments;i++) {
		//generate a random sender
		switch(rnd.nextInt(3)) {
		case 0:
			sender = new Goverment();
			break;
		case 1:
			sender = new Company();
			break;
		default:
			sender = new Person();
			break;
		}
		
		
		
		
		
		
		
		
		
		//generate a random receiver
		switch(rnd.nextInt(4)) {
		case 0:
			receiver = new GovermentOffice();
			break;
		case 1:
			receiver = new CompanyOffice();
			break;
		case 2:
			receiver = new Individual();
			break;
			
		default:
			receiver = new OnStreetBuisness();
		}
		
		
		
		
		
		
		
		//generate a random shipment
		switch(rnd.nextInt(4)) {
		case 0:
			shipment = new OfficialPapers(sender, receiver);
			break;
		case 1:
			shipment = new PersonalDelivery(sender, receiver);
			break;
		case 2:
			shipment = new Food(sender, receiver);
			break;
			
		default:
			shipment = new ECompanySales(sender, receiver);
		}
		
		
		
		
		
		
		//receives the shipment from the sender 
		 receive(shipment,sender,receiver,hour,simulatedPhase);
		 
		 
		 
		}
		
	}
	
	

	
	//================================================================[Deleivering processing]=========================================================================
		/**
		 *  the shipment considered delivered as it is out for deleivery at the PrefereedDeleveryTime for both the shipment and the receiver
		 *  Otherwise the shipment status is Delivery_Failed 
		 *  Pre-defined assumption is that a shipment on average takes 20 min to be deleveried
		 *  This shows that the carrier has the ability to deleiver 3 shipments per hour at best case senario.
		 */
	

	
	
	
	
	private static void deliver(int hour) {
		
		/*
		*loop through all the carriers array and call method deliver(hour) from carrier class
		*/
		
		
		for(Carrier carrier : carriers) {
			updatedShipments.addAll(carrier.deliver(hour));   //add all the shipments updated
		}
		
		
	}
	
	
	//===============================================================================================================================================================

	
	
	
}













