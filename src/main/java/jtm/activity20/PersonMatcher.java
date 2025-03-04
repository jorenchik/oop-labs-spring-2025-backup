package jtm.activity20;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface PersonMatcher {

    void addPerson(RandomPerson person);

    List<RandomPerson> getPersonList();


}
