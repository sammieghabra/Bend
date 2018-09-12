package ml.classifier;

import ml.input.Input;
import ml.output.Output;
import ml.parameter.HyperParameter;

public interface Classifier {
    Output classify(Input input, HyperParameter parameters);
}
