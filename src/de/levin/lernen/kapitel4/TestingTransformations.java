package de.levin.lernen.kapitel4;

import de.levin.lernen.glfw.GLFWManager;
import de.levin.lernen.kapitel1.AbstractMain;
import de.levin.lernen.kapitel1.shader.ShaderProgram;
import de.levin.lernen.kapitel1.shader.ShaderUtils;
import de.levin.lernen.kapitel3.VertexAttributes;
import toolbox.math.Vector4f;
import toolbox.math.matrix.Matrix4f;
import toolbox.math.vector.Vector;
import toolbox.math.vector.Vector3f;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;
import static org.lwjgl.opengl.GL45.glCreateVertexArrays;

/**
 * Created by Levin on 08.09.2017.
 */
public class TestingTransformations extends AbstractMain{

    private ShaderProgram shaderProgram;
    private int vao;
    private Vector4f color;
    private Vector4f triangleColor;

    //private Matrix4f translation;
    //private Matrix4f rotation;
    //private Matrix4f scale;

    private Matrix4f transformation;
    private Matrix4f projection;

    private float rot;

    @Override
    protected void init() {
        shaderProgram = new ShaderProgram("res/kapitel4/shader.vert", null, null, null,
                "res/kapitel4/shader.frag");
        vao = glCreateVertexArrays();
        glBindVertexArray(vao);
        color = new Vector4f(0,0,0,1);
        triangleColor = new Vector4f(0,0,1,1);
        //translation = Matrix4f.identity();
        //rotation = Matrix4f.identity();
        //scale = Matrix4f.identity();
        transformation = Matrix4f.identity();
        projection = Matrix4f.projection(FOV, NEAR_PLANE, FAR_PLANE, GLFWManager.w, GLFWManager.h);

        rot = 0;
    }

    @Override
    protected void update() {
        color.x = (float) Math.sin(System.currentTimeMillis()/5000d) * 0.5f +0.5f;
        color.y = (float) Math.cos(System.currentTimeMillis()/5000d) * 0.5f +0.5f;
        color.z = (0);

        rot += 0.005f;

        //translation = Matrix4f.translation(new Vector3f(0, 0, 0));
        //rotation = Matrix4f.rotation(new Vector3f(0, 0, 0));
        //scale = Matrix4f.scaling(new Vector3f(1,1,1));

        Vector3f translation = new Vector3f(0, 0, 0);
        Vector3f rotation = new Vector3f(0, 0, 0);
        Vector3f scale = new Vector3f(1, 1, 1);

        transformation = Matrix4f.transformation(translation, rotation, scale);
    }

    @Override
    protected void render() {
        glClearColor(color.x,color.y,color.z,color.w);

        shaderProgram.enable();


        glVertexAttrib4fv(0, triangleColor.toArray());
        //glUniformMatrix4fv(shaderProgram.getUniformLocation("translation_Matrix"), false, translation.toFloatBuffer());
        //glUniformMatrix4fv(shaderProgram.getUniformLocation("rotation_Matrix"), false, rotation.toFloatBuffer());
        //glUniformMatrix4fv(shaderProgram.getUniformLocation("scale_Matrix"), false, scale.toFloatBuffer());
        glUniformMatrix4fv(shaderProgram.getUniformLocation("transformation_Matrix"), false, transformation.toFloatBuffer());
        glUniformMatrix4fv(shaderProgram.getUniformLocation("projection_Matrix"), false, projection.toFloatBuffer());

        glDrawArrays(GL_TRIANGLES, 0, 3);
    }

    @Override
    protected void cleanUp() {
        shaderProgram.cleanUp();
        glDeleteVertexArrays(vao);
    }

    public static void main(String[] args){
        new TestingTransformations().run();
    }

}
