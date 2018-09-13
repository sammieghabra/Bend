package ml.loss;

import ml.input.Input;
import ml.output.Output;
import ml.output.Score;

import java.util.List;

public class SVMLossFunction implements LossFunction {

    public Loss determineMultiClassLoss(Input input, Output output) {

        // TODO - this should be summed per class then divided by number of classes
        List<Score> scores = output.getScores();
        Double lossValue = scores.stream()
                .mapToDouble(i -> i.getValue() + 1)
                .filter(i -> i > 0)
                .sum();
        return new Loss(lossValue);
    }
}
