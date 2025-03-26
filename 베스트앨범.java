import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> musicPlayCounts = new HashMap<>();
        Map<String, Music> firstOrderMusic = new HashMap<>(); 
        Map<String, Music> secondOrderMusic = new HashMap<>(); 
        int len = genres.length;
        
        for(int i = 0; i < len; i++) {
            String curGenre = genres[i];
            Music curMusic = new Music(i, plays[i]);
            
            int totalPlays = 0;
            if(musicPlayCounts.containsKey(curGenre)) {
                totalPlays = musicPlayCounts.get(curGenre);
            }
            musicPlayCounts.put(curGenre, plays[i] + totalPlays);
            
            if(!firstOrderMusic.containsKey(curGenre)) {
                firstOrderMusic.put(curGenre, curMusic);
            } else {
                Music originalMusic = firstOrderMusic.get(curGenre);
                
                if(curMusic.isPrior(originalMusic)) {
                    secondOrderMusic.put(curGenre, originalMusic);
                    firstOrderMusic.put(curGenre, curMusic);
                } else if (!secondOrderMusic.containsKey(curGenre) || curMusic.isPrior(secondOrderMusic.get(curGenre))) {
                    secondOrderMusic.put(curGenre, curMusic);
                }
            }

        }
        
        List<String> keySet = new ArrayList<>(musicPlayCounts.keySet());
        keySet.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return musicPlayCounts.get(o2).compareTo(musicPlayCounts.get(o1));
            }
        });
        
        List<Integer> result = new ArrayList<>();
        for(String key : keySet) {
            result.add(firstOrderMusic.get(key).getIndex());
            if(secondOrderMusic.containsKey(key)) {
                result.add(secondOrderMusic.get(key).getIndex());
            }
        }
        
        return result.stream().mapToInt(i->i).toArray();
    }
    
    class Music {
        private int index;
        private int plays;
        
        public Music(int index, int plays) {
            this.index = index;
            this.plays = plays;
        }
        
        public int getIndex() { return this.index; }
        
        public boolean isPrior(Music comp) {
            return (this.plays > comp.plays) || (this.plays == comp.plays && this.index < comp.getIndex());
        }
    }
}