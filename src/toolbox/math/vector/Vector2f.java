package toolbox.math.vector;

/**
 * Created by Levin on 17.08.2017.
 */
public class Vector2f extends Vector{

    public float x;
    public float y;

    public Vector2f(float x, float y){
        super(2);
        this.x = x;
        this.y = y;
    }

    public Vector2f add(Vector2f v){
        return new Vector2f(this.getX() + v.getX(), this.getY() + v.getY());
    }

    public float dot(Vector2f v){
        return (this.getX() * v.getX()) + (this.getY() + v.getY());
    }

    public float length(){
        return (float) Math.sqrt((double) ((getX() * getX()) + (getY() * getY())));
    }

    public Vector2f normalize(){
        float l = length();
        return new Vector2f(getX() / l, getY() / l);
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public float[] toArray(){
        float[] out = new float[2];
        out[0] = getX();
        out[1] = getY();
        return out;
    }
}
