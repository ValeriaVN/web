package ru.netology.CardOrder;

import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;


public class CardOrderTest {


    @Test
    public void shouldSubmitRequest(){
       open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Александр Иванов");
        $("[data-test-id=phone] input").setValue("+74951001010");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

   }

    @Test
    public void shouldFieldErrorName(){
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Alex Ivanov");
        $("[data-test-id=phone] input").setValue("+74951001010");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    public void shouldFieldErrorPhone(){
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Александр Иванов");
        $("[data-test-id=phone] input").setValue("+74951001010000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    public void shouldErrorPersonalData(){
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Александр Иванов");
        $("[data-test-id=phone] input").setValue("+74951001010");
        $("[type=button]").click();
        $("[data-test-id=agreement].input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));

    }

}
