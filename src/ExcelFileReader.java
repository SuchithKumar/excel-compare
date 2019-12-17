
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileReader {

	List<String> file1List = new ArrayList<String>();
	List<String> file2List = new ArrayList<String>();
	public static String message;

	private static String getCellValue(Cell cell) {
		String celldata = "";
		try {
			switch (cell.getCellTypeEnum()) {
			case STRING:
				celldata = cell.getRichStringCellValue().getString().trim();
				break;
			case NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					celldata = cell.getDateCellValue().toString();
				} else {
					BigDecimal bigDecimal = BigDecimal.valueOf(cell.getNumericCellValue()).stripTrailingZeros();
					celldata = bigDecimal.toPlainString().trim();
				}
				break;
			default:
				celldata = "";

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return celldata;

	}

	public List<String> getUniqueItems(File file1,File file2) throws EncryptedDocumentException, InvalidFormatException, IOException {

		Workbook workbook1 = WorkbookFactory.create(file1);
		Sheet file1sheet1 = workbook1.getSheetAt(0);
		for (int i = 1; i <= file1sheet1.getLastRowNum(); i++) {
			Row row = file1sheet1.getRow(i);
			Cell cell = row.getCell(0);
			String celldata = getCellValue(cell);
			file1List.add(celldata);
		}

		Workbook workbook2 = WorkbookFactory.create(file2);
		Sheet file2sheet1 = workbook2.getSheetAt(0);
		for (int i = 1; i <= file2sheet1.getLastRowNum(); i++) {
			Row row = file2sheet1.getRow(i);
			Cell cell = row.getCell(0);
			String celldata = getCellValue(cell);
			file2List.add(celldata);
		}

		if (file1List.size() > file2List.size()) {
			HashSet<String> sameItems = new HashSet<>();
			List<String> uniqueItems = new ArrayList<>();
			for (String item : file2List) {
				sameItems.add(item);
			}
			System.out.println("File 1 is having below unique items compared with File 2");
			message = "File 1 is having below unique items compared with File 2";
			for (String item : file1List) {
				if (!file2List.contains(item)) {
					System.out.println(item);
					uniqueItems.add(item);
				}
			}
			
			return uniqueItems;
			
		} else if (file1List.size() == file2List.size()) {
			message = "no unique elements";
			return null;
		} else {

			HashSet<String> sameItems = new HashSet<>();
			List<String> uniqueItems = new ArrayList<>();
			for (String item : file1List) {
				sameItems.add(item);
			}
			System.out.println("File 2 is having below unique items compared with File 1");
			message = "File 2 is having below unique items compared with File 1";
			for (String item : file2List) {
				if (!file1List.contains(item)) {
					System.out.println(item);
					uniqueItems.add(item);
				}
			}
			return uniqueItems;
		}

	}

}
