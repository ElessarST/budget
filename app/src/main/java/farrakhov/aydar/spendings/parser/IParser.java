package farrakhov.aydar.spendings.parser;

import java.util.Date;

import farrakhov.aydar.spendings.content.Spending;

/**
 * Created by aydar on 09.10.16.
 */

public interface IParser {

    Spending parse(String text, Date date);

}
