package hilburnlib.java.web;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class HttpPost extends HttpGet
{
    private HashMap<String, String> postData;
    
    public HttpPost(String url)
    {
        super(url);
        postData = new HashMap<>();
    }

    public void put(String key, String value)
    {
        try
        {
            this.postData.put(URLEncoder.encode(key, ENCODING), URLEncoder.encode(value, ENCODING).replaceAll("%00", ""));
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
    }

    public String getPost()
    {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : postData.entrySet())
            builder.append(entry.getKey()).append('=').append(entry.getValue()).append('&');
        if (postData.size() > 0)
            builder.deleteCharAt(builder.length() - 1);
        return new String(builder);
    }

    @Override
    public String getContents()
    {
        try
        {
            URL url = new URL(this.url);

            URLConnection connection = url.openConnection();

            connection.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            wr.write(this.getPost());
            wr.flush();
            wr.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null)
            {
                if (builder.length() > 0)
                    builder.append('\n');
                builder.append(line);
            }
            reader.close();
            return new String(builder);
        } catch (MalformedURLException e)
        {
            throw new IllegalArgumentException("Malformed link: " + e);
        } catch (IOException e)
        {
            throw new RuntimeException("Failed to fetch contents from link: " + e);
        }
    }
}
