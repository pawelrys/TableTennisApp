//Paweł Ryś - Espresso - automatyczne testowanie

package IO.IO_project;

import android.database.Cursor;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.action.ViewActions;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.uiautomator.UiDevice;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainTest {

    @Rule
    public ActivityTestRule mActivityTestRule = new ActivityTestRule<Main>(Main.class);

    private String mName = "admin";


    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void atestAddPlayerToTournament1(){
        onView(withId(R.id.start)).perform(click());
        onView(withId(R.id.refereeButton1)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.addPlayerButton)).perform(click());

        onView(withId(R.id.addNameText)).perform(typeText("Bartek"));
        onView(withId(R.id.addSurnameText)).perform(typeText("Kruk23"));
        onView(withId(R.id.addSex)).perform(typeText("M"));
        onView(withId(R.id.addDate)).perform(typeText("01-01-1999"));
        onView(withId(R.id.addClub)).perform(typeText("ULKS Moszczenica"));
        onView(withId(R.id.addCategory)).perform(typeText("Senior"));
        onView(withId(R.id.addPoints)).perform(typeText("16"));
        closeSoftKeyboard();
        onView(withId(R.id.addPlayerButton1)).perform(click());

        UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        mDevice.pressBack();   //cofajnie layoutu

        onView(withId(R.id.addPlayerToTournamentButton)).perform(scrollTo()).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.addPlayerToTournamentNameText)).perform(ViewActions.replaceText("Bartek"));
        closeSoftKeyboard();
        onView(withId(R.id.addPlayerToTournamentSurnameText)).perform(typeText("Kruk23"));
        closeSoftKeyboard();
        onView(withId(R.id.addPlayerToTournamentPlayerButton)).perform(click());
        onView(withId(R.id.addPlayerToTournamentNameText)).check(matches(hasValueEqualTo("")));
    }


    @Test
    public void atestAddPlayerToSystem1()
    {
        onView(withId(R.id.start)).perform(click());
        onView(withId(R.id.refereeButton1)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.addPlayerButton)).perform(click());
        onView(withId(R.id.addNameText)).perform(typeText("Konrad1"));
        onView(withId(R.id.addSurnameText)).perform(typeText("Dzik"));
        onView(withId(R.id.addSex)).perform(typeText("M"));
        onView(withId(R.id.addDate)).perform(typeText("01-01-1999"));
        onView(withId(R.id.addClub)).perform(typeText("ULKS Krakow"));
        onView(withId(R.id.addCategory)).perform(typeText("Senior"));
        onView(withId(R.id.addPoints)).perform(typeText("100"));
        closeSoftKeyboard();
        onView(withId(R.id.addPlayerButton1)).perform(click());
        onView(withId(R.id.addNameText)).check(matches(hasValueEqualTo("")));
    }

    @Test
    public void btestCreateLadder1()
    {
        onView(withId(R.id.start)).perform(click());
        onView(withId(R.id.refereeButton1)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.loginButton)).perform(click());


        onView(withId(R.id.spacePlayersButton)).perform(scrollTo()).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.spacingText)).perform(ViewActions.replaceText("32"));
        closeSoftKeyboard();
        onView(withId(R.id.spacingButton)).perform(click());

        UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        mDevice.pressBack();

        onView(withId(R.id.createLadderButton)).perform(scrollTo()).perform(click());
        assertTrue(Main.isLadder_tournament());
    }

    @Test
    public void ctestSetMatch1()
    {
        onView(withId(R.id.start)).perform(click());
        onView(withId(R.id.refereeButton1)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.loginButton)).perform(click());

        onView(withId(R.id.setMatchButton)).perform(scrollTo()).perform(click());

        onView(withId(R.id.matchFirstNameText)).perform(ViewActions.replaceText("Aleksander"));
        onView(withId(R.id.matchFirstSurnameText)).perform(ViewActions.replaceText("Kruk11"));
        onView(withId(R.id.matchSecondNameText)).perform(ViewActions.replaceText("Jacek"));
        onView(withId(R.id.matchSecondSurnameText)).perform(ViewActions.replaceText("Kruk1"));
        onView(withId(R.id.matchSetFirstText)).perform(ViewActions.replaceText("11-6"));
        onView(withId(R.id.matchSetSecondText)).perform(ViewActions.replaceText("11-7"));
        onView(withId(R.id.matchSetThirdText)).perform(ViewActions.replaceText("11-8"));
        closeSoftKeyboard();

        onView(withId(R.id.enterMatchButton1)).perform(click());

        databaseAdmin db = new databaseAdmin(ApplicationProvider.getApplicationContext());


        Cursor k = db.showMatch();
        k.moveToNext();
        assertNotNull(k.getString(4));
    }


    @Test
    public void dtestSetMatch2()
    {
        onView(withId(R.id.start)).perform(click());
        onView(withId(R.id.refereeButton1)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.loginButton)).perform(click());

        onView(withId(R.id.setMatchButton)).perform(scrollTo()).perform(click());

        onView(withId(R.id.matchFirstNameText)).perform(ViewActions.replaceText("Aleksander23123"));
        onView(withId(R.id.matchFirstSurnameText)).perform(ViewActions.replaceText("Kruk113123123"));
        onView(withId(R.id.matchSecondNameText)).perform(ViewActions.replaceText("Jacek4234234"));
        onView(withId(R.id.matchSecondSurnameText)).perform(ViewActions.replaceText("Kruk14234324"));
        onView(withId(R.id.matchSetFirstText)).perform(ViewActions.replaceText("11-6"));
        onView(withId(R.id.matchSetSecondText)).perform(ViewActions.replaceText("11-7"));
        onView(withId(R.id.matchSetThirdText)).perform(ViewActions.replaceText("11-8"));
        closeSoftKeyboard();

        onView(withId(R.id.enterMatchButton1)).perform(click());


        onView(withId(R.id.matchFirstNameText)).check(matches(hasValueEqualTo("Aleksander23123")));
    }


    @Test
    public void etestSetMatch3()
    {
        onView(withId(R.id.start)).perform(click());
        onView(withId(R.id.refereeButton1)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.loginButton)).perform(click());

        onView(withId(R.id.setMatchButton)).perform(scrollTo()).perform(click());

        onView(withId(R.id.matchFirstNameText)).perform(ViewActions.replaceText("Aleksander"));
        onView(withId(R.id.matchFirstSurnameText)).perform(ViewActions.replaceText("Kruk11"));
        onView(withId(R.id.matchSecondNameText)).perform(ViewActions.replaceText("Jacek"));
        onView(withId(R.id.matchSecondSurnameText)).perform(ViewActions.replaceText("Kruk1"));
        onView(withId(R.id.matchSetFirstText)).perform(ViewActions.replaceText("11-6"));
        onView(withId(R.id.matchSetSecondText)).perform(ViewActions.replaceText("11-7"));
        onView(withId(R.id.matchSetThirdText)).perform(ViewActions.replaceText(" 11-8"));
        closeSoftKeyboard();

        onView(withId(R.id.enterMatchButton1)).perform(click());

        onView(withId(R.id.matchFirstNameText)).check(matches(hasValueEqualTo("Aleksander")));
    }



    @Test
    public void ftestGuestLadderClassification1()
    {
        onView(withId(R.id.start)).perform(click());
        onView(withId(R.id.guestButton)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.loginButton)).perform(click());
        assertTrue(Main.isLadder_tournament());
    }


    @Test
    public void gtestGuestLadderClassification3()
    {
        onView(withId(R.id.start)).perform(click());
        onView(withId(R.id.guestButton)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.loginButton)).perform(click());
        assertFalse(Main.getAll_match() == Main.getEnd_match());
    }


    @Test
    public void htestAddPlayerToSystem2()
    {
        onView(withId(R.id.start)).perform(click());
        onView(withId(R.id.refereeButton1)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.addPlayerButton)).perform(click());

        onView(withId(R.id.addNameText)).perform(typeText("Konrad2"));
        onView(withId(R.id.addSurnameText)).perform(typeText("Dzik"));
        onView(withId(R.id.addSex)).perform(typeText("M"));
        onView(withId(R.id.addDate)).perform(typeText("01-01-3000"));
        onView(withId(R.id.addClub)).perform(typeText("ULKS Krakow"));
        onView(withId(R.id.addCategory)).perform(typeText("Senior"));
        onView(withId(R.id.addPoints)).perform(typeText("100"));
        closeSoftKeyboard();
        onView(withId(R.id.addPlayerButton1)).perform(click());

        onView(withId(R.id.addNameText)).check(matches(hasValueEqualTo("Konrad2")));
    }


    @Test
    public void itestAddPlayerToSystem3()
    {
        onView(withId(R.id.start)).perform(click());
        onView(withId(R.id.refereeButton1)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.addPlayerButton)).perform(click());

        onView(withId(R.id.addNameText)).perform(typeText("Konrad3"));
        onView(withId(R.id.addSurnameText)).perform(typeText(""));
        onView(withId(R.id.addSex)).perform(typeText("M"));
        onView(withId(R.id.addDate)).perform(typeText("01-01-1999"));
        onView(withId(R.id.addClub)).perform(typeText("ULKS Krakow"));
        onView(withId(R.id.addCategory)).perform(typeText("Senior"));
        onView(withId(R.id.addPoints)).perform(typeText("100"));
        closeSoftKeyboard();
        onView(withId(R.id.addPlayerButton1)).perform(click());

        onView(withId(R.id.addNameText)).check(matches(hasValueEqualTo("Konrad3")));
    }


    @Test
    public void ktestAddPlayerToTournament2()
    {
        onView(withId(R.id.start)).perform(click());
        onView(withId(R.id.refereeButton1)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.loginButton)).perform(click());

        onView(withId(R.id.addPlayerToTournamentButton)).perform(scrollTo()).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.addPlayerToTournamentNameText)).perform(ViewActions.replaceText("Konrad15"));
        closeSoftKeyboard();
        onView(withId(R.id.addPlayerToTournamentSurnameText)).perform(typeText("Fajny"));
        closeSoftKeyboard();
        onView(withId(R.id.addPlayerToTournamentPlayerButton)).perform(click());
        onView(withId(R.id.addPlayerToTournamentNameText)).check(matches(hasValueEqualTo("Konrad15")));
    }


    @Test
    public void ltestAddPlayerToTournament3()
    {
        onView(withId(R.id.start)).perform(click());
        onView(withId(R.id.refereeButton1)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.loginButton)).perform(click());

        onView(withId(R.id.addPlayerToTournamentButton)).perform(scrollTo()).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.addPlayerToTournamentNameText)).perform(ViewActions.replaceText("Konrad11"));
        closeSoftKeyboard();
        onView(withId(R.id.addPlayerToTournamentSurnameText)).perform(typeText("Fajny"));
        closeSoftKeyboard();
        onView(withId(R.id.addPlayerToTournamentPlayerButton)).perform(click());
        onView(withId(R.id.addPlayerToTournamentNameText)).check(matches(hasValueEqualTo("Konrad11")));
    }


    @Test
    public void mtestSortTable1()
    {
        onView(withId(R.id.start)).perform(click());
        onView(withId(R.id.refereeButton1)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.loginButton)).perform(click());

        onView(withId(R.id.sortTableButton)).perform(scrollTo()).perform(click());
        closeSoftKeyboard();
        databaseAdmin db = new databaseAdmin(ApplicationProvider.getApplicationContext());
        Cursor k = db.showPlayerByParameter("Imię");
        k.moveToNext();
        assertEquals("Adam", k.getString(1));
        assertEquals("Kruk", k.getString(2));
    }


    @Test
    public void ntestSortTable2()
    {
        onView(withId(R.id.start)).perform(click());
        onView(withId(R.id.refereeButton1)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.loginButton)).perform(click());

        onView(withId(R.id.sortTableButton)).perform(scrollTo()).perform(click());
        closeSoftKeyboard();
        databaseAdmin db = new databaseAdmin(ApplicationProvider.getApplicationContext());
        Cursor k = db.showPlayerByParameter("Data_Urodzenia");
        k.moveToNext();
        assertEquals("Tomasz", k.getString(1));
        assertEquals("Pająk", k.getString(2));
    }


    @Test
    public void otestSortTable3()
    {
        onView(withId(R.id.start)).perform(click());
        onView(withId(R.id.refereeButton1)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.sortTableButton)).perform(scrollTo()).perform(click());
        closeSoftKeyboard();
        databaseAdmin db = new databaseAdmin(ApplicationProvider.getApplicationContext());
        Cursor k = db.showPlayerByParameter("Klub");
        k.moveToNext();
        assertEquals("Maciej", k.getString(1));
        assertEquals("Lew", k.getString(2));

    }

    @Test
    public void ptestCreateLadder2()
    {
        onView(withId(R.id.start)).perform(click());
        onView(withId(R.id.refereeButton1)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.restartTournamentButton)).perform(scrollTo()).perform(click());
        onView(withId(R.id.restartTournamentButton)).perform(scrollTo()).perform(click());
        assertFalse(Main.isLadder_tournament());
        addPlayerToTournament();
    }

    @Test
    public void stestRandomPlayer1()
    {
        onView(withId(R.id.start)).perform(click());
        onView(withId(R.id.refereeButton1)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.loginButton)).perform(click());

        onView(withId(R.id.spacePlayersButton)).perform(scrollTo()).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.spacingText)).perform(ViewActions.replaceText("5"));
        closeSoftKeyboard();
        onView(withId(R.id.spacingButton)).perform(click());
        onView(withId(R.id.spacingText)).check(matches(hasValueEqualTo("")));
    }


    @Test
    public void stestRandomPlayer2()
    {
        onView(withId(R.id.start)).perform(click());
        onView(withId(R.id.refereeButton1)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.loginButton)).perform(click());

        onView(withId(R.id.spacePlayersButton)).perform(scrollTo()).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.spacingText)).perform(ViewActions.replaceText("50"));
        closeSoftKeyboard();
        onView(withId(R.id.spacingButton)).perform(click());
        onView(withId(R.id.spacingText)).check(matches(hasValueEqualTo("50")));
    }


    @Test
    public void rtestRandomPlayer3()
    {
        onView(withId(R.id.start)).perform(click());
        onView(withId(R.id.refereeButton1)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.loginButton)).perform(click());

        onView(withId(R.id.randomPlayersButton)).perform(scrollTo()).perform(click());

        onView(withId(R.id.randomText)).perform(ViewActions.replaceText("5"));
//        closeSoftKeyboard();
//        onView(withId(R.id.randomButton)).perform(click());
        onView(withId(R.id.randomText)).check(matches(hasValueEqualTo("5")));
    }


    @Test
    public void otestCreateLadder3()
    {
        onView(withId(R.id.start)).perform(click());
        onView(withId(R.id.refereeButton1)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.restartTournamentButton)).perform(scrollTo()).perform(click());
        onView(withId(R.id.restartTournamentButton)).perform(scrollTo()).perform(click());

        addPlayerToTournament();

        assertFalse(Main.isLadder_tournament());

    }

    @Test
    public void utestGuestLadderClassification2()
    {
        onView(withId(R.id.start)).perform(click());
        onView(withId(R.id.guestButton)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.loginButton)).perform(click());
        assertFalse(Main.isLadder_tournament());
    }

    @After
    public void tearDown() throws Exception {
    }


    Matcher<View> hasValueEqualTo(final String content) {

        return new TypeSafeMatcher<View>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("Has EditText/TextView the value:  " + content);
            }

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof TextView) && !(view instanceof EditText)) {
                    return false;
                }
                if (view != null) {
                    String text;
                    if (view instanceof TextView) {
                        text = ((TextView) view).getText().toString();
                    } else {
                        text = ((EditText) view).getText().toString();
                    }

                    return (text.equalsIgnoreCase(content));
                }
                return false;
            }
        };
    }

    public void addPlayerToTournament(){
        databaseAdmin db = new databaseAdmin(ApplicationProvider.getApplicationContext());



        Player p1 = new Player("Jacek", "Kruk1", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 1);
        Player p2 = new Player("Tomasz", "Kruk2", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 2);
        Player p3 = new Player("Marcin", "Kruk3", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 3);
        Player p4 = new Player("Dawid", "Kruk4", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 4);
        Player p5 = new Player("Paweł", "Kruk5", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 5);
        Player p6 = new Player("Adam", "Kruk", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 6);
        Player p7 = new Player("Eryk", "Kruk7", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 7);
        Player p8 = new Player("Jakub", "Kruk8", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 8);
        Player p9 = new Player("Jarosław", "Kruk9", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 9);
        Player p10 = new Player("Patryk", "Kruk10", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 10);
        Player p11 = new Player("Aleksander", "Kruk11", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 32);
        Player p12 = new Player("Igor", "Kruk12", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 31);
        Player p13 = new Player("Hubert", "Kruk13", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 30);
        Player p14 = new Player("Arkadiusz", "Kruk14", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 29);
        Player p15 = new Player("Maciej", "Lew", "M", "01-01-1999", "ALKS Moszczenica", "Senior", 28);
        Player p16 = new Player("Antoni", "Kruk16", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 27);
        Player p17 = new Player("Jan", "Kruk17", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 26);
        Player p18 = new Player("Szymon", "Kruk18", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 11);
        Player p19 = new Player("Franciszek", "Kruk19", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 12);
        Player p20 = new Player("Filip", "Kruk20", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 13);
        Player p21 = new Player("Mikołaj", "Kruk21", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 14);
        Player p22 = new Player("Mateusz", "Kruk22", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 15);
        Player p23 = new Player("Bartek", "Kruk23", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 16);
        Player p24 = new Player("Kacper", "Kruk24", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 17);
        Player p25 = new Player("Stanisław", "Kruk25", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 25);
        Player p26 = new Player("Piotrek", "Kruk26", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 24);
        Player p27 = new Player("Leon", "Kruk27", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 23);
        Player p28 = new Player("Marcel", "Kruk28", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 22);
        Player p29 = new Player("Michał", "Kruk29", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 18);
        Player p30 = new Player("Nikodem", "Kruk30", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 19);
        Player p31 = new Player("Tomasz", "Pająk", "M", "01-01-1998", "ULKS Moszczenica", "Senior", 20);
        Player p32 = new Player("Ignacy", "Kruk32", "M", "01-01-1999", "ULKS Moszczenica", "Senior", 21);


        db.addPlayerToTournament(p1);
        db.addPlayerToTournament(p2);
        db.addPlayerToTournament(p3);
        db.addPlayerToTournament(p4);
        db.addPlayerToTournament(p5);
        db.addPlayerToTournament(p6);
        db.addPlayerToTournament(p7);
        db.addPlayerToTournament(p8);
        db.addPlayerToTournament(p9);
        db.addPlayerToTournament(p10);
        db.addPlayerToTournament(p11);
        db.addPlayerToTournament(p12);
        db.addPlayerToTournament(p13);
        db.addPlayerToTournament(p14);
        db.addPlayerToTournament(p15);
        db.addPlayerToTournament(p16);
        db.addPlayerToTournament(p17);
        db.addPlayerToTournament(p18);
        db.addPlayerToTournament(p19);
        db.addPlayerToTournament(p20);
        db.addPlayerToTournament(p21);
        db.addPlayerToTournament(p22);
        db.addPlayerToTournament(p23);
        db.addPlayerToTournament(p24);
        db.addPlayerToTournament(p25);
        db.addPlayerToTournament(p26);
        db.addPlayerToTournament(p27);
        db.addPlayerToTournament(p28);
        db.addPlayerToTournament(p29);
        db.addPlayerToTournament(p30);
        db.addPlayerToTournament(p31);
        db.addPlayerToTournament(p32);

        Main.setRandom_player(0);
        Main.setSpace_player(0);
        Main.setPlayer_in_tournament(32);
    }
}