package jtm.activity06;

public class Martian implements Alien {

    private int weight = BirthWeight;
    private Object stomachContents = null;

    @Override
    public void eat(Object item) {
        if (stomachContents != null || item == null) return;

        int gainedWeight = 0;

        if (item instanceof Integer) {
            gainedWeight = (Integer) item;
        } else if (item instanceof Humanoid) {
            Humanoid humanoid = (Humanoid) item;
            gainedWeight = humanoid.getWeight();
//            humanoid.kill();
        } else if (item instanceof Alien) {
            Alien alien = (Alien) item;
            gainedWeight = alien.getWeight();
//            alien.killHimself(); 
        } else {
            return;
        }

        weight += gainedWeight;
        stomachContents = item;
    }

    @Override
    public Object vomit() {
        Object vomited = stomachContents;
        stomachContents = null;
        return vomited;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public String isAlive() {
        return "I AM IMMORTAL!";
    }

    @Override
    public String killHimself() {
        return "I AM IMMORTAL!";
    }

	@Override
	public String toString() {
		String contentString = "";

		if (stomachContents != null) {
			if (stomachContents instanceof Martian) {
				contentString = ((Martian) stomachContents).toString();
			} else if (stomachContents instanceof Alien) {
				contentString = ((Alien) stomachContents).toString();
			} else if (stomachContents instanceof Humanoid) {
				contentString = ((Humanoid) stomachContents).toString();
			} else {
				contentString = stomachContents.toString();
			}
		}

		return String.format("%s: %d [%s]", getClass().getSimpleName(), weight, contentString);
	}
}
