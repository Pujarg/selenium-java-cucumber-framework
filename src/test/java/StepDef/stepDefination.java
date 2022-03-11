package StepDef;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;

import WebDriverFactory.WebDriverfactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefination {

	private static final Logger logger = LogManager.getLogger(stepDefination.class);


	WebDriver driver;
	String url ="http://automationpractice.com";
	Scenario scn;
	int expectedHeight = 99;
	int expectedWidth = 350;

	
	@Before
	public void setUp(  Scenario scn) throws Exception{
		this.scn = scn;
		String browserName = WebDriverfactory.getBrowserName();
		driver = WebDriverfactory.getWebDriverForBrowser(browserName);
		scn.log("Browser Invoked");
	}

	@After(order=1)
	public void cleanUp(){
		driver.quit();
		scn.log("Browser Closed");
		logger.info("Browser Closed");
	}

	@After(order=2)
	public void takeScreenShot(Scenario s) {
		if (s.isFailed()) {
			TakesScreenshot scrnShot = (TakesScreenshot)driver;
			byte[] data = scrnShot.getScreenshotAs(OutputType.BYTES);
			scn.attach(data, "image/png","Failed Step Name: " + s.getName());
		}else{
			scn.log("Test case is passed, no screen shot captured");
		}
	}

	@Given("user on home page")
	public void user_on_home_page() {
		driver.get(url);
	}

	//TestCase:1
	@When("user click User navigated to the home application url")
	public void user_click_user_navigated_to_the_home_application_url() {

		String expected = "http://automationpractice.com/index.php";
		String ActualUrl = driver.getCurrentUrl();
		Assert.assertTrue(ActualUrl.equalsIgnoreCase(expected));
	}
	@Then("user should be on the {string} page")
	public void user_should_be_on_the_page(String string) {
		String redirected_url = driver.getCurrentUrl();
		driver.get(redirected_url);
	}

	//TestCase:2

	@When("home page opened logo should display")
	public void home_page_opened_logo_should_display() {
		WebElement logo = driver.findElement(By.xpath("//img[@alt='My Store']"));
		logo.isDisplayed(); 
	}


	@Then("logo should be in height and width")
	public void logo_should_be_in_height_and_width() {
		WebElement logo = driver.findElement(By.xpath("//img[@class='logo img-responsive']"));
		int actualHeight = logo.getSize().getHeight();

		scn.log("height of logo :"+actualHeight);
		logger.info("height of logo :"+actualHeight);

		Assert.assertEquals(actualHeight, expectedHeight);
		int actualWidth = logo.getSize().getWidth();
		scn.log("Width of logo :"+actualWidth);
		logger.info("Width of logo :"+actualWidth);

		Assert.assertEquals(actualWidth, expectedWidth);
	}

	//TestCase:3

	@When("user fetch product main category")
	public void user_fetch_product_main_category() {
		
		
		driver.findElement(By.xpath("//ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']")).isDisplayed();
	}

	@Then("count should be {int}")
	public void count_should_be(Integer int1) {

		List<WebElement> listMainCategory = driver.findElements(By.xpath("//div[@class='sf-contener clearfix col-lg-12']/ul/li"));
		for (int i = 0; i <listMainCategory.size(); i++) 
		{
			scn.log(listMainCategory.get(i).getText());
			logger.info(listMainCategory.get(i).getText());
		}
	}

	//TestCase:4
	@When("user enter the text {string}")
	public void user_enter_the_text(String string) {
		WebElement searchBox = driver.findElement(By.xpath("//input[@id='search_query_top']"));
		searchBox.sendKeys("T-shirt");	    
	}

	@Then("it should contains T-shirt as a text")
	public void it_should_contains_t_shirt_as_a_text() {
		WebElement actualText = driver.findElement(By.xpath("//div[@class='ac_results']//li"));
		String SearchActualText =  actualText.getText();
		scn.log("Actual text :"+SearchActualText);
		logger.info("Actual text :"+SearchActualText);

		if
		(SearchActualText.contains("T-shirt")) {

			scn.log("The actual text is :-> T-shirt");
			logger.info("The actual text is :-> T-shirt");
		}
		else
		{
			scn.log("The actual text is not :-> T-shirt");
			logger.info("The actual text is not :-> T-shirt");
		}

	}
	//TestCase:5

	@When("user click on twitter logo")
	public void user_click_on_twitter_logo() {
		WebElement twitterLink = driver.findElement(By.xpath("//section[@id='social_block']//li[@class='twitter']"));
		twitterLink.click();
		scn.log("current window title is : "+driver.getTitle());
		logger.info("current window title is : "+driver.getTitle());

	}

	@Then("new tab will open")
	public void new_tab_will_open() {
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		String parent_tab = it.next();
		String child_tab = it.next();
		scn.log("parent tab id is :"+parent_tab);


		driver.switchTo().window(child_tab);
		scn.log("child tab title is :"+driver.getTitle());
		scn.log("child tab id is :"+child_tab);

	}

	@Then("verify the account name and opened page url")
	public void verify_the_account_name_and_opened_page_url() {
		WebElement accName = driver.findElement(By.xpath("//span[text()='Selenium Framework']"));
		scn.log("Accouunt Name :"+accName.getText());
		logger.info("Accouunt Name :"+accName.getText());

		String openPageUrl = driver.getCurrentUrl();
		scn.log("current page url is :" +openPageUrl);
		logger.info("current page url is :" +openPageUrl);

		if (openPageUrl.equalsIgnoreCase("https://twitter.com/seleniumfrmwrk")) 
		{
			scn.log("Matches :" + openPageUrl);
			logger.info("Matches :" + openPageUrl);
		} 
		else
		{
			scn.log("Does not match :" + openPageUrl);
			logger.info("Does not match :" + openPageUrl);
		}
	}
}
