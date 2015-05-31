package diasporaTests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Screenshots;
import com.google.common.io.Files;
import org.junit.Before;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static helpers.Helpers.dropCreateSeedDiasporaDB;
import static pages.diaspora.*;
/**
 * Created by yana on 30.05.15.
 */
public class AllDiasporaTests {

    @Before
    public void clearData() {
        dropCreateSeedDiasporaDB();
        open("http://localhost:3000/stream");
        Configuration.timeout = 20000;
    }

    @Test
    public void loginWritePostsLikePostTests() {

        // log in
        enterLogin("bob");
        enterPassword("evankorth");
        $(".user-name").shouldHave(text("Bob Grimm"));

        //create activity
        openMyActivity();
        writeText("A from activity");
        contentsList.find(text("A from activity"));

        //create mentions
        openMyMentions();
        writeText("D from mentios");
        contentsList.find(text("D from mentios"));

        //create mark
        openFollowedtags();
        writeText("T from tags");
        contentsList.find(text("T from tags"));

        //my aspects
        openMyAspects();
        aspectsList.shouldHave(texts("Deselect all", "generic", "+ Add an aspect"));

        // add new aspect
        addNewAspects("B");

        $(byText("Stream")).click();
        openMyAspects();
        aspectsList.shouldHave(texts("Select all", "generic", "B", "+ Add an aspect"));

        //go to prifile
        userPopupMenu("Profile");
        contentsList.shouldHave(texts("T from tags", "D from mentios", "A from activity"));

        //comment posts
        contentsList.find(text("T from tags"));
        commentPublic("M");
        $(".comment.media").shouldHave(text("M"));

        //delete post
        deletePost("T from tags");
        contentsList.shouldHave(texts("D from mentios", "A from activity"));

        //cencel delete post
        cencelDeletePost("D from mentios");
        contentsList.shouldHave(texts("D from mentios", "A from activity"));

        //like post
        likePost("A from activity");

        //log out
        userPopupMenu("Log out");
    }

    @Test
    public void popupChangePasswordCreatMes(){

        // precondition
        enterLogin("bob");
        enterPassword("evankorth");
        $(".user-name").shouldHave(text("Bob Grimm"));

        //go to Contacts
        userPopupMenu("Contacts");
        $(byText("My contacts")).shouldBe(visible).click();

        //open contact page
        openContactPage("Alice Smith");
        $("#name").shouldHave(text("Alice Smith")).click();

        //press message icon in contact page
        pressMessageIconInUsrPage();
        $(byText("New conversation")).shouldBe(visible).click();

        //create message
        creatNewMessage("hi");
        $(".last_message").shouldHave(text("hi"));

        //go to settings
        userPopupMenu("Settings");
        $("#section_header>h2").shouldHave(text("Settings"));

        //change password
        changePassword("evankorth", "qwerty", "qwerty");

        //login with new password
        enterLogin("bob");
        enterPassword("qwerty");
        $(".user-name").shouldHave(text("Bob Grimm"));

        //Change language
        changeLanguage("Русский");
        $("#section_header>h2").shouldHave(text("Настройки"));

        //press button close account
        $("#close_account").click();
        $("#inner_account_delete>h1").shouldHave("Пожалуйста, не уходите!");
        $(".close_image").click();

        //go to Help
        userPopupMenu("Помощь");
        $(".help_header").shouldHave(text("Помощь"));

        //go to admin
        userPopupMenu("Администратор");
        $(".user_search.span9>h3").shouldHave(text("Поиск пользователей"));

        //Log out
        userPopupMenu("Выйти");
    }

    @Test
    public void editUserProfile(){

        //precondition
        enterLogin("bob");
        enterPassword("evankorth");
        $(".user-name").shouldHave(text("Bob Grimm"));

        userPopupMenu("Profile");

        //go to edit
        $("#edit_profile").click();

        //edit user name
        editUserName("Boby");

        //add
        addDiscription("yoga");
        addDiscription("theater");

        //edit user bio
        editUserBio("NEW edited");

        //edit user local
        editUserLocal("ZP edited");

        //edit user gander
        editUserGender("O edited");

        //edit user birthday
        editUserBerthday("1987", "June", "22");

        // update edited
        $("#update_profile").click();

        $(".user-name").shouldHave(text("Boby Grimm"));
        $$(".as-selection-item.blur").shouldHave(texts("yoga", "theater"));
        $("#profile_bio").shouldHave(text("NEW edited"));
        userPopupMenu("Profile");
        userPopupMenu("Log out");
        }


        @After
        public void tearDown ()throws IOException {
            screenshot();
        }

        @Attachment(type = "image/png")
        public byte[] screenshot ()throws IOException {
            File screenshot = Screenshots.getScreenShotAsFile();
            return Files.toByteArray(screenshot);
        }
}
