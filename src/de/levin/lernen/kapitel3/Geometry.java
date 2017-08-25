package de.levin.lernen.kapitel3;

import de.levin.lernen.kapitel1.AbstractMain;
import de.levin.lernen.kapitel1.shader.ShaderProgram;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL40;
import org.lwjgl.opengl.GL45;

/**
 * Created by Levin on 24.08.2017.
 */
public class Geometry extends AbstractMain{
    private ShaderProgram shaderProgram;
    private int vao;

    @Override
    protected void init() {
        shaderProgram = new ShaderProgram("res/kapitel3/tessellation.vert", "res/kapitel3/tessellation.tcs",
                "res/kapitel3/tessellation.tes", "res/kapitel3/geometry.geo",
                "res/kapitel3/tessellation.frag");
        vao = GL45.glCreateVertexArrays();
        GL30.glBindVertexArray(vao);
    }

    @Override
    protected void update() {

    }

    @Override
    protected void render() {
        GL11.glClearColor(0,0,1,0);

        shaderProgram.enable();
        GL30.glBindVertexArray(vao);

        GL40.glPatchParameteri(GL40.GL_PATCH_VERTICES, 3);
        GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
        GL11.glPointSize(5f);

        GL11.glDrawArrays(GL40.GL_PATCHES, 0, 3);


    }

    @Override
    protected void cleanUp() {
        shaderProgram.cleanUp();
        GL30.glDeleteVertexArrays(vao);
    }

    public static void main(String[] args){
        new Geometry().run();
    }
}
