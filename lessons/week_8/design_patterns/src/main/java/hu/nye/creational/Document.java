package hu.nye.creational;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Document {
    String title;
    String body;

    @Override
    public Document clone() {
        return toBuilder().build();
    }
}
