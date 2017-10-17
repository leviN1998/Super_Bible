#version 450 core

layout (location = 0) in vec4 color;

//uniform mat4 translation_Matrix;
//uniform mat4 rotation_Matrix;
//uniform mat4 scale_Matrix;
uniform mat4 transformation_Matrix;
uniform mat4 projection_Matrix;

out VS_OUT
{
    vec4 color;
} vs_out;

void main() {
    const vec4 vertices[3] = vec4[3](vec4(0.25,-0.25,-0.5,1.0),
                                    vec4(-0.25,-0.25,-0.5,1.0),
                                    vec4(0.25,0.25, -0.5,1.0));
    gl_Position = projection_Matrix * vertices[gl_VertexID];

    vs_out.color = color *projection_Matrix * transformation_Matrix;
}
