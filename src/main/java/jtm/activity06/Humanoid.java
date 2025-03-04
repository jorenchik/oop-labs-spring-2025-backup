package jtm.activity06;

public interface Humanoid {

    /**
     * Birth weight for Humanoids
     */
    int BirthWeight = 2;

    /**
     * @param food If Humanoid's stomach is empty, eat food and gain weight
     */
    void eat(Integer food);

    /**
     * @return eaten food from the stomach
     */
    Object vomit();

    /**
     * @return "Alive" or "Dead" depending on his status
     */
    String isAlive();

    /**
     * @return "Dead" if humanoid successfully killed himself
     */
    String killHimself();

    /**
     * @return current weight = BirthWeight + weight of the stomach of the current
     * humanoid.
     */
    int getWeight();

    /**
     * @return value of Humanoid in form "ImplementingType: weight
     * [contentOfstomach]";
     */
    String toString();
}
