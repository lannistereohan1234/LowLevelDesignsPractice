import java.time.LocalDateTime;

public class Solution {

    public static void main(String[] args) {
        Converter converter = new Converter();

        // Shorten a URL
        Request request = new Request("https://www.google.com/search?q=url+shortener", LocalDateTime.now());
        Response response = converter.urlConverter(request);

        System.out.println("Short URL : " + response.getResponseURL());
        System.out.println("Expiry    : " + response.getExpiry());

        // Redirect: resolve short URL back to the original long URL
        String longURL = converter.redirect(response.getResponseURL());
        System.out.println("Redirects to: " + longURL);
    }
}
