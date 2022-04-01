import java.util.Calendar;

public class SmartPlug extends SmartObject implements Programmable {

	private boolean status;
	private Calendar programTime;
	private boolean programAction = false;
	
	public SmartPlug(String alias, String macId) {
		super();
		super.setAlias(alias);
		super.setMacId(macId);
	}
	
	public void turnOn() {
        // turns on the plug
		if(super.getConnectionStatus()) {
			if(status == false) {
				status = true;
				System.out.println("Smart Plug - " + getAlias() + " is turned on now (Current Time : " 
                    + Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" 
					+ Calendar.getInstance().get(Calendar.MINUTE) + ":" 
					+ Calendar.getInstance().get(Calendar.SECOND) +")");
			}
			else {
				System.out.println("Smart Plug - " + getAlias() + " has been already turned on.");
			}
		}
		else {
			System.out.println("There is no internet connection.");	
		}
	}
	
	public void turnOff() {     
        // turns off the plug.
		if(super.getConnectionStatus()) {
			if(status == true) {
				status = false;
				System.out.println("Smart Plug - " + getAlias() + " is turned off now (Current Time : " 
                    + Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" 
					+ Calendar.getInstance().get(Calendar.MINUTE) + ":" 
					+ Calendar.getInstance().get(Calendar.SECOND) +")");
			}
			else {
				System.out.println("Smart Plug - " + getAlias() + " has been already turned off.");
			}
		}
		else {
			System.out.println("There is no internet connection.");	
		}
	}
	
    @Override
	public boolean testObject() {  
        //tests the plugs if there is an internet connection to check whether there is a problem
		if(super.getConnectionStatus()) {
			System.out.println("Test is starting for SmartPlug");
			SmartObjectToString();
			turnOn();
			turnOff();
			System.out.println("Test Completed for smartPlug\n");
			return true;
		}
		else {
			System.out.println("There is no internet connection.");	
			return false;
		}
		
	}

	@Override
	public boolean shutDownObject() {  
        //turns off the plug if there is an internet connection.
		if(super.getConnectionStatus()) {
			SmartObjectToString();
			if(status == true) {
				status = false;
				return true;
			}
			else {
                return false;
            }
		}
		else {
			System.out.println("There is no internet connection.");	
			return false;
		}
	}
	
	@Override
	public void setTimer(int seconds) {   
        //sets timer to turn on/off the plug.
		if(super.getConnectionStatus()) {
			programTime = Calendar.getInstance();
			programTime.add(Calendar.SECOND, seconds);
			programAction = true;
			if(status == true) {
				System.out.println("Smart Plug - " + getAlias() + " will be turned off " +seconds + " seconds later! (Current Time : " 
                    + programTime.get(Calendar.HOUR_OF_DAY) + ":" + 
					+ Calendar.getInstance().get(Calendar.MINUTE) + ":" +
					+ Calendar.getInstance().get(Calendar.SECOND) + ")" );
				
			}
			else {
				System.out.println("Smart Plug - " + getAlias() + " will be turned on " + seconds + " seconds later! (Current Time : " 
                    + programTime.get(Calendar.HOUR_OF_DAY) + ":" + 
					+ Calendar.getInstance().get(Calendar.MINUTE) + ":" +
					+ Calendar.getInstance().get(Calendar.SECOND) + ")");
			}
		}
		else {
			System.out.println("There is no internet connection.");	
		}
		
	}

	@Override
	public void cancelTimer() {  
        // cancel the timer. 
		if(super.getConnectionStatus()) {
			programTime= null;
			programAction = false;
		}
		else {
			System.out.println("There is no internet connection.");	
		}
		
	}

	@Override
	public void runProgram() {       
        //according to the programAction and programTime, the plugs will be turned on/off.       
		if(super.getConnectionStatus()) {
			if(programAction == true) {
				if(programTime != null){
					if(programTime.get(Calendar.HOUR_OF_DAY) == Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
					&& programTime.get(Calendar.MINUTE) == Calendar.getInstance().get(Calendar.MINUTE)
					&& programTime.get(Calendar.SECOND) == Calendar.getInstance().get(Calendar.SECOND)) {
						System.out.println("RunProgram -> SmartPlug - "+ getAlias());
						if(status == true) {
							turnOff();
							programTime = null;
						}
						else {
							turnOn();
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

	

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Calendar getProgramTime() {
		return programTime;
	}

	public void setProgramTime(Calendar programTime) {
		this.programTime = programTime;
	}

	public boolean isProgramAction() {
		return programAction;
	}

	public void setProgramAction(boolean programAction) {
		this.programAction = programAction;
	}
	
	

}
