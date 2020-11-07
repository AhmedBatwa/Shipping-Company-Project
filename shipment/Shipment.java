package shipment;
import company.Carrier;
import receiver.Receiver;
import sender.Sender;
 
public abstract class  Shipment {
	
	private Sender sender;
	private Receiver receiver;
	private History history;
	private Carrier carrier;
	private int[] prefferedDeliveryTime;
	private int[] registeredDeliveryTime; // length = 2 {column,row} indices in the array of the AssignedShipment of its carrier 
	private Status status;
	
	
	public void setCarrier() {
		
	}
	public Shipment(int[] prefferedDeliveryTime ,Sender sender,Receiver receiver) {
		this.prefferedDeliveryTime = prefferedDeliveryTime;
		this.receiver=receiver;
		this.sender=sender;
	}
	public void setStatus(Status status,int hour) {
		
	}
	public void deliver(int Hour,int min) {
		// call receiver.receive(this);
	}
	
	public int[] getRegisteredDeliveryTime() {
		return prefferedDeliveryTime;
	}
	
	public Status getStatus() {
		return status;
		
	}
	public Sender getSender() {
		return sender;
	}
	public void setSender(Sender sender) {
		this.sender = sender;
	}
	public Receiver getReciever() {
		return receiver;
	}
	public void setReciever(Receiver reciever) {
		this.receiver = reciever;
	}
	public History getHistory() {
		return history;
	}
	public void setHistory(History history) {
		this.history = history;
	}
	public Carrier getCarrier() {
		return carrier;
	}
	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}
	public int[] getPrefferedDeliveryTime() {
		return prefferedDeliveryTime;
	}
	public void setPrefferedDeliveryTime(int[] prefferedDeliveryTime) {
		this.prefferedDeliveryTime = prefferedDeliveryTime;
	}
	public void setRegisteredDeliveryTime(int[] registeredDeliveryTime) {
		this.registeredDeliveryTime = registeredDeliveryTime;
	}
	
}
