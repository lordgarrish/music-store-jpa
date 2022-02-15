package lordgarrish.dao;

import java.util.List;


public interface AbstractDao<E, K> {

    boolean save(E entity) throws Exception;
    E update(E entity) throws Exception;
    boolean delete(K id) throws Exception;
    List<E> getAll() throws Exception;
    E getById(K id) throws Exception;
}
