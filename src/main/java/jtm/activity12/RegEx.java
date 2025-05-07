// File: src/jtm/activity12/RegEx.java
package jtm.activity12;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx {

    /**
     * Finds out if the digits in the input string can form a "lucky number"
     * (digit sum == 25).
     *
     * @param input any string
     * @return true if sum of all digits is 25
     */
    public boolean isLuckyNumber(String input) {
        // Remove all non-digits
        String digitsOnly = input.replaceAll("\\D+", "");
        int sum = 0;
        for (char c : digitsOnly.toCharArray()) {
            sum += c - '0';
        }
        return sum == 25;
    }

    /**
     * Finds "Kenny" or "Kelly" variants with at least two n's or l's.
     * e.g. "Kennny" or "Kelllly".
     *
     * @param input arbitrary string
     * @return index of match or -1
     */
    public int findKenny(String input) {
        // Ke + (n{2,}|l{2,}) + y
        Pattern p = Pattern.compile("Ke(n{2,}|l{2,})y");
        Matcher m = p.matcher(input);
        return m.find() ? m.start() : -1;
    }

    /**
     * Validates a Riga city phone number:
     * Optional +371, then 67 or 66, then exactly 6 digits.
     *
     * @param telephoneNumber candidate string
     * @return true if matches
     */
    public boolean isGood(String telephoneNumber) {
        return telephoneNumber.matches("^(?:\\+371)?(?:67|66)\\d{6}$");
    }
}
