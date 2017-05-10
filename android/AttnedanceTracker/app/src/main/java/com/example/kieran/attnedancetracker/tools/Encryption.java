package com.example.kieran.attnedancetracker.tools;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.security.SecureRandom;

/**
 * Created by David on 12/11/2016.
 *
 * Implementation for using Dual key encryption
 */
public class Encryption {
    private final String keyAlgo = "RSA";
    private final String dataAlgo = "AES";
    private final int size = 2048;
    private Key key_priv, session_key;
    private Key key_pub;
    private SecureRandom random = new SecureRandom();

    /**
     * @return Public key
     */
    public Key getPublicKey() {
        return key_pub;
    }

    /**
     * Set public key used to encrypt session key
     * @param key_pub
     */
    public void setPublicKey(Key key_pub) {
        this.key_pub = key_pub;
    }
    public void setPublicKey(byte[] key_pub)
    {
        try {
            KeyFactory kf = KeyFactory.getInstance("RSA");
            this.key_pub = kf.generatePublic(new X509EncodedKeySpec(key_pub));
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Encryption()
    {
    }


    /**
     * Generates session key used to encrypt data
     * @return
     */

    ///
    ///
    ///
    /*
    public Key generateSessionKey() {
        byte[] bytes = new byte[16];
        try {
            random.getInstanceStrong().nextBytes(bytes);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        session_key = new SecretKeySpec(bytes,dataAlgo);
        return session_key;
    }
    */
    ///
    ///
    ///

    public void setSessionKey(Key key)
    {
        session_key = key;
    }

    /**
     * Encrypts a byte stream of data
     * @param data data to be encrypted
     * @return encrypted byte stream
     */
    public byte[] encrypt(byte[] data)
    {
        byte[] crypt = null;
        try {
            Cipher cipher = Cipher.getInstance(dataAlgo);
            cipher.init(Cipher.ENCRYPT_MODE, session_key);
            crypt= cipher.doFinal(data);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return crypt;
    }

    /**
     * Decrypts byte stream using session key
     * @param k session key
     * @param crypt encrypted data
     * @return decrypted byte stream
     */
    public byte[] decrypt(Key k, byte[] crypt)
    {
        byte[] data = null;
        try {
            Cipher cipher = Cipher.getInstance(dataAlgo);
            cipher.init(cipher.DECRYPT_MODE,k);
            data = cipher.doFinal(crypt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * generate a public & private key pair for Encryption to use
     */
    public void generateKeys()
    {
        try {
            KeyPairGenerator keygen = KeyPairGenerator.getInstance(keyAlgo);
            keygen.initialize(size);
            KeyPair pair = keygen.generateKeyPair();
            key_priv = pair.getPrivate();
            key_pub = pair.getPublic();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * Encrypts a session key using public key
     * @param k session key
     * @return encrypted key
     */
    public byte[] encryptKey(Key k)
    {
        byte[] crypt = null;
        try {
            Cipher cipher = Cipher.getInstance(keyAlgo);
            cipher.init(Cipher.ENCRYPT_MODE, key_pub);
            crypt = cipher.doFinal(k.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return crypt;
    }

    /**
     * decrypts a session key using private key
     * @param crypt encrypted session key
     * @return decrypted session key in byte stream
     */
    public Key decryptKey(byte[] crypt)
    {
        byte[] data= null;
        SecretKeySpec s = null;
        try {
            Cipher cipher = Cipher.getInstance(keyAlgo);
            cipher.init(Cipher.DECRYPT_MODE, key_priv);
            data = cipher.doFinal(crypt);
            s = new SecretKeySpec(data, dataAlgo);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return s;
    }
}