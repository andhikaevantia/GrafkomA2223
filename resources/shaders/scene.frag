#version 330

out vec4 fragColor;
uniform vec4 uni_color;
uniform vec3 lightColor;
uniform vec3 lightPos;
uniform vec3 viewPos;

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

    //specular
    float specularStrength = 0.5;
    vec3 viewDir = normalize(viewPos - FragPos);
    //blinn-phong
    vec3 halfwayDir = normalize(lightDir + viewDir);
    //pow pangkat 2 * 3
    float spec = pow(max(dot(Normal,halfwayDir),0.0),64*3);
//    //original phong
//    vec3 reflectDir = reflect(-lightDir,Normal);
//    float spec = pow(max(dot(viewDir, reflectDir),0.0),64);
    vec3 specular = specularStrength * spec * lightColor;

    vec3 result = (ambient+diffuse+specular) * vec3(uni_color);
    fragColor = vec4(result,1.0);
}
