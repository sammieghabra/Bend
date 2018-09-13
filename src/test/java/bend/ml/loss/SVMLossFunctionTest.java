package bend.ml.loss;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import bend.ml.output.Output;
import bend.ml.output.Score;
import bend.ml.output.StockOutput;
import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class SVMLossFunctionTest {

    private SVMLossFunction svmLossFunction;

    @Before
    public void setUp() {
        svmLossFunction = new SVMLossFunction();
    }

    @Test
    public void verifyLossForSVM() {
        Output output = new StockOutput(getScores());
        Loss loss = svmLossFunction.determineMultiClassLoss(output);
        BigDecimal actual = BigDecimal.valueOf(loss.getValue()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal expected = BigDecimal.valueOf(3.61);
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
