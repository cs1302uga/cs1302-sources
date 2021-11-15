package cs1302.apitools;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.DocumentContext;
import java.io.InputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpResponse.BodySubscriber;
import java.net.http.HttpResponse.BodySubscribers;
import java.nio.charset.Charset;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * A collection of static methods and constants for working with APIs.
 */
public final class ApiTools {

    /**
     * The UTF-8 charset specified by RFC 2279. This constant is provided here
     * to make the {@link java.net.URLEncoder#encode(String, Charset)} method
     * easier to use.
     *
     * @see <a href="http://www.ietf.org/rfc/rfc2279.txt">RFC 2279</a>
     * @see <a href="http://www.unicode.org/unicode/standard/standard.html">Unicode Standard</a>
     */
    public static final Charset UTF8 = Charset.forName("UTF-8");

    /**
     * {@link HttpClient} with default settings as described
     * {@linkplain HttpClient#newHttpClient here}.
     */
    public static final HttpClient DEFAULT_CLIENT = HttpClient.newHttpClient();

    /**
     * {@link BodyHandler BodyHandler} for treating response payloads as strings.
     */
    public static final BodyHandler<String> STR = BodyHandlers.ofString(UTF8);

    /**
     * {@link BodyHandler BodyHandler} for JSON response payloads. Gson's
     * {@link JsonParser#parseString} method is used to create a
     * {@link JsonElement} object representing the JSON root element
     * for a response payload.
     */
    public static final BodyHandler<JsonElement> JSON = (responseInfo) -> {
        return BodySubscribers.mapping(BodySubscribers.ofString(UTF8), JsonParser::parseString);
    };

    /**
     * {@link BodyHandler BodyHandler} for JSON response payloads that will be
     * read using the Jayway JsonPath library. JsonPath's
     * {@link JsonPath#parse} method is used to create a
     * {@link DocumentContext} object representing the JSON document
     * for a response payload.
     */
    public static final BodyHandler<DocumentContext> JSON_PATH = (responseInfo) -> {
        return BodySubscribers.mapping(BodySubscribers.ofInputStream(), JsonPath::parse);
    };

    /**
     * {@link BodyHandler BodyHandler} for DOM-compatible XML response payloads.
     * The {@link DocumentBuilder#parse} method is used to create a
     * {@code Document} object representing the Document Object Model
     * (DOM) for a response payload.
     *
     * @see <a href="https://dom.spec.whatwg.org/">DOM Standard</a>
     */
    public static final BodyHandler<Document> DOM = (responseInfo) -> {
        Function<InputStream, Document> parser = is -> {
            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                return builder.parse(is);
            } catch(SAXException | IOException | ParserConfigurationException e) {
                throw new RuntimeException(e);
            } // try
        };
        return BodySubscribers.mapping(BodySubscribers.ofInputStream(), parser);
    };

    /**
     * Hidden default constructor.
     *
     * @deprecated The private default constructor for {@link ApiTools}
     *     should not be used. It is the intent of the authors that the
     *     {@link ApiTools} class contain only static members. This
     *     deprecation note is left so that maintainers of the {@link ApiTools}
     *     class are warned if they attempt to use private default constructor.
     **/
    @Deprecated
    private ApiTools() {}

    /**
     * Start the process of fetching an HTTP response based.
     *
     * @param <T> The response body type.
     * @param client The HTTP client to use when the fetch is performed.
     * @param request The request object that describes what to fetch.
     * @param bodyHandler The response body handler to use.
     * @return a {@link CompletableFuture} supporting dependent
     *     handlers that execute once the response is available.
     */
    public <T> CompletableFuture<HttpResponse<T>> fetch(
        HttpClient client, HttpRequest request, BodyHandler<T> bodyHandler) {
        return client.sendAsync(request, bodyHandler);
    } // fetch

    /**
     * Start the process of fetching an HTTP response based on an HTTP request
     * using the {@linkplain #DEFAULT_CLIENT default client}. This method
     * returns a {@link CompletableFuture} supporting dependent handlers
     * that execute once the response is available. The type of the response
     * payload / body is determined using the provided {@code bodyHandler}.
     *
     * @see java.net.http.HttpResponse.BodyHandlers
     *
     * @param <T> The response body type.
     * @param request The request object that describes what to fetch.
     * @param bodyHandler The response body handler to use.
     *
     * @return a {@link CompletableFuture} supporting dependent
     *     handlers that execute once the response is available.
     */
    public <T> CompletableFuture<HttpResponse<T>> fetch(
        HttpRequest request, BodyHandler<T> bodyHandler) {
        return fetch(DEFAULT_CLIENT, request, bodyHandler);
    } // fetch

    /**
     * Start the process of fetching an HTTP response based on an HTTP GET
     * request using the {@linkplain #DEFAULT_CLIENT default client}. This
     * method returns a {@link CompletableFuture} supporting dependent handlers
     * that execute once the response is available. The type of the response
     * payload / body is determined using the provided {@code bodyHandler}.
     *
     * @see java.net.http.HttpResponse.BodyHandlers
     *
     * @param <T> The response body type.
     * @param url The URL that describes what to fetch.
     * @param bodyHandler The response body handler to use.
     *
     * @return a {@link CompletableFuture} supporting dependent
     *     handlers that execute once the response is available.
     */
    public <T> CompletableFuture<HttpResponse<T>> fetch(
        String url, BodyHandler<T> bodyHandler) {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build();
        return fetch(DEFAULT_CLIENT, request, bodyHandler);
    } // fetch

} // ApiTools
