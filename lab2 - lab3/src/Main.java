public class Main {

    public static void main(String[] args) {
        Matrix m = new Matrix(new double[][]{{1,2,3,4},{5,6},{7,8},{9}});
        System.out.println("Converting to String");
        System.out.println(m + "\n");
        System.out.println("Rows and Columns of Matrix");
        System.out.println("(" + m.shape()[0] + "," + m.shape()[1] + ")" + "\n");

        Matrix mAlt = new Matrix(new double[][]{{4,5,6,8},{2,2,1,9},{6,9,9,9},{4,1,8,9}});
        System.out.println("Math operations on Matrices");
        System.out.println("Addition: " + mAlt.add(m));
        System.out.println("Subtraction: " + mAlt.sub(m));
        System.out.println("Multiplication: " + mAlt.mul(m));
        System.out.println("Division: " + mAlt.div(m) + "\n");

        double w = 5;
        System.out.println("Math operations on Matrix with Scalar");
        System.out.println("Addition: " + mAlt.add(w));
        System.out.println("Subtraction: " + mAlt.sub(w));
        System.out.println("Multiplication: " + mAlt.mul(w));
        System.out.println("Division: " + mAlt.div(w) + "\n");

        System.out.println("Multiplying Matrices with different Sizes");
        System.out.println("Multiplication: " + mAlt.dot(m) + "\n");

        System.out.println("Frobenius Norm of Matrix m");
        System.out.print(m.frobenius() + "\n" + "\n");

        System.out.println("Generating Random Matrix");
        Matrix r = Matrix.random(4,5);
        System.out.println(r + "\n");

        System.out.println("Determinant of Matrix (Gaussian Elimination)");
        System.out.println("Matrix m: " + m.gauss());
        System.out.println("Matrix mAlt: " + mAlt.gauss());
        System.out.println("Matrix mAlt: " + mAlt.inv());
    }
}
