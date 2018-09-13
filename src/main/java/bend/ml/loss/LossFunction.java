package bend.ml.loss;

import bend.ml.output.Output;

public interface LossFunction {

    Loss determineMultiClassLoss(Output output);

}
