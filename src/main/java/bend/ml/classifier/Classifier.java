package bend.ml.classifier;

import bend.ml.input.Input;
import bend.ml.output.Output;
import bend.ml.parameter.HyperParameter;

public interface Classifier {
    Output classify(Input input, HyperParameter parameters);
}
