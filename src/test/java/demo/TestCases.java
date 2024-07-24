package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;


// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @Test 
    public void testCase01() throws InterruptedException {
        System.out.println("TestCase01: Started the testCase01");
        driver.get("https://www.flipkart.com/");
        Wrappers obj = new Wrappers(driver);
        obj.searchProduct("Washing Machine");
        WebElement PopularityButton = driver.findElement(By.xpath("//div[contains(text(),'Popularity')]"));
        obj.clickOnMe(PopularityButton);
        obj.countLessThanFour();
        System.out.println("TestCase01: End the testCase01"); 
    }

    @Test
    public void testCase02() throws InterruptedException {
        System.out.println("TestCase02: Started the testCase02");
        driver.get("https://www.flipkart.com/");
        Wrappers obj = new Wrappers(driver);
        obj.closingLoginPopUP();
        obj.searchProduct("iPhone");
        obj.printNameAndDiscount();
        System.out.println("TestCase02: Ended the testCase02");
    }

    
    @Test
    public void testCase03() throws InterruptedException {
        System.out.println("TestCase03: Started the testCase03");
        driver.get("https://www.flipkart.com/");
        Wrappers obj = new Wrappers(driver);
        obj.closingLoginPopUP();
        obj.searchProduct("Coffee Mug");
        WebElement rating4AndAboveElement = driver.findElement(By.xpath("//label[@class='tJjCVx _3DvUAf']/div[2][contains(text(),'4')]"));
        obj.clickOnMe(rating4AndAboveElement);
        List<String> result = obj.titleAndImageUrl();
        for (String s : result) {
            System.out.println(s);
        }
        System.out.println("Ending Test Case 03");
    }


    @AfterTest
    public void endTest() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}