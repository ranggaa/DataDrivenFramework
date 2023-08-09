package driverFactory;

import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.Test;

import commonFunctions.FunctionLibrary;
import config.AppUtil;
import utilities.ExcelFileUtil;

public class DriverScript extends AppUtil {
String inputpath ="./TestInput/LoginData.xlsx";
String outputpath ="./TestOutPut/DataDrivenResults.xlsx";
@Test
public void startTest() throws Throwable
{
	//create obejct for excel file util class
	ExcelFileUtil xl = new ExcelFileUtil(inputpath);
	//count no of rows in excel sheet
	int rc = xl.rowCount("Login");
	Reporter.log("No of rows are::"+rc,true);
	for(int i=1;i<=rc;i++)
	{
		String user = xl.getCellData("Login", i, 0);
		String pass = xl.getCellData("Login", i, 1);
		//call login method
		boolean res =FunctionLibrary.Login(user, pass);
		if(res)
		{
			//if res is true write as login success into results cell
			xl.setCellData("Login", i, 2, "Login Success", outputpath);
			//write as pass into status cell
			xl.setCellData("Login", i, 3, "Pass", outputpath);
		}
		else
		{
			String Message =driver.findElement(By.xpath(conpro.getProperty("ObjErrorMessage"))).getText();
			//if res is false write as login Fail into results cell
			xl.setCellData("Login", i, 2, Message, outputpath);
			xl.setCellData("Login", i, 3, "Fail", outputpath);
		}
	}
	
}
}
