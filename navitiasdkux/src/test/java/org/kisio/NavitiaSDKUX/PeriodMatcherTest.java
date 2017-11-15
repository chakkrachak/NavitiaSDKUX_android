package org.kisio.NavitiaSDKUX;

import org.junit.Test;
import static org.junit.Assert.*;
import org.kisio.NavitiaSDK.models.Period;
import org.kisio.NavitiaSDKUX.BusinessLogic.PeriodMatcher;

public class PeriodMatcherTest {
    @Test
    public void containsWithString() {
        Period period = new Period();
        period.begin("20170928T140500");
        period.end("20171201T194459");

        assertTrue(PeriodMatcher.contains(period, "20170928T140500"));
        assertTrue(PeriodMatcher.contains(period, "20171111T220000"));
        assertTrue(PeriodMatcher.contains(period, "20171201T194459"));
        assertFalse(PeriodMatcher.contains(period, "20180101T001000"));
        assertFalse(PeriodMatcher.contains(period, ""));
    }
}
