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
        mainPackage.CodeTracker.markExecuted("PracticalOne.java", "19");
        PracticalOne p1 = new PracticalOne();
        mainPackage.CodeTracker.markExecuted("PracticalOne.java", "23");
        System.out.println(p1.nextstates("MIIIIUIIIIU"));
    }

    public List<String> nextstates(String s) {
        mainPackage.CodeTracker.markExecuted("PracticalOne.java", "28");
        String input;
        mainPackage.CodeTracker.markExecuted("PracticalOne.java", "29");
        input = s;
        mainPackage.CodeTracker.markExecuted("PracticalOne.java", "30");
        int x = 10;
        do {
            mainPackage.CodeTracker.markExecuted("PracticalOne.java", "34");
            //			System.out.print("value of x : " + x);
            x++;
        } while (x < 20);
        mainPackage.CodeTracker.markExecuted("PracticalOne.java", "32");
        // rule 1 - if it ends in an I
        if (s.charAt(s.length() - 1) == 'I') {
            mainPackage.CodeTracker.markExecuted("PracticalOne.java", "43");
            nextStates.add(input += 'U');
            mainPackage.CodeTracker.markExecuted("PracticalOne.java", "44");
            input = s;
        }
        mainPackage.CodeTracker.markExecuted("PracticalOne.java", "41");
        // rule 2 - if it starts with M
        if (s.charAt(0) == 'M') {
            mainPackage.CodeTracker.markExecuted("PracticalOne.java", "58");
            String doubleString = input.substring(1, input.length());
            mainPackage.CodeTracker.markExecuted("PracticalOne.java", "60");
            nextStates.add(input += doubleString);
            mainPackage.CodeTracker.markExecuted("PracticalOne.java", "61");
            input = s;
        }
        mainPackage.CodeTracker.markExecuted("PracticalOne.java", "56");
        // rule 3 if the string contains III replace with U
        if (s.contains("III")) {
            mainPackage.CodeTracker.markExecuted("PracticalOne.java", "67");
            String subString = "";
            mainPackage.CodeTracker.markExecuted("PracticalOne.java", "68");
            String checkedString = "";
            for (int i = 1; i < input.length(); i++) {
                mainPackage.CodeTracker.markExecuted("PracticalOne.java", "72");
                checkedString = input.substring(0, i);
                mainPackage.CodeTracker.markExecuted("PracticalOne.java", "73");
                subString = input.substring(i);
                if (subString.contains("III")) {
                    mainPackage.CodeTracker.markExecuted("PracticalOne.java", "76");
                    String updatedSequence = (checkedString + subString.replaceFirst("III", "U"));
                    if (!nextStates.contains(updatedSequence)) {
                        nextStates.add(checkedString + subString.replaceFirst("III", "U"));
                        mainPackage.CodeTracker.markExecuted("PracticalOne.java", "78");
                    }
                    mainPackage.CodeTracker.markExecuted("PracticalOne.java", "77");
                }
                mainPackage.CodeTracker.markExecuted("PracticalOne.java", "75");
            }
            mainPackage.CodeTracker.markExecuted("PracticalOne.java", "70");
            mainPackage.CodeTracker.markExecuted("PracticalOne.java", "81");
            input = s;
        }
        mainPackage.CodeTracker.markExecuted("PracticalOne.java", "65");
        // rule4 - if the string contains UU replace it with ""
        if (s.contains("UU")) {
            mainPackage.CodeTracker.markExecuted("PracticalOne.java", "87");
            String subString = "";
            mainPackage.CodeTracker.markExecuted("PracticalOne.java", "88");
            String checkedString = "";
            for (int i = 1; i < input.length(); i++) {
                mainPackage.CodeTracker.markExecuted("PracticalOne.java", "92");
                checkedString = input.substring(0, i);
                mainPackage.CodeTracker.markExecuted("PracticalOne.java", "93");
                subString = input.substring(i);
                if (subString.contains("UU")) {
                    mainPackage.CodeTracker.markExecuted("PracticalOne.java", "96");
                    String updatedSequence = checkedString + subString.replaceFirst("UU", "");
                    if (!nextStates.contains(updatedSequence)) {
                        nextStates.add(updatedSequence);
                        mainPackage.CodeTracker.markExecuted("PracticalOne.java", "98");
                    }
                    mainPackage.CodeTracker.markExecuted("PracticalOne.java", "97");
                }
                mainPackage.CodeTracker.markExecuted("PracticalOne.java", "95");
            }
            mainPackage.CodeTracker.markExecuted("PracticalOne.java", "90");
        }
        mainPackage.CodeTracker.markExecuted("PracticalOne.java", "85");
        mainPackage.CodeTracker.markExecuted("PracticalOne.java", "103");
        return nextStates;
    }
}
