package BoxOfParticles;

import java.util.Random;

public class Particle {
    private int x;
    private int y;
    private int dx;
    private int dy;
    
    private enum Direction{
        N, NE, E, SE, S, SW, W, NW
    }
    private static final Direction[] DIRECTIONS = Direction.values();
    private static final Random RANDOM = new Random();

    public Particle(){
        this.x = RANDOM.nextInt(Box.WIDTH + 1);
        this.y = RANDOM.nextInt(Box.HEIGHT + 1);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void move() {
        Direction dir = DIRECTIONS[RANDOM.nextInt(8)];
        switch (dir) {
            case N:
                dy = y - 1;
                break;
            case NE:
                dx = x + 1;
                dy = y - 1;
                break;
            case E:
                dx = x + 1;
                break;
            case SE:
                dx = x + 1;
                dy = y + 1;
                break;
            case S:
                dy = y + 1;
                break;
            case SW:
                dx = x - 1;
                dy = y + 1;
                break;
            case W:
                dx = x - 1;
                break;
            case NW:
                dx = x - 1;
                dy = y - 1;
                break;
            default:
                break;
        }
        if (dx < 0 || dy < 0 || dx > Box.WIDTH || dy > Box.HEIGHT) {
            this.move();

        }
        this.x = dx;
        this.y = dy;
    }


}
