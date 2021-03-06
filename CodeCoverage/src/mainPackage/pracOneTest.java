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
	public void init() {
		System.out.println("------ Lines Executed ------ ");
		CodeTracker.start();
	}

	@Test
	public void test1() {
		PracticalOne p1 = new PracticalOne();

		p1.nextstates("MIIIUU");
		CodeTracker.getStatistics();

	}

	@Test
	public void test2() {
		PracticalTwo p2 = new PracticalTwo();
		p2.iterativeDeepening("MUIUUIU");
		CodeTracker.getStatistics();
	}

}
