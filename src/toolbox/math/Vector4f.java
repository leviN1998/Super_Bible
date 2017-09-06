package toolbox.math;

import toolbox.math.vector.Vector;

/**
 * Created by Levin on 02.07.2017.
 */
public class Vector4f extends Vector{

    public float x;
    public float y;
    public float z;
    public float w;

    public Vector4f(float x, float y, float z, float w) {
        super(4);
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vector4f(){
        super(4);
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.w = 0;
    }

    public Vector4f add(Vector4f v){
        return new Vector4f(getX() + v.getX(), getY() + v.getY(),
                getZ() + v.getZ(), getW() + v.getW());
    }


    /**
     * A simple method to subtract the vector a and b (a-b) (a is the caller, b is the parameter)
     * subtracts component by component
     * @param v
     * The vector b
     * @return
     * returns vector c, the result of the subtraction
     */
    public Vector4f subtract(Vector4f v){
        return new Vector4f(getX() - v.getX(), getY() - v.getY(),
                getZ() - v.getZ(), getW() - v.getW());
    }


    /**
     * a simple dot product (i = x*x + y*y + z*z + w*w) which is needed for matrix multiplications
     * @param v
     * the vector to do the product with
     * @return
     * the calculated number
     */
    public float dot(Vector4f v){
        return (getX() * v.getX()) + (getY() * v.getY()) +
                (getZ() * v.getZ()) + (getW() * v.getW());
    }

    public void setAll(float x, float y, float z, float w){
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public float[] toArray(){
        float[] out = new float[4];
        out[0] = x;
        out[1] = y;
        out[2] = z;
        out[3] = w;
        return out;
    }

    public void print(){
        System.out.println("Vector: " + x + " , " + y + " , " + z + " , " + w);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public float getW() {
        return w;
    }
}
