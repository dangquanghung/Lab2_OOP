package BoxOfParticles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class Box {
    static int WIDTH;
    static int HEIGHT;
    private static Box BOX;
    private static Collection<Particle> particles;
    static int[][] positionMatrix;

    public Box(int width, int height, int numParticles) {

        this.WIDTH = width;
        this.HEIGHT = height;
        positionMatrix = new int[height + 1][width + 1];
        particles = new HashSet<>();

        for (int i = 0; i < numParticles; i++) {
            particles.add(new Particle());
        }
        Box.updatePositionMatrix();
    }
    public static List<int[]> allParticlesMove() {
        for (Particle particle : particles) {
            particle.move();
        }
        Box.updatePositionMatrix();
        return Box.getCollidingPosition();
    }

    private static List<int[]> getCollidingPosition() {
        List<int[]> collidePos = new ArrayList<>();
        int numParticlesAdded = 0;
        for (int i = 0; i <= Box.HEIGHT; i++) {
            for (int j = 0; j <= Box.WIDTH; j++) {
                if (Box.positionMatrix[i][j] <= 1) {
                    continue; // Skip because there's no collision here !!!
                }
                numParticlesAdded += Box.positionMatrix[i][j] - 1;
                collidePos.add(new int[]{i, j});
            }
        }
        for (int i = 0; i < numParticlesAdded; i++) {
            particles.add(new Particle());
        }
        Box.updatePositionMatrix();
        return collidePos;
    }

    private static void updatePositionMatrix() {
        for (int[] row : positionMatrix) {
            Arrays.fill(row, 0); // Reset all values in positionMatrix to 0 !
        }
        for (Particle particle : particles) {
            positionMatrix[particle.getY()][particle.getX()] += 1;
        }
    }

    /**
     * Singleton pattern !
     *
     * @param width
     * @param height
     * @param numParticles
     * @return
     * @throws InstantiationException
     */
    public static Box createBox(int width, int height, int numParticles) throws InstantiationException {
        if (BOX == null) {
            BOX = new Box(width, height, numParticles);
            return BOX;
        }
        throw new InstantiationException("Box has already been created !");
    }

    public static Box getBox() throws NullPointerException {
        if (BOX == null) {
            throw new NullPointerException("Cannot get the Box !");
        }
        return BOX;
    }

    public static int getNumParticles() {
        return particles.size();
    }


}
