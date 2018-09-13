package ml.loss;

import ml.input.Input;
import ml.output.Output;
import ml.output.Score;

import java.util.List;

public class SoftMaxLossFunction implements LossFunction {
    @Override
    public Loss determineMultiClassLoss(Input input, Output output) {
        List<Score> scores = output.getScores();

        // TODO - this should be per class and then divided by number of classes
        //Lets just say that the right score is in the first index ...
        Double probability = Math.exp(scores.get(0).getValue()) / scores.stream()
                .mapToDouble(i -> Math.exp(i.getValue())).sum();
        return new Loss(Math.log(probability) * -1);
    }
}
