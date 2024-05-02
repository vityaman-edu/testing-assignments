package utils

import org.openqa.selenium.By
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.NoSuchSessionException
import org.openqa.selenium.chrome.ChromeDriver

class MainPageActivity(var utils: Utils) {

    private final var driver = utils.driver
    companion object {
        private final var TRUE_LOGIN_EMAIL = "curlymary07@gmail.com"
        private final var TRUE_LOGIN_PASSWD = "D9MMdftg"
        private final var FALSE_LOGIN_EMAIL = "curlymary0@gmail.com"
        private final var FALSE_LOGIN_PASSWD = "d9MMdftg"
    }

    fun closeAnnoyingAlert() {
        // если алерт с предложением ЗАГРУЗИТЬ КАК МОЖНО БОЛЬШЕ ФОТО
        Thread.sleep(1000)
        try {
            driver.findElement(By.xpath("//*[@id=\"app\"]/div[3]/div/div/div/div[1]/button")).let{
                // закрыть алерт
                driver.findElement(By.xpath("//*[@id=\"app\"]/div[3]/div/div/div/div[1]/button")).click()
            }
        } catch (e:NoSuchSessionException) {

        } catch (e:NoSuchElementException) {

        }

        Thread.sleep(1000)

    }

    fun mainPage() {
        utils.login(TRUE_LOGIN_EMAIL, TRUE_LOGIN_PASSWD)
        closeAnnoyingAlert()
        Thread.sleep(2000)
    }

    fun toProfile() {
        closeAnnoyingAlert()
        Thread.sleep(1500)
        // press profile icon
        driver.findElement(By.xpath("//*[@id=\"app-wrapper\"]/div[1]/header/div[6]/a[1]"))
                .click()
        Thread.sleep(1500)


    }

    fun logout() {
        closeAnnoyingAlert()
        toProfile()
        Thread.sleep(1000)
        // press shesternya
        closeAnnoyingAlert()
        driver.findElement(By.xpath("//*[@id=\"app-wrapper\"]/div[3]/div/div[2]/div/section/nav/div[3]/div/a"))
                .click()
        Thread.sleep(500)
        //press log out
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[3]/div/div/div/main/a/div[1]/div[2]"))
                .click()
        Thread.sleep(500)
    }

    fun toMsg() {
        closeAnnoyingAlert()
        // press messages
        driver.findElement(By.xpath("//*[@id=\"app-wrapper\"]/div[1]/header/a[2]")).click()

        Thread.sleep(500)
    }

    fun toDating() {
        closeAnnoyingAlert()
        // press big MAMBA left corner
        driver.findElement(By.xpath("//*[@id=\"Fill-4\"]"))
                .click()

        Thread.sleep(500)
        closeAnnoyingAlert()
        Thread.sleep(500)
    }

    fun pressLike() {
        Thread.sleep(700)
        closeAnnoyingAlert()
        Thread.sleep(200)
        //press like
        driver.findElement(By.xpath("//*[@id=\"app-wrapper\"]/div[3]/div/div[2]/div/div/section/div/div[1]/div[7]/div[2]/div[1]/div[4]/div/button[3]"))
                    .click()
        Thread.sleep(500)
    }

    fun pressSkip() {
        Thread.sleep(700)
        closeAnnoyingAlert()
        Thread.sleep(200)
        //press skip
        driver.findElement(By.xpath("//*[@id=\"app-wrapper\"]/div[3]/div/div[2]/div/div/section/div/div[1]/div[7]/div[2]/div[1]/div[4]/div/button[2]"))
                .click()
        Thread.sleep(500)
    }

    fun toEvents() {
        closeAnnoyingAlert()
        // click to events
        driver.findElement(By.xpath("//*[@id=\"app-wrapper\"]/div[1]/header/a[1]"))
                    .click()
        Thread.sleep(500)
    }

    fun toGuests() {
        toEvents()
        driver.findElement(By.xpath("//*[@id=\"app-wrapper\"]/div[3]/div/div[2]/div[1]/div/div/div/a[2]"))
                .click()
        Thread.sleep(500)
    }

}