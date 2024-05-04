import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.Select
import utils.Utils

class test {

    companion object {
        private lateinit var driver: ChromeDriver
        private lateinit var mail: String
        private lateinit var utils: Utils
        private final var TRUE_LOGIN_EMAIL = "curlymary07@gmail.com"
        private final var TRUE_LOGIN_PASSWD = "D9MMdftg"
        private final var FALSE_LOGIN_EMAIL = "curlymary0@gmail.com"
        private final var FALSE_LOGIN_PASSWD = "d9MMdftg"
    }

    @BeforeEach
    fun init(): Unit {
        driver = ChromeDriver()
        utils = Utils(driver);
        driver.get("https://www.mamba.ru/ru")
        mail = "bobzik" + (Math.random() * 100 + Math.random() * 10) + "@gmail.com"
        Thread.sleep(15000)
    }

    @AfterEach
    fun reInit() {
        driver.close()
    }

    @Test
    fun `try to register`() {
        driver.findElement(By.xpath("//*[@id=\"VideoBackgroundModalLayout\"]/div[2]/main/div/div/main/div[1]/div/div[2]/button")).click()
        Thread.sleep(500)
        driver.findElement(By.xpath("//*[@id=\"VideoBackgroundModalLayout\"]/div[2]/main/div/div/main/div[2]/div/button")).click()
        Thread.sleep(500)
        driver.findElement(By.xpath("//*[@id=\"VideoBackgroundModalLayout\"]/div[2]/main/div/div/main/div[4]/div/button")).click()
        Thread.sleep(500)
        driver.findElement(By.xpath("//*[@id=\"VideoBackgroundModalLayout\"]/div[2]/main/div/div/main/div[5]/div/button")).click()
        Thread.sleep(500)
        driver.findElement(By.xpath("//*[@id=\"VideoBackgroundModalLayout\"]/div[2]/main/div/div/main/form/div[1]/div/input"))
                .sendKeys("Arcadia")
        driver.findElement(By.xpath("//*[@id=\"VideoBackgroundModalLayout\"]/div[2]/main/div/div/main/form/div[3]/div/input"))
                .click()

        var day = driver.findElement(By.xpath("//*[@id=\"VideoBackgroundModalLayout\"]/div[2]/main/div/div/main/form/div[2]/div/div[1]/label/select"))
        var selectDay = Select(day)
        selectDay.selectByValue("2")
        var mounth = driver.findElement(By.xpath("//*[@id=\"VideoBackgroundModalLayout\"]/div[2]/main/div/div/main/form/div[2]/div/div[2]/label/select"))
        var selectMounth = Select(mounth)
        selectMounth.selectByValue("5")
        var year = driver.findElement(By.xpath("//*[@id=\"VideoBackgroundModalLayout\"]/div[2]/main/div/div/main/form/div[2]/div/div[3]/label/select"))
        var selectYear = Select(year)
        selectYear.selectByValue("1998")
        Thread.sleep(500)
        driver.findElement(By.xpath("//*[@id=\"VideoBackgroundModalLayout\"]/div[2]/main/div/div/main/form/div[4]/div/input"))
                .click()
        Thread.sleep(500)
        driver.findElement(By.xpath("//*[@id=\"VideoBackgroundModalLayout\"]/div[2]/main/div/div/main/form/div[1]/div/input"))
                .sendKeys(mail)
        driver.findElement(By.xpath("//*[@id=\"VideoBackgroundModalLayout\"]/div[2]/main/div/div/main/form/div[2]/div/button"))
                .click()
    }

    @Test
    fun `login with correct email and password` () {
        utils.login(TRUE_LOGIN_EMAIL, TRUE_LOGIN_PASSWD)
        Thread.sleep(1000)
        var mainPage = driver.findElement(By.xpath("//*[@id=\"app-wrapper\"]/div[3]/div/div[2]/div/div/section/div/nav/div[2]"))
        assertEquals(mainPage.text, "Знакомства")
    }

    @Test
    fun `login with correct email and incorrect password` () {
        utils.login(TRUE_LOGIN_EMAIL, FALSE_LOGIN_PASSWD)
        Thread.sleep(2000)
        var msg = driver.findElement(By.xpath("//*[@id=\"VideoBackgroundModalLayout\"]/div[4]/div/div/div/main/form/div[5]"))
        assertEquals(msg.text, "Неверно указан адрес электронной почты или пароль")
    }

    @Test
    fun `login with incorrect email and incorrect password` () {
        utils.login(FALSE_LOGIN_EMAIL, FALSE_LOGIN_PASSWD)
        Thread.sleep(1000)
        var msg = driver.findElement(By.xpath("//*[@id=\"VideoBackgroundModalLayout\"]/div[4]/div/div/div/main/form/div[5]"))
        assertEquals(msg.text, "Неверно указан адрес электронной почты или пароль")
    }


}