package jtm.activity06;

public class Martian implements Alien {

    public int weight = BirthWeight;

    public Object stomachContents = null;

    @Override
    public void eat(Object item) {
        if (stomachContents != null || item == null) return;

        int gainedWeight = 0;

        if (item instanceof Integer) {
            gainedWeight = (Integer) item;
        } else if (item instanceof Humanoid) {
            Humanoid humanoid = (Humanoid) item;
            gainedWeight = humanoid.getWeight();
            //??
            //humanoid.kill();
        } else if (item instanceof Alien) {
            Alien alien = (Alien) item;
            gainedWeight = alien.getWeight();
            //??
            //alien.kill(); 
        } else {
            return;
        }

        weight += gainedWeight;
        stomachContents = item;
    }

    public void eat(Integer food) {
        if (stomachContents == null && food != null) {
            stomachContents = food;
            weight += food;
        }
    }

    public Object vomit() {
        Object vomited = stomachContents;
        if (stomachContents != null) {
			if (stomachContents instanceof Integer) {
				weight -= (Integer) stomachContents;
			} else if (stomachContents instanceof Humanoid) {
				Humanoid humanoid = (Humanoid) stomachContents;
				weight -= humanoid.getWeight();
			} else if (stomachContents instanceof Alien) {
				Alien alien = (Alien) stomachContents;
				weight -= alien.getWeight();
			}
        }
        stomachContents = null;
        return vomited;
    }

    public int getWeight() {
        return weight;
    }

    public String isAlive() {
        return "I AM IMMORTAL!";
    }

    public String killHimself() {
        return "I AM IMMORTAL!";
    }

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

    public Object clone() throws CloneNotSupportedException {
        Martian copy = new Martian();
        copy.weight = this.weight;

        if (this.stomachContents != null) {
            copy.stomachContents = clone(this.stomachContents);
        }

        return copy;
    }

    private Object clone(Object current) throws CloneNotSupportedException {
		if (current instanceof Martian) {
			return ((Martian) current).clone();
		} else if (current instanceof Human) {
			//current.getClass().getMethod("clone").invoke(current)
			return ((Human) current).clone();
		} else if (current instanceof Integer) {
			return current;
		} else {
			throw new CloneNotSupportedException();
		}
	}
}
