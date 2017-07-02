package de.levin.lernen;


import de.levin.lernen.glfw.GLFWManager;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

/**
 * Created by levin on 01.07.2017.
 */
public class FirstMain implements Runnable{


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

    }

    public void update(){

    }

    public void render(){

    }

    public void cleanUp(){

    }


    public static void main(String[] args){
        new FirstMain().run();
    }
}
