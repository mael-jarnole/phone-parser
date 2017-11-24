package fr.aerow.demo.phone;

public class PhoneNumber {
	private String phone;
	private String aeraCode;
	private String local3;
	private String last4;
	private String extension;

	public String getLocal3() {
		return local3;
	}

	public void setLocal3(String local3) throws PhoneFormatException {
		if (local3.length() != 3) {
			throw new PhoneFormatException("Invalid local set of digits");
		}
		this.local3 = local3;
	}

	public String getAeraCode() {
		return aeraCode;
	}

	public void setAeraCode(String aeraCode) throws PhoneFormatException {
		if (aeraCode.length() != 3) {
			throw new PhoneFormatException("Invalid aera code");
		}
		this.aeraCode = aeraCode;
	}
	
	public String getPhone() {
		phone = "";
		if (aeraCode != null) {
			phone += "(" + aeraCode + ") ";
		}
		phone += local3 + "-" + last4;
		if (extension != null) {
			phone += " ext. " + extension;
		}
		return phone;
	}

	public String getLast4() {
		return last4;
	}

	public void setLast4(String last4) throws PhoneFormatException {
		if (last4.length() != 4) {
			throw new PhoneFormatException("Invalid last 4 set of digits");
		}
		this.last4 = last4;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
