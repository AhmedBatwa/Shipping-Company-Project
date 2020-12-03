package shipment;
import java.util.ArrayList;

import company.Carrier;
import receiver.Receiver;
import sender.Sender;

public abstract class  Shipment implements Cloneable{
	
	private Sender sender;
	private Carrier carrier;
	private Receiver receiver;
	private int[] prefferedDeliveryTime;
	private int[] registeredDeliveryTime; // length = 2 {column,row} indices in the array of the AssignedShipment of its carrier 
	private Status status;
	private String lastUpdateTime;
	private static int shipmentsCounter;
	private final int ID;
	private ArrayList<History> history;
	public int daysElapsed;
	
	
	// Constructors
	public Shipment(Sender sender,Receiver receiver) {
		this.receiver=receiver;
		this.sender=sender;
		this.prefferedDeliveryTime = new int[24];
		shipmentsCounter++;
		ID = shipmentsCounter;
		history = new ArrayList<>();
	}
	public Shipment(Sender sender,Receiver receiver,int[] prefferedDeliveryTime) {
		this(sender, receiver);
		this.prefferedDeliveryTime = prefferedDeliveryTime;
	}
	
	public Object clone() 
	{ 
		try{return super.clone();}catch (CloneNotSupportedException e) {}
		return this;
	} 
	
	
	
	// Setters
	public void setSender(Sender sender) {this.sender = sender;}
	
	public void setCarrier(Carrier carrier) {this.carrier = carrier;}
	
	public void setReciever(Receiver reciever) {this.receiver = reciever;}
	
	public void setPrefferedDeliveryTime(int[] prefferedDeliveryTime) {this.prefferedDeliveryTime = prefferedDeliveryTime;}

	public void setRegisteredDeliveryTime(int[] registeredDeliveryTime) {this.registeredDeliveryTime = registeredDeliveryTime;}

	public void setStatus(Status status,int hour) {setStatus(status, hour, 0,0);}
	
	public void setStatus(int day,Status status,int hour) {setStatus(status, hour,0,day);}
	
	public void setStatus(Status status,int hour, int min, int day) {
		this.status = status;
		setLastUpdateTime(hour, min);
		history.add(new History(status, hour, min,day));
		}
	public void setStatus(Status status,int hour, int min) {
		this.status = status;
		setLastUpdateTime(hour, min);
		history.add(new History(status, hour, min,this.daysElapsed));
		}
	public void setLastUpdateTime(int hour, int min) {
		lastUpdateTime = String.format("%02d:%02d", hour,min);
	}
	
	public static void resetShipmentsCounter() {
		shipmentsCounter = 0;
	}
	
	// Getters
	public Sender getSender() {return sender;}
	
	public Carrier getCarrier() {return carrier;}

	public Receiver getReciever() {return receiver;}
	
	public int[] getPrefferedDeliveryTime() {return prefferedDeliveryTime;}

	public int[] getRegisteredDeliveryTime() {return registeredDeliveryTime;}

	public Status getStatus() {return status;}
	
	public int getID() {return this.ID;}

	public int getCounter() {return shipmentsCounter;}
	
	public String getLastUpdateTime() {return lastUpdateTime;}
	
	public ArrayList<History> getHistory(){
		return history;
	}
	
	// Print

	public String toString() {return String.format("#%-15s |  %-22s |  %-27s | [%-5s] | [%03d] | [%1d]",
					Integer.toString(getID()), getClass().getSimpleName(), getStatus(), getLastUpdateTime(),getHistory().get(0).getDay(),this.getDaysElapsed());}

	// Prints all the actions that the shipment have gone through
	public String trackShipment(int a) {
		String text = String.format("Shipment ID: %d\t|\tShipment Type: %s\t|\tCurrent Status: %s\n", getID(), this.getClass().getSimpleName(), getStatus());
		for(History h : history) {
			text+=h.toString()+"\n";
	}
		return text;
	}
	public void trackShipment() {
		System.out.println("\n===================================================[SHIPMENT HISTORY]=======================================================\n");
		String text = String.format("Shipment ID: %d\t|\tShipment Type: %s\t|\tCurrent Status: %s", getID(), this.getClass().getSimpleName(), getStatus());
		System.out.println(text);
		for(History h : history) {
			System.out.println(h.toString());
		}
		System.out.println("\n====================================================[END OF HISTORY]========================================================\n");
	}
	
	// Action methods
	public boolean deliver(int hour,int min) {
		if(receiver.receive(this) == true) {
			setStatus(Status.DELIVERED, hour, min);
			return true;
		}
		setStatus(Status.DELIVERY_FAILED, hour, min);
		return false;
	}
	public int getDaysElapsed() {
		return daysElapsed;
	}
	public void incrementDays() {
		daysElapsed++;
	}
}
