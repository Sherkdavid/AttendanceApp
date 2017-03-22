package tools;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.servlet.ServletException;

import com.mysql.fabric.xmlrpc.base.Param;

import users.Student;
/**
 * Class for sending Param's to a servlet and returning an Object
 * @author David
 *
 */
public class GetServletObject {
	String hostURL;
	final String charset = "UTF-8";
	
	public GetServletObject(String host)
	{
		hostURL = host;
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
	
	public Object getResult(HttpURLConnection conn) throws IOException, ClassNotFoundException
	{
		ObjectInputStream obj = new ObjectInputStream(conn.getInputStream());
		Object result = obj.readObject();
		obj.close();
		return result;
	}
	
	public Object sendPostRequest(String servlet, HashMap<String, String> params) throws ServletException
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
			throw new ServletException("No object returned by Servlet");
		}
		return result;
		
	}
}
