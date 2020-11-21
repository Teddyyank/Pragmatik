package proba;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
//import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.nio.channels.SelectableChannel;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class BackEndURL {
    WebDriver driver;

    /**
     * Preconditions necessary to execute the test cases
     */
    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/home/teddy/tools/chromedriver/chromedriver-87");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://shop.pragmatic.bg/admin/");

        WebElement usernameInput = driver.findElement(By.id("input-username"));
        usernameInput.sendKeys("admin");
        // shorter, but less understandable
        // driver.findElement(By.id("input-username")).sendKeys("admin");

        WebElement passwordInput = driver.findElement(By.id("input-password"));
        passwordInput.sendKeys("parola123!");

        driver.findElement(By.xpath("//div/button[@class='btn btn-primary']")).click();
    }

    /**
     * Locate button "Sales" and verify its' 'class' attribute equals the expected value _before_ clicking.
     */
    @Test
    public void salesPreClickAttribute() {
        WebElement salesButton = driver.findElement(By.xpath("//li[@id='menu-catalog']/a"));
        String salesClass = salesButton.getAttribute("class");
        assertEquals(salesClass, "parent collapsed");
    }

    /**
     * Locate button "Sales" and verify its' 'class' attribute equals the expected value _after_ clicking.
     */
    @Test
    public void salesPostClickAttribute() {
        // Find and click on Sales menu/category
        driver.findElement(By.xpath("//li[@id='menu-sale']/a")).click();
        WebElement salesCollapsed = driver.findElement(By.xpath("//li[@id='menu-sale']/a"));
        String salesCollapsedClass = salesCollapsed.getAttribute("class");
        assertEquals(salesCollapsedClass, "parent");
    }

    /**
     * After clicking the "Orders" button, the //ul/li class should be "Active"
     */
    @Test
    public void ordersUlActive() {
        // Find and click on Orders Button
        //WebElement ne6to = driver.findElement(By.xpath("//ul[@id='collapse4']/li[1]"));


        //List<String> options = Arrays.asList(new String[] {"", "Missing orders", "sdsad", "dss"});

        // Get Orders button attributes after collapsing
        WebElement orderButton = driver.findElement(By.xpath("//*[@id='collapse4']/li[1]"));
        String orderDropDown = orderButton.getAttribute("class");
        assertEquals(orderDropDown, "active");


    }

    @Test
    public void selectCheck() {
        // Check if URL matches pattern
        //driver.getCurrentUrl(); + http://shop.pragmatic.bg/admin/index.php?route=sale/order&user_token=1xB7qzmvdw9A4W6g5xqyY3KTmifrcyf7
        System.out.println(driver.getCurrentUrl());
        Assert.assertTrue(driver.getCurrentUrl().contains("sale/order"));

        // Find and store select "Order Status" via xpath
        Select orderStatus = new Select(driver.findElement(By.xpath("//select[@id='input-order-status']")));

        // Check that no multiple options can be selected
        Assert.assertFalse(orderStatus.isMultiple());


      //  List<String> exp_sel_options = Arrays.asList(new String[]{"","Missing Orders","Canceled","Canceled reversal","Chargeback","Complete","Denied","Expired","Failed","Pending","Processed","Processing","Refunded","Reversed","Shipped","Voided"});
        List<String> act_sel_options = new ArrayList<>();

    //    List<WebElement> allOptions = make.getOptions();


        for (WebElement option : orderStatus.getOptions()){
            act_sel_options.add(option.getText());
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        System.out.println(act_sel_options.toArray().toString());
        assertEquals(act_sel_options.toArray(), 1); // both rows are the same?
        assertEquals(act_sel_options.toArray(), act_sel_options.toArray());

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }


//    @Test
//    public void dropDown() {
//
//        WebElement dropDownOS = driver.findElement(By.name("order"));
//
//       // Select
//
//    }


    @Test
    public void MultipleSelectList() {

        Select filter_order_status_id = new Select(driver.findElement(By.xpath("//select")));

        System.out.println(filter_order_status_id.toString());
        assertEquals(filter_order_status_id.getOptions().size(), 16);


    }






    /**
     * Close chromedriver after the tests have completed.
     */
    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
