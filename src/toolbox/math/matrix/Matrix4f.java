package toolbox.math.matrix;

import toolbox.math.BufferTools;
import toolbox.math.Vector4f;
import toolbox.math.vector.Vector3f;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Levin on 30.08.2017.
 */
public class Matrix4f{

    private float[] elements;


    /**
     * Creates an empty Matrix
     */
    public Matrix4f(){
        this.elements = new float[4 * 4];
        for (int i = 0; i<16; i++){
            elements[i] = 0;
        }
    }


    /**
     * Method which creates an identity matrix
     * @return
     * the matrix
     */
    public static Matrix4f identity(){
        Vector4f a = new Vector4f(1.0f, 0, 0, 0);
        Vector4f b = new Vector4f(0, 1.0f, 0, 0);
        Vector4f c = new Vector4f(0, 0, 1.0f, 0);
        Vector4f d = new Vector4f(0, 0, 0, 1.0f);
        List<Vector4f> rows = new ArrayList<>();
        rows.add(a);
        rows.add(b);
        rows.add(c);
        rows.add(d);
        return createWithRows(rows);
    }


    /**
     * Method to create a translation-matrix
     * @param trans
     * the wanted translation
     * @return
     * the translation-matrix
     */
    public static Matrix4f translation(Vector3f trans){
        Matrix4f out = identity();
        out.setElement(0, 3, trans.getX());
        out.setElement(1, 3, trans.getY());
        out.setElement(2, 3, trans.getZ());
        return out;
    }


    /**
     * This Method creates a rotation Matrix with the given rotations
     * @param rot
     * Vector3f with the rotation around the x-Axis as x-Value usw.
     * @return
     * the rotationMatrix
     */
    public static Matrix4f rotation(Vector3f rot){
        Vector3f rota = new Vector3f((float)Math.toRadians(rot.getX()),
                (float) Math.toRadians(rot.getY()),(float) Math.toRadians(rot.getZ()));
        Matrix4f out = identity();

        double cT = Math.cos(rota.getY());
        double sT = Math.sin(rota.getY());

        double cV = Math.cos(rota.getZ());
        double sV = Math.sin(rota.getZ());

        double cO = Math.cos(rota.getX());
        double sO = Math.sin(rota.getX());

        out.setElement(0, 0, cT * cV);
        out.setElement(0, 1, (cO * sV) + (sO * sT * cV));
        out.setElement(0, 2, (sO * sV) - (cO * sT * cV));

        out.setElement(1, 0, (-cT) * sV);
        out.setElement(1, 1, (cO * cV) - (sO * sT * sV));
        out.setElement(1, 2, (sO * cV) + (cO * sT * sV));

        out.setElement(2, 0, sT);
        out.setElement(2, 1, (-sO) * cT);
        out.setElement(2, 2, cO * cT);

        return out;
    }


    /**
     * this Method creates a scaling Matrix with the given scaling as Vector3f
     * @param scale
     * x-Value = scaling in x-direction usw.
     * @return
     * scaling matrix
     */
    public static Matrix4f scaling(Vector3f scale){
        Matrix4f out = identity();
        out.setElement(0, 0, scale.x);
        out.setElement(1, 1, scale.y);
        out.setElement(2, 2, scale.z);
        return out;
    }


    /**
     * This method returns a transformationMatrix which consists of the 3 transformations multiplied together
     * @param trans
     * The translation of the Matrix
     * @param rot
     * The rotation part
     * @param scale
     * the scaling part
     * @return
     * the transformation Matrix
     */
    public static Matrix4f transformation(Vector3f trans, Vector3f rot, Vector3f scale){
        Matrix4f tR = translation(trans).multiply(rotation(rot));
        return tR.multiply(scaling(scale));
    }


    /**
     * This method creates a projection Matrix for the given parameters
     * @param fov
     * the field of view
     * @param nearPlane
     * the point closest to the camera (closer points will be discarded)
     * @param farPlane
     * the point most far away from the camera
     * @param width
     * width of the screen in pixels
     * @param height
     * height of the screen in picels
     * @return
     * a projectionMatrix
     */
    public static Matrix4f projection(float fov, float nearPlane, float farPlane, int width, int height){
        float aspectRatio = (float) width / (float) height;
        float y_scale = (float) ((1f / Math.tan(Math.toRadians(fov / 2f))) * aspectRatio);
        float x_scale = y_scale / aspectRatio;
        float frustrumLength = farPlane - nearPlane;

        Matrix4f m = new Matrix4f();
        m.setElement(0, 0, x_scale);
        m.setElement(1, 1, y_scale);
        m.setElement(2, 2, -((farPlane + nearPlane) / frustrumLength));
        m.setElement(2, 3, -1);
        m.setElement(3, 2, -((2f * nearPlane * farPlane) / frustrumLength));
        m.setElement(3, 3, 0);
        return m;
    }

    /**
     * This Method creates a Matrix with your given rows
     * @param rows
     * the content of the Matrix as a list of 4D-Vectors
     * @return
     * The Matrix
     */
    public static Matrix4f createWithRows(List<Vector4f> rows){
        Matrix4f m = new Matrix4f();
        for (int i = 0; i<4;i++){
            m.setRow(rows.get(i), i);
        }
        return m;
    }


    /**
     * This Method creates a Matrix with your given columns as content
     * @param columns
     * the content of the matrix as a list of Vector4f
     * @return
     * The Matrix
     */
    public static Matrix4f createWithColumns(List<Vector4f> columns){
        Matrix4f m = new Matrix4f();
        for (int i = 0;i<4;i++){
            m.setColumn(columns.get(i),i);
        }
        return m;
    }


    /**
     * Sets the Matrixes elemts to the given parameter
     * @param elements
     * the new elements of the Matrix (hast to be size 16)
     */
    public void setElements(float[] elements){
        this.elements = elements;
    }

    /**
     * @return
     * simply returns the elements
     */
    public float[] getElements(){
        return elements;
    }


    /**
     * returns an element at the coordinates of the parameters
     * @param row
     * y-coordinate of the element
     * @param column
     * x-coordinate of the element
     * @return
     * the element!
     */
    public float getElement(int row, int column){
        return elements[row * 4 + column];
    }


    /**
     * This Method was created for setting a singleelement of the matrix without having to calculate its poisition in the array
     * @param row
     * the y-coordinate of the elements position in the matrix
     * @param column
     * the x-coordinate of the elements position in the matrix
     * @param element
     * the value of the element
     */
    public void setElement(int row, int column, float element){
        elements[row * 4 + column] = element;
    }
    //The same Method just with a double which casts it to a float inside
    public void setElement(int row, int column, double element){
        setElement(row, column, (float) element);
    }


    /**
     * This method is for getting a full row of the matrix in form of a 4D-Vector
     * @param row
     * the y-coordinate of the row that you want to get
     * @return
     * returns the row as a Vector4f
     */
    public Vector4f getRow(int row){
        return new Vector4f(getElement(row, 0), getElement(row, 1),
                getElement(row, 2), getElement(row,3));
    }


    /**
     * This method is for getting a full column of the matrix in form of a 4D-vector
     * @param column
     * the x-coordinate of the column
     * @return
     * returns the column as Vector4f
     */
    public Vector4f getColumn(int column){
        return new Vector4f(getElement(0, column), getElement(1, column),
                getElement(2, column), getElement(3, column));
    }


    /**
     * This Method can set a full row of the matrix
     * @param content
     * The content that you want to set
     * @param row
     * the number of the row you want to override (y-position)
     */
    public void setRow(Vector4f content, int row){
        setElement(row, 0, content.getX());
        setElement(row, 1, content.getY());
        setElement(row, 2, content.getZ());
        setElement(row, 3, content.getW());
    }


    /**
     * This Method can set a full column of the Matrix
     * @param content
     * The content that you want to set
     * @param column
     * the place of the column in the matrix (x-Position)
     */
    public void setColumn(Vector4f content, int column){
        setElement(0, column, content.getX());
        setElement(1, column, content.getY());
        setElement(2, column, content.getZ());
        setElement(3, column, content.getW());
    }


    /**
     * This Method creates a third Matrix by adding the two matrices together component for component
     * @param m
     * the second Matrix (remember the first is the one which calls this method)
     * @return
     * returns the result of the addition as a third matrix
     */
    public Matrix4f add(Matrix4f m){
        List<Vector4f> rows = new ArrayList<>();
        for (int i = 0;i<4;i++){
            rows.add(this.getRow(i).add(m.getRow(i)));
        }
        return Matrix4f.createWithRows(rows);
    }


    /**
     * This Method subtracts two matrices (a-b) (b is the parameter)
     * the subtraction is component by component
     * @param m
     * the matrix b
     * @return
     * returns the result of the subtraction as the matrix c (a-b =c) (a.subtract(b) returns c)
     */
    public Matrix4f subtract(Matrix4f m){
        List<Vector4f> rows = new ArrayList<>();
        for (int i=0;i<4;i++){
            rows.add(getRow(i).subtract(m.getRow(i)));
        }
        return Matrix4f.createWithRows(rows);
    }


    /**
     * This Methods multiplies two matrices (a*b = c)
     * @param m
     * The Matrix b;
     * @return
     * The Matrix c
     */
    public Matrix4f multiply(Matrix4f m){
        List<Float> elements = new ArrayList<>();
        for (int row = 0;row<4;row++){
            for (int column = 0;column<4;column++){
                elements.add(getRow(row).dot(m.getColumn(column)));
            }
        }
        float[] e = new float[16];
        for (int i = 0;i<e.length;i++){
            e[i] = elements.get(i);
        }
        Matrix4f out = new Matrix4f();
        out.setElements(e);
        return out;
    }


    /**
     * Simple Method to get the matrixes content as floatBuffer (needed to lod up to any shader)
     * The order is important!!!!
     * @return
     * returns a float buffer holding the matrixes content
     */
    public FloatBuffer toFloatBuffer(){
        float[] e = new float[16];
        List<Float> eL = new ArrayList<>();

        for (int i = 0;i<4;i++){
            Vector4f v = getColumn(i);
            eL.add(v.getX());
            eL.add(v.getY());
            eL.add(v.getZ());
            eL.add(v.getW());
        }

        for (int i = 0;i<eL.size();i++){
            e[i] = eL.get(i);
        }

        return BufferTools.createFloatBuffer(e);
    }









}
