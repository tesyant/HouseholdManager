package com.lulua.tesyant.householdmanager.db;

import android.provider.BaseColumns;

/**
 * Created by tesyant on 24/12/17.
 */

public class DatabaseContract {

    static String TABLE_FIXED_NEEDS = "fixedNeeds";
    static String TABLE_UNFIXED_NEEDS = "unfixedNeeds";

    static final class FixedNeedsColumns implements BaseColumns {

        static String FIXED_NAME = "fixedName";
        static String FIXED_QUANTITY = "fixedQuantity";
        static String FIXED_PRICE = "fixedPrice";
        static String FIXED_DATE = "fixedDate";
    }

    static final class UnfixedNeedsColumns implements BaseColumns {

        static String UNFIXED_NAME = "unfixedName";
        static String UNFIXED_QUANTITY = "unfixedQuantity";
        static String UNFIXED_PRICE = "unfixedPrice";
        static String UNFIXED_DATE = "uhfixedDate";
    }
}
