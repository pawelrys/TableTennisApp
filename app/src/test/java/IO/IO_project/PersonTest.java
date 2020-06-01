package IO.IO_project;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.*;
@RunWith(AndroidJUnit4.class)
public class PersonTest {

    @Test
    public void check1AdditionPlayerToDatabase_isCorrect() {  //dodawanie zawodnika do bazy danych
        // given
        databaseAdmin db = new databaseAdmin(ApplicationProvider.getApplicationContext());
        Player player = new Player("Bartek", "Kowalski", "M", "01-01-1999", "ULKS Krakow", "Junior", 25);

        // when
        boolean result = db.addPlayer(player);

        // then
        assertTrue(result);    //jezeli zawodnika dodano prawidlowo, to liczba zawodnikow w bazie danych > 0
        db.deletePlayer(player);
        db.close();
    }

    @Test
    public void check2AdditionPlayerToDatabase_isCorrect() {  //dodawanie zawodnika do bazy danych
        // given
        databaseAdmin db = new databaseAdmin(ApplicationProvider.getApplicationContext());
        Player player = new Player("Bartek", "Kowalski", "M", "01-01-2021", "ULKS Krakow", "Junior", 25);

        // when
        boolean result = db.addPlayer(player);

        // then
        assertFalse(result);  //zawodnik ma niepoprawne dane, więc nie może zostać dodany do bazy danych (zła data)
        db.deletePlayer(player);
        db.close();
    }

    @Test
    public void check1AdditionPlayerToTournament_isCorrect() {  //dodawanie zawodnika do turnieju
        // given
        databaseAdmin db = new databaseAdmin(ApplicationProvider.getApplicationContext());
        Player player = new Player("Bartek", "Kowalski", "M", "01-01-1999", "ULKS Krakow", "Junior", 25);

        // when
        boolean result = db.addPlayerToTournament(player);

        // then
        assertTrue(result);
        db.deletePlayer(player);
        db.close();
    }


    @Test
    public void check2AdditionPlayerToTournament_isCorrect() {  //dodawanie zawodnika do turnieju
        // given
        databaseAdmin db = new databaseAdmin(ApplicationProvider.getApplicationContext());
        Player player = new Player("Bartek", "Kowalski", "M", "01-01-2999", "ULKS Krakow", "Junior", 25);

        // when
        boolean result = db.addPlayerToTournament(player);

        // then
        assertFalse(result);
        db.deletePlayer(player);
        db.close();
    }

    @Test
    public void check1IfPlayerExists_isCorrect() {   //Sprawdzanie czy zawodnik istnieje
        // given
        databaseAdmin db = new databaseAdmin(ApplicationProvider.getApplicationContext());
        Player player = new Player("Bartek", "Kowalski", "M", "01-01-1999", "ULKS Krakow", "Junior", 25);
        db.addPlayer(player);

        // when
        boolean result = db.searchPlayer(player);

        // then
        assertTrue(result);  //sprawdzanie czy zawodnik o podanym ID istnieje
        db.deletePlayer(player);
        db.close();
    }

    @Test
    public void check2IfPlayerExists_isCorrect() {   //Sprawdzanie czy zawodnik istnieje
        // given
        databaseAdmin db = new databaseAdmin(ApplicationProvider.getApplicationContext());
        Player player = new Player("Bartek", "Kowalski", "M", "01-01-1999", "ULKS Krakow", "Junior", 25);

        // when
        boolean result = db.searchPlayer(player);

        // then
        assertFalse(result);  //sprawdzanie czy zawodnik o podanym ID istnieje
        db.deletePlayer(player);
        db.close();
    }
}
