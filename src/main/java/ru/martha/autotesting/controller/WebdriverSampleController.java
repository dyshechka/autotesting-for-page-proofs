package ru.martha.autotesting.controller;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

@ManagedBean
@ViewScoped
public class WebdriverSampleController implements Serializable {

    @Getter
    @Setter
    private String output;

    public void demo() {
        try {
            WebDriver driver = new RemoteWebDriver(
                    new URL("http://localhost:4444/wd/hub/"),
                    DesiredCapabilities.chrome()
            );
            driver.get("https://www.google.com");
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            WebElement element = driver.findElement(By.id("lst-ib"));
            element.sendKeys("google");
            element.sendKeys(Keys.ENTER);
            List<WebElement> titles = driver.findElements(By.cssSelector("div.g a"));
            output = "";
            titles.forEach(e -> output += e.getText() + " ");
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
