package tue.yellow;

import java.util.*;

import tue.yellow.Bed.Urgency;
import tue.yellow.Bed.Zone;
import tue.yellow.Patient.HealthProblem;
import tue.yellow.Patient.HealthStatus;
import tue.yellow.Patient.Status;
import tue.yellow.Patient.TriageUrgency;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ShowCase {

	private int patientNumber;
	private int doctorNumber;
	private int nurseNumber;
	//	private int bedNumber;
	private int TUrgentBedNumber;
	private int TNotUrgentBedNumber;
	private int NIUrgentBedNumber;
	private int NINotUrgentBedNumber;
	private int chairNumber;
	private int dieNumber;
	private ListTracker lt;
	private Random rand,rand1;
	private double avTimeAppendix;
	private double avTimeConcussion;
	private double avTimeBrokenArm;
	private int urgentWaitingTime;
	private int nonUrgentWaitingTime;
	private Map<String,Date> arriveTimeTable;

	private static List<Integer> valueStorage;
	//	private Object[] waitingTimeMap;

	//constructor
	public ShowCase(){
		lt = new ListTracker();
		valueStorage = new ArrayList<Integer>();
		patientNumber = 0;
		doctorNumber = 0;
		nurseNumber = 0;
		//		bedNumber = 0;
		TUrgentBedNumber = 0;
		TNotUrgentBedNumber = 0;
		NIUrgentBedNumber = 0;
		NINotUrgentBedNumber = 0;
		chairNumber = 0;
		
		//		waitingTimeMap = new TreeMap<String, Object[] >();;
	}

	public int getPatientNumber() {
		return patientNumber;
	}

	public void setPatientNumber(int patientNumber) {
		this.patientNumber = patientNumber;
	}

	public int getDoctorNumber() {
		return doctorNumber;
	}

	public void setDoctorNumber(int doctorNumber) {
		this.doctorNumber = doctorNumber;
	}

	public int getNurseNumber() {
		return nurseNumber;
	}

	public void setNurseNumber(int nurseNumber) {
		this.nurseNumber = nurseNumber;
	}
	
	public int getChairNumber() {
		return chairNumber;
	}

	public int getTUrgentBedNumber() {
		return TUrgentBedNumber;
	}

	public int getTNotUrgentBedNumber() {
		return TNotUrgentBedNumber;
	}

	public int getNIUrgentBedNumber() {
		return NIUrgentBedNumber;
	}

	public int getNINotUrgentBedNumber() {
		return NINotUrgentBedNumber;
	}

	public void setTUrgentBedNumber(int tUrgentBedNumber) {
		TUrgentBedNumber = tUrgentBedNumber;
	}

	public void setTNotUrgentBedNumber(int tNotUrgentBedNumber) {
		TNotUrgentBedNumber = tNotUrgentBedNumber;
	}

	public void setNIUrgentBedNumber(int nIUrgentBedNumber) {
		NIUrgentBedNumber = nIUrgentBedNumber;
	}

	public void setNINotUrgentBedNumber(int nINotUrgentBedNumber) {
		NINotUrgentBedNumber = nINotUrgentBedNumber;
	}

	public void setChairNumber(int chairNumber) {
		this.chairNumber = chairNumber;
	}

	public int getDieNumber() {
		return dieNumber;
	}

	public void setDieNumber(int dieNumber) {
		this.dieNumber = dieNumber;
	}

	public Map<String, Date> getArriveTimeTable() {
		return arriveTimeTable;
	}

	public void setArriveTimeTable(Map<String, Date> arriveTimeTable) {
		this.arriveTimeTable = arriveTimeTable;
	}

	@SuppressWarnings("deprecation")
	public void read() throws IOException{
		String excelFilePath = "read.xlsx";
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();

		while (iterator.hasNext()) {

			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();

			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();

				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					System.out.print(cell.getStringCellValue());
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					System.out.print(cell.getBooleanCellValue());
					break;
				case Cell.CELL_TYPE_NUMERIC:
					System.out.print((int)cell.getNumericCellValue());
					valueStorage.add((int)cell.getNumericCellValue());
					break;
				}
				System.out.print(" - ");
			}
			System.out.println();

		}
		patientNumber = valueStorage.get(0);
		doctorNumber = valueStorage.get(1);
		nurseNumber = valueStorage.get(2);
		TUrgentBedNumber =  valueStorage.get(3);
		TNotUrgentBedNumber =  valueStorage.get(4);
		NIUrgentBedNumber =  valueStorage.get(5);
		NINotUrgentBedNumber =  valueStorage.get(6);
		chairNumber = valueStorage.get(7);
		urgentWaitingTime = valueStorage.get(8);
		nonUrgentWaitingTime = valueStorage.get(9);
		workbook.close();
		inputStream.close();
	}

	private void genPatient(ListTracker lt){
		int j = 0;
		int i;
		while (j < patientNumber){
			rand = new Random();
			int n = rand.nextInt(10);
			
			Boolean ambulance = false;
			
			HealthProblem healthProblem = HealthProblem.UNKNOWN;
			
			if(n<=5){
				ambulance = true;
			} 
			if (ambulance ) {
				rand1 = new Random();
				int k = rand1.nextInt(3);
				if (k==0) {
					healthProblem = HealthProblem.BROKEN_ARM;
				} else if (k==1) {
					healthProblem = HealthProblem.CONCUSSION;
				} else {
					healthProblem = HealthProblem.APPENDIX;
				}
			}
			i = j+1;
			int entertime = rand.nextInt(100);
			Patient patient =new Patient(lt, entertime,ambulance, healthProblem, "patient"+i);
			patient.setCurrentStatus(Status.ENTERED_ER);
			if (ambulance) {
				patient.setCurrentStatus(Status.WAITING_FOR_BED) ;
			}
			if (ambulance) {
				System.out.println(lt.getNotProcessedPatients().get(j).getName() + " came in by Ambulance. Health problem = " + healthProblem);
			} else {
				System.out.println(lt.getNotProcessedPatients().get(j).getName() + " walked into the emergency room.");
			}
			ambulance = false;
			j++;
		}
		System.out.print(lt.getNotProcessedPatients().size());
	}
	private void genDoctor(ListTracker lt){
		int j = 0;
		int i;
		
		while (j < doctorNumber){
			i = j+1;
			Doctor doctor=new Doctor(lt, "doctor"+ i);

			j++;
		}
	}
	private void genNurse(ListTracker lt){
		int j = 0;
		int i;
		
		while (j < nurseNumber){
			i = j+1;
			Nurse nurse =new Nurse(lt, "nurse"+ i);

			j++;
		}
	}

	private void genBed(ListTracker lt){
		int j = 0;int k=0;int l=0;int m=0;
		int i;
		while (j < TUrgentBedNumber){
			i = j+1;
			Bed bed=new Bed(lt, "TraumaUrgentbed"+i, Zone.TRAUMA, Urgency.URGENT);
			j++;
		}
		while (k < TNotUrgentBedNumber){
			i = k+1;
			Bed bed=new Bed(lt, "TraumaNotUrgentbed"+i, Zone.TRAUMA, Urgency.NOT_URGENT);
			k++;
		}
		while (l < NIUrgentBedNumber){
			i = l+1;
			Bed bed=new Bed(lt, "NoInjuryUrgentbed"+i, Zone.NO_INJURY, Urgency.URGENT);
			l++;
		}
		while (m < NINotUrgentBedNumber){
			i = m+1;
			Bed bed=new Bed(lt, "NoInjuryNotUrgentbed"+i, Zone.NO_INJURY, Urgency.NOT_URGENT);
			m++;
		}


	}
	private void genChair(ListTracker lt){
		int j = 0;
		int i;
		while (j < chairNumber){
			i = j+1;
			Chair chair = new Chair(lt, "Chair" + i);
			
			j++;
		}
	}
	public void simulation(ListTracker lt){
		int notProcessedNumber;
		Patient currentPatient;
		WholeProcess process = new WholeProcess(lt);
		process.setNonUrgentWaitingTime(nonUrgentWaitingTime);
		process.setUrgentWaitingTime(urgentWaitingTime);
		genBed(lt);
		genChair(lt);
		genDoctor(lt);
		genNurse(lt);
		genPatient(lt);
		System.out.println("\n"+"\n");
		notProcessedNumber = lt.getNotProcessedPatients().size();
		System.out.println("Number of patients left to process: "+notProcessedNumber);
		while(notProcessedNumber>0){
			currentPatient = lt.getNotProcessedPatients().get(0);
			process.setCurrentPatient(currentPatient);
			process.start(currentPatient);
			lt.getNotProcessedPatients().remove(currentPatient);
			notProcessedNumber = lt.getNotProcessedPatients().size();
		}
		System.out.println("All the patients have been processed.");
		getTotalWaitingAverages(lt);
		
		//for GUI simulation, let patients go to bed, to see whether beds will show occupancy
		lt.getPatients().get(1).setCurrentStatus(Status.WAITING_FOR_BED);
		lt.getPatients().get(1).goToBed();
		lt.getPatients().get(2).setCurrentStatus(Status.WAITING_FOR_BED);
		lt.getPatients().get(2).goToBed();
		lt.getPatients().get(3).setCurrentStatus(Status.WAITING_FOR_BED);
		lt.getPatients().get(3).goToBed();
		lt.getPatients().get(4).setCurrentStatus(Status.WAITING_FOR_BED);
		lt.getPatients().get(4).goToBed();
		lt.getPatients().get(5).setCurrentStatus(Status.WAITING_FOR_BED);
		lt.getPatients().get(5).goToBed();
		lt.getPatients().get(6).setCurrentStatus(Status.WAITING_FOR_BED);
		lt.getPatients().get(6).goToBed();
		lt.getPatients().get(7).setCurrentStatus(Status.WAITING_FOR_BED);
		lt.getPatients().get(7).goToBed();
		
	}


	private void getTotalWaitingAverages(ListTracker lt) {
		double totalWaitingAppendix = 0.0;
		double totalWaitingConcussion = 0.0;
		double totalWaitingBrokenArm = 0.0;
		int countAppendix = 0;
		int countConcussion = 0;
		int countBrokenArm = 0;
		int j = 0;
		while (j<patientNumber) {
			Patient patient = lt.getPatients().get(j);
			if (patient.getHealthProblem()==HealthProblem.APPENDIX) {
				totalWaitingAppendix = totalWaitingAppendix + patient.getTotalWaitingTime();
				countAppendix = countAppendix + 1;
			} else if (patient.getHealthProblem()==HealthProblem.CONCUSSION) {
				totalWaitingConcussion = totalWaitingConcussion + patient.getTotalWaitingTime();
				countConcussion = countConcussion + 1;

			} else {
				totalWaitingBrokenArm = totalWaitingBrokenArm + patient.getTotalWaitingTime();
				countBrokenArm = countBrokenArm + 1;

			}
			j++;
		}
		
		double averageWaitingAppendix = totalWaitingAppendix / countAppendix;
		double averageWaitingConcussion = totalWaitingConcussion / countConcussion;
		double averageWaitingBrokenArm = totalWaitingBrokenArm / countBrokenArm;
		
		this.avTimeAppendix = averageWaitingAppendix;
		this.avTimeConcussion = averageWaitingConcussion;
		this.avTimeBrokenArm = averageWaitingBrokenArm;
		
	}

	public void write(ListTracker lt) throws FileNotFoundException, IOException{
		//Create blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook(); 
		//Create a blank sheet
		XSSFSheet spreadsheet = workbook.createSheet( 
				" Waiting Time ");
		//Create row object
		XSSFRow row;
		int j = 0;
		
		//This data needs to be written (Object[])
		Map < String, Object[] > waitingTimeMap = 
				new TreeMap < String, Object[] >();
				
				while(j < patientNumber){
					int i = j+2;
					Patient patient = lt.getPatients().get(j);
					int entryTime = patient.getEnterTime();
					int waitTimeTriage = patient.getWaitingTriageTime();
					int triageTime = patient.getTriageTime();
					int waitBedTime = patient.getWaitingBedTime();
					int waitTreatmentTime = patient.getWaitingTreatmentTime();
					int treatmentTime = patient.getTreatmentTime();
					int totalWaitingTime = patient.getTotalWaitingTime();
					int totalProcessTime = patient.getTotalProcessTime();
					int leaveTime = patient.getLeaveTime();
					double percentWaiting = ((double) totalWaitingTime / (double) totalProcessTime) * 100;
					String healthproblem = String.valueOf(patient.getHealthProblem());
					String ambulance ;
					double waitAppendix = this.avTimeAppendix;
					double waitConcussion = this.avTimeConcussion;
					double waitBrokenArm = this.avTimeBrokenArm;
					if (patient.isAmbulance()){
						ambulance = "ambulance";
					} else {
						ambulance = "walk in";
					}
					DecimalFormat df = new DecimalFormat("0.0");
					waitingTimeMap.put(String.valueOf(1), new Object[] {
							"Patient name", "Health problem", "Entering mode", "Entering time",
							"Waiting time triage", "Triage time", "Waiting time bed", 
							"Waiting time for/during treatment", "Effective treatment time", 
							"Leaving time", "Total waiting time", "Total ER time", 
							"Percentage waiting", "Appendix", String.valueOf(df.format(waitAppendix)),
							"Concussion", String.valueOf(df.format(waitConcussion)), 
							"Broken arm", String.valueOf(df.format(waitBrokenArm))
					});
					
					waitingTimeMap.put( String.valueOf(i), new Object[] { 
							lt.getPatients().get(j).getName(), healthproblem, ambulance, 
							String.valueOf(entryTime), 
							String.valueOf(waitTimeTriage) , String.valueOf(triageTime) ,
							String.valueOf(waitBedTime) , String.valueOf(waitTreatmentTime) , 
							String.valueOf(treatmentTime) , String.valueOf(leaveTime) , 
							String.valueOf(totalWaitingTime) , String.valueOf(totalProcessTime) ,
							String.valueOf(df.format(percentWaiting))});
					j++;
				}

				//Iterate over data and write to sheet
				Set < String > keyid = waitingTimeMap.keySet();
				int rowid = 0;
				for (String key : keyid)
				{
					row = spreadsheet.createRow(rowid++);
					Object [] objectArr = waitingTimeMap.get(key);
					int cellid = 0;
					for (Object obj : objectArr)
					{
						Cell cell = row.createCell(cellid++);
						cell.setCellValue((String)obj);
					}
				}
				//Write the workbook in file system
				FileOutputStream out = new FileOutputStream( 
						new File("WaitingTime.xlsx"));
				workbook.write(out);
				out.close();
				workbook.close();
				DecimalFormat dec = new DecimalFormat("0.00");
				System.out.println( 
						"-------------------" + "\n\n" + "WaitingTime.xlsx written successfully" + "\n\n" +
				"Average waiting time for patient with appendix problem   = "+ dec.format(avTimeAppendix) + 
				"\nAverage waiting time for patient with concussion problem = " + dec.format(avTimeConcussion)+
				"\nAverage waiting time for patient with broken arm problem = " + dec.format(avTimeBrokenArm));
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		ShowCase showcase = new ShowCase();
		ListTracker lt = new ListTracker();
		showcase.read();
		showcase.simulation(lt);
		showcase.write(lt);

	}

}
