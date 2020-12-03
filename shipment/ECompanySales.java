package shipment;
import sender.Sender;
import receiver.Receiver;

public class ECompanySales extends Shipment{
	// Constructors
	public ECompanySales(Sender sender,Receiver receiver) {
		super(sender, receiver);
		super.setPrefferedDeliveryTime(new int[] {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0});
	}
	
	// Print
//	public String toString() {return String.format("%-15s |  %-22s |  %-27s | [%-5s] | [%-1d]",
//			super.toString(), getClass().getSimpleName(), getStatus(), getLastUpdateTime(),getHistory().get(0).getDay());}
}
