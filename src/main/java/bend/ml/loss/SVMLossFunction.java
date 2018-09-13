package bend.ml.loss;

import lombok.extern.slf4j.Slf4j;
import bend.ml.output.Output;
import bend.ml.output.Score;

@Slf4j
public class SVMLossFunction implements LossFunction {

    public Loss determineMultiClassLoss(Output output) {

        // TODO - Can you figure this out using Java8 streams?
        //List<Score> scores = output.getScores();
        //Double lossValue = scores.stream()
        //        .mapToDouble(i -> i.getValue() + 1)
        //        .filter(i -> i > 0)
        //        .sum();
        //return new Loss(lossValue);

        Double sumOfAllClasses = 0.0;

        for (int i = 0; i < output.getScores().size(); ++i) {

            Double perClass = 0.0;

            Score score = (Score) output.getScores().get(i);

            for (int j = 0; j < output.getScores().size(); ++j) {

                Score classScore = ((Score) output.getScores().get(j));
                if (j != i) {
                    perClass = perClass + Math.max((classScore.getValue() - score.getValue() + 1), 0);

                }
            }
            log.info("Score for index:"  + i + " is " + String.valueOf(perClass));
            sumOfAllClasses = sumOfAllClasses + perClass;
        }

        return new Loss(sumOfAllClasses/output.getScores().size());
    }
}
