import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.testng.Assert;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class ScrollResults extends base {

	public static void main(String[] args) throws MalformedURLException {
		// TODO Auto-generated method stub	
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
		//if has 2 message on screen and u want the second so toast[2]
		//name attribute for toast message will have content
		//driver.findElement(By.xpath("//android.widget.toast[1]")).getAttribute("name");
		int count=driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()"
        + ".resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView("
        + "new UiSelector().text(\"Jordan 6 Rings\"));");
		
		for(int i=0;i<count;i++) {
			String text=driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
			
			if(text.equals("Jordan 6 Rings")) {
				driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
				break;
			}
		}
		//show on cart if appear
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		String expected=driver.findElement(By.xpath("//*[@text='Jordan 6 Rings']")).getText();
		String actual="Jordan 6 Rings";
		Assert.assertEquals(actual, expected);
	//	System.out.println(driver.findElement(By.xpath("//*[@text='Jordan 6 Rings']")).getText());
	}

}
