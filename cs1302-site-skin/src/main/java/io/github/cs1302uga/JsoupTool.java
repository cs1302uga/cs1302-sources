package io.github.cs1302uga;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.velocity.tools.ToolContext;
import org.apache.velocity.tools.config.DefaultKey;
import org.apache.velocity.tools.generic.SafeConfig;
import org.apache.velocity.tools.generic.ValueParser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * An Apache Velocity tool that provides utility methods to manipulate HTML code using
 * <a href="http://jsoup.org/">jsoup</a> HTML5 parser.
 *
 * @author Michael E. Cotterell
 */
@DefaultKey("soup")
public class JsoupTool extends SafeConfig {

    private Charset outputEncoding = UTF_8;

    /**
     * Construct a {@code JsoupTool} object.
     */
    public JsoupTool() {
        setSafeMode(false);
        System.err.println("constructing JsoupTool");
    } // JsoupTool

    /**
     * Get the output encoding.
     *
     * @return the output encoding.
     */
    public Charset getOutputEncoding() {
        return this.outputEncoding;
    } // getOutputEncoding

    /**
     * Set the output encoding.
     *
     * @param outputEncoding the desired output encoding
     */
    protected void setOutputEncoding(Charset outputEncoding) {
        this.outputEncoding = outputEncoding;
    } // setOutputEncoding

    /**
     * Parse the supplied HTML {@code content} string using jsoup.
     *
     * @param content HTML content string
     * @return An {@link org.jsoup.nodes.Element} that wraps the supplied {@code content}.
     */
    public Element parse(String content) {
        Document doc = Jsoup.parseBodyFragment(content);
        doc.outputSettings().charset(outputEncoding);
        return doc.body();
    } // parse

    /**
     * Select elements in the supplied HTML {@code content} string that match the
     * selector CSS query, with this element as the starting context. Matched
     * elements may include this element, or any of its children.
     *
     * @param content HTML content string
     * @param cssQuery a selector CSS-like query
     * @return The selected elements.
     */
    public Elements select(String content, String cssQuery) {
        Element elem = parse(content);
        return elem.select(cssQuery);
    } // select

} // JsoupTool
