package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();

        //ChromeOptions
        ChromeOptions options = new ChromeOptions();

        //Cek config apakah headless atau  tidak
        String headlessMode = ConfigReader.get("headless");
        if ("true".equalsIgnoreCase(headlessMode)){
            options.addArguments("--headless=new"); // menggunakan engine bari supaya stabil
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
        }

        //Tambahan opsi (bisa digunakan baik headless atau non-headless)
        options.addArguments("--disable-nontifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get(ConfigReader.get("baseUrl"));

    }

    @AfterMethod
    public void tearDown() {
        if (driver != null){
            driver.quit();
        }
    }




}
