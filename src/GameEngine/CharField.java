package GameEngine;

import java.util.LinkedHashMap;
import java.util.Map;

public class CharField {
    public Map<Short2DPoint,Character> map = new LinkedHashMap<>();
    
    public CharField() {}
    public CharField(Character[][] init) {
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
        return map.get(p);
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
}
