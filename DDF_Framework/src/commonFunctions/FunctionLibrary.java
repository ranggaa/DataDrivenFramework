package commonFunctions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Reporter;

import config.AppUtil;

public class FunctionLibrary extends AppUtil{
public static boolean Login(String Username,String password)
{
	driver.get(conpro.getProperty("Url"));
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.findElement(By.xpath(conpro.getProperty("ObjUser"))).sendKeys(Username);
	driver.findElement(By.xpath(conpro.getProperty("ObjPass"))).sendKeys(password);
	driver.findElement(By.xpath(conpro.getProperty("ObjLogin"))).click();
	String Expected ="dashboard";
	String Actual =driver.getCurrentUrl();
	if(Actual.contains(Expected))
	{
		Reporter.log("Login Success::"+Expected+"     "+Actual,true);
		return true;
	}
	else
	{
		
		Reporter.log("Login Fail"+"    "+Expected+"      "+Actual,true);
		return false;
		
	}
	
}
}
