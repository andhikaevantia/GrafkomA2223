package Engine;

import org.joml.*;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;
import java.util.*;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.system.MemoryStack.stackPush;

public class UniformsMap {
    private final Map<String, Integer> uniforms;

    public UniformsMap(int programId) {
        uniforms = new HashMap<>();

        int uniformCount = glGetProgrami(programId, GL_ACTIVE_UNIFORMS);

        try (MemoryStack stack = stackPush()) {
            IntBuffer tempBuffer = stack.mallocInt(1);
            for (int i = 0; i < uniformCount; i++) {
                String key = glGetActiveUniform(programId, i, glGetProgrami(programId, GL_ACTIVE_UNIFORM_MAX_LENGTH), tempBuffer, tempBuffer);

                int location = glGetUniformLocation(programId, key);

                uniforms.put(key, location);
            }
        }
    }

    private int getUniformLocation(String uniformName) {
        Integer location = uniforms.get(uniformName);
        if (location == null) {
            throw new RuntimeException("Could not find uniform [" + uniformName + "]");
        }
        return location;
    }

    public void setUniform(String uniformName, int value) {
        glUniform1i(getUniformLocation(uniformName), value);
    }

    public void setUniform(String uniformName, Matrix4f value) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            glUniformMatrix4fv(getUniformLocation(uniformName), false, value.get(stack.mallocFloat(16)));
        }
    }

    public void setUniform(String uniformName, Vector4f value) {
        glUniform4f(getUniformLocation(uniformName), value.x, value.y, value.z, value.w);
    }

    public void setUniform(String uniformName, Vector3f value) {
        glUniform3f(getUniformLocation(uniformName), value.x, value.y, value.z);
    }
    public void setUniform(String uniformName, Float value) {
        glUniform1f(getUniformLocation(uniformName), value);
    }
}
