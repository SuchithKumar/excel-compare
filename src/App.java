import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class App {
	
	public static void main(String[] args) {
		ExcelFileReader reader = new ExcelFileReader();
		try {
			File file1 = new File("file1.xlsx");
			File file2 = new File("file2.xlsx");

			try {
				
			List<String> uncommonElements = reader.getUniqueItems(file1,file2);
			ExcelFileWriter writer = new ExcelFileWriter();
			boolean written = writer.createExcelOutputFile(uncommonElements);
			
			System.out.println(ExcelFileReader.message+"\n unique-items-file has been created in :"+new File("").getAbsolutePath()+"\\unique-items-file.xslx");
			
				
			} catch (EncryptedDocumentException | InvalidFormatException | IOException ioe) {
				
				ioe.printStackTrace();
			}
			
		} catch (EncryptedDocumentException e) {
			
			e.printStackTrace();
		}
		
	}

}
