package jtm.activity03;

/**
 * Black Knight is brave soldier who fights till he is alive. He doesn't bother
 * if some of his arms or legs are cut off. You can kill him only when he lose
 * head. More info at:
 * https://en.wikipedia.org/wiki/Black_Knight_%28Monty_Python%29
 */
public class BlackKnight {
    // Class variables which are shared between all class members (objects)
    public static short totalKnights; // total number of knights at the start of
    // the battle
    public static short aliveKnights; // total number of alive knights
    public static short deadKnights; // total number of dead knights
    public static BlackKnight[] knights; // array of knights in the battle

    // instance variables which are separate for each class member (object)
    public final String name; // knight name
    public byte arms, legs, head; // number of limbs
    public boolean alive; // is knight alive

    public static void setBattle(int initialNumber) {
    	knights = new BlackKnight[initialNumber];
    	totalKnights = 0;
    	deadKnights = 0;
    	aliveKnights = 0;
    }

    public BlackKnight(String name) {
    	arms = 2;
    	legs = 2;
    	head = 1;
    	alive = true;
    	knights[totalKnights++] = this;
    	aliveKnights++;

        this.name = name;
    }

    public String cutOffArm() {
    	if (!alive) {
    		return "Only chicken beats dead!";
    	}
    	if (arms > 0) {
    		arms--;
    		return "Tis but a scratch!";
    	} else {
    		return "Haah!";
    	}
    }

    public String cutOffLeg() {
    	if (!alive) {
    		return "Only chicken beats dead!";
    	}
    	if (legs > 0) {
    		legs--;
    		return "Had worse!";
    	} else {
    		return "Haah!";
    	}
    }

    public String cutOffHead() {
    	if (!alive) {
    		return "Only chicken beats dead!";
    	}
    	if (head > 0) {
    		head--;
    		alive = false;
    		aliveKnights--;
    		deadKnights++;
    	}
    	var sb = new StringBuilder();
    	if (aliveKnights > 0) {
			sb.append("You'll never win! ");
			boolean isFirst = true;
    		for (int i = 0; i < totalKnights; i++) {
    			if (knights[i].alive) {
    				if (isFirst) {
    					isFirst = false;
    				} else {
    					sb.append(", ");
    				}
					sb.append(knights[i].name);
    			}
    		}
			sb.append(" will still fight!");
    	} else {
			sb.append("You'll burn in hell forever!");
    	}
    	return sb.toString();
    }
}