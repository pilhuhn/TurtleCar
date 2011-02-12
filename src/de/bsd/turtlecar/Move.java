package de.bsd.turtlecar;


/**
 * @author Heiko W. Rupp
 */
public class Move {

    Moves move;
    int units;

    public Move(Moves move, int units) {
        this.move = move;
        this.units = units;
    }

    public Moves getMove() {
        return move;
    }

    public void setMove(Moves move) {
        this.move = move;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Move");
        sb.append("{move=").append(move);
        sb.append(", units=").append(units);
        sb.append('}');
        return sb.toString();
    }
}
