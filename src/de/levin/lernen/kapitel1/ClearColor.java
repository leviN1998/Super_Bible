package de.levin.lernen.kapitel1;

import de.levin.lernen.FirstMain;
import de.levin.lernen.glfw.GLFWManager;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import toolbox.math.Vector4f;

/**
 * Created by Levin on 02.07.2017.
 */
public class ClearColor implements Runnable{

    Vector4f color;


    @Override
    public void run() {
        GLFWManager.init(1280, 720);
        init();
        GLFWManager.initLoop();
        while (!GLFW.glfwWindowShouldClose(GLFWManager.window)){

            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
            GLFW.glfwSwapBuffers(GLFWManager.window);

            update();
            render();

            GLFW.glfwPollEvents();
        }
        cleanUp();
        GLFWManager.cleanUp();
    }

    public void init(){
        color = new Vector4f();
    }

    public void update(){
        color.setAll(0.1f, (float)Math.sin(System.currentTimeMillis()/1000d), (float)Math.cos(System.currentTimeMillis()/1000d), 1);
    }

    public void render(){
        //GL30.glClearBufferfv(GL11.GL_COLOR, 0, color.toArray());
        GL11.glClearColor(color.x, color.y, color.z, color.w);
    }

    public void cleanUp(){

    }


    public static void main(String[] args){
        new ClearColor().run();
    }

}
