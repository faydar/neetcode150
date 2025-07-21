package problems.design_twitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Twitter {

    static class Tweet {
        Integer order;
        Integer id;

        public Tweet(int order, int id) {
            this.order = order;
            this.id = id;
        }
    }

    Integer order = 0;
    Map<Integer, List<Tweet>> tweets;
    Map<Integer, Set<Integer>> follows;

    public Twitter() {
        this.tweets = new HashMap<>();
        this.follows = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        this.tweets.computeIfAbsent(userId, k -> new ArrayList<>()).add(new Tweet(order++, tweetId));
    }

    public List<Integer> getNewsFeed(int userId) {
        Set<Integer> followees = new HashSet<>(follows.getOrDefault(userId, new HashSet<>()));
        followees.add(userId);

        List<Tweet> allTweets = followees.stream()
                .flatMap(followeeId -> tweets.getOrDefault(followeeId, new ArrayList<>()).stream())
                .sorted((t1, t2) -> Integer.compare(t2.order, t1.order))
                .limit(10)
                .toList();

        return allTweets.stream().map(t -> t.id).toList();
    }

    public void follow(int followerId, int followeeId) {
        this.follows.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        this.follows.computeIfAbsent(followerId, k -> new HashSet<>()).remove(followeeId);
    }

    public static void main(String[] args) {
        Twitter twitter = new Twitter();
        twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5).
        twitter.getNewsFeed(1); // User 1's news feed should return a list with 1 tweet id -> [5]. return [5]
        twitter.follow(1, 2); // User 1 follows user 2.
        twitter.postTweet(2, 6); // User 2 posts a new tweet (id = 6).
        twitter.getNewsFeed(1); // User 1's news feed should return a list with 2 tweet ids -> [6, 5]. Tweet id
                                // 6 should precede tweet id 5 because it is posted after tweet id 5.
        twitter.unfollow(1, 2); // User 1 unfollows user 2.
        twitter.getNewsFeed(1); // User 1's news feed should return a list with 1 tweet id -> [5], since user 1
                                // is no longer following user 2.
    }
}
