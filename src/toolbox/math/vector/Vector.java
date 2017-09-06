package toolbox.math.vector;

import toolbox.math.Vector4f;

/**
 * Created by Levin on 02.09.2017.
 */
public abstract class Vector {

    //This is a Vector template
    private int grade;

    public Vector(int grade){
        this.grade = grade;
    }

    public int getGrade(){
        return grade;
    }

    /*
    public static Vector add(Vector a, Vector b) throws NotTheSameVectorException{
        if (a.grade == b.grade){

            if (a.grade == 1){

                Vector1f va = (Vector1f) a;
                Vector1f vb = (Vector1f) b;
                return va.add(vb);


            }else if (a.grade == 2){

                Vector2f va = (Vector2f) a;
                Vector2f vb = (Vector2f) b;
                return va.add(vb);

            }else if (a.grade == 3){

                Vector3f va = (Vector3f) a;
                Vector3f vb = (Vector3f) b;
                return va.add(vb);

            }else if (a.grade == 4){

                Vector4f va = (Vector4f) a;
                Vector4f vb = (Vector4f) b;
                return va.add(vb);

            }else{
                //TODO: implement!!!!
                System.err.println("Update Vector class");
                System.exit(-1);
            }

        }else {
            throw new NotTheSameVectorException();
        }
        return null;
    }
    */

    public static class NotTheSameVectorException extends Exception {

    }
}
