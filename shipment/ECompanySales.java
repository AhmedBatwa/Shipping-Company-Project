package shipment;
import sender.Sender;
import receiver.Receiver;

public class ECompanySales extends Shipment{
	public ECompanySales(Sender sender,Receiver receiver) {
		super(new int[] {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},sender,receiver); //TODO make sense of input
	}
}
