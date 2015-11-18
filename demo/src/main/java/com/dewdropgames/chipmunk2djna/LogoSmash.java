/* Copyright (c) 2007 Scott Lembcke
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.dewdropgames.chipmunk2djna;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.dewdropgames.chipmunk2djna.structs.CpBody;
import com.dewdropgames.chipmunk2djna.structs.CpShape;
import com.dewdropgames.chipmunk2djna.structs.CpSpace;
import com.dewdropgames.chipmunk2djna.structs.CpVect;

import java.util.Random;

import static com.dewdropgames.chipmunk2djna.CLibrary.CHIPMUNK;
import static com.dewdropgames.chipmunk2djna.CLibrary.CPVZERO_BY_VALUE;

/**
 * https://github.com/slembcke/Chipmunk2D/blob/master/demo/LogoSmash.c
 * http://patorjk.com/gradient-image-generator/
 */
public class LogoSmash extends ScreenAdapter {
    private static final int IMAGE_WIDTH = 188;
    private static final int IMAGE_WIDTH_2 = IMAGE_WIDTH / 2;
    private static final int IMAGE_HEIGHT = 35;
    private static final int IMAGE_HEIGHT_2 = IMAGE_HEIGHT / 2;
    private static final int IMAGE_ROW_LENGTH = 24;

    private static final byte[] IMAGE_BITMAP = {
            15, -16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, -64, 15, 63, -32, -2, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 31, -64, 15, 127, -125, -1, -128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 127, -64, 15, 127, 15, -1, -64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, -1, -64, 15, -2,
            31, -1, -64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, -1, -64, 0, -4, 63, -1, -32, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, -1, -64, 15, -8, 127, -1, -32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            1, -1, -64, 0, -8, -15, -1, -32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, -31, -1, -64, 15, -8, -32,
            -1, -32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, -15, -1, -64, 9, -15, -32, -1, -32, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 31, -15, -1, -64, 0, -15, -32, -1, -32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 63, -7, -1, -64, 9, -29, -32, 127, -61, -16, 63, 15, -61, -1, -8, 31, -16, 15, -8, 126, 7, -31,
            -8, 31, -65, -7, -1, -64, 9, -29, -32, 0, 7, -8, 127, -97, -25, -1, -2, 63, -8, 31, -4, -1, 15, -13,
            -4, 63, -1, -3, -1, -64, 9, -29, -32, 0, 7, -8, 127, -97, -25, -1, -2, 63, -8, 31, -4, -1, 15, -13,
            -2, 63, -1, -3, -1, -64, 9, -29, -32, 0, 7, -8, 127, -97, -25, -1, -1, 63, -4, 63, -4, -1, 15, -13,
            -2, 63, -33, -1, -1, -32, 9, -25, -32, 0, 7, -8, 127, -97, -25, -1, -1, 63, -4, 63, -4, -1, 15, -13,
            -1, 63, -33, -1, -1, -16, 9, -25, -32, 0, 7, -8, 127, -97, -25, -1, -1, 63, -4, 63, -4, -1, 15, -13,
            -1, 63, -49, -1, -1, -8, 9, -57, -32, 0, 7, -8, 127, -97, -25, -8, -1, 63, -2, 127, -4, -1, 15, -13,
            -1, -65, -49, -1, -1, -4, 9, -57, -32, 0, 7, -8, 127, -97, -25, -8, -1, 63, -2, 127, -4, -1, 15, -13,
            -1, -65, -57, -1, -1, -2, 9, -57, -32, 0, 7, -8, 127, -97, -25, -8, -1, 63, -2, 127, -4, -1, 15, -13,
            -1, -1, -57, -1, -1, -1, 9, -57, -32, 0, 7, -1, -1, -97, -25, -8, -1, 63, -1, -1, -4, -1, 15, -13, -1,
            -1, -61, -1, -1, -1, -119, -57, -32, 0, 7, -1, -1, -97, -25, -8, -1, 63, -1, -1, -4, -1, 15, -13, -1,
            -1, -61, -1, -1, -1, -55, -49, -32, 0, 7, -1, -1, -97, -25, -8, -1, 63, -1, -1, -4, -1, 15, -13, -1,
            -1, -63, -1, -1, -1, -23, -49, -32, 127, -57, -1, -1, -97, -25, -1, -1, 63, -1, -1, -4, -1, 15, -13,
            -1, -1, -63, -1, -1, -1, -16, -49, -32, -1, -25, -1, -1, -97, -25, -1, -1, 63, -33, -5, -4, -1, 15,
            -13, -1, -1, -64, -1, -9, -1, -7, -49, -32, -1, -25, -8, 127, -97, -25, -1, -1, 63, -33, -5, -4, -1,
            15, -13, -1, -1, -64, -1, -13, -1, -32, -49, -32, -1, -25, -8, 127, -97, -25, -1, -2, 63, -49, -13,
            -4, -1, 15, -13, -1, -1, -64, 127, -7, -1, -119, -17, -15, -1, -25, -8, 127, -97, -25, -1, -2, 63,
            -49, -13, -4, -1, 15, -13, -3, -1, -64, 127, -8, -2, 15, -17, -1, -1, -25, -8, 127, -97, -25, -1,
            -8, 63, -49, -13, -4, -1, 15, -13, -3, -1, -64, 63, -4, 120, 0, -17, -1, -1, -25, -8, 127, -97, -25,
            -8, 0, 63, -57, -29, -4, -1, 15, -13, -4, -1, -64, 63, -4, 0, 15, -17, -1, -1, -25, -8, 127, -97,
            -25, -8, 0, 63, -57, -29, -4, -1, -1, -13, -4, -1, -64, 31, -2, 0, 0, 103, -1, -1, -57, -8, 127, -97,
            -25, -8, 0, 63, -57, -29, -4, -1, -1, -13, -4, 127, -64, 31, -2, 0, 15, 103, -1, -1, -57, -8, 127,
            -97, -25, -8, 0, 63, -61, -61, -4, 127, -1, -29, -4, 127, -64, 15, -8, 0, 0, 55, -1, -1, -121, -8,
            127, -97, -25, -8, 0, 63, -61, -61, -4, 127, -1, -29, -4, 63, -64, 15, -32, 0, 0, 23, -1, -2, 3, -16,
            63, 15, -61, -16, 0, 31, -127, -127, -8, 31, -1, -127, -8, 31, -128, 7, -128, 0, 0
    };

    private static boolean getPixel(int x, int y) {
        return ((IMAGE_BITMAP[(x >> 3) + y * IMAGE_ROW_LENGTH] >> (~x & 0x7)) & 1) > 0;
    }

    private static final float GRID_SIZE_MULTIPLIER = 3f;

    private static final Color[] PALETTE = new Color[1024];

    static {
        Texture texture;
        texture = new Texture(Gdx.files.internal("palette.png"));
        texture.getTextureData().prepare();
        Pixmap pixmap = texture.getTextureData().consumePixmap();
        for (int i = 0; i < 1024; i++) {
            PALETTE[i] = new Color(pixmap.getPixel(i, 0));
        }
    }

    CpSpace space = CHIPMUNK.cpSpaceNew();


    private final OrthographicCamera cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    private final ShapeRenderer shapeRenderer = new ShapeRenderer();

    private final Array<CpBody> bodies = new Array<>();

    @Override
    public void show() {
        cam.position.set(0f, 0f, 0);
        cam.update();
        shapeRenderer.setProjectionMatrix(cam.combined);

        Random random = new Random();
        space.iterations = 1;
        space.writeField("iterations");
        CHIPMUNK.cpSpaceUseSpatialHash(space, 2.0, 100000);

        for (int y = 0; y < IMAGE_HEIGHT; y++) {
            System.out.println("y = " + y);
            for (int x = 0; x < IMAGE_WIDTH; x++) {
                if (getPixel(x, y)) {
                    double xJitter = random.nextDouble() * 0.05;
                    double yJitter = random.nextDouble() * 0.05;

                    CpShape shape = make_ball(GRID_SIZE_MULTIPLIER * (x - IMAGE_WIDTH_2 + xJitter) - 500, GRID_SIZE_MULTIPLIER * (IMAGE_HEIGHT_2 - y + yJitter));
                    CHIPMUNK.cpSpaceAddBody(space, shape.body);
                    CHIPMUNK.cpSpaceAddShape(space, shape);
                    bodies.add(shape.body);
                }
            }
        }

        CpBody bullet = CHIPMUNK.cpSpaceAddBody(space, CHIPMUNK.cpBodyNew(1e9, Double.POSITIVE_INFINITY));
        bullet.p.x = -1000;
        bullet.p.y = 0;
        bullet.v.x = 400;
        bullet.write();
        bodies.add(bullet);
        CpShape bulletCircle = CHIPMUNK.cpCircleShapeNew(bullet, 8.0, new CpVect.ByValue(0, 0));
        CHIPMUNK.cpSpaceAddShape(space, bulletCircle);
        bulletCircle.e = 0;
        bulletCircle.u = 0;
//        cpShapeSetFilter(shape, NOT_GRABBABLE_FILTER);
        bulletCircle.write();
    }

    @Override
    public void render(float delta) {
//        CHIPMUNK.cpSpaceStep(space, delta);
        CHIPMUNK.cpSpaceStep(space, 1.0/60);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (CpBody body : bodies) {
            body.readField("v");
            shapeRenderer.setColor(PALETTE[(int) (body.v.len() * 3) % PALETTE.length]);
            body.readField("p");
            shapeRenderer.rect((float) body.p.x, (float) body.p.y, GRID_SIZE_MULTIPLIER, GRID_SIZE_MULTIPLIER);
        }
        shapeRenderer.end();
    }

    private CpShape make_ball(double x, double y) {
        CpBody body = CHIPMUNK.cpBodyNew(1.0, Double.POSITIVE_INFINITY);
        body.p.x = x;
        body.p.y = y;
        body.writeField("p");

        CpShape shape = CHIPMUNK.cpCircleShapeNew(body, 0.95, CPVZERO_BY_VALUE);

        shape.e = 0; //elasticity
        shape.writeField("e");
        shape.u = 0; //friction
        shape.writeField("u");

        return shape;
    }

    //TODO free resources
}