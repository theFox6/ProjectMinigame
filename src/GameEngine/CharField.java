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
    
    public void set(Short2DPoint p, char c) {
        map.put(p, c);
    }

    public void set(short x, short y, char c) {
        set(new Short2DPoint(x,y), c);
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
    
    public String toString(Short2DPoint origin, Short2DPoint size, Short2DPoint scale) {
        String str = "";
        int maxy = origin.y+size.y*scale.y;
        int maxx = origin.x+size.x*scale.x;
        for (short yp = origin.y; yp < maxy; yp += scale.y) {
            for (short xp = origin.x; xp < maxx; xp += scale.x) {
                str = str + get(xp,yp);
            }
            str = str + "\n";
        }
        return str;
    }
}
