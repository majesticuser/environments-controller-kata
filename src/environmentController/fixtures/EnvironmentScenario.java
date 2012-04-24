package environmentController.fixtures;

import environmentController.Environment;

public class EnvironmentScenario {
	
	private Environment env;
	private int minute;
	private int temp;
	
	public EnvironmentScenario(String comment) {
		this.env = new Environment();
	}
	
	public void setMinute(int minute) {
		this.minute = minute;
	}
	
	public void setTemp(int temp) {
		this.temp = temp;
	}
	
	public String Heater() {
		return this.env.isHeaterOn() ? "X" : "-";
	}

	public String Blower() {
		return this.env.isBlowerOn() ? "X" : "-";
	}
	
	public String Cooler() {
		return this.env.isCoolerOn() ? "X" : "-";
	}
	
	public void execute() {
		env.setMinuteAndTemp(minute, temp);
	}
}
