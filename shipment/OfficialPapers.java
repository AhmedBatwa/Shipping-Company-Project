package shipment;
import receiver.Receiver;
import sender.Sender;

public class OfficialPapers extends Shipment implements Undelayable{
	public OfficialPapers(Sender sender,Receiver receiver) {
		super(sender, receiver);
		super.setPrefferedDeliveryTime(new int[] {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1});
	}
}
