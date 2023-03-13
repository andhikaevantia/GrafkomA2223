#version 330
layout (location=0) in vec3 position;
uniform mat4 model;


void main()
{
    //vec4(position,alpha)
    gl_Position = model * vec4(position, 1.0);
}
