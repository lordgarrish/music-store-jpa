package lordgarrish.dao;

import lordgarrish.business.*;
import lordgarrish.data.DBUtil;

import javax.persistence.*;
import java.util.*;
import java.util.logging.*;

public class OrderDao implements AbstractDao<Order, String> {
    private static Logger logger = Logger.getLogger("lordgarrish.dao");

    @Override
    public boolean save(Order order) throws Exception {
        EntityManager manager = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        transaction.begin();
        try {
            manager.persist(order);
            transaction.commit();
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Error while saving order to the database:", ex);
            transaction.rollback();
            throw new Exception(ex);
        } finally {
            manager.close();
        }
        return true;
    }

    @Override
    public Order update(Order entity) throws Exception {
        return null;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public List<Order> getAll() throws Exception {
        return null;
    }

    @Override
    public Order getById(String id) throws Exception {
        return null;
    }
}
