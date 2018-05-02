import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinksTestingUtils {

    public static boolean verifyUrl(String url) {
        String urlRegex = "^(http|https)://[-a-zA-Z0-9+&@#/%?=~_|,!:.;]*[-a-zA-Z0-9+@#/%=&_|]";
        Pattern pattern = Pattern.compile(urlRegex);
        Matcher m = pattern.matcher(url);
        return m.matches();
    }

    public static boolean validateUrl(String url) throws IOException{
        URL myURL = new URL(url);
        HttpURLConnection myConnection = (HttpURLConnection) myURL.openConnection();
        return myConnection.getResponseCode()==HttpURLConnection.HTTP_OK;
    }

}
