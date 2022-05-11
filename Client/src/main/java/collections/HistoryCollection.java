package collections;

import java.util.ArrayList;

public class HistoryCollection {
    public static ArrayList<String> historyCollectrion = new ArrayList(14);

    public static void capacity(String history) {
        if (historyCollectrion.size() == 13) {
            historyCollectrion.remove(0);
            historyCollectrion.add(history);
        } else {
            historyCollectrion.add(history);
        }

    }
}