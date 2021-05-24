import java.util.Arrays;

public class Anagrams {
    public static boolean check(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        } else {
            char[] arr1 = s1.toLowerCase().toCharArray();
            char[] arr2 = s2.toLowerCase().toCharArray();
            Arrays.sort(arr1);
            Arrays.sort(arr2);
            return Arrays.equals(arr1, arr2);
        }
    }
}
