//import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
//import org.junit.jupiter.api.*;

/**
 * This class is testing the methods of the AIPlayer test.

 */
public class AIPlayerTest {

    private AIPlayer aiPlayer;

    @Before
    public void setUp() {
        aiPlayer = new AIPlayer("AI");

    }

    private Card makeLight(Card.LightColour colour, Card.LightType type) {
        Card c = new Card();
        c.lightMode = true;
        c.setCardLightColour(colour.name());
        c.setCardLightType(type);
        return c;
    }


    private Card makeDark(Card.DarkColour colour, Card.DarkType type) {
        Card c = new Card();
        c.lightMode = false;
        c.setCardDarkColour(colour.name());
        c.setCardDarkType(type);
        return c;
    }


    @Test
    public void testChooseCardToPlay_NoValidCard() {
        Card top = makeLight(Card.LightColour.RED, Card.LightType.FIVE);

        // Add cards that CANNOT be played on RED FIVE
        aiPlayer.getHand().add(makeLight(Card.LightColour.BLUE, Card.LightType.ONE));
        aiPlayer.getHand().add(makeLight(Card.LightColour.GREEN, Card.LightType.TWO));

        assertNull(aiPlayer.chooseCardToPlay(top));
    }

    @Test
    public void testChooseCardToPlay_MatchingColour() {
        Card top = makeLight(Card.LightColour.BLUE, Card.LightType.FIVE);

        Card playable = makeLight(Card.LightColour.BLUE, Card.LightType.ONE);
        aiPlayer.getHand().add(playable);

        assertEquals(playable, aiPlayer.chooseCardToPlay(top));
    }

    @Test
    public void testChooseCardToPlay_MatchingNumber() {
        Card top = makeLight(Card.LightColour.RED, Card.LightType.THREE);

        Card playable = makeLight(Card.LightColour.GREEN, Card.LightType.THREE);
        aiPlayer.getHand().add(playable);

        assertEquals(playable, aiPlayer.chooseCardToPlay(top));
    }

    @Test
    public void testChooseBestDarkColour_PicksMostFrequent() {
        aiPlayer.getHand().add(makeDark(Card.DarkColour.TEAL, Card.DarkType.ONE));
        aiPlayer.getHand().add(makeDark(Card.DarkColour.TEAL, Card.DarkType.TWO));
        aiPlayer.getHand().add(makeDark(Card.DarkColour.PINK, Card.DarkType.THREE));

        assertEquals(Card.DarkColour.TEAL, aiPlayer.chooseBestDarkColour());
    }

    @Test
    public void testChooseBestDarkColour_DefaultOrangeOnTie() {
        aiPlayer.getHand().add(makeDark(Card.DarkColour.PURPLE, Card.DarkType.ONE));
        aiPlayer.getHand().add(makeDark(Card.DarkColour.PINK, Card.DarkType.TWO));

        assertEquals(Card.DarkColour.PINK, aiPlayer.chooseBestDarkColour());
    }


}