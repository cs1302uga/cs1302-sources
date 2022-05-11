package io.github.cs1302uga;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.google.gson.JsonElement;

/**
 * Provides tests for the {@link io.github.cs1302uga.GsonTool} class.
 */
class GsonToolTests {

    private static final String EXAMPLE = "[{\"name\": \"Bob\"}]";

    private final GsonTool gson = new GsonTool();

    @Test
    void parseTest() {
        JsonElement people = gson.parse(GsonToolTests.EXAMPLE);
        assertTrue(people.isJsonArray());
        assertEquals(1, people.getAsJsonArray().size());
    } // parseTest

    @Test
    void parseResourceTest() {
        JsonElement squad = gson.parseResource("squad.json");
        assertTrue(squad.isJsonObject());
        assertTrue(squad.getAsJsonObject().has("squadName"));
    } // parseTestTest

} // GsonToolTests
