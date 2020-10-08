package pages_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {

	public WebDriver driver;
	By tf_email = By.id("formHorizontalEmail");
	By tf_password = By.id("formHorizontalPassword");
	By bt_login = By.xpath("//a[contains(@class, 'login__btn')]");

	public Login(WebDriver driver) {
		this.driver = driver;
	}

	public void inputMail(String email) {	
		driver.findElement(tf_email).click();
		driver.findElement(tf_email).clear();
		driver.findElement(tf_email).sendKeys(email);
	}

	public void inputPass(String password) {
		driver.findElement(tf_password).click();
		driver.findElement(tf_password).clear();
		driver.findElement(tf_password).sendKeys(password);
	}

	public void clickLogin() {
		driver.findElement(bt_login).click();
	}
	
}