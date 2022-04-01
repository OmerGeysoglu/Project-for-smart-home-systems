
public abstract class SmartObject {
	
	private String alias;
	private String macId;
	private String IP;
	private boolean connectionStatus;
	
	public SmartObject() {
		
	}
	
	public boolean connect(String IP) {
        // checks if the smartObject is connected to the internet
        // if there is no internet connection connects it
		if(connectionStatus == false) {
			this.IP = IP;
			connectionStatus = true;
			System.out.println(alias + " connection established.");     
			return true;
		}
		else {
			System.out.println(alias + " connection has been already established.");
			return false;
		}
			
	}
	
	public boolean disconnect() {
        // checks if the smartObject is connected to the internet
        // if there is internet connection disconnects it
		if(connectionStatus) {
			IP = null;
			connectionStatus = false;  
			return true;
		}
		else {
			return false;
		}
	}
	
	public void SmartObjectToString() {
        // print the smart object
		System.out.println("This is " + getClass().getSimpleName() +" device " + alias); 
		System.out.println("\tMacId: " + macId);
		System.out.println("\tIP: " + IP);
	}
	
	public boolean controlConnection() {
        //checks the internet connection
		if(connectionStatus == false) {
			System.out.println("This device is not connected. " + getClass().getSimpleName() + " -> " + alias);
			return true;
		}
		else
			return false;
	}
	
	public abstract boolean testObject();
	public abstract boolean shutDownObject();
	
	

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getMacId() {
		return macId;
	}

	public void setMacId(String macId) {
		this.macId = macId;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public boolean getConnectionStatus() {
		return connectionStatus;
	}

	public void setConnectionStatus(boolean connectionStatus) {
		this.connectionStatus = connectionStatus;
	}	
	
	

}
