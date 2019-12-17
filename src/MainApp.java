import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class MainApp extends JFrame {

	private JPanel contentPane;
	private JTextField textField1;
	private JTextField textField2;
	private File file1 ;
	private File file2 ;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainApp frame = new MainApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 533, 347);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 497, 48);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblExcelFileComparator = new JLabel("Excel File Comparator");
		lblExcelFileComparator.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblExcelFileComparator.setBounds(183, 11, 147, 26);
		panel.add(lblExcelFileComparator);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 70, 497, 226);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		textField1 = new JTextField();
		textField1.setBounds(20, 28, 347, 20);
		panel_1.add(textField1);
		textField1.setColumns(10);
		
		textField2 = new JTextField();
		textField2.setBounds(20, 95, 347, 20);
		panel_1.add(textField2);
		textField2.setColumns(10);
		
		JButton fileSelect1 = new JButton("Excel File 1");
		fileSelect1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser(new File(""));
				jfc.setCurrentDirectory(new File("").getAbsoluteFile());
				File selectedFile=null;
				int returnValue = jfc.showOpenDialog(null);
				// int returnValue = jfc.showSaveDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					selectedFile = jfc.getSelectedFile();
					System.out.println(selectedFile.getAbsolutePath());
					
				}
				
				file1 = selectedFile;
				textField1.setText(file1.getAbsolutePath());
				
			}
		});
		fileSelect1.setBounds(377, 27, 110, 23);
		panel_1.add(fileSelect1);
		
		JButton fileSelect2 = new JButton("Excel File 1");
		fileSelect2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser jfc = new JFileChooser(new File(""));
				jfc.setCurrentDirectory(new File("").getAbsoluteFile());
				File selectedFile=null;
				int returnValue = jfc.showOpenDialog(null);
				// int returnValue = jfc.showSaveDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					selectedFile = jfc.getSelectedFile();
					System.out.println(selectedFile.getAbsolutePath());
					
				}
				
				file2 = selectedFile;
				textField2.setText(file2.getAbsolutePath());
				
			}
		});
		fileSelect2.setBounds(383, 94, 104, 23);
		panel_1.add(fileSelect2);
		
		JButton compareButton = new JButton("Compare");
		compareButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ExcelFileReader reader = new ExcelFileReader();
				try {
					
				List<String> uncommonElements = reader.getUniqueItems(file1,file2);
				ExcelFileWriter writer = new ExcelFileWriter();
				boolean written = writer.createExcelOutputFile(uncommonElements);
			
				JOptionPane.showMessageDialog(null, ExcelFileReader.message+"\n unique-items-file has been created in :"+new File("").getAbsolutePath()+"\\unique-items-file.xslx");
				
					
				} catch (EncryptedDocumentException | InvalidFormatException | IOException ioe) {
					
					ioe.printStackTrace();
				}
				
			}
		});
		compareButton.setBounds(208, 159, 89, 23);
		panel_1.add(compareButton);
		
	}
}
