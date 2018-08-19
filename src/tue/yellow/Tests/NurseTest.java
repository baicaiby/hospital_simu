package tue.yellow.Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import tue.yellow.Bed;
import tue.yellow.Bed.Urgency;
import tue.yellow.Bed.Zone;
import tue.yellow.Doctor;
import tue.yellow.Nurse;
import tue.yellow.Staff.Availability;
import tue.yellow.Patient;
import tue.yellow.ListTracker;
import tue.yellow.Patient.HealthProblem;
import tue.yellow.Patient.HealthStatus;
import tue.yellow.Patient.Status;
import tue.yellow.Patient.TriageUrgency;
/**
 * This class tests whether the class Nurse functions properly with unit tests
 * @author Team Yellow
 */
public class NurseTest {
	/**
	 * This unit test makes sure than when a nurse is created as follows: 
	 * <b>Nurse("name","001")</b>
	 * that the result is that the name of the nurse is "name", and the 
	 * ID is "001". Furthermore, it checks that the initial availability and current
	 * activity are "unknown", and the nurse is initially not assigned to a patient.
	 */
	@Test
	public void Nurse() {
		ListTracker st=new ListTracker();
		Nurse nurse1 = new Nurse(st,"name");
		assertEquals("nameWrong", "name", nurse1.getStaffName());
		assertEquals("Available", Availability.AVAILABLE, nurse1.getStaffAvailability());
		assertEquals("In the staff room", "Unknown", nurse1.getCurrentActivity());
		assertEquals(null,nurse1.getAssignedPatient());
	}
	/**
	 * Tests if the function setStaffName() places the right string at the name of the nurse
	 */
	@Test
	public void setStaffName(){
		ListTracker st=new ListTracker();
		Nurse nurse2 = new Nurse(st);
		nurse2.setStaffName("Shawn Albert");
		assertEquals("Shawn Albert", nurse2.getStaffName());
	}
	
	/**
	 * Tests if the function setCurrentActivity() places the right string at the current activity of the nurse
	 */
	@Test 
	public void setCurrentActivity(){
		ListTracker st=new ListTracker();
		Nurse nurse4 = new Nurse(st);
		nurse4.setCurrentActivity("In the staff room");
		assertEquals("In the staff room", nurse4.getCurrentActivity());
	}
	/**
	 * Tests that a nurse can perform clinical examination if the patient has no nurse yet, the nurse
	 * is also not treating anyone else, the nurse is available, and the patient is assigned to a bed.
	 */
	@Test
	public void performClinicalExamTrue(){
		ListTracker st=new ListTracker();
		Nurse nurse9 = new Nurse(st);
		Doctor doctor1=new Doctor(st);
		doctor1.setStaffAvailability(Availability.AVAILABLE);
		Patient patient5 = new Patient(st, HealthProblem.CONCUSSION);
		Bed bed1=new Bed(st,"bed1",Zone.TRAUMA,Urgency.URGENT);
		nurse9.setStaffAvailability(Availability.AVAILABLE);
		bed1.setAssignedPatient(patient5);
		assertEquals(bed1,patient5.getCurrentBed());
		nurse9.performClinicalExam(patient5);
		assertEquals("patient was wrongly assigned!",patient5,nurse9.getAssignedPatient());
		assertEquals("Available",Availability.NOT_AVAILABLE , nurse9.getStaffAvailability());
		assertEquals(Status.IN_TREATMENT,patient5.getCurrentStatus());
		assertEquals(Availability.NOT_AVAILABLE,doctor1.getStaffAvailability());
		assertEquals("Performing Clinical Exam",doctor1.getCurrentActivity());
		assertEquals("Performing nothing", "Performing Clinical Exam", nurse9.getCurrentActivity());
		assertEquals(patient5, doctor1.getAssignedPatient());
		assertEquals(doctor1,patient5.getCurrentDoctor());
		assertEquals(nurse9,patient5.getCurrentNurse());
	}

	/**
	 * Tests that a nurse cannot perform clinical examination if the nurse is not available
	 */
	@Test
	public void performClinicalExamFalseUnavailable(){
		ListTracker st=new ListTracker();
		Nurse nurse9 = new Nurse( st);
		Doctor doctor1=new Doctor(st);
		Patient patient5 = new Patient(st, HealthProblem.CONCUSSION);
		Bed bed1=new Bed(st,"bed1",Zone.TRAUMA,Urgency.URGENT);
		nurse9.setStaffAvailability(Availability.NOT_AVAILABLE);
		doctor1.setStaffAvailability(Availability.AVAILABLE);
		bed1.setAssignedPatient(patient5);
		assertEquals(bed1,patient5.getCurrentBed());
		nurse9.performClinicalExam(patient5);
		assertEquals("patient was wrongly assigned!",null,nurse9.getAssignedPatient());
		assertEquals("Available", Availability.NOT_AVAILABLE, nurse9.getStaffAvailability());
		assertEquals(Status.WAITING,patient5.getCurrentStatus());
	}
	/**
	 * Tests that a nurse cannot perform clinical examination if the patient is not on a bed
	 */
	@Test
	public void performClinicalExamFalseNoBed(){
		ListTracker st=new ListTracker();
		Nurse nurse9 = new Nurse( st);
		Patient patient5 = new Patient(st, HealthProblem.CONCUSSION);
		Bed bed1=new Bed(st,"bed1",Zone.NO_INJURY,Urgency.URGENT);
		nurse9.setStaffAvailability(Availability.AVAILABLE);
		bed1.setAssignedPatient(patient5);
		Doctor doctor1=new Doctor(st);
		doctor1.setStaffAvailability(Availability.AVAILABLE);
		nurse9.performClinicalExam(patient5);
		assertEquals("patient was wrongly assigned!",null,nurse9.getAssignedPatient());
		assertEquals("Available", Availability.AVAILABLE, nurse9.getStaffAvailability());
		assertEquals(Status.WAITING_FOR_BED,patient5.getCurrentStatus());
	}
	/**
	 * Tests that a nurse cannot perform clinical examination if there is no doctor available
	 */
	@Test
	public void performClinicalExamFalseNoDoctor(){
		ListTracker st=new ListTracker();
		Nurse nurse9 = new Nurse( st);
		Patient patient5 = new Patient(st, HealthProblem.CONCUSSION);
		Bed bed1=new Bed(st,"bed1",Zone.TRAUMA,Urgency.URGENT);
		nurse9.setStaffAvailability(Availability.AVAILABLE);
		bed1.setAssignedPatient(patient5);
		Doctor doctor1=new Doctor(st);
		doctor1.setStaffAvailability(Availability.NOT_AVAILABLE);
		nurse9.performClinicalExam(patient5);
		assertEquals("patient was wrongly assigned!",null,nurse9.getAssignedPatient());
		assertEquals("Available", Availability.AVAILABLE, nurse9.getStaffAvailability());
		assertEquals(Status.WAITING,patient5.getCurrentStatus());
	}
	@Test
	public void performDischargeFalseNoDoctor(){
		ListTracker st=new ListTracker();
		Nurse nurse9 = new Nurse( st);
		Patient patient5 = new Patient(st, HealthProblem.CONCUSSION);
		Bed bed1=new Bed(st,"bed1",Zone.TRAUMA,Urgency.URGENT);
		nurse9.setStaffAvailability(Availability.AVAILABLE);
		bed1.setAssignedPatient(patient5);
		Doctor doctor1=new Doctor(st);
		Doctor doctor2=new Doctor(st);
		doctor1.setStaffAvailability(Availability.NOT_AVAILABLE);
		doctor2.setStaffAvailability(Availability.NOT_AVAILABLE);
		nurse9.performDischarge(patient5);
		assertEquals("patient was wrongly assigned!",null,nurse9.getAssignedPatient());
		assertEquals("Available", Availability.AVAILABLE, nurse9.getStaffAvailability());
		assertEquals(Status.WAITING,patient5.getCurrentStatus());
		assertEquals(null,patient5.getCurrentDoctor());
	}
	/**
	 * Tests that a nurse can perform parameters monitoring if the patient has no nurse yet, the nurse
	 * is also not treating anyone else, the nurse is available, and the patient is assigned to a bed.
	 */
	@Test
	public void performParametersMonitoring(){
		ListTracker st=new ListTracker();
		Nurse nurse9 = new Nurse( st);
		Patient patient5 = new Patient(st, HealthProblem.CONCUSSION);
		Bed bed1=new Bed(st,"bed1",Zone.TRAUMA,Urgency.URGENT);
		nurse9.setStaffAvailability(Availability.AVAILABLE);
		bed1.setAssignedPatient(patient5);
		assertEquals(bed1,patient5.getCurrentBed());
		nurse9.performParametersMonitoring(patient5);
		assertEquals("patient was wrongly assigned!",patient5,nurse9.getAssignedPatient());
		assertEquals("Available", Availability.NOT_AVAILABLE, nurse9.getStaffAvailability());
		assertEquals(Status.IN_TREATMENT,patient5.getCurrentStatus());
		assertEquals("Performing nothing", "Performing Parameters Monitoring", nurse9.getCurrentActivity());
	}
	/**
	 * Tests that a nurse can perform blood sampling if the patient has no nurse yet, the nurse
	 * is also not treating anyone else, the nurse is available, and the patient is assigned to a bed.
	 */
	@Test
	public void performBloodSampling(){
		ListTracker st=new ListTracker();
		Nurse nurse9 = new Nurse( st);
		Patient patient5 = new Patient(st, HealthProblem.CONCUSSION);
		Bed bed1=new Bed(st,"bed1",Zone.TRAUMA,Urgency.URGENT);
		nurse9.setStaffAvailability(Availability.AVAILABLE);
		bed1.setAssignedPatient(patient5);
		assertEquals(bed1,patient5.getCurrentBed());
		nurse9.performBloodSampling(patient5);
		assertEquals("patient was wrongly assigned!",patient5,nurse9.getAssignedPatient());
		assertEquals("Available",Availability.NOT_AVAILABLE, nurse9.getStaffAvailability());
		assertEquals(Status.IN_TREATMENT,patient5.getCurrentStatus());
		assertEquals("Performing nothing", "Performing Blood Sampling", nurse9.getCurrentActivity());
	}
	/**
	 * Tests that a nurse can perform internal medical imaging if the patient has no nurse yet, the nurse
	 * is also not treating anyone else, the nurse is available, and the patient is assigned to a bed.
	 */
	@Test
	public void performInternalMI(){
		ListTracker st=new ListTracker();
		Nurse nurse9 = new Nurse( st);
		Patient patient5 = new Patient(st, HealthProblem.CONCUSSION);
		Bed bed1=new Bed(st,"bed1",Zone.TRAUMA,Urgency.URGENT);
		nurse9.setStaffAvailability(Availability.AVAILABLE);
		bed1.setAssignedPatient(patient5);
		assertEquals(bed1,patient5.getCurrentBed());
		nurse9.performInternalMI(patient5);
		assertEquals("patient was wrongly assigned!",patient5,nurse9.getAssignedPatient());
		assertEquals("Available", Availability.NOT_AVAILABLE, nurse9.getStaffAvailability());
		assertEquals(Status.IN_TREATMENT,patient5.getCurrentStatus());
		assertEquals("Performing nothing", "Performing Internal Medical Imaging", nurse9.getCurrentActivity());
	}
	/**
	 * Tests that a nurse can perform external medical imaging if the patient has no nurse yet, the nurse
	 * is also not treating anyone else, the nurse is available, and the patient is assigned to a bed.
	 */
	@Test
	public void performExternalMI(){
		ListTracker st=new ListTracker();
		Nurse nurse9 = new Nurse( st);
		Patient patient5 = new Patient(st, HealthProblem.CONCUSSION);
		Bed bed1=new Bed(st,"bed1",Zone.TRAUMA,Urgency.URGENT);
		Doctor doctor1=new Doctor(st);
		doctor1.setStaffAvailability(Availability.AVAILABLE);
		nurse9.setStaffAvailability(Availability.AVAILABLE);
		bed1.setAssignedPatient(patient5);
		assertEquals(bed1,patient5.getCurrentBed());
		nurse9.performExternalMI(patient5);
		assertEquals("patient was wrongly assigned!",patient5,nurse9.getAssignedPatient());
		assertEquals("Available", Availability.NOT_AVAILABLE, nurse9.getStaffAvailability());
		assertEquals(Status.IN_TREATMENT,patient5.getCurrentStatus());
		assertEquals("Performing nothing", "Performing External Medical Imaging", nurse9.getCurrentActivity());
	}
	/**
	 * Tests that a nurse can perform medication if the patient has no nurse yet, the nurse
	 * is also not treating anyone else, the nurse is available, and the patient is assigned to a bed.
	 */
	@Test
	public void performMedication(){
		ListTracker st=new ListTracker();
		Nurse nurse9 = new Nurse(st);
		Patient patient5 = new Patient(st, HealthProblem.CONCUSSION);
		Bed bed1=new Bed(st,"bed1",Zone.TRAUMA,Urgency.URGENT);
		nurse9.setStaffAvailability(Availability.AVAILABLE);
		Doctor doctor1=new Doctor(st);
		doctor1.setStaffAvailability(Availability.AVAILABLE);
		bed1.setAssignedPatient(patient5);
		assertEquals(bed1,patient5.getCurrentBed());
		nurse9.performMedication(patient5);
		assertEquals("patient was wrongly assigned!",patient5,nurse9.getAssignedPatient());
		assertEquals("Available", Availability.NOT_AVAILABLE, nurse9.getStaffAvailability());
		assertEquals(Status.IN_TREATMENT,patient5.getCurrentStatus());
		assertEquals("Performing nothing", "Performing Medication", nurse9.getCurrentActivity());
	}
	/**
	 * Tests that a nurse can perform other examination and treatment if the patient has no nurse yet, the nurse
	 * is also not treating anyone else, the nurse is available, and the patient is assigned to a bed.
	 */
	@Test 
	public void performOtherExamTreatment(){
		ListTracker st=new ListTracker();
		Nurse nurse9 = new Nurse(st);
		Patient patient5 = new Patient(st, HealthProblem.CONCUSSION);
		Bed bed1=new Bed(st,"bed1",Zone.TRAUMA,Urgency.URGENT);
		nurse9.setStaffAvailability(Availability.AVAILABLE);
		Doctor doctor1=new Doctor(st);
		doctor1.setStaffAvailability(Availability.AVAILABLE);
		bed1.setAssignedPatient(patient5);
		assertEquals(bed1,patient5.getCurrentBed());
		nurse9.performOtherExamTreatment(patient5);
		assertEquals("patient was wrongly assigned!",patient5,nurse9.getAssignedPatient());
		assertEquals("Available", Availability.NOT_AVAILABLE, nurse9.getStaffAvailability());
		assertEquals(Status.IN_TREATMENT,patient5.getCurrentStatus());
		assertEquals("Performing nothing", "Performing Other Examination and Treatment", nurse9.getCurrentActivity());
	}
	/**
	 * Tests that a nurse can perform discharge if the patient has no nurse yet, the nurse
	 * is also not treating anyone else, the nurse is available, and the patient is assigned to a bed.
	 */
	@Test
	public void performDischarge(){
		ListTracker st=new ListTracker();
		Nurse nurse9 = new Nurse( st);
		Patient patient5 = new Patient(st, HealthProblem.CONCUSSION);
		Bed bed1=new Bed(st,"bed1",Zone.TRAUMA,Urgency.URGENT);
		Doctor doctor1=new Doctor(st);
		doctor1.setStaffAvailability(Availability.AVAILABLE);
		nurse9.setStaffAvailability(Availability.AVAILABLE);
		bed1.setAssignedPatient(patient5);
		assertEquals(bed1,patient5.getCurrentBed());
		nurse9.performDischarge(patient5);
		assertEquals("patient was wrongly assigned!",patient5,nurse9.getAssignedPatient());
		assertEquals("Available", Availability.NOT_AVAILABLE, nurse9.getStaffAvailability());
		assertEquals(Status.IN_DISCHARGE,patient5.getCurrentStatus());
		assertEquals("Performing nothing", "Performing Discharge", nurse9.getCurrentActivity());
	}
	/**
	 * Tests if the method finish treatment correctly releases the patient from the nurse, 
	 * and fills in the correct status at the nurse and patient.
	 * Simultaneously tests if the method releasePatient() works appropriately (as this is 
	 * the method that releases the patient from the nurse).
	 */
	@Test
	public void finishTreatment(){
		ListTracker st=new ListTracker();
		Nurse nurse9 = new Nurse( st);
		Patient patient5 = new Patient(st, HealthProblem.CONCUSSION);
		Bed bed1=new Bed(st,"bed1",Zone.TRAUMA,Urgency.URGENT);
		nurse9.setStaffAvailability(Availability.AVAILABLE);
		bed1.setAssignedPatient(patient5);
		nurse9.performBloodSampling(patient5);
		assertEquals(bed1,patient5.getCurrentBed());
		nurse9.finishTreatment(patient5, HealthStatus.HEALTHY);
		assertEquals("Unavailable",Availability.AVAILABLE, nurse9.getStaffAvailability());
		assertEquals("Performing a Discharge", "In the staff room", nurse9.getCurrentActivity());
		assertEquals("unwaiting", Status.WAITING, patient5.getCurrentStatus());
		assertEquals("unhealthy", HealthStatus.HEALTHY, patient5.getHealthStatus());
		assertEquals("patient should be not assigned", null, nurse9.getAssignedPatient());
	}
	/**
	 * Tests if the method finish triage correctly releases the patient from the nurse, 
	 * and fills in the correct status at the nurse and patient.
	 * Simultaneously tests if the method releasePatient() works appropriately (as this is 
	 * the method that releases the patient from the nurse).
	 */
	@Test
	public void finishTriage(){
		ListTracker st=new ListTracker();
		Nurse nurse14 = new Nurse( st);
		Patient patient10 = new Patient(st);
		nurse14.setStaffAvailability(Availability.AVAILABLE);
		nurse14.performTriage(patient10, HealthProblem.APPENDIX);
		assertEquals(Status.UNDERGO_TRIAGE,patient10.getCurrentStatus());
		assertEquals(nurse14, patient10.getCurrentNurse());
		nurse14.finishTriage(patient10);
		assertEquals("Unavailable", Availability.AVAILABLE, nurse14.getStaffAvailability());
		assertEquals("Performing a Discharge", "In the staff room", nurse14.getCurrentActivity());
		assertEquals("unwaiting", Status.WAITING_FOR_BED, patient10.getCurrentStatus());
		assertEquals("healthy", HealthStatus.SICK, patient10.getHealthStatus());
		assertEquals("patient should be not assigned", null, nurse14.getAssignedPatient());
		assertEquals(TriageUrgency.IMMEDIATE,patient10.getTriageUrgency());
		assertEquals(Zone.NO_INJURY,patient10.getTriageZone());
		assertEquals("should not be assigned to a chair",null,patient10.getCurrentChair());
	}
	
	@Test
	public void nurseList(){
		ListTracker st=new ListTracker();
		Nurse nurse2=new Nurse(st,"nurse2");
		nurse2.setStaffAvailability(Availability.AVAILABLE);
	}
	
	@Test
	public void calmPatientTrue(){
		ListTracker st=new ListTracker();
		Patient p1=new Patient(st, null, "headache");
		Patient p2=new Patient(st, null, "headache");
		Nurse nurse1=new Nurse( st);
		nurse1.setStaffAvailability(Availability.AVAILABLE);
		p1.getViolent();
		p2.getViolent();
		assertEquals(p1,st.getCallNurseList().get(0));
		assertEquals(p2,st.getCallNurseList().get(1));
		nurse1.calmPatient(p1);
		assertEquals("patient is wrongly assigned", p1,nurse1.getAssignedPatient());
		assertEquals("p1 should not be in the callNurse list", p2,st.getCallNurseList().get(0));
	}
	
	@Test
	public void checkOnPatientTrue(){
		ListTracker st = new ListTracker();
		Patient p1=new Patient(st, HealthProblem.BROKEN_ARM);
		Patient p2=new Patient(st, HealthProblem.BROKEN_ARM);
		Nurse nurse1=new Nurse(st);
		nurse1.setStaffAvailability(Availability.AVAILABLE);
		p1.setCurrentStatus(Status.WAITING_FOR_BOARDING);
		p2.setCurrentStatus(Status.WAITING_FOR_BOARDING);
		p1.getSicker();
		p2.getSicker();
		nurse1.checkOnPatient(p1, TriageUrgency.URGENT);
		assertEquals("should not be available", Availability.NOT_AVAILABLE, nurse1.getStaffAvailability());
		assertEquals("Performing a check on a patient", nurse1.getCurrentActivity());
		assertEquals("wrong Urgency", TriageUrgency.URGENT, p1.getTriageUrgency());
		assertEquals("patient is wrongly assigned", p1,nurse1.getAssignedPatient());
		assertEquals("p1 should not be in the callForCheck list", p2,st.getCallForCheckList().get(0));
	}
	
	@Test
	public void finishCheck(){
		ListTracker st=new ListTracker();
		Patient p1=new Patient(st, HealthProblem.BROKEN_ARM);
		Nurse nurse1=new Nurse( st);
		nurse1.setStaffAvailability(Availability.AVAILABLE);
		p1.setCurrentStatus(Status.WAITING_FOR_BOARDING);
		p1.getSicker();
		nurse1.checkOnPatient(nurse1.getListTracker().getCallForCheckList().get(0), TriageUrgency.URGENT);
		nurse1.finishCheck(p1, HealthStatus.SICK);
		assertEquals("Unavailable", Availability.AVAILABLE, nurse1.getStaffAvailability());
		assertEquals("wrong activity", "In the staff room", nurse1.getCurrentActivity());
		assertEquals("should not be unwaiting", Status.WAITING, p1.getCurrentStatus());
		assertEquals("patient should be not assigned", null, nurse1.getAssignedPatient());
		assertEquals(TriageUrgency.URGENT,p1.getTriageUrgency());
		assertEquals(HealthStatus.SICK, p1.getHealthStatus());
	}
	

}
