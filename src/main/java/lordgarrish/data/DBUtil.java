package lordgarrish.data;

import lordgarrish.business.MusicAlbum;

import javax.persistence.*;
import java.util.logging.*;

public class DBUtil {
    private static final EntityManagerFactory entityManagerFactory
            = Persistence.createEntityManagerFactory("music-store");

    public static EntityManagerFactory getEmFactory() {
        return entityManagerFactory;
    }

    public static void createTestCatalog() throws Exception {
        MusicAlbum album1 = new MusicAlbum("mah001", "F♯ A♯ ∞", "Godspeed You! Black Emperor", "Post-Rock", 1998, "TEST_GYBE", 29.9);
        MusicAlbum album2 = new MusicAlbum("mah002", "Soundtracks for the Blind", "Swans", "Experimental", 1996, "TEST_SWANS", 39.9);
        MusicAlbum album3 = new MusicAlbum("mah003", "In the Court of the Crimson King", "King Crimson", "Prog-Rock", 1969, "TEST_KINGCRMSN", 60.0);
        MusicAlbum album4 = new MusicAlbum("mah004", "Pulse Demon", "Merzbow", "Noise", 1996, "TEST_MERZBW", 66.6);

        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        transaction.begin();
        try {
            manager.persist(album1);
            manager.persist(album2);
            manager.persist(album3);
            manager.persist(album4);
            transaction.commit();
        } catch (Exception e) {
            Logger.getLogger("lordgarrish.data").log(Level.WARNING, "Error while adding test catalog to the database:", e);
            transaction.rollback();
            throw new Exception(e);
        } finally {
            manager.close();
        }
    }
}
