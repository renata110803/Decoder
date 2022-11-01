
/* Renata Gabdrakhmanova
 * pd 2
 * 3/14/21
 */
import java.util.*;

public class Client {
	public static void main(String[] args) {

		Scanner keyboard = new Scanner(System.in);
		System.out.println("What type of encryption algorithm do you want to use? ");
		System.out.println("1. PigLatin  2.UnstoppableCrypt 3.VigenereCrypt");
		int type = keyboard.nextInt();

		if (type == 1) {
			Cryptable one = new PigLatin();
			convert(one);
		} else if (type == 2) {
			Cryptable one = new UnstoppableCrypt();
			convert(one);
		} else if (type == 3) {
			Cryptable one = new VigenereCrypt();
			convert(one);
		}
	}

	public static void convert(Cryptable elem) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter an English phrase for translaton: ");
		String phrase = keyboard.nextLine();

		System.out.println(elem.encrypt(phrase));

	}
}
