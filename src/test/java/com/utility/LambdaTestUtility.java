package com.utility;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LambdaTestUtility {
    public static final String HUB_URL = "https://hub.lambdatest.com/wd/hub";
    private static ThreadLocal<WebDriver> driverLocal = new ThreadLocal<WebDriver>();
    private static ThreadLocal<DesiredCapabilities> capabilitiesLocal = new ThreadLocal<DesiredCapabilities>();

    @SuppressWarnings({ "null", "deprecation" })
    public static WebDriver initializeLambdaTestSession(String testName, String browser){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("browserVersion", "latest");
        Map<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("user", "shivajyothipravallikacse599");
        ltOptions.put("accessKey", "LT_x6HS0XRxcXIaPvPqlC1S4tR4wXmUCTH9kpJ3Wq8GNN8hCOJ");
        ltOptions.put("build", "Selenium 4");
        ltOptions.put("name", testName);
        ltOptions.put("platformName", "Windows 10");
        ltOptions.put("seCdp", true);
        ltOptions.put("selenium_version", "latest");
        capabilities.setCapability("LT:Options", ltOptions);
        capabilitiesLocal.set(capabilities);
        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL(HUB_URL), capabilitiesLocal.get());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driverLocal.set(driver);
        return driverLocal.get();
    }

    public static void quitSession(){
        if(driverLocal.get() != null){
            driverLocal.get().quit();
        }
    }


}
