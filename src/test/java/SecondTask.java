import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class SecondTask {

    @Test
    void shouldRegister() {

        open("http://localhost:9999");

        $("[placeholder='Город']").setValue("Ка");
        $(withText("Абакан")).click();


        DateFormat df = new SimpleDateFormat("dd");
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.DATE, 3); //minus number would decrement the days
        String a = df.format(calender.getTime());
        $("[class = 'input__icon']").click();
        $$("[class = 'calendar__day calendar__day_state_current']").find(exactText(a)).click();


        $("[name='name']").setValue("Софья");

        $("[name='phone']").setValue("+79991112233");

        $("[class='checkbox__box']").click();

        $$("button").find(exactText("Забронировать")).click();

        $(withText("Успешно")).waitUntil(Condition.visible, 15000);
    }

}
