package io.github.cs1302uga;

import static java.nio.charset.StandardCharsets.UTF_8;

import org.apache.velocity.tools.config.DefaultKey;
import org.apache.velocity.tools.generic.SafeConfig;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * An Apache Velocity tool that provides utility methods to manipulate HTML code using
 * <a href="http://jsoup.org/">jsoup</a> HTML5 parser.
 *
 * @author Michael E. Cotterell
 */
@DefaultKey("soup")
public class JsoupTool extends SafeConfig {

    /**
     * Construct a {@code JsoupTool} object.
     */
    public JsoupTool() {
        System.out.println("READT");
    }

    /**
     * Parse the supplied HTML {@code content} string using jsoup.
     *
     * @param content HTML content string
     * @return An {@link org.jsoup.nodes.Element} that wraps the supplied {@code content}.
     *
     */
    public Element parse(String content) {
        Document doc = Jsoup.parseBodyFragment(content);
        return doc.body();
    } // parse

} // JsoupTool
