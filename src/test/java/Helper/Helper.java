package Helper;

import config.BaseClass;
import config.PageObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class Helper extends BaseClass {
    PageObject element = new PageObject(driver); // Creating Page Object reference


    public Helper(WebDriver driver) {

        this.driver = driver;

    }

    JavascriptExecutor js = (JavascriptExecutor) driver;
    WebElement js_element;
    WebDriverWait wait = new WebDriverWait(driver, 30); //passing driver instance to wait


    public void click_element(WebElement element) {
        js_element = element;   // assigning local element value to class level js element
        try {

            WebElement element_Field = wait.until(elementToBeClickable(element));
            element_Field.click();
            System.out.println(element.getText() + "\t" + "element clicked");
        } catch (StaleElementReferenceException e) {
            //driver.navigate().refresh();
        } catch (Throwable e) {
            js.executeScript("arguments[0].click();", js_element); //try to interact with element using js executor

        }
    }

    public void enter_text(WebElement element, String text) {
        js_element = element;
        try {
            WebElement element_Field = wait.until(visibilityOf(element));
            element_Field.sendKeys(text);
            System.out.println(text + "\t" + " entered into the field");

        } catch (StaleElementReferenceException e) {
            driver.navigate().refresh();
        } catch (Throwable e) {

            js.executeScript("arguments[0].click();", js_element);
            js.executeScript("document." + element + "[0].value= " + text + ""); //trying to enter text using js executor
        }
    }
    public void element_visibility(WebElement element) {
       try{
           System.out.println("validating visibility");
           WebDriverWait wait = new WebDriverWait(driver, 20);
           wait.until(ExpectedConditions.visibilityOf(element));
    }catch (Throwable e) {
           System.out.println("visibility failed");
           e.getMessage();
       }
    }


}















