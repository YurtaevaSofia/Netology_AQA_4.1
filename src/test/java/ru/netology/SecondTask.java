package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class SecondTask {

    @Test
    void shouldRegister() {

        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Ка");
        $(withText("Абакан")).click();
        String dateMeeting = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("d"));
        $(".calendar-input__custom-control input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[class = 'input__icon']").click();
        if (Integer.parseInt(dateMeeting) < 4) {
            $("[data-step = '1']").click();
            $$("[role = 'gridcell']").find(exactText(dateMeeting)).click();
        }
        else {
            $$("[role = 'gridcell']").find(exactText(dateMeeting)).click();}
        $("[name='name']").setValue("Юртаева Софья");
        $("[name='phone']").setValue("+79991112233");
        $("[class='checkbox__box']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=notification]").waitUntil(Condition.visible, 15000).shouldHave(text("Встреча успешно забронирована на " + dateMeeting));
    }

}
