public enum MapDirection {
    NORTH,
    SOUTH,
    WEST,
    EAST;


    @Override
    public String toString() {
        String directionPL = "";
        switch (this) {
            case NORTH:
                directionPL = "N";
                break;
            case SOUTH:
                directionPL = "S";
                break;
            case WEST:
                directionPL = "W";
                break;
            case EAST:
                directionPL = "E";
                break;
        }
        return directionPL;
    }

    public MapDirection next() {
        MapDirection nextDirection = null;

        switch (this) {
            case NORTH:
                nextDirection = EAST;
                break;
            case SOUTH:
                nextDirection = WEST;
                break;
            case WEST:
                nextDirection = NORTH;
                break;
            case EAST:
                nextDirection = SOUTH;
                break;
        }

        return nextDirection;
    }

    public MapDirection previous() {
        MapDirection nextDirection = null;

        switch (this) {
            case NORTH:
                nextDirection = WEST;
                break;
            case SOUTH:
                nextDirection = EAST;
                break;
            case WEST:
                nextDirection = SOUTH;
                break;
            case EAST:
                nextDirection = NORTH;
                break;
        }

        return nextDirection;
    }

    public Vector2d toUnitVector() {
        Vector2d unitVector = null;

        switch (this) {
            case NORTH:
                unitVector = new Vector2d(0, 1);
                break;
            case SOUTH:
                unitVector = new Vector2d(0, -1);
                break;
            case WEST:
                unitVector = new Vector2d(-1, 0);
                break;
            case EAST:
                unitVector = new Vector2d(1, 0);
                break;
        }
        return unitVector;
    }
}