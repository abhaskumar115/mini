package miniproject;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;

public class stage2project {
	 WebDriver driver;
	@BeforeTest
  public void beforeTest() {
		
		  driver=new ChromeDriver();
		  driver.get("http://www.ebay.com");
		  driver.manage().window().maximize();
			
	  }
	@Test
  public void test() throws IOException {
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		  driver.findElement(By.xpath("//*[@id=\"gh-as-a\"]")).click();
		  driver.findElement(By.xpath("//*[@id=\"_nkw\"]")).sendKeys("outdoor toys");
		     
		     WebElement anyWords = driver.findElement(By.xpath("//*[@id=\"s0-1-17-4[0]-7[1]-_in_kw\"]"));
		     new Select(anyWords).selectByVisibleText("Any words, any order");
		     
		     WebElement category = driver.findElement(By.xpath("//*[@id=\"s0-1-17-4[0]-7[3]-_sacat\"]"));
		     new Select(category).selectByVisibleText("Toys & Hobbies");
		     
		     driver.findElement(By.xpath("/html/body/div[2]/div/main/form/fieldset[2]/div[1]/label")).click();
		    
		     driver.findElement(By.xpath("/html/body/div[2]/div/main/form/fieldset[5]/div[1]/label")).click();
		      
		     driver.findElement(By.xpath("/html/body/div[2]/div/main/form/fieldset[6]/div[1]/label")).click();
		   
		     driver.findElement(By.xpath("/html/body/div[2]/div/main/form/fieldset[6]/div[2]/label")).click();
		     
		     driver.findElement(By.xpath("/html/body/div[2]/div/main/form/fieldset[8]/div[4]/label")).click();
		     
		     driver.findElement(By.xpath("/html/body/div[2]/div/main/form/div[2]/button")).click();
		  
		     List<WebElement> links =driver.findElements(By.xpath("//a[@class='s-item__link']"));
			    
			 List<WebElement> names =driver.findElements(By.xpath("//div[@class='s-item__title']"));
				
			
			XSSFWorkbook workbook = new XSSFWorkbook();
		    XSSFSheet sheet = workbook.createSheet("Outddor toys");
					     

		    int rowCount = 0;
		    for (int i = 0; i < links.size(); i++) {
				         if (names.get(i).getText().toLowerCase().contains("toys")) 
				         {
				         	XSSFRow row=sheet.createRow(rowCount++);
				                      
				            row.createCell(0).setCellValue(names.get(i).getText());
				            row.createCell(1).setCellValue(links.get(i).getAttribute("href"));
				         }
				    }
				    
				   FileOutputStream fileOut = new FileOutputStream(new File("output.xlsx"));
				   workbook.write(fileOut);
				   fileOut.close();
				   workbook.close();		
		
  }
  
  @AfterTest
  public void afterTest() {
	  driver.quit(); 
  }

}
