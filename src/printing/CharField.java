package printing;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A 2D field containing characters.
 */
public class CharField {

    /**
     * Mapping of each character to a coordinate
     */
    public Map<Short2DPoint,Character> map = new LinkedHashMap<>();
    
    /**
     * Create an empty CharField
     */
    public CharField() {}
    
    /**
     * Read the contents of a character array into the CharField
     * @param init the array to load
     */
    public void readArray(Character[][] init) {
        //start at 0
        short x = 0;
        //iterate through rows
        for (Character[] row : init) {
            //restart at 0
            short y = 0;
            //iterate through columns/cells
            for (Character c : row) {
                //set the coordinate
                this.set(x,y,c);
                //increment y-position
                y++;
            }
            //increment x-position
            x++;
        }
    }
    
    /**
     * Get the character at a certain point.
     * If the field is empty a space is returned.
     * @param p the coordinate to check
     * @return the character
     */
    public char get(Short2DPoint p) {
        //get the character mapped to the coordinate
        return map.getOrDefault(p,' ');
    }
    
    /**
     * Get the character at a certain point in 2D space.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @return the character or space if empty
     */
    public char get(short x, short y) {
        //create a coordinate point and get
        return get(new Short2DPoint(x,y));
    }
    
    /**
     * Get the character at a certain point in 2D space.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @return the character or space if empty
     */
    public char get(int x, int y) {
        //create a coordinate point and get
        return get(new Short2DPoint(x,y));
    }
    
    /**
     * Set the character at a certain point.
     * @param p the coordinates to set
     * @param c the character to put there
     */
    public void set(Short2DPoint p, char c) {
        //put a character at a point
        map.put(p, c);
    }

    /**
     * Set the character at a certain point.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param c the character to put there
     */
    public void set(short x, short y, char c) {
        //create a coordinate point and set
        set(new Short2DPoint(x,y), c);
    }
    
    /**
     * Set the character at a certain point.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param c the character to put there
     */
    public void set(int x, int y, char c) {
        set((short) x,(short) y,c);
    }
    
    /**
     * Remove all characters from the CharField
     */
    public void clear() {
        map.clear();
    }
    
    /**
     * turn the CharField into a printable String
     * @param x the first x coordinate to convert
     * @param y the first y coordinate to convert
     * @param width the amount of x fields to convert
     * @param height the amount of y fields to convert
     * @return a printable String representation of the selected contents
     */
    public String toString(short x, short y, short width, short height) {
        //initialize the string
        String str = "";
        //iterate through rows
        for (short yp = y; yp < y+height; yp++) {
            //iterate through columns
            for (short xp = x; xp < x+width; xp++) {
                //add the field
                str = str + get(xp,yp);
            }
            //end the row
            str = str + "\n";
        }
        //return the string?
        return str;
    }

    public String toString(int x, int y, int width, int height) {
        return toString((short) x,(short) y,(short) width,(short) height);
    }
    
    /**
     * turn the CharField into a printable String
     * @param origin the first coordinates to convert
     * @param size the amount of fields to convert
     * @param scale the amount of space to use for each field
     * @return a printable String representation of the selected contents
     */
    public String toString(Short2DPoint origin, Short2DPoint size, Short2DPoint scale) {
        //initialize the return string
        String str = "";
        //calculate maximal coordinates to scan
        int maxy = origin.y+size.y*scale.y;
        int maxx = origin.x+size.x*scale.x;
        //iterate through rows
        for (short yp = origin.y; yp < maxy; yp++) {
            //iterate through columns
            for (short xp = origin.x; xp < maxx; xp++) {
                if (xp%scale.x==0 && yp%scale.y==0)
                    //if it's a field to scan add it
                    str += get(xp/scale.x,yp/scale.y);
                else
                    //if it's a scaling field add a space
                    str += " ";
            }
            //end the row
            str += "\n";
        }
        //return the field
        return str;
    }
}
