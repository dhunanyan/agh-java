import java.util.Random;
import java.lang.Math;

public class Matrix {
    double[] data;
    int rows;
    int cols;
    String matrix;

    Matrix( int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        data = new double[rows * cols];
    }

    Matrix(double[][] d){
        rows = d.length;
        cols = 0;

        //Declaration
        for(int i = 0; i < rows; i++){
            if(d[i].length > cols){
                cols = d[i].length;
            }
        }

        data = new double[rows*cols];

        //Implementation
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < d[i].length; j++){
                data[i * cols + j] = d[i][j];
            }
        }
    }

    double[][] asArray() {
        double[][] temp = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                temp[i][j] = data[i * cols + j];
            }
        }
        return temp;
    }


    //Getter
    double get(int r,int c){
        return this.data[r * cols + c];
    }

    //Setter
    void set(int r,int c, double value){
        this.data[r * cols + c] = value;}

    //Setter Add
    void setAdd(int r,int c, double value){
        this.data[r * cols + c] += value;
    }

    //Setter Sub
    void setSub(int r,int c, double value){
        this.data[r * cols + c] -= value;
    }

    //Setter Mul
    void setMul(int r,int c, double value){
        this.data[r * cols + c] *= value;
    }

    //ToString
    public String toString(){
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        int x = 0;
        int y = 0;
        for(int i = 0; i < rows; i++){
                buf.append("[");
                for(int j = 0; j < cols; j++) {
                    buf.append(data[i * cols + j]);
                    if (x < cols - 1) {
                        buf.append(",");
                    }
                    x++;
                }
                x = 0;
                buf.append("]");
                if(y < rows - 1){
                    buf.append(",");
                }
                y++;
        }
        buf.append("]");
        matrix = buf.toString();
        return buf.toString();
    }

    //Reshapes the Matrix
    void reshape(int newRows,int newCols){
        if(rows * cols != newRows*newCols)
            throw new RuntimeException(String.format("%d x %d matrix can't be reshaped to %d x %d",rows,cols,newRows,newCols));

    }

    //Returns the Size of Matrix
    int[] shape(){
        int[] temp = new int[]{rows,cols};
        return temp;
    }

    //Addition of Matrices
    Matrix add(Matrix m){
        Matrix resultMatrix = new Matrix(rows, cols);
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                resultMatrix.set(i,j, m.get(i,j) + this.get(i, j));
            }
        }

        return resultMatrix;
    }

    //Subtraction of Matrices
    Matrix sub(Matrix m){
        Matrix resultMatrix = new Matrix(rows, cols);
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                resultMatrix.set(i,j, m.get(i,j) - this.get(i, j));
            }
        }

        return resultMatrix;
    }

    //Multiplication of Matrices
    Matrix mul(Matrix m){
        Matrix resultMatrix = new Matrix(rows, cols);
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                resultMatrix.set(i,j, m.get(i,j) * this.get(i, j));
            }
        }

        return resultMatrix;
    }

    //Division of Matrices
    Matrix div(Matrix m){
        Matrix resultMatrix = new Matrix(rows, cols);
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                resultMatrix.set(i,j, m.get(i,j) / this.get(i, j));
            }
        }

        return resultMatrix;
    }

    //Matrix Addition with Scalar
    Matrix add(double w){
        Matrix resultMatrix = new Matrix(rows, cols);
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                resultMatrix.set(i,j, this.get(i,j) + w);
            }
        }

        return resultMatrix;
    }

    //Matrix Subtraction with Scalar
    Matrix sub(double w){
        Matrix resultMatrix = new Matrix(rows, cols);
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                resultMatrix.set(i,j, this.get(i,j) - w);
            }
        }

        return resultMatrix;
    }

    //Matrix Multiplication with Scalar
    Matrix mul(double w){
        Matrix resultMatrix = new Matrix(rows, cols);
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                resultMatrix.set(i,j, this.get(i,j) * w);
            }
        }

        return resultMatrix;
    }

    //Matrix Division with Scalar
    Matrix div(double w){
        Matrix resultMatrix = new Matrix(rows, cols);
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                resultMatrix.set(i,j, this.get(i,j) / w);
            }
        }

        return resultMatrix;
    }

    //Addition of Matrices with different Size
    Matrix dot(Matrix m){

        int resultRows = this.rows;
        int resultCols = m.cols;
        Matrix resultMatrix = new Matrix(resultRows, resultCols);
        int nRowsCols = this.cols;

        for(int i = 0; i < resultRows; i++){
            for(int j = 0; j < resultCols; j++){
                for( int k = 0; k < nRowsCols; k++){
                    resultMatrix.setAdd(i,j, resultMatrix.get(i,j) + this.get(i,k) * m.get(k,j));
                }
            }
        }

        return resultMatrix;
    }

    //Frobenius Norm
    double frobenius(){
        double result = 0;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                result =  result + Math.pow(this.get(i,j), 2);
            }
        }

        return result;
    }

    //Eye
    public static Matrix eye(int n){
        Matrix m = new Matrix(n,n);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i == j){
                    m.set(i,j,1);
                } else {
                    m.set(i,j,0);
                }
            }
        }
        return m;
    }

    //Matrix Random
    public static Matrix random(int rows, int cols){
        Matrix m = new Matrix(rows,cols);
        Random r = new Random();
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                m.set(i,j,r.nextDouble());
            }
        }
        return m;
    }

    //Gauss Determinant
    double gauss(){
       int N = rows;
       double det = 1;

        for (int i = 0; i < N; ++i)
        {
            boolean flag = false;
            if (this.get(i,i) == 0)
            {
                flag = true;
                for (int j = i; j < N; ++j)
                {
                    if (this.get(j,i) != 0) {
                        det *= -1;
                        for (int k = 0; k < N; ++k) {
                            double t = this.get(i,k);
                            this.set(i,k,this.get(j,k));
                            this.set(j,k,t);
                            flag = false;
                        }
                    }
                }
            }

            if (flag == true) {
                det = 0;
                break;
            } else {
                for (int j = i+1; j < N; ++j)
                {
                    double store = this.get(j,i);
                    for (int k = i; k < N; ++k) {
                        this.setSub(j,k,(this.get(i,k)*store)/this.get(i,i));

                    }
                }
                det *= this.get(i,i);
            }
        }
        return det;
    }

    //Inversion
    Matrix inv()
    {
        Matrix m = new Matrix(rows, cols * 2);
        double temp;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                m.set(i,j,this.get(i,j));
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < 2 * rows; j++)
                if (j == (i + rows))
                    m.set(i, j, 1);
        }

        for (int i = rows - 1; i > 0; i--) {
            if (m.get(i - 1, 0) < m.get(i,0)) {
                for(int j = 0; j < 2 * rows; j++){
                    temp = m.get(i,j);
                    m.set(i,j,m.get(i-1,j));
                    m.set(i-1, j ,temp);
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                if (j != i) {
                    temp = m.get(j,i) / m.get(i,i);
                    for (int k = 0; k < 2 * rows; k++) {
                        m.setSub(j,k,m.get(i,k) * temp);
                    }
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            temp = m.get(i,i);
            for (int j = 0; j < 2 * rows; j++) {
                m.set(i,j,m.get(i,j) / temp);
            }
        }

        Matrix resultMatrix = new Matrix(rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = rows; j < cols * 2; j++) {
                resultMatrix.set(i,j-rows,m.get(i,j));
            }
        }

        return resultMatrix;
    }
}


