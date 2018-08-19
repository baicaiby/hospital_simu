package tue.yellow;

import tue.yellow.Patient.Status;

/**
 * Bed is the class for all beds that exist in the emergency department. 
 * Patients can be assigned to a bed object. When a patient is in a bed, 
 * he/she can receive treatment.
 * @author Team Yellow
 * 
 * 
 *
 */
public class Bed extends Resource {
	// Data members
	public static enum Zone{TRAUMA,NO_INJURY,UNKNOWN}
	public static enum Urgency{URGENT,NOT_URGENT,UNKNOWN}
	private Zone zone;
	private Urgency urgency;
	/**
	 * Creates a bed object, using three input values: <br>
	 * 1) name of the bed <br>
	 * 2) zone of the bed<br>
	 * 3) urgency level of the bed <br>
	 * <p>
	 * These input values are set to the data members of the object.
	 * @param name          the name of the bed
	 * @param zone          the zone of the bed (<I>TRAUMA/NO_INJURY</I>)
	 * @param urgencyLevel	the urgency level of the bed (<I>URGENT/NOT_URGENT</I>)
	 */
	// Constructor

	public Bed (ListTracker lt){
		this(lt,"no name");
	}

	public Bed(ListTracker lt, String name) {
		this(lt,name,Zone.UNKNOWN,Urgency.UNKNOWN);

	}

	public Bed(ListTracker lt, String name, Zone zone, Urgency urgency) {
		super(lt, name);
		setZone(zone);
		setUrgencyLevel(urgency);
		lt.addBed(this);
	}



	// Methods

	/**
	 * Gets the zone of the bed object. Value is either <I>TRAUMA</I> or <I>NO_INJURY</I>
	 * @return zone of the bed object
	 */

	public Zone getZone() {
		return zone;
	}

	/**
	 * Sets the zone of the bed object. Value is either <I>TRAUMA</I> or <I>NO_INJURY</I>.
	 * @param zone zone of the bed
	 */

	public void setZone(Zone zone) {
		this.zone=zone;
	}

	/**
	 * Gets the urgency level of the bed object. Value is either <I>URGENT</I> or <I>NOT_URGENT</I>
	 * @return urgency level of the bed
	 */

	public Urgency getUrgencyLevel() {
		return urgency;
	}

	/**
	 * Sets the urgency level of the bed object. Value is either <I>URGENT</I> or <I>NOT_URGENT</I>
	 * @param urgencyLevel urgency level of the bed
	 */

	public void setUrgencyLevel(Urgency urgencyLevel) {
		this.urgency = urgencyLevel;
	}



	/**
	 * Assigns a patient to the bed if the following conditions are met:<br>
	 * <ul>
	 * <li>The patient's zone is the same as the bed's zone</li>
	 * <li>The patient's urgency level is the same as the bed's urgency level</li>
	 * <li>The bed is currently empty</li>
	 * <li>The patient is currently <i>WAITING_FOR_BED</i></li>
	 * </ul>
	 * <p>
	 * Furthermore, when a patient is in a chair at the moment of assigning him/her
	 * to the bed, the patient will be released from the chair and then assigned to the bed.
	 * 
	 * @param assignedPatient the patient to be assigned to the bed
	 */
	@Override
	public void setAssignedPatient(Patient assignedPatient) {
		Status patientStatus;
		Zone bedZone, patientZone;
		Urgency bedUrgency,patientUrgency;
		Patient currentPatient = getAssignedPatient();
		Chair patientChair;
		patientZone=assignedPatient.getTriageZone();
		bedZone=getZone();
		patientUrgency=assignedPatient.getBedUrgency();
		bedUrgency=getUrgencyLevel();
		patientStatus=assignedPatient.getCurrentStatus();
		patientChair=assignedPatient.getCurrentChair();
		boolean bedFree = false;
		boolean typeMatch = false;
		if (currentPatient==null){
			bedFree = true;
		}

		if (bedZone == patientZone && 
				bedUrgency == patientUrgency && 
				patientStatus == Status.WAITING_FOR_BED){
			typeMatch = true;
		}

		if (bedFree && typeMatch && patientChair == null) {
			this.assignedPatient = assignedPatient;
			assignedPatient.setCurrentBed(this);
			assignedPatient.setCurrentStatus(Status.WAITING);
		} else if (bedFree && typeMatch && patientChair != null){
			patientChair.releasePatient(assignedPatient);
			this.assignedPatient = assignedPatient;
			assignedPatient.setCurrentBed(this);
			assignedPatient.setCurrentStatus(Status.WAITING);
		} else {
			this.assignedPatient=currentPatient;
		}
	}


	/**
	 * Displays the information of a bed object
	 * @return Bed information: Name, Zone, Urgency Level, Assigned Patient (name)
	 */
	@Override
	public String toString() {
		Patient patient=getAssignedPatient();
		if (patient==null){
			return "Bed information:"+"\n"+ 
					"Name             = " + name +"\n"+ 
					"Zone             = " + zone + "\n"+ 
					"Urgency level    = " + urgency + "\n"+
					"Assigned patient = empty" + "\n";
		} else{
			return "Bed information:"+"\n"+ 
					"Name             = " + name +"\n"+ 
					"Zone             = " + zone + "\n"+ 
					"Urgency level    = " + urgency + "\n"+
					"Assigned patient = " + assignedPatient.getName()+ "\n";
		}
	}



}
