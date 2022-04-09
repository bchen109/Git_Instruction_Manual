/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  RandomGenerator
 */
import java.util.ArrayList;

class World {
    private int w;
    private int h;
    private Cell[][] cell;

    public World(int width, int height) {
        this.w = width;
        this.h = height;
        this.cell = new Cell[this.w][this.h];
    }

    public int getRowCount() {
        return this.w;
    }

    public int getColumnCount() {
        return this.h;
    }

    public void takeTurn() {
        this.update();
    }

    public void init() {
        RandomGenerator.reset();
        for (int i = 0; i < this.w; ++i) {
            for (int j = 0; j < this.h; ++j) {
                this.cell[i][j] = new Cell(this, i, j);
                int val = RandomGenerator.nextNumber((int)99);
                if (val >= 80) {
                    this.cell[i][j].addLife(new Herbivore(this.cell[i][j]));
                    continue;
                }
                if (val < 50) continue;
                this.cell[i][j].addLife(new Plant(this.cell[i][j]));
            }
        }
    }

    public void update() {
        ArrayList<Lifeform> lives = this.getAllLifeforms();
        for (Lifeform life : lives) {
            if (life.isAlive()) {
                life.move();
            }
            if (life.isAlive()) {
                life.giveBirth();
            }
            if (life.isAlive()) continue;
            life.setPos(null);
        }
    }

    private ArrayList<Lifeform> getAllLifeforms() {
        ArrayList<Lifeform> life = new ArrayList<Lifeform>();
        Lifeform l = null;
        for (int i = 0; i < this.cell.length; ++i) {
            for (int j = 0; j < this.cell[i].length; ++j) {
                l = this.cell[i][j].getLife();
                if (l == null) continue;
                life.add(l);
            }
        }
        return life;
    }

    public Cell getCellAt(int row, int col) {
        if (row >= 0 && row < this.w && col >= 0 && col < this.h) {
            return this.cell[row][col];
        }
        return null;
    }
}
