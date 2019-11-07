package Database;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    Optional<T> get(int id);
    Optional<T> get(String name);

    List<T> getAll();

    void add(T t);

    void update(T t);

    void delete(T t);

}
