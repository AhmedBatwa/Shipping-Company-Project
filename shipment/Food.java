package shipment;
import sender.Sender;
import receiver.Receiver;

public class Food extends Shipment implements Undelayable{
	
	
	private static final int shelfLife=2;
	/**
	 * food has limited shelf life assumed to be 2 hours here
	 *
	 */
	public Food(Sender sender , Receiver receiver,int hour) {
		super(sender, receiver);
		init(hour);
	}
	
	public void init(int hour){
		int[] PrefferedDeliveryTime= new int[24];
	for(int i=hour;i<shelfLife ; i++) {
		PrefferedDeliveryTime[i]=1;
	}
	setPrefferedDeliveryTime(PrefferedDeliveryTime);
	
	}
	
	
}
	
