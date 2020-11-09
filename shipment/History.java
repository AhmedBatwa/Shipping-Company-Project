package shipment;

	public class History {
		
		/*time convention used : 
		 * 24 hours system
		 * {hour,min} eg: {1,30} = 1:30
		 */
		//if min is not given = [hour,00]
		
	private int[] recievedTime;
	private int[] LeftDepositoryTime;
	private int[] DeliveredTime;
	
	public int[] getRecievedTime() {
		return recievedTime;
	}
	public void setRecievedTime(int[] recievedTime) {
		this.recievedTime = recievedTime;
	}
	public int[] getLeftDepositoryTime() {
		return LeftDepositoryTime;
	}
	public void setLeftDepositoryTime(int[] leftDepositoryTime) {
		LeftDepositoryTime = leftDepositoryTime;
	}
	public int[] getDeliveredTime() {
		return DeliveredTime;
	}
	public void setDeliveredTime(int[] deliveredTime) {
		DeliveredTime = deliveredTime;
	}
	public String toString() {
		String text;
			text="Received time for shipment:"+getRecievedTime()+"\nShipment left depository at :"+getLeftDepositoryTime()+
			"\nDelivered time for the shipment:"+getDeliveredTime();
			return text;
		
	}
	}
