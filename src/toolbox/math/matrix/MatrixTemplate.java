package toolbox.math.matrix;

import toolbox.math.Vector4f;
import toolbox.math.vector.Vector;
import toolbox.math.vector.Vector1f;
import toolbox.math.vector.Vector2f;
import toolbox.math.vector.Vector3f;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Levin on 30.08.2017.
 */
public class MatrixTemplate {

    protected float[] elements;
    protected int columns;
    protected int rows;

    /**
     *
     * @param rows
     * the number of rows of the Matrix
     * @param columns
     * the numer of columns of the matrix
     */

    public MatrixTemplate(int rows, int columns){
        this.elements = new float[columns*rows];
        this.columns = columns;
        this.rows = rows;
    }

    /**
     *
     * @param e
     * The element you want to add to the Matrix
     *
     * @param row
     * The row in which the element is
     *
     * @param column
     * The column in which the elemnt is
     *
     */

    public void setElement(float e, int row, int column) {
        elements[row * columns + column] = e;
    }

    /**
     *
     * @param elements
     * Set all new elements
     *
     */

    public void setElements(float[] elements){
        this.elements = elements;
    }

    /**
     *
     * @param list
     * A list of Vectors with some sizes(beliebig) which will be the matrixes rows
     *
     * @return
     * returns a new Matrix with the content of the Vectors asrows
     *
     */

    public static MatrixTemplate createRawMatrix(List<Vector> list){
        int columns = list.get(0).getGrade();
        int rows = list.size();
        float[] content = new float[columns * rows];

        //Stuff to get the Vectors content
        if (list.get(0).getGrade() == 1){
            for (int i = 0; i<list.size(); i++){
                Vector1f v = (Vector1f) list.get(i);
                content[i * columns + 0] = v.getX();
            }
        }else if (list.get(0).getGrade() == 2){
            for (int i = 0; i<list.size(); i++){
                Vector2f v = (Vector2f) list.get(i);
                content[i * columns + 0] = v.getX();
                content[i * columns + 1] = v.getY();
            }
        }else if (list.get(0).getGrade() == 3){
            for (int i = 0; i<list.size(); i++){
                Vector3f v = (Vector3f) list.get(i);
                content[i * columns + 0] = v.getX();
                content[i * columns + 1] = v.getY();
                content[i * columns + 2] = v.getZ();
            }
        }else if (list.get(0).getGrade() == 4){
            for (int i = 0; i<list.size(); i++){
                Vector4f v = (Vector4f) list.get(i);
                content[i * columns + 0] = v.getX();
                content[i * columns + 1] = v.getY();
                content[i * columns + 2] = v.getZ();
                content[i * columns + 3] = v.getW();
            }
        }else {
            System.err.println("Update RawMatrix class!");
            System.exit(-1);
        }
        //--------------------------------------
        MatrixTemplate m = new MatrixTemplate(rows, columns);
        m.setElements(content);
        return m;
    }

    /**
     *
     * @param row
     * the row of the element
     *
     * @param column
     * the column of the elemnt
     *
     * @return
     * returns the needed element
     *
     */

    public float getElement(int row, int column){
        if (row > columns || column > rows){
            System.err.println("Matrix isn't big enough");
            System.exit(-1);
        }
        if (row < 0 || column < 0){
            System.err.println("Matrix call below 0");
            System.exit(-1);
        }

        return elements[row * columns + column];
    }


    /**
     * @param matrix
     * Needs a Matrix to add to the current Matrix
     * @return returns the new Matrix
     * @throws MatrixErrors.NotTheSameSizeException
     * throws an exception if the matrices are not compatible f.e. if you try to add a 3x3 to 2x2Matrix
     */

    public MatrixTemplate add(MatrixTemplate matrix) throws MatrixErrors.NotTheSameSizeException{
        if (matrix.columns != columns || matrix.rows != rows){
            throw new MatrixErrors.NotTheSameSizeException(rows, matrix.rows, columns, matrix.columns);
        }else {

            List<Vector> newMatrix = new ArrayList<>();
            for (int i = 0; i<rows;i++){
                Vector rowA = getRow(i);
                Vector rowB = getRow(i);
                /*try {
                    newMatrix.add(Vector.add(rowA,rowB));
                } catch (Vector.NotTheSameVectorException e) {
                    e.printStackTrace();
                }*/
            }
            return createRawMatrix(newMatrix);
        }
    }


    public Vector getRow(int rowNumber){
        if (rowNumber > rows){
            System.err.println("Matrix isn't big enough");
            System.exit(-1);
        }

        if (columns == 1){
            //Vector1f
            return new Vector1f(getElement(rowNumber, 0));

        }else if (columns == 2){
            //Vector2f
            float x = getElement(rowNumber, 0);
            float y = getElement(rowNumber, 1);
            return new Vector2f(x,y);

        }else if (columns == 3){
            //Vector3f
            float x = getElement(rowNumber, 0);
            float y = getElement(rowNumber, 1);
            float z = getElement(rowNumber, 2);
            return new Vector3f(x,y,z);

        }else if (columns == 4){
            //Vector4f
            float x = getElement(rowNumber, 0);
            float y = getElement(rowNumber, 1);
            float z = getElement(rowNumber, 2);
            float w = getElement(rowNumber, 3);
            return new Vector4f(x,y,z,w);

        }else {
            //Not implemented yet
            //TODO: implement!!!
            System.err.println("Update MatrixTemplate class");
            System.exit(-1);
        }
        System.err.println("Update MatrixTemplate class");
        System.exit(-1);
        return null;
    }

    public Vector getColumn(int columnNumber){
        if (columnNumber > columns){
            System.err.println("Matrix isn't big enough");
            System.exit(-1);
        }

        if (rows == 1){
            //Vector1f
            return new Vector1f(getElement(0, columnNumber));

        }else if (rows == 2){
            //Vector2f
            float x = getElement(0, columnNumber);
            float y = getElement(1, columnNumber);
            return new Vector2f(x,y);

        }else if (rows == 3){
            //Vector3f
            float x = getElement(0, columnNumber);
            float y = getElement(1, columnNumber);
            float z = getElement(2, columnNumber);
            return new Vector3f(x,y,z);

        }else if (rows == 4){
            //Vector4f
            float x = getElement(0, columnNumber);
            float y = getElement(1, columnNumber);
            float z = getElement(2, columnNumber);
            float w = getElement(3, columnNumber);
            return new Vector4f(x,y,z,w);

        }else {
            //Not implemented yet
            //TODO: implement!!!
            System.err.println("Update MatrixTemplate class");
            System.exit(-1);
        }
        System.err.println("Update MatrixTemplate class");
        System.exit(-1);
        return null;
    }

    public float[] getElements() {
        return elements;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }


}
