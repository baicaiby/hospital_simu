package tue.yellow;
import tue.yellow.Bed.Urgency;
import tue.yellow.Bed.Zone;
import tue.yellow.Patient.HealthProblem;
import tue.yellow.Patient.HealthStatus;
import tue.yellow.Patient.TriageUrgency;

import java.util.Random;
/**
 * The class WholeProcess explains the processes happen in the emergency department.
 * For walking patients, they will get triage when they arrive the hospital, while the ambulance
 * patients won't get, because they already received triage in the ambulance. Patient will get
 * specific health problem after triage, for every health problem, fixed process is set here. Before 
 * they get treated, they should be assigned to a bed. Before they get beds, every patient has a zone and
 * urgency level(received from triage), it should matches the beds' zone and urgency level.
 * During process for every health problem, we give a fixed plan, and after treated, they will leave the emergency room.
 * One WholeProcess object only belongs to one patient, which is the currenPatient, for every stap of the process, the
 * currentNurse or currentDoctor should be the one is performing something on the current patient.
 * process time and waiting time are also counted here.
 * 
 * @author Team Yellow
 *
 */
public class WholeProcess {
	private Doctor currentDoctor;
	private Nurse currentNurse;
	private Patient currentPatient;
	private Bed currentBed;
	private Chair currentChair;
	private ListTracker listTracker;
	private int triageTime;
	private int processTime;
	private int waitForBedTime;
	private int patientMinusBed = 0;
	private Random rand;
	private int urgentWaitingTime = 5;
	private int nonUrgentWaitingTime = 40;
	/**
	 * When an object of WholeProcess is created, they have to be added to a listtracker, which can be seen as 
	 * the emergency room the treatment strategies come from. Therefore, this is a necessary parameter
	 * in creating the staff object. 
	 * <br>
	 * For a new WholeProcess object, the <i>currentPatient</i>, <i>currentNurse</i>, <i>currentDoctor</i>
	 * will be null.
	 * @param lt the emergency department (list) to which the treatment strategies come from
	 */
	//constructor
	public WholeProcess(ListTracker lt){
		currentDoctor = null;
		currentNurse = null;
		currentPatient = null;
		listTracker = lt;
		currentChair = null;
		rand = new Random();

	}

	//method
	/**
	 * Gets the current bed the current patient is assigned to.
	 * @return the bed for the current patient
	 */
	public Bed getCurrentBed() {
		return currentBed;
	}
	/**
	 * Gets the current chair the current patient is assigned to.
	 * @return the chair for the current patient
	 */
	public Chair getCurrentChair() {
		return currentChair;
	}
	/**
	 * Sets the current nurse to the current patient, in almost every step, the nurse is need for the 
	 * process.
	 * @return the current nurse
	 */
	public void setCurrentNurse(Nurse currentNurse) {
		this.currentNurse = currentNurse;
	}
	/**
	 * Sets the current patient to the wholeprocess object..
	 * @param currentPatient the current patient
	 */
	public void setCurrentPatient(Patient currentPatient) {
		this.currentPatient = currentPatient;
	}
	//	public int getUrgentWaitingTime() {
	//		return urgentWaitingTime;
	//	}

	//	public int getNonUrgentWaitingTime() {
	//		return nonUrgentWaitingTime;
	//	}
	/**
	 * Sets the urgent waiting time for the patient whose urgency level is <i>urgent</i>
	 * Usually the urgent patient have less waiting time than the others, Appendix and Concussion is urgent.
	 * @param urgentWaitingTime the waiting time for urgent patient
	 */
	public void setUrgentWaitingTime(int urgentWaitingTime) {
		this.urgentWaitingTime = urgentWaitingTime;
	}
	/**
	 * Sets the not-urgent waiting time for the patient whose urgency level is <i>not-urgent</i>
	 * Usually the not-urgent patient have more waiting time than the urgent patient, brkoen_arm is not urgent..
	 * @return the waiting time for urgent patient
	 */
	public void setNonUrgentWaitingTime(int nonUrgentWaitingTime) {
		this.nonUrgentWaitingTime = nonUrgentWaitingTime;
	}
	/**
	 * Gets the list (emergency department) to which the treatment whole process belongs to
	 * @return the list / emergency department of the treatment whole process
	 */
	public ListTracker getListTracker() {
		return listTracker;
	}
	/**
	 * Sets the list (emergency department) to which the treatment whole process belongs to
	 * @param the list / emergency department of the treatment whole process
	 */
	public void setListTracker(ListTracker listTracker) {
		this.listTracker = listTracker;
	}
	/**
	 * Gets the time when patients get treatment process 
	 * @return the list / emergency department of the treatment whole process
	 */
	public int getProcessTime() {
		return processTime;
	}
	/**
	 * Sets the time when patients get treatment process
	 * @param the time when patients get treatment process
	 */
	public void setProcessTime(int processTime) {
		this.processTime = processTime;
	}
	/**
	 * Gets the time when patients are waiting for the beds after triage 
	 * @return time when patients are waiting for the beds after triage 
	 */
	public int getWaitForBedTime() {
		return waitForBedTime;
	}
	/**
	 * Sets the time when patients are waiting for the beds after triage 
	 * @param waitForBedTime time when patients are waiting for the beds after triage 
	 */
	public void setWaitForBedTime(int waitForBedTime) {
		this.waitForBedTime = waitForBedTime;
	}
	//	public void setPatientMinusBed(int patientMinusBed) {
	//		this.patientMinusBed = patientMinusBed;
	//	}
	/**
	 * Patient will be assigned to a chair first, and an available nurse will perform the triage.
	 * If there is no available nurse, the patient can't get the triage process done.
	 * Patient will be randomly assign a health problem, broken_arm, concussion or appendix.
	 * During the process, waiting triage time and triage time are randomly generated. 
	 * @param patient the patient who is getting into the triage process
	 */
	private void triageProcess(Patient patient) throws NullPointerException{
		int n;
		HealthProblem healthProblem = patient.getHealthProblem();
		setCurrentPatient(patient);
		if(patient.getListTracker().getAvailableChair() == null){
			System.out.println("Can't triage, no chair for this patient.");
		}else{
			patient.goToChair();

		}
		Nurse freeNurse = listTracker.getAvailableNurse();
		if (freeNurse != null){
			this.currentNurse = getListTracker().getAvailableNurse();
		} else {
			System.out.println("Sorry, no nurse available to perform triage");
		}


		int k = rand.nextInt(3);
		if (k==0) {
			healthProblem = HealthProblem.BROKEN_ARM;
			patient.setHealthProblem(healthProblem);
		} else if (k==1) {
			healthProblem = HealthProblem.CONCUSSION;
			patient.setHealthProblem(healthProblem);
		} else {
			healthProblem = HealthProblem.APPENDIX;
			patient.setHealthProblem(healthProblem);
		}
		System.out.println("triage patient: "+ currentPatient.getName());
		System.out.println("triage nurse: "+ currentNurse.getStaffName());
		System.out.println("triage chair: "+ patient.getCurrentChair().getName());
		currentNurse.performTriage(currentPatient, healthProblem);
		//add duration
		Random rand = new Random();
		int waitingtime = rand.nextInt(5);
		patient.setWaitingTriageTime(waitingtime);
		int time = rand.nextInt(10);
		patient.setTriageTime(time);
		currentNurse.finishTriage(currentPatient, HealthStatus.SICK);

		System.out.println("Triage finished, took " + triageTime +" minutes. Health problem = " + patient.getHealthProblem());

	}
	/**
	 * When this patient is not assigned to any other bed, he/she will be released from the chair and 
	 * assigned to an available bed.
	 * The time for waiting for the bed is randomly generated.
	 * @param patient the patient who is waitting for the bed
	 */
	public void waitForBed(Patient patient){

		if(patient.getCurrentChair()!=null){
			patient.getCurrentChair().releasePatient(currentPatient);
		}
		patient.goToBed();
		if (patient!= null){
			System.out.println(patient.getName() + " is assigned to " + patient.getCurrentBed().getName());
			Random random = new Random();
			waitForBedTime = rand.nextInt(20);
			patient.setWaitingBedTime(waitForBedTime);;
		}


	}
	/**
	 * When this patient gets the health problem of broken arm, he/she be assigned a nurse first, and waits to get 
	 * clinical examination. After 15 minutes and waits for a random time, he/she will get ExternalMI, this step will
	 * last for 30 minutes, and then he/she will get other exam treatment. 15 minutes later, and  random time waiting 
	 * for discharge, after 5 minutes' discharge, this patient will get healthy and leave the emergency room. 
	 * @param patient the patient who is getting the Broken_arm process treatment steps
	 */
	private void processBrokenArm(Patient patient){
		if(currentPatient.getHealthProblem() == HealthProblem.BROKEN_ARM){
			int processtime = 0;
			int treatmentWaitingTime = 0;
			Random random = new Random();
			ListTracker lt = this.getListTracker();
			Nurse currentNurse = lt.getAvailableNurse();
			treatmentWaitingTime = treatmentWaitingTime + random.nextInt(nonUrgentWaitingTime);
			currentNurse.performClinicalExam(patient);
			System.out.println("Staff operating on "+patient.getName()+" : "+patient.getCurrentDoctor().getStaffName()+", "+patient.getCurrentNurse().getStaffName());
			processTime = processtime + 15;
			currentNurse.finishTreatment(patient, HealthStatus.SICK);
			treatmentWaitingTime = treatmentWaitingTime + random.nextInt(nonUrgentWaitingTime);
			currentNurse.performExternalMI(patient);
			processTime = processTime + 30;
			currentNurse.finishTreatment(patient, HealthStatus.SICK);
			treatmentWaitingTime = treatmentWaitingTime + random.nextInt(nonUrgentWaitingTime);
			currentNurse.performOtherExamTreatment(patient);
			processTime = processTime + 15;
			currentNurse.finishTreatment(patient, HealthStatus.SICK);
			treatmentWaitingTime = treatmentWaitingTime + random.nextInt(nonUrgentWaitingTime);
			currentNurse.performDischarge(patient);
			processTime = processTime + 5;
			currentNurse.finishTreatment(patient, HealthStatus.HEALTHY);
			patient.setTreatmentTime(processTime);
			patient.setWaitingTreatmentTime(treatmentWaitingTime);
			patient.setTotalWaitingTime();
			patient.setTotalProcessTime();
			System.out.println("waitForBedTime: "+waitForBedTime + "\n" +"processTime: "+processTime+"\n");
			//System.out.println(patient.toString());

		}else{
			System.out.println("This patient is not supposed to be assigned to Broken arm process.");
		}

	}
	/**
	 * when this patient gets the health problem of concussion, he/she be assigned a nurse first, and waits to get 
	 * clinical examination. After 15 minutes and waits for a random time, he/she will get ParametersMonitoring lasting for
	 * 5 minutes, then he/she will spend 5 minutes on bloodsampling. Then he/she get InternalMI, this step will
	 * last for 20 minutes, and then he/she will get other exam treatment. 15 minutes later, and  random time waiting 
	 * for discharge, after 5 minutes' discharge, this patient will get healthy and leave the emergency room. 
	 * @param patient the patient who is getting the concussion process treatment steps
	 */
	private void processConcussion(Patient patient){
		if(currentPatient.getHealthProblem() == HealthProblem.CONCUSSION){
			ListTracker lt = this.getListTracker();
			Nurse currentNurse = lt.getAvailableNurse();
			Random random = new Random();
			int treatmentWaitingTime = 0;
			treatmentWaitingTime = treatmentWaitingTime + random.nextInt(urgentWaitingTime);
			currentNurse.performClinicalExam(patient);
			System.out.println("Staff operating on "+patient.getName()+" : "+patient.getCurrentDoctor().getStaffName()+", "+patient.getCurrentNurse().getStaffName());
			processTime = processTime + 15;
			currentNurse.finishTreatment(patient, HealthStatus.SICK);
			treatmentWaitingTime = treatmentWaitingTime + random.nextInt(urgentWaitingTime);
			currentNurse.performParametersMonitoring(patient);
			processTime = processTime + 5;
			currentNurse.finishTreatment(patient, HealthStatus.SICK);
			treatmentWaitingTime = treatmentWaitingTime + random.nextInt(urgentWaitingTime);
			currentNurse.performBloodSampling(patient);
			processTime = processTime + 5;
			currentNurse.finishTreatment(patient, HealthStatus.SICK);
			treatmentWaitingTime = treatmentWaitingTime + random.nextInt(urgentWaitingTime);
			currentNurse.performInternalMI(patient);
			processTime = processTime + 20;
			currentNurse.finishTreatment(patient, HealthStatus.SICK);
			treatmentWaitingTime = treatmentWaitingTime + random.nextInt(urgentWaitingTime);
			currentNurse.performOtherExamTreatment(patient);
			processTime = processTime + 15;
			currentNurse.finishTreatment(patient, HealthStatus.SICK);
			treatmentWaitingTime = treatmentWaitingTime + random.nextInt(urgentWaitingTime);
			currentNurse.performDischarge(patient);
			processTime = processTime + 5;
			currentNurse.finishTreatment(patient, HealthStatus.SICK);
			treatmentWaitingTime = treatmentWaitingTime + random.nextInt(urgentWaitingTime);
			currentNurse.performBoarding(patient);
			currentNurse.finishTreatment(patient, HealthStatus.HEALTHY);
			patient.setTreatmentTime(processTime);
			patient.setWaitingTreatmentTime(treatmentWaitingTime);
			patient.setTotalWaitingTime();
			patient.setTotalProcessTime();
			System.out.println( "waitForBedTime: " + waitForBedTime + "\n" + "processTime: " + processTime+"\n");
		}else{
			System.out.println("This patient is not supposed to be assigned to concussion process.");
		}
	}
	/**
	 * When this patient gets the health problem of APPENDIX, he/she be assigned a nurse first, and waits to get 
	 * clinical examination. After 15 minutes and waits for a random time, he/she will spend 5 minutes on bloodsampling. Then he/she get InternalMI, this step will
	 * last for 20 minutes, and then he/she will get other exam treatment. 15 minutes later, and  random time waiting 
	 * for boarding, this patient will get healthy and leave the emergency room. 
	 * @param patient the patient who is getting the APPENDIX process treatment steps
	 */
	private void processAppendix(Patient patient){
		if(currentPatient.getHealthProblem() == HealthProblem.APPENDIX){
			ListTracker lt = this.getListTracker();
			Random random = new Random();
			int treatmentWaitingTime = 0;
			Nurse currentNurse = lt.getAvailableNurse();
			treatmentWaitingTime = treatmentWaitingTime + random.nextInt(urgentWaitingTime);
			currentNurse.performClinicalExam(currentPatient);
			System.out.println("Staff operating on "+patient.getName()+" : "+patient.getCurrentDoctor().getStaffName()+", "+patient.getCurrentNurse().getStaffName());
			processTime = processTime + 15;
			currentNurse.finishTreatment(currentPatient, HealthStatus.SICK);
			treatmentWaitingTime = treatmentWaitingTime + random.nextInt(urgentWaitingTime);
			currentNurse.performBloodSampling(currentPatient);
			processTime = processTime + 5;
			currentNurse.finishTreatment(currentPatient, HealthStatus.SICK);
			treatmentWaitingTime = treatmentWaitingTime + random.nextInt(urgentWaitingTime);
			currentNurse.performInternalMI(currentPatient);
			processTime = processTime + 20;
			currentNurse.finishTreatment(currentPatient, HealthStatus.SICK);
			treatmentWaitingTime = treatmentWaitingTime + random.nextInt(urgentWaitingTime);
			currentNurse.performOtherExamTreatment(currentPatient);
			processTime = processTime + 15;
			currentNurse.finishTreatment(currentPatient, HealthStatus.SICK);
			treatmentWaitingTime = treatmentWaitingTime + random.nextInt(urgentWaitingTime);
			currentNurse.performDischarge(currentPatient);
			processTime = processTime + 5;
			currentNurse.finishTreatment(currentPatient, HealthStatus.SICK);
			treatmentWaitingTime = treatmentWaitingTime + random.nextInt(urgentWaitingTime);
			currentNurse.performBoarding(currentPatient);
			currentNurse.finishTreatment(currentPatient, HealthStatus.HEALTHY);
			System.out.println("waitForBedTime: "+waitForBedTime + "\n" +"processTime: "+processTime +"\n");
			patient.setTreatmentTime(processTime);
			patient.setWaitingTreatmentTime(treatmentWaitingTime);
			patient.setTotalWaitingTime();
			patient.setTotalProcessTime();
			//System.out.println(currentPatient.toString());
			//			System.out.println("\n"+"\n");
		}else{
			System.out.println("This patient is not supposed to be assigned to Appendix process.");
		}
	}
	/**
	 * First to check whether the patient is coming in by foot or ambulance, if is by ambulance, he will not get 
	 * triage in the emergency room, he already received triage in ambulance;
	 * After triage, patient will be assigned to the treatment process which his health problem belongs to
	 * @param patient the patient who is getting the whole process
	 */
	public void start(Patient patient){
		if(!patient.isAmbulance()){
			triageTime = 0;
			System.out.println(currentPatient.getName()+" walked into the emergency room.");
			triageProcess(currentPatient);
		}

		if (patient.getHealthProblem()==HealthProblem.APPENDIX){
			waitForBed(patient);
			processTime = 0;
			processAppendix(patient);
		} else if (patient.getHealthProblem()==HealthProblem.BROKEN_ARM){
			waitForBed(patient);
			processTime = 0;
			processBrokenArm(patient);
		} else if (patient.getHealthProblem()==HealthProblem.CONCUSSION){
			waitForBed(patient);
			processTime = 0;
			processConcussion(patient);
		}else{
			System.out.println("Unknown healthProblem, bye." + "\n"+"\n");
		}
		System.out.println(currentPatient.toString());

	}

}
