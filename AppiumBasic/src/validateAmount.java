import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static java.time.Duration.ofSeconds;

public class validateAmount extends base {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		// TODO Auto-generated method stub	
		double sum=0;
		AndroidDriver<AndroidElement> driver=capabilities();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("hello");
		driver.hideKeyboard();
		driver.findElement(By.xpath("//*[@text='Female']")).click();
		driver.findElement(By.id("android:id/text1")).click();
		//String s="Argentina";
		//driver.findElementByAndroidUIAutomator("new UiScrollable(new Uiselector()).scrollIntoView(test(\"Argentina\"));");
		//driver.findElement(MobileBy.AndroidUIAutomator("new Uiscrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new Uiselector().textMatches(\""+s+"\").instance(0))"));
		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
				+ "new UiSelector().text(\"Argentina\"));"));
		driver.findElement(By.xpath("//*[@text='Argentina']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

		driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
		driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
		//show on cart if appear
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		//sleep for load the new page that not throw exception
		Thread.sleep(4000);
		int count=driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).size();
		for(int i=0;i<count;i++) {
			String amount1=driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(i).getText();
			double amount=getValue(amount1);
			sum+=amount;
		}
		//String amount1=driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(0).getText();
		//$120.0 -> 120.0
		
		//double amount1value=getValue(amount1);
		//String amount2=driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(1).getText();
		
		//double amount2value=getValue(amount2);
		//double totalOfProducts=amount1value+amount2value;
		System.out.println("sum of prodcuts is: "+sum);
		
		String total=driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
		total=total.substring(1);
		double totalValue=Double.parseDouble(total);
		System.out.println("sum of total values is: "+totalValue);
		Assert.assertEquals(totalValue, sum);
		
		//mobile gestures
		WebElement checkbox=driver.findElement(By.className("android.widget.CheckBox"));
		TouchAction t=new TouchAction(driver);
		t.tap(tapOptions().withElement(element(checkbox))).perform();
		WebElement tc=driver.findElement(By.xpath("//*[@text='Please read our terms of conditions']"));
		
		t.longPress(longPressOptions().withElement(element(tc)).withDuration(ofSeconds(5))).release().perform();
		driver.findElement(By.id("android:id/button1")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
	}
	
	public static double getValue(String value) {
		value=value.substring(1);
		double amountvalue=Double.parseDouble(value);
		return amountvalue;
	}
}
