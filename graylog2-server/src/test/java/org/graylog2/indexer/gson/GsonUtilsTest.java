/**
 * This file is part of Graylog.
 *
 * Graylog is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog2.indexer.gson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.LongNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GsonUtilsTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void asJsonNode() throws Exception {
        final JsonNode jsonObject = objectMapper.createObjectNode();
        assertThat(GsonUtils.asJsonObject(jsonObject)).isEqualTo(jsonObject);
    }

    @Test
    public void asJsonObjectWithNull() throws Exception {
        assertThat(GsonUtils.asJsonObject(null)).isNull();
    }

    @Test
    public void asJsonObjectWithIncorrectType() throws Exception {
        final JsonNode jsonPrimitive = new TextNode("test");
        assertThat(GsonUtils.asJsonObject(jsonPrimitive)).isNull();
    }

    @Test
    public void asJsonArray() throws Exception {
        final JsonNode jsonArray = objectMapper.createArrayNode();
        assertThat(GsonUtils.asJsonArray(jsonArray)).isEqualTo(jsonArray);
    }

    @Test
    public void asJsonArrayWithNull() throws Exception {
        assertThat(GsonUtils.asJsonArray(null)).isNull();
    }

    @Test
    public void asJsonArrayWithIncorrectType() throws Exception {
        final JsonNode jsonPrimitive = new TextNode("test");
        assertThat(GsonUtils.asJsonArray(jsonPrimitive)).isNull();
    }

    @Test
    public void asString() throws Exception {
        final JsonNode jsonPrimitive = new TextNode("test");
        assertThat(GsonUtils.asString(jsonPrimitive)).isEqualTo("test");
    }

    @Test
    public void asStringObjectWithNull() throws Exception {
        assertThat(GsonUtils.asString(null)).isNull();
    }

    @Test
    public void asStringWithIncorrectType() throws Exception {
        final JsonNode jsonObject = new IntNode(42);
        assertThat(GsonUtils.asString(jsonObject)).isNull();
    }

    @Test
    public void asBoolean() throws Exception {
        final JsonNode jsonPrimitive = BooleanNode.FALSE;
        assertThat(GsonUtils.asBoolean(jsonPrimitive)).isFalse();
    }

    @Test
    public void asBooleanWithNull() throws Exception {
        assertThat(GsonUtils.asBoolean(null)).isNull();
    }

    @Test
    public void asBooleanWithIncorrectType() throws Exception {
        final JsonNode jsonPrimitive = new TextNode("test");
        assertThat(GsonUtils.asBoolean(jsonPrimitive)).isNull();
    }

    @Test
    public void asLong() throws Exception {
        final JsonNode jsonPrimitive = new LongNode(42L);
        assertThat(GsonUtils.asLong(jsonPrimitive)).isEqualTo(42L);
    }

    @Test
    public void asLongWithNull() throws Exception {
        assertThat(GsonUtils.asLong(null)).isNull();
    }

    @Test
    public void asLongWithIncorrectType() throws Exception {
        final JsonNode jsonPrimitive = new TextNode("test");
        assertThat(GsonUtils.asLong(jsonPrimitive)).isNull();
    }

    @Test
    public void asInteger() throws Exception {
        final JsonNode jsonPrimitive = new IntNode(42);
        assertThat(GsonUtils.asInteger(jsonPrimitive)).isEqualTo(42);
    }

    @Test
    public void asIntegerWithNull() throws Exception {
        assertThat(GsonUtils.asInteger(null)).isNull();
    }

    @Test
    public void asIntegerWithIncorrectType() throws Exception {
        final JsonNode jsonPrimitive = new TextNode("test");
        assertThat(GsonUtils.asInteger(jsonPrimitive)).isNull();
    }

    @Test
    public void entrySetAsMap() throws Exception {
        final ObjectNode jsonObject = objectMapper.createObjectNode();
        jsonObject.set("foo", new TextNode("bar"));
        jsonObject.set("question", new IntNode(42));

        assertThat(GsonUtils.entrySetAsMap(jsonObject))
                .containsEntry("foo", new TextNode("bar"))
                .containsEntry("question", new IntNode(42));
    }

    @Test
    public void entrySetAsMapWithNull() throws Exception {
        assertThat(GsonUtils.entrySetAsMap(null)).isNull();
    }

    @Test
    public void asMap() throws Exception {
        final ObjectNode jsonObject = objectMapper.createObjectNode();
        jsonObject.set("foo", new TextNode("bar"));
        jsonObject.set("question", new IntNode(42));

        assertThat(GsonUtils.asMap(objectMapper, jsonObject))
                .containsEntry("foo", "bar")
                .containsEntry("question", 42);
    }

    @Test
    public void asMapWithNull() throws Exception {
        assertThat(GsonUtils.asMap(objectMapper, null)).isNull();
    }
}