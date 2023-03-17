// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class DeleteWishlistTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() throws MalformedURLException {
    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.chrome());
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void deleteWishlist() {
    driver.get("https://www.amazon.ca/");
    driver.manage().window().setSize(new Dimension(1200, 777));
    driver.findElement(By.cssSelector("#nav-signin-tooltip .nav-action-inner")).click();
    driver.findElement(By.id("ap_email")).sendKeys("mdsadat.3115@gmail.com");
    driver.findElement(By.cssSelector(".a-button-inner > #continue")).click();
    driver.findElement(By.id("ap_password")).sendKeys("Sadatsakib3115@");
    driver.findElement(By.id("ap_password")).sendKeys(Keys.ENTER);
    {
      WebElement element = driver.findElement(By.id("nav-link-accountList"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    driver.findElement(By.cssSelector("#nav-al-wishlist > .nav-link:nth-child(3) > .nav-text")).click();
    driver.findElement(By.cssSelector("#overflow-menu-popover-trigger > .aok-inline-block:nth-child(2)")).click();
    {
      WebElement element = driver.findElement(By.cssSelector(".a-modal-scroller"));
      Actions builder = new Actions(driver);
      builder.doubleClick(element).perform();
    }
    {
      WebElement element = driver.findElement(By.cssSelector(".a-modal-scroller"));
      Actions builder = new Actions(driver);
      builder.doubleClick(element).perform();
    }
    {
      WebElement element = driver.findElement(By.cssSelector("#overflow-menu-popover-trigger > .aok-inline-block:nth-child(2)"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    driver.findElement(By.id("editYourList")).click();
    js.executeScript("window.scrollTo(0,400)");
    {
      WebElement element = driver.findElement(By.cssSelector(".a-spacing-base > .a-button-inner > .a-button-input"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    {
      WebElement element = driver.findElement(By.cssSelector(".a-spacing-base > .a-button-inner > .a-button-input"));
      Actions builder = new Actions(driver);
      builder.doubleClick(element).perform();
    }
    {
      WebElement element = driver.findElement(By.name("submit.save"));
      Actions builder = new Actions(driver);
      builder.doubleClick(element).perform();
    }
  }
}