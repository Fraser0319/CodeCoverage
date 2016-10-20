package mainPackage;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class pracOneTest {

	@Test
	public void test() throws ClassNotFoundException, IOException {
		PracticalOne p1 = new PracticalOne();
		List<String> temp = new ArrayList<String>();
		
		temp.add("MIIIIUIIIIUIIIIUIIIIU");
		temp.add("MUIUIIIIU");
		temp.add("MIUUIIIIU");
		temp.add("MIIIIUUIU");
		temp.add("MIIIIUIUU");
		System.out.println(temp);
		assertFalse(p1.nextstates("MIIIIUIIIIU") == temp);
	}

}
