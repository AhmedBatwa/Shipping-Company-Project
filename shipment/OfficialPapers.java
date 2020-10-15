package shipment;

import receiver.Receiver;
import sender.Sender;

public class OfficialPapers extends Shipment implements Undelayable{
	
	public OfficialPapers(Sender sender,Receiver receiver) {
		super(new int[] {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}, sender, receiver);    //TODO make sense of input
	}
	
}
