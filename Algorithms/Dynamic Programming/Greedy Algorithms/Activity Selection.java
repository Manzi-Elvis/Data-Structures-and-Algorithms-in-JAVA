/*
  Description:
  The Activity Selection problem is a classic demonstration of the greedy approach.
  Given a set of activities with start and finish times, the goal is to select the
  maximum number of non-overlapping activities.
 
  The key insight:
     Always pick the activity that finishes earliest.
 
  This works because choosing the activity that ends first gives space for as many
  future activities as possible — a perfect example of why greedy algorithms can
  be both simple and powerful.
 
  Use Cases:
  - Scheduling problems
  - Resource allocation
  - Conflict-free event planning
 
  Time Complexity:
  - O(n log n) → for sorting by finish time.
 
  Space Complexity:
  - O(1) → if sorting in-place.
 
  Approach:
  1. Sort all activities by their finishing times.
  2. Select the first activity (it finishes the earliest).
  3. Then, always select the next activity whose start time is >= the finish time
     of the previously selected one.
 */

import java.util.*;

public class ActivitySelection {

    // A simple class to represent an activity
    public static class Activity {
        int start;
        int end;

        public Activity(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    // Function to select maximum number of non-overlapping activities
    public static List<Activity> selectActivities(List<Activity> activities) {

        // Sort by finish time
        activities.sort(Comparator.comparingInt(a -> a.end));

        List<Activity> result = new ArrayList<>();

        // Always pick the first activity
        if (!activities.isEmpty()) {
            result.add(activities.get(0));
        }

        int lastEndTime = result.get(0).end;

        // Pick the next non-overlapping activities
        for (int i = 1; i < activities.size(); i++) {
            if (activities.get(i).start >= lastEndTime) {
                result.add(activities.get(i));
                lastEndTime = activities.get(i).end;
            }
        }

        return result;
    }

    // Demonstration
    public static void main(String[] args) {
        List<Activity> activities = Arrays.asList(
            new Activity(1, 3),
            new Activity(2, 4),
            new Activity(0, 6),
            new Activity(5, 7),
            new Activity(8, 9),
            new Activity(5, 9)
        );

        List<Activity> selected = selectActivities(activities);

        System.out.println("Selected Activities (start → end):");
        for (Activity activity : selected) {
            System.out.println(activity.start + " → " + activity.end);
        }
    }
}