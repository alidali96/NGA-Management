package Database;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    Optional<? extends T> get(int id);
    Optional<? extends T> get(String name);

    List<? extends T> getAll();

    void create(T t);

    void update(T t);

    void delete(T t);

    void updateList();

}
