package farrakhov.aydar.spendings.content;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by aydar on 09.10.16.
 */

public class Category extends RealmObject {

    @PrimaryKey
    private Long id;

    private String name;

    private Budget mBudget;

    public Category() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Budget getBudget() {
        return mBudget;
    }

    public void setBudget(Budget budget) {
        mBudget = budget;
    }
}
