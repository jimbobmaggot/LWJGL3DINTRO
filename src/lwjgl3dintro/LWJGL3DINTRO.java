package lwjgl3dintro;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

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

    public static void gameLoop()
    {
        Camera cam = new Camera(70, (float) Display.getWidth() / (float) Display.getHeight(), 0.3f, 1000);
        float x = 0;
        while (!Display.isCloseRequested())
        {
            glClear(GL_COLOR_BUFFER_BIT);
            glLoadIdentity();
            cam.useView();

            glPushMatrix();
            {
                glColor3f(1.0f, 0.5f, 0f);
                glTranslatef(0, 0, -10);
                glRotatef(x, 1, 1, 0);
                glBegin(GL_QUADS);
                {
                    //FrontFace
                    glVertex3f(-1, -1, 1);
                    glVertex3f(-1, 1, 1);
                    glVertex3f(1, 1, 1);
                    glVertex3f(1, -1, 1);
                    //BackFace
                    glVertex3f(-1, -1, -1);
                    glVertex3f(-1, 1, -1);
                    glVertex3f(1, 1, -1);
                    glVertex3f(1, -1, -1);
                    //BottomFace
                    glVertex3f(-1, -1, -1);
                    glVertex3f(-1, -1, 1);
                    glVertex3f(-1, 1, 1);
                    glVertex3f(-1, 1, -1);
                    //TopFace
                    glVertex3f(1, -1, -1);
                    glVertex3f(1, -1, 1);
                    glVertex3f(1, 1, 1);
                    glVertex3f(1, 1, -1);
                    //LeftFace
                    glVertex3f(-1, -1, -1);
                    glVertex3f(1, -1, -1);
                    glVertex3f(1, -1, 1);
                    glVertex3f(-1, -1, 1);
                    //RightFace
                    glVertex3f(-1, 1, -1);
                    glVertex3f(1, 1, -1);
                    glVertex3f(1, 1, 1);
                    glVertex3f(-1, 1, 1);

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
