package diasporaTests;

import com.codeborne.selenide.Screenshots;
import com.google.common.io.Files;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.confirm;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static helpers.Helpers.dropCreateSeedDiasporaDB;
import static pages.diaspora.*;
/**
 * Created by yana on 30.05.15.
 */
public class AllDiasporaTests {

   /* @BeforeClass
    public static void openDiaspora() {
        open("http://localhost:3000/stream");
        Configuration.timeout = 20000;
    }*/

    /*@Before
    public void clearData() {
        dropCreateSeedDiasporaDB();
    }*/

    @Test
    public void diasporaTests() {

        open("http://localhost:3000/stream");

        // log in
        enterLogin("bob");
        enterPassword("evankorth");
        $(".user-name").shouldHave(text("Bob Grimm"));

        //create activity
        openMyActivity();
        writeText("A from activity");
        shareNews();
        contentsList.find(text("A from activity"));

        //create mentions
        openMyMentions();
        writeText("D from mentios");
        shareNews();
        contentsList.find(text("D from mentios"));

        //create mark
        openFollowedtags();
        writeText("T from tags");
        shareNews();
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

        //delete post
        deletePost("T from tags");

        //cencel delete post

    }


        @After
        public void tearDown ()throws IOException {
            screenshot();
        }

        @Attachment(type = "image/png")
        public byte[] screenshot() throws IOException {
            File screenshot = Screenshots.getScreenShotAsFile();
            return Files.toByteArray(screenshot);
        }
}
