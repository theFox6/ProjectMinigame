package GameEngine;

public class Short2DPoint {

    /**
     * the coordinate on the horizontal axis
     */
    public short x;

    /**
     * the coordinate on the vertical axis
     */
    public short y;
    
    /**
     * create a Short2DPoint with the given coordinates
     * @param x the x position
     * @param y the y position
     */
    public Short2DPoint(short x, short y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * create a Short2DPoint with the given coordinates
     * the given ints will be converted to shorts
     * @param x the x position
     * @param y the y position
     */
    public Short2DPoint(int x, int y) {
        this((short) x,(short) y);
    }
    
    /**
     * create a Short2DPoint referencing 0,0
     */
    public Short2DPoint() {
        this(0,0);
    }
    
    /**
     * hash the two short coordinates into an int hash code
     * @return the y coordinate shifted 16 bits to the left plus the x coordinate
     */
    @Override
    public int hashCode() {
        return Short.toUnsignedInt(x) + (Short.toUnsignedInt(y)<<16);
    }

    /**
     * check if two Short2DPoint reference the same coordinates
     * @param obj the object to compare to
     * @return whether the objects are the same
     */
    @Override
    public boolean equals(Object obj) {
        //if we compare a point to itself
        if (this == obj) {
            //it's the same
            return true;
        }
        //it's not null
        if (obj == null) {
            return false;
        }
        //if it's not a Short2DPoint
        if (!getClass().isInstance(obj)) {
            //it's not the same
            return false;
        }
        // convert the objetc into a Short2DPoint
        final Short2DPoint other = (Short2DPoint) obj;
        //if the x coordinate is different
        if (this.x != other.x) {
            //it's not the same
            return false;
        }
        //if the x coordinate is different
        if (this.y != other.y) {
            //it's not the same
            return false;
        }
        //they are the same
        return true;
    }
    
}
