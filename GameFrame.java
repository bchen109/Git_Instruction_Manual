/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  TurnListener
 *  World
 */
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import javax.swing.JFrame;

public class GameFrame
extends JFrame {
    private final World world;

    public GameFrame(World w) {
        this.world = w;
    }

    public void init() {
        this.setTitle("Assignment 2a");
        this.setLayout(new GridLayout(this.world.getRowCount(), this.world.getColumnCount()));
        for (int row = 0; row < this.world.getRowCount(); ++row) {
            for (int col = 0; col < this.world.getColumnCount(); ++col) {
                this.add(this.world.getCellAt(row, col));
            }
        }
        this.addMouseListener((MouseListener)new TurnListener(this));
    }

    public void takeTurn() {
        this.world.takeTurn();
        this.repaint();
    }
}
