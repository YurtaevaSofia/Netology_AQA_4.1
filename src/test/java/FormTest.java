import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class FormTest {

    @Test
    void shouldRegister() {

        open("http://localhost:9999");

        $("[placeholder='Город']").setValue("Москва");

        DateFormat df = new SimpleDateFormat("dd.MM.yy");
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.DATE, 3); //minus number would decrement the days
        String a = df.format(calender.getTime());
        System.out.println(a);
        $("[placeholder='Дата встречи']").setValue(a);

        $("[name='name']").setValue("Софья");

        $("[name='phone']").setValue("+79991112233");

        $("[class='checkbox__box']").click();

        $$("button").find(exactText("Забронировать")).click();

        $(withText("Успешно")).waitUntil(Condition.visible, 15000);
    }


}
