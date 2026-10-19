package genericUtility;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

public class DataProviderUtility {

	@DataProvider(name = "data")
	  public Object[][] data()throws EncryptedDocumentException, IOException {

	     FileInputStream fis =new FileInputStream("./src/test/resources/QADemo/QADemo.xlsx");
	     Workbook wb = WorkbookFactory.create(fis);
	     Sheet sh = wb.getSheet("Sheet1");
	     Object[][] obj = new Object[1][4];
	      {
	        obj[0][0]=sh.getRow(1).getCell(0).getStringCellValue();
	        obj[0][1]=sh.getRow(1).getCell(1).getStringCellValue();
	        obj[0][2]=sh.getRow(1).getCell(2).getStringCellValue();
	        obj[0][3]=sh.getRow(1).getCell(3).getStringCellValue();

	    }

	    wb.close();
	    fis.close();

	    return obj;
	}
	
	@DataProvider(name = "data1")
	  public Object[][] data1()throws EncryptedDocumentException, IOException {

	     FileInputStream fis =new FileInputStream("./src/test/resources/QADemo/QADemo.xlsx");
	     Workbook wb = WorkbookFactory.create(fis);
	     Sheet sh = wb.getSheet("Sheet1");
	     Object[][] obj = new Object[1][4];
	      {
	        obj[0][0]=sh.getRow(2).getCell(0).getStringCellValue();
	        obj[0][1]=sh.getRow(2).getCell(1).getStringCellValue();
	        obj[0][2]=sh.getRow(2).getCell(2).getStringCellValue();
	        obj[0][3]=sh.getRow(2).getCell(3).getStringCellValue();

	    }

	    wb.close();
	    fis.close();

	    return obj;
	}
	
	
	@DataProvider(name = "systemData")
	public Object[][] systemData() throws Exception {

	    FileInputStream fis = new FileInputStream(
	            "./src/test/resources/QADemo/QADemo.xlsx");

	    Workbook wb = WorkbookFactory.create(fis);
	    Sheet sh = wb.getSheet("Sheet2");

	    Object[][] obj = new Object[1][2];

	    obj[0][0] = sh.getRow(1).getCell(0).getStringCellValue();
	    obj[0][1] = sh.getRow(1).getCell(1).getStringCellValue();
	    

	    wb.close();
	    fis.close();

	    return obj;
	}
	@DataProvider(name = "invaliddata")
	public Object[][] invaliddata() throws Exception {

	    FileInputStream fis = new FileInputStream(
	            "./src/test/resources/QADemo/QADemo.xlsx");

	    Workbook wb = WorkbookFactory.create(fis);
	    Sheet sh = wb.getSheet("Sheet2");

	    Object[][] obj = new Object[1][2];

	    obj[0][0] = sh.getRow(2).getCell(0).getStringCellValue();
	    obj[0][1] = sh.getRow(2).getCell(1).getStringCellValue();

	    wb.close();
	    fis.close();

	    return obj;
	}
	    
	    
	    @DataProvider(name = "checkoutdata")
		  public Object[][] checkoutdata()throws EncryptedDocumentException, IOException {

		     FileInputStream fis =new FileInputStream("./src/test/resources/QADemo/QADemo.xlsx");
		     Workbook wb = WorkbookFactory.create(fis);
		     Sheet sh = wb.getSheet("Sheet3");
		     Object[][] obj = new Object[1][9];

		     obj[0][0] = sh.getRow(1).getCell(0).getStringCellValue();
		     obj[0][1] = sh.getRow(1).getCell(1).getStringCellValue();
		     obj[0][2] = sh.getRow(1).getCell(2).getStringCellValue();
		     obj[0][3] = sh.getRow(1).getCell(3).getStringCellValue();
		     obj[0][4] = sh.getRow(1).getCell(4).getStringCellValue();
		     obj[0][5] = sh.getRow(1).getCell(5).getStringCellValue();
		     obj[0][6] = sh.getRow(1).getCell(6).getStringCellValue();
		     obj[0][7] = sh.getRow(1).getCell(7).getStringCellValue();
		     obj[0][8] = sh.getRow(1).getCell(8).getStringCellValue();
		     
		     wb.close();
		     fis.close();
		     return obj;
		}
	    

@DataProvider(name = "negativeCheckoutData")
public Object[][] negativeCheckoutData()
        throws EncryptedDocumentException, IOException {

    FileInputStream fis = new FileInputStream(
            "./src/test/resources/QADemo/QADemo.xlsx");

    Workbook wb = WorkbookFactory.create(fis);
    Sheet sh = wb.getSheet("Sheet3");

    Object[][] obj = new Object[1][9];

    DataFormatter df = new DataFormatter();

    obj[0][0] = df.formatCellValue(sh.getRow(2).getCell(0));
    obj[0][1] = df.formatCellValue(sh.getRow(2).getCell(1));
    
    for (int i = 2; i < 9; i++) {
        obj[0][i] = df.formatCellValue(
                sh.getRow(2).getCell(i));
    }

    

    wb.close();
    fis.close();

    return obj;
}

}