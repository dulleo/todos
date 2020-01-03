package in.rs.mdprogramming.todos.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import in.rs.mdprogramming.todos.deserializer.TodoStatusDeserializer;

import java.util.Arrays;

@JsonDeserialize(using = TodoStatusDeserializer.class)
public enum TodoStatus {

    OPEN("OPEN"),CLOSED("CLOSED");

    private String status;

    TodoStatus(String status) {
        this.status = status;
    }

    @JsonCreator
    public static TodoStatus fromStatusName(String statusName) {
        for(TodoStatus status : values()) {
            if(status.status.equals(statusName)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown enum type " + statusName + ", Allowed values are " + Arrays.toString(values()));
    }

    @Override
    public String toString() {
        return status;
    }
}
