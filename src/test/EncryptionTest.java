package test;

import java.io.IOException;
import java.security.Key;
import java.util.HashMap;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import tools.*;

public class EncryptionTest {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		String s = "Test Encryption for a longer string length to see what the craic is";
		HashMap<String,String> map = new HashMap<String,String>();
		//receiver and sender have their own Encryption instances
		Encryption sender = new Encryption();
		Encryption receiver = new Encryption();
		//receiver generates key pairs
		receiver.generateKeys();
		//sender is transmitted the receiver public key
		//This can be done the exact same the other direction
		sender.setPublicKey(receiver.getPublicKey());
		//Sender generates a session key, used to encrypt data
		Key k = sender.generateSessionKey();
		//Sender encrypts key and transmits to receiver
		byte[] key_bytes = sender.encryptKey(k);
		//Sender encrypts data
		byte[] data;
		data = sender.encrypt(ObjectSerializer.serialize(s));
		System.err.println("Encrypted value : " + Base64.encode(data));
		
		//Receiver decrypts session key using private key
		Key key = receiver.decryptKey(key_bytes);
		
		//Receiver decrypts data and uses
		System.out.print("Unencrypted value : "+(String)ObjectSerializer.deserialize(receiver.decrypt(key, data)));
	}

}
