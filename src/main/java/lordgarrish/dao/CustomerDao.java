package lordgarrish.dao;

import lordgarrish.business.*;
import lordgarrish.data.DBUtil;

import javax.persistence.*;
import java.util.*;
import java.util.logging.*;

public class CustomerDao implements AbstractDao<Customer, String> {
    private static Logger logger = Logger.getLogger("lordgarrish.dao");

    @Override
    public boolean save(Customer customer) throws Exception {
        EntityManager manager = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        transaction.begin();
        try {
            manager.persist(customer);
            transaction.commit();
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Error while saving customer to the database:", ex);
            transaction.rollback();
            throw new Exception(ex);
        } finally {
            manager.close();
        }
        return true;
    }

    @Override
    public Customer update(Customer entity) throws Exception {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public boolean delete(String id) throws Exception {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public List<Customer> getAll() throws Exception {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Customer getById(String id) throws Exception {
        throw new UnsupportedOperationException("Not implemented");
    }
}
