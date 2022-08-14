package net.maidkleid.weaponapi.utils;

import java.util.HashMap;
import java.util.Map;

public interface LevelMapper {

    LevelMapper DEFAULT_MAPPER = new LevelMapper() {
        private static final HashMap<Integer, Integer> map = new HashMap<>();
        private static final int maxLevel;
                                     //level, minXp
        static {
            map.put(1,0);
            map.put(2,2);
            map.put(3,6);
            map.put(4,16);
            map.put(5,24);
            map.put(6,40);
            map.put(7,70);
            maxLevel = 7;
        }

        @Override
        public int getLevel(int xp) {
            int level = 1;
            for (int i = 2; i <= maxLevel; i++) {
                int minXp = map.get(i);
                if(minXp > xp) break;
                level = i;
            }
            return level;
        }

        @Override
        public int getLowestXpForLevel(int level) {
            return map.get(level);
        }

        @Override
        public int getMaxLevel() {
            return maxLevel;
        }
    };
    int getLevel(int xp);

    int getLowestXpForLevel(int level);

    default int getHighestXpForLevel(int level) {
        return this.getLowestXpForLevel(level + 1)-1;
    }

    int getMaxLevel();


}
