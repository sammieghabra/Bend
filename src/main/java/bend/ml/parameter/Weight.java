package bend.ml.parameter;

import lombok.Data;

import java.util.List;

@Data
public class Weight {
    List<List<Double>> values;
}
