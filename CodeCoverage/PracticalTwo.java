package Practical2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import javax.print.attribute.Size2DSyntax;

import Practical1.PracticalOne;

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
			List<String> newPath = new ArrayList<String>(path);
			newPath.add(s);
			extendPath.add(newPath);
		}

		if (2 == 4) {
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
			currentPath = Agenda.poll();
			if (goalString.equals(currentPath.get(currentPath.size() - 1))) {
				System.out.println("lemgth of current path: " + currentPath.size());
				System.out.println("length of the agenda: " + Agenda.size());
				System.out.println("number of times extendPath is called: " + countExtendPathCalls);
				return currentPath;
			} else {
				countExtendPathCalls++;
				Agenda.addAll(extendPath(currentPath));
			}
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
			if (goalString.equals(currentPath.get(currentPath.size() - 1))) {
				System.out.println("lemgth of current path: " + currentPath.size());
				System.out.println("length of the agenda: " + Agenda.size());
				System.out.println("number of times extendPath is called: " + countExtendPathCalls);
				return currentPath;
			} else {
				if (limit == currentPath.size()) {

				} else {
					for (int i = 0; i < extendPath(currentPath).size(); i++) {
						List<String> newPath = new ArrayList<String>();
						newPath.addAll(extendPath(currentPath).get(i));
						Agenda.push(newPath);
						countExtendPathCalls++;
					}
				}
			}
		}
		return currentPath;
	}

	public List<String> iterativeDeepening(String goalString) {
		List<String> bestPath = new ArrayList<String>();
		for (int i = 2; i <= 50; i++) {
			int lastElem = depthLimitedDFS("MUIUUIU", i).size() - 1;
			bestPath = depthLimitedDFS("MUIUUIU", i);
			if (bestPath.get(lastElem).equals(goalString)) {
				return bestPath;
			}
		}
		return null;
	}

}
