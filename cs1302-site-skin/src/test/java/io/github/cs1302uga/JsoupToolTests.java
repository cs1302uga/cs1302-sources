package io.github.cs1302uga;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.charset.StandardCharsets.UTF_16;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.ToolContext;
import org.apache.velocity.tools.generic.ValueParser;

import org.junit.jupiter.api.Test;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Provides tests for the {@link io.github.cs1302uga.Jsouptool} class.
 */
class JsoupToolTests {

    private static final String EXAMPLE = """
        <h1 class="visually-hidden">cs1302</h1>
        <section>
        <h2 id="cs1302">cs1302</h2>
        <p>This project provides...</p>
        </section>
        """;

    private final VelocityEngine velocityEngine = new VelocityEngine();

    private final JsoupTool soup = new JsoupTool();

    @Test
    void configureTest() {
        final ToolContext velocityContext = new ToolContext(velocityEngine);
        final Map<String, Object> source = Map.of(ToolContext.CONTEXT_KEY, velocityContext);
        final ValueParser values = new ValueParser(source);
        soup.configure(values);
        assertEquals(UTF_8, soup.getOutputEncoding());
    } // configureTest

    @Test
    void setOutputEncodingTest() {
        assertEquals(UTF_8, soup.getOutputEncoding());
        soup.setOutputEncoding(UTF_16);
        assertEquals(UTF_16, soup.getOutputEncoding());
    } // setOutputEncodingTest


    @Test
    void parseTest() {
        Element fragment = soup.parse(JsoupToolTests.EXAMPLE);
        assertEquals(2, fragment.childrenSize());
        assertEquals("cs1302", fragment.child(0).text());
    } // parseTest

    @Test
    void selectTest() {
        Elements selected = soup.select(JsoupToolTests.EXAMPLE, "section > p");
        assertEquals(1, selected.size());
    } // selectTest

} // JsoupToolTests
