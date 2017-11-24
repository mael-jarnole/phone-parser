package fr.aerow.demo.phone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyPhoneParser implements PhoneParser {

	private static MyPhoneParser instance;
	
	public static MyPhoneParser getInstance() {
		if (instance == null) {
			instance = new MyPhoneParser();
		}
		return instance;
	}
	
	private MyPhoneParser () {
		
	}
	
	/**
	 * TDD: in progress
	 * 1- Write tests that fail
	 * 2- Write code that makes the tests succeed (in progress)
	 * 3- Refactor (Not done yet)
	 */
	public PhoneNumber parse(String input) {
		PhoneNumber phoneNumber = new PhoneNumber();
		try {
			List<String> digits = new ArrayList<String>(Arrays.asList(input.split("\\D+")));
			digits.removeAll(Arrays.asList("", null));
			if (digits.size() > 0) {
				// if there is an area code prefix (figure 1)
				if (Integer.parseInt(digits.get(0)) == 1) {
					phoneNumber.setAeraCode(digits.get(1));
					// In this case the next set of digit should be local3
					phoneNumber.setLocal3(digits.get(2));
					phoneNumber.setLast4(digits.get(3));
				} else {
					// there is no area code prefix
					if (3 == digits.get(0).length() && digits.get(0).length() == digits.get(1).length()) {
						// there is an area code followed by the local 3 first digits
						phoneNumber.setAeraCode(digits.get(0));
						phoneNumber.setLocal3(digits.get(1));
						phoneNumber.setLast4(digits.get(2));
					} else {
						// there is no area code
						phoneNumber.setLocal3(digits.get(0));
						phoneNumber.setLast4(digits.get(1));
					}
				}
			}
		} catch (PhoneFormatException e) {
			System.out.println(e.toString() + "\n\n Input: " + input);
			return null;
		}
		
		return phoneNumber;
	}

}
