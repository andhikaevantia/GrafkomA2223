import Engine.Object2d;
import Engine.ShaderProgram;
import Engine.Window;
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
    private ArrayList<Object2d> objects
            = new ArrayList<>();
    public void init(){
        window.init();
        GL.createCapabilities();

        //code
        objects.add(new Object2d(
            Arrays.asList(
                //shaderFile lokasi menyesuaikan objectnya
                new ShaderProgram.ShaderModuleData
                ("resources/shaders/scene.vert"
                , GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData
                ("resources/shaders/scene.frag"
                , GL_FRAGMENT_SHADER)
            ),
            new ArrayList<>(
                List.of(
                    new Vector3f(0.0f,0.5f,0.0f),
                    new Vector3f(-0.5f,-0.5f,0.0f),
                    new Vector3f(0.5f,-0.5f,0.0f)
                )
            )
        ));
    }
    public void loop(){
        while (window.isOpen()) {
            window.update();
            glClearColor(0.0f,
                    0.0f, 0.0f,
                    0.0f);
            GL.createCapabilities();

            //code
            for(Object2d object: objects){
                object.draw();
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