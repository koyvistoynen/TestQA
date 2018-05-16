package ru.koyvistoynen.denis;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FirstTest {
    // Создаем объект драйвера для браузера Chrome
    private static WebDriver driver;

    @BeforeClass
    public static void setup() {
        //Прописываем настройки в свойства и указываем нахождение chromdriver
        System.setProperty("webdriver.chrome.driver", "I:/Project/firstest/src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();  //Разворачиваем окно на весь экран
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // устанавливаем неявное ожидаение элементов в 10 секунд
        driver.get("https://www.ebay.com"); // Открываем главную страницу сайта
    }
    @Test
    public void userLogin() {
        //переходим в личный кабинет
        WebElement MyAccaunt = driver.findElement(By.id("gh-eb-My"));
        MyAccaunt.click();
        //Выбираем поле логина и вновим туда свою учетку
        WebElement loginField = driver.findElement(By.id("userid"));
        loginField.sendKeys("denis@koyvistoynen.ru");
        //Выбираем поле пароля и вновим свой пароль
        WebElement passwordField = driver.findElement(By.id("pass"));
        passwordField.sendKeys("1PNGZL1p");
        //Подтверждаем вход в систему
        WebElement loginButton = driver.findElement(By.id("sgnBt"));
        loginButton.click();
        //Переходим на главную страницу ebay
        WebElement IndexPage = driver.findElement(By.id("gh-la"));
        IndexPage.click();
        //Вводим в строку поиска blackberry
        WebElement search = driver.findElement(By.id("gh-ac"));
        search.sendKeys("blackberry");
        //Нажимаем кнопку найти
        WebElement searchButton = driver.findElement(By.id("gh-btn"));
        searchButton.click();
        //Просматривам коллекцию элементов - если их не 50 вызываем ошибку теста
        List<WebElement> elements = driver.findElement(By.id("ListViewInner")).findElements(By.className("sresult"));
        if (elements.size() != 50) {
            Assert.fail();
        }
        //Выходим с учетной записи
        WebElement LogOutButton = driver.findElement(By.id("gh-ug"));
        LogOutButton.click();
        WebElement LogOut = driver.findElement(By.xpath("//*[@id=\"gh-uo\"]/a"));
        LogOut.click();
        //Проверяем что действительно вышли - ждём надписи
        WebElement AcceptLogOut = driver.findElement(By.className("ds3pHTxt"));
        String textAccept = AcceptLogOut.getText();
        Assert.assertEquals("Выход успешно выполнен. До скорой встречи.", textAccept);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
