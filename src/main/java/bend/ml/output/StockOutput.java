package bend.ml.output;

import bend.model.Stock;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class StockOutput implements Output<Stock>{

    List<Score> scores;

    @Override
    public Stock getOutput() {
        return null;
    }
}
