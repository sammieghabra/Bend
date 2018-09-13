package ml.output;

import java.util.List;

public interface Output<T> {
    T getOutput();
    List<Score> getScores();
}
