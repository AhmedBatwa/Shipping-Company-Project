package shipment;
import receiver.Receiver;
import sender.Sender;

public class PersonalDelivery extends Shipment{
	public PersonalDelivery(Sender sender, Receiver receiver) {
		super(sender, receiver);
		super.setPrefferedDeliveryTime(new int[] {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1});
	}
}
