#version 330

out vec4 fragColor;
uniform vec4 uni_color;
uniform vec3 lightColor;
uniform vec3 lightPos;

in vec3 Normal;
in vec3 FragPos;
void main()
{
    //ambient
    float ambientStrength = 0.1;
    vec3 ambient = ambientStrength * lightColor;


    //diffuse
    vec3 lightDir = normalize(lightPos - FragPos);
    float diff = max(dot(Normal,lightDir),0.0f);
    vec3 diffuse = diff * lightColor;

    vec3 result = (ambient+diffuse) * vec3(uni_color);
    fragColor = vec4(result,1.0);
}
