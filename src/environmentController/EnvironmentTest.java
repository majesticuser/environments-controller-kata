package environmentController;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EnvironmentTest {
	
	private Environment env;
		
	@Before
	public void setUp() {
		this.env = new Environment();
	}
	
	@Test
	public void tempIs70_allOff() {
		env.setTemp(70);
		assertIdle();
	}
	
	@Test
	public void tempIs75_coolerAndBlowerOn() {
		env.setTemp(75);
		assertCooling();
	}
	
	@Test
	public void tempIs65Then75_heaterOnThenCoolerOn() {
		env.setTemp(65);	
		assertHeating();
		
		env.setTemp(75);
		assertCooling();
	}

	@Test
	public void tempIs65_heaterAndBlowerOn() {
		env.setTemp(65);
		assertHeating();
	}
	
	@Test
	public void afterCooling_blowerStaysOn5Minutes() {
		env.setMinuteAndTemp(0, 75);
		assertCooling();
		
		env.setMinuteAndTemp(1, 70);
		assertOnlyBlowing();
		
		env.setMinuteAndTemp(2, 70);
		assertOnlyBlowing();
		
		env.setMinuteAndTemp(3, 70);
		assertOnlyBlowing();
		
		env.setMinuteAndTemp(4, 70);
		assertOnlyBlowing();

		env.setMinuteAndTemp(5, 70);
		assertOnlyBlowing();
		
		env.setMinuteAndTemp(6, 70);
		assertIdle();
	}
	
	@Test
	public void afterHeating_blowerStaysOn5Minutes() {
		env.setMinuteAndTemp(0, 65);
		assertHeating();
		
		env.setMinuteAndTemp(1, 70);
		assertOnlyBlowing();
		
		env.setMinuteAndTemp(2, 70);
		assertOnlyBlowing();
		
		env.setMinuteAndTemp(3, 70);
		assertOnlyBlowing();
		
		env.setMinuteAndTemp(4, 70);
		assertOnlyBlowing();
		
		env.setMinuteAndTemp(5, 70);
		assertOnlyBlowing();
		
		env.setMinuteAndTemp(6, 70);
		assertIdle();
	}
	
	@Test
	public void afterCooling_coolerCanNotStartAgainBeforeXMinutes() {
		env.setMinuteAndTemp(0, 75);
		assertCooling();
		
		env.setMinuteAndTemp(1, 65);
		assertHeating();
		
		env.setMinuteAndTemp(2, 75);
		assertOnlyBlowing();
	}
	
	private void assertIdle() {
		assertState("");
	}
	
	private void assertState(String state) {
		assertEquals("Heater in wrong state", state.contains("H"), env.isHeaterOn());
		assertEquals("Cooler in wrong state", state.contains("C"), env.isCoolerOn());
		assertEquals("Blower in wrong state", state.contains("B"), env.isBlowerOn());
	}
	
	private void assertHeating() {
		assertState("HB");
	}

	private void assertCooling() {
		assertState("CB");
	}
	
	private void assertOnlyBlowing() {
		assertState("B");
	}
}
