package mainPackage;

import java.util.ArrayList;
import java.util.List;

public class PracticalOne {

    private List<String> nextStates;

    public PracticalOne() {
        nextStates = new ArrayList<String>();
    }

    public static void main(String[] args) {
    	
        PracticalOne p1 = new PracticalOne();
        // Step 1 - create a new node
        // (Various options)
        // NameExpr systemOut = NameExpr.name("System.out");
        // MethodCallExpr call = new MethodCallExpr(systemOut, "println");
        // MethodCallExpr call = new MethodCallExpr(NameExpr.name("System.out"), "println");
        System.out.println(p1.nextstates("MIIIIUIIIIU"));
    }

    public List<String> nextstates(String s) {
        String input;
        input = s;
        // rule 1 - if it ends in an I
        if (s.charAt(s.length() - 1) == 'I') {
            nextStates.add(input += 'U');
            mainPackage.CodeTracker.markExecuted("PracticalOne", "38");
            input = s;
            mainPackage.CodeTracker.markExecuted("PracticalOne", "39");
        }
        if (2 == 4) {
            if (2 < 4) {
                System.out.println("hello");
                mainPackage.CodeTracker.markExecuted("PracticalOne", "44");
            }
            mainPackage.CodeTracker.markExecuted("PracticalOne", "43");
        }
        // rule 2 - if it starts with M
        if (s.charAt(0) == 'M') {
            String doubleString = input.substring(1, input.length());
            mainPackage.CodeTracker.markExecuted("PracticalOne", "52");
            nextStates.add(input += doubleString);
            mainPackage.CodeTracker.markExecuted("PracticalOne", "54");
            input = s;
            mainPackage.CodeTracker.markExecuted("PracticalOne", "55");
        }
        // rule 3 if the string contains III replace with U
        if (s.contains("III")) {
            String subString = "";
            mainPackage.CodeTracker.markExecuted("PracticalOne", "61");
            String checkedString = "";
            mainPackage.CodeTracker.markExecuted("PracticalOne", "62");
            for (int i = 1; i < input.length(); i++) {
                checkedString = input.substring(0, i);
                mainPackage.CodeTracker.markExecuted("PracticalOne", "66");
                subString = input.substring(i);
                mainPackage.CodeTracker.markExecuted("PracticalOne", "67");
                if (subString.contains("III")) {
                    String updatedSequence = (checkedString + subString.replaceFirst("III", "U"));
                    mainPackage.CodeTracker.markExecuted("PracticalOne", "70");
                    if (!nextStates.contains(updatedSequence)) {
                        nextStates.add(checkedString + subString.replaceFirst("III", "U"));
                        mainPackage.CodeTracker.markExecuted("PracticalOne", "72");
                    }
                    mainPackage.CodeTracker.markExecuted("PracticalOne", "71");
                }
                mainPackage.CodeTracker.markExecuted("PracticalOne", "69");
            }
            mainPackage.CodeTracker.markExecuted("PracticalOne", "64");
            input = s;
            mainPackage.CodeTracker.markExecuted("PracticalOne", "75");
        }
        // rule4 - if the string contains UU replace it with ""
        if (s.contains("UU")) {
            String subString = "";
            mainPackage.CodeTracker.markExecuted("PracticalOne", "81");
            String checkedString = "";
            mainPackage.CodeTracker.markExecuted("PracticalOne", "82");
            for (int i = 1; i < input.length(); i++) {
                checkedString = input.substring(0, i);
                mainPackage.CodeTracker.markExecuted("PracticalOne", "86");
                subString = input.substring(i);
                mainPackage.CodeTracker.markExecuted("PracticalOne", "87");
                if (subString.contains("UU")) {
                    String updatedSequence = checkedString + subString.replaceFirst("UU", "");
                    mainPackage.CodeTracker.markExecuted("PracticalOne", "90");
                    if (!nextStates.contains(updatedSequence)) {
                        nextStates.add(updatedSequence);
                        mainPackage.CodeTracker.markExecuted("PracticalOne", "92");
                    }
                    mainPackage.CodeTracker.markExecuted("PracticalOne", "91");
                }
                mainPackage.CodeTracker.markExecuted("PracticalOne", "89");
            }
            mainPackage.CodeTracker.markExecuted("PracticalOne", "84");
        }
        return nextStates;
    }
}
