package lordgarrish.dao;

import lordgarrish.business.*;
import lordgarrish.data.DBUtil;

import javax.persistence.*;
import java.util.*;
import java.util.logging.*;

public class MusicAlbumDao implements AbstractDao<MusicAlbum, String> {
    private static Logger logger = Logger.getLogger("lordgarrish.dao");

    @Override
    public boolean save(MusicAlbum entity) throws Exception {
        return false;
    }

    @Override
    public MusicAlbum update(MusicAlbum entity) throws Exception {
        return null;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<MusicAlbum> getAll() throws Exception {
        EntityManager manager = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        List<MusicAlbum> albums;

        transaction.begin();
        try {
            albums = manager.createQuery("select i from MusicAlbum i").getResultList();
            transaction.commit();
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Error while reading from the database:", ex);
            transaction.rollback();
            throw new Exception(ex);
        } finally {
            manager.close();
        }

        return albums;
    }

    @Override
    public MusicAlbum getById(String code) throws Exception {
        EntityManager manager = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        MusicAlbum album;

        transaction.begin();
        try {
            album = (MusicAlbum) manager.createQuery("select i from MusicAlbum i where i.code = :code")
                    .setParameter("code", code).getSingleResult();
            transaction.commit();
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Error while reading from the database:", ex);
            transaction.rollback();
            throw new Exception(ex);
        } finally {
            manager.close();
        }

        return album;
    }
}
