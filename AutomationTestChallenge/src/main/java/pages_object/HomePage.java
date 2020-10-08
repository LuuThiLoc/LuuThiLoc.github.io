package pages_object;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.*;

public class HomePage {
    public WebDriver driver;
    By btFilter = By.xpath("//button[contains(@class, 'btn-filter')]");
    By selectRequest = By.id("formControlsSelect");
    By applyFilter = By.xpath("//div[contains(@class, 'modal-footer')]//button[contains(@class, 'btn-filter')]");
    By requestInactive = By.xpath("//tr[td[contains(@class, 'request-inactive')]]");
    By notifyRequestStatus = By.xpath("//a[contains(@class, 'query__filter__item')]");
    By tableRows = By.xpath("//table[contains(@class, 'table-body')]//tr");
    By firstName = By.xpath("//th[contains(@title, 'First Name')]");
    By firstNameColumn = By.xpath("//td[contains(@class, 'request')]//following-sibling::td[4]");

    public HomePage(WebDriver driver) { this.driver = driver; }
    public void clickFilter() {
        driver.findElement(btFilter).click();
    }
    public void selectStudentAccessRequest(String status) {
        driver.findElement(selectRequest).click();
        Select select = new Select(driver.findElement(selectRequest));
        select.selectByVisibleText(status);
    }

    public void clickApplyFilter() {
        driver.findElement(applyFilter).click();
    }
    public int getNumberOfInactiveRequests(){
        List<WebElement> inactiveElements = driver.findElements(requestInactive);
        return inactiveElements.size();
    }
    public String getStatusNotification() {
        return driver.findElement(notifyRequestStatus).getText();
    }
    public int getNumberOfTableRows(){
        List<WebElement> tableRowsElements = driver.findElements(tableRows);
        return tableRowsElements.size();
    }
    public void sortAsc() {
        driver.findElement(firstName).click();
    }

    public List<String> getFirstNameColumn() {
        List<WebElement> firstNameElements = driver.findElements(firstNameColumn);
        List<String> firstNameList = new ArrayList<>();
        for(WebElement firstNameEle : firstNameElements) {
            firstNameList.add(firstNameEle.getText());
            System.out.println(firstNameEle.getText());
        }
        return firstNameList;
    }

    public List<String> getAscendingSortingFirstNameColumn() {
        List<String> firstNameList = getFirstNameColumn();
        Collections.sort(firstNameList);
        return firstNameList;
    }

    public List<String> getDescendingSortingFirstNameColumn() {
        List<String> firstNameList = getFirstNameColumn();
        Collections.sort(firstNameList, Collections.reverseOrder());
        return firstNameList;
    }
}
