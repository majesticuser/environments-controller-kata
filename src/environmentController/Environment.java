package environmentController;

public class Environment {

	private int minute = 0;
	private int temp;
	private boolean wasCooling = false;

	public boolean isHeaterOn() {
		return temp < 66;
	}

	public boolean isCoolerOn() {
		boolean isCoolerOn = temp > 74; // && !(wasCooling && minute < 3);
		wasCooling = isCoolerOn;
		return isCoolerOn;
	}

	public boolean isBlowerOn() {
		return isHeaterOn() || isCoolerOn() || (minute > 0 && minute < 6);
	}

	public void setTemp(int temp) {
		setMinuteAndTemp(0, temp);
	}

	public void setMinuteAndTemp(int minute, int temp) {
		this.minute = minute;
		this.temp = temp;
	}

}
