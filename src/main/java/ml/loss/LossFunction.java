package ml.loss;

import ml.input.Input;
import ml.output.Output;

public interface LossFunction {

    Loss determineMultiClassLoss(Input input, Output output);

}
