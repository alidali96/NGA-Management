package Database;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    Optional<? extends T> get(int id);
    Optional<? extends T> get(String name);

    List<? extends T> getAll();

    boolean create(T t);

    boolean update(T t);

    boolean delete(T t);

    void updateList();

}
