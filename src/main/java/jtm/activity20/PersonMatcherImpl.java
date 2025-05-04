package jtm.activity20;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PersonMatcherImpl implements PersonMatcher {

    private final List<RandomPerson> people;

    public PersonMatcherImpl() {
        this.people = new ArrayList<>();
    }

    @Override
    public void addPerson(RandomPerson person) {
        if (person != null) {
            people.add(person);
        }
    }

    @Override
    public List<RandomPerson> getPersonList() {
        return List.copyOf(people); // Unmodifiable copy
    }

    @Override
    public Stream<RandomPerson> getPersonStream() {
        Stream.Builder<RandomPerson> builder = Stream.builder();
        for (RandomPerson p : people) {
            builder.add(p);
        }
        return builder.build();
    }
}
