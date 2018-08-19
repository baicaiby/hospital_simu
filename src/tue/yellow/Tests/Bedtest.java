package tue.yellow.Tests;
import tue.yellow.*;
import static org.junit.Assert.*;

import org.junit.Test;

import tue.yellow.Bed.Zone;
import tue.yellow.Bed.Urgency;
import tue.yellow.Patient.TriageUrgency;
import tue.yellow.Patient.HealthProblem;
import tue.yellow.Patient.Status;
/**
 * This class tests whether the class Bed functions properly using unit tests.
 * @author Team Yellow
 *
 */
public class Bedtest {
	// Test if the constructor works
	/**
	 * This unit test makes sure that when a bed is created as follows:
	 * <b>Bed("name","zone","urgency")</b>
	 * that the result is that the name of the bed is "name", 
	 * the zone is "zone", the urgency level is "urgency"
	 */
	@Test
	public void Bed() {
		ListTracker lt = new ListTracker();
		Bed bed1=new Bed(lt,"name1",Zone.TRAUMA,Urgency.URGENT);
		assertEquals("name wrong!","name1",bed1.getName());
		assertEquals("zone wrong!",Zone.TRAUMA,bed1.getZone());
		assertEquals("urgency level wrong!",Urgency.URGENT,bed1.getUrgencyLevel());
	}
	
	// Test if setName places the right string at the data member
	/**
	 * Tests if the function setName() places the right string at the name of the bed
	 */
	@Test
	public void setName(){
		ListTracker lt = new ListTracker();
		Bed bed2=new Bed(lt);
		bed2.setName("shark bed");
		assertEquals("shark bed", bed2.getName());
		assertNotEquals("bed",bed2.getName());
	}
	
	// Test if setZone places the right string at the data member
	/**
	 * Tests if the function setZone() places the right string at the zone of the bed
	 */
	@Test
	public void setZone(){
		ListTracker lt = new ListTracker();
		Bed bed3=new Bed(lt);
		bed3.setZone(Zone.TRAUMA);
		assertEquals(Zone.TRAUMA,bed3.getZone());
	}
	
	// Test if setUrgencyLevel places the right string at the data member
	/**
	 * Tests if the function setUrgencyLevel() places the right string at the urgency level of the bed
	 */
	@Test 
	public void setUrgencyLevel(){
		ListTracker lt = new ListTracker();
		Bed bed4 = new Bed (lt);
		bed4.setUrgencyLevel(Urgency.NOT_URGENT);
		assertEquals(Urgency.NOT_URGENT,bed4.getUrgencyLevel());
	}
	
	// Test if the patient and bed are correctly related to each other, status = waiting
	/**
	 * Tests that setAssignedPatient(patient) indeed assigns the patient to the bed, 
	 * and also that the bed is in turn assigned to the patient. Furthermore, it test that 
	 * the new status of the patient is <i>"waiting"</i>, and that a patient is no longer 
	 * associated with a chair.
	 */
	@Test
	public void setAssignedPatientTrue(){
		ListTracker lt = new ListTracker();
		Patient p1=new Patient(lt,HealthProblem.CONCUSSION);
		Chair chair1=new Chair(lt,"chair1");
		chair1.setAssignedPatient(p1);
		Bed bed5=new Bed (lt,"bed5",Zone.TRAUMA,Urgency.URGENT);
		bed5.setAssignedPatient(p1);
		assertEquals("patient was wrongly assigned!",p1,bed5.getAssignedPatient());
		assertEquals("patient has no bed!",bed5,p1.getCurrentBed());
		assertEquals("patient is not waiting for treatment",Status.WAITING,p1.getCurrentStatus());
		assertEquals("patient still has a chair",null,p1.getCurrentChair());
		
	}
	
	// Test that patient with a different zone cannot match with a bed in another zone
	/**
	 * Tests that a patient can only be assigned to a bed with the same zone, so that 
	 * a bed is not assigned to a patient (returns null) and a patient is not assigned
	 * to a bed (returns null) when this is not the case.
	 */
	@Test
	public void setAssignedPatientFalseZone(){
		ListTracker lt = new ListTracker();
		Patient p2=new Patient(lt,HealthProblem.CONCUSSION);
		Bed bed6=new Bed (lt,"bed6",Zone.NO_INJURY,Urgency.URGENT);
		bed6.setAssignedPatient(p2);
		assertEquals("patient was wrongly assigned!",null,bed6.getAssignedPatient());
		assertEquals("patient has a bed!",null,p2.getCurrentBed());
	}
	
	// Test that patient with a different urgency level cannot match with a bed in another level
	/**
	 * Tests that a patient can only be assigned to a bed with the same urgency level, so that 
	 * a bed is not assigned to a patient (returns null) and a patient is not assigned
	 * to a bed (returns null) when this is not the case.
	 */
	@Test
	public void setAssignedPatientFalseUrgency(){
		ListTracker lt = new ListTracker();
		Patient p3=new Patient(lt,HealthProblem.APPENDIX);
		Bed bed7=new Bed (lt,"bed7",Zone.TRAUMA,Urgency.URGENT);
		bed7.setAssignedPatient(p3);
		assertEquals("patient was wrongly assigned!",null,bed7.getAssignedPatient());
		assertEquals("patient has a bed!",null,p3.getCurrentBed());
		
	}
	
	// Test that patient that is not waiting for bed cannot match with a bed
	/**
	 * Tests that a patient can only be assigned to a bed when his status is <i>"waiting for bed"</i>,
	 * so that a bed is not assigned to a patient (returns null) and a patient is not assigned to a bed 
	 * (returns null) when this is not the case.
	 */
	@Test
	public void setAssignedPatientFalseStatus(){
		ListTracker listTracker = new ListTracker();
		Patient p2=new Patient(listTracker,HealthProblem.CONCUSSION);
		p2.setCurrentStatus(Status.ENTERED_ER);
		Bed bed6=new Bed (listTracker,"bed6",Zone.TRAUMA,Urgency.URGENT);
		bed6.setAssignedPatient(p2);
		assertEquals("patient was wrongly assigned!",null,bed6.getAssignedPatient());
		assertEquals("patient has a bed!",null,p2.getCurrentBed());
	}
	
	// Test that patient cannot be assigned to two beds
	/**
	 * Tests that a patient can only be assigned to one bed, and that if he/she is already
	 * assigned to a bed, he will remain assigned to that bed, and not switch to another bed.
	 * Thus, this other bed remains empty.
	 */
	@Test
	public void setAssignedPatientFalseAlreadyAssigned(){
		ListTracker listTracker = new ListTracker();
		Patient p3=new Patient(listTracker,HealthProblem.CONCUSSION);
		Bed bed6=new Bed (listTracker,"bed6",Zone.TRAUMA,Urgency.URGENT);
		bed6.setAssignedPatient(p3);
		Bed bed7=new Bed (listTracker,"bed7",Zone.TRAUMA,Urgency.URGENT);
		bed7.setAssignedPatient(p3);
		assertEquals("patient already had a bed!",null,bed7.getAssignedPatient());
		assertEquals("patient has different bed?",bed6,p3.getCurrentBed());
			
	}
	
	// Test that bed cannot be assigned to two patients
	/**
	 * Tests that there can only be one patient in a bed. The second patient assigned to the bed
	 * (the one with a patient already assigned to it) will remain unassigned to a bed.
	 */
		@Test
		public void setAssignedPatientFalseAlreadyFull(){
			ListTracker listTracker = new ListTracker();
			Patient p3=new Patient(listTracker,HealthProblem.CONCUSSION);
			Patient p4=new Patient(listTracker,HealthProblem.CONCUSSION);
			Bed bed6=new Bed (listTracker,"bed6",Zone.TRAUMA,Urgency.URGENT);
			bed6.setAssignedPatient(p3);
			bed6.setAssignedPatient(p4);
			assertEquals("patient already had a bed!",p3,bed6.getAssignedPatient());
			assertEquals("patient has a bed?",bed6,p3.getCurrentBed());
			assertEquals("patient 4 is assigned to bed",null,p4.getCurrentBed());
				
		}
	
	// Test that patient is correctly released, status is 'left the emergency room'
	/**
	 * Tests that when a patient is released from the bed, the status of that patient
	 * changes into <i>"left the emergency room"</i>, that the patient is no longer 
	 * assigned to the bed, and that the bed is no longer assigned to the patient.
	 */
	@Test 
	public void releasePatientTrue(){
		ListTracker listTracker = new ListTracker();
		Patient p4= new Patient(listTracker,HealthProblem.CONCUSSION);
		Bed bed8=new Bed (listTracker,"bed8",Zone.TRAUMA,Urgency.URGENT);
		bed8.setAssignedPatient(p4);
		assertEquals("patient is not assigned?!",p4,bed8.getAssignedPatient());
		bed8.releasePatient(p4);
		assertEquals("patient is not released!",null,bed8.getAssignedPatient());
		assertEquals("patient current resource has not been updated!",null,p4.getCurrentBed());
		assertEquals("patient has not left the emergency room?!",Status.LEFT_ER,p4.getCurrentStatus());
	}
	
	// Test that you can't release a patient that is not assigned
	/**
	 * Tests that a patient can only be released from a bed to which it was assigned. 
	 */
	@Test
	public void releasePatientNotAssigned(){
		ListTracker listTracker = new ListTracker();
		Patient p5=new Patient(listTracker,HealthProblem.CONCUSSION);
		Patient p6=new Patient(listTracker,HealthProblem.CONCUSSION);
		Bed bed9=new Bed(listTracker,"bed9",Zone.TRAUMA,Urgency.URGENT);
		Bed bed8=new Bed(listTracker,"bed8",Zone.TRAUMA,Urgency.URGENT);
		bed9.setAssignedPatient(p6);
		bed8.setAssignedPatient(p5);
		assertEquals("patient is not assigned?!",p6,bed9.getAssignedPatient());
		bed9.releasePatient(p5);
		assertEquals("patient is released?!",p6,bed9.getAssignedPatient());
		assertEquals("patient is released?!",bed9,p6.getCurrentBed());
		assertEquals("patient is not waiting anymore?!",Status.WAITING,p5.getCurrentStatus());
		assertEquals("patient 5 now has a bed?!",bed8,p5.getCurrentBed());
	}
}
