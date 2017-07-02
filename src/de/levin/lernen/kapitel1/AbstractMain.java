package de.levin.lernen.kapitel1;

import de.levin.lernen.glfw.GLFWManager;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import toolbox.math.Vector4f;

import static org.lwjgl.opengl.GL11.GL_VERSION;
import static org.lwjgl.opengl.GL11.glGetString;

/**
 * Created by Levin on 02.07.2017.
 */
public abstract class AbstractMain implements Runnable{

    @Override
    public void run() {
        GLFWManager.init(1280, 720);
        GLFWManager.initLoop();
        System.out.println("OpenGL: " + glGetString(GL_VERSION));
        init();
        while (!GLFW.glfwWindowShouldClose(GLFWManager.window)){
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
            GLFW.glfwPollEvents();

            update();
            render();

            GLFW.glfwSwapBuffers(GLFWManager.window);
        }
        cleanUp();
        GLFWManager.cleanUp();
    }

    protected abstract void init();

    protected abstract void update();

    protected abstract void render();

    protected abstract void cleanUp();

}
