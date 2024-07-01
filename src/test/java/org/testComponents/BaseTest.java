package org.testComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.pageObjects.LandingPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest  {

    public  WebDriver driver;
    public LandingPage lp;

    public WebDriver initializeDriver() throws IOException {


        // properties class
        Properties properties = new Properties();
        FileInputStream fileInputStream = new
                FileInputStream(System.getProperty("user.dir")+"//src//main//java//org//resources//GlobalData" +
                ".properties");
        properties.load(fileInputStream);
        String browserName = properties.getProperty("browser");

        if(browserName.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
             driver = new ChromeDriver();
        }else if(browserName.equalsIgnoreCase("firefox")){
             driver = new FirefoxDriver();
            driver.manage().window().maximize();
        }else if(browserName.equalsIgnoreCase("edge")){
            driver = new EdgeDriver();
            driver.manage().window().maximize();
        }else if(browserName.equalsIgnoreCase("safari")){
            driver = new SafariDriver();
            driver.manage().window().maximize();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        return driver;
    }


    public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {

        // read json to string
        String jsonContent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);

        // convert string to hashmap using jackson databind
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;

    }


    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
        driver = initializeDriver();
        lp = new LandingPage(driver);
        lp.goTo();
        return lp;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.close();
    }
}
