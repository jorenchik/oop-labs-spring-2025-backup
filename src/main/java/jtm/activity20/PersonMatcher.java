package jtm.activity20;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface PersonMatcher {

    void addPerson(RandomPerson person);

    List<RandomPerson> getPersonList();

    Stream<RandomPerson> getPersonStream();

    // --- default filtering method ---
    default Stream<RandomPerson> getMatchedPersonStream(Stream<RandomPerson> persons,
                                                        boolean isFemale,
                                                        int ageFrom,
                                                        int ageTo,
                                                        float weightFrom,
                                                        float weightTo) {
        Objects.requireNonNull(persons, "Stream must not be null");

        return persons.filter(p ->
                p.female() == isFemale &&
                p.age() >= ageFrom && p.age() <= ageTo &&
                p.weight() >= weightFrom && p.weight() <= weightTo
        );
    }

    // --- static method to collect stream to list ---
    static List<RandomPerson> getPersonList(Stream<RandomPerson> persons) {
        return persons.collect(Collectors.toList());
    }

    // --- static method to get manager instance ---
    static PersonMatcher getPersonManager() {
        return new PersonMatcherImpl();
    }
}
