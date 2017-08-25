package de.levin.lernen.kapitel1.shader;

import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL32;
import org.lwjgl.opengl.GL40;

import static org.lwjgl.opengl.GL20.*;

/**
 * Created by Levin on 02.07.2017.
 */
public class ShaderProgram {

    private int programID;
    private boolean enabled;
    private boolean hasControl;
    private boolean hasEvaluation;
    private boolean hasGeometry;

    public ShaderProgram(String vertexPath, String controlPath, String evaluationPath, String geometryPath, String fragmentPath){
        String vertSource = ShaderUtils.loadAsString(vertexPath);
        String fragSource = ShaderUtils.loadAsString(fragmentPath);
        String controlSource = "";
        String evaluationSouce = "";
        String geometrySource = "";
        hasControl = false;
        hasEvaluation = false;
        hasGeometry = false;

        if (controlPath != null){
            controlSource = ShaderUtils.loadAsString(controlPath);
            hasControl = true;
        }
        if (evaluationPath != null){
            evaluationSouce = ShaderUtils.loadAsString(evaluationPath);
            hasEvaluation = true;
        }
        if (geometryPath != null){
            geometrySource = ShaderUtils.loadAsString(geometryPath);
            hasGeometry = true;
        }

        int vert = ShaderUtils.createShader(vertSource, GL20.GL_VERTEX_SHADER, "Vertex-Shader");
        int tcs = 0;
        int tes = 0;
        int geo = 0;
        if (hasControl) tcs = ShaderUtils.createShader(controlSource, GL40.GL_TESS_CONTROL_SHADER, "Control-Shader");
        if (hasEvaluation) tes = ShaderUtils.createShader(evaluationSouce, GL40.GL_TESS_EVALUATION_SHADER, "Eval-Shader");
        if (hasGeometry) geo = ShaderUtils.createShader(geometrySource, GL32.GL_GEOMETRY_SHADER, "Geo-Shader");
        int frag = ShaderUtils.createShader(fragSource, GL20.GL_FRAGMENT_SHADER, "Fragment-Shader");

        initProgram(vert,tcs,tes,geo,frag );
        enabled = false;
    }

    public static ShaderProgram basic(String vertexPath, String  fragmentpath){
        return new ShaderProgram(vertexPath, null, null, null, fragmentpath);
    }

    private void initProgram(int vert, int tcs, int tes, int geo, int frag){
        int program = glCreateProgram();
        glAttachShader(program, vert);
        if (hasControl) glAttachShader(program, tcs);
        if (hasEvaluation) glAttachShader(program, tes);
        if (hasGeometry) glAttachShader(program, geo);
        glAttachShader(program, frag);
        glLinkProgram(program);
        glValidateProgram(program);
        programID = program;

        GL20.glDeleteShader(vert);
        if (hasControl) GL20.glDeleteShader(tcs);
        if (hasEvaluation) GL20.glDeleteShader(tes);
        if (hasGeometry) GL20.glDeleteShader(geo);
        GL20.glDeleteShader(frag);
    }

    public void enable(){
        if (!enabled) GL20.glUseProgram(programID);
        enabled = true;
    }

    public void disable(){
        if (enabled) GL20.glUseProgram(0);
        enabled = false;
    }

    public void cleanUp(){
        GL20.glDeleteProgram(programID);
    }

}
