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
	
	public PhoneNumber parse(String input) {
		PhoneNumber phoneNumber = new PhoneNumber();
		try {
			List<String> digits = new ArrayList<String>(Arrays.asList(input.split("\\D+")));
			digits.removeAll(Arrays.asList("", null));
			if (digits.size() > 0) {
				setPhoneNumber(phoneNumber, digits, itContainsAnAreaCode(digits), itContainsAnAreaCodePrefix(digits));
			}
		} catch (PhoneFormatException e) {
			System.out.println(e.toString() + "\n\n Input: " + input);
			return null;
		}
		
		return phoneNumber;
	}
	
	private boolean itContainsAnAreaCodePrefix (List<String> digits) {
		return Integer.parseInt(digits.get(0)) == 1;
	}
	
	private boolean itContainsAnAreaCode (List<String> digits) {
		return (PhoneNumber.AERA_CODE_LENGTH == digits.get(0).length() && digits.get(0).length() == digits.get(1).length()) 
				|| (itContainsAnAreaCodePrefix (digits) && PhoneNumber.AERA_CODE_LENGTH == digits.get(1).length());
	}
	
	private void setPhoneNumber(
			PhoneNumber phoneNumber,List<String> digits, boolean hasAreaCode, boolean hasAreaCodePrefix) 
					throws PhoneFormatException {
		int index = 0;
		index += (hasAreaCodePrefix ? 1 : 0);
		if (hasAreaCodePrefix || hasAreaCode) {
			phoneNumber.setAeraCode(digits.get(index)); 
			index++;
		}
		phoneNumber.setLocal3(digits.get(index));
		phoneNumber.setLast4(digits.get(index+1));
	}

}
