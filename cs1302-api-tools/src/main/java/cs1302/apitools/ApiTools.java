package cs1302.apitools;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.nio.charset.Charset;

import com.google.gson.JsonElement;

/**
 * A collection of static methods and constants for working with APIs.
 */
public final class ApiTools {

    /**
     * The UTF-8 charset specified by RFC 2279. This constant is provided here
     * to make the {@link java.net.URLEncoder#encode(String, Charset)} method
     * easier to use.
     * @see <a href="http://www.ietf.org/rfc/rfc2279.txt">RFC 2279</a>
     * @see <a href="http://www.unicode.org/unicode/standard/standard.html">Unicode Standard</a>
     */
    public static final Charset UTF8 = Charset.forName("UTF-8");

    /**
     * Hidden default constructor.
     * @deprecated The private default constructor for {@link ApiTools}
     *     should not be used. It is the intent of the authors that the
     *     {@link ApiTools} class contain only static members. This
     *     deprecation note is left so that maintainers of the {@link ApiTools}
     *     class are warned if they attempt to use private default constructor.
     **/
    @Deprecated
    private ApiTools() {}

} // ApiTools
