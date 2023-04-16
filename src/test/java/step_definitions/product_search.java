package step_definitions;

import Helper.Helper;
import config.BaseClass;
import config.PageObject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Listeners(listernerConfig.Listeners.class)
public class product_search extends BaseClass {
    String actual_message;
  public  static String note_id;
    String actual_note ;
    String user_email;
    String actual_email;
     String base_url = "https://www.evernote.com/";

    JavascriptExecutor js = (JavascriptExecutor) driver;
    WebDriverWait wait = new WebDriverWait(driver, 30);

    String product_url = "https://www.goldenscent.com/en/p/gucci-guilty-absolute-pour-homme-eau-de-parfum-for-men.html?action=prod&id=6108";

    PageObject element = new PageObject(driver); // Creating Page Object reference
    Helper action = new Helper(driver); // Creating helper class Object reference

    public product_search() throws SQLException, IOException, ClassNotFoundException {
    }

    //step definitions for evernote features

    @Given("^User clicks on already have account$")
    public void User_clicks_on_already_have_account() {
        driver.get(base_url);
        action.click_element(element.already_account_element());
    }

    @When("^User logs into evernote application using ([^\"]*) and ([^\"]*)$")
    public void User_logs_into_evernote_application_using_email_password(String email, String password) {
        action.enter_text(element.username(), email);
        action.click_element(element.login_button());
        action.enter_text(element.password(), password);
    }

    @And("^User clicks login$")
    public void User_clicks_login() {
        action.click_element(element.login_button());
    }

    @Then("^User must see message as ([^\"]*)$")
    public void User_must_see_message_as_incorrect_password(String expected_message) {

        if (expected_message.equalsIgnoreCase("Incorrect password")) {
            System.out.println("executing validation for invalid login");
            actual_message = element.incorrect_password().getText();
            System.out.println(actual_message);

            Assert.assertTrue(actual_message.contains(expected_message));
        } else if (expected_message.equalsIgnoreCase("bilalroshan5@gmail.com")) {
            System.out.println("executing validation for Valid login");
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            actual_email = element.account_email().getText();
            System.out.println("logged in user confirmation email is " + actual_email);
            Assert.assertEquals(actual_email, expected_message);


        }
    }

    @When("^User clicks new note button$")
    public void User_clicks_new_note_button() {
        action.click_element(element.new_note());
    }

    @And("^User enters data into title as ([^\"]*)$")
    public void user_enters_data_into_title(String note_title) {
        WebElement frame1 = driver.findElement(By.xpath("//iframe[@id='qa-COMMON_EDITOR_IFRAME']"));
        driver.switchTo().frame(frame1);
        System.out.println("switched to default o frame");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        note_id = note_title +String.valueOf(Math.random());
        System.out.println("created note title is "+note_id);
        action.enter_text(element.title_area(), note_id);

        driver.switchTo().parentFrame();
    }

    @Then("^User clicks logout$")
    public void User_clicks_logout() {

        // action.click_element(element.nav_home());
        action.click_element(element.account_email());
        action.click_element(element.sign_out());

        try {
            action.click_element(element.confirm_logout());
        } catch (Exception e) {
            e.getMessage();
        }
        System.out.println("logout completed");

    }


    @Then("^User searches for existing_note$")
    public void User_searches_for_existing_note() {

        System.out.println("initiated search");
        List<WebElement> list = new ArrayList<>();
        list = driver.findElements(By.xpath("//div[@class='Fuix_q8N7ezroVVJ104t']/div/span[1]"));
        System.out.println("length of titles list is"+list.size());
        for (WebElement itr : list) {
            System.out.println( "text value is"+itr.getText());
            String expected_note = note_id;
            actual_note = itr.getText();
              if ((actual_note.equalsIgnoreCase(expected_note))){
                  System.out.println(itr.getText());

              }


            }
        }
    }





