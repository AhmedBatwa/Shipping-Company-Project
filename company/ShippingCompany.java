package company;

import java.io.IOException;
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
	
	
	
	
	
	
	private static int totalReceived;
	private static int totalDelivered;
	private static int totalFailed;

	
	
	
	
	
	
	
	public static void main(String args[]) {
		
		
		
	
		
		/* 
		 * ask the user for number of shipmments , employee
		 * simulate phase1();
		 * simulate phase2();
		 */

		System.out.println("=====================================================[Shipping Company]========================================================= ");

		
		
//		ask number of shipments , days ,hourlyLimit
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Number of Carriers : ");
		int numberOfCarriers= scanner.nextInt();
		System.out.println("Enter Number of Days : ");
		int numberOfDays= scanner.nextInt();
		System.out.println("Enter Number of limit of generated shipments per hour : ");
		int hourlyLimit= scanner.nextInt();
		
		
		
		
		
		
		
		System.out.println("=====================================================[Phase#1]========================================================= ");
		simulatePhase1(numberOfCarriers,numberOfDays,hourlyLimit);
		System.out.println("=======================================================[End]=========================================================== ");
		System.out.println("=====================================================[Phase#2]========================================================= ");
		simulatePhase2(numberOfCarriers,numberOfDays,hourlyLimit);
		System.out.println("=======================================================[End]=========================================================== ");

			
		
		
		
		
		
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
		 * printing the life updates is meant to life track the process
		 * printing the daily Report is meant to show the daily summary & statistics 
		 */
		
		
		
		
		
		
		
		
		
		init();
		generateCarries(numberOfCarriers);
		Random rnd=new Random();
		
		
		for(int day=0;day<numberOfDays;day++) {
			//start new day... start new counters
			totalDelivered=0;
			totalFailed=0;
			totalReceived=0;
			
			
			assignToCarrier(1);						   //this will assign the shippmets in repo from prev days before start receiving new ones
		for (int hour=0;hour<24;hour++) {
			System.out.printf("______________________________________________________[%02d:00]__________________________________________________________%n",hour);

			if(rnd.nextBoolean()) {
				generateShipments(rnd.nextInt(hourlyLimit)+1,hour,1);     //recieve random number of shipments at random hour
			}
			
		deliver(hour);          
		
		
		
		try {
			System.out.println("Press Enter to Continue...");
			System.in.read();
		} catch (IOException e) {
		}
		
		
		
		
		}
		printUpdates();		             //print hourly updates	
		printDailyReport(day); 			//print the Daily Report
		dailyCleanUp();
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static void simulatePhase2(int numberOfCarriers,int numberOfDays,int hourlyLimit) {
		/*
		 * generate carriers through the method generateCarriers()
		 * generate carriers , shipments,assign to carriers
		 * and start delivering process
		 * printing the life updates is meant to life track the process
		 * printing the daily Report is meant to show the daily summary & statistics 
		 */
		

		
		
		
		
		
		
		
		
		
		init();
		generateCarries(numberOfCarriers);
		Random rnd=new Random();
		
		
		
		
		
		for(int day=0;day<numberOfDays;day++) {
			
			
			
			//start new day... start new counters
			totalDelivered=0;
			totalFailed=0;
			totalReceived=0;
			assignToCarrier(2);						   //this will assign the shippmets in repo from prev days before start receiving new ones
			
			
			
			
		for (int hour=0;hour<24;hour++) {
			
			System.out.printf("______________________________________________________[%02d:00]__________________________________________________________%n",hour);
			
			
			if(rnd.nextBoolean()) {
				generateShipments(rnd.nextInt(hourlyLimit)+1,hour,2);     //recieve random number of shipments at random hour
			}
			
			
			
			deliver(hour);          		
		
			
			
			try {
				System.out.println("Press Enter to Continue...");
				System.in.read();
			} catch (IOException e) {
			}
			
			
			
			
			
		
		}
		
		
		
		
		
		
		printUpdates();		             //print hourly updates	
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
	 * it meant to remove all delivered shipments from the shipments stored 
	 * it will clear all the carriers assignedShipments 
	 * preparing for a new day
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
		
	
		
		System.out.printf("===================================================[Day#%d Report]=======================================================\n",day);
		System.out.println("Today's Recieved Shipments :\t"+totalReceived);
		System.out.println("Today's Delivered Shipments :\t"+totalDelivered);
		System.out.println("Today's Failed Shipments :\t"+totalFailed);
		System.out.println("Total Shipments in Depository :\t"+shipments.size());
		System.out.printf("_________________________________________________________________________________________________________________________\n",day);
		
		

		for(Shipment shipment:shipments) {
			System.out.println(shipment);
		}

		System.out.println("==================================================[End of Report]======================================================= ");

		
		
	}
	
	

	
	
	
	
	
	
	
	/**
	 * hourly updated report of shipments changed their status
	 * meant to life track the whole process 
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
		receiver.addShipment(shipment);
		sender.addShipment(shipment);
		
		//shipment is now received by the depositoray 
		shipment.setStatus(Status.IN_DEPOSITORY,hour);
		updatedShipments.add(shipment);
		totalReceived++;
		printUpdates();		             //print hourly updates	--> receiving

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
					printUpdates();		             //print hourly updates	--> Out for delivery
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
				printUpdates();		             //print hourly updates	--> out for delivery
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
					System.out.println("inside");
					//dropping the normal shipment to the depository
					shipment.getCarrier().dropShipment(shipment);
					shipment.setStatus(Status.RETURNED_TO_DEPOSITORY,hour);
					updatedShipments.add(shipment);
					

					
					//assigning the undelayble shipment to the carrier 
					shipment.getCarrier().assignShipment((Shipment)undelayableShipment,hour,2);
					((Shipment)undelayableShipment).setStatus(Status.OUT_FOR_DELIVERY,hour);
					updatedShipments.add(shipment);
					
					printUpdates();		             //print hourly updates	--> forceAssigning
					
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
			shipment = new OfficialPapers(sender, receiver,hour);
			break;
		case 1:
			shipment = new PersonalDelivery(sender, receiver);
			break;
		case 2:
			shipment = new Food(sender, receiver,hour);
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
			totalDelivered+=carrier.getHourlyReport()[0];
			totalFailed+=carrier.getHourlyReport()[1];
		}
		
		
	}
	
	
	//===============================================================================================================================================================

	
	
	
}













