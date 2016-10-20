package mainPackage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import javax.print.attribute.Size2DSyntax;


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
//        List<Integer> list = new ArrayList<Integer>();
//        list.add(1);
//        list.add(2);
//        switch(list.iterator().next()) {
//            case 1:
//                mainPackage.CodeTracker.markExecuted("PracticalTwo", "39");
//                System.out.println("1");
//                mainPackage.CodeTracker.markExecuted("PracticalTwo", "40");
//                p1.nextStates();
//                mainPackage.CodeTracker.markExecuted("PracticalTwo", "41");
//                System.out.println("3");
//                mainPackage.CodeTracker.markExecuted("PracticalTwo", "42");
//                break;
//            case 2:
//                mainPackage.CodeTracker.markExecuted("PracticalTwo", "45");
//                System.out.println("2");
//                mainPackage.CodeTracker.markExecuted("PracticalTwo", "46");
//                break;
//        }
        int lastElem = path.size() - 1;
        List<String> nextStates = new ArrayList<String>();
        List<List<String>> extendPath = new ArrayList<List<String>>();
        nextStates.addAll(p1.nextstates(path.get(lastElem)));
        for (String s : nextStates) {
            List<String> newPath = new ArrayList<String>(path);
            mainPackage.CodeTracker.markExecuted("PracticalTwo", "57");
            newPath.add(s);
            mainPackage.CodeTracker.markExecuted("PracticalTwo", "58");
            extendPath.add(newPath);
            mainPackage.CodeTracker.markExecuted("PracticalTwo", "59");
        }
        if (2 == 4) {
            System.out.println("hello");
            mainPackage.CodeTracker.markExecuted("PracticalTwo", "63");
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
            currentPath = Agenda.poll();
            mainPackage.CodeTracker.markExecuted("PracticalTwo", "77");
            if (goalString.equals(currentPath.get(currentPath.size() - 1))) {
                System.out.println("lemgth of current path: " + currentPath.size());
                mainPackage.CodeTracker.markExecuted("PracticalTwo", "79");
                System.out.println("length of the agenda: " + Agenda.size());
                mainPackage.CodeTracker.markExecuted("PracticalTwo", "80");
                System.out.println("number of times extendPath is called: " + countExtendPathCalls);
                mainPackage.CodeTracker.markExecuted("PracticalTwo", "81");
                mainPackage.CodeTracker.markExecuted("PracticalTwo", "82");
                return currentPath;
               
            } else {
                countExtendPathCalls++;
                mainPackage.CodeTracker.markExecuted("PracticalTwo", "84");
                Agenda.addAll(extendPath(currentPath));
                mainPackage.CodeTracker.markExecuted("PracticalTwo", "85");
            }
            mainPackage.CodeTracker.markExecuted("PracticalTwo", "78");
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
            currentPath = Agenda.pop();
            mainPackage.CodeTracker.markExecuted("PracticalTwo", "100");
            if (goalString.equals(currentPath.get(currentPath.size() - 1))) {
                System.out.println("lemgth of current path: " + currentPath.size());
                mainPackage.CodeTracker.markExecuted("PracticalTwo", "102");
                System.out.println("length of the agenda: " + Agenda.size());
                mainPackage.CodeTracker.markExecuted("PracticalTwo", "103");
                System.out.println("number of times extendPath is called: " + countExtendPathCalls);
                mainPackage.CodeTracker.markExecuted("PracticalTwo", "104");
                mainPackage.CodeTracker.markExecuted("PracticalTwo", "105");
                return currentPath;
                
            } else {
                if (limit == currentPath.size()) {
                } else {
                    for (int i = 0; i < extendPath(currentPath).size(); i++) {
                        List<String> newPath = new ArrayList<String>();
                        mainPackage.CodeTracker.markExecuted("PracticalTwo", "111");
                        newPath.addAll(extendPath(currentPath).get(i));
                        mainPackage.CodeTracker.markExecuted("PracticalTwo", "112");
                        Agenda.push(newPath);
                        mainPackage.CodeTracker.markExecuted("PracticalTwo", "113");
                        countExtendPathCalls++;
                        mainPackage.CodeTracker.markExecuted("PracticalTwo", "114");
                    }
                    mainPackage.CodeTracker.markExecuted("PracticalTwo", "110");
                }
                mainPackage.CodeTracker.markExecuted("PracticalTwo", "107");
            }
            mainPackage.CodeTracker.markExecuted("PracticalTwo", "101");
        }
        return currentPath;
    }

    public List<String> iterativeDeepening(String goalString) {
        List<String> bestPath = new ArrayList<String>();
        for (int i = 2; i <= 50; i++) {
            int lastElem = depthLimitedDFS("MUIUUIU", i).size() - 1;
            mainPackage.CodeTracker.markExecuted("PracticalTwo", "125");
            bestPath = depthLimitedDFS("MUIUUIU", i);
            mainPackage.CodeTracker.markExecuted("PracticalTwo", "126");
            if (bestPath.get(lastElem).equals(goalString)) {
            	 mainPackage.CodeTracker.markExecuted("PracticalTwo", "128");
                return bestPath;
               
            }
            mainPackage.CodeTracker.markExecuted("PracticalTwo", "127");
        }
        return null;
    }
}
