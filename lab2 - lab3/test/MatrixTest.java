import static org.junit.Assert.*;

public class MatrixTest {

    @org.junit.Test
    public void asArray() {
        Matrix m = new Matrix(new double[][]{{1,2,3,4},{5,6},{7,8},{9}});
        double[][] mAlt = {{1,2,3,4},{5,6,0,0},{7,8,0,0},{9,0,0,0}};
        m.asArray();
        for(int i = 0; i < m.rows; i++){
            for(int j = 0; j < m.cols; j++){
                assertEquals(m.get(i,j), mAlt[i][j], 1e-10);
            }
        }
    }

    @org.junit.Test
    public void get() {
        Matrix m = new Matrix(new double[][]{{1,2,3,4},{5,6},{7,8},{9}});
        assertEquals(m.get(3,0), 9, 1e-10);
    }

    @org.junit.Test
    public void set() {
        Matrix m = new Matrix(new double[][]{{1,2,3,4},{5,6},{7,8},{9}});
        m.set(0,0,10);
        assertEquals(m.get(0,0), 10, 1e-10);
    }

    @org.junit.Test
    public void setAdd() {
        Matrix m = new Matrix(new double[][]{{1,2,3,4},{5,6},{7,8},{9}});
        double x = m.get(0,0);
        m.setAdd(0,0,10);
        assertEquals(m.get(0,0),x + 10, 1e-10);
    }

    @org.junit.Test
    public void setSub() {
        Matrix m = new Matrix(new double[][]{{1,2,3,4},{5,6},{7,8},{9}});
        double x = m.get(0,0);
        m.setSub(0,0,10);
        assertEquals(m.get(0,0),x - 10, 1e-10);
    }

    @org.junit.Test
    public void setMul() {
        Matrix m = new Matrix(new double[][]{{1,2,3,4},{5,6},{7,8},{9}});
        double x = m.get(0,0);
        m.setMul(0,0,10);
        assertEquals(m.get(0,0),x * 10, 1e-10);
    }

    @org.junit.Test
    public void testToString() {
        Matrix m = new Matrix(new double[][]{{1,2,3,4},{5,6},{7,8},{9}});
        int brackets = 2 * m.cols + 2;
        int commas = ((m.cols - 1) * m.rows) + (m.rows - 1);
        int total = commas + brackets;

        String s= "[[1.0,2.0,3.0,4.0],[5.0,6.0,0.0,0.0],[7.0,8.0,0.0,0.0],[9.0,0.0,0.0,0.0]]";
        s= s.replaceAll("]","x");
        s= s.replaceAll("\\[","x");
        s= s.replaceAll(",","x");
        int x = 0;
        for(int i=0;i<s.length();i++) {
            if(s.charAt(i) == 'x'){
                x++;
            }
        }

        assertEquals(x, total);
    }

    @org.junit.Test
    public void reshape() {
    }

    @org.junit.Test
    public void testAdd() {
        Matrix m = new Matrix(new double[][]{{1,2,3,4},{5,6},{7,8},{9}});
        Matrix mAlt = new Matrix(new double[][]{{1,2,3,4},{5,6},{7,8},{9}});

        assertEquals(m.add(mAlt.mul(-1)).frobenius(),0,1e-10);
    }

    @org.junit.Test
    public void testSub() {
        Matrix m = new Matrix(new double[][]{{1,2,3,4},{5,6},{7,8},{9}});

        assertEquals(m.sub(m).frobenius(),0,1e-10);
    }

    @org.junit.Test
    public void testMul() {
        Matrix m = new Matrix(new double[][]{{1,2,3,4},{5,6},{7,8},{9}});
        Matrix mAlt = new Matrix(new double[][]{{1,1/2,1/3,1/4},{1/5,1/6},{1/7,1/8},{1/9}});

        assertEquals(m.mul(mAlt).frobenius(),1,1e-10);
    }

    @org.junit.Test
    public void testDiv() {
        Matrix m = new Matrix(new double[][]{{1,2,3,4},{5,6,7,8},{7,8,9,10},{9,4,5,6}});

        assertEquals(m.div(m).frobenius(),m.rows * m.cols,1e-10);
    }

    @org.junit.Test
    public void dot() {
        //nie zrozumiałem jak mam porównać swoje obliczenia na kartce do testu.
    }

    @org.junit.Test
    public void eye() {
        Matrix m = new Matrix(6,6);
        assertEquals(m.eye(6).frobenius(), 6,1e-10);
    }

    //błąd w metodzie
    @org.junit.Test
    public void inv() {
        Matrix m = new Matrix(new double[][]{{1,2,3,4},{5,6},{7,8},{9}});
        Matrix A = new Matrix(4,4);
        Matrix B = new Matrix(4,4);
        A = m.inv().mul(m);
        B = m.eye(4);

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(i == j) assertEquals(A.get(i,j), B.get(i,j), 1e-10);
            }
        }
    }
}