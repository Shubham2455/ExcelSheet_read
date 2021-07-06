package Read;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class readFile {

	public static void main(String[] args) {
		try {
			File file = new File(
					"C:\\Users\\shubh\\Documents\\Ex work\\Book1.xlsx");
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook wbk = new XSSFWorkbook(fis);
			XSSFSheet sheet = wbk.getSheet("Sheet2");

			int rowcount = sheet.getLastRowNum() + 1;
			for (int i = 1; i < rowcount; i++) {
				XSSFRow row = sheet.getRow(i);
				for (int j = 0; j < row.getLastCellNum(); j++) {
					Cell cell = row.getCell(j);
					System.out.print((cell) + "   ");
				}
				System.out.println("   ");
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
