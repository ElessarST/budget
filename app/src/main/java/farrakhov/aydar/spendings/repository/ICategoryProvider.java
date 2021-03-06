package farrakhov.aydar.spendings.repository;

import farrakhov.aydar.spendings.content.Category;
import rx.Observable;

/**
 * Created by aydar farrakhov on 30.01.17.
 */

public interface ICategoryProvider {

    Observable<Category> getAll();

    void create(String name);

    Category get(Long id);

    void change(Long id, String name, float cost, boolean monthly);

    void delete(Long id);
}
