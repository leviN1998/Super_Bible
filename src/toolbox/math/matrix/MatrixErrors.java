package toolbox.math.matrix;


import toolbox.math.vector.Vector2f;


/**
 * Created by Levin on 03.09.2017.
 */
public class MatrixErrors {
    public static class NotTheSameSizeException extends Exception{
        private int rowsA;
        private int rowsB;
        private int columnsA;
        private int columnsB;

        public NotTheSameSizeException(int rA, int rB, int cA, int cB){
            this.rowsA = rA;
            this.rowsB = rB;
            this.columnsA = cA;
            this.columnsB = cB;
        }

        public Vector2f getRows(){
            return new Vector2f(rowsA, rowsB);
        }

        public Vector2f getColumns(){
            return new Vector2f(columnsA, columnsB);
        }
    }
}


