import java.util.Arrays;

/**
 * An implementation of the Matrix ADT. Provides four basic operations over an
 * immutable type.
 *
 * 08/27/20
 *
 * @author Jeremy Munson
 * @version (1.0)
 */
public class MunsonMatrix implements Matrix {

    private int[][] values;

    //Constructor
    public MunsonMatrix(int[][] matrix) {
        this.values = matrix.clone();
        values = new int[matrix.length][];
        for (int i = 0; i < matrix.length; i++) {
            values[i] = Arrays.copyOf(matrix[i], matrix[i].length);
        }
    }

    @Override
    public int getElement(int y, int x) {
        return this.values[x][y];
    }

    @Override
    public int getRows() {
        int r=0;
        try {
            r= values.length;
        }catch(ArrayIndexOutOfBoundsException ae){
            System.out.println("No rows");
        }
        return r;
    }

    @Override
    public int getColumns() {
        int c=0;
        try {
            c=values[0].length;
        }catch(ArrayIndexOutOfBoundsException ae){
            System.out.println("No columns");
        }
        return c;
    }

    @Override
    public Matrix scale(int scalar) {
        int[][] result;
        int rowCount = getRows();
        int columnCount;
        int r = 0;
        int count = 0;
        int c;
        if (rowCount !=0) {
            columnCount=getColumns();
            int resultRow = rowCount * scalar;
            int resultCol = columnCount * scalar;
            result = new int[resultRow][resultCol];

            for (int i = 0; i < resultRow; i++) {
                c = 0;
                for (int j = 0; j < columnCount; j++) {
                    for (int k = 0; k < scalar; k++) {
                        result[i][c] = this.values[r][j];
                        c++;
                    }
                }
                count++;
                if (count >= scalar) {
                    r++;
                    count = 0;
                }

            }
            return new MunsonMatrix(result);
        }
        return null;

    }

    @Override
    public Matrix plus(Matrix other) {
        int rows=getRows();
        int columns=getColumns();
        if((rows != other.getRows()) || (columns != other.getColumns()) )
            throw new RuntimeException("Matrices do not have matching dimensions");
        int[][] result = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][j] = this.getElement(i,j) + other.getElement(i, j);
            }
        }
        return new MunsonMatrix(result);
    }

    @Override
    public Matrix minus(Matrix other) {
        int rows=getRows();
        int columns=getColumns();
        if((rows != other.getRows()) || (columns != other.getColumns()) )
            throw new RuntimeException("Matrices do not have matching dimensions");
        int[][] result = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][j] = this.getElement(i,j) - other.getElement(i, j);
            }
        }
        return new MunsonMatrix(result);
    }

    @Override
    public Matrix multiply(Matrix other) {
        int rows=getRows();
        int columns=getColumns();
        if((rows != other.getRows()) || (columns != other.getColumns()) )
            throw new RuntimeException("Matrices do not have matching dimensions");
        int[][] result = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][j] = this.getElement(i,j) * other.getElement(i, j);
            }
        }
        return new MunsonMatrix(result);
    }

    @Override
    public boolean equals(Object other) {
        int rows=getRows();
        int columns=getColumns();
        Matrix m=null;
        boolean flag=true;
        try {
            m = (MunsonMatrix) other;
            if((rows != m.getRows()) || (columns != m.getColumns()) ) {
                flag = false;
                return flag;
            }
            int[][] result = new int[rows][columns];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if(this.getElement(i,j) == m.getElement(i, j))
                        flag= flag && true;
                    else{
                        flag= flag && false;
                    }
                }
            }
        }catch(Exception e){
            flag=false;
            System.out.print("Null or Not a valid type.\n");
        }
        return flag;
    }

    @Override
    public String toString() {
        StringBuffer sb=new StringBuffer();
        int rows = getRows();
        int columns = getColumns();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                sb.append(values[i][j] + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Entry point for matrix testing.
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int[][] data1 = new int[0][0];
        int[][] data2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] data3 = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
        int[][] data4 = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
        int[][] data5 = {{1, 4, 7}, {2, 5, 8}};

        Matrix m1 = new MunsonMatrix(data1);
        Matrix m2 = new MunsonMatrix(data2);
        Matrix m3 = new MunsonMatrix(data3);
        Matrix m4 = new MunsonMatrix(data4);
        Matrix m5 = new MunsonMatrix(data5);

        System.out.println("m1 --> Rows: " + m1.getRows() + " Columns: " + m1.getColumns());
        System.out.println("m2 --> Rows: " + m2.getRows() + " Columns: " + m2.getColumns());
        System.out.println("m3 --> Rows: " + m3.getRows() + " Columns: " + m3.getColumns());

        //check for reference issues
        System.out.println("m2 -->\n" + m2);
        data2[1][1] = 101;
        System.out.println("m2 -->\n" + m2);

        //test equals
        System.out.println("m2==null: " + m2.equals(null));             //false
        System.out.println("m3==\"MATRIX\": " + m2.equals("MATRIX"));   //false
        System.out.println("m2==m1: " + m2.equals(m1));                 //false
        System.out.println("m2==m2: " + m2.equals(m2));                 //true
        System.out.println("m2==m3: " + m2.equals(m3));                 //false
        System.out.println("m3==m4: " + m3.equals(m4));                 //true

        //test operations (valid)
        System.out.println("m1 + m1:\n" + m1.plus(m1));
        System.out.println("m1 + m1:\n" + m1.plus(m1));
        System.out.println("2 * m2:\n" + m2.scale(2));
        System.out.println("m2 + m3:\n" + m2.plus(m3));
        System.out.println("m2 - m3:\n" + m2.minus(m3));
        System.out.println("3 * m5:\n" + m5.scale(3));

        //not tested... multiply(). you know what to do.

        //test operations (invalid)
        //System.out.println("m1 + m2" + m1.plus(m2));
        //System.out.println("m1 + m5" + m1.plus(m5));
        //System.out.println("m1 - m2" + m1.minus(m2));

    }

    
}