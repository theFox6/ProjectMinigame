package GameEngine;

import java.util.LinkedHashMap;
import java.util.Map;

public class CharField {
    public Map<Short2DPoint,Character> map = new LinkedHashMap<>();
    
    public CharField() {}
    
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
    
    public void set(int x, int y, char c) {
        set((short) x,(short) y,c);
    }
    
    public void clear() {
        map.clear();
    }
    
    public String toString(short x, short y, short width, short height) {
        String str = "";
        for (short yp = y; yp < y+height; yp++) {
            for (short xp = x; xp < x+width; xp++) {
                str = str + get(xp,yp);
            }
            str = str + "\n";
        }
        return str;
    }

    public String toString(int x, int y, int width, int height) {
        return toString((short) x,(short) y,(short) width,(short) height);
    }
    
    public String toString(Short2DPoint origin, Short2DPoint size, Short2DPoint scale) {
        String str = "";
        int maxy = origin.y+size.y*scale.y;
        int maxx = origin.x+size.x*scale.x;
        for (short yp = origin.y; yp < maxy; yp++) {
            for (short xp = origin.x; xp < maxx; xp++) {
                if (xp%scale.x==0 && yp%scale.y==0)
                    str += get(xp/scale.x,yp/scale.y);
                else
                    str += " ";
            }
            str += "\n";
        }
        return str;
    }
}
