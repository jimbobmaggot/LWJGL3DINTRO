package lwjgl3dintro;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

/**
 *
 * @author Stephen Rumpel
 */
public class LWJGL3DINTRO
{

    public static void main(String[] args)
    {
        initDisplay();

        gameLoop();
        cleanUp();
    }

    public static Texture loadTexture(String key)
    {
        try
        {
            return TextureLoader.getTexture("png", new FileInputStream(new File("res/" + key + ".png")));
        }
        catch (IOException ex)
        {
            Logger.getLogger(LWJGL3DINTRO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static void gameLoop()
    {
        Texture wood = loadTexture("wood");

        Camera cam = new Camera(70, (float) Display.getWidth() / (float) Display.getHeight(), 0.3f, 1000);
        float x = 0;

        boolean temp = false;

        while (!Display.isCloseRequested())
        {
            boolean forward = (Keyboard.isKeyDown(Keyboard.KEY_W) || Keyboard.isKeyDown(Keyboard.KEY_UP));
            boolean backward = Keyboard.isKeyDown(Keyboard.KEY_S) || Keyboard.isKeyDown(Keyboard.KEY_DOWN);
            boolean left = Keyboard.isKeyDown(Keyboard.KEY_A);
            boolean right = Keyboard.isKeyDown(Keyboard.KEY_D);

            if (forward)
            {
                cam.move(0.005f, 1);
            }
            if (backward)
            {
                cam.move(-0.005f, 1);
            }
            if (left)
            {
                cam.move(0.005f, 0);
            }
            if (right)
            {
                cam.move(-0.005f, 0);
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_LEFT))
            {
                cam.rotateY(-0.05f);
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
            {
                cam.rotateY(0.05f);
            }
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            glLoadIdentity();
            cam.useView();

            glPushMatrix();
            {
                glColor3f(1.0f, 0.5f, 0f);
                glTranslatef(0, 0, -10);
                glRotatef(x, 1, 1, 0);

                if ((forward && left) || (forward && right) || (backward && left) || (backward && right))
                {
                    temp = true;
                }

                if (temp)
                {
                    glRotatef(45, 0, 1, 0);
                }

                wood.bind();

                glBegin(GL_QUADS);
                {
                    //FrontFace
                    glColor3f(1f, 0f, 0f);
                    glTexCoord2f(0, 0);
                    glVertex3f(-1, -1, 1); //bottomleft
                    glTexCoord2f(0, 1);
                    glVertex3f(-1, 1, 1); //up
                    glTexCoord2f(1, 1);
                    glVertex3f(1, 1, 1); //left
                    glTexCoord2f(1, 0);
                    glVertex3f(1, -1, 1); //down
                    //BackFace
                    glColor3f(0f, 1f, 0f);
                    glTexCoord2f(0, 0);
                    glVertex3f(-1, -1, -1); //bottomleft
                    glTexCoord2f(0, 1);
                    glVertex3f(-1, 1, -1); //up
                    glTexCoord2f(1, 1);
                    glVertex3f(1, 1, -1); //left
                    glTexCoord2f(1, 0);
                    glVertex3f(1, -1, -1); //down
                    //BottomFace
                    glColor3f(0f, 0f, 1f);
                    glTexCoord2f(0, 0);
                    glVertex3f(-1, -1, -1); //bottomleft
                    glTexCoord2f(0, 1);
                    glVertex3f(-1, -1, 1); //up
                    glTexCoord2f(1, 1);
                    glVertex3f(-1, 1, 1); //left
                    glTexCoord2f(1, 0);
                    glVertex3f(-1, 1, -1); //down
                    //TopFace
                    glColor3f(1f, 1f, 0f);
                    glTexCoord2f(0, 0);
                    glVertex3f(1, -1, -1); //bottomleft
                    glTexCoord2f(0, 1);
                    glVertex3f(1, -1, 1); //up
                    glTexCoord2f(1, 1);
                    glVertex3f(1, 1, 1); //left
                    glTexCoord2f(1, 0);
                    glVertex3f(1, 1, -1); //down
                    //LeftFace
                    glColor3f(0f, 1f, 1f);
                    glTexCoord2f(0, 0);
                    glVertex3f(-1, -1, -1); //bottomleft
                    glTexCoord2f(0, 1);
                    glVertex3f(1, -1, -1); //up
                    glTexCoord2f(1, 1);
                    glVertex3f(1, -1, 1); //left
                    glTexCoord2f(1, 0);
                    glVertex3f(-1, -1, 1); //down
                    //RightFace
                    glColor3f(1f, 0f, 1f);
                    glTexCoord2f(0, 0);
                    glVertex3f(-1, 1, -1); //bottomleft
                    glTexCoord2f(0, 1);
                    glVertex3f(1, 1, -1); //up
                    glTexCoord2f(1, 1);
                    glVertex3f(1, 1, 1); //left
                    glTexCoord2f(1, 0);
                    glVertex3f(-1, 1, 1); //down

                }
                glEnd();
            }
            glPopMatrix();
            x += 0.05f;
            Display.update();
        }
    }

    public static void cleanUp()
    {
        Display.destroy();
    }

    public static void initDisplay()
    {
        try
        {
            Display.setDisplayMode(new DisplayMode(800, 600));
            Display.create();
        }
        catch (LWJGLException ex)
        {
            Logger.getLogger(LWJGL3DINTRO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
