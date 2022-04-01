import java.util.Calendar;

public class SmartLight extends SmartObject implements LocationControl, Programmable{
	
	private boolean hasLightTurned;
	private Calendar programTime;
	private boolean programAction = false;;
	
	public SmartLight(String alias, String macId) {
        super();
		super.setAlias(alias);
		super.setMacId(macId);
	}
	
	public void turnOnLight() {
        // turns on the light if there is an internet connection and the light is off
		if(getConnectionStatus() == true) {
			if(hasLightTurned == false) {
				hasLightTurned = true;
				System.out.println("SmartLight - " + getAlias() + " is turned on now (Current Time: " 
                    + Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" 
					+ Calendar.getInstance().get(Calendar.MINUTE) + ":"
					+ Calendar.getInstance().get(Calendar.SECOND) +")"	);
			}
			else {
				System.out.println("SmartLight - " + getAlias() + " has been already turned on.");
			}				
		}
		else {
			System.out.println("There is no internet connection.");	
		}
	}
	
	public void turnOffLight() {
        // turns off the light if there is an internet connection and the light is on
		if(getConnectionStatus()) {
			if(hasLightTurned == true) {
				hasLightTurned = false;
				System.out.println("SmartLight - " + getAlias() + " is turned off now (Current Time: " 
                    + Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" 
					+ Calendar.getInstance().get(Calendar.MINUTE) + ":"
					+ Calendar.getInstance().get(Calendar.SECOND) +")"	);
			}
			else {
				System.out.println("SmartLight - " + getAlias() + " has been already turned off.");
			}
		}
		else
		    System.out.println("There is no internet connection.");
	}
	
	@Override
	public boolean testObject() {
        //tests lights if there is an internet connection to check whether there is a problem
		if(getConnectionStatus()) {
			System.out.println("Test is starting for SmartLight");   
			SmartObjectToString();
			turnOnLight();
			turnOffLight();
			System.out.println("Test completed for SmartLight\n");
			return true;
		}
		else {
			return false;
		}		
		
	}

	@Override
	public boolean shutDownObject() {
        //Turns off the light if there is an internet connection.
		if(getConnectionStatus()) {
			if(hasLightTurned == true) {
				hasLightTurned = false;
				SmartObjectToString();
				return true;
			}
			else {
				System.out.println("The light has been already turned off.");
				return false;
			}
		}
		else {
			return false;
		}
		
	}

    @Override
	public void onLeave() {
        //turns off the lights when people leave the house
		if(getConnectionStatus()) {
			hasLightTurned = false;
			System.out.println("On Leave -> SmartLight - " + getAlias());
            System.out.println("SmartLight - " + getAlias() + " is turned off now (Current Time : " 
            + Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + 
            + Calendar.getInstance().get(Calendar.MINUTE) + ":" +
            + Calendar.getInstance().get(Calendar.SECOND)+  ")");
		}
		else {
			System.out.println("There is no internet connection.");	
		}
		
		
	}

	@Override
	public void onCome() {
        //turns on the lights when people come the house
		if(getConnectionStatus()) {
			hasLightTurned = true;
			System.out.println("On Come -> SmartLight - " + getAlias());
			System.out.println("SmartLight - " + getAlias() + " is turned on now (Current Time : " 
                + Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + 
                + Calendar.getInstance().get(Calendar.MINUTE) + ":" +
                + Calendar.getInstance().get(Calendar.SECOND)+  ")");
		}
		else {
			System.out.println("There is no internet connection.");	
		}
	}

	@Override
	public void setTimer(int seconds) {
        //sets timer to turn on/off the lights.
		if(getConnectionStatus()) {
			programTime = Calendar.getInstance();
			programTime.add(Calendar.SECOND, seconds);
			programAction = true;
			if(hasLightTurned == true) {
				System.out.println("SmartLight - " + getAlias() + " will be turned off " +seconds + " seconds later!"
                    + "(Current Time : " + programTime.get(Calendar.HOUR_OF_DAY) + ":" 
					+ Calendar.getInstance().get(Calendar.MINUTE) + ":"
					+ Calendar.getInstance().get(Calendar.SECOND) + ")");
				
						
			}
			else {
				System.out.println("SmartLight - " + getAlias() + " will be turned on " +seconds + " seconds later!"
                    + "(Current Time : " + programTime.get(Calendar.HOUR_OF_DAY) + ":" 
					+ Calendar.getInstance().get(Calendar.MINUTE) + ":"
					+ Calendar.getInstance().get(Calendar.SECOND) + ")");
					
			}
		}
		else {
			System.out.println("There is no internet connection.");	
		}
		
	}

	@Override
	public void cancelTimer() {
        //cancel the timer.     
		if(getConnectionStatus()) {
			programTime = null;
			programAction = false;
		}
		else {
			System.out.println("There is no internet connection.");	
		}
		
	}

	@Override
	public void runProgram() {
         //according to the programAction and programTime, the lights will be turned on/off.       
		if(getConnectionStatus()) {
			if(programAction == true) {
				if(programTime != null){
					if(programTime.get(Calendar.HOUR_OF_DAY) == Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
					&& programTime.get(Calendar.MINUTE) == Calendar.getInstance().get(Calendar.MINUTE)
					&& programTime.get(Calendar.SECOND) == Calendar.getInstance().get(Calendar.SECOND)) {
						System.out.println("RunProgram -> SmartLight - " + getAlias());
						if(hasLightTurned == true) {
							turnOffLight();
							programTime = null;
						}
						else {
							turnOnLight();
							programTime = null;
						}
					}
				}
			}
			
		}
		else {
			System.out.println("There is no internet connection.");	
		}
		
	}

	

	public boolean getHasLightTurned() {
		return hasLightTurned;
	}

	public void setHasLightTurned(boolean hasLightTurned) {
		this.hasLightTurned = hasLightTurned;
	}

	public Calendar getProgramTime() {
		return programTime;
	}

	public void setProgramTime(Calendar programTime) {
		this.programTime = programTime;
	}

	public boolean getProgramAction() {
		return programAction;
	}

	public void setProgramAction(boolean programAction) {
		this.programAction = programAction;
	}
	
	

}
