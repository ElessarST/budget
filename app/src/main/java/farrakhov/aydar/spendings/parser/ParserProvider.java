package farrakhov.aydar.spendings.parser;

import farrakhov.aydar.spendings.content.CreditCardType;

/**
 * Created by aydar on 09.10.16.
 */

public class ParserProvider {

    public static IParser getParser(CreditCardType type) {
        switch (type) {
            case ALPHA_BANK:
                return new AlfaBankParser();
            case SBERBANK:
                return new SberbankParser();
        }
        return null;
    }

}
