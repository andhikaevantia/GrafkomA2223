package Engine;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Object extends ShaderProgram{

    List<Vector3f> vertices;
    int vao;
    int vbo;
    UniformsMap uniformsMap;
    Vector4f color;

    Matrix4f model;

    int vboColor;

    List<Object> childObject;
    List<Float> centerPoint;

    public List<Object> getChildObject() {
        return childObject;
    }

    public void setChildObject(List<Object> childObject) {
        this.childObject = childObject;
    }

    public List<Float> getCenterPoint() {
        updateCenterPoint();
        return centerPoint;
    }

    public void setCenterPoint(List<Float> centerPoint) {
        this.centerPoint = centerPoint;
    }

    List<Vector3f> verticesColor;
    public Object(List<ShaderModuleData> shaderModuleDataList
            , List<Vector3f> vertices
            , Vector4f color) {
        super(shaderModuleDataList);
        this.vertices = vertices;
        setupVAOVBO();
        uniformsMap = new UniformsMap(getProgramId());
        uniformsMap.createUniform(
                "uni_color");
        uniformsMap.createUniform(
                "model");
        this.color = color;
        model = new Matrix4f().identity();
        childObject = new ArrayList<>();
        centerPoint = Arrays.asList(0f,0f,0f);
    }
    public Object(List<ShaderModuleData> shaderModuleDataList,
                  List<Vector3f> vertices,
                  List<Vector3f> verticesColor) {
        super(shaderModuleDataList);
        this.vertices = vertices;
        this.verticesColor = verticesColor;
        setupVAOVBOWithVerticesColor();
    }
    public void setupVAOVBO(){
        //set vao
        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        //set vbo
        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER,
                Utils.listoFloat(vertices),
                GL_STATIC_DRAW);
    }
    public void setupVAOVBOWithVerticesColor(){
        //set vao
        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        //set vbo
        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER,
                Utils.listoFloat(vertices),
                GL_STATIC_DRAW);

        //set vboColor
        vboColor = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboColor);
        glBufferData(GL_ARRAY_BUFFER,
                Utils.listoFloat(verticesColor),
                GL_STATIC_DRAW);
    }
    public void drawSetup(){
        bind();
        uniformsMap.setUniform(
                "uni_color", color);
        uniformsMap.setUniform(
                "model", model);
        // Bind VBO
        glEnableVertexAttribArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glVertexAttribPointer(0, 3,
                GL_FLOAT,
                false,
                0, 0);

    }
    public void drawSetupWithVerticesColor(){
        bind();
        // Bind VBO
        glEnableVertexAttribArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glVertexAttribPointer(0, 3,
                GL_FLOAT,
                false,
                0, 0);

        // Bind VBOColor
        glEnableVertexAttribArray(1);
        glBindBuffer(GL_ARRAY_BUFFER, vboColor);
        glVertexAttribPointer(1, 3,
                GL_FLOAT,
                false,
                0, 0);
    }
    public void draw(){
        drawSetup();
        // Draw the vertices
        //optional
        glLineWidth(10); //ketebalan garis
        glPointSize(10); //besar kecil vertex
        //wajib
        //GL_LINES
        //GL_LINE_STRIP
        //GL_LINE_LOOP
        //GL_TRIANGLES
        //GL_TRIANGLE_FAN
        //GL_POINT
        glDrawArrays(GL_POLYGON,
                0,
                vertices.size());
        for(Object child:childObject){
            child.draw();
        }
    }
    public void drawWithVerticesColor(){
        drawSetupWithVerticesColor();
        // Draw the vertices
        //optional
        glLineWidth(10); //ketebalan garis
        glPointSize(10); //besar kecil vertex
        //wajib
        //GL_LINES
        //GL_LINE_STRIP
        //GL_LINE_LOOP
        //GL_TRIANGLES
        //GL_TRIANGLE_FAN
        //GL_POINT
        glDrawArrays(GL_TRIANGLES,
                0,
                vertices.size());
    }
    public void drawLine(){
        drawSetup();
        // Draw the vertices
        //optional
        glLineWidth(1); //ketebalan garis
        glPointSize(1); //besar kecil vertex
        glDrawArrays(GL_LINE_STRIP,
                0,
                vertices.size());
    }
    public void addVertices(Vector3f newVertices){
        vertices.add(newVertices);
        setupVAOVBO();
    }
    public void translateObject(Float offsetX,Float offsetY,Float offsetZ){
        model = new Matrix4f().translate(offsetX,offsetY,offsetZ).mul(new Matrix4f(model));
        updateCenterPoint();
        for(Object child:childObject){
            child.translateObject(offsetX,offsetY,offsetZ);
        }
    }
    public void rotateObject(Float degree, Float x,Float y,Float z){
        model = new Matrix4f().rotate(degree,x,y,z).mul(new Matrix4f(model));
        updateCenterPoint();
        for(Object child:childObject){
            child.rotateObject(degree,x,y,z);
        }

    }
    public void updateCenterPoint(){
        Vector3f destTemp = new Vector3f();
        model.transformPosition(0.0f,0.0f,0.0f,destTemp);
        centerPoint.set(0,destTemp.x);
        centerPoint.set(1,destTemp.y);
        centerPoint.set(2,destTemp.z);
        System.out.println(centerPoint.get(0) + " " + centerPoint.get(1));
    }
    public void scaleObject(Float scaleX,Float scaleY,Float scaleZ){
        model = new Matrix4f().scale(scaleX,scaleY,scaleZ).mul(new Matrix4f(model));
        for(Object child:childObject){
            child.translateObject(scaleX,scaleY,scaleZ);
        }
    }

}
