import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class YuliaKonkovaTest {
    //открыть страницу openweather
    //набрать в строке поиска город Paris
    //нажать пункт меню Search
    //из выпадающего списка выбрать paris, fr
    //подтвердить что заголовок изменился на paris fr
    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {//требования к названию теста - что где когда (мы тестируем)
        System.setProperty("webdriver.chrome.driver", "/Users/juliafedosova/Desktop/Java_2022/chromedriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        //arrange
        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        //act
        driver.get(url);
        Thread.sleep(3000);
        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder='Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);
        WebElement searchButton = driver.findElement(
                By.xpath("//div[@id='weather-widget']//button[@type='submit']")
        );
        searchButton.click();

        Thread.sleep(3000);
        WebElement parisFRChoiceInDropdownMenu = driver.findElement(
                By.xpath("//ul[@class='search-dropdown-menu']/li/span[text()='Paris, FR ']")
        );
        parisFRChoiceInDropdownMenu.click();
        Thread.sleep(3000);

        WebElement h2CityNameHeader = driver.findElement(By.xpath("//div[@id='weather-widget']//h2"));

        Thread.sleep(1000);
        String actualResult = h2CityNameHeader.getText();

        //assert
        Assert.assertEquals(actualResult,expectedResult);

        driver.quit();
    }

    /** TC_11_01
     * 1.  Открыть базовую ссылку
     2.  Нажать на пункт меню Guide
     3.  Подтвердить, что вы перешли на страницу со ссылкой
     https://openweathermap.org/guide и что title этой страницы OpenWeatherMap API guide - OpenWeatherMap*/
    @Test
    public void testGuidePageReference() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/juliafedosova/Desktop/Java_2022/chromedriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "https://openweathermap.org/guide";
        String expectedResult1 = "OpenWeatherMap API guide - OpenWeatherMap";

        driver.get(url);
        Thread.sleep(4000);
        WebElement guideMenu = driver.findElement(
                By.xpath("//div[@id='desktop-menu']//ul/li/a[@href='/guide']")
        );
        guideMenu.click();
        Thread.sleep(4000);
        String actualResult = driver.getCurrentUrl();
        Assert.assertEquals(actualResult,expectedResult);

        String actualResult1 = driver.getTitle();
        Assert.assertEquals(actualResult1, expectedResult1);

        driver.quit();
    }

    /** TC_11_02
     *
     */
    @Test
    public void temperatureInFarenheitOnMainPage() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/juliafedosova/Desktop/Java_2022/chromedriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        Boolean expectedResult = true;

        driver.get(url);
        Thread.sleep(4000);
        WebElement changeUnit = driver.findElement(
                By.xpath("//div[@class='switch-container']/div[@class='option' and text()=\"Imperial: °F, mph\"]")
        );
        changeUnit.click();
        Thread.sleep(2000);
        WebElement text1 = driver.findElement(By.xpath("//span[@class='heading']"));
        Boolean actualResult = driver.findElement(By.xpath("//span[@class='heading']")).getText().contains("F");
        Assert.assertEquals(actualResult,expectedResult);

        driver.quit();
    }

    /** TC_11_03
     1. Открыть базовую ссылку
     2. Подтвердить, что внизу страницы есть панель с текстом
     “We use cookies which are essential for the site to work.
     We also use non-essential cookies to help us improve our services.
     Any data collected is anonymised. You can allow all cookies or manage them individually.”
     3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies” */
    @Test
    public void panelBelowAndTwoButtonsOnIt() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/juliafedosova/Desktop/Java_2022/chromedriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "We use cookies which are essential for the site to work. We also use non-essential cookies to help us improve our services. Any data collected is anonymised. You can allow all cookies or manage them individually.";
        String expectedResult1 = "button";
        String expectedResult2 = "Allow all";
        String expectedResult3 = "Manage cookies";

        driver.get(url);
        Thread.sleep(7000);
        WebElement textBelow = driver.findElement(By.xpath("//div[@id='stick-footer-panel']//p"));
        String actualResult = driver.findElement(By.xpath("//div[@id='stick-footer-panel']//p")).getText();
        Assert.assertEquals(actualResult, expectedResult);

        WebElement button1 = driver.findElement(By.xpath("//div[@class='stick-footer-panel__btn-container']/button"));
        String actualResult1 = button1.getTagName();
        String actualResult2 = button1.getText();
        Assert.assertEquals(actualResult1,expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
        WebElement button2 = driver.findElement(By.xpath("//div[@class='stick-footer-panel__btn-container']/a"));
        String actualResult3 = button2.getText();
        Assert.assertEquals(actualResult3, expectedResult3);

        driver.quit();
    }

    /** TC_11_04
     1.  Открыть базовую ссылку
     2.  Подтвердить, что в меню Support есть 3 подменю с названиями “FAQ”, “How to start” и “Ask a question”*/
    @Test
    public void menuSupportHasThreeSubmenu() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/juliafedosova/Desktop/Java_2022/chromedriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult1 = "FAQ";
        String expectedResult2 = "How to start";
        String expectedResult3 = "Ask a question";

        long start = 0;
        long end = 0;
        start = System.nanoTime();
        driver.get(url);
        Thread.sleep(6000);

        WebElement support = driver.findElement(By.xpath("//div[@id='support-dropdown']"));
        support.click();
        ArrayList list = new ArrayList();
        list = (ArrayList) driver.findElements(By.xpath("//ul[@class='dropdown-menu dropdown-visible']/li"));
        int expectedResult = 3;
        int actualResult = list.size();
        Assert.assertEquals(actualResult,expectedResult);

        String actualResult1 = driver.findElement(
                By.xpath("//ul[@class='dropdown-menu dropdown-visible']/li/a[@href='/faq']")
        ).getText();
        String actualResult2 = driver.findElement(
                By.xpath("//ul[@class='dropdown-menu dropdown-visible']/li/a[@href='/appid']")
        ).getText();
        String actualResult3 = driver.findElement(
                By.xpath("//ul[@class='dropdown-menu dropdown-visible']/li/a[@href='https://home.openweathermap.org/questions']")
        ).getText();
        Assert.assertEquals(actualResult1,expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
        Assert.assertEquals(actualResult3, expectedResult3);

        end = System.nanoTime();
        System.out.println(end - start);
        driver.quit();
    }

    /** TC_11_05
     1. Открыть базовую ссылку
     2. Нажать пункт меню Support → Ask a question
     3. Заполнить поля Email, Subject, Message
     4. Не подтвердив CAPTCHA, нажать кнопку Submit
     5. Подтвердить, что пользователю будет показана ошибка “reCAPTCHA verification failed, please try again.”*/
    @Test
    public void inSupportClickAskFillFieldsCLickSubmit() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/juliafedosova/Desktop/Java_2022/chromedriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String email = "koko@yandex.ru";
        String subject = "About Koko";
        String message = "Kindly remind you to send us new dresses";
        String expectedResult = "reCAPTCHA verification failed, please try again.";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(6000);

        WebElement support = driver.findElement(By.xpath("//div[@id='support-dropdown']"));
        support.click();
        WebElement askQuestion = driver.findElement(By.xpath
                ("//ul[@class='dropdown-menu dropdown-visible']/li/a[@href='https://home.openweathermap.org/questions']")
        );
        askQuestion.click();
        Thread.sleep(5000);
        ArrayList<String> page = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(page.get(1));

        WebElement fillEmail = driver.findElement(By.xpath("//input[@class='form-control string email required']"));
        fillEmail.click();
        fillEmail.sendKeys(email);
        WebElement fillChooseSubject = driver.findElement(By.xpath("//select[@class='form-control select required']"));
        fillChooseSubject.click();
        WebElement chosenSubject = driver.findElement(By.xpath("//option[@value='Sales']"));
        chosenSubject.click();
        WebElement fillAskQuestion = driver.findElement(By.xpath("//textarea[@class='form-control text required']"));
        fillAskQuestion.click();
        fillAskQuestion.sendKeys(message);
        WebElement submitField = driver.findElement(By.xpath(" //input[@type='submit' and @name='commit']"));
        submitField.click();
        Thread.sleep(5000);
        String actualResult = driver.findElement(By.xpath("//div[@class='help-block']")).getText();
        Assert.assertEquals(actualResult,expectedResult);

        driver.quit();
    }

    /** TC_11_06
     1.  Открыть базовую ссылку
     2.  Нажать пункт меню Support → Ask a question
     3.  Оставить значение по умолчанию в checkbox Are you an OpenWeather user?
     4. Оставить пустым поле Email
     5. Заполнить поля  Subject, Message
     6. Подтвердить CAPTCHA
     7. Нажать кнопку Submit
     8. Подтвердить, что в поле Email пользователю будет показана ошибка “can't be blank”*/
    @Test
    public void askQuestionDoNotFillEmail() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/juliafedosova/Desktop/Java_2022/chromedriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String subject = "About Koko";
        String message = "Kindly remind you to send us new dresses";
        String expectedResult = "can't be blank";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(6000);

        WebElement support = driver.findElement(By.xpath("//div[@id='support-dropdown']"));
        support.click();
        WebElement askQuestion = driver.findElement(By.xpath
                ("//ul[@class='dropdown-menu dropdown-visible']/li/a[@href='https://home.openweathermap.org/questions']")
        );
        askQuestion.click();
        Thread.sleep(5000);
        ArrayList<String> page = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(page.get(1));

        WebElement fillChooseSubject = driver.findElement(By.xpath("//select[@class='form-control select required']"));
        fillChooseSubject.click();
        WebElement chosenSubject = driver.findElement(By.xpath("//option[@value='Sales']"));
        chosenSubject.click();
        WebElement fillAskQuestion = driver.findElement(By.xpath("//textarea[@class='form-control text required']"));
        fillAskQuestion.click();
        fillAskQuestion.sendKeys(message);
        Thread.sleep(10000);
        String window2 = driver.getWindowHandle();

        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[title='reCAPTCHA']")));

        WebElement enterCaptcha = driver.findElement(By.xpath(
                "//span[@class='recaptcha-checkbox goog-inline-block recaptcha-checkbox-unchecked "
                        + "rc-anchor-checkbox']"));
        enterCaptcha.click();

        Thread.sleep(10000);

        driver.switchTo().window(window2);
//        WebElement captcha = driver.findElement(By.xpath("//div[@class='recaptcha-checkbox-spinner']"));
//        captcha.click();
        WebElement submitField = driver.findElement(By.xpath(" //input[@type='submit' and @name='commit']"));
        submitField.click();
        Thread.sleep(5000);
        String actualResult = driver.findElement(By.xpath("//span[@class='help-block']")).getText();
        Assert.assertEquals(actualResult,expectedResult);

        driver.quit();
    }

    /** TC_11_07
     1.  Открыть базовую ссылку
     2.  Нажать на единицы измерения Imperial: °F, mph
     3.  Нажать на единицы измерения Metric: °C, m/s
     4.  Подтвердить, что в результате этих действий, единицы измерения температуры изменились с F на С */
    @Test
    public void changeUnitsTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/juliafedosova/Desktop/Java_2022/chromedriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        Boolean expectedResult = true;

        driver.get(url);
        Thread.sleep(7000);
        WebElement changeUnit = driver.findElement(
                By.xpath("//div[@class='switch-container']/div[@class='option' and text()=\"Imperial: °F, mph\"]")
        );
        changeUnit.click();
        WebElement changeUnitC = driver.findElement(By.xpath("//div[@class='switch-container']/div[@class='option' and text()=\"Metric: °C, m/s\"]"));
        changeUnitC.click();
        WebElement text = driver.findElement(By.xpath("//span[@class='heading']"));
        Boolean actualResult = driver.findElement(By.xpath("//span[@class='heading']")).getText().contains("C");
        Assert.assertEquals(actualResult,expectedResult);

        driver.quit();
    }

    /** TC_11_08
     * 1.  Открыть базовую ссылку
     2.  Нажать на лого компании
     3.  Дождаться, когда произойдет перезагрузка сайта, и подтвердить, что текущая ссылка не изменилась*/
    @Test
    public void affirmReferenceIsSame() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/juliafedosova/Desktop/Java_2022/chromedriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = url;

        driver.get(url);
        Thread.sleep(4000);
        WebElement logoClick = driver.findElement(
                By.xpath("//img[@src='/themes/openweathermap/assets/img/logo_white_cropped.png']")
        );
        logoClick.click();
        Thread.sleep(4000);
        String actualResult = driver.getCurrentUrl();
        Assert.assertEquals(actualResult,expectedResult);

        driver.quit();
    }

    /** TC_11_09
     * 1.  Открыть базовую ссылку
     2.  В строке поиска в навигационной панели набрать “Rome”
     3.  Нажать клавишу Enter
     4.  Подтвердить, что вы перешли на страницу в ссылке которой содержатся слова “find” и “Rome”
     5. Подтвердить, что в строке поиска на новой странице вписано слово “Rome”*/
    @Test
    public void Romesearch() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/juliafedosova/Desktop/Java_2022/chromedriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String city = "Rome";
        String find = "find";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(6000);
        WebElement navigationPanel = driver.findElement(By.xpath("//div[@id='desktop-menu']//input[@type='text']"));
        navigationPanel.click();
        navigationPanel.sendKeys(city);
        navigationPanel.sendKeys(Keys.ENTER);
        Thread.sleep(7000);
        String referenceField = driver.getCurrentUrl().toLowerCase();
        boolean expectedResult = true;
        boolean actualResult = false;
        if (referenceField.contains("find".toLowerCase()) && referenceField.contains("Rome".toLowerCase())){
            actualResult = true;
        }
        Assert.assertEquals(actualResult,expectedResult);

        driver.quit();
    }

    /** TC_11_10
     1.  Открыть базовую ссылку
     2.  Нажать на пункт меню API
     3.  Подтвердить, что на открывшейся странице пользователь видит 30 оранжевых кнопок*/

    @Test
    public void menuApiConsistsThirtyOrangeButtons() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/juliafedosova/Desktop/Java_2022/chromedriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        driver.get(url);
        Thread.sleep(7000);
        WebElement apiField = driver.findElement(By.xpath("//div[@id='desktop-menu']//a[@href='/api']"));
        apiField.click();
        Thread.sleep(7000);

        ArrayList<String> list = new ArrayList<String>();
        list = (ArrayList) driver.findElements(By.xpath("//a[@type='button']"));
        String[] list1 = new String[list.size()];

        int count = 0;
        for (int i = 0; i < list.size(); i++){
            if (list.contains("button") && list.contains("orange")){
            }
        }
        boolean expectedResult = true;
        boolean actualResult = true;
        if (count == 30){
            actualResult = true;
        } else{
            actualResult = false;
        }
        Assert.assertEquals(actualResult,expectedResult);

        driver.quit();
    }




//    @Test
//    public void testH2TagText_WhenSearchingCityCountry(){
//        System.setProperty("webdriver.chrome.driver", "/Users/juliafedosova/Desktop/Java_2022/chromedriver");
//        WebDriver driver = new ChromeDriver();
    //
    //
    //driver.quit();
    //        driver.close();
//    }
}
