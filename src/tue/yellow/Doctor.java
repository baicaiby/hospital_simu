package tue.yellow;

import tue.yellow.Patient.Status;


public class Doctor extends Staff {


	public Doctor(ListTracker lt){
		this(lt, "no name");
	}

	public Doctor(ListTracker lt, String name) {
		this(lt, name, Availability.AVAILABLE);
	}

	public Doctor (ListTracker lt, String name, Availability availability){
		this(lt, name, availability, null);
	}

	public Doctor(ListTracker lt, String name, Availability availability,Patient patient){
		this(lt, name, availability, patient,"Unknown");
	}



	public Doctor(ListTracker lt,String staffName, Availability staffAvailability, Patient assignedPatient, String currentActivity) {
		super(lt,staffName,staffAvailability,assignedPatient,currentActivity);
		lt.addDoctor(this);

	}

	@Override
	public void setAssignedPatient(Patient assignedPatient) {
		Patient patient=getAssignedPatient();
		Doctor doctor = assignedPatient.getCurrentDoctor();
		boolean patientMatch = false;
		if (patient == null && doctor == null) {
			patientMatch = true;
		}
		if (patientMatch){
			this.assignedPatient=assignedPatient;
			assignedPatient.setCurrentDoctor(this);
			assignedPatient.setCurrentStatus(Status.IN_TREATMENT);
		} else {
			System.out.println("Doctor, Not able to assign this patient to you.");
		}
	}

}


