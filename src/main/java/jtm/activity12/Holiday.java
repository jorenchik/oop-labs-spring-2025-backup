// File: src/jtm/activity12/Holiday.java
package jtm.activity12;

/**
 * This enum represents holidays, displayed as month + day value.
 * It can return the nearest upcoming holiday.
 */
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

    /**
     * Returns the next holiday on or after the given date.
     * If none remain this year, wraps to the first in the enum.
     *
     * @param currentMonth 1–12
     * @param currentDay   1–31
     * @return nearest upcoming Holiday
     */
    public static Holiday getNearest(int currentMonth, int currentDay) {
        for (Holiday h : values()) {
            if (h.month  > currentMonth ||
               (h.month == currentMonth && h.day >= currentDay)) {
                return h;
            }
        }
        // wrap to first holiday of next year
        return values()[0];
    }

    public int getMonth() { return month; }
    public int getDay()   { return day;   }
}
