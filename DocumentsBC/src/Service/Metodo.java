package Service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Metodo 
{
	public String CheckHash(String cadena) {

		String hash2 = "";

		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(cadena.getBytes("UTF-8"));
			StringBuilder hexString = new StringBuilder();

			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}

			hash2 = hexString.toString();

		} catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
			throw new RuntimeException(ex);
		}

		return hash2;
	}

	public String Hash(String cadena) {
		String hash;
		char arrayhash[];
		int contador = 0;
		String aleatorio;

		do {
			aleatorio = Integer.toString(contador);
			hash = CheckHash(cadena + aleatorio);
			arrayhash = hash.toCharArray();
			contador++;

		} while (arrayhash[0] != '0');

		return hash;
	}


}
