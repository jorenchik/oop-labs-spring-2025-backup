package jtm.activity12;

public enum Holiday {
    NEW_YEAR(1, 1),
    WOMAN_DAY(3, 8),
    CHUCK_NORRIS_BIRTHSDAY(3, 10),
    FOOLS_DAY(4, 1),
    WORLD_END(12, 21);

    private final int month;
    private final int day;

    Holiday(int month, int day) {
        this.month = month;
        this.day   = day;
    }

    public static Holiday getNearest(int currentMonth, int currentDay) {
        for (Holiday h : values()) {
            if (h.month  > currentMonth ||
               (h.month == currentMonth && h.day >= currentDay)) {
                return h;
            }
        }
        return values()[0];
    }

    public int getMonth() { return month; }
    public int getDay()   { return day;   }
}
