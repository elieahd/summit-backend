package utils;

import summit.models.Member;
import summit.models.TrackerInterval;
import summit.models.TrackerTemplate;

import static utils.Randomizer.random;
import static utils.Randomizer.randomInteger;
import static utils.Randomizer.randomString;

public class TestData {

    private TestData() {
        // utility classes shouldn't be initialized
    }

    public static TrackerTemplate aTrackerTemplate() {
        TrackerTemplate trackerTemplate = new TrackerTemplate();
        trackerTemplate.setId(randomInteger());
        trackerTemplate.setName(randomString());
        trackerTemplate.setDescription(randomString());
        trackerTemplate.setInterval(random(TrackerInterval.class));
        return trackerTemplate;
    }

    public static Member aMember() {
        Member member = new Member();
        member.setId(randomInteger());
        member.setName(randomString());
        member.setUsername(randomString());
        return member;
    }
}
