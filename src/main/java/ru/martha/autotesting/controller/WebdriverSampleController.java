package ru.martha.autotesting.controller;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.net.URL;
import java.util.List;

@ManagedBean
@ViewScoped
public class WebdriverSampleController implements Serializable {

    @Getter
    @Setter
    private String output;

    public void demo() {
        try {
            WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub/"), DesiredCapabilities.chrome());
            driver.get("https://www.google.com");
            WebElement element = driver.findElement(By.id("lst-ib"));
            element.sendKeys("google");
            element.submit();
            List<WebElement> titles = driver.findElements(By.cssSelector("div.g a"));
            output = "";
            titles.forEach(e -> output += e.getText() + " ");
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
