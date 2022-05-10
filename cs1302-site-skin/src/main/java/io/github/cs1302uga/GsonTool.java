package io.github.cs1302uga;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.velocity.tools.ToolContext;
import org.apache.velocity.tools.config.DefaultKey;
import org.apache.velocity.tools.generic.SafeConfig;
import org.apache.velocity.tools.generic.ValueParser;

import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;

/**
 * An Apache Velocity tool that provides utility methods to manipulate JSON code using
 * the <a href="https://github.com/google/gson">gson</a> JSON library.
 *
 * @author Michael E. Cotterell
 */
@DefaultKey("gson")
public class GsonTool extends SafeConfig {

    private Charset outputEncoding = UTF_8;

    /**
     * Construct a {@code GsonTool} object.
     */
    public GsonTool() {}

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configure(ValueParser values) {
        Optional.ofNullable(values.get(ToolContext.CONTEXT_KEY))
            .filter(ToolContext.class::isInstance)
            .map(ToolContext.class::cast)
            .ifPresent(this::initFromContext);
    } // configure

    /**
     * Inform this {@link io.github.cs1302uga.GsonTool} so that it should take the
     * specified {@code velocityContext} into consideration, when applicable.
     *
     * @param velocityContext
     */
    private void initFromContext(ToolContext velocityContext) {
        Optional.ofNullable(velocityContext.get("outputEncoding"))
            .filter(String.class::isInstance)
            .map(String.class::cast)
            .map(Charset::forName)
            .ifPresent(this::setOutputEncoding);
    } // initFromContext

    /**
     * Set the output encoding.
     *
     * @param outputEncoding the desired output encoding
     */
    protected void setOutputEncoding(Charset outputEncoding) {
        this.outputEncoding = outputEncoding;
    } // setOutputEncoding

    /**
     * Parse the supplied JSON {@code content} string using gson.
     *
     * @param content JSON content string
     * @return An {@link com.google.gson.JsonElement} that wraps the supplied {@code content}.
     */
    public JsonElement parse(String content) {
        return JsonParser.parseString(content);
    } // parse

    /**
     * Parse the supplied JSON {@code resouce} using gson.
     *
     * @param resource the resource name
     * @return An {@link com.google.gson.JsonElement} that wraps the JSON in {@code resource}.
     */
    public JsonElement parseResource(String resource) {
        return Optional.ofNullable(getClass().getClassLoader().getResourceAsStream(resource))
            .map(InputStreamReader::new)
            .map(BufferedReader::new)
            .map(JsonParser::parseReader)
            .orElse(JsonNull.INSTANCE);
    } // parseResource

} // GsonTool
