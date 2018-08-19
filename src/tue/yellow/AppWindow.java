package tue.yellow;
/**
 * This class models the AppWindow is a class file from windowsbuilder, in this simple GUI of simulation, buttons should
 * be pressed in order, that first <i>generate</i>, once click on this button, it call the read and generate parts in 
 * showcase class. we can get the number of people and resources in the hospital. The beds will be visualized in the bed area, beds are in 4 types.
 * Second, press <i>start simulation</i>, will process the simulation part in showcase, and then will generate a excel file in the folder.
 * <i>refresh</i> button is used to see the occupancy of beds, after we manually let patients go to bed. Because the process goes too fast, we can't see
 * the status of bed changes in such a short time.
 */
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import tue.yellow.Bed.Urgency;
import tue.yellow.Bed.Zone;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
/**
 * This BedLabelArray is used for generate beds labels
 */
public class AppWindow {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JLabel[] BedLabelArray;
	ShowCase showcase = new ShowCase();
	ListTracker lt = new ListTracker();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppWindow window = new AppWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AppWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 600, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		panel.setBounds(20, 20, 300, 400);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
//		JLabel lblBed = new JLabel("");
//		lblBed.setBounds(6, 6, 40, 40);
//		lblBed.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
//		lblBed.setIcon(new ImageIcon("/Users/dansixuan/Documents/workspace/tue.yellow/bed.png"));
//		panel.add(lblBed);
		
		//create 27 beds shaped label in the panel, and set them invisible.
		int j = 0;
		int i;
		JLabel[] BedLabelArray = new JLabel[27];
		while (j < 27){
			i = j+1;
			BedLabelArray[j]=new JLabel("b"+i);
			BedLabelArray[j].setIcon(new ImageIcon("/Users/langbai/Documents/workspace/tue.yellow/bed.png"));
			BedLabelArray[j].setText("b"+i);
			BedLabelArray[j].setVisible(false);
			panel.add(BedLabelArray[j]);
 			if(j < 9){
				BedLabelArray[j].setBounds(16, 16+40*j, 80, 40);
			}else if(9<=j&&j < 18){
				BedLabelArray[j].setBounds(105, 16+40*(j-9), 80, 40);
			}else if(18<= j &&j<27){
				BedLabelArray[j].setBounds(179, 16+40*(j-18), 80, 40);
			}else{
				System.out.println("The hospital is full, too many beds!");
			}
			j++;
		}
		
		//title label
		JLabel lblHospitalSimulation = new JLabel("Hospital simulation");
		lblHospitalSimulation.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 14));
		lblHospitalSimulation.setBounds(389, 21, 137, 16);
		frame.getContentPane().add(lblHospitalSimulation);
		//patient number  label
		JLabel lblPatientNumber = new JLabel("patient number");
		lblPatientNumber.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblPatientNumber.setBounds(389, 119, 75, 16);
		frame.getContentPane().add(lblPatientNumber);
		//doctor number label
		JLabel lblDoctorNumber = new JLabel("doctor number");
		lblDoctorNumber.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblDoctorNumber.setBounds(389, 147, 75, 16);
		frame.getContentPane().add(lblDoctorNumber);
		//nurse number label
		JLabel lblNurseNumber = new JLabel("nurse number");
		lblNurseNumber.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblNurseNumber.setBounds(389, 175, 75, 16);
		frame.getContentPane().add(lblNurseNumber);
		//chair number label
		JLabel lblChairNumber = new JLabel("chair number");
		lblChairNumber.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblChairNumber.setBounds(389, 203, 75, 16);
		frame.getContentPane().add(lblChairNumber);
		// bed area label
		JLabel lblBedArea = new JLabel("bed area");
		lblBedArea.setBounds(145, 6, 49, 14);
		frame.getContentPane().add(lblBedArea);
		lblBedArea.setFont(new Font("Arial", Font.PLAIN, 12));
		//patient number textfield, to show the patient number getting from the excel file
		textField = new JTextField();
		textField.setBounds(473, 118, 63, 16);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		//doctor number textfield, to show the doctor number getting from the excel file
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(473, 146, 63, 16);
		frame.getContentPane().add(textField_1);
		//nurse number textfield, to show the nurse number getting from the excel file
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(473, 175, 63, 16);
		frame.getContentPane().add(textField_2);
		//chair number textfield, to show the chair number getting from the excel file
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(473, 203, 63, 16);
		frame.getContentPane().add(textField_3);
		//create a button to start simulation, it will call simulation() in the showcase class
		JButton btnStartSimulation = new JButton("start simulation");
		btnStartSimulation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				showcase.simulation(lt);
				try {
					showcase.write(lt);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnStartSimulation.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		btnStartSimulation.setBounds(389, 391, 127, 29);
		frame.getContentPane().add(btnStartSimulation);
		//create a button to exit the GUI
		JButton btnExit = new JButton("exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(519, 391, 75, 29);
		frame.getContentPane().add(btnExit);
		//create a button to generate stuffs we need, it will first read the number of stuffs, and visualize the different types of
		//beds(set the beds label visible)
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					showcase.read();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				textField.setText(String.valueOf(showcase.getPatientNumber()));
				textField_1.setText(String.valueOf(showcase.getDoctorNumber()));
				textField_2.setText(String.valueOf(showcase.getNurseNumber()));
				textField_3.setText(String.valueOf(showcase.getChairNumber()));
				int j = 0;int k=0;int l=0;int m=0;
				int i;
				//generate trauma not urgent beds
				while (k < showcase.getTNotUrgentBedNumber()){
					i = k+1;
					BedLabelArray[k].setText("TN_"+i);
					BedLabelArray[k].setVisible(true);
					k++;
				}
				//generate trauma urgent beds
				while(l < showcase.getTUrgentBedNumber()){
					i = l+1;
					BedLabelArray[l+k].setText("TU_"+i);
					BedLabelArray[l+k].setVisible(true);
					l++;
				}
				//generate non-injury and urgent beds
				while (m < showcase.getNIUrgentBedNumber()){
					i = m+1;
					BedLabelArray[m+l+k].setText("NU_"+i);
					BedLabelArray[m+l+k].setVisible(true);
					m++;
				}
				//generate non-injury and not urgent beds
				while (j < showcase.getNINotUrgentBedNumber()){
					i = j+1;
					BedLabelArray[j+m+l+k].setText("NN_"+i);
					BedLabelArray[j+m+l+k].setVisible(true);
					j++;
				}
				
			}
		});
		btnGenerate.setBounds(399, 58, 117, 29);
		frame.getContentPane().add(btnGenerate);
		//create the button refresh, after manually ask patients go to bed, once click the button, the occupied beds will shows NO in red.
		JButton btnRefresh = new JButton("refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) throws NullPointerException {
				int index=0;
				int bednumber = showcase.getNINotUrgentBedNumber()+showcase.getNIUrgentBedNumber()+showcase.getTNotUrgentBedNumber()+showcase.getTUrgentBedNumber();
				while(index<bednumber){
					if(lt.getBeds().get(index).getAssignedPatient() != null){
						BedLabelArray[index].setIcon(new ImageIcon("/Users/dansixuan/Documents/workspace/tue.yellow/noBed.png"));
//						BedLabelArray[index].setText("No");
					}
					index++;
				}
			}
		});
		btnRefresh.setBounds(212, 6, 82, 18);
		panel.add(btnRefresh);
		
		JButton btnNewButton = new JButton("BBB");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton.setBounds(359, 271, 117, 29);
		frame.getContentPane().add(btnNewButton);

	}
}
