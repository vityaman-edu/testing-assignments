package utils

import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver


class Utils(var driver: ChromeDriver) {




    fun login(email: String, passwd: String) {
        driver.findElement(By.xpath("//*[@id=\"VideoBackgroundModalLayout\"]/div[2]/div[1]/header/div/a"))
                .click()
        Thread.sleep(500)
        driver.findElement(By.xpath("//*[@id=\"VideoBackgroundModalLayout\"]/div[4]/div/div/div/div/a[1]"))
                .click()
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"VideoBackgroundModalLayout\"]/div[4]/div/div/div/main/form/div[1]/div/input"))
                .sendKeys(email)
        driver.findElement(By.xpath("//*[@id=\"VideoBackgroundModalLayout\"]/div[4]/div/div/div/main/form/div[2]/div/div/input"))
                .sendKeys(passwd)
        driver.findElement(By.xpath("//*[@id=\"VideoBackgroundModalLayout\"]/div[4]/div/div/div/main/form/div[4]/input"))
                .click()
    }


}