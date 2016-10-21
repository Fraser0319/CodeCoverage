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
        List<String> path = new ArrayList<String>();
        path.add("MI");
        path.add("MII");
        PracticalTwo p2 = new PracticalTwo();
        // System.out.println(p2.extendPath(path));
        // System.out.println(p2.breadthFirstSearch("MUIUUIU"));
        // System.out.println(p2.depthLimitedDFS("MUIUUIU",5));
        System.out.println(p2.iterativeDeepening("MUIUUIU"));
    }

    public List<List<String>> extendPath(List<String> path) {
        PracticalOne p1 = new PracticalOne();
        int lastElem = path.size() - 1;
        List<String> nextStates = new ArrayList<String>();
        List<List<String>> extendPath = new ArrayList<List<String>>();
        nextStates.addAll(p1.nextstates(path.get(lastElem)));
        for (String s : nextStates) {
            mainPackage.CodeTracker.markExecuted("PracticalTwo", "41");
            List<String> newPath = new ArrayList<String>(path);
            mainPackage.CodeTracker.markExecuted("PracticalTwo", "42");
            newPath.add(s);
            mainPackage.CodeTracker.markExecuted("PracticalTwo", "43");
            extendPath.add(newPath);
        }
        if (2 == 4) {
            mainPackage.CodeTracker.markExecuted("PracticalTwo", "47");
            System.out.println("hello");
        }
        return extendPath;
    }

    public List<String> breadthFirstSearch(String goalString) {
        int countExtendPathCalls = 0;
        Queue<List<String>> Agenda = new LinkedList<List<String>>();
        List<String> path = new ArrayList<String>();
        List<String> currentPath = new ArrayList<String>();
        path.add("MI");
        Agenda.add(path);
        while (!Agenda.isEmpty()) {
            mainPackage.CodeTracker.markExecuted("PracticalTwo", "61");
            currentPath = Agenda.poll();
            if (goalString.equals(currentPath.get(currentPath.size() - 1))) {
                mainPackage.CodeTracker.markExecuted("PracticalTwo", "63");
                System.out.println("lemgth of current path: " + currentPath.size());
                mainPackage.CodeTracker.markExecuted("PracticalTwo", "64");
                System.out.println("length of the agenda: " + Agenda.size());
                mainPackage.CodeTracker.markExecuted("PracticalTwo", "65");
                System.out.println("number of times extendPath is called: " + countExtendPathCalls);
                mainPackage.CodeTracker.markExecuted("PracticalTwo", "66");
                return currentPath;
            } else {
                mainPackage.CodeTracker.markExecuted("PracticalTwo", "68");
                countExtendPathCalls++;
                mainPackage.CodeTracker.markExecuted("PracticalTwo", "69");
                Agenda.addAll(extendPath(currentPath));
            }
            mainPackage.CodeTracker.markExecuted("PracticalTwo", "62");
        }
        return currentPath;
    }

    public List<String> depthLimitedDFS(String goalString, int limit) {
        int countExtendPathCalls = 0;
        Stack<List<String>> Agenda = new Stack<List<String>>();
        List<String> path = new ArrayList<String>();
        List<String> currentPath = new ArrayList<String>();
        path.add("MI");
        Agenda.add(path);
        while (!Agenda.isEmpty()) {
            mainPackage.CodeTracker.markExecuted("PracticalTwo", "84");
            currentPath = Agenda.pop();
            if (goalString.equals(currentPath.get(currentPath.size() - 1))) {
                mainPackage.CodeTracker.markExecuted("PracticalTwo", "86");
                System.out.println("lemgth of current path: " + currentPath.size());
                mainPackage.CodeTracker.markExecuted("PracticalTwo", "87");
                System.out.println("length of the agenda: " + Agenda.size());
                mainPackage.CodeTracker.markExecuted("PracticalTwo", "88");
                System.out.println("number of times extendPath is called: " + countExtendPathCalls);
                mainPackage.CodeTracker.markExecuted("PracticalTwo", "89");
                return currentPath;
            } else {
                if (limit == currentPath.size()) {
                } else {
                    for (int i = 0; i < extendPath(currentPath).size(); i++) {
                        mainPackage.CodeTracker.markExecuted("PracticalTwo", "95");
                        List<String> newPath = new ArrayList<String>();
                        mainPackage.CodeTracker.markExecuted("PracticalTwo", "96");
                        newPath.addAll(extendPath(currentPath).get(i));
                        mainPackage.CodeTracker.markExecuted("PracticalTwo", "97");
                        Agenda.push(newPath);
                        mainPackage.CodeTracker.markExecuted("PracticalTwo", "98");
                        countExtendPathCalls++;
                    }
                    mainPackage.CodeTracker.markExecuted("PracticalTwo", "94");
                }
                mainPackage.CodeTracker.markExecuted("PracticalTwo", "91");
            }
            mainPackage.CodeTracker.markExecuted("PracticalTwo", "85");
        }
        return currentPath;
    }

    public List<String> iterativeDeepening(String goalString) {
        List<String> bestPath = new ArrayList<String>();
        for (int i = 2; i <= 50; i++) {
            mainPackage.CodeTracker.markExecuted("PracticalTwo", "109");
            int lastElem = depthLimitedDFS("MUIUUIU", i).size() - 1;
            mainPackage.CodeTracker.markExecuted("PracticalTwo", "110");
            bestPath = depthLimitedDFS("MUIUUIU", i);
            if (bestPath.get(lastElem).equals(goalString)) {
                mainPackage.CodeTracker.markExecuted("PracticalTwo", "112");
                return bestPath;
            }
            mainPackage.CodeTracker.markExecuted("PracticalTwo", "111");
        }
        return null;
    }
}
