package pl.kurs.java.model.command;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
public class CreateShapeCommand {
    @NotNull (message = "TYPE_NOT_NULL")
    private String type;
    private Map<String,Object> parameters;

}
