package tue.yellow.Tests;
import tue.yellow.Bed;
import tue.yellow.Patient;
import tue.yellow.ListTracker;
import tue.yellow.Bed.Zone;
import tue.yellow.Bed.Urgency;
import tue.yellow.Patient.HealthProblem;
import tue.yellow.Patient.TriageUrgency;
import static org.junit.Assert.*;

import org.junit.Test;

import tue.yellow.Chair;

/**
 * This class tests whether the class Chair functions properly using unit tests.
 * @author Tema Yellow
 *
 */
public class ChairTest {
	// Test that the constructor works
	/**
	 * This unit tests makes sure that when a chair is created
	 * as follows: <b> Chair("name")</b>, that the result is
	 * that the name of the bed is "name".
	 */
	@Test
	public void Chair() {
		ListTracker st = new ListTracker();
		Chair chair1=new Chair(st, "name1");
		assertEquals("name wrong!","name1",chair1.getName());
	}
	
	// Test that setName places the right string
	/**
	 * Tests if the method setName() places the right string at the name of the chair
	 */
	@Test
	public void setName(){
		ListTracker st = new ListTracker();
		Chair chair2=new Chair(st);
		chair2.setName("shark chair");
		assertEquals("shark chair", chair2.getName());
	}

	// Test that a patient and chair are correctly related
	/**
	 * Tests that setAssignedPatient(patient) indeed assigns a patient
	 * to the chair, and also that the chair is in turn assigned to the patient.
	 */
	@Test
	public void setAssignedPatientTrue(){
		ListTracker lt = new ListTracker();
		Patient p1=new Patient(lt,true,HealthProblem.CONCUSSION);
		Chair chair3=new Chair (lt);
		chair3.setAssignedPatient(p1);
		assertEquals("patient was not assigned!",p1,chair3.getAssignedPatient());
		assertEquals("patient has no chair!",chair3,p1.getCurrentChair());
		
	}
	
	// Test that a patient can only be assigned to one chair
	/**
	 * Tests that a patient can only be assigned to one chair. When it is tried 
	 * to assign this patient to another chair, he/she will remain in his/her
	 * original chair.
	 */
	@Test
	public void setAssignedPatientFalseAlreadyAssigned(){
		ListTracker lt = new ListTracker();
		Patient p2=new Patient(lt);
		Chair chair4=new Chair(lt);
		chair4.setAssignedPatient(p2);
		Chair chair5=new Chair (lt);
		chair5.setAssignedPatient(p2);
		assertEquals("patient already had a chair!",null,chair5.getAssignedPatient());
		assertEquals("patient is in a new chair?",chair4,p2.getCurrentChair());
		assertEquals("patient is in a new chair?",p2,chair4.getAssignedPatient());
			
	}
	// Test that a patient can only be assigned to a chair if he is not in a bed
	/**
	 * Tests that a patient can only be assigned to a chair if he is not in a bed. 
	 * A chair is not assigned to the patient (returns null) when the patient is already
	 * in a bed.
	 */
	@Test
	public void setAssignedPatientFalseBed(){
		ListTracker lt = new ListTracker();
		Patient p2=new Patient(lt, true,HealthProblem.CONCUSSION,"John");
		Bed bed1=new Bed(lt, "bed1",Zone.TRAUMA,Urgency.URGENT);
		bed1.setAssignedPatient(p2);
		Chair chair4=new Chair(lt, "chair4");
		assertEquals(bed1,p2.getCurrentBed());
		chair4.setAssignedPatient(p2);
		assertEquals(null,p2.getCurrentChair());
		assertEquals(null,chair4.getAssignedPatient());


	}
		
	// Test that a patient is released from a chair, and that the chair is also cleared from the patient's data member
	/**
	 * Tests that when the method releasePatient() is called, the chair is also
	 * cleared from the patient's information in currentChair.
	 */
	@Test 
	public void releasePatientTrue(){
		ListTracker lt = new ListTracker();
		Patient p3= new Patient(lt);
		Chair chair6=new Chair (lt,"chair6");
		chair6.setAssignedPatient(p3);
		assertEquals("patient is not assigned?!",p3,chair6.getAssignedPatient());
		chair6.releasePatient(p3);
		assertEquals("patient is not released!",null,chair6.getAssignedPatient());
		assertEquals("patient current resource has not been updated!",null,p3.getCurrentChair());
	}
	
	// Test that a patient cannot be released from a chair that he is not assigned to
	/**
	 * Tests that a patient can only be released from a chair to which it was assigned.
	 */
	@Test
	public void releasePatientNotAssigned(){
		ListTracker lt = new ListTracker();
		Patient p4=new Patient(lt);
		Patient p5=new Patient(lt);
		Chair chair7=new Chair(lt,"chair7");
		Chair chair8=new Chair (lt,"chair8");
		chair7.setAssignedPatient(p4);
		chair8.setAssignedPatient(p5);
		assertEquals("patient is not assigned?!",p4,chair7.getAssignedPatient());
		assertEquals("patient is not assigned?!",p5,chair8.getAssignedPatient());
		chair7.releasePatient(p5);
		assertEquals("patient is released?!",p4,chair7.getAssignedPatient());
		assertEquals("patient is released?!",p5,chair8.getAssignedPatient());
	}
}
	

