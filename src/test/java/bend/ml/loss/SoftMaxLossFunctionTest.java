package bend.ml.loss;

import bend.ml.output.Output;
import bend.ml.output.Score;
import bend.ml.output.StockOutput;
import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class SoftMaxLossFunctionTest {
    private SoftMaxLossFunction softMaxLossFunction;

    @Before
    public void setUp() {
        softMaxLossFunction = new SoftMaxLossFunction();
    }

    @Test
    public void verifyLossForSVM() {
        Output output = new StockOutput(getScores());
        Loss loss = softMaxLossFunction.determineMultiClassLoss(output);
        BigDecimal actual = BigDecimal.valueOf(loss.getValue()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal expected = BigDecimal.valueOf(0.82);
        assertThat(actual, equalTo(expected));
    }

    List<Score> getScores() {
        return ImmutableList.of(
                new Score(-2.84),
                new Score(0.86),
                new Score(0.28)
        );
    }
}
