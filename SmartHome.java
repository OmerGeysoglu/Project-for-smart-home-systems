import java.util.ArrayList;
import java.util.Arrays;

public class SmartHome {
	
	private ArrayList<SmartObject> smartObjects = new ArrayList<>();
	
	public SmartHome() {
		
	}
	
	public boolean addSmartObject(SmartObject smartObject) {
        /*
        adds smartObject to the smartObjects
        */
		if(smartObjects.contains(smartObject) == false) {
			System.out.println("--------------------------------------------------------------------------");
			System.out.println("--------------------------------------------------------------------------");
			System.out.println("Adding New SmartObject");
			System.out.println("--------------------------------------------------------------------------");
			smartObject.connect(smartObject.getIP());
			smartObjects.add(smartObject);
			int index = smartObjects.indexOf(smartObject);
            //creates IP by adding 100 to the indexes.
			String IP = "10.0.0." +(100+ index);                   
			smartObject.setIP(IP);
			smartObject.testObject();
			
			smartObjects.get(index).setIP(IP);
			return true;
		}
		else {
            System.out.println(smartObject.getAlias() + " has been already added"); 
			return false;
		}
	}
	
	public boolean removeSmartObject(SmartObject smartObject) {
        /*
        removes object from objects.
        */      
		if(smartObjects.contains(smartObject)) {
			smartObjects.remove(smartObject);
			return true;
		}
		else {
			System.out.println(smartObject.getAlias() + " is not in smartObjects.");
			return false;
		}
	}
	
	public void controlLocation(boolean onCome) { 
        /*
        iterates smartObjects and invokes onCome/onLeave methods for objects implementing LocationControl interface 
        */
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("LocationControl : OnCome");
		System.out.println("--------------------------------------------------------------------------");
		
		for(int i = 0 ; i < smartObjects.size(); i++) {
			if(smartObjects.get(i) instanceof SmartLight) {
				if(onCome == true) {
					((SmartLight) smartObjects.get(i)).onCome();
				}
				else {
					((SmartLight)smartObjects.get(i)).onLeave();
				}
			}
		}
	}
	
	
	public void controlMotion(boolean hasMotion, boolean isDay) {   
        /*
        iterates smartObjects and invokes controlMotion method for objects implementing MotionControl interface 
        */
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("MotionControl : HasMotion , isDay");
		System.out.println("--------------------------------------------------------------------------");
		
		for(int i = 0; i < smartObjects.size(); i++) {
			if(smartObjects.get(i) instanceof SmartCamera) {
				((SmartCamera)(smartObjects.get(i))).controlMotion(hasMotion, isDay);
			}
		}
	}
	
	public void controlProgrammable() {
        /*
        iterates smartObjects and invokes runProgram method for objects implementing Programmable interface 
        */
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Programmable : runProgram");
		System.out.println("--------------------------------------------------------------------------");
		for(int i = 0; i < smartObjects.size(); i++) {
			if(smartObjects.get(i) instanceof SmartLight) {
				((SmartLight)(smartObjects.get(i))).runProgram();
			}
			if(smartObjects.get(i) instanceof SmartPlug) {
				((SmartPlug)(smartObjects.get(i))).runProgram();
			}
		}
		
	}
	
	public void controlTimer(int seconds) {
        /*
        iterates smartObjects and invokes setTimer and cancelTimer methods for objects implementing Programmable interface 
        */
		for(int i = 0; i < smartObjects.size(); i++) {
			if(smartObjects.get(i) instanceof SmartLight) {
				if(seconds > 0) {
				((SmartLight)(smartObjects.get(i))).setTimer(seconds);
				}
				else if(seconds == 0){
					((SmartLight)(smartObjects.get(i))).cancelTimer();
				}
			}
			if(smartObjects.get(i) instanceof SmartPlug) {
				if(seconds > 0) {
				((SmartPlug)(smartObjects.get(i))).setTimer(seconds);
				}
				else if(seconds == 0) {
					((SmartPlug)(smartObjects.get(i))).cancelTimer();
				}
			}
		}
		
	}
	
	public void controlTimerRandomly() {    
        /*
        iterates smartObjects and invokes setTimer and cancelTimer methods for objects implementing Programmable interface
        using Math.random() 
        */
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Programmable : 0, 5 or 10 seconds randomly");
		System.out.println("--------------------------------------------------------------------------");
		for(int i = 0; i < smartObjects.size(); i++) {
			if(smartObjects.get(i) instanceof SmartLight) {
                // get random number from 0 to 2
                // 0 for 0
                // 1 for 5
                // 2 for 10
				int number = ((int)Math.random())+2; 
				if(number == 0) {
					System.out.println(smartObjects.get(i).getAlias() + " 's cancelTimer method is invoked");
					((SmartLight)(smartObjects.get(i))).cancelTimer();
				}
				else if(number==1) {
					number = 5;
					((SmartLight)(smartObjects.get(i))).setTimer(number);
				}
				else {
					number = 10;
				    ((SmartLight)(smartObjects.get(i))).setTimer(number);
				}
			}
			if(smartObjects.get(i) instanceof SmartPlug) {
                // get random number from 0 to 2
                // 0 for 0
                // 1 for 5
                // 2 for 10
				int number = ((int)Math.random())+2;
				if(number == 0) {
					System.out.println(smartObjects.get(i).getAlias() + "'s cancelTimer method is invoked");
					((SmartPlug)(smartObjects.get(i))).cancelTimer();
				}
				else if(number==1) {
					number = 5;
					((SmartPlug)(smartObjects.get(i))).setTimer(number);
				}
				else {
					number = 5;
				    ((SmartPlug)(smartObjects.get(i))).setTimer(number);
				}
			}
		}
	}
	
	public void sortCameras() {  
        /*
        iterates smartObjects for objects implementing Comparable interface
        then it invokes Arrays.sort method to sort smart cameras based on the battery life
        */
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Sort Smart Cameras");
		System.out.println("--------------------------------------------------------------------------");
        ArrayList<SmartCamera> smartCameras = new ArrayList<SmartCamera>();
        for(int i = 0 ; i < smartObjects.size(); i++) {
			if(smartObjects.get(i) instanceof SmartCamera) {
				smartCameras.add((SmartCamera)smartObjects.get(i));
			}
		}
        SmartCamera[] smartCamerasArray = new SmartCamera[smartCameras.size()];
        for (int i = 0; i < smartCameras.size(); i++) {
            smartCamerasArray[i] = smartCameras.get(i);
        }

        Arrays.sort(smartCamerasArray);
		
		for(int i = 0 ; i < smartCamerasArray.length; i++) {
			System.out.println(smartCamerasArray[i]);
		}
		
	}

	public ArrayList<SmartObject> getSmartObjectList() {
		return smartObjects;
	}

	public void setSmartObjectList(ArrayList<SmartObject> smartObjectList) {
		this.smartObjects = smartObjectList;
	}
	
	

}
