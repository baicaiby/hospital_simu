package tue.yellow;

import tue.yellow.Bed.Zone;
import tue.yellow.Bed.Urgency;

/**
 * Patient is the class for all patients that enter and have entered the emergency department.
 * Patients interact with many different other objects (classes), for example doctors, nurses, 
 * beds, chairs, etc.
 * 
 * @author Team Yellow
 *
 */
public class Patient {
	// Data members
	public static enum TriageUrgency{IMMEDIATE,VERY_URGENT,URGENT,STANDARD,NON_URGENT}
	private String name;
	public static enum HealthStatus{SICK,HEALTHY,DEAD}
	private HealthStatus healthStatus;
	public static enum HealthProblem{BROKEN_ARM,CONCUSSION,APPENDIX,UNKNOWN}
	// Broken arm --> Trauma, standard
	// Concussion --> Trauma, immediate
	// Appendix --> NoInjury, immediate
	private HealthProblem healthProblem;
	private String complaints;
	private TriageUrgency triageUrgency;
	private tue.yellow.Bed.Urgency bedUrgency;
	private tue.yellow.Bed.Zone triageZone;
	private Bed currentBed;
	private Chair currentChair;
	public static enum Status{ENTERED_ER,UNDERGO_TRIAGE,WAITING_FOR_BED,WAITING,IN_TREATMENT,
		WAITING_FOR_BOARDING,BOARDING,IN_DISCHARGE,LEFT_ER,GONE, IN_CHECK};
		private Status currentStatus;
		private Doctor currentDoctor;
		private Nurse currentNurse;
		private static ListTracker lt;
		private int enterTime;
		private int waitingTriageTime;
		private int triageTime;
		private int waitingBedTime;
		private int waitingTreatmentTime;
		private int treatmentTime;
		private int totalWaitingTime;
		private int totalProcessTime;
		private int leaveTime;

		private boolean ambulance;


		// Constructor

		/**
		 * Creates a patient with only one known parameter. Will set the entering time to 0.
		 * @param lt list tracker (emergency room) of the patient
		 */
		public Patient (ListTracker lt){
			this (lt,0);
		}
		/**
		 * Creates a patient with only two known parameters. Will set the healthproblem to UNKNOWN, and the 
		 * entering mode (ambulance) to false.
		 * @param lt list tracker (emergency room) of the patient
		 * @param enterTime time of entering of the patient
		 */
		public Patient(ListTracker lt, int enterTime){
			this(lt,enterTime, false, HealthProblem.UNKNOWN);
		}
		/**
		 * Creates a patient with only two known parameters. Will set the entering time to 0,
		 * the entering mode to walk in, and the health problem to UNKNOWN.
		 * @param lt list tracker (emergency room) of the patient
		 * @param name name of the patient
		 */
		public Patient (ListTracker lt, String name){
			this(lt, 0, false, HealthProblem.UNKNOWN,name);
		}
		/**
		 * Creates a patient with only two known parameters, namely the list tracker (emergency room)
		 *  and the health  problem. Will set the entering time to 0, entering mode to ambulance, and name 
		 *  to "unknown".
		 * @param lt list tracker (emergency room) of the patient
		 * @param healthProblem health problem of the patient (APPENDIX, CONCUSSION, BROKEN_ARM)
		 */
		public Patient (ListTracker lt, HealthProblem healthProblem){
			this(lt, 0, true, healthProblem,"unknown");
		}
		/**
		 * Creates a patient with only three known parameters, namely the list tracker (emergency room),
		 * name, and health problem. Will set the entering time to 0 and entering mode to ambulance.
		 * @param lt list tracker (emergency room) of the patient
		 * @param name name of the patient
		 * @param healthProblem health problem of the patient (APPENDIX, CONCUSSION, BROKEN_ARM)
		 */
		public Patient (ListTracker lt, String name, HealthProblem healthProblem){
			this(lt, 0, true, healthProblem,name);
		}
		/**
		 * Creates a patient with three known parameters: list tracker, entering time and name of the patient.
		 * Sets the entering mode to walk in and health problem to UNKONWN.
		 * @param lt list tracker (emergency room) of the patient
		 * @param enterTime time of entering of the patient
		 * @param name name of the patient
		 */
		public Patient (ListTracker lt, int enterTime, String name){
			this(lt,enterTime, false, HealthProblem.UNKNOWN,name);
		}
		/**
		 * Creates a patient with four known parameters: list tracker, entering time, name and complaints.
		 * Sets the entering mode to walk in and health problem to UNKNOWN.
		 * @param lt list tracker (emergency room) of the patient
		 * @param enterTime time of entering of the patient
		 * @param name name of the patient
		 * @param complaints complaints of the patient
		 */
		public Patient (ListTracker lt, int enterTime, String name, String complaints){
			this(lt,enterTime, false, HealthProblem.UNKNOWN,name,complaints);
		}
		/**
		 * 
		 * @param lt list tracker (emergency room) of the patient
		 * @param name name of the patient
		 * @param complaints complaints of the patient
		 */
		public Patient (ListTracker lt,  String name, String complaints){
			this(lt,0, false, HealthProblem.UNKNOWN,name,complaints);
		}
		/**
		 * 
		 * @param lt list tracker (emergency room) of the patient
		 * @param enterTime time of entering of the patient
		 * @param ambulance boolean, true = ambulance, false = walk in
		 * @param healthProblem health problem of the patient (APPENDIX, CONCUSSION, BROKEN_ARM)
		 */
		public Patient(ListTracker lt, int enterTime,Boolean ambulance, HealthProblem healthProblem){
			this(lt,enterTime,ambulance,healthProblem, "unknown");
		}
		/** 
		 * Creates a patient with only three known parameters. As the enter time 0 is filled in. Also, 
		 * "unknown" is filled in for complaints)
		 * @param lt list tracker (emergency room) of the patient
		 * @param ambulance boolean, true = ambulance, false = walk in
		 * @param healthProblem health problem of the patient (APPENDIX, CONCUSSION, BROKEN_ARM)
		 */
		public Patient(ListTracker lt, Boolean ambulance, HealthProblem healthProblem){
			this(lt,0,ambulance,healthProblem, "unknown");
		}
		/**
		 * Creates a patient with only five known parameters. as the complaints, "unknown" is filled in.
		 * @param lt list tracker (emergency room) of the patient
		 * @param enterTime time of entering of the patient
		 * @param ambulance boolean, true = ambulance, false = walk in
		 * @param healthProblem health problem of the patient (APPENDIX, CONCUSSION, BROKEN_ARM)
		 * @param name name of the patients
		 */
		public Patient(ListTracker lt, int enterTime,Boolean ambulance, HealthProblem healthProblem, String name){
			this(lt,enterTime,ambulance,healthProblem, name,"unknown");
		}
		/**
		 * Creates a patient with only four known parameters. As the entering time, 0 is filled in, and 
		 * as the complaints, it is set to "unknown".
		 * @param lt list tracker (emergency room) of the patient
		 * @param ambulance boolean, true = ambulance, false = walk in
		 * @param healthProblem health problem of the patient (APPENDIX, CONCUSSION, BROKEN_ARM)
		 * @param name name of the patients
		 */
		public Patient(ListTracker lt,Boolean ambulance, HealthProblem healthProblem, String name){
			this(lt,0,ambulance,healthProblem, name,"unknown");
		}
		/**
		 * Creates a patient, with only five known parameters. As the entering time, 0 is filled in.
		 * @param lt list tracker (emergency room) of the patient
		 * @param ambulance boolean, true = ambulance, false = walk in
		 * @param healthProblem health problem of the patient (APPENDIX, CONCUSSION, BROKEN_ARM)
		 * @param name name of the patients
		 * @param complaints complaints of the patient
		 */
		public Patient(ListTracker lt,Boolean ambulance, HealthProblem healthProblem, String name, String complaints){
			this(lt,0,ambulance,healthProblem, name,complaints);
		}

		/**
		 * Creates a patient object using 6 input values: <br>
		 * 1) List tracker (emergency room) of the patient <br>
		 * 2) Entering time of the patient <br>
		 * 3) Entering mode of the patient <br>
		 * 4) Health problem of the patient <br>
		 * 5) Name of the patient <br>
		 * 6) Complaints of the patient <br>
		 * The constructor adds the patient to the list tracker upon creation.
		 * <p>
		 * The current status of the patient after entering is ENTERED_ER, and if the patient entered
		 * by ambulance (meaning that triage has already been set) the status will be WAITING_FOR_BED.
		 * The current health status of the patient after creation is SICK. The patient will not yet be 
		 * assigned to any staff or resource.
		 * @param lt list tracker (emergency room) of the patient
		 * @param enterTime time of entering of the patient
		 * @param ambulance boolean, true = ambulance, false = walk in
		 * @param healthProblem health problem of the patient (APPENDIX, CONCUSSION, BROKEN_ARM)
		 * @param name name of the patients
		 * @param complaints complaints of the patient
		 */
		public Patient(ListTracker lt, int enterTime,Boolean ambulance,  HealthProblem healthProblem,String name,String complaints){
			setEnterTime(enterTime);
			setName(name);
			setAmbulance(ambulance);
			setHealthProblem(healthProblem);
			setHealthStatus(this, HealthStatus.SICK);
			setCurrentStatus(Status.ENTERED_ER);
			setComplaints(complaints);
			setListTracker(lt);
			if (isAmbulance()){
				setTriage(healthProblem);
			} else {
				triageUrgency=null;
				triageZone=null;
				bedUrgency=null;
			}
			currentBed=null;
			currentNurse=null;
			currentDoctor=null;
			currentChair=null;
			lt.addPatient(this);
		}

		// Methods
		/**
		 * Gets the name of the patient object.
		 * @return the name of the patient object
		 */
		public String getName() {
			return name;
		}

		/**
		 * Sets the name of the patient object.
		 * @param name name of the patient
		 */
		public void setName(String name) {
			this.name = name;
		}
		/**
		 * Gets the health status of the patient object.
		 * @return the health status of the patient object
		 */
		public HealthStatus getHealthStatus() {
			return healthStatus;
		}
		/**
		 * Sets the health status of the patient. If a patient dies while in the emergency room, 
		 * the health status changes to <i>DEAD</i>. In this case, the method patientDie() is 
		 * called, to make sure that the patient is correctly removed from the emergency room.
		 * @param patient patient for which the health status is set
		 * @param healthStatus health status of the patient
		 * 
		 */
		public void setHealthStatus(Patient patient, HealthStatus healthStatus) {

			if (healthStatus==HealthStatus.DEAD){
				patientDie(patient);
			} else {
				patient.healthStatus = healthStatus;
			}
		}

		/**
		 * Gets the complaints of the patient object.
		 * @return the complaints of the patient object
		 */
		public String getComplaints() {
			return complaints;
		}
		/**
		 * Sets the complaints of the patient object.
		 * @param complaints complaints of the patient object
		 */
		public void setComplaints(String complaints) {
			this.complaints = complaints;
		}
		/**
		 * Gets the triage urgency of the patient object.
		 * @return the triage urgency of the patient object
		 */
		public TriageUrgency getTriageUrgency() {
			return triageUrgency;
		}
		/**
		 * Sets the triage urgency of the patient object. The triage urgency is chosen from the
		 * Manchester Triage System, and can take the following options:
		 * <ul>
		 * <li> IMMEDIATE</li>
		 * <li>VERY_URGENT </li>
		 * <li> URGENT</li>
		 * <li> STANDARD</li>
		 * <li> NON_URGENT</li>
		 * </ul>
		 *  After setting the triage urgency, the method setBedUrgency() is called,  in order to match immediately
		 * with the appropriate bed.
		 * <p>
		 * @param healthProblem triage urgency of the patient object
		 * 
		 */
		public void setTriage(HealthProblem healthProblem) {
			if (healthProblem==null||healthProblem==HealthProblem.UNKNOWN){
				System.out.println("Health problem not known, triage is not possible");
			}

			if (healthProblem==HealthProblem.APPENDIX){
				setTriageUrgency(TriageUrgency.IMMEDIATE);
				setTriageZone(Zone.NO_INJURY);

			} else if (healthProblem==HealthProblem.BROKEN_ARM){
				setTriageUrgency(TriageUrgency.STANDARD);
				setTriageZone(Zone.TRAUMA);
			} else if (healthProblem==HealthProblem.CONCUSSION){
				setTriageUrgency(TriageUrgency.IMMEDIATE);
				setTriageZone(Zone.TRAUMA);
			}
			if (triageUrgency!=null&&triageZone!=null){
				setBedUrgency();
			}
			if (isAmbulance() && healthProblem != HealthProblem.UNKNOWN){
				setCurrentStatus(Status.WAITING_FOR_BED);
			}

		}
		public void setTriageUrgency(TriageUrgency triageUrgency) {

			this.triageUrgency = triageUrgency;
			setBedUrgency();

		}

		public void setTriageZone(tue.yellow.Bed.Zone triageZone) {
			this.triageZone = triageZone;
		}

		/**
		 * Gets the bed urgency of the patient object.
		 * @return the bed urgency of the patient object
		 */
		public tue.yellow.Bed.Urgency getBedUrgency() {
			return bedUrgency;
		}
		/**
		 * Sets the bedUrgency of the patient. The bed urgency depends on the triage urgency. 
		 * The bed urgency is <i>URGENT</i> if the triage urgency is:
		 * <ul>
		 * <li> IMMEDIATE </li>
		 * <li> VERY_URGENT</li>
		 * <li> URGENT </li>
		 * </ul>
		 * <p>
		 * The bed urgency is <i>Not urgent</i> if the triage urgency is:
		 * <ul>
		 * <li> STANDARD</li>
		 * <li> NON_URGENT</li>
		 * </ul>
		 */
		public void setBedUrgency() {
			TriageUrgency triageUrgency;
			triageUrgency=getTriageUrgency();
			if (triageUrgency==TriageUrgency.IMMEDIATE){
				this.bedUrgency=tue.yellow.Bed.Urgency.URGENT;
			} else if (triageUrgency==TriageUrgency.VERY_URGENT){
				this.bedUrgency=tue.yellow.Bed.Urgency.URGENT;
			} else if (triageUrgency==TriageUrgency.URGENT){
				this.bedUrgency=tue.yellow.Bed.Urgency.URGENT;
			} else if (triageUrgency==TriageUrgency.STANDARD){
				this.bedUrgency=tue.yellow.Bed.Urgency.NOT_URGENT;
			} else if (triageUrgency==TriageUrgency.NON_URGENT){
				this.bedUrgency=tue.yellow.Bed.Urgency.NOT_URGENT;
			} else {
				this.bedUrgency=null;
			}
		}
		/**
		 * Gets the triage zone of the patient object.
		 * @return the triage zone of the patient object
		 */
		public tue.yellow.Bed.Zone getTriageZone() {
			return triageZone;
		}

		/**
		 * Gets the current status of the patient object.
		 * @return the current status of the patient object
		 */
		public Status getCurrentStatus() {
			return currentStatus;
		}


		public boolean isAmbulance() {
			return ambulance;
		}

		public void setAmbulance(boolean ambulance) {
			this.ambulance = ambulance;
		}

		public HealthProblem getHealthProblem() {
			return healthProblem;
		}

		public int getEnterTime() {
			return enterTime;
		}

		public int getWaitingTriageTime() {
			return waitingTriageTime;
		}

		public int getTriageTime() {
			return triageTime;
		}

		public int getWaitingBedTime() {
			return waitingBedTime;
		}

		public int getWaitingTreatmentTime() {
			return waitingTreatmentTime;
		}

		public int getTreatmentTime() {
			return treatmentTime;
		}

		public void setWaitingTriageTime(int waitingTriageTime) {
			this.waitingTriageTime = waitingTriageTime;
		}

		public void setTriageTime(int triageTime) {
			this.triageTime = triageTime;
		}

		public void setWaitingBedTime(int waitingBedTime) {
			this.waitingBedTime = waitingBedTime;
		}

		public void setWaitingTreatmentTime(int waitingTreatmentTime) {
			this.waitingTreatmentTime = waitingTreatmentTime;
		}

		public void setTreatmentTime(int treatmentTime) {
			this.treatmentTime = treatmentTime;
		}

		public void setTotalWaitingTime(int totalWaitingTime) {
			this.totalWaitingTime = totalWaitingTime;
		}

		public int getTotalWaitingTime() {
			return totalWaitingTime;
		}

		public void setTotalWaitingTime() {
			this.totalWaitingTime = this.getWaitingTriageTime() + this.getWaitingBedTime()
			+ this.getWaitingTreatmentTime();
		}

		public void setTotalProcessTime() {
			this.totalProcessTime = this.waitingTriageTime + this.triageTime +
					this.waitingBedTime + this.waitingTreatmentTime +
					this.treatmentTime;
			this.leaveTime = this.totalProcessTime + this.enterTime;
		}
		public int getTotalProcessTime() {
			return totalProcessTime;
		}

		public int getLeaveTime() {
			return leaveTime;
		}

		public void setHealthProblem(HealthProblem healthProblem) {
			this.healthProblem = healthProblem;
		}

		public void setEnterTime(int enterTime) {
			this.enterTime = enterTime;
		}





		/**
		 * Sets the current status of the patient object. This can take several forms, which are
		 * incorporate in the methods that call upon the setCurrentStatus() method. The possibilities
		 * for current status are:
		 * <ul>
		 * <li>ENTERED_ER </li>
		 * <li> UNDERGO_TRIAGE</li>
		 * <li>WAITING_FOR_BED </li>
		 * <li>IN_TREATMENT </li>
		 * <li>IN_DISCHARGE </li>
		 * <li> WAITING</li>
		 * <li>BOARDING </li>
		 * <li> LEFT_ER</li>
		 * <li> GONE</li>
		 * <li>IN_CHECK </li>
		 * </ul>
		 * @param currentStatus the status of the patient at a moment
		 * 
		 */
		public void setCurrentStatus(Status currentStatus) {
			this.currentStatus = currentStatus;
		}
		/**
		 * Gets the currently occupied bed of the patient object.
		 * @return the currently occupied bed of the patient object
		 */
		public Bed getCurrentBed() {
			return currentBed;
		}
		/**
		 * Sets the current bed of the patient object. If the patient already has a bed,
		 * he will still be assigned to this original bed, and not to the currentBed that is given as input.
		 * This method is called by the method setAssignedPatient() in the class Bed,
		 * and cannot be called on its own.  
		 * @param bed bed to which the patient is to be assigned
		 */
		public void setCurrentBed(Bed bed) {
			Bed currentBed=getCurrentBed();
			//int getBedTime = (int) Math.random()*30+1; // simulation time
			if (currentBed==null|| bed == null){
				this.currentBed=bed;
			} else {

				this.currentBed=currentBed;
			}
		}
		/**
		 * Gets the currently occupied chair of the patient object.
		 * @return the currently occupied chair of the patient object
		 */
		public Chair getCurrentChair() {
			return currentChair;
		}
		/**
		 * Sets the current chair of the patient object. If the patient already has a chair,
		 * he will still be assigned to this original chair, and not to the currentChair that is given as input.
		 * This method is called by the method setAssignedPatient() in the class Chair,
		 * and cannot be called on its own.  
		 * 
		 * @param chair chair to which the patient is to be assigned
		 * @see tue.yellow.Chair#setAssignedPatient(Patient)
		 */
		public void setCurrentChair(Chair chair) {
			Chair currentChair=getCurrentChair();
			if (currentChair==null||chair==null){
				this.currentChair=chair;
			} else {
				this.currentChair=currentChair;
			}
		}
		/**
		 * Gets the currently assigned doctor of the patient object.
		 * @return the currently assigned doctor of the patient object
		 */
		public Doctor getCurrentDoctor() {
			return currentDoctor;
		}
		/**
		 * Sets the current doctor of the patient object. If the patient already has a doctor,
		 * he will still be assigned to this original doctor, and not to the currentDoctor that is given as input.
		 * This method is called by the methods that initiate treatment with a doctor, and cannot be 
		 * called on its own. This method is also called to release a doctor from a patient.
		 * 
		 * @param currentDoctor doctor that is assigned to the patient at a moment
		 */
		public void setCurrentDoctor(Doctor currentDoctor) {
			Doctor doctor=getCurrentDoctor();
			if (doctor==null&&currentDoctor!=null){
				this.currentDoctor=currentDoctor;
			} else if (currentDoctor==null){
				this.currentDoctor=currentDoctor;
			} else {
				this.currentDoctor = doctor;
			}
		}
		/**
		 * Gets the currently assigned nurse of the patient object.
		 * @return the currently assigned nurse of the patient object
		 */
		public Nurse getCurrentNurse() {
			return currentNurse;
		}
		/**Sets the current nurse for the patient object. If the patient already has a nurse,
		 * he will still be assigned to this original nurse, and not to the currentNurse that is given as input.
		 * This method is called by the methods that initiate treatment with a nurse, and cannot be 
		 * called on its own. This method is also called to release a nurse from a patient.
		 * 
		 * @param currentNurse nurse that is assigned to the patient at a moment
		 */
		public void setCurrentNurse(Nurse currentNurse) {
			Nurse nurse = getCurrentNurse();
			if (nurse==null&&currentNurse!=null){
				this.currentNurse=currentNurse;

			} else if (currentNurse==null){
				this.currentNurse=currentNurse;
			} else {
				this.currentNurse=nurse;
			}
		}
		/**
		 * Releases a patient from the emergency room. A patient can leave the emergency room
		 * when he is in one of the following states:
		 * <ul>
		 * <li>ENTERED_ER </li>
		 * <li>WAITING_FOR_BED </li>
		 * <li>WAITING </li>
		 * <li>BOARDING </li>
		 * <li>IN_DISCHARGE </li>
		 * </ul>
		 * <p>
		 * This means that a patient cannot leave during treatment. When a patient leaves
		 * the emergency room, the status will change into <i>LEFT_ER</i>, 
		 * and the associated chair, bed, doctor or nurse will be released from the patient.
		 * 
		 * @param patient patient that is to be released from the emergency room
		 */

		public void leaveEmergencyRoom(Patient patient){
			Status patientStatus;
			Bed bed=patient.getCurrentBed();
			Chair chair=patient.getCurrentChair();
			Doctor doctor=patient.getCurrentDoctor();
			Nurse nurse=patient.getCurrentNurse();
			patientStatus=getCurrentStatus();
			if (patientStatus==Status.ENTERED_ER||patientStatus==Status.WAITING_FOR_BED){
				this.currentStatus=Status.LEFT_ER;
				chair.releasePatient(patient);
				bed.releasePatient(patient);
				doctor.releasePatient(patient);
				nurse.releasePatient(patient);
			} else if (patientStatus==Status.WAITING){
				this.currentStatus=Status.LEFT_ER;
				// When the patient is the status 'waiting', he already is not in a chair anymore
				bed.releasePatient(patient);
				// When the patient is waiting, he is not assigned to a doctor/nurse anymore
			} else if (patientStatus==Status.BOARDING){
				this.currentStatus=Status.LEFT_ER;
				bed.releasePatient(patient);
				nurse.releasePatient(patient);
			} else if (patientStatus==Status.IN_DISCHARGE){
				this.currentStatus=Status.LEFT_ER;
				bed.releasePatient(patient);
				doctor.releasePatient(patient);
				nurse.releasePatient(patient);
				patient.setCurrentBed(null);
			} else {
				this.currentStatus=patientStatus;
			}
		}

		public void goToChair(){
			Chair freeChair = lt.getAvailableChair();
			if (freeChair != null){
				freeChair.setAssignedPatient(this);
				this.setCurrentChair(freeChair);
			} else {
				System.out.println("Sorry, no chair available");
			}
		}

		public void goToBed(){
			Zone zone = getTriageZone();
			Bed freeBed = null;
			Bed.Urgency urgency = getBedUrgency();
			if (urgency == Bed.Urgency.URGENT && zone== Bed.Zone.NO_INJURY) {
				freeBed = lt.getAvailableNoInjuryUrgentBed();
				lt.getAvailableNoInjuryUrgentBedList().remove(freeBed);
			} else if (urgency == Bed.Urgency.NOT_URGENT && zone == Zone.NO_INJURY){
				freeBed = lt.getAvailableNoInjuryNotUrgentBed();
				lt.getAvailableNoInjuryNotUrgentBedList().remove(freeBed);
			} else if (urgency == Bed.Urgency.URGENT && zone == Zone.TRAUMA){
				freeBed = lt.getAvailableTraumaUrgentBed();
				lt.getAvailableTraumaUrgentBedList().remove(freeBed);
			} else if (urgency == Bed.Urgency.NOT_URGENT && zone == Zone.TRAUMA){
				freeBed = lt.getAvailableTraumaNotUrgentBed();
				lt.getAvailableTraumaNotUrgentBedList().remove(freeBed);
			}
			if (freeBed != null){
				freeBed.setAssignedPatient(this);
				lt.getAvailableBedList().remove(freeBed);
				this.setCurrentBed(freeBed);
			} else {
				System.out.println("Sorry, no bed available");
			}
		}
		/**
		 * In the unfortunate case that a patient dies during his/her stay in the
		 * emergency room, this method is called. It changes the health status of the
		 * patient to <i>DEAD</i> and the current status to <i>GONE</i>. Furthermore, 
		 * associated resources are released from the patient.
		 * @param patient patient that dies while in the emergency room
		 */
		public void patientDie(Patient patient){
			Nurse nurse=patient.getCurrentNurse();
			Doctor doctor=patient.getCurrentDoctor();
			Bed bed=patient.getCurrentBed();
			Chair chair=patient.getCurrentChair();
			this.healthStatus=HealthStatus.DEAD;

			if (doctor!=null){
				doctor.releasePatient(patient);
			} 
			if (nurse!=null){
				nurse.releasePatient(patient);
			}
			if (bed!=null){
				bed.releasePatient(patient);
			} 
			if (chair!=null){
				chair.releasePatient(patient);
			}
			this.currentStatus=Status.GONE;
		}
		/**
		 * Gets the list (emergency room) to which the patient belongs
		 * @return the list (emergency room) to which the patient belongs
		 */
		public ListTracker getListTracker() {
			return lt;
		}
		/**
		 * Sets the list to which the patient belongs. Note that this does not add the patient to the list.
		 * @param listTracker list to which the patient belongs.
		 */
		public void setListTracker(ListTracker listTracker) {
			lt = listTracker;
		}
		/**
		 * When a patient is unsatisfied with the staff or treatment, he/she
		 * may get violent. In this case, this method is called. It will ask for a nurse
		 * to come for comfort/assistance. It will change the complains of patient by 
		 * adding <i>Violent</i> behind.
		 */

		public void getViolent(){
			ListTracker lt=getListTracker();
			String currentComplaints = this.getComplaints();
			setComplaints(currentComplaints + ". Violent");
			this.callNurse(lt);
		}
		/**
		 *When a patient calls for a nurse, he/she will be added in the list of patient
		 *that have called or requested a nurse. If a patient is already assigned to a nurse,
		 *this method doesn't add the patient to the list.
		 */
		public void callNurse(ListTracker lt){
			if(this.getCurrentNurse()!=null){
				System.out.println("This patient already has a nurse.");
			}else {
				lt.setCallNurseList(this);
			}
		}
		/**
		 *When a patient gets sicker after triage, he/she calls for another check by a nurse.
		 *It will change the complains of patient by adding <i>Sicker</i> behind.
		 */
		public void getSicker(){
			if(this.getCurrentNurse()!=null){
				System.out.println("This patient already has a nurse.");
			}else {
				this.complaints = this.getComplaints() + ".Sicker";
				lt.setCallForCheckList(this);
			}
		}
		/**
		 *When a patient calms down, he/she will no longer be violent and the nurse will be released.
		 */
		public void calmDown(){
			Nurse nurse=getCurrentNurse();
			if(this.complaints.endsWith(". Violent")&&nurse!=null){
				this.complaints = this.complaints.substring(0, this.complaints.length()-9);
				nurse.releasePatient(this);
			} else {
				System.out.println("This patient doesn't need to calm down.");
			}
		}
		/**
		 * Displays the information of a patient object
		 * @return Patient information: name, health status, current status, complaints, triage urgency,
		 * triage zone, bed urgency, current bed, current chair, current status, current doctor and current nurse
		 */
		@Override
		public String toString() {
			String currentBed,currentChair,currentDoctor,currentNurse;
			Bed bed=getCurrentBed();
			Chair chair=getCurrentChair();
			Nurse nurse=getCurrentNurse();
			Doctor doctor=getCurrentDoctor();
			if (bed==null){
				currentBed="No bed";
			} else {
				currentBed=bed.getName();
			}
			if (chair==null){
				currentChair="No chair";
			} else {
				currentChair=chair.getName();
			}
			if (nurse==null){
				currentNurse="No nurse";
			} else {
				currentNurse=nurse.getStaffName();
			}
			if (doctor==null){
				currentDoctor="No doctor";
			} else {
				currentDoctor=doctor.getStaffName();
			}
			return "Patient information:"+"\n"+ 
			"Name            = " + name +"\n"+ 
			"Health status   = " + healthStatus +"\n"+ 
			"Ambulance       = " + ambulance +"\n" +
			"Health probllem = " + healthProblem +"\n" +
			"Complaints      = "+ complaints +"\n"+ 
			"Triage urgency  = " + triageUrgency +"\n"+ 
			"Bed urgency     = " + bedUrgency +"\n"+ 
			"Triage zone     = " + triageZone + "\n"+
			"Current status  = "+ currentStatus + "\n"+
			"Current bed     = "+ currentBed + "\n"+
			"Current chair   = "+ currentChair + "\n"+
			"Current nurse   = "+ currentNurse + "\n"+
			"Current doctor  = "+ currentDoctor + "\n";

		}



}
