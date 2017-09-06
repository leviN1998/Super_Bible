package toolbox.math.vector;

/**
 * Created by Levin on 17.08.2017.
 */
public class Vector3f extends Vector{

    public float x;
    public float y;
    public float z;

    public Vector3f(float x, float y, float z){
        super(3);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3f add(Vector3f v){
        return new Vector3f(getX() + v.getX(), getY() + v.getY(), getZ() + v.getZ());
    }

    public float dot(Vector3f v){
        return (getX() * v.getX()) + (getY() * v.getY()) * (getZ() * v.getZ());
    }

    public float length(){
        return (float) Math.sqrt((double) dot(this));
    }

    public Vector3f normalize(){
        float l = length();
        return new Vector3f(getX() / l, getY() / l, getZ() / l);
    }

    public Vector3f cross(Vector3f v){
        return new Vector3f((getY() * v.getZ()) - (getZ() * v.getY()),
                (getZ() * v.getX()) * (getX() * v.getZ()),
                (getX() * v.getY()) - (getY() * v.getX()));
    }

    public float getX(){
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public float[] toArray(){
        float[] out = new float[3];
        out[0] = getX();
        out[1] = getY();
        out[2] = getZ();
        return out;
    }


}
