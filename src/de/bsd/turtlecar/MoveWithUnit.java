package de.bsd.turtlecar;


/**
 * @author Heiko W. Rupp
 */
public class MoveWithUnit {

    Move move;
    int units;

    public MoveWithUnit(Move move, int units) {
        this.move = move;
        this.units = units;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
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
        sb.append("MoveWithUnit");
        sb.append("{move=").append(move);
        sb.append(", units=").append(units);
        sb.append('}');
        return sb.toString();
    }
}
