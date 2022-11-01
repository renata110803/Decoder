/* Renata Gabdrakhmanova
 * pd 2
 * 3/21/21
 * Decoder-asks the user for the type of encryption and based on the choice will encrypt and decrypt the word or phrase
 *outputs GUI to take in the word and output it there. Can also reset both fields.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class Decoder extends JFrame implements ActionListener {
	private JTextField plain;
	private JTextField cipher;

	private JButton encrypt;
	private JButton decrypt;
	private JButton reset;

	private Cryptable type;

	public Decoder(Cryptable elem) {
		type = elem;

		setSize(340, 140);
		setTitle("Cryptography");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// setting boxes's sizes, names and naming buttons
		JLabel Plaintext = new JLabel("Plaintext:");
		JLabel ciphertext = new JLabel("Ciphertext:");
		plain = new JTextField(20);
		cipher = new JTextField(20);
		
		encrypt = new JButton("Encrypt");
		decrypt = new JButton("Decrypt");
		reset = new JButton("Reset");

		// event handling for buttons and 2 boxes
		plain.addActionListener(this);
		cipher.addActionListener(this);
		encrypt.addActionListener(this);
		decrypt.addActionListener(this);
		reset.addActionListener(this);

		// sending name of the file of the picture to PicPanel to open it
		PicPanel Panel = new PicPanel("pig.jpg");

		JLabel display = new JLabel();

		// add widgets to the panel
		Panel.setLayout(new FlowLayout());
		Panel.add(Plaintext);
		Panel.add(plain);
		Panel.add(ciphertext);
		Panel.add(cipher);
		Panel.add(encrypt);
		Panel.add(decrypt);
		Panel.add(reset);
		Panel.add(display);

		add(Panel);
		setVisible(true);

	}

	public void actionPerformed(ActionEvent ae) {

		// if Encrypt button was pressed then encrypt the word from plaintext
		// and display it in the ciphertext box
		// wont encrypt if there is nothing in plaintext
		if (ae.getActionCommand().equals("Encrypt")) {
			String phrase = plain.getText();
			if (!phrase.equals("")) {
				cipher.setText(type.encrypt(phrase));
			}
		}

		// if Decrypt button was pressed then decrypt the word from ciphertext
		// and display it in the plaintext box
		// wont encrypt if there is nothing in ciphertext
		if (ae.getActionCommand().equals("Decrypt")) {
			String phrase = cipher.getText();
			if (!phrase.equals("")) {
				plain.setText(type.decrypt(phrase));
			}
		}

		// resets all buttons
		if (ae.getActionCommand().equals("Reset")) {
			plain.setText("");
			cipher.setText("");
		}
	}

	public class PicPanel extends JPanel {
		private BufferedImage image;

		public PicPanel(String fname) {

			try {
				image = ImageIO.read(new File(fname));
			} catch (IOException ioe) {
				System.out.println("Could not read in the pic");
				System.exit(0);
			}
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image, 0, 0, this);
		}
	}

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		
		//based on the option the word will be crypted or decrypted in specific way
		System.out.println("What type of encryption algorithm do you want to use? ");
		System.out.println("1. PigLatin  2.UnstoppableCrypt 3.VigenereCrypt");
		int type = keyboard.nextInt();

		if (type == 1) {
			Cryptable pig = new PigLatin();
			Decoder decode = new Decoder(pig);
		} else if (type == 2) {
			 Cryptable unstoppable = new UnstoppableCrypt();
			 Decoder decode = new Decoder(unstoppable);
		} else if (type == 3) {
			Cryptable vigenere = new VigenereCrypt();
			Decoder decode = new Decoder(vigenere);
		}

	}

}
