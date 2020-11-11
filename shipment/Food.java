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
		for(int i=hour;i<hour+shelfLife ; i++) {
			if(i<PrefferedDeliveryTime.length)
				PrefferedDeliveryTime[i]=1;
			else
				PrefferedDeliveryTime[i-PrefferedDeliveryTime.length]=1;
		}
		setPrefferedDeliveryTime(PrefferedDeliveryTime);
	}
	


}
	
