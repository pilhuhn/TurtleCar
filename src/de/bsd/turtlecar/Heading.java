package de.bsd.turtlecar;


/**
 * In which direction does the car drive?
 * @author Heiko W. Rupp
 */
public enum Heading {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    public static Heading toLeft(Heading heading) {
        Heading next = null;
        switch (heading) {
        case NORTH:
            next=WEST;
            break;
        case EAST:
            next= NORTH;
            break;
        case SOUTH:
            next=EAST;
            break;
        case WEST:
            next=SOUTH;
            break;
        }
        return next;
    }

    public static Heading toRight(Heading heading) {
        Heading next = null;
        switch (heading) {

        case NORTH:
            next=EAST;
            break;
        case EAST:
            next=SOUTH;
            break;
        case SOUTH:
            next=WEST;
            break;
        case WEST:
            next=NORTH;
            break;
        }
        return next;
    }
}
