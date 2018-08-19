package tue.yellow;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Date;
import tue.yellow.Bed.Urgency;
import tue.yellow.Bed.Zone;
import tue.yellow.Staff.Availability;
import tue.yellow.Patient.HealthStatus;
import tue.yellow.Patient.TriageUrgency;
/**
 * The class ListTracker keeps track of all the objects in the emergency department.
 * For each type of object (Nurse, Doctor, Patient, Bed, Chair), lists are kept, which 
 * allows for certain methods to for example find the first available doctor.<br><p>
 * One could view two separate ListTracker objects as two different Emergency Rooms. 
 * @author Team Yellow
 *
 */
public class ListTracker {

	private List<Patient> patients;
	private List<Doctor> doctors;
	private List<Nurse> nurses;
	private List<Bed> beds;
	private List<Chair> chairs;
	private List<Patient> notProcessedPatients;
	private List<Patient> callNurseList;
	private List<Patient> callForCheckList;
	private List<Nurse> availableNurseList;
	private List<Doctor> availableDoctorList;
	private List<Bed> availableBedList;
	private List<Chair> availableChairList;
	private List<Bed> availableTraumaUrgentBedList;
	private List<Bed> availableNoInjuryUrgentBedList;
	private List<Bed> availableTraumaNotUrgentBedList;
	private List<Bed> availableNoInjuryNotUrgentBedList;

	/**
	 * Creates a ListTracker object, by creating several lists:<br>
	 * <ul>
	 * <li> list of all doctors
	 * <li> list of all nurses
	 * <li> list of all patients
	 * <li> list of all patients that have called for a nurse
	 * <li> list of all patients that have gotten sicker, and need to be checked by a nurse
	 * <li> list of all available nurses
	 * <li> list of all available doctors
	 * </ul>
	 * 
	 */
	public ListTracker() {
		doctors=new LinkedList<Doctor>();
		nurses = new LinkedList<Nurse>();
		patients = new LinkedList<Patient>();
		beds = new LinkedList<Bed>();
		chairs = new LinkedList<Chair>();
		callNurseList = new LinkedList<Patient>();
		callForCheckList = new LinkedList<Patient>();
		availableNurseList=new LinkedList<Nurse>();
		availableDoctorList=new LinkedList<Doctor>();
		availableBedList = new LinkedList<Bed>();
		availableChairList = new LinkedList<Chair>();
		availableTraumaUrgentBedList = new LinkedList<Bed>();
		availableNoInjuryNotUrgentBedList = new LinkedList<Bed>();
		availableNoInjuryUrgentBedList = new LinkedList<Bed>();
		availableTraumaNotUrgentBedList = new LinkedList<Bed>();
		notProcessedPatients = new LinkedList<Patient>();
	}

	public List<Patient> getPatients() {
		return patients;
	}

	/**
	 * Gets the list of all patients that have called for a nurse
	 * @return list of all patients that have called for a nurse
	 */
	public List<Patient> getCallNurseList() {
		return callNurseList;
	}

	/**
	 * Adds a patient in the list of patients that have called for a nurse
	 * @param patient patient to be added to the list
	 */
	public void setCallNurseList(Patient patient) {
		this.callNurseList.add(patient);
	}

	/**
	 * Removes a patient from the list of patients that have called for a nurse
	 * @param patient patient to be removed from the list
	 */
	public void removeCallNurseList(Patient patient) {
		this.callNurseList.remove(patient);
	}

	/**
	 * Gets the list of all patients that require another check by the nurse
	 * @return list of all patients that require another check by the nurse
	 */

	public List<Patient> getCallForCheckList() {
		return callForCheckList;
	}

	/**
	 * Adds a patient to the list of all patients that require another check by the nurse
	 * @param patient patient to be added
	 */
	public void setCallForCheckList(Patient patient) {
		this.callForCheckList.add(patient);
	}

	/**
	 * Removes a patient from the list of all patients that require another check by the nurse
	 * @param patient patient to be removed
	 */
	public void removeCallForCheckList(Patient patient) {
		this.callForCheckList.remove(patient);
	}
	/**
	 * Adds a nurse to the list of all nurses
	 * @param nurse nurse to be added
	 */
	public void addNurse(Nurse nurse){
		nurses.add(nurse);
		nurse.setListTracker(this);
	}
	/**
	 * Adds a doctor to the list of all doctors
	 * @param doctor doctor to be added
	 */
	public void addDoctor(Doctor doctor){
		doctors.add(doctor);
		doctor.setListTracker(this);
	}
	/**
	 * Adds a patient to the list of all patients
	 * @param patient patient to be added
	 */
	public void addPatient(Patient patient){
		patients.add(patient);
		notProcessedPatients.add(patient);
		patient.setListTracker(this);
	}
	/**
	 * Adds a patient to the list of all patients
	 * @param patient patient to be added
	 */
	//	public void addNotProcessedPatient(Patient patient){
	//		notProcessedPatients.add(patient);
	//		patient.setListTracker(this);
	//	}
	/**
	 * Adds a Bed to the list of all beds
	 * @param bed bed to be added
	 */
	public void addBed(Bed bed){
		beds.add(bed);
		bed.setListTracker(this);
	}
	/**
	 * Adds a chair to the list of all chairs
	 * @param chair chair to be added
	 */
	public void addChair(Chair chair){
		chairs.add(chair);
		chair.setListTracker(this);
	}
	/**
	 * Gets the list of all not processed patients
	 */
	public List<Patient> getNotProcessedPatients() {
		return notProcessedPatients;
	}

	/**
	 * When a patient has been processed, removes this patient from notProcessedPatients list
	 * patients remained in this list are up to get process
	 * @param patient patient to be removed
	 */
	public void removeNotProcessedPatient(Patient patient) {
		this.notProcessedPatients.remove(patient);
	}
	/**
	 * Gets the list of the beds
	 * @return the list of the beds
	 */
	public List<Bed> getBeds() {
		return beds;
	}
	/**
	 * Gets the list of available urgent trauma beds
	 * @return the list of the available beds
	 */
	public List<Bed> getAvailableTraumaUrgentBedList() {
		return availableTraumaUrgentBedList;
	}
	/**
	 * Gets the list of available urgent no_injury beds
	 * @return the list of the available beds
	 */
	public List<Bed> getAvailableNoInjuryUrgentBedList() {
		return availableNoInjuryUrgentBedList;
	}
	/**
	 * Gets the list of available non_urgent trauma beds
	 * @return the list of the available beds
	 */
	public List<Bed> getAvailableTraumaNotUrgentBedList() {
		return availableTraumaNotUrgentBedList;
	}
	/**
	 * Gets the list of available non_urgent no_injury beds
	 * @return the list of the available beds
	 */
	public List<Bed> getAvailableNoInjuryNotUrgentBedList() {
		return availableNoInjuryNotUrgentBedList;
	}
	/**
	 * Gets the list of available nurses
	 * @return the list of available nurses
	 */
	public List<Nurse> getAvailableNurseList() {
		return availableNurseList;
	}
	/**
	 * Gets the list of available doctors
	 * @return the list of available doctors
	 */
	public List<Doctor> getAvailableDoctorList() {
		return availableDoctorList;
	}
	/**
	 * Gets the list of available beds
	 * @return the list of available beds
	 */
	public List<Bed> getAvailableBedList() {
		return availableBedList;
	}
	/** 
	 * Gets the list of available chairs
	 * @return the list of available chairs
	 */
	public List<Chair> getAvailableChairList() {
		return availableChairList;
	}

	/**
	 * Creates a list of available doctors, and gets the first available doctor in the list
	 * @return the first available doctor
	 */
	public Doctor getAvailableDoctor(){
		for (Doctor d:doctors){
			if (d.getStaffAvailability()==Availability.AVAILABLE){
				availableDoctorList.add(d);
			}
		}
		Doctor freeDoctor;
		if (availableDoctorList.isEmpty()){
			freeDoctor=null;
		} else {
			freeDoctor=availableDoctorList.get(0);
		}

		return freeDoctor;
	}
	/**
	 * Creates a list of available nurses, and gets the first available nurse in the list
	 * @return the first available nurse
	 */
	public Nurse getAvailableNurse(){
		for (Nurse n:nurses){
			if (n.getStaffAvailability()==Availability.AVAILABLE){
				availableNurseList.add(n);
			}
		}
		Nurse freeNurse;
		if (availableNurseList.isEmpty()){
			freeNurse=null;
		} else {
			freeNurse=availableNurseList.get(0);
		}
		return freeNurse;
	}
	/**
	 * Creates a list of available urgent trauma beds, and gets the first available bed in the list
	 * @return the first available bed
	 */


	public Bed getAvailableTraumaUrgentBed(){
		for (Bed b:beds){
			if (b.getAssignedPatient()==null && 
					b.getUrgencyLevel()==Bed.Urgency.URGENT && 
					b.getZone()==Bed.Zone.TRAUMA){
				availableTraumaUrgentBedList.add(b);
			}
		}
		Bed freeBed;
		if (availableTraumaUrgentBedList.isEmpty()){
			freeBed=null;
		} else {
			freeBed=availableTraumaUrgentBedList.get(0);
		}

		return freeBed;
	}
	/**
	 * Creates a list of available urgent no_injury beds, and gets the first available bed in the list
	 * @return the first available bed
	 */
	public Bed getAvailableNoInjuryUrgentBed(){
		for (Bed b:beds){
			if (b.getAssignedPatient()==null && 
					b.getUrgencyLevel()==Bed.Urgency.URGENT&& 
					b.getZone()==Bed.Zone.NO_INJURY){
				availableNoInjuryUrgentBedList.add(b);
			}
		}
		Bed freeBed;
		if (availableNoInjuryUrgentBedList.isEmpty()){
			freeBed=null;
		} else {
			freeBed=availableNoInjuryUrgentBedList.get(0);
		}

		return freeBed;
	}
	/**
	 * Creates a list of available non_urgent trauma beds, and gets the first available bed in the list
	 * @return the first available bed
	 */
	public Bed getAvailableTraumaNotUrgentBed(){
		for (Bed b:beds){
			if (b.getAssignedPatient()==null && 
					b.getUrgencyLevel()==Bed.Urgency.NOT_URGENT&& 
					b.getZone()==Bed.Zone.TRAUMA){
				availableTraumaNotUrgentBedList.add(b);
			}
		}
		Bed freeBed;
		if (availableTraumaNotUrgentBedList.isEmpty()){
			freeBed=null;
		} else {
			freeBed=availableTraumaNotUrgentBedList.get(0);
		}

		return freeBed;
	}
	/**
	 * Creates a list of available non_urgent no_injury beds, and gets the first available bed in the list
	 * @return the first available bed
	 */
	public Bed getAvailableNoInjuryNotUrgentBed(){
		for (Bed b:beds){
			if (b.getAssignedPatient()==null && 
					b.getUrgencyLevel()==Bed.Urgency.NOT_URGENT&& 
					b.getZone()==Bed.Zone.NO_INJURY){
				availableNoInjuryNotUrgentBedList.add(b);
			}
		}
		Bed freeBed;
		if (availableNoInjuryNotUrgentBedList.isEmpty()){
			freeBed=null;
		} else {
			freeBed=availableNoInjuryNotUrgentBedList.get(0);
		}

		return freeBed;
	}
	/**
	 * Creates a list of available beds, and gets the first available bed in the list
	 * @return the first available bed
	 */
	public Chair getAvailableChair(){
		for (Chair c:chairs){
			if (c.getAssignedPatient() == null){
				availableChairList.add(c);
			}
		}
		Chair freeChair;
		if (availableChairList.isEmpty()){
			freeChair=null;
		} else {
			freeChair=availableChairList.get(0);
		}
		return freeChair;
	}


}
