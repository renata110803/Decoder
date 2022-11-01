
public class VigenereCrypt implements Cryptable {
	private String[] table;
	private String keyword = "tacoma";

	public VigenereCrypt() {
		table = new String[26];
		int val = 0;
		int start = 0;
		for (int row = 0; row < table.length; row++) {
			String toBuild = "";
			for (int col = 0; col < table.length; col++) {
				toBuild += (char) (val % 26 + 97);
				val++;
			}
			table[row] = toBuild;
			start++;
			val = start;
		}
	}

	public String encrypt(String plain) {
		String key = buildKey(plain);
		String toReturn = "";
		for (int i = 0; i < plain.length(); i++) {
			int row = key.charAt(i) - 97;
			int col = plain.charAt(i) - 97;
			toReturn += table[row].substring(col, col + 1);
		}
		return toReturn;
	}

	public String decrypt(String cipher) {
		String key = buildKey(cipher);
		String toReturn = "";
		for (int i = 0; i < cipher.length(); i++) {
			int row = key.charAt(i) - 97;
			int col = table[row].indexOf(cipher.charAt(i));
			toReturn += (char) (col + 97);
		}
		return toReturn;
	}

	private String buildKey(String from) {
		String key = "";
		int loc = 0;
		for (int i = 0; i < from.length(); i++) {
			key += keyword.substring(loc, loc + 1);
			loc = (loc + 1) % keyword.length();
		}
		return key;
	}
}
