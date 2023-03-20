import Engine.*;
import Engine.Object;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL30.*;

public class Main {
    private Window window =
            new Window
    (800,800,"Hello World");
    private ArrayList<Object> objects
            = new ArrayList<>();
    private ArrayList<Object> objectsRectangle
            = new ArrayList<>();

    private ArrayList<Object> objectsPointsControl
            = new ArrayList<>();

    private MouseInput mouseInput;
    int countDegree = 0;
    public void init(){
        window.init();
        GL.createCapabilities();
        mouseInput = window.getMouseInput();
        //code
//        objects.add(new Object2d(
//            Arrays.asList(
//                //shaderFile lokasi menyesuaikan objectnya
//                new ShaderProgram.ShaderModuleData
//                ("resources/shaders/scene.vert"
//                , GL_VERTEX_SHADER),
//                new ShaderProgram.ShaderModuleData
//                ("resources/shaders/scene.frag"
//                , GL_FRAGMENT_SHADER)
//            ),
//            new ArrayList<>(
//                List.of(
//                    new Vector3f(0.0f,0.5f,0.0f),
//                    new Vector3f(-0.5f,-0.5f,0.0f),
//                    new Vector3f(0.5f,-0.5f,0.0f)
//                )
//            ),
//            new Vector4f(0.0f,1.0f,1.0f,1.0f)
//        ));
//        objects.add(new Object(
//            Arrays.asList(
//                //shaderFile lokasi menyesuaikan objectnya
//                new ShaderProgram.ShaderModuleData
//                ("resources/shaders/" +
//                    "sceneWithVerticesColor.vert"
//                        , GL_VERTEX_SHADER),
//                new ShaderProgram.ShaderModuleData
//                    ("resources/shaders/" +
//                    "sceneWithVerticesColor.frag"
//                            , GL_FRAGMENT_SHADER)
//        ),
//        new ArrayList<>(
//                List.of(
//                    new Vector3f(0.0f,0.5f,0.0f),
//                    new Vector3f(-0.5f,-0.5f,0.0f),
//                    new Vector3f(0.5f,-0.5f,0.0f)
//                )
//            ),
//        new ArrayList<>(
//            List.of(
//                new Vector3f(1.0f,0.0f,0.0f),
//                new Vector3f(0.0f,1.0f,0.0f),
//                new Vector3f(0.0f,0.0f,1.0f)
//            )
//        )
//        ));
//        objectsRectangle.add(new Rectangle(
//            Arrays.asList(
//                //shaderFile lokasi menyesuaikan objectnya
//                new ShaderProgram.ShaderModuleData
//                ("resources/shaders/scene.vert"
//                , GL_VERTEX_SHADER),
//                new ShaderProgram.ShaderModuleData
//                ("resources/shaders/scene.frag"
//                , GL_FRAGMENT_SHADER)
//            ),
//            new ArrayList<>(
//                List.of(
//                    new Vector3f(0.0f,0.0f,0.0f),
//                    new Vector3f(0.5f,0.0f,0.0f),
//                    new Vector3f(0.0f,0.5f,0.0f),
//                    new Vector3f( 0.5f,0.5f,0.0f)
//                )
//            ),
//            new Vector4f(0.0f,1.0f,1.0f,1.0f),
//            Arrays.asList(0,1,2,1,2,3)
//
//        ));
//        objectsPointsControl.add(new Object(
//            Arrays.asList(
//                //shaderFile lokasi menyesuaikan objectnya
//                new ShaderProgram.ShaderModuleData
//                ("resources/shaders/scene.vert"
//                , GL_VERTEX_SHADER),
//                new ShaderProgram.ShaderModuleData
//                ("resources/shaders/scene.frag"
//                , GL_FRAGMENT_SHADER)
//            ),
//            new ArrayList<>(),
//            new Vector4f(0.0f,1.0f,1.0f,1.0f)
//        ));
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f,1.0f,0.0f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.125f,
                0.125f,
                0.125f,
                36,
                18
        ));
//        objects.get(0).translateObject(0.5f,0.0f,0.0f);
        objects.get(0).scaleObject(2f,2f,2f);

        objects.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f,1.0f,0.0f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.125f,
                0.125f,
                0.125f,
                36,
                18
        ));
        objects.get(0).getChildObject().get(0).translateObject(0.25f,0.0f,0.0f);
//        objects.get(0).getChildObject().get(0).setCenterPoint(Arrays.asList(0.25f,0.0f,0.0f));

        objects.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f,1.0f,0.0f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.125f,
                0.125f,
                0.125f,
                36,
                18
        ));
        objects.get(0).getChildObject().get(1).translateObject(0.5f,0.0f,0.0f);
//        objects.get(0).getChildObject().get(1).setCenterPoint(Arrays.asList(0.5f,0.0f,0.0f));

        objects.get(0).getChildObject().get(1).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f,1.0f,0.0f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.125f,
                0.125f,
                0.125f,
                36,
                18
        ));
        objects.get(0).getChildObject().get(1).getChildObject().get(0).scaleObject(0.5f,0.5f,0.5f);
        objects.get(0).getChildObject().get(1).getChildObject().get(0).translateObject(0.5f,-0.1f,0.0f);
//        objects.get(0).getChildObject().get(1).getChildObject().get(0).setCenterPoint(Arrays.asList(0.5f,-0.1f,0.0f));
    }
    public void input(){
        if (window.isKeyPressed(GLFW_KEY_W)) {
            countDegree++;
            //rotasi terhadap matahari
            objects.get(0).rotateObject((float) Math.toRadians(0.5f),0.0f,0.0f,1.0f);
            for(Object child:objects.get(0).getChildObject()){
                List<Float> temp = new ArrayList<>(child.getCenterPoint());
                //rotasi terhadap sumbu masing-masing planet
                child.translateObject(temp.get(0)*-1,temp.get(1)*-1,temp.get(2)*-1);
                child.rotateObject((float) Math.toRadians(0.5f),0.0f,0.0f,1.0f);
                child.translateObject(temp.get(0)*1,temp.get(1)*1,temp.get(2)*1);
                for(Object y:objects.get(0).getChildObject().get(1).getChildObject()){
                    //rotasi terhadap bumi
                    List<Float> temp1 = new ArrayList<>(objects.get(0).getChildObject().get(1).getCenterPoint());
                    y.translateObject(temp1.get(0)*-1,temp1.get(1)*-1,temp1.get(2)*-1);
                    y.rotateObject((float) Math.toRadians(0.5f),0.0f,0.0f,1.0f);
                    y.translateObject(temp1.get(0)*1,temp1.get(1)*1,temp1.get(2)*1);
                    //rotasi terhadap sumbunya sendiri
                    temp1 = new ArrayList<>(objects.get(0).getChildObject().get(1).getChildObject().get(0).getCenterPoint());
                    y.translateObject(temp1.get(0)*-1,temp1.get(1)*-1,temp1.get(2)*-1);
                    y.rotateObject((float) Math.toRadians(0.5f),0.0f,0.0f,1.0f);
                    y.translateObject(temp1.get(0)*1,temp1.get(1)*1,temp1.get(2)*1);
                }
                child.rotateObject((float) Math.toRadians(0.5f),0.0f,0.0f,1.0f);
            }
        }
        if(mouseInput.isLeftButtonPressed()){
            Vector2f pos = mouseInput.getCurrentPos();
//            System.out.println("x : "+pos.x+" y : "+pos.y);
            pos.x = (pos.x - (window.getWidth())/2.0f) /
                    (window.getWidth()/2.0f);
            pos.y = (pos.y - (window.getHeight())/2.0f) /
                    (-window.getHeight()/2.0f);
            //System.out.println("x : "+pos.x+" y : "+pos.y);

            if((!(pos.x > 1 || pos.x < -0.97) && !(pos.y > 0.97 || pos.y < -1))){
                System.out.println("x : "+pos.x+" y : "+pos.y);
//                objectsPointsControl.get(0).addVertices(new Vector3f(pos.x,pos.y,0));
            }
        }

    }
    public void loop(){
        while (window.isOpen()) {
            window.update();
            glClearColor(0.0f,
                    0.0f, 0.0f,
                    0.0f);
            GL.createCapabilities();
            input();

            //code
            for(Object object: objects){
                object.draw();
            }
            for(Object object: objectsRectangle){
                object.draw();
            }
            for(Object object: objectsPointsControl){
                object.drawLine();
            }

            // Restore state
            glDisableVertexAttribArray(0);

            // Poll for window events.
            // The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }
    public void run() {

        init();
        loop();

        // Terminate GLFW and
        // free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }
    public static void main(String[] args) {
        new Main().run();
    }
}