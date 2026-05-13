package com.msb.lrg.problems.interview;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WordLadder {

	public static void main(String[] args) {
        WordLadder w = new WordLadder();
        System.out.println(w.ladderLength("hit","cog",
            List.of("hot","dot","dog","lot","log","cog")));
    }

    public int ladderLength(String begin,String end,List<String> list){
        Set<String> set=new HashSet<>(list);
        Queue<String> q=new LinkedList<>();
        q.offer(begin);
        int level=1;

        while(!q.isEmpty()){
            for(int size=q.size();size>0;size--){
                String w=q.poll();
                if(w.equals(end)) return level;

                char[] arr=w.toCharArray();
                for(int i=0;i<arr.length;i++){
                    char old=arr[i];
                    for(char c='a';c<='z';c++){
                        arr[i]=c;
                        String next=new String(arr);
                        if(set.remove(next)) q.offer(next);
                    }
                    arr[i]=old;
                }
            }
            level++;
        }
        return 0;
    }

}
