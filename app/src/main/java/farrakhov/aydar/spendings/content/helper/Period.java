package farrakhov.aydar.spendings.content.helper;

/**
 * Created by aydar farrakhov on 01.02.17.
 */

public enum Period {

    MONTH(1),
    WEEK(2),
    DAY(3);

    private int num;

    Period(int num) {
        this.num = num;
    }

    public static Period getByNum(int num) {
        for (Period period : values()) {
            if (period.num == num) {
                return period;
            }
        }
        return MONTH;
    }
}
