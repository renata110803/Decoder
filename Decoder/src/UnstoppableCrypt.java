
public class UnstoppableCrypt implements Cryptable {
	public String encrypt(String text) {
		String toReturn = "";
		for (int i = 0; i < text.length(); i++) {
			toReturn += (char) (text.charAt(i) + 5);
		}
		return toReturn;
	}

	public String decrypt(String text) {
		String toReturn = "";
		for (int i = 0; i < text.length(); i++) {
			toReturn += (char) (text.charAt(i) - 5);
		}
		return toReturn;
	}
}
