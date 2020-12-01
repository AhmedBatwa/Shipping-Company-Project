package company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

import company.Carrier.Shift;
import receiver.*;
import sender.*;
import shipment.*;


public class ShippingCompany {
	
	protected static ArrayList<Carrier> carriers = new ArrayList<>();
	protected static ArrayList<Shipment> shipments= new ArrayList<>();
	protected static ArrayList<Sender> senders = new ArrayList<>();
	protected static ArrayList<Receiver> receivers= new ArrayList<>();
	protected static ArrayList<Shipment> cumulativeShipments= new ArrayList<>();
	protected static Queue<Shipment> updatedShipments = new LinkedList<>();
	
	
	
	
	
	
	protected static int totalReceived;
	protected static int totalDelivered;
	protected static int totalFailed;
	protected static int totalInDepository;
	protected static int totalExpired;
	protected static int totalOutForDelivery;
	protected static int totalReturnedToSender;
	protected static int morningCarriers;
	protected static int eveningCarriers;
	protected static int nightCarriers;
	protected static int numberOfFailedPhase1;
	protected static int numberOfFailedPhase2;
	
	protected static int curr_day;
	protected static int curr_hour;
	protected static int carrierShift;
	protected static int cumluativeFailed;
	protected static int cumluativeRecieved;
	protected static int cumluativeDelivered;
	
        
        protected static int phase1Average;
	protected static int phase2Average;
	
//	public static void main(String args[]) {
//		/* 
//		 * ask the user for number of shipmments , employee
//		 * simulate phase1();
//		 * simulate phase2();
//		 */
//		//YOU MAY TRACK THE SHIPMENT FULL HISTORY USING shipment.track();		
//		System.out.println("=====================================================[Shipping Company]========================================================= ");
//
//		int numberOfCarriers=0;
//		int numberOfDays=0;
//		int hourlyLimit=0;
////		ask number of shipments , days ,hourlyLimit
//		
//		Scanner scanner = new Scanner(System.in);
//		while(numberOfCarriers<1 || numberOfDays <1 || hourlyLimit<1) {
//			 			 
//			try {
//		System.out.println("Enter Number of Carriers : ");
//		numberOfCarriers= scanner.nextInt();
//		System.out.println("Enter Number of Days : ");
//		numberOfDays= scanner.nextInt();
//		System.out.println("Enter Number of limit of generated shipments per hour : ");
//		hourlyLimit= scanner.nextInt();
//			}catch(InputMismatchException e) {
//				System.out.println("Sorry... You Must Enter Valid Number!");
//				scanner.next();
//			}
//		}
//		
//		
//		
//		
//		
//		System.out.println("=====================================================[Phase#1]========================================================= ");
//		simulatePhase1(numberOfCarriers,numberOfDays,hourlyLimit);
//		System.out.println("=======================================================[End]=========================================================== ");
//		System.out.println("=====================================================[Phase#2]========================================================= ");
//		simulatePhase2(numberOfCarriers,numberOfDays,hourlyLimit);
//		System.out.println("=======================================================[End]=========================================================== ");
//		System.out.println(numberOfFailedPhase1);
//		System.out.println(numberOfFailedPhase2);
//		System.out.println("Percentage of Improvement: "+ ((1-(double)numberOfFailedPhase2/(numberOfFailedPhase1+numberOfFailedPhase2)))*100);
//			
//	}
//	
	
	
	
	
	
	
	
	
	
	
	
	//new
	
	
	
	
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	protected static void simulatePhase1(int numberOfCarriers,int numberOfDays,int hourlyLimit) {
		/*
		 * generate carriers , shipments,assign to carriers
		 * and start delivering process
		 * printing the life updates is meant to life track the process
		 * printing the daily Report is meant to show the daily summary & statistics 
		 */
                int count = 0;
		init();
		generateCarries(numberOfCarriers);
		Random rnd=new Random();
		for(int day=1;day<=numberOfDays;day++) {
                    curr_day = day;
			//start new day... start new counters
			totalDelivered=0;
			totalFailed=0;
			totalReceived=0;
			totalInDepository = 0;
			System.out.printf("=======================================================[Day#%d]=========================================================%n",day);
		assignToCarrier(1);						   //this will assign the shippmets in repo from prev days before start receiving new ones
		for (int hour=0;hour<24;hour++) {
                    count++;
                    curr_hour = hour;
			int carrierAtShift=0;
			if (hour>=8 && hour <16) {
				carrierAtShift=morningCarriers;
                                carrierShift = morningCarriers;
			}
			else if (hour>=16&& hour<24) {
				carrierAtShift=eveningCarriers;
                                carrierShift = morningCarriers;
			}
			else if(hour>=0 && hour<8) {
				carrierAtShift=nightCarriers;
                                carrierShift = morningCarriers;
			}
			System.out.println();
			System.out.println();
			System.out.printf("___________________________________________[Day#%d][%02d:00][%s%03d]_________________________________________________%n",day,hour,"Carriers#:",carrierAtShift);
			System.out.printf("%-15s  |    %-18s  |  %-25s  | %-5s   | %-3s   | %-12s\n","ID","\tType","\t Status"," Time","Day","Days Elapsed");
			System.out.printf("_________________________________________________________________________________________________________________________\n",day);
			
			if(rnd.nextBoolean()) {
				generateShipments(rnd.nextInt(hourlyLimit)+1,hour,day,1);     //recieve random number of shipments at random hour
			}
		deliver(hour);          
	/*	try {
			System.out.println("Press Enter to Continue...");
			System.in.read();
		} catch (IOException e) {
		}
		*/
		}
		printUpdates();		             //print hourly updates	
		cleanDepository();
		dailyCounters();
		printDailyReport(day); //print the Daily Report
		dailyCleanUp();
//		historyTracking();
		}
		
		numberOfFailedPhase1=totalFailed;
                phase1Average = cumulativeShipments.size()/count;
	}

        
 	protected static void simulatePhase1_DailyHourly(int numberOfCarriers,int numberOfDays,int hourlyLimit, int DAY, int HOUR) {
		/*
		 * generate carriers , shipments,assign to carriers
		 * and start delivering process
		 * printing the life updates is meant to life track the process
		 * printing the daily Report is meant to show the daily summary & statistics 
		 */
                int count = 0;
		init();
		generateCarries(numberOfCarriers);
		Random rnd=new Random();
                DAY:
		for(int day=1;day<=numberOfDays;day++) {
                    curr_day = day;
			//start new day... start new counters
			totalDelivered=0;
			totalFailed=0;
			totalReceived=0;
			totalInDepository = 0;
			System.out.printf("=======================================================[Day#%d]=========================================================%n",day);
		assignToCarrier(1);						   //this will assign the shippmets in repo from prev days before start receiving new ones
                HOUR:
                for (int hour=0;hour<24;hour++) {
                    count++;
                    curr_hour = hour;
			int carrierAtShift=0;
			if (hour>=8 && hour <16) {
				carrierAtShift=morningCarriers;
                                carrierShift = morningCarriers;
			}
			else if (hour>=16&& hour<24) {
				carrierAtShift=eveningCarriers;
                                carrierShift = morningCarriers;
			}
			else if(hour>=0 && hour<8) {
				carrierAtShift=nightCarriers;
                                carrierShift = morningCarriers;
			}
			System.out.println();
			System.out.println();
			System.out.printf("___________________________________________[Day#%d][%02d:00][%s%03d]_________________________________________________%n",day,hour,"Carriers#:",carrierAtShift);
			System.out.printf("%-15s  |    %-18s  |  %-25s  | %-5s   | %-3s   | %-12s\n","ID","\tType","\t Status"," Time","Day","Days Elapsed");
			System.out.printf("_________________________________________________________________________________________________________________________\n",day);
			
			if(rnd.nextBoolean()) {
				generateShipments(rnd.nextInt(hourlyLimit)+1,hour,day,1);     //recieve random number of shipments at random hour
			}
		deliver(hour);          
                if(hour == HOUR) break HOUR;
	/*	try {
			System.out.println("Press Enter to Continue...");
			System.in.read();
		} catch (IOException e) {
		}
		*/
		}
		printUpdates();		             //print hourly updates	
		cleanDepository();
		dailyCounters();
		printDailyReport(day); //print the Daily Report
		dailyCleanUp();
                if(day == DAY) break DAY;
//		historyTracking();
		}
		numberOfFailedPhase1=totalFailed;
                phase1Average = cumulativeShipments.size()/count;
	}

        
	
	
	
	
	
	
	
	
	
	
	protected static void simulatePhase2(int numberOfCarriers,int numberOfDays,int hourlyLimit) {
		/*
		 * generate carriers through the method generateCarriers()
		 * generate carriers , shipments,assign to carriers
		 * and start delivering process
		 * printing the life updates is meant to life track the process
		 * printing the daily Report is meant to show the daily summary & statistics 
		 */
                int count =0;
		init();
		generateCarries(numberOfCarriers);
		Random rnd=new Random();
		for(int day=1;day<=numberOfDays;day++) {
			curr_day = day;
			System.out.printf("=======================================================[Day#%d]=========================================================%n",day);

			
			//start new day... start new counters
			totalDelivered=0;
			totalFailed=0;
			totalReceived=0;
			totalInDepository = 0;
			assignToCarrier(2);						   //this will assign the shippmets in repo from prev days before start receiving new ones
		for (int hour=0;hour<24;hour++) {
                    count++;
                    curr_hour = hour;
			int carrierAtShift=0;
			if (hour>=8 && hour <16) {
				carrierAtShift=morningCarriers;
                                carrierShift = morningCarriers;

			}
			else if (hour>=16&& hour<24) {
				carrierAtShift=eveningCarriers;
                                carrierShift = morningCarriers;
			}
			else if(hour>=0 && hour<8) {
				carrierAtShift=nightCarriers;
                                carrierShift = morningCarriers;
			}
			System.out.println();
			System.out.println();
			System.out.printf("___________________________________________[Day#%d][%02d:00][%s%03d]_________________________________________________%n",day,hour,"Carriers#:",carrierAtShift);
			System.out.printf("%-15s  |    %-18s  |  %-25s  | %-5s   | %-3s   | %-12s\n","ID","\tType","\t Status"," Time","Day","Days Elapsed");
			System.out.printf("_________________________________________________________________________________________________________________________\n",day);
			if(rnd.nextBoolean()) {
				generateShipments(rnd.nextInt(hourlyLimit)+1,hour,day,2);    //receive random number of shipments at random hour
			}
			deliver(hour);          		
		//	try {
				//System.out.println("Press Enter to Continue...");
				//System.in.read();
		//	} catch (IOException e) {
		//	}
		
		}
		printUpdates();		             //print hourly updates	
		cleanDepository();
		dailyCounters();
		printDailyReport(day); //print the Daily Report
		dailyCleanUp();
		
		}
		 numberOfFailedPhase2=totalFailed;
                 phase2Average = cumulativeShipments.size()/count;
	}
	
	protected static void simulatePhase2_DailyHourly(int numberOfCarriers,int numberOfDays,int hourlyLimit, int DAY, int HOUR) {
		/*
		 * generate carriers through the method generateCarriers()
		 * generate carriers , shipments,assign to carriers
		 * and start delivering process
		 * printing the life updates is meant to life track the process
		 * printing the daily Report is meant to show the daily summary & statistics 
		 */
                int count =0;
		init();
		generateCarries(numberOfCarriers);
		Random rnd=new Random();
                DAY:
		for(int day=1;day<=numberOfDays;day++) {
                    curr_day = day;
			System.out.printf("=======================================================[Day#%d]=========================================================%n",day);

			
			//start new day... start new counters
			totalDelivered=0;
			totalFailed=0;
			totalReceived=0;
			totalInDepository = 0;
			assignToCarrier(2);						   //this will assign the shippmets in repo from prev days before start receiving new ones
                        HOUR:
        		for (int hour=0;hour<24;hour++) {
                        count++;
                        curr_hour = hour;
			int carrierAtShift=0;
			if (hour>=8 && hour <16) {
				carrierAtShift=morningCarriers;
                                carrierShift = morningCarriers;
			}
			else if (hour>=16&& hour<24) {
				carrierAtShift=eveningCarriers;
                                carrierShift = morningCarriers;
			}
			else if(hour>=0 && hour<8) {
				carrierAtShift=nightCarriers;
                                carrierShift = morningCarriers;
			}
			System.out.println();
			System.out.println();
			System.out.printf("___________________________________________[Day#%d][%02d:00][%s%03d]_________________________________________________%n",day,hour,"Carriers#:",carrierAtShift);
			System.out.printf("%-15s  |    %-18s  |  %-25s  | %-5s   | %-3s   | %-12s\n","ID","\tType","\t Status"," Time","Day","Days Elapsed");
			System.out.printf("_________________________________________________________________________________________________________________________\n",day);
			if(rnd.nextBoolean()) {
				generateShipments(rnd.nextInt(hourlyLimit)+1,hour,day,2);    //receive random number of shipments at random hour
			}
			deliver(hour);
                        if(hour == HOUR) break HOUR;		//	try {
				//System.out.println("Press Enter to Continue...");
				//System.in.read();
		//	} catch (IOException e) {
		//	}
		
		}
		printUpdates();		             //print hourly updates	
		cleanDepository();
		dailyCounters();
		printDailyReport(day); //print the Daily Report
		dailyCleanUp();
		if(day == DAY) break DAY;
		}
		 numberOfFailedPhase2=totalFailed;
                 phase2Average = cumulativeShipments.size()/count;
	}
	
	
	
	
	
	
	
	
	private static void init() {
		/*
		 * initialize to start simulating another phase
		 * clear all data related to previously simulated phase
		 */
		Shipment.resetShipmentsCounter();
		cumulativeShipments.clear();
		carriers.clear();
		senders.clear();
		shipments.clear();
		receivers.clear();
		cumluativeFailed = 0;
		cumluativeRecieved=0;
		cumluativeDelivered=0;
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
			if(shipment.getStatus()==Status.DELIVERED || shipment.getStatus()==Status.RETURNED_TO_SENDER || shipment.getStatus()==Status.EXPIRED) {
				DeliveredShipments.add(shipment);
			}
			shipment.incrementDays();
			
		}
		
		
		shipments.removeAll(DeliveredShipments);
		
		
		for(Carrier carrier: carriers) {
			carrier.dailyCleanUp();
		}
		
	}
	
	public static void cleanDepository() {
		// Changed here: the inner if statements
		for(Shipment shipment:shipments) {
			if (shipment.getDaysElapsed()>2) {
				shipment.setStatus(Status.RETURNED_TO_SENDER,0);
		}
			if (shipment instanceof Food) {
					shipment.setStatus(Status.EXPIRED,23);				//setFood as expired, and add it to totalFailed
			}
		}
	}

	
	
	
	
	

	
	private static void dailyCounters() {
		for (Shipment shipment : shipments) {
			if(shipment.getStatus() == Status.DELIVERY_FAILED || shipment.getStatus() == Status.EXPIRED || shipment.getStatus() == Status.RETURNED_TO_SENDER) {
				totalFailed ++;
			}else if(shipment.getStatus() == Status.DELIVERED) {
				totalDelivered ++;
			}
			if(shipment.getStatus()==Status.IN_DEPOSITORY || shipment.getStatus()==Status.DELIVERY_FAILED ) {
				totalInDepository++;
			}
                        if(shipment.getStatus()==Status.EXPIRED ) {
				totalExpired++;
			}
                        if(shipment.getStatus()==Status.RETURNED_TO_SENDER ) {
				totalReturnedToSender++;
			}
                        if(shipment.getStatus()==Status.OUT_FOR_DELIVERY) {
				totalOutForDelivery++;
			}
		}
		cumluativeFailed += totalFailed;
		cumluativeDelivered += totalDelivered;
		cumluativeRecieved += totalReceived;
	}
	
	
	
	
	
	
	private static void printDailyReport(int day) {
		
		System.out.printf("===================================================[Day#%d Report]=======================================================\n",day);
		System.out.println("Today's Recieved Shipments :\t"+totalReceived);
		System.out.println("Today's Delivered Shipments :\t"+totalDelivered);
		System.out.println("Today's Failed Shipments :\t"+totalFailed);
		System.out.println("Currently in depository:"+ (shipments.size()-totalDelivered));
		System.out.println("Shipments not handled today:"+ (shipments.size()-totalDelivered-totalFailed));
		System.out.println("cumulative Shipments :\t"+cumulativeShipments.size());
		System.out.printf("_________________________________________________________________________________________________________________________\n",day);
		System.out.printf("%-15s  |    %-18s  |  %-25s  | %-5s   | %-3s   | %-12s\n","ID","\tType","\t Status"," Time","Day","Days Elapsed");
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static void receive(Shipment shipment , Sender sender,Receiver receiver,int hour,int day,int simulatedPhase) {
		 
		/*
		 * Receives the shipment from the sender
		 * now the shipment is in depositoray
		 * record receiver,sender,shipment into company's records
		 * will pass the shipment to assignToCarrier 
		 * 
		 */
		
		
		
		
		
		shipments.add(shipment);
		cumulativeShipments.add(shipment);
		receivers.add(receiver);
		senders.add(sender);
		receiver.addShipment(shipment);
		sender.addShipment(shipment);
		
		//shipment is now received by the depositoray 
		shipment.setStatus(day,Status.IN_DEPOSITORY,hour);
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
		 morningCarriers=(int) Math.ceil((double)numberOfCarriers/3);
		 eveningCarriers=(int) Math.floor((double)numberOfCarriers/3);
		 nightCarriers=(int) numberOfCarriers-(morningCarriers + eveningCarriers);

		
		
		
		
		
		
	
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
	
	
	
	
	
	
	
	private static void generateShipments(int numberOfShipments,int hour,int day,int simulatedPhase) {
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
		 receive(shipment,sender,receiver,hour,day,simulatedPhase);
		 
		 
		 
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
		printUpdates();		             //print hourly updates	--> receiving
		
		
	}
	
	
	public static void historyTracking() {
		Scanner in = new Scanner(System.in);
		int input = 0;
		while(true) {
			System.out.print("Enter number of shipment to track OR 0 to continue or exit: ");
			try{input = in.nextInt();}
			catch(InputMismatchException e) {System.out.println("Please enter a valid value");}
			if(input == 0) {return;}
			else {cumulativeShipments.get(input-1).trackShipment();}
		}
	}
	

	
	//===============================================================================================================================================================

	
	
	
}













