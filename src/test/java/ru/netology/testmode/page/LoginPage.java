package ru.netology.testmode.page;

import ru.netology.testmode.data.DataGenerator;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    public void login(DataGenerator.RegistrationDto user) {
        $("[data-test-id=login] input").setValue(user.getLogin());
        $("[data-test-id=password] input").setValue(user.getPassword());
        $("[data-test-id=action-login]").click();
    }

    public void loginWithWrongLogin(DataGenerator.RegistrationDto user, String wrongLogin) {
        $("[data-test-id=login] input").setValue(wrongLogin);
        $("[data-test-id=password] input").setValue(user.getPassword());
        $("[data-test-id=action-login]").click();
    }

    public void loginWithWrongPassword(DataGenerator.RegistrationDto user, String wrongPassword) {
        $("[data-test-id=login] input").setValue(user.getLogin());
        $("[data-test-id=password] input").setValue(wrongPassword);
        $("[data-test-id=action-login]").click();
    }

}
