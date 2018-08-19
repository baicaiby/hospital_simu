package tue.yellow;



/**
 * Chair is the class for all chairs that exist in the emergency department. 
 * Patients can be assigned to a chair object, for example to rest. Patients can be
 * in a chair while waiting, or while undergoing triage. A chair is not adequate for 
 * receiving treatment.
 * @author Team Yellow
 *
 */
public class Chair extends Resource{
	// Data members
	private String name;
	
	/**
	 * Creates a chair object, using one input value, namely the name of the chair
	 * @param name the name of the chair
	 */
	// Constructor
	public Chair (ListTracker lt){
		this (lt, "no name");
	}
	
	public Chair (ListTracker lt, String name){
		super(lt,name);
		lt.addChair(this);
	}

	// Methods

	/**
	 * Assigns a patient to the chair if the following conditions are met:
	 * <ul>
	 * <li> the patient is currently not assigned to a chair or a bed</li>
	 * <li> the chair is currently empty</li>
	 * <li>the patient is currently in one of the following states: <I>ENTERED_ER, UNDERGO_TRIAGE, WAITING_FOR_BED</I>
	 * 
	 * </ul>
	 * @param assignedPatient patient to be assigned to chair
	 */
	@Override
	public void setAssignedPatient(Patient assignedPatient) {
		Chair patientChair=assignedPatient.getCurrentChair();
		Bed patientBed=assignedPatient.getCurrentBed();
		Patient currentPatient=getAssignedPatient();
		
		boolean noResource = false;
		boolean chairFree = false;
		
		if (currentPatient == null){
			chairFree = true;
		}
		
		if (patientChair == null && patientBed == null){
			noResource = true;
		}
		
		if (chairFree && noResource) {
			this.assignedPatient=assignedPatient;
			assignedPatient.setCurrentChair(this);
						
		} else {
			this.assignedPatient=currentPatient;
		}
	}
	
		
	/**
	 * Removes the patient from the chair. Only patients that are actually assigned
	 * to a chair can be released from that specific chair. This method removes the 
	 * chair from the patient.
	 * @param releasedPatient patient to be released from the chair
	 */
	public void releasePatient(Patient releasedPatient){
		Patient patient;
		patient=getAssignedPatient();
		if (releasedPatient==patient){
			releasedPatient.setCurrentChair(null);
			this.assignedPatient=null;
		}  else {
			this.assignedPatient=patient;
		}
	}
	/**
	 * Displays the information of the chair object
	 * @return Chair information: Name, Assigned patient (name)
	 */
	@Override
	public String toString() {
		Patient patient=getAssignedPatient();
		if (patient==null){
			return "Chair information:"+"\n"+ 
					"Name             = " + name +"\n"+ 
					"Assigned patient = empty" + "\n";
		} else{
			return "Chair information:"+"\n"+ 
					"Name             = " + name +"\n"+ 
					"Assigned patient = " + assignedPatient.getName()+ "\n";
		}
	}
	
}
	


