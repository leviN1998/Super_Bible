package toolbox.other;

import toolbox.math.Vector4f;
import toolbox.math.matrix.Matrix4f;
import toolbox.math.matrix.MatrixErrors;
import toolbox.math.matrix.MatrixTemplate;
import toolbox.math.vector.Vector1f;

/**
 * Created by Levin on 03.09.2017.
 */
public class Testing {
    public static void main(String[] args){
        Matrix4f a = new Matrix4f();
        Matrix4f b = new Matrix4f();
        float[] ea = {
                1,5,3,9,
                2,7,8,11,
                3,1,6,8,
                2,1,4,9
        };
        float[] eb = {
                9,2,4,1,
                8,3,1,6,
                11,8,2,7,
                9,1,3,5
        };

        a.setElements(ea);
        b.setElements(eb);


        Matrix4f c = a.multiply(b);

        for (int i = 0; i<4;i++){
            c.getRow(i).print();
        }


    }
}
