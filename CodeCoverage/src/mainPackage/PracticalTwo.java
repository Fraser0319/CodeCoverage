package mainPackage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import javax.print.attribute.Size2DSyntax;
import mainPackage.PracticalOne;

public class PracticalTwo {

    public static void main(String[] args) {
        mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "17");
        List<String> path = new ArrayList<String>();
        mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "18");
        path.add("MI");
        mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "19");
        path.add("MII");
        mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "21");
        PracticalTwo p2 = new PracticalTwo();
        mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "26");
        // System.out.println(p2.depthLimitedDFS("MUIUUIU",5));
        System.out.println(p2.iterativeDeepening("MUIUUIU"));
    }

    public List<List<String>> extendPath(List<String> path) {
        mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "31");
        PracticalOne p1 = new PracticalOne();
        mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "33");
        int lastElem = path.size() - 1;
        mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "35");
        List<String> nextStates = new ArrayList<String>();
        mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "36");
        List<List<String>> extendPath = new ArrayList<List<String>>();
        mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "38");
        nextStates.addAll(p1.nextstates(path.get(lastElem)));
        for (String s : nextStates) {
            mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "41");
            List<String> newPath = new ArrayList<String>(path);
            mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "42");
            newPath.add(s);
            mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "43");
            extendPath.add(newPath);
        }
        mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "40");
        if (2 == 4) {
            mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "47");
            System.out.println("hello");
        }
        mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "46");
        mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "50");
        return extendPath;
    }

    public List<String> breadthFirstSearch(String goalString) {
        mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "54");
        int countExtendPathCalls = 0;
        mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "55");
        Queue<List<String>> Agenda = new LinkedList<List<String>>();
        mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "56");
        List<String> path = new ArrayList<String>();
        mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "57");
        List<String> currentPath = new ArrayList<String>();
        mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "58");
        path.add("MI");
        mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "59");
        Agenda.add(path);
        while (!Agenda.isEmpty()) {
            mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "61");
            currentPath = Agenda.poll();
            if (goalString.equals(currentPath.get(currentPath.size() - 1))) {
                mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "63");
                System.out.println("lemgth of current path: " + currentPath.size());
                mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "64");
                System.out.println("length of the agenda: " + Agenda.size());
                mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "65");
                System.out.println("number of times extendPath is called: " + countExtendPathCalls);
                mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "66");
                return currentPath;
            } else {
                mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "68");
                countExtendPathCalls++;
                mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "69");
                Agenda.addAll(extendPath(currentPath));
            }
            mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "62");
        }
        mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "60");
        mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "72");
        return currentPath;
    }

    public List<String> depthLimitedDFS(String goalString, int limit) {
        mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "77");
        int countExtendPathCalls = 0;
        mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "78");
        Stack<List<String>> Agenda = new Stack<List<String>>();
        mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "79");
        List<String> path = new ArrayList<String>();
        mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "80");
        List<String> currentPath = new ArrayList<String>();
        mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "81");
        path.add("MI");
        mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "82");
        Agenda.add(path);
        while (!Agenda.isEmpty()) {
            mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "84");
            currentPath = Agenda.pop();
            if (goalString.equals(currentPath.get(currentPath.size() - 1))) {
                mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "86");
                System.out.println("lemgth of current path: " + currentPath.size());
                mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "87");
                System.out.println("length of the agenda: " + Agenda.size());
                mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "88");
                System.out.println("number of times extendPath is called: " + countExtendPathCalls);
                mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "89");
                return currentPath;
            } else {
                if (limit == currentPath.size()) {
                } else {
                    for (int i = 0; i < extendPath(currentPath).size(); i++) {
                        mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "95");
                        List<String> newPath = new ArrayList<String>();
                        mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "96");
                        newPath.addAll(extendPath(currentPath).get(i));
                        mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "97");
                        Agenda.push(newPath);
                        mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "98");
                        countExtendPathCalls++;
                    }
                    mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "94");
                }
                mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "91");
            }
            mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "85");
        }
        mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "83");
        mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "103");
        return currentPath;
    }

    public List<String> iterativeDeepening(String goalString) {
        mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "107");
        List<String> bestPath = new ArrayList<String>();
        for (int i = 2; i <= 50; i++) {
            mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "109");
            int lastElem = depthLimitedDFS("MUIUUIU", i).size() - 1;
            mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "110");
            bestPath = depthLimitedDFS("MUIUUIU", i);
            if (bestPath.get(lastElem).equals(goalString)) {
                mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "112");
                return bestPath;
            }
            mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "111");
        }
        mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "108");
        mainPackage.CodeTracker.markExecuted("PracticalTwo.java", "115");
        return null;
    }
}
