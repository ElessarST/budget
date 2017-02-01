package farrakhov.aydar.spendings.content.helper;

import org.joda.time.DateTime;

import farrakhov.aydar.spendings.content.Category;

/**
 * Created by aydar farrakhov on 01.02.17.
 */

public class CategoryWithDetails {

    private float total;

    private Long id;

    private String name;

    private boolean monthly;

    private Float sum;

    private Period mPeriod;

    public Float getPlanned() {
        int dayOfMonth = DateTime.now().dayOfMonth().getMaximumValue();
        float daily = !this.isMonthly() ? this.getSum() :
                Math.round(this.getSum() / dayOfMonth);
        switch (mPeriod) {
            case MONTH:
                return this.isMonthly() ? this.getSum() : this.getSum() * dayOfMonth;
            case WEEK:
                return daily * DateTime.now().dayOfWeek().getMaximumValue();
            case DAY:
                return daily;
        }
        return 0F;
    }

    public CategoryWithDetails(Category category) {
        this.setId(category.getId());
        this.setName(category.getName());
        this.setMonthly(category.isMonthly());
        this.setSum(category.getSum());
        this.total = 0f;
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

    public boolean isMonthly() {
        return monthly;
    }

    public void setMonthly(boolean monthly) {
        this.monthly = monthly;
    }

    public Float getSum() {
        return sum == null ? 0 : sum;
    }

    public void setSum(Float sum) {
        this.sum = sum;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public void addSpending(float sum) {
        this.total += sum;
    }

    public void setPeriod(Period period) {
        mPeriod = period;
    }

    public Period getPeriod() {
        return mPeriod;
    }
}
