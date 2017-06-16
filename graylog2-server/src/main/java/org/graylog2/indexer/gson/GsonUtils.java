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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.ImmutableMap;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.Map;

public final class GsonUtils {

    public static final TypeReference MAP_STRING_OBJECT_TYPE = new TypeReference<Map<String, Object>>() {
    };

    private GsonUtils() {
    }

    @Nullable
    public static ObjectNode asJsonObject(JsonNode jsonElement) {
        return jsonElement != null && jsonElement.isObject() ? (ObjectNode) jsonElement : null;
    }

    @Nullable
    public static ArrayNode asJsonArray(JsonNode jsonElement) {
        return jsonElement != null && jsonElement.isArray() ? (ArrayNode) jsonElement : null;
    }

    @Nullable
    public static String asString(JsonNode jsonElement) {
        return jsonElement != null && jsonElement.isTextual() ? jsonElement.asText() : null;
    }

    @Nullable
    public static Boolean asBoolean(JsonNode jsonElement) {
        return jsonElement != null && jsonElement.isBoolean() ? jsonElement.asBoolean() : null;
    }

    @Nullable
    public static Long asLong(JsonNode jsonElement) {
        return jsonElement != null && jsonElement.isLong() ? jsonElement.asLong() : null;
    }

    @Nullable
    public static Integer asInteger(JsonNode jsonElement) {
        return jsonElement != null && jsonElement.isInt() ? jsonElement.asInt() : null;
    }

    @Nullable
    public static Map<String, JsonNode> entrySetAsMap(JsonNode jsonObject) {
        if (jsonObject == null) {
            return null;
        } else {
            final ImmutableMap.Builder<String, JsonNode> mapBuilder = ImmutableMap.builder();
            final Iterator<Map.Entry<String, JsonNode>> entrySet = jsonObject.fields();
            while (entrySet.hasNext()) {
                Map.Entry<String, JsonNode> entry = entrySet.next();
                mapBuilder.put(entry.getKey(), entry.getValue());
            }
            return mapBuilder.build();
        }
    }

    @Nullable
    public static Map<String, Object> asMap(ObjectMapper objectMapper, JsonNode jsonObject) {
        return objectMapper.convertValue(jsonObject, MAP_STRING_OBJECT_TYPE);
    }
}
