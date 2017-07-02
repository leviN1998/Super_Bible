package de.levin.lernen.kapitel3;

import com.sun.prism.ps.Shader;
import de.levin.lernen.kapitel1.AbstractMain;
import de.levin.lernen.kapitel1.shader.ShaderUtils;
import toolbox.math.Vector4f;

import static org.lwjgl.opengl.GL45.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

/**
 * Created by Levin on 02.07.2017.
 */
public class VertexAttributes extends AbstractMain{

    private int shaderProgram;
    private int vao;
    private Vector4f color;
    private Vector4f attrib;
    private Vector4f triangleColor;

    @Override
    protected void init() {
       shaderProgram = ShaderUtils.createProgram("res/kapitel3/shader.vert", "res/kapitel3/shader.frag");
       vao = glCreateVertexArrays();
       glBindVertexArray(vao);
       color = new Vector4f(0,0,0,1);
       attrib = new Vector4f();
       triangleColor = new Vector4f(0,0,1,1);
    }

    @Override
    protected void update() {
        color.x = (float) Math.sin(System.currentTimeMillis()/5000d) * 0.5f +0.5f;
        color.y = (float) Math.cos(System.currentTimeMillis()/5000d) * 0.5f +0.5f;
        color.z = (0);

        attrib.x = (float) Math.sin(System.currentTimeMillis()/1000d) * 0.5f;
        attrib.y = (float) Math.cos(System.currentTimeMillis()/1000d) * 0.6f;
    }

    @Override
    protected void render() {
        glClearColor(color.x,color.y,color.z,color.w);

        glUseProgram(shaderProgram);


        glVertexAttrib4fv(0, attrib.toArray());
        glVertexAttrib4fv(1, triangleColor.toArray());

        glDrawArrays(GL_TRIANGLES, 0, 3);
    }

    @Override
    protected void cleanUp() {
        glDeleteProgram(shaderProgram);
        glDeleteVertexArrays(vao);
    }

    public static void main(String[] args){
        new VertexAttributes().run();
    }
}
