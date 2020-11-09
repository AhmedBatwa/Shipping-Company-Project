package shipment;
import sender.Sender;
import receiver.Receiver;

public class Food extends Shipment implements Undelayable{
	/**
	 * food has limited shelf life assumed to be 2 hours.
	 */
	private static final int shelfLife=2;
	
	// Constructors
	public Food(Sender sender , Receiver receiver,int hour) {
		super(sender, receiver);
		init(hour);
	}
	
	// Set preferred delivery time
	public void init(int hour){
		int[] PrefferedDeliveryTime= new int[24];
		for(int i=hour;i<shelfLife ; i++) {PrefferedDeliveryTime[i]=1;}
		setPrefferedDeliveryTime(PrefferedDeliveryTime);
	}
	
	// Print
	public String toString() {return String.format("%s | Shimpent Type: %s | Shipment Status: %s", super.toString(), "Food", getStatus());}
}
	
