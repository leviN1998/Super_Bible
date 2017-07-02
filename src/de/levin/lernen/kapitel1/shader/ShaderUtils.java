package de.levin.lernen.kapitel1.shader;

import java.io.*;
import static org.lwjgl.opengl.GL20.*;

/**
 * Created by Levin on 02.07.2017.
 */
public class ShaderUtils {

    public static int createProgram(String vertexPath, String fragmentPath){
        String vertex = loadAsString(vertexPath);
        String fragment = loadAsString(fragmentPath);

        //Create and Compile Vertex-Shader
        int vertShader = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertShader, vertex);
        glCompileShader(vertShader);

        //create and Compile Fragment-Shader
        int fragShader = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragShader, fragment);
        glCompileShader(fragShader);

        //create a Programm and attach the Shaders to it
        int program = glCreateProgram();
        glAttachShader(program, vertShader);
        glAttachShader(program, fragShader);
        glLinkProgram(program);

        //delete shaders as the program has them now
        glDeleteShader(vertShader);
        glDeleteShader(fragShader);

        return program;
    }

    public static String loadAsString(String file) {
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String buffer = "";
            while ((buffer = reader.readLine()) != null) {
                result.append(buffer + '\n');
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Konnte den Shader nicht finden");
            e.printStackTrace();
        }
        return result.toString();
    }

}
