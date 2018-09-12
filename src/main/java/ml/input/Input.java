package ml.input;

import ml.output.Output;

public interface Input<T> {

    void setInput(T input);

    T getInput();

    Class<? extends Output> getOutputClass();
}
