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
public class AddWishlistItemTest {
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
  public void addWishlistItem() {
    driver.get("https://www.amazon.ca/");
    driver.manage().window().setSize(new Dimension(1200, 777));
    driver.findElement(By.cssSelector(".feed-carousel-card:nth-child(1) .\\_deals-shoveler-v2_style_dealImagePrimavera__39h59")).click();
    driver.findElement(By.cssSelector(".a-list-normal:nth-child(2) .a-link-normal > .a-section")).click();
    js.executeScript("window.scrollTo(0,1500)");
    driver.findElement(By.linkText("Add to Wish List")).click();
    driver.findElement(By.id("ap_email")).sendKeys("aqw.galanoth@gmail.com");
    driver.findElement(By.cssSelector(".a-button-inner > #continue")).click();
    driver.findElement(By.id("ap_password")).sendKeys("FinalAssignment4");
    driver.findElement(By.id("ap_password")).sendKeys(Keys.ENTER);
    js.executeScript("window.scrollTo(0,1500)");
    driver.findElement(By.id("add-to-wishlist-button-submit")).click();
    driver.findElement(By.cssSelector(".a-button-primary .a-declarative")).click();
  }
}
