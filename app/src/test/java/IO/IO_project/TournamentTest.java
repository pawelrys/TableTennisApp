package IO.IO_project;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertTrue;
@RunWith(AndroidJUnit4.class)

public class TournamentTest {

//    @Test
//    public void checkSetWinnerMatch_isCorrect(){ //wpisywanie wyniku i ustawianie wygranego meczu
//        // given
//        databaseAdmin db = new databaseAdmin(ApplicationProvider.getApplicationContext());
//        Player player1 = new Player("Bartek", "Kowalski", "M", "01-01-1999", "ULKS Krakow", "Junior", 25);
//        Player player2 = new Player("Maciej", "Boryna", "M", "01-01-1999", "ULKS Krakow", "Junior", 25);
//        db.addPlayer(player1);
//        db.addPlayer(player2);
//        db.addPlayerToTournament(player1);
//        db.addPlayerToTournament(player2);
//        Match match = new Match(1, player1.getID() + 1, player2.getID() + 1);
//        db.addMatch(match);
//
//
//        // when
//        boolean result = setMatchPage.setMatch("Bartek", "Kowalski" ,"Maciej", "Boryna","11-8", "11-5", "15-13", "", "", db); //funkcja wpisuje wynik meczu i zwraca true jezeli wyloniono zwyciezce, zawodnik wygral 3 sety, wiec wygral mecz, funkcja powinna zwrocic true
//
//        // then
//        assertTrue(result);
//    }

    @Test
    public void checkTableSort_isCorrect() {  //Sprawdzanie czy tabela sortuje sie prawidlowo
        // given
        databaseAdmin db = new databaseAdmin(ApplicationProvider.getApplicationContext());
        Player player1 = new Player("Bartek5", "Kowalski1", "M", "01-01-1999", "ULKS Krakow", "Junior", 125);
        Player player2 = new Player("Mikołaj6", "Kowalski2", "M", "01-01-1999", "ULKS Krakow", "Junior", 225);
        Player player3 = new Player("Tadeusz7", "Kowalski3", "M", "01-01-1999", "ULKS Krakow", "Junior", 325);
        Player player4 = new Player("Maciej8", "Kowalski4", "M", "01-01-1999", "ULKS Krakow", "Junior", 425);
        db.addPlayer(player1);
        db.addPlayer(player2);
        db.addPlayer(player3);
        db.addPlayer(player4);
        db.addPlayerToTournament(player1);
        db.addPlayerToTournament(player2);
        db.addPlayerToTournament(player3);
        db.addPlayerToTournament(player4);

        // when
        boolean result = db.booleanPersonPlayerTournamentByParameter("Imię");

        // then
        assertTrue(result);    //funkcja sortuje tabele po imieniu
    }
}