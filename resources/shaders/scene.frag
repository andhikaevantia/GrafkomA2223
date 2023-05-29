#version 330
//Directional Light
 struct DirLight{
    vec3 direction;

     vec3 ambient;
     vec3 diffuse;
     vec3 specular;
};
uniform DirLight dirLight;
out vec4 fragColor;
uniform vec4 uni_color;

uniform vec3 viewPos;

in vec3 Normal;
in vec3 FragPos;

vec3 CalcDirLight(DirLight light, vec3 normal, vec3 viewDir){
    vec3 lightDir = normalize(-light.direction);

    //diffuse
    float diff = max(dot(normal,lightDir),0.0);
    //specular
    vec3 reflectDir = reflect(-lightDir,normal);
    float spec = pow(max(dot(viewDir, reflectDir),0.0),64);

    vec3 ambient = light.ambient;
    vec3 diffuse = light.diffuse * diff;
    vec3 specular = light.specular * spec;
    return(ambient + diffuse + specular);
}
void main()
{
    //properties
    vec3 normal = normalize(Normal);
    vec3 viewDir = normalize(viewPos - FragPos);
    //Directional Light
    vec3 result = CalcDirLight(dirLight,normal,viewDir);


    fragColor = vec4(result * vec3(uni_color),1.0);
}
