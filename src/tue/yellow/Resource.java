package tue.yellow;
/**
 * Resource is the class for all the resources that exists in the emergency room.
 * This is a superclass to the Bed and the Chair. Chairs are used for triage, while beds are used
 * to host patients during treatment. A resource has a name, an assigned patient, and a listtracker (emergency room)
 * to which it belongs.
 * @author Team Yellow
 *
 */
import tue.yellow.Patient.Status;

public abstract class Resource {
	protected String name;
	public Patient assignedPatient;
	protected ListTracker lt;

	// Constructor
	/**
	 * When a resource is created, it has to be added to a listTracker (emergency room). It wouldn't
	 * make much sense to create a resource and not add it to an emergency room. If the emergency room is the 
	 * only known parameter of the resource, the default name will be "no name". Upon creation, the resource
	 * is not assigned to a patient yet.
	 * @param lt listtracker (emergency room) to which the resource belongs.
	 */
	public Resource (ListTracker lt) {
		this (lt, "no name");
	}
	/**
	 * When a resource is created, it has to be added to a listTracker (emergency room). It wouldn't
	 * make much sense to create a resource and not add it to an emergency room. Also the name of the 
	 * resource will be set. Upon creation, the resource
	 * is not assigned to a patient yet. 
	 * @param lt
	 * @param name
	 */
	public Resource (ListTracker lt, String name){
		setListTracker(lt);
		setName(name);
		this.assignedPatient=null;
	}
	/**
	 * Gets the name of the resource
	 * @return the name of the resource
	 */
	public String getName() {
		return name;
	}
	/**
	 * Gets the assigned patient of the resource
	 * @return the assigned patient of the resource
	 */
	public Patient getAssignedPatient() {
		return assignedPatient;
	}
	/**
	 * Gets the list tracker (emergency room) to which the resource belongs
	 * @return the list tracker (emergency room) of the resource
	 */
	public ListTracker getLt() {
		return lt;
	}
	/**
	 * Sets the name of the resource
	 * @param name name of the resource
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the list tracker (emergency room) of the resource
	 * @param lt list tracker (emergency room) of the resource
	 */
	public void setListTracker(ListTracker lt) {
		this.lt = lt;
	}
	/**
	 * Sets the patient to which the resource is assigned. This is an abstract method, as it is implemented
	 * differently by the chair and the bed
	 * @param assignedPatient patient to be assigend to a resource
	 */
	public abstract void setAssignedPatient(Patient assignedPatient);
	/**
	 * Releases the patient from the resource. If a patient is released from a bed, his status will
	 * be set to "LEFT_ER".
	 * @param releasedPatient patient to be released from resource
	 */
	public void releasePatient(Patient releasedPatient){
		Patient patient;
		patient=getAssignedPatient();
		if (releasedPatient==patient&&this instanceof Chair){
			releasedPatient.setCurrentChair(null);
			this.assignedPatient=null;
		} else if (releasedPatient==patient && this instanceof Bed){

			releasedPatient.setCurrentBed(null);
			releasedPatient.setCurrentStatus(Status.LEFT_ER);
			this.assignedPatient=null;
		} else {
			this.assignedPatient=patient;
		}
	}
	/**
	 * Gives resource information: resource type (chair/bed), resource name, and name of assigned patient.
	 */
	@Override
	public String toString() {
		Patient patient=getAssignedPatient();
		String type;
		if (this instanceof Chair){
			type = "Chair";

		} else {
			type = "Bed";
		}
		if (patient==null){
			return type +" information:"+"\n"+ 
					"Name             = " + name +"\n"+ 
					"Assigned patient = empty" + "\n";
		} else{
			return type +" information:"+"\n"+ 
					"Name             = " + name +"\n"+ 
					"Assigned patient = " + assignedPatient.getName()+ "\n";
		}
	}

}



