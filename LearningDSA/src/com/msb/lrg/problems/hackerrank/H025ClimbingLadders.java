package com.msb.lrg.problems.hackerrank;

import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/*

	An arcade game player wants to climb to the top of the leaderboard and track their ranking. The game 
	uses Dense Ranking, so its leaderboard works like this:
		The player with the highest score is ranked number  on the leaderboard.
		Players who have equal scores receive the same ranking number, and the next player(s) receive the 
		immediately following ranking number.
	Example
		The ranked players will have ranks , , , and , respectively. If the player's scores are ,  and , their rankings after each game are ,  and . Return .
	Function Description
		Complete the climbingLeaderboard function in the editor below.
		climbingLeaderboard has the following parameter(s):
			int ranked[n]: the leaderboard scores
			int player[m]: the player's scores
		Returns
			int[m]: the player's rank after each new score
	Input Format
		The first line contains an integer , the number of players on the leaderboard.
		The next line contains  space-separated integers , the leaderboard scores in decreasing order.
		The next line contains an integer, , the number games the player plays.
		The last line contains  space-separated integers , the game scores.
	Sample Input 1
	CopyDownload
	Array: ranked
		100
		100
		50
		40
		40
		20
		10
	Array: player
		5
		25
		50
		120
	Sample Output 1
		6
		4
		2
		1

 */
public class H025ClimbingLadders {

	public static void main(String[] args) {
		List<Integer> ranked = Arrays.asList(100,100,50,40,40,20,10);
		List<Integer> player = Arrays.asList(5,25,50,120);
		List<Integer> res = climbingLeaderboard(ranked, player);
		System.out.println(res);
	}

    public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
    	List<Integer> unique = new ArrayList<>();
    	unique.add(ranked.get(0));
    	
    	for(int i = 1; i < ranked.size(); i++) {
    		if(ranked.get(i) != ranked.get(i - 1))
    			unique.add(ranked.get(i));
    	}
    	
    	int i = unique.size() - 1;
    	List<Integer> res = new ArrayList<>();
    	for(int score : player) {
    		while(i >= 0 && score >= unique.get(i)) {
    			i--;
    		}
    		res.add(i + 2);
    	}
    	return res;
    }

    public static List<Integer> climbingLeaderboard3(List<Integer> ranked, List<Integer> player) {

        // Step 1: Build TreeMap (score → rank)
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int rank = 1;
        for (int score : ranked) {
            if (!map.containsKey(score)) {
                map.put(score, rank++);
            }
        }

        List<Integer> result = new ArrayList<>();
        // Step 2: Process Alice scores
        for (int score : player) {
            Integer key = map.floorKey(score);
            if (key != null) {
                result.add(map.get(key));
            } else {
                // smaller than all → last rank
                result.add(map.size() + 1);
            }
        }

        return result;
    }
	
    public static List<Integer> climbingLeaderboard2(List<Integer> ranked, List<Integer> player) {
        
        TreeMap<Integer, Integer> map = new TreeMap<>(Collections.reverseOrder());
        int rank = 1;
        for (int score : ranked) {
            if (!map.containsKey(score)) {
                map.put(score, rank++);
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int score : player) {
            Integer key = map.ceilingKey(score);

            if (key != null) {
                result.add(map.get(key));
            } else {
                result.add(map.size() + 1);
            }
        }

        return result;
    }
	
}
