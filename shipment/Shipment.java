package shipment;
import company.Carrier;
import receiver.Receiver;
import sender.Sender;
 
public abstract class  Shipment {
	
	private Sender sender;
	private Carrier carrier;
	private Receiver receiver;
	private History history;
	private int[] prefferedDeliveryTime;
	private int[] registeredDeliveryTime; // length = 2 {column,row} indices in the array of the AssignedShipment of its carrier 
	private Status status;
	
	// Constructors
	public Shipment(Sender sender,Receiver receiver) {
		this.receiver=receiver;
		this.sender=sender;
		this.prefferedDeliveryTime = new int[24];
		for(int i : prefferedDeliveryTime) {i= 1;} // Default preferred time
		this.history=new History();
	}
	public Shipment(Sender sender,Receiver receiver,int[] prefferedDeliveryTime) {
		this.prefferedDeliveryTime = prefferedDeliveryTime;
		this.receiver=receiver;
		this.sender=sender;
		
	}
	
	// Setters
	public void setSender(Sender sender) {this.sender = sender;}
	
	public void setCarrier(Carrier carrier) {this.carrier = carrier;}
	
	public void setReciever(Receiver reciever) {this.receiver = reciever;}
	
	public void setHistory(History history) {this.history = history;}

	public void setPrefferedDeliveryTime(int[] prefferedDeliveryTime) {this.prefferedDeliveryTime = prefferedDeliveryTime;}

	public void setRegisteredDeliveryTime(int[] registeredDeliveryTime) {this.registeredDeliveryTime = registeredDeliveryTime;}

	public void setStatus(Status status,int hour) {this.status = status;}
	
	// Getters
	public Sender getSender() {return sender;}
	
	public Carrier getCarrier() {return carrier;}

	public Receiver getReciever() {return receiver;}

	public History getHistory() {return history;}
	
	public int[] getPrefferedDeliveryTime() {return prefferedDeliveryTime;}

	public int[] getRegisteredDeliveryTime() {return prefferedDeliveryTime;}

	public Status getStatus() {return status;}

	// Action methods
	public void deliver(int Hour,int min) {
		if(receiver.Recieve(this) == true) {
			status = status.DELIVERED;
			history.setDeliveredTime(registeredDeliveryTime);
		}
		else {status = status.DELIVERY_FAILED;}
	}
}
