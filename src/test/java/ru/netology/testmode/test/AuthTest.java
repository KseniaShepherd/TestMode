package ru.netology.testmode.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.testmode.page.LoginPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.testmode.data.DataGenerator.Registration.getRegisteredUser;
import static ru.netology.testmode.data.DataGenerator.Registration.getUser;
import static ru.netology.testmode.data.DataGenerator.getRandomLogin;
import static ru.netology.testmode.data.DataGenerator.getRandomPassword;

class AuthTest {

    @BeforeEach
    void setup() {
        open("http://127.0.0.1:9999");
    }

    @Test
    @DisplayName("Should successfully login with active registered user")
    void shouldSuccessfulLoginIfRegisteredActiveUser() {
        var registeredUser = getRegisteredUser("active");
        var loginPage = new LoginPage();
        loginPage.login(registeredUser.getLogin(), registeredUser.getPassword());
        $("[id=root]").shouldHave(Condition.text("Личный кабинет"), Duration.ofSeconds(10));
    }

    @Test
    @DisplayName("Should get error message if login with not registered user")
    void shouldGetErrorIfNotRegisteredUser() {
        var notRegisteredUser = getUser("active");
        var loginPage = new LoginPage();
        loginPage.login(notRegisteredUser.getLogin(), notRegisteredUser.getPassword());
        $("[data-test-id=error-notification").shouldHave(Condition.text("Неверно указан логин или пароль"));

    }

    @Test
    @DisplayName("Should get error message if login with blocked registered user")
    void shouldGetErrorIfBlockedUser() {
        var blockedUser = getRegisteredUser("blocked");
        var loginPage = new LoginPage();
        loginPage.login(blockedUser.getLogin(), blockedUser.getPassword());
        $("[data-test-id=error-notification").shouldHave(Condition.text("Пользователь заблокирован"));
    }

    @Test
    @DisplayName("Should get error message if login with wrong login")
    void shouldGetErrorIfWrongLogin() {
        var registeredUser = getRegisteredUser("active");
        var wrongLogin = getRandomLogin();
        var loginPage = new LoginPage();
        loginPage.login(wrongLogin, registeredUser.getPassword());
        $("[data-test-id=error-notification").shouldHave(Condition.text("Неверно указан логин или пароль"));
    }

    @Test
    @DisplayName("Should get error message if login with wrong password")
    void shouldGetErrorIfWrongPassword() {
        var registeredUser = getRegisteredUser("active");
        var wrongPassword = getRandomPassword();
        var loginPage = new LoginPage();
        loginPage.login(registeredUser.getLogin(), wrongPassword);
        $("[data-test-id=error-notification").shouldHave(Condition.text("Неверно указан логин или пароль"));
    }
}