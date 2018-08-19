package tue.yellow;


import tue.yellow.Patient.HealthProblem;
import tue.yellow.Patient.HealthStatus;
import tue.yellow.Patient.Status;
import tue.yellow.Patient.TriageUrgency;
import tue.yellow.ListTracker;


/**
 * Nurse is the class for the nurses in the emergency room. A nurse has a name, 
 * an indicator of their availability, an assigned patient, and a current activity.
 * <p>
 * A nurse interacts with patients, in order to give treatment or perform triage. 

 *
 * @author Team Yellow
 */
public class Nurse extends Staff{


	//constructor
	public Nurse(ListTracker lt){
		this(lt, "no name");
	}

	public Nurse(ListTracker lt, String name) {
		this(lt, name, Availability.AVAILABLE);
	}

	public Nurse (ListTracker lt, String name, Availability availability){
		this(lt, name, availability, null);
	}

	public Nurse(ListTracker lt, String name, Availability availability,Patient patient){
		this(lt, name, availability, patient,"Unknown");
	}



	public Nurse(ListTracker lt,String staffName, Availability staffAvailability, Patient assignedPatient, String currentActivity) {
		super(lt,staffName,staffAvailability,assignedPatient,currentActivity);
		lt.addNurse(this);

	}

	// Methods	



	@Override
	public void setAssignedPatient(Patient assignedPatient) {
		Patient patient=getAssignedPatient();
		Nurse nurse = assignedPatient.getCurrentNurse();
		boolean patientMatch = false;
		if (patient == null && nurse == null) {
			patientMatch = true;
		}else{
			patientMatch = false;
		}
		if (patientMatch){
			this.assignedPatient=assignedPatient;
			assignedPatient.setCurrentNurse(this);
			assignedPatient.setCurrentStatus(Status.IN_TREATMENT);
		} else {
			System.out.println("Not able to assign this patient to you.");
		}
	}


	/**
	 * The nurse performs triage on a patient. This can only happen if the nurse is
	 * currently available, unassigned to a patient, and if that patient is not being triaged by 
	 * another nurse.
	 * <p>
	 * While performing triage, the nurse and patient are assigned to each other.
	 * Furthermore, the current status of the patient is <i>UNDERGO_TRIAGE</i>, 
	 * while the nurse's activity will be <i>"Performing triage"</i>, and the nurse's 
	 * availability will be <i>NOT_AVAILABLE</i>.
	 * <p>
	 * As input for this method, not only the patient on which the triage will be performed is needed, 
	 * but also the resulting zone and urgency level of the triage.
	 *
	 * @param assignedPatient the patient who will receive triage
	 * @param zone the zone in which this patient should be treated, decided by this nurse
	 * @param urgencyLevel the urgency level of this patient's triage, decided by this nurse
	 */

	public void performTriage(Patient assignedPatient) {
		performTriage(assignedPatient,HealthProblem.UNKNOWN);
	}
	public void performTriage(Patient assignedPatient, HealthProblem healthProblem){
		String 			activity;
		Nurse 			nurse = assignedPatient.getCurrentNurse();
		Doctor 			doctor = assignedPatient.getCurrentDoctor();
		Patient 		patient = getAssignedPatient();
		boolean 		noStaff = false;
		boolean 		noPatient = false;
		if (nurse == null && doctor == null){
			noStaff=true;
		}
		if (patient == null && this.staffAvailability==Availability.AVAILABLE){
			noPatient = true;
		}

		if (noStaff && noPatient){
			setAssignedPatient(assignedPatient);
			assignedPatient.setCurrentStatus(Status.UNDERGO_TRIAGE);
			setStaffAvailability(Availability.NOT_AVAILABLE);
			assignedPatient.setTriage(healthProblem);
			activity = "Performing Triage";
			setCurrentActivity(activity);
			System.out.println(activity);
		} else {
			System.out.println("Nurse Not possible to perform Triage. Good luck.");
		}



	}

	/**
	 * Performs a Parameters Monitoring on the patient, if the nurse is available to perform treatment,
	 * and the patient is not already treated by another nurse, and the patient is assigned to a bed.
	 * <p>
	 * While performing Parameters Monitoring, the nurse and patient are assigned to each other, 
	 * the current status of the patient is changed to <i>IN_TREATMENT</i>. The current 
	 * activity of the nurse will be <i>"Performing a Parameters Monitoring"</i>, while the nurse's
	 * availability will be <i>NOT_AVAILABLE</i> while performing this method.
	 *
	 * @param assignedPatient the patient who will receive this Parameters Monitoring
	 */
	public void performParametersMonitoring(Patient assignedPatient){
		String 			activity;
		Nurse 			nurse = assignedPatient.getCurrentNurse();
		Doctor 			doctor = assignedPatient.getCurrentDoctor();
		Patient 		patient = getAssignedPatient();
		Bed 			bed = assignedPatient.getCurrentBed();
		boolean 		noStaff = false;
		boolean 		noPatient = false;
		boolean			patientOnBed = false;
		if (nurse == null && doctor == null){
			noStaff=true;
		}
		if (patient == null && this.staffAvailability==Availability.AVAILABLE){
			noPatient = true;
		}
		if (bed!=null){
			patientOnBed = true;
		}

		if (noStaff && noPatient && patientOnBed){
			setAssignedPatient(assignedPatient);
			assignedPatient.setCurrentStatus(Status.IN_TREATMENT);
			setStaffAvailability(Availability.NOT_AVAILABLE);
			activity = "Performing Parameters Monitoring";
			setCurrentActivity(activity);
			System.out.println(activity);
		} else {
			System.out.println("Nurse Not possible to perform Parameters Monitoring. Good luck.");
		}

	}

	/**
	 * Performs a Blood Sampling on the patient, if the nurse is available to perform treatment,
	 * and the patient is not already treated by another nurse, and the patient is assigned to a bed.
	 * <p>
	 * While performing Blood Sampling, the nurse and patient are assigned to each other, 
	 * the current status of the patient is changed to <i>IN_TREATMENT</i>. The current 
	 * activity of the nurse will be <i>"Performing a Blood Sampling"</i>, while the nurse's
	 * availability will be <i>"NOT_AVAILABLE"</i> while performing this method.
	 *
	 * @param assignedPatient the patient who will receive this Blood Sampling
	 */
	public void performBloodSampling(Patient assignedPatient){
		String 			activity;
		Nurse 			nurse = assignedPatient.getCurrentNurse();
		Doctor 			doctor = assignedPatient.getCurrentDoctor();
		Patient 		patient = getAssignedPatient();
		Bed 			bed = assignedPatient.getCurrentBed();
		boolean 		noStaff = false;
		boolean 		noPatient = false;
		boolean			patientOnBed = false;
		if (nurse == null && doctor == null){
			noStaff=true;
		}
		if (patient == null && this.staffAvailability==Availability.AVAILABLE){
			noPatient = true;
		}
		if (bed!=null){
			patientOnBed = true;
		}

		if (noStaff && noPatient && patientOnBed){
			setAssignedPatient(assignedPatient);
			assignedPatient.setCurrentStatus(Status.IN_TREATMENT);
			setStaffAvailability(Availability.NOT_AVAILABLE);
			activity = "Performing Blood Sampling";
			setCurrentActivity(activity);
			System.out.println(activity);
		} else {
			System.out.println("Nurse Not possible to perform Blood Sampling. Good luck.");
		}

	}

	/**
	 * Performs an Internal Medical Imaging on the patient, if the nurse is available to perform treatment,
	 * and the patient is not already treated by another nurse, and the patient is assigned to a bed.
	 * <p>
	 * While performing Internal Medical Imaging, the nurse and patient are assigned to each other, 
	 * the current status of the patient is changed to <i>IN_TREATMENT</i>. The current 
	 * activity of the nurse will be <i>"Performing an Internal Medical Imaging"</i>, while the nurse's
	 * availability will be <i>NOT_AVAILABLE</i> while performing this method.
	 *
	 * @param assignedPatient the patient who will receive this Internal Medical Imaging
	 */

	public void performInternalMI(Patient assignedPatient){
		String 			activity;
		Nurse 			nurse = assignedPatient.getCurrentNurse();
		Doctor 			doctor = assignedPatient.getCurrentDoctor();
		Patient 		patient = getAssignedPatient();
		Bed 			bed = assignedPatient.getCurrentBed();
		boolean 		noStaff = false;
		boolean 		noPatient = false;
		boolean			patientOnBed = false;
		if (nurse == null && doctor == null){
			noStaff=true;
		}
		if (patient == null && this.staffAvailability==Availability.AVAILABLE){
			noPatient = true;
		}
		if (bed!=null){
			patientOnBed = true;
		}

		if (noStaff && noPatient && patientOnBed){
			setAssignedPatient(assignedPatient);
			assignedPatient.setCurrentStatus(Status.IN_TREATMENT);
			setStaffAvailability(Availability.NOT_AVAILABLE);
			activity = "Performing Internal Medical Imaging";
			setCurrentActivity(activity);
			System.out.println(activity);
		} else {
			System.out.println("Nurse Not possible to Internal Medical Imaging. Good luck.");
		}

	}

	/**
	 * Performs Boarding on the patient, if the nurse is available to perform treatment,
	 * and the patient is not already treated by another nurse, and the patient is assigned to a bed.
	 * <p>
	 * While performing Boarding, the nurse and patient are assigned to each other, 
	 * the current status of the patient is changed to <i>BOARDING</i>. The current 
	 * activity of the nurse will be <i>"Performing boarding"</i>, while the nurse's
	 * availability will be <i>NOT_AVAILABLE</i> while performing this method. During this method,
	 * the patient will leave the emergency room.
	 *
	 * @param assignedPatient the patient who will receive this Other Examination and Treatment
	 */
	public void performBoarding(Patient assignedPatient){
		Status 			status = assignedPatient.getCurrentStatus();
		String 			activity;
		Nurse 			nurse = assignedPatient.getCurrentNurse();
		Doctor 			doctor = assignedPatient.getCurrentDoctor();
		Patient 		patient = getAssignedPatient();
		Bed 			bed = assignedPatient.getCurrentBed();
		boolean 		noStaff = false;
		boolean 		noPatient = false;
		boolean			patientOnBed = false;
		boolean 		patientBoarding = false;
		if (nurse == null && doctor == null){
			noStaff=true;
		}
		if (patient == null&& this.staffAvailability==Availability.AVAILABLE){
			noPatient = true;
			
		}
		if (bed!=null){
			patientOnBed = true;
		}
		if (status == Status.WAITING_FOR_BOARDING) {
			patientBoarding = true;
		}

		if (noStaff && noPatient && patientOnBed && patientBoarding ){
			setAssignedPatient(assignedPatient);
			assignedPatient.setCurrentStatus(Status.BOARDING);
			setStaffAvailability(Availability.NOT_AVAILABLE);
			activity = "Performing Boarding";
			setCurrentActivity(activity);
			System.out.println(activity);
		} else {
			System.out.println("Nurse Not possible to Boarding. Good luck.");
		}

	}


	/**
	 * Finishes triage and gives a health status to this patient. Triage can only be finished 
	 * if the nurse is assigned to that patient, meaning that a triage has to be started
	 * and finished by the same nurse. The nurse gives an assessment of the health status after
	 * the triage, and assigns this to the patient. The patient's status will be <i>WAITING_FOR_BED</i>
	 * after triage, and the staff member will become <i>AVAILABLE</i> again. After finishing triage,
	 * the nurse will retreat to the staff room (current activity: <i>"In the staff room"</i>).
	 *
	 * @param assignedPatient the patient whose treatment will be finished
	 * @param healthStatus the assessment from the nurse given to the patient following the treatment
	 */	

	public void finishTriage(Patient assignedPatient) {
		finishTriage(assignedPatient,HealthStatus.SICK);
	}


	public void finishTriage(Patient assignedPatient, HealthStatus healthStatus){
		Patient currentPatient;
		Doctor doctor=assignedPatient.getCurrentDoctor();
		Nurse nurse = assignedPatient.getCurrentNurse();
		currentPatient=getAssignedPatient();
		Status status=assignedPatient.getCurrentStatus();
		boolean patientMatch=false;
		if (currentPatient == assignedPatient){
			if (nurse == this && doctor == null){
				patientMatch = true;
			}
		}

		if(patientMatch && status==Status.UNDERGO_TRIAGE){
			assignedPatient.setCurrentStatus(Status.WAITING_FOR_BED);
			assignedPatient.setHealthStatus(assignedPatient, healthStatus);
			nurse.releasePatient(assignedPatient);
		} else {
			System.out.println("Nurse Can't finish the triage, the patient is not assigned to you.");
		}


	}

	/**
	 * A nurse will be assigned to the patient in the callNurseList to comfort him/her.
	 * After being assigned a nurse, the patient will be no more in the callNurseList.
	 */
	/**
	 * A nurse will be assigned to the patient in the callNurseList to calm the patient.
	 * After being assigned a nurse, the patient will be removed from the callNurseList.
	 * @param assignedPatient
	 */
	public void calmPatient(Patient assignedPatient){
		String activity;
		Patient patient=getAssignedPatient();
		Availability availability=getStaffAvailability();
		if(assignedPatient!=this.listTracker.getCallNurseList().get(0)){
			System.out.println("This patient is not calling for a check.");
		}else if (this.listTracker.getCallNurseList().isEmpty()){
			System.out.println("There is no patient call for help.");
		}else if(patient==null&&availability==Availability.AVAILABLE){
			setAssignedPatient(this.listTracker.getCallNurseList().get(0));
			setStaffAvailability(Availability.NOT_AVAILABLE);
			activity = "calm a patient down";
			setCurrentActivity(activity);
			this.listTracker.removeCallNurseList(assignedPatient);
		}
	}
	/**
	 * When a patient gets sicker, a nurse will be assigned to this patient to redo triage, as
	 * it could happen that the urgency level has changed.
	 * This method can only happen after triage, as otherwise there is no reference point as to 
	 * what was the initial 'sickness' (urgency).
	 * 
	 * @param assignedPatient the patient who will receive the check
	 * @param urgencyLevel the urgency level of this patient, can be decided and changed by nurse
	 */
	public void checkOnPatient(Patient assignedPatient, TriageUrgency urgencyLevel){
		String activity;
		Nurse nurse=assignedPatient.getCurrentNurse();
		Patient patient=getAssignedPatient();
		Availability availability=getStaffAvailability();

		if(assignedPatient!=this.listTracker.getCallForCheckList().get(0)){
			System.out.println("This patient is not calling for a check.");
		}else if (assignedPatient.getCurrentStatus() == Status.ENTERED_ER){
			System.out.println("This patient needs a triage, not a check.");
		}else if (patient==null&&nurse==null&&availability==Availability.AVAILABLE){ 
			setAssignedPatient(assignedPatient);
			assignedPatient.setTriageUrgency(urgencyLevel);
			//			complains = assignedPatient.getComplaints().substring(0, assignedPatient.getComplaints().length()-7);
			assignedPatient.setCurrentStatus(Status.IN_CHECK);
			setStaffAvailability(Availability.NOT_AVAILABLE);
			activity = "Performing a check on a patient";
			setCurrentActivity(activity);
			this.listTracker.removeCallForCheckList(assignedPatient);;
		} else {
			System.out.println("Nurse Not able to perform a check");
		}
	}
	/**
	 * Finishes a check and gives a health status to this patient. A check can only be finished 
	 * if the nurse is assigned to that patient, meaning that a check has to be started
	 * and finished by the same nurse. The nurse gives an assessment of the health status after
	 * the check, and assigns this to the patient. The patient's status will be <i>WAITING</i>
	 * after check, and the staff member will become <i>AVAILABLE</i> again. After finishing check,
	 * the nurse will retreat to the staff room (current activity: <i>"In the staff room"</i>).
	 *
	 * @param assignedPatient the patient whose treatment will be finished
	 * @param healthStatus the assessment from the nurse given to the patient following the treatment
	 */	
	public void finishCheck(Patient assignedPatient, HealthStatus healthStatus){
		Patient currentPatient;
		Nurse nurse=assignedPatient.getCurrentNurse();
		currentPatient=getAssignedPatient();
		if(currentPatient==assignedPatient&&nurse==this){
			assignedPatient.setCurrentStatus(Status.WAITING);
			assignedPatient.setHealthStatus(assignedPatient, healthStatus);
			releasePatient(assignedPatient);
			setCurrentActivity("In the staff room");
			setStaffAvailability(Availability.AVAILABLE);
		} else {
			System.out.println("Nurse Can't finish the check, the patient is not assigned to you.");
		}
	}
	/**
	 * When a nurse goes home, he/she is not available anymore to perform treatment.
	 * This method sets his/her availability to <I>NOT_AVAILABLE</I>.
	 */


}

