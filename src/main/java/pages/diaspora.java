package pages;

import com.codeborne.selenide.ElementsCollection;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

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
    public  static void writeText(String newText){
        $("#status_message_fake_text").setValue(newText);
        $("#submit").click();
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
    @Step
    public static void cencelDeletePost(String postTest){
        contentsList.find(text(postTest)).hover().find(".entypo.trash").click();
        dismiss("Are you sure?");
    }
    @Step
    public static void likePost(String postText){
        contentsList.find(text(postText)).find(".like").click();
    }

    @Step
    public static void openContactPage(String nameContact) {
        $$(".name").find(text(nameContact)).click();
    }

    @Step
    public static void pressMessageIconInUsrPage(){
        $("#message_button").click();
    }
    @Step
    public static void creatNewMessage(String messageText){
        $("#conversation_text").setValue(messageText);
        $(".btn.btn-primary.creation").click();
    }

    @Step
    public static void changePassword(String oldPassword, String newPassword, String newPasswordAgain) {
        $("#user_current_password").setValue(oldPassword);
        $("#user_password").setValue(newPassword);
        $("#user_password_confirmation").setValue(newPasswordAgain);
        $("[name = 'change_password']").click();
    }

    @Step
    public static void changeLanguage(String newLanguage) {
        userPopupMenu("Settings");
        $("#section_header>h2").shouldHave(text("Settings"));
        $("#user_language").click();
        $(byText(newLanguage)).click();
        $("[value='Change language']").click();
    }
    @Step
    public static void addDiscription(String world) {
        $("#tags").setValue(world).pressEnter();
    }
    @Step
    public static void editUserName(String newUserName) {
        $("#profile_first_name").setValue(newUserName);
    }
    @Step
    public static void editUserBio(String newUserBio) {
        $("#profile_bio").setValue(newUserBio);
    }
    @Step
    public static void editUserLocal(String newLocal) {
        $("#profile_location").setValue(newLocal);
    }
    @Step
    public static void editUserGender(String newUserGender) {
        $("#profile_gender").setValue(newUserGender);
    }
    @Step
    public static void editUserBerthday(String year, String month, String day) {
        $("#profile_date_year").click();
        $(byText(year)).click();
        $("#profile_date_month").click();
        $(byText(month)).click();
        $("#profile_date_day").click();
        $(byText(day)).click();
    }
}
