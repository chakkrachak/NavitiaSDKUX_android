package org.kisio.NavitiaSDKUX.BusinessLogic;

import org.kisio.NavitiaSDK.models.Period;
import org.kisio.NavitiaSDKUX.Util.Metrics;

import java.util.Date;

public class PeriodMatcher {
    public static Boolean contains(Period period, Date date) {
        Date beginDate = Metrics.navitiaDate(period.getBegin());
        Date endDate = Metrics.navitiaDate(period.getEnd());

        if (date.equals(beginDate) || date.equals(endDate) || (date.after(beginDate) && date.before(endDate))) {
            return true;
        }

        return false;
    }

    public static Boolean contains(Period period, String isoDateString) {
        Date date = new Date(0);
        if (!isoDateString.equals("")) {
            date = Metrics.navitiaDate(isoDateString);
        }
        return contains(period, date);
    }
}
