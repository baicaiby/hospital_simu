package tue.yellow.Tests;
import tue.yellow.Bed;
import tue.yellow.Patient;
import tue.yellow.Bed.Zone;
import tue.yellow.Bed.Urgency;
import tue.yellow.Patient.TriageUrgency;
import tue.yellow.Staff.Availability;
import tue.yellow.ListTracker;
import tue.yellow.Patient.Status;
import tue.yellow.Patient.HealthProblem;
import tue.yellow.Patient.HealthStatus;
import static org.junit.Assert.*;

import org.junit.Test;


import tue.yellow.Chair;
import tue.yellow.Doctor;
import tue.yellow.Nurse;

/**
 * This class tests whether the class Patient functions propertly with unit tests
 * @author Team Yellow
 *
 */
public class PatientTest {
	// Test that the constructor works
	/**
	 * This unit test makes sure that when a Patient is created as follows:
	 * <b>Patient(name,ID,health status, complaints, triage urgency, triage zone)</b>
	 * that the result is that correct data members are set, and that upon creation, the 
	 * patient is not linked to a bed or chair immediately.
	 */
	@Test
	public void Patient() {
		ListTracker lt = new ListTracker();
		Patient p1=new Patient(lt);
		assertEquals("ambulance not known",false,p1.isAmbulance());
		assertEquals("entertime false",0,p1.getEnterTime());
		assertEquals("name wrong","unknown",p1.getName());
		assertEquals("health status wrong",HealthStatus.SICK,p1.getHealthStatus());
		assertEquals("health problem wrong",HealthProblem.UNKNOWN,p1.getHealthProblem());
		assertEquals("complaints wrong","unknown",p1.getComplaints());
		assertEquals("triage urgency wrong",null,p1.getTriageUrgency());
		assertEquals("triage zone wrong",null,p1.getTriageZone());
		assertEquals("current status wrong",Status.ENTERED_ER,p1.getCurrentStatus());
		assertEquals("current bed filled?",null,p1.getCurrentBed());
		assertEquals("current chair filled?",null,p1.getCurrentChair());

	}
	
	@Test
	public void PatientEnterTime() {
		ListTracker lt = new ListTracker();
		Patient p1=new Patient(lt,5);
		assertEquals("ambulance not known",false,p1.isAmbulance());
		assertEquals("entertime false",5,p1.getEnterTime());
		assertEquals("name wrong","unknown",p1.getName());
		assertEquals("health status wrong",HealthStatus.SICK,p1.getHealthStatus());
		assertEquals("health problem wrong",HealthProblem.UNKNOWN,p1.getHealthProblem());
		assertEquals("complaints wrong","unknown",p1.getComplaints());
		assertEquals("triage urgency wrong",null,p1.getTriageUrgency());
		assertEquals("triage zone wrong",null,p1.getTriageZone());
		assertEquals("current status wrong",Status.ENTERED_ER,p1.getCurrentStatus());
		assertEquals("current bed filled?",null,p1.getCurrentBed());
		assertEquals("current chair filled?",null,p1.getCurrentChair());

	}
	
	@Test
	public void PatientAmbulanceAppendix() {
		ListTracker lt = new ListTracker();
		Patient p1=new Patient(lt,5,true,HealthProblem.APPENDIX);
		assertEquals("ambulance not known",true,p1.isAmbulance());
		assertEquals("entertime false",5,p1.getEnterTime());
		assertEquals("name wrong","unknown",p1.getName());
		assertEquals("health status wrong",HealthStatus.SICK,p1.getHealthStatus());
		assertEquals("health problem wrong",HealthProblem.APPENDIX,p1.getHealthProblem());
		assertEquals("complaints wrong","unknown",p1.getComplaints());
		assertEquals("triage urgency wrong",TriageUrgency.IMMEDIATE,p1.getTriageUrgency());
		assertEquals("triage zone wrong",Zone.NO_INJURY,p1.getTriageZone());
		assertEquals("bed urgency wrong",Urgency.URGENT,p1.getBedUrgency());
		assertEquals("current status wrong",Status.WAITING_FOR_BED,p1.getCurrentStatus());
		assertEquals("current bed filled?",null,p1.getCurrentBed());
		assertEquals("current chair filled?",null,p1.getCurrentChair());
	}
	
	@Test
	public void PatientAmbulanceBrokenArm() {
		ListTracker lt = new ListTracker();
		Patient p1=new Patient(lt,5,true,HealthProblem.BROKEN_ARM);
		assertEquals("ambulance not known",true,p1.isAmbulance());
		assertEquals("entertime false",5,p1.getEnterTime());
		assertEquals("name wrong","unknown",p1.getName());
		assertEquals("health status wrong",HealthStatus.SICK,p1.getHealthStatus());
		assertEquals("health problem wrong",HealthProblem.BROKEN_ARM,p1.getHealthProblem());
		assertEquals("complaints wrong","unknown",p1.getComplaints());
		assertEquals("triage urgency wrong",TriageUrgency.STANDARD,p1.getTriageUrgency());
		assertEquals("triage zone wrong",Zone.TRAUMA,p1.getTriageZone());
		assertEquals("bed urgency wrong",Urgency.NOT_URGENT,p1.getBedUrgency());
		assertEquals("current status wrong",Status.WAITING_FOR_BED,p1.getCurrentStatus());
		assertEquals("current bed filled?",null,p1.getCurrentBed());
		assertEquals("current chair filled?",null,p1.getCurrentChair());

	}
	
	@Test
	public void PatientAmbulanceConcussion() {
		ListTracker lt = new ListTracker();
		Patient p1=new Patient(lt,5,true,HealthProblem.CONCUSSION);
		assertEquals("ambulance not known",true,p1.isAmbulance());
		assertEquals("entertime false",5,p1.getEnterTime());
		assertEquals("name wrong","unknown",p1.getName());
		assertEquals("health status wrong",HealthStatus.SICK,p1.getHealthStatus());
		assertEquals("health problem wrong",HealthProblem.CONCUSSION,p1.getHealthProblem());
		assertEquals("complaints wrong","unknown",p1.getComplaints());
		assertEquals("triage urgency wrong",TriageUrgency.IMMEDIATE,p1.getTriageUrgency());
		assertEquals("triage zone wrong",Zone.TRAUMA,p1.getTriageZone());
		assertEquals("bed urgency wrong",Urgency.URGENT,p1.getBedUrgency());
		assertEquals("current status wrong",Status.WAITING_FOR_BED,p1.getCurrentStatus());
		assertEquals("current bed filled?",null,p1.getCurrentBed());
		assertEquals("current chair filled?",null,p1.getCurrentChair());

	}
	
	@Test
	public void PatientAmbulanceUnknownProblem() {
		ListTracker lt = new ListTracker();
		Patient p1=new Patient(lt,5,true,HealthProblem.UNKNOWN);
		assertEquals("ambulance not known",true,p1.isAmbulance());
		assertEquals("entertime false",5,p1.getEnterTime());
		assertEquals("name wrong","unknown",p1.getName());
		assertEquals("health status wrong",HealthStatus.SICK,p1.getHealthStatus());
		assertEquals("health problem wrong",HealthProblem.UNKNOWN,p1.getHealthProblem());
		assertEquals("complaints wrong","unknown",p1.getComplaints());
		assertEquals("triage urgency wrong",null,p1.getTriageUrgency());
		assertEquals("triage zone wrong",null,p1.getTriageZone());
		assertEquals("bed urgency wrong",null,p1.getBedUrgency());
		assertEquals("current status wrong",Status.ENTERED_ER,p1.getCurrentStatus());
		assertEquals("current bed filled?",null,p1.getCurrentBed());
		assertEquals("current chair filled?",null,p1.getCurrentChair());

	}
	
	@Test
	public void PatientNameNoAmbulance() {
		ListTracker lt = new ListTracker();
		Patient p1=new Patient(lt,5,"james bond");
		assertEquals("ambulance not known",false,p1.isAmbulance());
		assertEquals("entertime false",5,p1.getEnterTime());
		assertEquals("name wrong","james bond",p1.getName());
		assertEquals("health status wrong",HealthStatus.SICK,p1.getHealthStatus());
		assertEquals("health problem wrong",HealthProblem.UNKNOWN,p1.getHealthProblem());
		assertEquals("complaints wrong","unknown",p1.getComplaints());
		assertEquals("triage urgency wrong",null,p1.getTriageUrgency());
		assertEquals("triage zone wrong",null,p1.getTriageZone());
		assertEquals("bed urgency wrong",null,p1.getBedUrgency());
		assertEquals("current status wrong",Status.ENTERED_ER,p1.getCurrentStatus());
		assertEquals("current bed filled?",null,p1.getCurrentBed());
		assertEquals("current chair filled?",null,p1.getCurrentChair());

	}
	
	@Test
	public void PatientComplaintsNoAmbulance() {
		ListTracker lt = new ListTracker();
		Patient p1=new Patient(lt,5,"james bond","headache");
		assertEquals("ambulance not known",false,p1.isAmbulance());
		assertEquals("entertime false",5,p1.getEnterTime());
		assertEquals("name wrong","james bond",p1.getName());
		assertEquals("health status wrong",HealthStatus.SICK,p1.getHealthStatus());
		assertEquals("health problem wrong",HealthProblem.UNKNOWN,p1.getHealthProblem());
		assertEquals("complaints wrong","headache",p1.getComplaints());
		assertEquals("triage urgency wrong",null,p1.getTriageUrgency());
		assertEquals("triage zone wrong",null,p1.getTriageZone());
		assertEquals("bed urgency wrong",null,p1.getBedUrgency());
		assertEquals("current status wrong",Status.ENTERED_ER,p1.getCurrentStatus());
		assertEquals("current bed filled?",null,p1.getCurrentBed());
		assertEquals("current chair filled?",null,p1.getCurrentChair());

	}
	
	@Test
	public void PatientNameAmbulance() {
		ListTracker lt = new ListTracker();
		Patient p1=new Patient(lt,true,HealthProblem.APPENDIX,"james bond");
		assertEquals("ambulance not known",true,p1.isAmbulance());
		assertEquals("entertime false",0,p1.getEnterTime());
		assertEquals("name wrong","james bond",p1.getName());
		assertEquals("health status wrong",HealthStatus.SICK,p1.getHealthStatus());
		assertEquals("health problem wrong",HealthProblem.APPENDIX,p1.getHealthProblem());
		assertEquals("complaints wrong","unknown",p1.getComplaints());
		assertEquals("triage urgency wrong",TriageUrgency.IMMEDIATE,p1.getTriageUrgency());
		assertEquals("triage zone wrong",Zone.NO_INJURY,p1.getTriageZone());
		assertEquals("bed urgency wrong",Urgency.URGENT,p1.getBedUrgency());
		assertEquals("current status wrong",Status.WAITING_FOR_BED,p1.getCurrentStatus());
		assertEquals("current bed filled?",null,p1.getCurrentBed());
		assertEquals("current chair filled?",null,p1.getCurrentChair());

	}
	
	@Test
	public void PatientComplaintsAmbulance() {
		ListTracker lt = new ListTracker();
		Patient p1=new Patient(lt,true,HealthProblem.APPENDIX,"james bond","headache");
		assertEquals("ambulance not known",true,p1.isAmbulance());
		assertEquals("entertime false",0,p1.getEnterTime());
		assertEquals("name wrong","james bond",p1.getName());
		assertEquals("health status wrong",HealthStatus.SICK,p1.getHealthStatus());
		assertEquals("health problem wrong",HealthProblem.APPENDIX,p1.getHealthProblem());
		assertEquals("complaints wrong","headache",p1.getComplaints());
		assertEquals("triage urgency wrong",TriageUrgency.IMMEDIATE,p1.getTriageUrgency());
		assertEquals("triage zone wrong",Zone.NO_INJURY,p1.getTriageZone());
		assertEquals("bed urgency wrong",Urgency.URGENT,p1.getBedUrgency());
		assertEquals("current status wrong",Status.WAITING_FOR_BED,p1.getCurrentStatus());
		assertEquals("current bed filled?",null,p1.getCurrentBed());
		assertEquals("current chair filled?",null,p1.getCurrentChair());

	}
	// Test that the patient will have the status 'waiting for bed' when he comes in by ambulance
	/**
	 * Tests if the function setName() places the right string at the name of the patient
	 */
	// Test that setName gives the right string
	@Test
	public void setName(){
		ListTracker lt = new ListTracker();
		Patient p1=new Patient(lt);
		p1.setName("james");
		assertEquals("name is wrong","james",p1.getName());
		
	}

	/**
	 * Tests if the function setHealthStatus() places the right string at the health status of the patient
	 */
	// Test that setHealthStatus gives the right string
	@Test
	public void setHealthStatus(){
		ListTracker lt = new ListTracker();
		Patient p1=new Patient(lt);
		p1.setHealthStatus(p1,HealthStatus.SICK);
		assertEquals("health status is wrong",HealthStatus.SICK,p1.getHealthStatus());

	}
	/**
	 * Tests that if the health status is "dead", the patient is released from all resources
	 * or staff assigned to him/her.
	 */

	// Test that setHealthStatus 'kills' a patient if it is entered as 'dead', meaning patient is no longer
	// assigned to doctors, nurses, beds or chairs
	@Test
	public void setHealthStatusDead(){
		ListTracker lt = new ListTracker();
		Patient p1=new Patient(lt, true, HealthProblem.CONCUSSION);
		Doctor doc1=new Doctor(lt);
		Nurse nurse1=new Nurse(lt);
		Bed bed1=new Bed(lt, "bed1", Zone.TRAUMA,Urgency.URGENT);
		Chair chair1=new Chair(lt);
		p1.goToChair();
		p1.goToBed();
		doc1.setAssignedPatient(p1);
		nurse1.setAssignedPatient(p1);
		assertEquals(doc1,p1.getCurrentDoctor());
		assertEquals(nurse1,p1.getCurrentNurse());
		assertEquals(bed1,p1.getCurrentBed());
		assertEquals(p1,bed1.getAssignedPatient());
		p1.setHealthStatus(p1,HealthStatus.DEAD);
		assertEquals("patient hasn't died",HealthStatus.DEAD,p1.getHealthStatus());
		assertEquals("patient hasn't died",Status.GONE,p1.getCurrentStatus());
		assertEquals("patient still assigned to doctor",null,p1.getCurrentDoctor());
		assertEquals("patient still assigned to doctor",null,doc1.getAssignedPatient());
		assertEquals("patient still assigned to nurse",null,p1.getCurrentNurse());
		assertEquals("patient still assigned to nurse",null,nurse1.getAssignedPatient());
		assertEquals("patient still has a bed",null,p1.getCurrentBed());
		assertEquals("patient still has a chair",null,p1.getCurrentChair());
		assertEquals("patient still has a bed",null,bed1.getAssignedPatient());
		assertEquals("patient still has a chair",null,chair1.getAssignedPatient());
	}
	/**
	 * Tests if the function setComplaints() places the right string at the complaints of the patient
	 */
	// Test that setComplaints gives the right string
	@Test
	public void setComplaints(){
		ListTracker lt = new ListTracker();
		Patient p1=new Patient(lt);
		p1.setComplaints("throat hurts");
		assertEquals("complaints are wrong","throat hurts",p1.getComplaints());

	}
	/**
	 * Tests if the function setTriageUrgency() places the right string at the complaints of the patient,
	 * and that it deducts the right bed urgency for the patient.
	 * <p>
	 * Immediate = Urgent<br>
	 * Very urgent = Urgent<br>
	 * Urgent = Urgent<br>
	 * Standard = Not urgent<br>
	 * Non-urgent = Not urgent <br>
	 */
	// Test that setTriageUrgency gives the right string, and deducts the right bedUrgency
	@Test
	public void setTriageUrgency(){
		ListTracker lt = new ListTracker();
		Patient p1 =new Patient(lt);
		p1.setTriageUrgency(TriageUrgency.IMMEDIATE);
		assertEquals("urgency is wrong",TriageUrgency.IMMEDIATE,p1.getTriageUrgency());
		assertEquals("bedurgency is wrong",Urgency.URGENT,p1.getBedUrgency());

		Patient p2 =new Patient(lt);
		p2.setTriageUrgency(TriageUrgency.VERY_URGENT);
		assertEquals("urgency is wrong",TriageUrgency.VERY_URGENT,p2.getTriageUrgency());
		assertEquals("bedurgency is wrong",Urgency.URGENT,p2.getBedUrgency());

		Patient p3 =new Patient(lt);
		p3.setTriageUrgency(TriageUrgency.URGENT);
		assertEquals("urgency is wrong",TriageUrgency.URGENT,p3.getTriageUrgency());
		assertEquals("bedurgency is wrong",Urgency.URGENT,p3.getBedUrgency());

		Patient p4 =new Patient(lt);
		p4.setTriageUrgency(TriageUrgency.STANDARD);
		assertEquals("urgency is wrong",TriageUrgency.STANDARD,p4.getTriageUrgency());
		assertEquals("bedurgency is wrong",Urgency.NOT_URGENT,p4.getBedUrgency());

		Patient p5 =new Patient(lt);
		p5.setTriageUrgency(TriageUrgency.NON_URGENT);
		assertEquals("urgency is wrong",TriageUrgency.NON_URGENT,p5.getTriageUrgency());
		assertEquals("bedurgency is wrong",Urgency.NOT_URGENT,p5.getBedUrgency());

		
		
	}

	/**
	 * Tests if the function setTriageZone() places the right string at the triage zone of the patient
	 */
	// Test that setTriageZone gives the right string
	@Test
	public void setTriageZone(){
		ListTracker lt = new ListTracker();
		Patient p1=new Patient(lt);
		p1.setTriageZone(Zone.TRAUMA);
		assertEquals("zone is wrong",Zone.TRAUMA,p1.getTriageZone());
	}
	/**
	 * Tests if the function setCurrentStatus() places the right string at the current status of the patient
	 */
	// Test that setCurrentStatus gives the right string
	@Test
	public void setCurrentStatus(){
		ListTracker lt = new ListTracker();
		Patient p1=new Patient(lt);
		p1.setCurrentStatus(Status.WAITING);
		assertEquals("status is wrong",Status.WAITING,p1.getCurrentStatus());
	}
	/**
	 * Tests if the function setCurrentBed() places the right bed at the current bed of the patient,
	 * only when the patient is waiting for a bed and the bed urgency and zone match that of the patient.
	 */
	// Test that setCurrentBed gives the right bed
	@Test
	public void setCurrentBed(){
		ListTracker lt = new ListTracker();
		Patient p1=new Patient(lt,HealthProblem.CONCUSSION);
		Bed bed2=new Bed(lt, "bed2",Zone.TRAUMA,Urgency.URGENT);
		bed2.setAssignedPatient(p1);
		assertEquals("bed is not assigned",p1,bed2.getAssignedPatient());
		assertEquals("patient is not assigned",bed2,p1.getCurrentBed());
		assertEquals("patient is not waiting",Status.WAITING,p1.getCurrentStatus());
	}
	/**
	 * Tests that a patient cannot be assigned to a bed if he is not waiting for a bed (hasn't had triage yet)
	 */
	// Test that a patient can't be assigned to a bed when he is not waiting for a bed
	@Test
	public void setCurrentBedNotWaitingForBed(){
		ListTracker lt = new ListTracker();
		Patient p1=new Patient(lt, HealthProblem.CONCUSSION);
		Bed bed3=new Bed(lt, "bed3",Zone.TRAUMA,Urgency.URGENT);
		p1.setCurrentStatus(Status.WAITING);
		bed3.setAssignedPatient(p1);
		assertEquals("bed is not assigned",null,bed3.getAssignedPatient());
		assertEquals("patient is not assigned",null,p1.getCurrentBed());
	}
	/**
	 * Tests that a patient can only be assigned to one bed.
	 */
	// Test that a patient can't be assigned to two beds
	@Test
	public void setCurrentBedTwoBedsAssigned(){
		ListTracker lt = new ListTracker();
		Patient p1=new Patient(lt, HealthProblem.CONCUSSION);
		Bed bed3=new Bed(lt, "bed3",Zone.TRAUMA,Urgency.URGENT);
		Bed bed4=new Bed(lt, "bed4",Zone.TRAUMA,Urgency.URGENT);
		bed3.setAssignedPatient(p1);
		bed4.setAssignedPatient(p1);
		assertEquals("bed is not assigned",p1,bed3.getAssignedPatient());
		assertEquals("patient is not assigned",bed3,p1.getCurrentBed());
		assertEquals("bed is assigned?",null,bed4.getAssignedPatient());

	}
	/**
	 * Tests if the function setCurrentChair() places the right chair at the current chair of the patient.
	 */
	// Test that setCurrentChair gives the right chair
	@Test
	public void setCurrentChairTrue(){
		ListTracker lt = new ListTracker();
		Patient p1=new Patient(lt);
		Chair chair1=new Chair(lt,"chair1");
		chair1.setAssignedPatient(p1);
		assertEquals("chair is not assigned",p1,chair1.getAssignedPatient());
		assertEquals("patient is not assigned",chair1,p1.getCurrentChair());

	}
	/**
	 * Tests that a patient cannot be assigned to a chair if he is already in a bed.
	 */
	// Test that a patient can't be assigned to a chair when he is already in a bed
	@Test
	public void setCurrentChairAlreadyInBed(){
		ListTracker lt = new ListTracker();
		Patient p1=new Patient(lt, HealthProblem.CONCUSSION);
		Bed bed3=new Bed(lt, "bed3",Zone.TRAUMA,Urgency.URGENT);
		Chair chair2=new Chair (lt,"chair2");
		bed3.setAssignedPatient(p1);
		chair2.setAssignedPatient(p1);
		assertEquals("bed is not assigned",p1,bed3.getAssignedPatient());
		assertEquals("patient is not assigned",bed3,p1.getCurrentBed());
		assertEquals("chair is assigned",null,chair2.getAssignedPatient());
		assertEquals("patient is assigned to chair",null,p1.getCurrentChair());

	}
	/**
	 * Tests that a patient cannot be assigned to two chair simultaneously.
	 */
	// Test that a patient can't be assigned to two chairs
	@Test
	public void setCurrentChairTwoChairsAssigned(){
		ListTracker lt = new ListTracker();
		Patient p1=new Patient(lt, HealthProblem.CONCUSSION);
		Chair chair3=new Chair(lt,"chair3");
		Chair chair4=new Chair(lt,"chair4");
		assertEquals(chair3,lt.getAvailableChair());
		assertEquals(null,chair3.getAssignedPatient());
		assertEquals(null,p1.getCurrentChair());
		assertEquals(null,p1.getCurrentBed());
		p1.goToChair();
		p1.goToChair();
		
		assertEquals("patient is not assigned",chair3,p1.getCurrentChair());
		assertEquals("chair is not assigned",p1,chair3.getAssignedPatient());
		assertEquals("chair is assigned?",null,chair4.getAssignedPatient());

	}
	/**
	 * Tests if the function setCurrentDoctor() places the right doctor at the current doctor of the patient.
	 */
	// Test that a patient is set to the right doctor
	@Test
	public void setCurrentDoctorTrue(){
		ListTracker lt = new ListTracker();
		Patient p1=new Patient(lt);
		Doctor doc1=new Doctor(lt);
		doc1.setAssignedPatient(p1);
		assertEquals("patient is not assigned",p1,doc1.getAssignedPatient());
		assertEquals("doctor is not assigned",doc1,p1.getCurrentDoctor());

	}
	/**
	 * Tests that a patient cannot be assigned to two doctors simultaneously.
	 */
	// Test that a patient can't be assigned to two doctors

	@Test
	public void setCurrentDoctorFalseTwo(){
		ListTracker lt = new ListTracker();
		Patient p1=new Patient(lt);
		Doctor doc1=new Doctor(lt);
		Doctor doc2=new Doctor (lt);
		doc1.setAssignedPatient(p1);
		doc2.setAssignedPatient(p1);
		assertEquals("patient is not assigned",p1,doc1.getAssignedPatient());
		assertEquals("doctor is not assigned",doc1,p1.getCurrentDoctor());
		assertEquals("patient is assigned again",null,doc2.getAssignedPatient());
		assertEquals("doctor 2 has a patient",doc1,p1.getCurrentDoctor());
	}
	/**
	 * Tests if the function setCurrentNurse() places the right nurse at the current nurse of the patient.
	 */
	// Test that a patient is set to the right nurse
	@Test
	public void setCurrentNurseTrue(){
		ListTracker lt = new ListTracker();
		Patient p1=new Patient(lt);
		Nurse nurse1=new Nurse(lt);
		nurse1.setAssignedPatient(p1);
		assertEquals("patient is not assigned",p1,nurse1.getAssignedPatient());
		assertEquals("doctor is not assigned",nurse1,p1.getCurrentNurse());

	}
	/**
	 * Tests that a patient cannot be assigned to two nurses simultaneously.
	 */
	// Test that a patient can't be assigned to two nurses
	public void setCurrentNurseFalseTwo(){
		ListTracker lt = new ListTracker();
		Patient p1=new Patient(lt);
		Nurse nurse1=new Nurse(lt);
		Nurse nurse2=new Nurse (lt);
		nurse1.setAssignedPatient(p1);
		nurse2.setAssignedPatient(p1);
		assertEquals("patient is not assigned",p1,nurse1.getAssignedPatient());
		assertEquals("doctor is not assigned",nurse1,p1.getCurrentNurse());
		assertEquals("patient is assigned again",null,nurse2.getAssignedPatient());
		assertEquals("doctor 2 has a patient",nurse1,p1.getCurrentNurse());
	}
	/**
	 * Tests that when a patient leaves the emergency room, no more resources or staff is related
	 * to him/her.
	 */
	// Test that when patient leaves emergency room, no more doctors, nurses, chairs/beds related to him
	@Test
	public void leaveEmergencyDepartmentTrue(){
		ListTracker lt = new ListTracker();
		Patient p1=new Patient(lt, HealthProblem.CONCUSSION);
		Nurse nurse1=new Nurse(lt);
		Doctor doctor1=new Doctor(lt);
		Bed bed1=new Bed(lt, "bed1",Zone.TRAUMA,Urgency.URGENT);
		Chair chair1=new Chair(lt,"chair1");
		chair1.setAssignedPatient(p1);
		bed1.setAssignedPatient(p1);
		assertEquals("patient is not waiting",Status.WAITING,p1.getCurrentStatus());
		nurse1.performClinicalExam(p1);
		assertEquals("patient is waiting",Status.IN_TREATMENT,p1.getCurrentStatus());
		assertEquals(p1,nurse1.getAssignedPatient());
		assertEquals(doctor1, p1.getCurrentDoctor());
		nurse1.finishTreatment(p1);
		p1.leaveEmergencyRoom(p1);
		assertEquals("patient still has a bed",null,p1.getCurrentBed());
		assertEquals("patient still has a chair",null,p1.getCurrentChair());
		assertEquals("patient still has a doctor",null,p1.getCurrentDoctor());
		assertEquals("patient still has a nurse",null,p1.getCurrentNurse());
		assertEquals("patient has wrong status",Status.LEFT_ER, p1.getCurrentStatus());

	}
	/** 
	 * Tests that a patient can't leave the emergency room when he is in treatment.
	 */
	// Test that a patient can't leave the emergency room when he is in treatment
	@Test
	public void leaveEmergencyDepartmentFalseInTreatment(){
		ListTracker lt = new ListTracker();
		Patient p1=new Patient(lt, HealthProblem.CONCUSSION);
		Nurse nurse1=new Nurse(lt);
		Doctor doctor1=new Doctor(lt);
		Bed bed1=new Bed(lt,"bed1",Zone.TRAUMA,Urgency.URGENT);
		Chair chair1=new Chair(lt,"chair1");
		chair1.setAssignedPatient(p1);
		bed1.setAssignedPatient(p1);
		assertEquals("patient is not waiting",Status.WAITING,p1.getCurrentStatus());
		doctor1.setAssignedPatient(p1);
		nurse1.setAssignedPatient(p1);
		p1.leaveEmergencyRoom(p1);
		assertEquals("patient has left bed",bed1,p1.getCurrentBed());
		assertEquals("patient still has a chair",null,p1.getCurrentChair());
		assertEquals("patient has no doctor anymore",doctor1,p1.getCurrentDoctor());
		assertEquals("patient still has a nurse",nurse1,p1.getCurrentNurse());
		assertEquals("patient has wrong status",Status.IN_TREATMENT, p1.getCurrentStatus());


	}
	/** 
	 * Tests that a patient gets violent, complains changes.
	 */
	@Test
	public void getViolentTrue(){
		ListTracker lt = new ListTracker();
		Patient p1=new Patient(lt, "james bond","headache");
		p1.getViolent();
		assertEquals("wrong complains", "headache. Violent", p1.getComplaints());
		assertEquals("Should be in call nurse list", p1, lt.getCallNurseList().get(0));
	}

	
	@Test
	public void getSickerTrue(){
		ListTracker lt = new ListTracker();
		Patient p1=new Patient(lt, "james bond","headache");
		p1.getSicker();
		assertEquals("should be in callNurse list", p1, lt.getCallForCheckList().get(0));
	}
	/**
	 * Tests that when a patient dies, all the related resources and staff have become available again.
	 */
	// Test that the patient has no more resources related to him when he dies
	@Test
	public void patientDieTrue(){
		ListTracker lt = new ListTracker();
		Patient p1=new Patient(lt, HealthProblem.CONCUSSION);
		Nurse nurse1=new Nurse(lt);
		Doctor doctor1=new Doctor(lt);
		Bed bed1=new Bed(lt,"bed1",Zone.TRAUMA,Urgency.URGENT);
		assertEquals(Zone.TRAUMA,bed1.getZone());
		assertEquals(Zone.TRAUMA,p1.getTriageZone());
		assertEquals(Urgency.URGENT,p1.getBedUrgency());
		assertEquals(bed1,lt.getAvailableTraumaUrgentBed());
		assertEquals(Status.WAITING_FOR_BED,p1.getCurrentStatus());
		Chair chair1=new Chair(lt,"chair1");
		chair1.setAssignedPatient(p1);
		assertEquals(Status.WAITING_FOR_BED,p1.getCurrentStatus());
		bed1.setAssignedPatient(p1);
		doctor1.setAssignedPatient(p1);
		nurse1.setAssignedPatient(p1);
		assertEquals(bed1,p1.getCurrentBed());
		assertEquals(doctor1,p1.getCurrentDoctor());
		assertEquals(nurse1,p1.getCurrentNurse());
		assertEquals(p1,nurse1.getAssignedPatient());
		assertEquals(p1,doctor1.getAssignedPatient());
		p1.patientDie(p1);
		assertEquals("bed still has patient",null,bed1.getAssignedPatient());
		assertEquals("patient has left bed",null,p1.getCurrentBed());
		assertEquals("patient still has a chair",null,p1.getCurrentChair());
		assertEquals("patient has no doctor anymore",null,p1.getCurrentDoctor());
		assertEquals("patient still has a nurse",null,p1.getCurrentNurse());
		assertEquals("patient has wrong status",Status.GONE, p1.getCurrentStatus());

		assertEquals("chair still has patient",null,chair1.getAssignedPatient());
		assertEquals("doctor still has patient",null,doctor1.getAssignedPatient());
		assertEquals("nurse still has patient",null,nurse1.getAssignedPatient());
		assertEquals(Availability.AVAILABLE,doctor1.getStaffAvailability());
		assertEquals(Availability.AVAILABLE,nurse1.getStaffAvailability());

	}
}
