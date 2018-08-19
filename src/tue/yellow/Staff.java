package tue.yellow;

import tue.yellow.Patient.HealthStatus;
import tue.yellow.Patient.Status;

/**
 * Staff is the class for all the staff that exists and operates in the emergency room.
 * This is a superclass to the Nurse and the Doctor. While the Nurse and Doctor both are similar 
 * in a way (e.g. they can be assigned to patients, they have certain availability, and they can
 * perform treatment), in terms of authorities they differ. Nurses can perform different treatments
 * than doctors, and sometimes their specialties need to be combined in a treatment. However, all
 * objects that are staff in the emergency room (in this case limited to Doctors and Nurses)
 * are able to perform the following functions:
 * <br>
 * <ul>
 * <li>perform clinical examination</li>
 * <li>perform external medical imaging</li>
 * <li>perform medication</li>
 * <li>perform other exam treatment</li>
 * <li>perform discharge</li>
 * <li>finish treatment</li>
 * <li>go home</li>
 * <li>start work</li>
 * </ul>
 * @author Team Yellow
 *
 */

public abstract class Staff {

	/** 
	 * The staff has the following data members:
	 * <ul>
	 * <li> name</li>
	 * <li> availability (enumeration "Availability": AVAILABLE/NOT_AVAILABLE)</li>
	 * <li> assigned patient </li>
	 * <li> current activity </li>
	 * <li> list tracker (emergency room to which they belong </li>
	 * </ul>
	 */
	protected String staffName;

	public static enum Availability {AVAILABLE,NOT_AVAILABLE}

	protected Availability staffAvailability;

	protected Patient assignedPatient;

	protected String currentActivity;

	protected ListTracker listTracker;
	/**
	 * When an object of staff is created, they have to be added to a list, which can be seen as 
	 * the emergency room to which they belong. It wouldn't make much sense to create an emergency 
	 * room staff without assigning them to an emergency room. Therefore, this is a necessary parameter
	 * in creating the staff object. 
	 * <br>
	 * If the emergency room is the only known parameter of the staff, his/her name will be set to
	 * <i>"no name"</i>, the availability will be </I>AVAILABLE</I>, they won't be assigned to a patient
	 * immediately upon creation, and their current activity will be <i>"I'm new here, please help me!"</i>
	 * @param lt the emergency department (list) to which the staff object belongs
	 */
	public Staff(ListTracker lt){
		this(lt, "no name");
	}
	/**
	 * Creates a staff object with a known emergency department and name. The availability will be </I>AVAILABLE</I>, 
	 * they won't be assigned to a patient
	 * immediately upon creation, and their current activity will be <i>"I'm new here, please help me!"</i>
	 * @param lt the emergency department (list) to which the staff belongs
	 * @param name the name of the staff object
	 */
	public Staff(ListTracker lt, String name) {
		this(lt, name, Availability.AVAILABLE);
	}
	/**
	 * Creates a staff object with a known emergency department, name, and availability (which could be 
	 * <i>UNAVAILABLE</I> upon creation. The staff object won't be assigned to a patient
	 * immediately upon creation, and their current activity will be <i>"I'm new here, please help me!"</i>
	 * @param lt the emergency department (list) to which the staff belongs
	 * @param name the name of the staff object
	 * @param availability the availability of the staff object upon creation
	 */
	public Staff (ListTracker lt, String name, Availability availability){
		this(lt, name, availability, null);
	}

	/**
	 * Creates a staff object with a known emergency department, name, availability and patient (a
	 * staff member could be assigned to a patient upon creation, however unlikely to happen). The current
	 * activity of this new staff member is <i>"I'm new here, please help me!"</i>
	 * @param lt the emergency department (list) to which the staff belongs
	 * @param name the name of the staff object
	 * @param availability the availability of the staff object upon creation
	 * @param patient the patient to which the staff is assigned
	 */
	public Staff(ListTracker lt, String name, Availability availability,Patient patient){
		this(lt, name, availability, patient,"I'm new here, please help me!"); // Easter egg
	}

	/**
	 * Creates a staff object with a known emergency department, name, availability, patient, and 
	 * current activity. 
	 * @param lt the emergency department (list) to which the staff belongs
	 * @param name the name of the staff object
	 * @param availability the availability of the staff object upon creation
	 * @param patient the patient to which the staff is assigned
	 * @param currentActivity the current activity of the staff member
	 */

	public Staff(ListTracker lt,String name, Availability availability, Patient patient, String currentActivity) {
		this.staffName = name;
		this.staffAvailability = availability;
		this.assignedPatient = patient;
		this.currentActivity = currentActivity;
		this.listTracker = lt;
	}
	/**
	 * Gets the name of the staff member.
	 * @return the staff member's name (as a String)
	 */
	public String getStaffName() {
		return staffName;
	}
	/**
	 * Gets the availability of the staff member
	 * @return the availability (AVAILABLE/NOT_AVAILABLE) of the staff member
	 */
	public Availability getStaffAvailability() {
		return staffAvailability;
	}
	/**
	 * Gets the patient to which the staff member is assigned and is currently attending.
	 * @return the patient of the staff member
	 */
	public Patient getAssignedPatient() {
		return assignedPatient;
	}
	/**
	 * Gets the current activity of the staff member
	 * @return the current activity of the staff member (as a String)
	 */
	public String getCurrentActivity() {
		return currentActivity;
	}
	/**
	 * Gets the list (emergency department) to which the staff member belongs/operates in
	 * @return the list / emergency department of the staff member
	 */
	public ListTracker getListTracker() {
		return listTracker;
	}
	/**
	 * Sets the name of the staff member
	 * @param staffName the name of the staff member
	 */
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	/**
	 * Sets the availability of the staff member to either of two options: AVAILABLE/NOT_AVAILABLE
	 * @param staffAvailability the availability of the staff
	 */
	public void setStaffAvailability(Availability staffAvailability) {
		this.staffAvailability = staffAvailability;
	}
	/**
	 * Sets the patient to which the staff member is assigned. This is a method that is executed differently,
	 * depending on which type of specialization of the class Staff the object is. That is why this is an abstract
	 * method in the class Staff.
	 * @param assignedPatient the patient to be assigned to the staff member
	 */
	public abstract void setAssignedPatient(Patient assignedPatient); 
	/**
	 * Sets the current activity of the staff member. If the current activity of the staff member
	 * is equal to <i>"at home"</i>, this will also immediately set the availability of that staff member
	 * to <I>NOT_AVAILABLE</I>, as the medical profession is generally not something that lends itself to 
	 * working at home.
	 * @param currentActivity the current activity of the staff member
	 */
	public void setCurrentActivity(String currentActivity) {
		if (currentActivity=="at home"){
			this.setStaffAvailability(Availability.NOT_AVAILABLE);
		}
		this.currentActivity = currentActivity;
	}
	/**
	 * Sets the current emergency room / list to which the staff member belongs
	 * @param listTracker the current emergency room / list of the staff member
	 */
	public void setListTracker(ListTracker listTracker) {
		this.listTracker = listTracker;
	}
	/**
	 * Whenever a staff member performs clinical examination on a patient, another staff member, of another type
	 * is necessary. Thus, if a doctor performs a clinical exam on a patient, this method will also find the first
	 * available nurse, in order to be able to start treatment. Another requirement before the treatment can start,
	 * is that the patient currently has no other doctor or nurse performing treatment on him/her, the patient is
	 * assigned to a bed (the only place to really receive treatment), and there are two staff members available
	 * for that patient (e.g. they are not currently performing treatment on another patient, and they are currently
	 * working (AVAILABLE). Only if all these conditions are met, it is possible to perform clinical examination.
	 * <br>
	 * This method assigns the patient to the staff member that calls this method, as well as the first other staff
	 * member that is found to be available. Also, it sets the status of both staff members performing this treatment
	 * to NOT_AVAILABLE, and sets their current activity to <i>"Performing Clinical Exam"</i>. The status of the patient
	 * is set to <i>IN_TREATMENT</i>
	 * @param assignedPatient the patient on which the treatment is to be performed
	 */
	public void performClinicalExam(Patient assignedPatient){
		String 			activity;
		ListTracker		lt = assignedPatient.getListTracker();
		Nurse 			nurse = assignedPatient.getCurrentNurse();
		Doctor 			doctor = assignedPatient.getCurrentDoctor();
		Staff			availableStaff;
		if (this instanceof Nurse){
			availableStaff = lt.getAvailableDoctor();
		} else {
			availableStaff = lt.getAvailableNurse();
		}
		Patient 		patient = getAssignedPatient();
		Bed 			bed = assignedPatient.getCurrentBed();
		boolean 		noStaff = false;
		boolean 		noPatient = false;
		boolean			patientOnBed = false;
		boolean 		staffAvailable = false;
		if (nurse == null && doctor == null){
			noStaff=true;
		}
		if (patient == null){
			noPatient = true;
		}
		if (bed!=null){
			patientOnBed = true;
		}

		if (availableStaff != null) {
			staffAvailable = true;
		}
		if (noStaff && noPatient && patientOnBed && staffAvailable && this.staffAvailability==Availability.AVAILABLE){
			setAssignedPatient(assignedPatient);
			availableStaff.setAssignedPatient(assignedPatient);
			assignedPatient.setCurrentStatus(Status.IN_TREATMENT);
			//			System.out.println(nurse.getStaffName() + "and " + doctor.getStaffName() + " are performing clinical exam on "+ assignedPatient.getName());
			setStaffAvailability(Availability.NOT_AVAILABLE);
			availableStaff.setStaffAvailability(Availability.NOT_AVAILABLE);
			activity = "Performing Clinical Exam";
			setCurrentActivity(activity);
			availableStaff.setCurrentActivity(activity);
			System.out.println(activity);
		} else {
			System.out.println("Not possible to perform Clinical Exam. Good luck.");
		}

	}
	/**
	 * Whenever a staff member performs external medical imaging on a patient, another staff member, of another type
	 * is necessary. Thus, if a doctor performs external medical imaging on a patient, this method will also find the first
	 * available nurse, in order to be able to start treatment. Another requirement before the treatment can start,
	 * is that the patient currently has no other doctor or nurse performing treatment on him/her, the patient is
	 * assigned to a bed (the only place to really receive treatment), and there are two staff members available
	 * for that patient (e.g. they are not currently performing treatment on another patient, and they are currently
	 * working (AVAILABLE). Only if all these conditions are met, it is possible to perform external medical imaging.
	 * <br>
	 * This method assigns the patient to the staff member that calls this method, as well as the first other staff
	 * member that is found to be available. Also, it sets the status of both staff members performing this treatment
	 * to NOT_AVAILABLE, and sets their current activity to <i>"Performing External Medical Imaging"</i>. The status of the patient
	 * is set to <i>IN_TREATMENT</I>
	 * @param assignedPatient the patient on which the treatment is to be performed
	 */
	public void performExternalMI(Patient assignedPatient){
		String 			activity;
		ListTracker		lt = assignedPatient.getListTracker();
		Nurse 			nurse = assignedPatient.getCurrentNurse();
		Doctor 			doctor = assignedPatient.getCurrentDoctor();
		Staff			availableStaff;
		if (this instanceof Nurse){
			availableStaff = lt.getAvailableDoctor();
		} else {
			availableStaff = lt.getAvailableNurse();
		}
		Patient 		patient = getAssignedPatient();
		Bed 			bed = assignedPatient.getCurrentBed();
		boolean 		noStaff = false;
		boolean 		noPatient = false;
		boolean			patientOnBed = false;
		boolean 		staffAvailable = false;
		if (nurse == null && doctor == null){
			noStaff=true;
		}
		if (patient == null){
			noPatient = true;
		}
		if (bed!=null){
			patientOnBed = true;
		}

		if (availableStaff != null) {
			staffAvailable = true;
		}
		if (noStaff && noPatient && patientOnBed && staffAvailable && this.staffAvailability==Availability.AVAILABLE){
			setAssignedPatient(assignedPatient);
			availableStaff.setAssignedPatient(assignedPatient);
			assignedPatient.setCurrentStatus(Status.IN_TREATMENT);
			setStaffAvailability(Availability.NOT_AVAILABLE);
			availableStaff.setStaffAvailability(Availability.NOT_AVAILABLE);
			activity = "Performing External Medical Imaging";
			setCurrentActivity(activity);
			availableStaff.setCurrentActivity(activity);
			System.out.println(activity);
		} else {
			System.out.println("Not possible to perform External Medical Imaging. Good luck.");
		}

	}
	/**
	 * Whenever a staff member performs medication on a patient, another staff member, of another type
	 * is necessary. Thus, if a doctor performs medication on a patient, this method will also find the first
	 * available nurse, in order to be able to start treatment. Another requirement before the treatment can start,
	 * is that the patient currently has no other doctor or nurse performing treatment on him/her, the patient is
	 * assigned to a bed (the only place to really receive treatment), and there are two staff members available
	 * for that patient (e.g. they are not currently performing treatment on another patient, and they are currently
	 * working (AVAILABLE). Only if all these conditions are met, it is possible to perform medication.
	 * <br>
	 * This method assigns the patient to the staff member that calls this method, as well as the first other staff
	 * member that is found to be available. Also, it sets the status of both staff members performing this treatment
	 * to NOT_AVAILABLE, and sets their current activity to <i>"Performing Medication"</i>. The status of the patient
	 * is set to <i>IN_TREATMENT</I>
	 * @param assignedPatient the patient on which the treatment is to be performed
	 */
	public void performMedication(Patient assignedPatient){
		String 			activity;
		ListTracker		lt = assignedPatient.getListTracker();
		Nurse 			nurse = assignedPatient.getCurrentNurse();
		Doctor 			doctor = assignedPatient.getCurrentDoctor();
		Staff			availableStaff;
		if (this instanceof Nurse){
			availableStaff = lt.getAvailableDoctor();
		} else {
			availableStaff = lt.getAvailableNurse();
		}
		Patient 		patient = getAssignedPatient();
		Bed 			bed = assignedPatient.getCurrentBed();
		boolean 		noStaff = false;
		boolean 		noPatient = false;
		boolean			patientOnBed = false;
		boolean 		staffAvailable = false;
		if (nurse == null && doctor == null){
			noStaff=true;
		}
		if (patient == null){
			noPatient = true;
		}
		if (bed!=null){
			patientOnBed = true;
		}

		if (availableStaff != null) {
			staffAvailable = true;
		}
		if (noStaff && noPatient && patientOnBed && staffAvailable && this.staffAvailability==Availability.AVAILABLE){
			setAssignedPatient(assignedPatient);
			availableStaff.setAssignedPatient(assignedPatient);
			assignedPatient.setCurrentStatus(Status.IN_TREATMENT);
			setStaffAvailability(Availability.NOT_AVAILABLE);
			availableStaff.setStaffAvailability(Availability.NOT_AVAILABLE);
			activity = "Performing Medication";
			setCurrentActivity(activity);
			availableStaff.setCurrentActivity(activity);
			System.out.println(activity);
		} else {
			System.out.println("Not possible to perform Medication. Good luck.");
		}

	}
	/**
	 * Whenever a staff member performs other exam treatment on a patient, another staff member, of another type
	 * is necessary. Thus, if a doctor performs other exam treatment on a patient, this method will also find the first
	 * available nurse, in order to be able to start treatment. Another requirement before the treatment can start,
	 * is that the patient currently has no other doctor or nurse performing treatment on him/her, the patient is
	 * assigned to a bed (the only place to really receive treatment), and there are two staff members available
	 * for that patient (e.g. they are not currently performing treatment on another patient, and they are currently
	 * working (AVAILABLE). Only if all these conditions are met, it is possible to perform other exam treatment.
	 * <br>
	 * This method assigns the patient to the staff member that calls this method, as well as the first other staff
	 * member that is found to be available. Also, it sets the status of both staff members performing this treatment
	 * to NOT_AVAILABLE, and sets their current activity to <i>"Performing Other Examination and Treatment"</i>. The status of the patient
	 * is set to <i>IN_TREATMENT</I>
	 * @param assignedPatient the patient on which the treatment is to be performed
	 */
	public void performOtherExamTreatment(Patient assignedPatient){
		String 			activity;
		ListTracker		lt = assignedPatient.getListTracker();
		Nurse 			nurse = assignedPatient.getCurrentNurse();
		Doctor 			doctor = assignedPatient.getCurrentDoctor();
		Staff			availableStaff;
		if (this instanceof Nurse){
			availableStaff = lt.getAvailableDoctor();
		} else {
			availableStaff = lt.getAvailableNurse();
		}
		Patient 		patient = getAssignedPatient();
		Bed 			bed = assignedPatient.getCurrentBed();
		boolean 		noStaff = false;
		boolean 		noPatient = false;
		boolean			patientOnBed = false;
		boolean 		staffAvailable = false;
		if (nurse == null && doctor == null){
			noStaff=true;
		}
		if (patient == null){
			noPatient = true;
		}
		if (bed!=null){
			patientOnBed = true;
		}

		if (availableStaff != null) {
			staffAvailable = true;
		}
		if (noStaff && noPatient && patientOnBed && staffAvailable && this.staffAvailability==Availability.AVAILABLE){
			setAssignedPatient(assignedPatient);
			availableStaff.setAssignedPatient(assignedPatient);
			assignedPatient.setCurrentStatus(Status.IN_TREATMENT);
			setStaffAvailability(Availability.NOT_AVAILABLE);
			availableStaff.setStaffAvailability(Availability.NOT_AVAILABLE);
			activity = "Performing Other Examination and Treatment";
			setCurrentActivity(activity);
			availableStaff.setCurrentActivity(activity);
			System.out.println(activity);
		} else {
			System.out.println("Not possible to perform Other Examination and Treatment. Good luck.");
		}

	}
	/**
	 * Whenever a staff member performs discharge on a patient, another staff member, of another type
	 * is necessary. Thus, if a doctor performs discharge on a patient, this method will also find the first
	 * available nurse, in order to be able to start treatment. Another requirement before the treatment can start,
	 * is that the patient currently has no other doctor or nurse performing treatment on him/her, the patient is
	 * assigned to a bed (the only place to really receive treatment), and there are two staff members available
	 * for that patient (e.g. they are not currently performing treatment on another patient, and they are currently
	 * working (AVAILABLE). Only if all these conditions are met, it is possible to perform discharge
	 * <br>
	 * This method assigns the patient to the staff member that calls this method, as well as the first other staff
	 * member that is found to be available. Also, it sets the status of both staff members performing this treatment
	 * to NOT_AVAILABLE, and sets their current activity to <i>"Performing Discharge"</i>. The status of the patient
	 * is set to <i>IN_DISCHARGE</I>
	 * @param assignedPatient the patient on which the treatment is to be performed
	 */
	public void performDischarge(Patient assignedPatient){
		String 			activity;
		ListTracker		lt = assignedPatient.getListTracker();
		Nurse 			nurse = assignedPatient.getCurrentNurse();
		Doctor 			doctor = assignedPatient.getCurrentDoctor();
		Staff			availableStaff;
		if (this instanceof Nurse){
			availableStaff = lt.getAvailableDoctor();
		} else {
			availableStaff = lt.getAvailableNurse();
		}
		Patient 		patient = getAssignedPatient();
		Bed 			bed = assignedPatient.getCurrentBed();
		boolean 		noStaff = false;
		boolean 		noPatient = false;
		boolean			patientOnBed = false;
		boolean 		staffAvailable = false;
		if (nurse == null && doctor == null){
			noStaff=true;
		}
		if (patient == null){
			noPatient = true;
		}
		if (bed!=null){
			patientOnBed = true;
		}

		if (availableStaff != null) {
			staffAvailable = true;
		}
		if (noStaff && noPatient && patientOnBed && staffAvailable && this.staffAvailability==Availability.AVAILABLE){
			setAssignedPatient(assignedPatient);
			availableStaff.setAssignedPatient(assignedPatient);
			assignedPatient.setCurrentStatus(Status.IN_DISCHARGE);
			setStaffAvailability(Availability.NOT_AVAILABLE);
			availableStaff.setStaffAvailability(Availability.NOT_AVAILABLE);
			activity = "Performing Discharge";
			setCurrentActivity(activity);
			availableStaff.setCurrentActivity(activity);
			System.out.println(activity);
		} else {
			System.out.println("Not possible to perform Discharge. Good luck.");
		}

	}
	/**
	 * Whenever a staff member finishes treatment on a patient, without specifying the 
	 * health status of that patient, the default value will be SICK.
	 * @param assignedPatient patient on which treatment was finished
	 */
	public void finishTreatment(Patient assignedPatient){
		finishTreatment(assignedPatient, HealthStatus.SICK);
	}
	/**
	 * When a staff member finished treatment on a patient, this method first checks if the staff member
	 * was indeed related to this patient (otherwise it is not possible to finish treatment on a patient 
	 * which was assigned to another staff member). Then, if treatment has finished, the nurse and doctor
	 * are released from the patient. If treatment is finished after discharge, the method finishDischarge() 
	 * will be called. If treatment is finished after boarding, the method leaveEmergencyDepartment() is 
	 * called.
	 * @param assignedPatient the patient for which treatment is finished.
	 * @param healthStatus the health status of the patient after that treatment.
	 */
	public void finishTreatment(Patient assignedPatient, HealthStatus healthStatus){
		Patient currentPatient;
		Doctor doctor=assignedPatient.getCurrentDoctor();
		Nurse nurse = assignedPatient.getCurrentNurse();
		currentPatient=getAssignedPatient();
		Status status=assignedPatient.getCurrentStatus();
		boolean patientMatch=false;
		if (currentPatient == assignedPatient){
			if (doctor == this || nurse == this){
				patientMatch = true;
			}
		}

		if(patientMatch){
			if (status==Status.IN_TREATMENT){
				assignedPatient.setCurrentStatus(Status.WAITING);
				assignedPatient.setHealthStatus(assignedPatient, healthStatus);
				System.out.println(nurse.getStaffName() + " : ok, treatment has been finished.");
				if (doctor != null && nurse != null) {
					doctor.releasePatient(assignedPatient);
					nurse.releasePatient(assignedPatient);
				} else if (doctor == null){
					nurse.releasePatient(assignedPatient);
				} else if (nurse == null) {
					doctor.releasePatient(assignedPatient);
				}

			} else if (status==Status.IN_DISCHARGE){
				finishDischarge(assignedPatient, healthStatus);
			} else if (status == Status.BOARDING){
				assignedPatient.leaveEmergencyRoom(assignedPatient);
			}
			else {
				System.out.println(nurse.getStaffName() + "Can't finish the treatment, the patient is not assigned to you.");
			}

		}
	}
	/**
	 * Whenever discharge is finished for a patient, it depends on the health status what action is performed
	 * next. If the patient is still sick at discharge, they wait for boarding, as they will move to another 
	 * unit in the hospital. If the patient is healthy, the method leaveEmergencyRoom() will be called.
	 * @param assignedPatient the patient to be discharged
	 * @param healthStatus the health status of the patient after discharge
	 */
	public void finishDischarge(Patient assignedPatient, HealthStatus healthStatus){
		Nurse nurse = assignedPatient.getCurrentNurse();
		Doctor doctor = assignedPatient.getCurrentDoctor();
		if (healthStatus == HealthStatus.SICK){
			assignedPatient.setHealthStatus(assignedPatient,healthStatus);
			assignedPatient.setCurrentStatus(Status.WAITING_FOR_BOARDING);
			doctor.releasePatient(assignedPatient);
			nurse.releasePatient(assignedPatient);
		} else if (healthStatus == HealthStatus.HEALTHY){
			assignedPatient.setHealthStatus(assignedPatient, healthStatus);
			assignedPatient.leaveEmergencyRoom(assignedPatient);
		}

	}
	/**
	 * Whenever a patient needs to be released from a staff member, it is checked that the staff member
	 * calling this method was indeed assigned to the patient. Also all other staff members that were 
	 * assigned to that patient will be releasing the patient. After releasing the patient, the availability
	 * of the staff will be set to AVAILABLE.
	 * @param assignedPatient
	 */
	public void releasePatient(Patient assignedPatient){
		Patient currentPatient = this.getAssignedPatient();
		Doctor doctor=assignedPatient.getCurrentDoctor();
		Nurse nurse = assignedPatient.getCurrentNurse();

		if (assignedPatient == currentPatient) {
			assignedPatient.setCurrentDoctor(null);
			assignedPatient.setCurrentNurse(null);
			if (doctor != null){
				doctor.assignedPatient=null;
				doctor.setStaffAvailability(Availability.AVAILABLE);
				setCurrentActivity("In the staff room");

			}
			if (nurse != null){
				nurse.assignedPatient=null;
				nurse.setStaffAvailability(Availability.AVAILABLE);
				setCurrentActivity("In the staff room");
			}

		} 

	}
	/**
	 * Whenever a staff member goes home, his staff availability will be set to NOT_AVAILABLE.
	 */
	public void goHome(){
		setStaffAvailability(Availability.NOT_AVAILABLE);
	}
	/**
	 * Whenever a staff member starts work, his staff availability will be set to AVAILABLE.
	 */
	public void startWork(){
		setStaffAvailability(Availability.AVAILABLE);
	}
	/**
	 * Produces the staff information (staff name, availability, current activity, assigned patient's name, and staff type (nurse/doctor).
	 */
	@Override
	public String toString() {
		Patient patient=getAssignedPatient();
		String patientName;
		String type;
		if (patient==null){
			patientName="No patient";
		} else{
			patientName=patient.getName();
		}
		if (this instanceof Nurse){
			type = "Nurse";
		} else {
			type = "Doctor";
		}
		return "Staff information:"+"\n"+ 
		"Name             = " + staffName +"\n"+ 
		"Availability     = " + staffAvailability + "\n"+
		"Current activity = " + currentActivity+ "\n" +
		"Assigned patient = " + patientName+"\n" + 
		"Staff type       = " + type;
	}
}

