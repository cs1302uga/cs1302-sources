package io.github.cs1302uga;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

import org.apache.velocity.tools.config.DefaultKey;
import org.apache.velocity.tools.generic.SafeConfig;

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

    /**
     * Construct a {@code GsonTool} object.
     */
    public GsonTool() {}

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
