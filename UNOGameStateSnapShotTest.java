import org.junit.*;

import java.io.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * This test class tests the different methods in the Card Class.
 *
 * @Author Anvita Ala 101301514
 * @Author Charis Nobossi 101297742
 * @Author Pulcherie Mbaye 101302394
 * @Author Pardis Ehsani 101300400
 */


public class UNOGameStateSnapShotTest {

    @Test
    public void testSnapshotSerializationDeserialization() throws Exception {
        // Build a simple snapshot manually
        Player p1 = new Player("A");
        Player p2 = new Player("B");

        GameLogicModel dummyModel = new GameLogicModel(); // if constructor needs args you adjust
        dummyModel.playerOrder.addPlayer(p1);
        dummyModel.playerOrder.addPlayer(p2);

        dummyModel.drawPile.add(new Card());
        dummyModel.discardPile.add(new Card());

        dummyModel.scores.put(p1, 10);
        dummyModel.scores.put(p2, 20);

        UNOGameStateSnapShot snap1 = new UNOGameStateSnapShot(dummyModel);

        // Serialize
        File file = new File("testsave.bin");
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(snap1);
        out.close();

        // Deserialize
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        UNOGameStateSnapShot snap2 = (UNOGameStateSnapShot) in.readObject();
        in.close();

        // Validate
        assertEquals(2, snap2.playersInOrder.size());
        //assertEquals(snap1.lightMode, snap2.lightMode);
    }
}
