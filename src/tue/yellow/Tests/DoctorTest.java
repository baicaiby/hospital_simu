package tue.yellow.Tests;
import tue.yellow.Bed;
import tue.yellow.Patient;
import tue.yellow.ListTracker;
import tue.yellow.Bed.Zone;
import tue.yellow.Bed.Urgency;
import tue.yellow.Staff.Availability;
import tue.yellow.Patient.Status;
import tue.yellow.Patient.HealthProblem;
import tue.yellow.Patient.HealthStatus;
import static org.junit.Assert.*;
/**
 * This class tests whether the class Doctor functions properly with unit tests
 * @author Team Yellow
 */

import org.junit.Test;
import tue.yellow.Doctor;
import tue.yellow.Nurse;

public class DoctorTest {
	/**
	 * This unit test makes sure than when a doctor is created as follows: 
	 * <b>Doctor("name","001")</b>
	 * that the result is that the name of the doctor is "name", and the 
	 * ID is "001". Furthermore, it checks that the initial availability and current
	 * activity are "unknown", and the doctor is initially not assigned to a patient.
	 */
	@Test
	public void Doctor() {
		ListTracker st=new ListTracker();
		Doctor doctor1 = new Doctor(st,"name");
		assertEquals("nameWrong", "name", doctor1.getStaffName());
		assertEquals("Available", Availability.AVAILABLE, doctor1.getStaffAvailability());
		assertEquals("In the staff room", "Unknown", doctor1.getCurrentActivity());
		assertEquals(null,doctor1.getAssignedPatient());
	}
	/**
	 * Tests if the function setStaffName() places the right string at the name of the doctor
	 */
	@Test
	public void setStaffName(){
		ListTracker st=new ListTracker();
		Doctor doctor2 = new Doctor( st);
		doctor2.setStaffName("Iris Chan");
		assertEquals("Iris Chan", doctor2.getStaffName());
	}
	/**
	 * Tests if the function setCurrentActivity() places the right string at the current activity of the doctor
	 */
	@Test 
	public void setCurrentActivity(){
		ListTracker st=new ListTracker();
		Doctor doctor4 = new Doctor( st);
		doctor4.setCurrentActivity("In the staff room");
		assertEquals("In the staff room", doctor4.getCurrentActivity());
	}
	/**
	 * Tests that a doctor can perform clinical examination if the patient has no doctor yet, the doctor
	 * is also not treating anyone else, the doctor is available, and the patient is assigned to a bed.
	 */
	@Test
	public void performClinicalExamTrue(){
		ListTracker st=new ListTracker();
		Doctor doctor9 = new Doctor( st);
		Patient patient5 = new Patient(st, "p1",HealthProblem.CONCUSSION);
		Bed bed1=new Bed(st,"bed1",Zone.TRAUMA,Urgency.URGENT);
		Nurse nurse1 = new Nurse(st);
		bed1.setAssignedPatient(patient5);
		assertEquals(bed1,patient5.getCurrentBed());
		doctor9.performClinicalExam(patient5);
		assertEquals("patient was wrongly assigned!",patient5,doctor9.getAssignedPatient());
		assertEquals("Available", Availability.NOT_AVAILABLE, doctor9.getStaffAvailability());
		assertEquals(Status.IN_TREATMENT,patient5.getCurrentStatus());
		assertEquals("Performing nothing", "Performing Clinical Exam", doctor9.getCurrentActivity());
	}
	/**
	 * Tests that a doctor cannot perform clinical examination if the doctor is not available
	 */
	@Test
	public void performClinicalExamFalseUnavailable(){
		ListTracker st=new ListTracker();
		Doctor doctor9 = new Doctor( st);
		Patient patient5 = new Patient(st, "p1",HealthProblem.CONCUSSION);
		Bed bed1=new Bed(st,"bed1",Zone.TRAUMA,Urgency.URGENT);
		doctor9.setStaffAvailability(Availability.NOT_AVAILABLE);
		bed1.setAssignedPatient(patient5);
		assertEquals(bed1,patient5.getCurrentBed());
		doctor9.performClinicalExam(patient5);
		assertEquals("patient was wrongly assigned!",null,doctor9.getAssignedPatient());
		assertEquals("Available", Availability.NOT_AVAILABLE, doctor9.getStaffAvailability());
		assertEquals(Status.WAITING,patient5.getCurrentStatus());
	}
	/**
	 * Tests that a doctor cannot perform clinical examination if the patient is not on a bed
	 */
	@Test
	public void performClinicalExamFalseNoBed(){
		ListTracker st=new ListTracker();
		Doctor doctor9 = new Doctor( st);
		Patient patient5 = new Patient(st, "p1",HealthProblem.CONCUSSION);
		doctor9.setStaffAvailability(Availability.AVAILABLE);
		assertEquals(null,patient5.getCurrentBed());
		doctor9.performClinicalExam(patient5);
		assertEquals("patient was wrongly assigned!",null,doctor9.getAssignedPatient());
		assertEquals("Available", Availability.AVAILABLE, doctor9.getStaffAvailability());
		assertEquals(Status.WAITING_FOR_BED,patient5.getCurrentStatus());
	}
	/**
	 * Tests that a doctor can perform external medical imaging if the patient has no doctor yet, the doctor
	 * is also not treating anyone else, the doctor is available, and the patient is assigned to a bed.
	 */
	@Test
	public void performExternalMI(){
		ListTracker st=new ListTracker();
		Doctor doctor9 = new Doctor( st);
		Patient patient5 = new Patient(st, "p1",HealthProblem.CONCUSSION);
		Bed bed1=new Bed(st,"bed1",Zone.TRAUMA,Urgency.URGENT);
		Nurse nurse1 = new Nurse(st);
		bed1.setAssignedPatient(patient5);
		assertEquals(bed1,patient5.getCurrentBed());
		doctor9.performExternalMI(patient5);
		assertEquals("patient was wrongly assigned!",patient5,doctor9.getAssignedPatient());
		assertEquals("Available",Availability.NOT_AVAILABLE, doctor9.getStaffAvailability());
		assertEquals(Status.IN_TREATMENT,patient5.getCurrentStatus());
		assertEquals("Performing nothing", "Performing External Medical Imaging", doctor9.getCurrentActivity());

	}
	/**
	 * Tests that a doctor can perform medication if the patient has no doctor yet, the doctor
	 * is also not treating anyone else, the doctor is available, and the patient is assigned to a bed.
	 */
	@Test
	public void performMedication(){
		ListTracker st=new ListTracker();
		Doctor doctor9 = new Doctor( st);
		Patient patient5 = new Patient(st, "p1",HealthProblem.CONCUSSION);
		Bed bed1=new Bed(st,"bed1",Zone.TRAUMA,Urgency.URGENT);
		Nurse nurse1 = new Nurse(st);
		bed1.setAssignedPatient(patient5);
		assertEquals(bed1,patient5.getCurrentBed());
		doctor9.performMedication(patient5);
		assertEquals("patient was wrongly assigned!",patient5,doctor9.getAssignedPatient());
		assertEquals("Available", Availability.NOT_AVAILABLE, doctor9.getStaffAvailability());
		assertEquals(Status.IN_TREATMENT,patient5.getCurrentStatus());
		assertEquals("Performing nothing", "Performing Medication", doctor9.getCurrentActivity());

	}
	/**
	 * Tests that a doctor can perform other examincation and treatment if the patient has no doctor yet, the doctor
	 * is also not treating anyone else, the doctor is available, and the patient is assigned to a bed.
	 */
	@Test 
	public void performOtherExamTreatment(){
		ListTracker st=new ListTracker();
		Doctor doctor9 = new Doctor( st);
		Patient patient5 = new Patient(st, "p1",HealthProblem.CONCUSSION);
		Bed bed1=new Bed(st,"bed1",Zone.TRAUMA,Urgency.URGENT);
		Nurse nurse1 = new Nurse(st);
		bed1.setAssignedPatient(patient5);
		assertEquals(bed1,patient5.getCurrentBed());
		doctor9.performOtherExamTreatment(patient5);
		assertEquals("patient was wrongly assigned!",patient5,doctor9.getAssignedPatient());
		assertEquals("Available", Availability.NOT_AVAILABLE, doctor9.getStaffAvailability());
		assertEquals(Status.IN_TREATMENT,patient5.getCurrentStatus());
		assertEquals("Performing nothing", "Performing Other Examination and Treatment", doctor9.getCurrentActivity());

	}
	/**
	 * Tests that a doctor can perform discharge if the patient has no doctor yet, the doctor
	 * is also not treating anyone else, the doctor is available, and the patient is assigned to a bed.
	 * Furthermore, when discharge is performed on a patient that is still sick, the patient will then
	 * wait for boarding (to another unit in the hospital).
	 */
	@Test
	public void performDischarge(){
		ListTracker st=new ListTracker();
		Doctor doctor9 = new Doctor( st);
		Patient patient5 = new Patient(st, "p1",HealthProblem.CONCUSSION);
		Bed bed1=new Bed(st,"bed1",Zone.TRAUMA,Urgency.URGENT);
		Nurse nurse1 = new Nurse(st);
		bed1.setAssignedPatient(patient5);
		assertEquals(bed1,patient5.getCurrentBed());
		doctor9.performDischarge(patient5);
		assertEquals("patient was wrongly assigned!",patient5,doctor9.getAssignedPatient());
		assertEquals("Available",Availability.NOT_AVAILABLE, doctor9.getStaffAvailability());
		assertEquals(Status.IN_DISCHARGE,patient5.getCurrentStatus());
		assertEquals("Performing nothing", "Performing Discharge", nurse1.getCurrentActivity());
		assertEquals("Performing nothing", "Performing Discharge", doctor9.getCurrentActivity());

	}
	
	/**
	 * Tests if the method finish treatment correctly releases the patient from the doctor, 
	 * and fills in the correct status at the doctor. 
	 * Simultaneously tests if the method releasePatient() works appropriately (as this is 
	 * the method that releases the patient from the doctor).
	 */
	@Test
	public void finishTreatment(){
		ListTracker st=new ListTracker();
		Doctor doctor9 = new Doctor(st);
		Patient patient5 = new Patient(st, "p1",HealthProblem.CONCUSSION);
		Bed bed1=new Bed(st,"bed1",Zone.TRAUMA,Urgency.URGENT);
		Nurse nurse1 = new Nurse(st);
		bed1.setAssignedPatient(patient5);
		doctor9.performClinicalExam(patient5);
		assertEquals(bed1,patient5.getCurrentBed());
		doctor9.finishTreatment(patient5, HealthStatus.HEALTHY);
		assertEquals("Unavailable", Availability.AVAILABLE, doctor9.getStaffAvailability());
		assertEquals("Performing a Discharge", "In the staff room", doctor9.getCurrentActivity());
		assertEquals("not waiting", Status.WAITING, patient5.getCurrentStatus());
		assertEquals("unhealthy", HealthStatus.HEALTHY, patient5.getHealthStatus());
		assertEquals("patient should be not assigned", null, doctor9.getAssignedPatient());
	}
	/**
	 * Tests if the method finish treatment correctly releases the patient from the doctor
	 * when he has been discharged (as a healthy patient). 
	 */
	@Test
	public void finishTreatmentHealthyDischarge(){
		ListTracker st=new ListTracker();
		Doctor doctor9 = new Doctor(st);
		Patient patient5 = new Patient(st, "p1",HealthProblem.CONCUSSION);
		Bed bed1=new Bed(st,"bed1",Zone.TRAUMA,Urgency.URGENT);
		Nurse nurse1=new Nurse(st);
		bed1.setAssignedPatient(patient5);
		nurse1.performDischarge(patient5);
		assertEquals(bed1,patient5.getCurrentBed());
		assertEquals(doctor9,patient5.getCurrentDoctor());
		assertEquals(patient5,doctor9.getAssignedPatient());
		assertEquals(nurse1,patient5.getCurrentNurse());
		assertEquals(patient5,nurse1.getAssignedPatient());
		nurse1.finishTreatment(patient5, HealthStatus.HEALTHY);
		assertEquals("Unavailable", Availability.AVAILABLE, doctor9.getStaffAvailability());
		assertEquals("Performing a Discharge", "In the staff room", doctor9.getCurrentActivity());
		assertEquals("not waiting", Status.LEFT_ER, patient5.getCurrentStatus());
		assertEquals("unhealthy", HealthStatus.HEALTHY, patient5.getHealthStatus());
		assertEquals("patient should be not assigned", null, doctor9.getAssignedPatient());
		assertEquals(Availability.AVAILABLE,nurse1.getStaffAvailability());
		assertEquals(null,nurse1.getAssignedPatient());
		assertEquals(null,patient5.getCurrentNurse());
	}
	/**
	 * Tests if the method finish treatment correctly releases the patient from the doctor
	 * when he has been discharged (as a healthy patient). 
	 */
	@Test
	public void finishTreatmentSickDischarge(){
		ListTracker st=new ListTracker();
		Doctor doctor9 = new Doctor(st);
		Patient patient5 = new Patient(st, "p1",HealthProblem.CONCUSSION);
		Bed bed1=new Bed(st,"bed1",Zone.TRAUMA,Urgency.URGENT);
		Nurse nurse1 = new Nurse(st);
		bed1.setAssignedPatient(patient5);
		doctor9.performDischarge(patient5);
		assertEquals(patient5,doctor9.getAssignedPatient());
		assertEquals(doctor9,patient5.getCurrentDoctor());
		assertEquals(bed1,patient5.getCurrentBed());
		doctor9.finishTreatment(patient5, HealthStatus.SICK);
		assertEquals("Unavailable", Availability.AVAILABLE, doctor9.getStaffAvailability());
		assertEquals("Performing a Discharge", "In the staff room", doctor9.getCurrentActivity());
		assertEquals("not waiting", Status.WAITING_FOR_BOARDING, patient5.getCurrentStatus());
		assertEquals("healthy", HealthStatus.SICK, patient5.getHealthStatus());
		assertEquals("patient should be not assigned", null, doctor9.getAssignedPatient());
	}
}
