package mainPackage;

import java.util.ArrayList;
import java.util.List;

public class PracticalOne {

    private List<String> nextStates;

    public PracticalOne() {
        nextStates = new ArrayList<String>();
        mainPackage.CodeTracker.markExecuted("PracticalOne", "14");
    }

    public static void main(String[] args) {
        mainPackage.CodeTracker.markExecuted("PracticalOne", "19");
        PracticalOne p1 = new PracticalOne();
        mainPackage.CodeTracker.markExecuted("PracticalOne", "23");
        System.out.println(p1.nextstates("MIIIIUIIIIU"));
    }

    public List<String> nextstates(String s) {
        mainPackage.CodeTracker.markExecuted("PracticalOne", "28");
        String input;
        mainPackage.CodeTracker.markExecuted("PracticalOne", "29");
        input = s;
        mainPackage.CodeTracker.markExecuted("PracticalOne", "30");
        int x = 10;
        do {
            mainPackage.CodeTracker.markExecuted("PracticalOne", "33");
            System.out.print("value of x : " + x);
            mainPackage.CodeTracker.markExecuted("PracticalOne", "34");
            x++;
            mainPackage.CodeTracker.markExecuted("PracticalOne", "35");
            System.out.print("\n");
        } while (x < 20);
        mainPackage.CodeTracker.markExecuted("PracticalOne", "32");
        // rule 1 - if it ends in an I
        if (s.charAt(s.length() - 1) == 'I') {
            mainPackage.CodeTracker.markExecuted("PracticalOne", "43");
            nextStates.add(input += 'U');
            mainPackage.CodeTracker.markExecuted("PracticalOne", "44");
            input = s;
        }
        mainPackage.CodeTracker.markExecuted("PracticalOne", "41");
        try {
            mainPackage.CodeTracker.markExecuted("PracticalOne", "48");
            System.out.println("hi");
        } catch (Exception e) {
            e.printStackTrace();
        }
        mainPackage.CodeTracker.markExecuted("PracticalOne", "47");
        // rule 2 - if it starts with M
        if (s.charAt(0) == 'M') {
            mainPackage.CodeTracker.markExecuted("PracticalOne", "58");
            String doubleString = input.substring(1, input.length());
            mainPackage.CodeTracker.markExecuted("PracticalOne", "60");
            nextStates.add(input += doubleString);
            mainPackage.CodeTracker.markExecuted("PracticalOne", "61");
            input = s;
        }
        mainPackage.CodeTracker.markExecuted("PracticalOne", "56");
        // rule 3 if the string contains III replace with U
        if (s.contains("III")) {
            mainPackage.CodeTracker.markExecuted("PracticalOne", "67");
            String subString = "";
            mainPackage.CodeTracker.markExecuted("PracticalOne", "68");
            String checkedString = "";
            for (int i = 1; i < input.length(); i++) {
                mainPackage.CodeTracker.markExecuted("PracticalOne", "72");
                checkedString = input.substring(0, i);
                mainPackage.CodeTracker.markExecuted("PracticalOne", "73");
                subString = input.substring(i);
                if (subString.contains("III")) {
                    mainPackage.CodeTracker.markExecuted("PracticalOne", "76");
                    String updatedSequence = (checkedString + subString.replaceFirst("III", "U"));
                    if (!nextStates.contains(updatedSequence)) {
                        nextStates.add(checkedString + subString.replaceFirst("III", "U"));
                        mainPackage.CodeTracker.markExecuted("PracticalOne", "78");
                    }
                    mainPackage.CodeTracker.markExecuted("PracticalOne", "77");
                }
                mainPackage.CodeTracker.markExecuted("PracticalOne", "75");
            }
            mainPackage.CodeTracker.markExecuted("PracticalOne", "70");
            mainPackage.CodeTracker.markExecuted("PracticalOne", "81");
            input = s;
        }
        mainPackage.CodeTracker.markExecuted("PracticalOne", "65");
        // rule4 - if the string contains UU replace it with ""
        if (s.contains("UU")) {
            mainPackage.CodeTracker.markExecuted("PracticalOne", "87");
            String subString = "";
            mainPackage.CodeTracker.markExecuted("PracticalOne", "88");
            String checkedString = "";
            for (int i = 1; i < input.length(); i++) {
                mainPackage.CodeTracker.markExecuted("PracticalOne", "92");
                checkedString = input.substring(0, i);
                mainPackage.CodeTracker.markExecuted("PracticalOne", "93");
                subString = input.substring(i);
                if (subString.contains("UU")) {
                    mainPackage.CodeTracker.markExecuted("PracticalOne", "96");
                    String updatedSequence = checkedString + subString.replaceFirst("UU", "");
                    if (!nextStates.contains(updatedSequence)) {
                        nextStates.add(updatedSequence);
                        mainPackage.CodeTracker.markExecuted("PracticalOne", "98");
                    }
                    mainPackage.CodeTracker.markExecuted("PracticalOne", "97");
                }
                mainPackage.CodeTracker.markExecuted("PracticalOne", "95");
            }
            mainPackage.CodeTracker.markExecuted("PracticalOne", "90");
        }
        mainPackage.CodeTracker.markExecuted("PracticalOne", "85");
        mainPackage.CodeTracker.markExecuted("PracticalOne", "103");
        return nextStates;
    }
}
