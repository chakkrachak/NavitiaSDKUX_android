package org.kisio.NavitiaSDKUX.BusinessLogic;

import org.kisio.NavitiaSDK.models.Disruption;
import org.kisio.NavitiaSDK.models.LinkSchema;
import org.kisio.NavitiaSDK.models.Period;
import org.kisio.NavitiaSDK.models.Section;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SectionMatcher {
    public static List<Disruption> getMatchingDisruptions(Section section, List<Disruption> disruptions, Date date) {
        if (disruptions == null) {
            return new ArrayList<>();
        }

        List<String> linkIdsWithDisruption = new ArrayList<>();
        for (LinkSchema linkSchema: section.getDisplayInformations().getLinks()) {
            if (linkSchema.getType().equals("disruption") && linkSchema.getId() != null) {
                linkIdsWithDisruption.add(linkSchema.getId());
            }
        }

        List<Disruption> disruptionsResult = new ArrayList<>();
        for (Disruption disruption: disruptions) {
            if (disruption.getId() != null && linkIdsWithDisruption.contains(disruption.getId()) && disruption.getApplicationPeriods() != null) {
                for (Period period: disruption.getApplicationPeriods()) {
                    if (PeriodMatcher.contains(period, (date != null ? date : new Date()))) {
                        disruptionsResult.add(disruption);
                    }
                }
            }
        }

        return disruptionsResult;
    }
}