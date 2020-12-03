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
	protected static ArrayList<String> reports1=new ArrayList<>();
	protected static ArrayList<String> reports2=new ArrayList<>();
	
	protected static ArrayList<ArrayList<String>> hourReports1=new ArrayList<>();
	protected static ArrayList<ArrayList<String>> hourReports2=new ArrayList<>();
	
	
	
	
	
	
	
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
	protected static int cumluativeFailed1;
	protected static int cumluativeRecieved1;
	protected static int cumluativeDelivered1;
	
	protected static int cumluativeFailed2;
	protected static int cumluativeRecieved2;
	protected static int cumluativeDelivered2;
        
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
//	
	
	
	

	
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
            hourReports1.add(new ArrayList<String>());
			totalDelivered=0;
			totalFailed=0;
			totalReceived=0;
			totalInDepository = 0;
			System.out.printf("=======================================================[Day#%d]=========================================================%n",day);
			assignToCarrier(day,1);		//this will assign the shippmets in repo from prev days before start receiving new ones
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
				deliver(day, hour, 1);          
				printUpdates(day, hour, 1);//print hourly updates	
		}
		cleanDepository();
		dailyCounters(1);
		printDailyReport(day,1); //print the Daily Report
		dailyCleanUp();
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
            hourReports2.add(new ArrayList<String>());
			totalDelivered=0;
			totalFailed=0;
			totalReceived=0;
			totalInDepository = 0;
			assignToCarrier(day,2);						   //this will assign the shippmets in repo from prev days before start receiving new ones
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
				deliver(day,hour,2);    
				printUpdates(day,hour,2);
			}
			cleanDepository();
			dailyCounters(2);
			printDailyReport(day,2); //print the Daily Report
			dailyCleanUp();
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
		cumluativeFailed1 = 0;
		cumluativeRecieved1 = 0;
		cumluativeDelivered1 = 0;
		
		cumluativeFailed2 = 0;
		cumluativeRecieved2 = 0;
		cumluativeDelivered2 = 0;
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
		for(Shipment shipment:shipments) {
			if (shipment.getDaysElapsed()>2) {
				shipment.setStatus(Status.RETURNED_TO_SENDER,0);
			}
			if (shipment instanceof Food) {
					shipment.setStatus(Status.EXPIRED,23);				//setFood as expired, and add it to totalFailed
			}
		}
	}

	
	
	
	
	

	
	private static void dailyCounters(int phase) {
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
		if(phase == 1) {
			cumluativeFailed1 += totalFailed;
			cumluativeDelivered1 += totalDelivered;
			cumluativeRecieved1 += totalReceived;
		}else if (phase == 2) {
			cumluativeFailed2 += totalFailed;
			cumluativeDelivered2 += totalDelivered;
			cumluativeRecieved2 += totalReceived;
		}
	}
	
	
	
	
	
	
	private static void printDailyReport(int day,int phase) {
		String report="";
		
		
		System.out.printf("===================================================[Day#%d Report]=======================================================\n",day);
		report+=String.format("===================================================[Day#%d Report]=======================================================\n",day);
		System.out.println("Today's Recieved Shipments :\t"+totalReceived);
		report+=String.format("Today's Recieved Shipments :\t"+totalReceived+"\n");
		System.out.println("Today's Delivered Shipments :\t"+totalDelivered);
		report+=String.format("Today's Delivered Shipments :\t"+totalDelivered+"\n");
		System.out.println("Today's Failed Shipments :\t"+totalFailed);
		report+=String.format("Today's Failed Shipments :\t"+totalFailed+"\n");
		System.out.println("Currently in depository:"+ (shipments.size()-totalDelivered));
		report+=String.format("Currently in depository:"+ (shipments.size()-totalDelivered)+"\n");
		System.out.println("Shipments not handled today:"+ (shipments.size()-totalDelivered-totalFailed));
		report+=String.format("Shipments not handled today:"+(shipments.size()-totalDelivered-totalFailed)+"\n");
		System.out.println("cumulative Shipments :\t"+cumulativeShipments.size());
		report+=String.format("cumulative Shipments :\t"+cumulativeShipments.size()+"\n");
		System.out.printf("_________________________________________________________________________________________________________________________\n",day);
		report+=String.format("_________________________________________________________________________________________________________________________\n",day);
		System.out.printf("%-15s  |    %-18s  |  %-25s  | %-5s   | %-3s   | %-12s\n","ID","\tType","\t Status"," Time","Day","Days Elapsed");
		report+=String.format("%-15s  |    %-18s  |  %-25s  | %-5s   | %-3s   | %-12s\n","ID","\tType","\t Status"," Time","Day","Days Elapsed");
		System.out.printf("_________________________________________________________________________________________________________________________\n",day);
		report+=String.format("_________________________________________________________________________________________________________________________\n",day);

		for(Shipment shipment:shipments) {
			System.out.println(shipment);
			report+=String.format(shipment.toString()+"\n");
			
		}

		System.out.println("==================================================[End of Report]======================================================= ");
		
		report+=String.format("==================================================[End of Report]======================================================= \n");
		if(phase == 1)reports1.add(report);
		else if(phase == 2)reports2.add(report);
	}
	
	

	
	
	
	
	
	
	
	/**
	 * hourly updated report of shipments changed their status
	 * meant to life track the whole process 
	 */
	
	private static void printUpdates(int day, int hour, int phase) {  
		String report = "";
		report += String.format("___________________________________________[Day#%d][%02d:00]_________________________________________________________%n",day,hour);
		report += String.format("%-15s  |    %-18s  |  %-25s  | %-5s   | %-3s   | %-12s\n","ID","\tType","\t Status"," Time","Day","Days Elapsed");
		report += String.format("_________________________________________________________________________________________________________________________\n",day);
		
		while(!updatedShipments.isEmpty()) {
			Shipment shipment = updatedShipments.poll();
			System.out.println(shipment.toString());
			report+=String.format(shipment.toString()+"\n");
		}
		
		if(phase == 1) {hourReports1.get(day-1).add(report);}
		else if(phase == 2) {hourReports2.get(day-1).add(report);}
	}
	
	
	
	
	
	
	
	
	
	

	/**
	 * assign the shipments in repository from previous days 
	 * @param simulatedPhase
	 */
	
	private static void assignToCarrier(int day,int simulatedPhase) {
		if(shipments.isEmpty()) return;
		
		for(Shipment shipment : shipments) {
		assignToCarrier(shipment,day,0,simulatedPhase);
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
		
		// shipment is now received by the depositoray 
		shipment.setStatus(day,Status.IN_DEPOSITORY,hour);
		
		updatedShipments.add((Shipment)shipment.clone());
		totalReceived++;
		// will try assigning the shipment to a carrier (Out for delivery) on the preferred time
		assignToCarrier(shipment, day, hour,simulatedPhase);
	}
	
	
	
	
	
	
	

	private static void assignToCarrier(Shipment shipment,int day, int hour ,int simulatedPhase) {

		
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
					updatedShipments.add((Shipment)shipment.clone());
					return;
				}
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		//================================================================Phase2=========================================================================

		
		
		
		
		
		
		
		
		else if(simulatedPhase==2) {
			
			
		for (Carrier carrier : carriers) {
			if(carrier.assignShipment(shipment,hour,2)) {
				shipment.setStatus(Status.OUT_FOR_DELIVERY,hour);
				updatedShipments.add((Shipment)shipment.clone());
				return;
			}
		}
		
		//force assign undelayable shipments
		if( shipment instanceof Undelayable ) {
			forceAssignToCarrier((Undelayable)shipment, day, hour, simulatedPhase);
		}
		
		}
	}
	
	
	
	
	
	
	private static void forceAssignToCarrier(Undelayable undelayableShipment,int day, int hour, int phase) {
	
		
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
					updatedShipments.add((Shipment)shipment.clone());

					
					
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
	

	
	private static void deliver(int day, int hour, int phase) {
		
		/*
		*loop through all the carriers array and call method deliver(hour) from carrier class
		*/
		
		
		for(Carrier carrier : carriers) {
			for(Shipment shipment:carrier.deliver(hour)) {
				updatedShipments.add((Shipment)shipment.clone());
			}
		}
	}
	public static String historyTracking(int trackingNum) {
		String text;
		text= cumulativeShipments.get(trackingNum-1).trackShipment(1);
		return text;
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
public static String printReports(int day, int phase) {
	
	if(phase == 1) {return reports1.get(day-1);}
	else {return reports2.get(day-1);}
}
	
public static String printHourReports(int day, int hour, int phase) {
	
	if(phase == 1) {return hourReports1.get(day-1).get(hour);}
	else {return hourReports2.get(day-1).get(hour);}
}

public static ArrayList<ArrayList<String>> getHourReports1() {return hourReports1;}
public static ArrayList<ArrayList<String>> getHourReports2() {return hourReports2;}

	
}













