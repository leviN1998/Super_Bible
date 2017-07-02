package de.levin.lernen.kapitel1;

import de.levin.lernen.kapitel1.shader.ShaderUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.CallbackI;
import toolbox.math.Vector4f;

import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL45.*;
import static org.lwjgl.opengl.GL20.*;


/**
 * Created by Levin on 02.07.2017.
 */
public class FirstShader extends AbstractMain{
    public static void main(String[] args){
        new FirstShader().run();
    }

    private int shaderProgram;
    private int vao;
    private Vector4f color;

    @Override
    protected void init() {
        shaderProgram = ShaderUtils.createProgram("res/kapitel1/first.vert", "res/kapitel1/first.frag");
        vao = glCreateVertexArrays();
        glBindVertexArray(vao);
        color = new Vector4f(0.1f, 0, 0, 1);
    }

    @Override
    protected void update() {
        color.y = (float) Math.sin(System.currentTimeMillis()/100d);
        color.z = (float) Math.cos(System.currentTimeMillis()/100d);
    }

    @Override
    protected void render() {

        GL11.glClearColor(color.x,color.y,color.z,color.w);


    }


    @Override
    protected void cleanUp() {
        glDeleteVertexArrays(vao);
        glDeleteProgram(shaderProgram);
        glDeleteVertexArrays(vao);
    }
}
