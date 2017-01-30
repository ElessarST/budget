package farrakhov.aydar.spendings.repository;

import farrakhov.aydar.spendings.content.Category;
import rx.Observable;

/**
 * Created by aydar farrakhov on 30.01.17.
 */

public interface ICategoryProvider {

    Observable<Category> getAll();

}
