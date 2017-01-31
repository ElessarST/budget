package farrakhov.aydar.spendings.screen.category;

import android.support.annotation.NonNull;

import farrakhov.aydar.spendings.content.Category;
import farrakhov.aydar.spendings.repository.RepositoryProvider;

/**
 * Created by aydar farrakhov on 29.01.17.
 */

public class CategoryPresenter {

    private final CategoryView mView;

    public CategoryPresenter(@NonNull CategoryView view) {
        mView = view;
    }

    public void init(Long id) {
        Category category = RepositoryProvider.provideCategoryRepository()
                .get(id);
        mView.show(category);
    }

    public void change(Category category, String name, float cost, boolean monthly) {
        RepositoryProvider.provideCategoryRepository().change(category.getId(),
                name, cost, monthly);
    }
}
