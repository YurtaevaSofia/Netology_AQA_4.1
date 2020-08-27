package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class FormTest {

    @Test
    void shouldRegister() {

        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Москва");
        String dateMeeting = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $(".calendar-input__custom-control input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(dateMeeting);
        $("[name='name']").setValue("Юртаева Софья");
        $("[name='phone']").setValue("+79991112233");
        $("[class='checkbox__box']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=notification]").waitUntil(Condition.visible, 15000).shouldHave(text("Встреча успешно забронирована на " + dateMeeting));
    }
}
