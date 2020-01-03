package in.rs.mdprogramming.todos.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import in.rs.mdprogramming.todos.model.TodoStatus;

import java.io.IOException;

public class TodoStatusDeserializer extends JsonDeserializer<TodoStatus> {

    @Override
    public TodoStatus deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);

        if (node == null) {
            return null;
        }

        String statusName = node.findPath("status").textValue();

        if (statusName == null) {
            return null;
        }

        return TodoStatus.fromStatusName(statusName);
    }
}
