/*
  Goal:
  --------
  Given a set of jobs where each job has:
       - an ID
       - a deadline
       - a profit
 
  Schedule the jobs in such a way that:
       ✔ Total profit is maximized
       ✔ Only ONE job can be done at each time slot
       ✔ A job must be completed before or on its deadline
 
  Approach (Greedy):
  ----------------------
  1. Sort jobs by profit in DESCENDING order.
  2. For each job, try to place it in the latest available time slot ≤ deadline.
  3. If slot is free, schedule it.
 
  Time Complexity:
       O(n log n) for sorting + O(n * maxDeadline)
 */

import java.util.Arrays;

class Job {
    int id;
    int deadline;
    int profit;

    Job(int id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class JobSequencing {

    public static int[] scheduleJobs(Job[] jobs) {

        // Step 1: Sort jobs by profit (highest first)
        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

        // Find the maximum deadline
        int maxDeadline = 0;
        for (Job job : jobs) {
            maxDeadline = Math.max(maxDeadline, job.deadline);
        }

        // Time slots (1-indexed)
        int[] slots = new int[maxDeadline + 1];
        Arrays.fill(slots, -1);

        int totalProfit = 0;
        int jobCount = 0;

        // Step 2: Place each job in the latest possible free slot
        for (Job job : jobs) {
            for (int t = job.deadline; t > 0; t--) {
                if (slots[t] == -1) {
                    slots[t] = job.id;
                    totalProfit += job.profit;
                    jobCount++;
                    break;
                }
            }
        }

        // Logging results
        System.out.println("Jobs completed: " + jobCount);
        System.out.println("Total Profit: " + totalProfit);

        return slots;
    }

    public static void main(String[] args) {

        Job[] jobs = {
                new Job(1, 2, 100),
                new Job(2, 1, 19),
                new Job(3, 2, 27),
                new Job(4, 1, 25),
                new Job(5, 3, 15)
        };

        int[] result = scheduleJobs(jobs);

        System.out.print("Scheduled Job Order: ");
        for (int i = 1; i < result.length; i++) {
            if (result[i] != -1) {
                System.out.print("J" + result[i] + " ");
            }
        }
    }
}