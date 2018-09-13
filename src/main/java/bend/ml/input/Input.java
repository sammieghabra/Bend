package bend.ml.input;

import bend.ml.output.Output;

public interface Input<T> {

    void setInput(T input);

    T getInput();

    Class<? extends Output> getOutputClass();
}
