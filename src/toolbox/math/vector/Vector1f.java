package toolbox.math.vector;

/**
 * Created by Levin on 02.09.2017.
 */
public class Vector1f extends Vector{

    //basically represents just a float value but is created for
    // more comfort using the matrix classes

    private float x;

    public Vector1f(float x){
        super(1);
        this.x = x;
    }

    public float getX() {
        return x;
    }

    public Vector1f add(Vector1f v){
        return new Vector1f(getX() + v.getX());
    }
}
