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
		

		
		System.out.println(p1.nextstates("MIIIIUIIIIU"));
	}

	public List<String> nextstates(String s) {

		String input;
		input = s;
		int x = 10;

		do {
//			System.out.print("value of x : " + x);
			x++;
//			System.out.print("\n");
		} while (x < 20);
		
		
		
		// rule 1 - if it ends in an I
		if (s.charAt(s.length() - 1) == 'I') {

			nextStates.add(input += 'U');
			input = s;
		}
		
//		try{
////			System.out.println("hi");
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		

	
		// rule 2 - if it starts with M
		if (s.charAt(0) == 'M') {

			String doubleString = input.substring(1, input.length());

			nextStates.add(input += doubleString);
			input = s;
		}

		// rule 3 if the string contains III replace with U
		if (s.contains("III")) {

			String subString = "";
			String checkedString = "";

			for (int i = 1; i < input.length(); i++) {

				checkedString = input.substring(0, i);
				subString = input.substring(i);

				if (subString.contains("III")) {
					String updatedSequence = (checkedString + subString.replaceFirst("III", "U"));
					if (!nextStates.contains(updatedSequence))
						nextStates.add(checkedString + subString.replaceFirst("III", "U"));
				}
			}
			input = s;
		}

		// rule4 - if the string contains UU replace it with ""
		if (s.contains("UU")) {

			String subString = "";
			String checkedString = "";

			for (int i = 1; i < input.length(); i++) {

				checkedString = input.substring(0, i);
				subString = input.substring(i);
				
				if (subString.contains("UU")) {
					String updatedSequence = checkedString + subString.replaceFirst("UU", "");
					if (!nextStates.contains(updatedSequence))
						nextStates.add(updatedSequence);
				}
			}
		}

		return nextStates;
	}

}
