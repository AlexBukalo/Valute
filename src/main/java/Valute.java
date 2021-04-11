
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shaded.parquet.org.codehaus.jackson.annotate.JsonProperty;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Valute {

    @JsonProperty("ID")
    private String id;

    @JsonProperty("NumCode")
    private String numCode;

    @JsonProperty("CharCode")
    private String charCode;

    @JsonProperty("Nominal")
    private int nominal;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Value")
    private double value;

    @JsonProperty("Previous")
    private double previous;

}
