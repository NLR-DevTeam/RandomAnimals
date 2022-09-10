package cn.whitrayhb.randomanimal.util;

import java.util.concurrent.ConcurrentHashMap;

public class Cooler {
    private static final ConcurrentHashSet<Long> set = new ConcurrentHashSet<Long>();
    public static void lock(long id,int second){
        if(second<=0) return;
        set.add(id);
        Thread unlockThread = new Thread(() ->{
            try {
                Thread.sleep(second* 1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            unlock(id);
        });
        unlockThread.start();
    }
    public static void unlock(long id){
        set.remove(id);
    }
    public static boolean isLocked(long id){
        return set.contains(id);
    }
}

class ConcurrentHashSet<T>{
    private final ConcurrentHashMap<T, Integer> map;
    ConcurrentHashSet(){
        map = new ConcurrentHashMap<T,Integer>();
    }
    public void add(T value){
        map.put(value,1);
    }
    public void remove(T value){
        map.remove(value,1);
    }
    public Boolean contains(T value){
        return map.containsKey(value);
    }
    public String mapReturn(){
        return String.valueOf(map);
    }
}

