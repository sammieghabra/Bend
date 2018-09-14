package bend.ml.loss;

import bend.ml.output.Output;
import bend.ml.parameter.Weight;

import java.util.List;

/**
 * Determines how bad your weight is according to the scores. You want a low loss
 * for good data
 */
public interface LossFunction {

    Loss determineMultiClassLoss(Output output);

    default Loss determineRegularization(Weight weight) {
        List<List<Double>> values = weight.getValues();

        Double totalLoss = 0.0;

        for (int i = 0; i < values.size(); ++i) {
            for (int j = 0; j < values.get(i).size(); ++i) {
                totalLoss+= Math.pow(values.get(i).get(j), 2);
            }
        }

        return new Loss(totalLoss);
    }

    default Loss determineOverallLoss(Output output, Weight weight) {
        Loss multiClassLoss = determineMultiClassLoss(output);
        Loss regularizationLoss = determineRegularization(weight);

        return new Loss(multiClassLoss.getValue() + regularizationLoss.getValue());
    }

}
