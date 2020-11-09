package shipment;
import sender.Sender;
import receiver.Receiver;

public class ECompanySales extends Shipment{
	// Constructors
	public ECompanySales(Sender sender,Receiver receiver) {
		super(sender, receiver);
		super.setPrefferedDeliveryTime(new int[] {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1});
	}
	
	// Print
	public String toString() {return String.format("%-15s | Shimpent Type: %-22s | Shipment Status: %-27s | [%-5s]",
			super.toString(), "E Company Sales", getStatus(), getLastUpdateTime());}
}
