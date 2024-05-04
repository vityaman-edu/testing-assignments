import org.example.main
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import utils.MainPageActivity
import utils.Utils

class MambaTest {
    companion object {
        private lateinit var driver: ChromeDriver
        private lateinit var utils: Utils
        private lateinit var mainPage: MainPageActivity
    }

    @BeforeEach
    fun reInit(): Unit {
        driver = ChromeDriver()
        driver.get("https://www.mamba.ru/ru")
        utils = Utils(driver)
        mainPage = MainPageActivity(utils)
        Thread.sleep(10000)

    }

    @AfterEach
    fun kill(): Unit {
        driver.close()
    }

    /*
    Сценарий №1
        1) залогинился
        2) зашел в профиль
        3) нажал на шестеренку
        4) вышел
     */
    @Test
    fun `first use case` () {
        mainPage.mainPage()
        mainPage.logout()
    }


    /*
    Сценарий №2
        1) залогинился
        2) зашел в сообщения
        3) зашел в события
        4) посмотрел гостей*_*
     */
    @Test
    fun `second use case` () {
        mainPage.mainPage()
        mainPage.toMsg()
        mainPage.toGuests()
    }


    /*
    Сценарий №3
        1) залогинился
        2) скипнул 2 раза
        3) зашел в профиль
        4) вышел
     */
    @Test
    fun `third use case` () {
        mainPage.mainPage()
        mainPage.pressSkip()
        mainPage.pressSkip()
        mainPage.logout()
    }



    /*
    Сценарий №4
        1) залогинился
        2) зашел в сообщения
        3) вернулся на страничку со знакомствами
        4) лайкнул
        5) перешел в профиль
        6) вышел
     */
    @Test
    fun `fourth use case` () {
        mainPage.mainPage()
        mainPage.toMsg()
        mainPage.toDating()
        mainPage.pressLike()
        mainPage.logout()
    }

}