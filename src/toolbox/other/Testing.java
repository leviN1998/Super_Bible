package toolbox.other;

import toolbox.math.Vector4f;
import toolbox.math.matrix.Matrix4f;
import toolbox.math.matrix.MatrixErrors;
import toolbox.math.matrix.MatrixTemplate;
import toolbox.math.vector.Vector1f;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Levin on 03.09.2017.
 */
public class Testing {
    public static void main(String[] args){

        Map<String, Integer> map = new HashMap<>();
        map.put("test", 0);
        System.out.println(map.containsKey("hallo"));

    }
}
