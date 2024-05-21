import java.util.HashMap;
import java.util.Scanner;

public class lab {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int q = input.nextInt();
        String s = input.nextLine();
        while(q-- > 0) {
            s = input.nextLine();
            System.out.println(palindromeReorder(s));
        }
    }
    public static String palindromeReorder(String s){
        String out = "";

        //a hashmap to store letter frequencies
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            }
            else{
                map.put(c, 1);
            }
        }
        //check if palindrome is possible
        int odd = 0;
        for(Character c : map.keySet()) {
            if (map.get(c) % 2 == 1) {
                odd++;
            }
        }
        if (odd > 1) {
            out = "NO";
            return out;
        }

        //reorder if possible
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        for(Character c : map.keySet()) {
            for(int j = 0; j < map.get(c) / 2; j++){
                sb1.append(c);
                sb2.insert(0, c);
            }
        }
        if(odd == 1){
            for(Character c : map.keySet()) {
                if (map.get(c) % 2 == 1) {
                    sb1.append(c);
                    break;
                }
            }
        }
        out = sb1.toString() + sb2.toString();

        return out;
    }
}