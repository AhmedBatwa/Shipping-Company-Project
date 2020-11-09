package shipment;
public class History {
	
	private Status status;
	private int[] time;
	private int id;
	private String type;
		
	public History(Status status, int hour, int min) {
	this.status = status;
	time = new int[] {hour, min};
	}
	
	public String toString() {
		return String.format("Updated: [%02d:%02d] | Status: %-22s", time[0], time[1], status);
	}
}
