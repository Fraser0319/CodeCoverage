package mainPackage;

import java.util.ArrayList;
import java.util.List;

public class PracticalOne {

    private List<String> nextStates;

    public PracticalOne() {
        nextStates = new ArrayList<String>();
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
        // rule 1 - if it ends in an I
        if (s.charAt(s.length() - 1) == 'I') {
            mainPackage.CodeTracker.markExecuted("PracticalOne", "34");
            nextStates.add(input += 'U');
            mainPackage.CodeTracker.markExecuted("PracticalOne", "35");
            input = s;
        }
        mainPackage.CodeTracker.markExecuted("PracticalOne", "32");
        // rule 2 - if it starts with M
        if (s.charAt(0) == 'M') {
            mainPackage.CodeTracker.markExecuted("PracticalOne", "43");
            String doubleString = input.substring(1, input.length());
            mainPackage.CodeTracker.markExecuted("PracticalOne", "45");
            nextStates.add(input += doubleString);
            mainPackage.CodeTracker.markExecuted("PracticalOne", "46");
            input = s;
        }
        mainPackage.CodeTracker.markExecuted("PracticalOne", "41");
        // rule 3 if the string contains III replace with U
        if (s.contains("III")) {
            mainPackage.CodeTracker.markExecuted("PracticalOne", "52");
            String subString = "";
            mainPackage.CodeTracker.markExecuted("PracticalOne", "53");
            String checkedString = "";
            for (int i = 1; i < input.length(); i++) {
                mainPackage.CodeTracker.markExecuted("PracticalOne", "57");
                checkedString = input.substring(0, i);
                mainPackage.CodeTracker.markExecuted("PracticalOne", "58");
                subString = input.substring(i);
                if (subString.contains("III")) {
                    mainPackage.CodeTracker.markExecuted("PracticalOne", "61");
                    String updatedSequence = (checkedString + subString.replaceFirst("III", "U"));
                    if (!nextStates.contains(updatedSequence)) {
                        nextStates.add(checkedString + subString.replaceFirst("III", "U"));
                        mainPackage.CodeTracker.markExecuted("PracticalOne", "63");
                    }
                    mainPackage.CodeTracker.markExecuted("PracticalOne", "62");
                }
                mainPackage.CodeTracker.markExecuted("PracticalOne", "60");
            }
            mainPackage.CodeTracker.markExecuted("PracticalOne", "55");
            mainPackage.CodeTracker.markExecuted("PracticalOne", "66");
            input = s;
        }
        mainPackage.CodeTracker.markExecuted("PracticalOne", "50");
        // rule4 - if the string contains UU replace it with ""
        if (s.contains("UU")) {
            mainPackage.CodeTracker.markExecuted("PracticalOne", "72");
            String subString = "";
            mainPackage.CodeTracker.markExecuted("PracticalOne", "73");
            String checkedString = "";
            for (int i = 1; i < input.length(); i++) {
                mainPackage.CodeTracker.markExecuted("PracticalOne", "77");
                checkedString = input.substring(0, i);
                mainPackage.CodeTracker.markExecuted("PracticalOne", "78");
                subString = input.substring(i);
                if (subString.contains("UU")) {
                    mainPackage.CodeTracker.markExecuted("PracticalOne", "81");
                    String updatedSequence = checkedString + subString.replaceFirst("UU", "");
                    if (!nextStates.contains(updatedSequence)) {
                        nextStates.add(updatedSequence);
                        mainPackage.CodeTracker.markExecuted("PracticalOne", "83");
                    }
                    mainPackage.CodeTracker.markExecuted("PracticalOne", "82");
                }
                mainPackage.CodeTracker.markExecuted("PracticalOne", "80");
            }
            mainPackage.CodeTracker.markExecuted("PracticalOne", "75");
        }
        mainPackage.CodeTracker.markExecuted("PracticalOne", "70");
        mainPackage.CodeTracker.markExecuted("PracticalOne", "88");
        return nextStates;
    }
}
