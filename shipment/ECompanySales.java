package shipment;
import sender.Sender;
import receiver.Receiver;

public class ECompanySales extends Shipment{
	// Constructors
	public ECompanySales(Sender sender,Receiver receiver) {
		super(sender, receiver);
		super.setPrefferedDeliveryTime(new int[] {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0});
	}
}
