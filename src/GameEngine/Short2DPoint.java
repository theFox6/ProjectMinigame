package GameEngine;

public class Short2DPoint {
    public short x;
    public short y;
    
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
    
    public Short2DPoint() {
        this(0,0);
    }
    
    @Override
    public int hashCode() {
        return Short.toUnsignedInt(x) + (Short.toUnsignedInt(y)<<16);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Short2DPoint other = (Short2DPoint) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }
    
}
