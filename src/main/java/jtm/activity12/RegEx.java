package jtm.activity12;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx {

    public boolean isLuckyNumber(String input) {
        String digitsOnly = input.replaceAll("\\D+", "");
        int sum = 0;
        for (char c : digitsOnly.toCharArray()) {
            sum += c - '0';
        }
        return sum == 25;
    }

    public int findKenny(String input) {
        Pattern p = Pattern.compile("Ke(n{2,}|l{2,})y");
        Matcher m = p.matcher(input);
        return m.find() ? m.start() : -1;
    }

    public boolean isGood(String telephoneNumber) {
        return telephoneNumber.matches("^(?:\\+371)?(?:67|66)\\d{6}$");
    }
}
