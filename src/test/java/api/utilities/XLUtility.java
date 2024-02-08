package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {

	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFCell cell;
	public XSSFRow row;
	public XSSFCellStyle cellStyle;
	String path;
	
	public XLUtility(String path) {
		this.path=path;
	}
	
	public int getRowCount(String Sheetname) throws IOException{
		
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(Sheetname);
		int rowCount=sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowCount;
		
	}
public int getCellCount(String Sheetname,int rowNum) throws IOException{
		
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(Sheetname);
		row=sheet.getRow(rowNum);
		int cellCount=row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellCount;
		
	}
public String getCellData(String Sheetname, int rowNum, int colnum) throws IOException {
	
	fi=new FileInputStream(path);
	workbook=new XSSFWorkbook(fi);
	sheet=workbook.getSheet(Sheetname);
	row=sheet.getRow(rowNum);
	cell=row.getCell(colnum);
	
	DataFormatter formatter=new DataFormatter();
	String data;
	try {
		data=formatter.formatCellValue(cell);
	}
	catch(Exception e) {
		
		data="";
	}
	workbook.close();
	fi.close();
	
	return data;
}
public void setCellData(String sheetName, int rowNum, int colnum, String data) throws IOException {
	
	File xlfile=new File(path);
	if(!xlfile.exists()) {
	fo=new FileOutputStream(path);
	workbook=new XSSFWorkbook();
	workbook.write(fo);
	}
	fi=new FileInputStream(path);
	workbook=new XSSFWorkbook(fi);
	
	if(workbook.getSheetIndex(sheetName)==-1)
		workbook.createSheet(sheetName);
	sheet=workbook.getSheet(sheetName);
	
	if(sheet.getRow(rowNum)==null)
		sheet.createRow(rowNum);
	row=sheet.getRow(rowNum);
	
	
	cell=row.getCell(colnum);
	cell.setCellValue(data);
	
	fo.close();
	fi.close();
	
}
	
}
