
public class SmartCamera extends SmartObject implements MotionControl, Comparable<SmartCamera> {
	
	private boolean status;
	private int batteryLife;
	private boolean nightVision;
	
	public SmartCamera(String alias, String macId, boolean nightVision, int batteryLife) {
        super();
		super.setAlias(alias);
		super.setMacId(macId);
		this.nightVision = nightVision;
		this.batteryLife = batteryLife;
	}
	
	public void recordOn(boolean isDay) {
        /*
        opens the recording if there is an internet connection
        */
		if(super.getConnectionStatus()) {
			if(isDay) {  //checks whether morning or night
				System.out.println("Test is starting for SmartCamera day time.");
                if(status == false) { 
                    status = true;
                    System.out.println("Smart Camera - " + getAlias() + " is turned on now.");
                }
                else {
                    System.out.println("Smart Camera - " + getAlias() + " has been already turned on.");
                }
			}
			else{
                System.out.println("Test is starting for SmartCamera night time.");
                if(nightVision == false) {
                    System.out.println("Sorry! Smart Camera - " + getAlias() + " does not have night vision feature.");
                    status = false;
                    return;
                }else{
                    if(status == false) {
                        status = true;
                        System.out.println("Smart Camera - " + getAlias() + " is turned on now.");
                    }
                    else {
                        System.out.println("Smart Camera - " + getAlias() + " has been already turned on.");
                    }
                }
                
            }
		}
		else {
			System.out.println("There is no internet connection.");	
		}
	}
	
	public void recordOff() {
        /*
        Closes the recording if there is an internet connection
        */
		if(super.getConnectionStatus()) {
			if(status == true) {
				status = false;
				System.out.println("Smart Camera - " + getAlias() + " is turned off now.");
			}
			else {
				System.out.println("Smart Camera - " + getAlias() + " has been already turned off.");
			}
		}
		else {
			System.out.println("There is no internet connection.");	
		}
	}

    @Override
	public boolean testObject() {
        /*
        tests Cameras if there is an internet connection to check whether there is a problem
        */
		if(super.getConnectionStatus()) {
			System.out.println("Test is starting for SmartCamera");
			SmartObjectToString();
			recordOn(true);
			recordOff();
			recordOn(false);
			recordOff();
			System.out.println("Test completed for SmartCamera.");
			return true;
		}
		else {
			System.out.println("There is no internet connection.");	
			return false;
		}
	}

	@Override
	public boolean shutDownObject() { 
        /*
        //Turns off the camera if there is an internet connection.
        */
		if(super.getConnectionStatus()) {
			SmartObjectToString();
			if(status == true) {
				status = false;
			}
			return true;
		}
		else {
			System.out.println("There is no internet connection.");	;
			return false;
		}
	}

    @Override
	public void controlMotion(boolean hasMotion, boolean isDay) {
        /*
        checks the motion and starts recording according to hasMotion
        */
		if(hasMotion == true) {
			System.out.println("Motion detected!");
		}
		else {
			System.out.println("Motion not detected");
		}
		
		if(isDay == true) {
			status = true;
			System.out.println("Smart Camera - " + getAlias() + " is turned on now.");
		}
		else {
			if(nightVision == true) {
				status = true;
				System.out.println("Smart Camera - " + getAlias() + " is turned on now.");
			}
			else{
				status = false;
			}
		}
		
	}
	@Override
	public int compareTo(SmartCamera o) {
        /*
        compares battery lifes
        */
		if(this.batteryLife > o.batteryLife) {
			return 1;
		}
		else if(this.batteryLife < o.batteryLife) {
			return -1;
		}
		else
			return 0;
	}

	

	

	@Override
	public String toString() {
		if(status == true) {
			return "Smart Camera -> " + getAlias() + "'s battery life is " + batteryLife + " status is recording.";
		}
		else {
			return "Smart Camera -> " + getAlias() + "'s battery life is " + batteryLife + " status is not recording.";
		}
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getBatteryLife() {
		return batteryLife;
	}

	public void setBatteryLife(int batteryLife) {
		this.batteryLife = batteryLife;
	}

	public boolean getNightVision() {
		return nightVision;
	}

	public void setNightVision(boolean nightVision) {
		this.nightVision = nightVision;
	}

	
	

	

}
