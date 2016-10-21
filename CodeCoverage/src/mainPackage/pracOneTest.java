package mainPackage;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class pracOneTest {

	@Before
	public void init(){
		System.out.println("------ Lines Executed ------ ");
		CodeTracker.start();
	}
	
	
	@Test
	public void test(){
//		PracticalOne p1 = new PracticalOne();
//		List<String> temp = new ArrayList<String>();
//		
//		temp.add("MIIIIUIIIIUIIIIUIIIIU");
//		temp.add("MUIUIIIIU");
//		temp.add("MIUUIIIIU");
//		temp.add("MIIIIUUIU");
//		temp.add("MIIIIUIUU");
//		System.out.println(temp);
//		assertFalse(p1.nextstates("MIIIIUIIIIU") == temp);
		
//		p1.nextstates("MIII");
		//System.out.println(CodeTracker.getCoverageRecord());
		
//		
		PracticalTwo p2 = new PracticalTwo();
//        // System.out.println(p2.extendPath(path));
//        // System.out.println(p2.breadthFirstSearch("MUIUUIU"));
//        // System.out.println(p2.depthLimitedDFS("MUIUUIU",5));
        p2.depthLimitedDFS("MII",10);
        CodeTracker.getStatistics();
//		System.out.println(CodeTracker.getCoverageRecord());
	}

}
