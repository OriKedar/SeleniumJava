import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import static org.openqa.selenium.By.name;

public class Main {

    private WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void run(){
        try {
            searchWix();
            openWix();
        } catch (Exception e){
            Assert.fail("Test failed with exception " + e);
        }
    }

    private void searchWix() {
        try {
            driver.get("https://www.google.com");
            driver.findElement(By.name("q")).sendKeys("Wix");
            driver.findElements(name("btnK")).get(1).click();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Failed to complete search " + e);
        }
    }

    private void openWix() {
        try {
            WebElement firstResult = driver.findElements(By.className("g")).get(0);
            firstResult.findElement(By.tagName("h3")).click();

            Assert.assertEquals((driver.getCurrentUrl()), "https://www.wix.com/");
        } catch (Exception e) {
            Assert.fail("Failed to open Wix.com " + e);
        }
    }
}
