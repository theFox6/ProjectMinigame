package GameEngine;

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
        short x = 0;
        for (Character[] row : init) {
            short y = 0;
            for (Character c : row) {
                this.set(x,y,c);
                y++;
            }
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
        return map.getOrDefault(p,' ');
    }
    
    public char get(short x, short y) {
        return get(new Short2DPoint(x,y));
    }
    
    public char get(int x, int y) {
        return get(new Short2DPoint(x,y));
    }
    
    public void set(Short2DPoint p, char c) {
        map.put(p, c);
    }

    public void set(short x, short y, char c) {
        set(new Short2DPoint(x,y), c);
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
            //iterate through collums
            for (short xp = x; xp < x+width; xp++) {
                //add the field
                str = str + get(xp,yp);
            }
            //end the row
            str = str + "\n";
        }
        //retrn the string?
        return str;
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
            //iterate through collums
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
