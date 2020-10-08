package excutiontest;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import pages_object.HomePage;
import pages_object.Login;

public class VerifySortingOfFirstNameColumn {

	WebDriver driver;

	@Parameters("browser")
	@BeforeTest
	public void login(String browser) throws InterruptedException, MalformedURLException {
		String URL = "http://ktvn-test.s3-website.us-east-1.amazonaws.com/";

		if (browser.equalsIgnoreCase("firefox")) {

			System.out.println(" Executing on FireFox");
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\driver\\geckodriver.exe");
			driver = new FirefoxDriver();
			// Puts an Implicit wait, Will wait for 10 seconds before throwing
			// exception
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			// Launch website
			driver.navigate().to(URL);
			driver.manage().window().maximize();
		} else if (browser.equalsIgnoreCase("chrome")) {
			System.out.println(" Executing on CHROME");
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\driver\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			// Launch website
			driver.navigate().to(URL);
			driver.manage().window().maximize();
		}else {
			throw new IllegalArgumentException("The Browser Type is Undefined");
		}

		// login
		Login login = new Login(driver);
		login.inputMail("admin@test.com");
		login.inputPass("test123");
		login.clickLogin();
		System.out.println("Log in successfully!");

		Thread.sleep(5000);

	}

	// Run TC
	@Test()
	public void TestCase2() throws InterruptedException {

		HomePage homePage = new HomePage(driver);
		List<String> expectedDescendingFirstName = homePage.getDescendingSortingFirstNameColumn();
		homePage.sortAsc();
		Thread.sleep(5000);
		List<String> actualDescendingFirstName = homePage.getFirstNameColumn();
		Assert.assertEquals(actualDescendingFirstName, expectedDescendingFirstName,
				String.format("Actual Descending First Name List is %s. Expected Descending First Name is %s.", actualDescendingFirstName, expectedDescendingFirstName));

		List<String> expectedAscendingFirstName = homePage.getAscendingSortingFirstNameColumn();
		homePage.sortAsc();
		Thread.sleep(5000);
		List<String> actualAscendingFirstName = homePage.getFirstNameColumn();
		Assert.assertEquals(actualAscendingFirstName, expectedAscendingFirstName,
				String.format("Actual First Name List is %s. Expected First Name is %s.", actualAscendingFirstName, expectedAscendingFirstName));
	}

		@AfterTest
		public void tearDown () {
			System.out.println("Test case done.");
			driver.close();
		}
}
