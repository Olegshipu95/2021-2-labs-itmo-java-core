package collections;

import java.util.Date;
import java.util.HashSet;

public class IdCollection {
    public static HashSet<Integer> idCollection = new HashSet();

    public static Integer createId() {
        int id;
        do {
            Date md = new Date();
            id = (int)(md.getTime() / 100000L);
        } while(idCollection.contains(id));

        return id;
    }
}
