package pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.Alert;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.confirm;

/**
 * Created by yana on 30.05.15.
 */
public class diaspora {

    public static ElementsCollection contentsList =  $$(".stream_element.loaded");
    public static ElementsCollection aspectsList =  $$(".selectable");

    @Step
    public static void openMyActivity() {
        $(byText("My activity")).click();
    }

    @Step
    public static void openMyMentions() {
        $(byText("@Mentions")).click();
    }

    @Step
    public static void openFollowedtags() {
        $(byText("#Followed tags")).click();
    }

    @Step
    public static void openMyAspects() {
        $(byText("My aspects")).click();
    }

    @Step
    public static void shareNews(){
        $("#submit").click();
    }

    @Step
    public  static void writeText(String newText){
        $("#status_message_fake_text").setValue(newText);
    }

    @Step
    public  static void enterLogin(String email){
        $("#user_username").setValue(email).pressEnter();
    }

    @Step
    public static void enterPassword(String password){
        $("#user_password").setValue(password).pressEnter();
    }

    @Step
    public static void addNewAspects(String nameAspect) {
        aspectsList.find(text("+ Add an aspect")).click();
        $("#aspect_name").setValue(nameAspect).pressEnter();
    }

    @Step
    public static void commentPublic(String comments){
        $(byText("Comment")).click();
        $("[name='text']").setValue(comments);
        $("[name='commit']").shouldBe(visible).click();
    }

    @Step
    public static void userPopupMenu(String nameMenuItem){
        $(".user-menu-trigger").click();
        $(byText(nameMenuItem)).click();
    }

    @Step
     public static void deletePost(String postText) {
        contentsList.find(text(postText)).hover().find(".entypo.trash").click();
        confirm("Are you sure?");
    }
}
