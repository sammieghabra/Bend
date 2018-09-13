package bend.math;

import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
final public class Matrix {
    private final int rows;             // number of rows
    private final int columns;             // number of columns
    private final double[][] data;   // rows-by-columns array

    // create rows-by-columns matrix of 0's
    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        data = new double[rows][columns];
    }

    // create matrix based on 2d array
    public Matrix(double[][] data) {
        rows = data.length;
        columns = data[0].length;
        this.data = new double[rows][columns];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                this.data[i][j] = data[i][j];
    }

    // copy constructor
    private Matrix(Matrix A) {
        this(A.data);
    }

    // create and return a random rows-by-columns matrix with values between 0 and 1
    public static Matrix random(int M, int N) {
        Matrix A = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                A.data[i][j] = Math.random();
        return A;
    }

    // create and return the columns-by-columns identity matrix
    public static Matrix identity(int N) {
        Matrix I = new Matrix(N, N);
        for (int i = 0; i < N; i++)
            I.data[i][i] = 1;
        return I;
    }

    // swap rows i and j
    private void swap(int i, int j) {
        double[] temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    // create and return the transpose of the invoking matrix
    public Matrix transpose() {
        Matrix A = new Matrix(columns, rows);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                A.data[j][i] = this.data[i][j];
        return A;
    }

    // return C = A + B
    public Matrix plus(Matrix B) {
        Matrix A = this;
        if (B.rows != A.rows || B.columns != A.columns) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(rows, columns);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                C.data[i][j] = A.data[i][j] + B.data[i][j];
        return C;
    }


    // return C = A - B
    public Matrix minus(Matrix B) {
        Matrix A = this;
        if (B.rows != A.rows || B.columns != A.columns) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(rows, columns);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                C.data[i][j] = A.data[i][j] - B.data[i][j];
        return C;
    }

    // does A = B exactly?
    public boolean eq(Matrix B) {
        Matrix A = this;
        if (B.rows != A.rows || B.columns != A.columns) throw new RuntimeException("Illegal matrix dimensions.");
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                if (A.data[i][j] != B.data[i][j]) return false;
        return true;
    }

    // return C = A * B
    public Matrix times(Matrix B) {
        Matrix A = this;
        if (A.columns != B.rows) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(A.rows, B.columns);
        for (int i = 0; i < C.rows; i++)
            for (int j = 0; j < C.columns; j++)
                for (int k = 0; k < A.columns; k++)
                    C.data[i][j] += (A.data[i][k] * B.data[k][j]);
        return C;
    }


    // return x = A^-1 b, assuming A is square and has full rank
    public Matrix solve(Matrix rhs) {
        if (rows != columns || rhs.rows != columns || rhs.columns != 1)
            throw new RuntimeException("Illegal matrix dimensions.");

        // create copies of the data
        Matrix A = new Matrix(this);
        Matrix b = new Matrix(rhs);

        // Gaussian elimination with partial pivoting
        for (int i = 0; i < columns; i++) {

            // find pivot row and swap
            int max = i;
            for (int j = i + 1; j < columns; j++)
                if (Math.abs(A.data[j][i]) > Math.abs(A.data[max][i]))
                    max = j;
            A.swap(i, max);
            b.swap(i, max);

            // singular
            if (A.data[i][i] == 0.0) throw new RuntimeException("Matrix is singular.");

            // pivot within b
            for (int j = i + 1; j < columns; j++)
                b.data[j][0] -= b.data[i][0] * A.data[j][i] / A.data[i][i];

            // pivot within A
            for (int j = i + 1; j < columns; j++) {
                double m = A.data[j][i] / A.data[i][i];
                for (int k = i + 1; k < columns; k++) {
                    A.data[j][k] -= A.data[i][k] * m;
                }
                A.data[j][i] = 0.0;
            }
        }

        // back substitution
        Matrix x = new Matrix(columns, 1);
        for (int j = columns - 1; j >= 0; j--) {
            double t = 0.0;
            for (int k = j + 1; k < columns; k++)
                t += A.data[j][k] * x.data[k][0];
            x.data[j][0] = (b.data[j][0] - t) / A.data[j][j];
        }
        return x;

    }

    public void printMatrix() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++)
                System.out.printf("%9.4f", data[i][j]);
            System.out.println();
        }
    }

    public BigDecimal[][] convertToBigDecimal() {
        BigDecimal [][]result = new BigDecimal[this.data.length][this.data[0].length];

        for (int i = 0; i < this.data.length; ++i) {
            for (int j = 0; j < this.data[0].length; j++) {
                BigDecimal bigDecimal =  BigDecimal.valueOf(this.data[i][j]);
                bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
                result[i][j] = bigDecimal;
            }
        }

        return result;
    }
}