package tools;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import com.mysql.fabric.xmlrpc.base.Param;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import model.Student;
/**
 * Class for sending Param's to a servlet and returning an Object
 * @author David
 *
 */
public class ServletInterfaceController {
	String hostURL;
	final String charset = "UTF-8";
	private Encryption crypt;
	private Key session_key;
	public ServletInterfaceController(String host)
	{
		hostURL = host;
		crypt = new Encryption();
		CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
		//requestSessionKey();
	}
	
	public HttpURLConnection connect(String serv)
	{
		HttpURLConnection servletConnection = null;
		try {
			servletConnection = (HttpURLConnection) new URL(hostURL+serv).openConnection();
			servletConnection.setRequestProperty("Accept-Charset", charset);
			servletConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
			servletConnection.setRequestMethod("POST");
			servletConnection.setDoOutput(true);
			servletConnection.setDoInput(true);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return servletConnection;
	}
	@Deprecated
	private void requestSessionKey()
	{
		HashMap<String, String> map = new HashMap<String, String>();
		crypt.generateKeys();
		map.put("public_key", Base64.encode(crypt.getPublicKey().getEncoded()));
		try {
			String keyData = (String) sendPostRequest("getSessionKey",map);
			Key key = crypt.decryptKey(Base64.decode(keyData));
			session_key = key;
			crypt.setSessionKey(session_key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Object getResult(HttpURLConnection conn) throws IOException, ClassNotFoundException
	{
		ObjectInputStream obj = new ObjectInputStream(conn.getInputStream());
		Object result = obj.readObject();
		obj.close();
		return result;
	}
	
	public Object sendPostRequest(String servlet, HashMap<String, String> params) throws Exception
	{
		/**
		 * Will be updating this to use map
		 */
		Set<String> keys = params.keySet();
		String query = "";
		int i = 0;
		for(String s: keys)
		{
			try {
				query += s + "=" + URLEncoder.encode(params.get(s), charset);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
			if(i<keys.size())
			{
				query+="&";
			}
		}
		HttpURLConnection connection;
		Object result = null;
		try {
			connection = connect(servlet);
			OutputStream stream;
			stream = connection.getOutputStream();
			stream.write(query.getBytes(charset));
			stream.flush();
			//connection.get
			result = getResult(connection);
			stream.close();
			connection.disconnect();
		}  catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (result==null)
		{
			throw new Exception("No object returned by Servlet");
		}
		return result;
		
	}
}
