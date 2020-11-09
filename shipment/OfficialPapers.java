package shipment;
import receiver.Receiver;
import sender.Sender;

public class OfficialPapers extends Shipment implements Undelayable{
	/**
	 * assuption 8 working hours the sender usually will take part of it
	 * to deliver the shipment to the office thus the time remaining will be reduced
	 * the effective assumed to be 5 here which is less than a full working shift
	 *
	 */
	private static final int shelfLife=5;
	
	// Constructors
	public OfficialPapers(Sender sender,Receiver receiver,int hour) {
		super(sender, receiver);
		init(hour);
	}
	
	// Set preferred delivery time
	public void init(int hour){
		int[] PrefferedDeliveryTime= new int[24];
		for(int i=hour;i<hour+shelfLife ; i++) {
			if(i<PrefferedDeliveryTime.length)
				PrefferedDeliveryTime[i]=1;
			else
				PrefferedDeliveryTime[i-PrefferedDeliveryTime.length]=1;
		}
		setPrefferedDeliveryTime(PrefferedDeliveryTime);
	}
	
	// Print
	public String toString() {return String.format("%-15s | Shimpent Type: %-22s | Shipment Status: %-27s | [%-5s]",
			super.toString(), "Official Papers", getStatus(), getLastUpdateTime());}

}
