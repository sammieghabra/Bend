package bend.ml.loss;

import bend.ml.output.Output;
import bend.ml.output.Score;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SoftMaxLossFunction implements LossFunction {
    @Override
    public Loss determineMultiClassLoss(Output output) {
        // TODO - use java8 streams
        //Lets just say that the right score is in the first index ...
        //Double probability = Math.exp(scores.get(0).getValue()) / scores.stream()
        //        .mapToDouble(i -> Math.exp(i.getValue())).sum();
        //return new Loss(Math.log(probability) * -1);

        List<Score> scores = output.getScores();

        Double sumOfProbabilityAllClasses = 0.0;

        List<Double> exponentiatedValues = new ArrayList<>();

        for (int i = 0; i < scores.size(); ++i) {
            Double probability = Math.exp(scores.get(i).getValue());
            sumOfProbabilityAllClasses += probability;
            exponentiatedValues.add(probability);
        }

        Double lossOfAllClasses = 0.0;

        for (int i = 0; i < exponentiatedValues.size(); ++i) {
            Double lossPerClass = Math.log10((exponentiatedValues.get(i)/ sumOfProbabilityAllClasses)) * -1;
            log.info("Loss per class: " + i + " was " + lossPerClass);
            lossOfAllClasses+=lossPerClass;
        }

        return new Loss(lossOfAllClasses/scores.size());
    }
}
