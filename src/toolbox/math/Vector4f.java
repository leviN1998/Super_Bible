package toolbox.math;

/**
 * Created by Levin on 02.07.2017.
 */
public class Vector4f {

    public float x;
    public float y;
    public float z;
    public float w;

    public Vector4f(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vector4f(){
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.w = 0;
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
}
