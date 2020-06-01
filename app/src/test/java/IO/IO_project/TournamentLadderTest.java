package IO.IO_project;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
@RunWith(AndroidJUnit4.class)

public class TournamentLadderTest {

//    @Test
//    public void checkPlayersSpacing_isCorrect(){  //sprawdzanie czy rozstawianie dziala prawidlowo
//        // given
//        databaseAdmin db = new databaseAdmin(ApplicationProvider.getApplicationContext());
//        Player player1 = new Player("Bartek1", "Kowalski1", "M", "01-01-1999", "ULKS Krakow", "Junior", 125);
//        Player player2 = new Player("Mikołaj2", "Kowalski2", "M", "01-01-1999", "ULKS Krakow", "Junior", 225);
//        Player player3 = new Player("Tadeusz3", "Kowalski3", "M", "01-01-1999", "ULKS Krakow", "Junior", 325);
//        Player player4 = new Player("Maciej4", "Kowalski4", "M", "01-01-1999", "ULKS Krakow", "Junior", 425);
//        db.addPlayer(player1);
//        db.addPlayer(player2);
//        db.addPlayer(player3);
//        db.addPlayer(player4);
//        db.addPlayerToTournament(player1);
//        db.addPlayerToTournament(player2);
//        db.addPlayerToTournament(player3);
//        db.addPlayerToTournament(player4);
//        Cursor k = db.showPlayersTournamentOrderByPunkty();
//
//        // when
//        boolean result = spacePlayerPage.spacingPlayers(4, 0, k, db); //funkcja rozstawia zawodnikow po punktach, zwraca true jezeli wszystko sie wykonalo prawidlowo
//
//        // then
//        assertTrue(result);
//        db.close();
//    }
//
//    @Test
//    public void checkPlayersRandom_isCorrect(){ //sprawdzenie czy losowanie dziala prawidlowo
//        //given
//        databaseAdmin db = new databaseAdmin(ApplicationProvider.getApplicationContext());
//        Player player1 = new Player("Bartek5", "Kowalski1", "M", "01-01-1999", "ULKS Krakow", "Junior", 125);
//        Player player2 = new Player("Mikołaj6", "Kowalski2", "M", "01-01-1999", "ULKS Krakow", "Junior", 225);
//        Player player3 = new Player("Tadeusz7", "Kowalski3", "M", "01-01-1999", "ULKS Krakow", "Junior", 325);
//        Player player4 = new Player("Maciej8", "Kowalski4", "M", "01-01-1999", "ULKS Krakow", "Junior", 425);
//        db.addPlayer(player1);
//        db.addPlayer(player2);
//        db.addPlayer(player3);
//        db.addPlayer(player4);
//        db.addPlayerToTournament(player1);
//        db.addPlayerToTournament(player2);
//        db.addPlayerToTournament(player3);
//        db.addPlayerToTournament(player4);
//        Cursor k = db.showPlayersTournamentOrderByPunkty();
//
//        // when
//        boolean result = randomPlayerPage.randomPlayer(4, 4, k, db); //funkcja rozstawia zawodnikow po punktach, zwraca true jezeli wszystko sie wykonalo prawidlowo
//
//        // then
//        assertTrue(result);
//        db.close();
//    }

    @Test
    public void check1AdditionMatch_isCorrect(){  //Sprawdzanie dodawania meczy
        // given
        databaseAdmin db = new databaseAdmin(ApplicationProvider.getApplicationContext());
        Player player1 = new Player("Bartek76", "Kowalski", "M", "01-01-1999", "ULKS Krakow", "Junior", 25);
        Player player2 = new Player("Maciej43", "Boryna", "M", "01-01-1999", "ULKS Krakow", "Junior", 25);
        Match match = new Match(1, player1.getID(), player2.getID());

        // when
        boolean result = db.addMatch(match);; //jesli mecz dodano prawidlowo, to liczba meczy++

        // then
        assertTrue(result);
        db.close();
    }

    @Test
    public void check2AdditionMatch_isCorrect(){  //Sprawdzanie dodawania meczy
        // given
        databaseAdmin db = new databaseAdmin(ApplicationProvider.getApplicationContext());
        Player player1 = new Player("Bartek42", "Kowalski", "M", "01-01-1999", "ULKS Krakow", "Junior", 25);
        Match match = new Match(1, player1.getID(), player1.getID());

        // when
        boolean result = db.addMatch(match);  //jesli mecz dodano prawidlowo, to liczba meczy++

        // then
        assertFalse(result);
        db.close();
    }
}
