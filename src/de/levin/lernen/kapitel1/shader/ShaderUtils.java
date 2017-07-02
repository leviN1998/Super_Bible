package de.levin.lernen.kapitel1.shader;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL40;
import org.lwjgl.opengl.GL45;

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
        if( glGetShaderi(vertShader, GL_COMPILE_STATUS) == GL11.GL_FALSE){
            System.out.println(GL20.glGetShaderInfoLog(vertShader, 500));
            System.err.println("Could not compile shader!");
            System.exit(-1);
        }

        //create and Compile Fragment-Shader
        int fragShader = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragShader, fragment);
        glCompileShader(fragShader);
        if( glGetShaderi(fragShader, GL_COMPILE_STATUS) == GL11.GL_FALSE){
            System.out.println(GL20.glGetShaderInfoLog(fragShader, 500));
            System.err.println("Could not compile shader!");
            System.exit(-1);
        }

        //create a Programm and attach the Shaders to it
        int program = glCreateProgram();
        glAttachShader(program, vertShader);
        glAttachShader(program, fragShader);
        glLinkProgram(program);
        glValidateProgram(program);

        //delete shaders as the program has them now
        glDeleteShader(vertShader);
        glDeleteShader(fragShader);

        return program;
    }

    public static int createShader(String source, int type, String debugName){
        int id = glCreateShader(type);
        glShaderSource(id, source);
        glCompileShader(id);
        if( glGetShaderi(id, GL_COMPILE_STATUS) == GL11.GL_FALSE){
            System.out.println(GL20.glGetShaderInfoLog(id, 500));
            System.err.println("Could not compile "+debugName+"!");
            System.exit(-1);
        }
        return id;
    }

    public static int createProgram(String vertPath, String fragPath, String tessellationControlPath, String tessEvaPath){
        String vertex = loadAsString(vertPath);
        String fragment = loadAsString(fragPath);
        String tessellationControlSource = loadAsString(tessellationControlPath);
        String tessEvaSource = loadAsString(tessEvaPath);

        //Create and Compile Vertex-Shader
        int vertShader = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertShader, vertex);
        glCompileShader(vertShader);
        if( glGetShaderi(vertShader, GL_COMPILE_STATUS) == GL11.GL_FALSE){
            System.out.println(GL20.glGetShaderInfoLog(vertShader, 500));
            System.err.println("Could not compile shader!");
            System.exit(-1);
        }

        //create and Compile Fragment-Shader
        int fragShader = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragShader, fragment);
        glCompileShader(fragShader);
        if( glGetShaderi(fragShader, GL_COMPILE_STATUS) == GL11.GL_FALSE){
            System.out.println(GL20.glGetShaderInfoLog(fragShader, 500));
            System.err.println("Could not compile shader!");
            System.exit(-1);
        }

        //Create and Compile Tessellation Control Shader
        int tessControl = glCreateShader(GL40.GL_TESS_CONTROL_SHADER);
        glShaderSource(tessControl, tessellationControlSource);
        glCompileShader(tessControl);
        if( glGetShaderi(tessControl, GL_COMPILE_STATUS) == GL11.GL_FALSE){
            System.out.println(GL20.glGetShaderInfoLog(tessControl, 500));
            System.err.println("Could not compile tc shader!");
            System.exit(-1);
        }

        //Create and Compile Tessellation Evaluation Shader
        int tessEv = glCreateShader(GL40.GL_TESS_EVALUATION_SHADER);
        glShaderSource(tessEv, tessEvaSource);
        glCompileShader(tessEv);
        if( glGetShaderi(tessEv, GL_COMPILE_STATUS) == GL11.GL_FALSE){
            System.out.println(GL20.glGetShaderInfoLog(tessEv, 500));
            System.err.println("Could not compile te shader!");
            System.exit(-1);
        }


        //create a Programm and attach the Shaders to it
        int program = glCreateProgram();
        glAttachShader(program, vertShader);
        glAttachShader(program, tessControl);
        glAttachShader(program, tessEv);
        glAttachShader(program, fragShader);
        glLinkProgram(program);
        glValidateProgram(program);

        //delete shaders as the program has them now
        glDeleteShader(vertShader);
        glDeleteShader(fragShader);
        glDeleteShader(tessControl);
        glDeleteShader(tessEv);

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
