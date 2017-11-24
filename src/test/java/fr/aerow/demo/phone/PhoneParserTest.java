package fr.aerow.demo.phone;

import java.util.Arrays;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class PhoneParserTest 
    extends TestCase
{
	private PhoneParser phoneParser;
	private List<PhoneNumber> parsedPhoneList;
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public PhoneParserTest( String testName )
    {
    	super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( PhoneParserTest.class );
    }
    
    @Override
	protected void setUp() throws Exception {
		super.setUp();
		// MyPhoneParser
    	phoneParser = MyPhoneParser.getInstance();
    	parsedPhoneList = Arrays.asList(
    			phoneParser.parse("Phone: 1 A:=[987] P:=\"654-3210\", X:=1234"), 
    			phoneParser.parse("A:=[987] P:=\"654-3210\", X:=1234"),
    			phoneParser.parse("654-3210"),
    			phoneParser.parse("6543-210"));
	}
     
    @org.junit.Test
    public void testCanContainAnAreaCode () {
    	// When the first set of digits is preceded by a 1
    	PhoneNumber p = phoneParser.parse("Phone: 1 A:=[987] P:=\"654-3210\", X:=1234");
    	// The area code should be set
    	assertEquals(true, "987".equals(p.getAeraCode()));
    			
    	// When the first two sets of digits are three figures long
    	p = phoneParser.parse("A:=[987] P:=\"654-3210\", X:=1234");
    	// The area code should be set
    	assertEquals(true, "987".equals(p.getAeraCode()));
    	
    	// When there are no Area code
    	p = phoneParser.parse("654-3210");
    	// Area code should be null
    	assertEquals(null, p.getAeraCode());
    }
    
    @org.junit.Test
    public void testShouldContainALocalSetOfDigits () {
    	parsedPhoneList.forEach(phone -> {
    		if(phone != null) {
    			// The local set of digits should never be null (mandatory field)
            	assertNotNull(phone.getLocal3());
            	// The local set of digits should be three figures long
            	assertEquals(3, phone.getLocal3().length());
    		}
    	});
    }
    
    @org.junit.Test
    public void testShouldContainALastSetOfDigits () {
    	parsedPhoneList.forEach(phone -> {
    		if(phone != null) {
    			// The local set of digits should never be null (mandatory field)
            	assertNotNull(phone.getLast4());
            	// The local set of digits should be three figures long
            	assertEquals(4, phone.getLast4().length());
    		}
    	});
    }
    
    
    
}