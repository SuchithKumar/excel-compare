import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileWriter {

	public boolean createExcelOutputFile(List<String> uniqueItems) throws IOException {

		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("unique-items");

		CreationHelper createHelper = workbook.getCreationHelper();

		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.RED.getIndex());

		// Create a CellStyle with the font
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		// Create a Row
		Row headerRow = sheet.createRow(0);

		// Create cells

		Cell cell = headerRow.createCell(0);
		cell.setCellValue("unique-items");
		cell.setCellStyle(headerCellStyle);

		// Create Cell Style for formatting Date
		CellStyle dateCellStyle = workbook.createCellStyle();
		dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
		if (uniqueItems != null) {
			// Create Other rows and cells with employees data
			int rowNum = 1;
			for (String item : uniqueItems) {
				Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(item);

			}

			// Resize all columns to fit the content size
			for (int i = 0; i < uniqueItems.size(); i++) {
				sheet.autoSizeColumn(i);
			}

			// Write the output to a file
			FileOutputStream fileOut = new FileOutputStream("unique-items-file.xlsx");
			workbook.write(fileOut);
			fileOut.close();

			// Closing the workbook
			workbook.close();
			
			return true;
		} else {
			int rowNum = 1;
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue("No unique items");
			
			FileOutputStream fileOut = new FileOutputStream("unique-items-file.xlsx");
			workbook.write(fileOut);
			fileOut.close();

			// Closing the workbook
			workbook.close();
			
			return false;
		}

	}
}