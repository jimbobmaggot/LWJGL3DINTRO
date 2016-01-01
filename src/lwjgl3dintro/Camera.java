package lwjgl3dintro;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

/**
 *
 * @author Stephen Rumpel
 */
public class Camera
{

    //Location
    public float x;
    public float y;
    public float z;
    //Rotation
    public float rx;
    public float ry;
    public float rz;
    //Field of View
    public float fov;
    //Aspect Ratio
    public float aspect;
    //Clipping Planes
    public float near;
    public float far;

    public Camera(float fov, float aspect, float near, float far)
    {
        x = 0;
        y = 0;
        z = 0;
        rx = 0;
        ry = 0;
        rz = 0;

        this.fov = fov;
        this.aspect = aspect;
        this.near = near;
        this.far = far;
        initProjection();
    }

    private void initProjection()
    {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        gluPerspective(getFov(), getAspect(), getNear(), getFar());
        glMatrixMode(GL_MODELVIEW);

        glEnable(GL_DEPTH_TEST);
    }

    public void useView()
    {
        glRotatef(getRx(), 1, 0, 0);
        glRotatef(getRy(), 0, 1, 0);
        glRotatef(getRz(), 0, 0, 1);
        glTranslatef(getX(), getY(), getZ());
    }

    /**
     * @return the x
     */
    public float getX()
    {
        return x;
    }

    /**
     * @return the y
     */
    public float getY()
    {
        return y;
    }

    /**
     * @return the z
     */
    public float getZ()
    {
        return z;
    }

    /**
     * @return the rx
     */
    public float getRx()
    {
        return rx;
    }

    /**
     * @return the ry
     */
    public float getRy()
    {
        return ry;
    }

    /**
     * @return the rz
     */
    public float getRz()
    {
        return rz;
    }

    /**
     * @return the fov
     */
    public float getFov()
    {
        return fov;
    }

    /**
     * @return the aspect
     */
    public float getAspect()
    {
        return aspect;
    }

    /**
     * @return the near
     */
    public float getNear()
    {
        return near;
    }

    /**
     * @return the far
     */
    public float getFar()
    {
        return far;
    }

    /**
     * @param x the x to set
     */
    public void setX(float x)
    {
        this.x = x;
    }

    /**
     * @param y the y to set
     */
    public void setY(float y)
    {
        this.y = y;
    }

    /**
     * @param z the z to set
     */
    public void setZ(float z)
    {
        this.z = z;
    }

    /**
     * @param rx the rx to set
     */
    public void setRx(float rx)
    {
        this.rx = rx;
    }

    /**
     * @param ry the ry to set
     */
    public void setRy(float ry)
    {
        this.ry = ry;
    }

    /**
     * @param rz the rz to set
     */
    public void setRz(float rz)
    {
        this.rz = rz;
    }

    /**
     * @param fov the fov to set
     */
    public void setFov(float fov)
    {
        this.fov = fov;
    }

    /**
     * @param aspect the aspect to set
     */
    public void setAspect(float aspect)
    {
        this.aspect = aspect;
    }

    /**
     * @param near the near to set
     */
    public void setNear(float near)
    {
        this.near = near;
    }

    /**
     * @param far the far to set
     */
    public void setFar(float far)
    {
        this.far = far;
    }

    public void move(float amt, float dir)
    {
        z += amt * Math.sin(Math.toRadians(ry + 90 * dir));
        x += amt * Math.cos(Math.toRadians(ry + 90 * dir));
    }

    public void rotateY(float amt)
    {
        ry += amt;
    }

}
