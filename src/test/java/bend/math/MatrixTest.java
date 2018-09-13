package bend.math;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class MatrixTest {

    public static final double[][] WEIGHT = {
            {0.01, -0.05, 0.1, 0.05},
            {0.7, 0.2, 0.05, 0.16},
            {0.0, -0.45, -0.2, 0.03}
    };
    public static final double[][] IMAGE = {
            {-15},
            {22},
            {-44},
            {56}
    };
    public static final double[][] BIAS = {
            {0.0},
            {0.2},
            {-0.3}
    };

    @Test
    public void testMatrixMultiplication() {
        Matrix weight = new Matrix(WEIGHT);
        Matrix image = new Matrix(IMAGE);
        Matrix bias = new Matrix(BIAS);

        Matrix score = weight.times(image).plus(bias);
        assertThat(score.getRows(), is(3));
        assertThat(score.getColumns(), is(1));

        BigDecimal[][] expectedAnswer = {
                {BigDecimal.valueOf(-2.85)},
                {BigDecimal.valueOf(0.86)},
                {BigDecimal.valueOf(0.28)}
        };

        assertThat(score.convertToBigDecimal(),
                equalTo(expectedAnswer));
    }
}
