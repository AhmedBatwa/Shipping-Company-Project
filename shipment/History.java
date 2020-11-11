package shipment;
public class History {
	
	private Status status;
	private int[] time;
	private int id;
	private String type;
	private int day;
		
	public History(Status status,int hour, int min) {
	this.status = status;
	time = new int[] {hour, min};
	}
	public History(Status status,int hour, int min,int day) {
		this(status,hour,min);
		this.day=day;
		}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day=day;
	}
	public String toString() {
		return String.format("Updated: [%02d:%02d] | Status: %-22s", time[0], time[1], status);
	}
}
