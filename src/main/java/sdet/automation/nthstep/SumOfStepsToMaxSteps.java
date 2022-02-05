package main.java.sdet.automation.nthstep;

import java.util.ArrayList;
import java.util.List;

public class SumOfStepsToMaxSteps {

    public static void main(String[] args) {
        // lets assume we have step 1, 2 at any time to reach N(th) step
        calculateAllPossibleWaysToReachNthStep(2, 2);
        calculateAllPossibleWaysToReachNthStep(2, 3);
        calculateAllPossibleWaysToReachNthStep(2, 4);
    }

    public static void calculateAllPossibleWaysToReachNthStep(int availableStepsSize, int maxSteps) {
        List<List<Integer>> pathsToReachNthStep = new ArrayList<>();
        List<Integer> availableSteps = new ArrayList<>();
        // initialize the pathsToReachNthStep & availableSteps
        for (int i = 1; i <= availableStepsSize; i++) {
            List<Integer> s = new ArrayList<>(1);
            s.add(i);
            availableSteps.add(i);
            pathsToReachNthStep.add(s);
        }

        // building app possible ways using while looping
        while (!buildAllPossibleWays(pathsToReachNthStep, availableSteps, maxSteps)) ;

        // identifying the final path to reach Max (Nth) Step
        System.out.println("\nAll paths(details steps taken) to reach N(th) step");
        List<List<Integer>> finalPaths = new ArrayList<>();
        for (List<Integer> p : pathsToReachNthStep) {
            if (isPathEqualToMax(p, maxSteps)) {
                // trying to print the way to Max steps
                StringBuilder stepInTheWayToMax = new StringBuilder();
                for (Integer step : p) {
                    stepInTheWayToMax.append(step).append(" ");
                }
                System.out.println(stepInTheWayToMax);
                // adding to list of list path to Max steps for just to have count of possible ways
                finalPaths.add(p);
            }
        }
        System.out.println("Total possible ways to reach N(th) step : " + finalPaths.size());
    }

    public static boolean buildAllPossibleWays(List<List<Integer>> pathsToReachNthStep, List<Integer> availableSteps,
                                               int maxSteps) {
        List<List<Integer>> nextPossibleWay = new ArrayList<>();
        for (List<Integer> path : pathsToReachNthStep) {
            if (isClimbedTooHigh(path, maxSteps)) {
                nextPossibleWay.add(path);
            }
            for (Integer step : availableSteps) {
                List<Integer> p = new ArrayList<>(path);
                p.add(step);
                nextPossibleWay.add(p);
            }
        }
        pathsToReachNthStep.clear();

        boolean completed = true;
        for (List<Integer> n : nextPossibleWay) {
            if (completed && !isClimbedTooHigh(n, maxSteps))
                completed = false;
            pathsToReachNthStep.add(n);
        }
        return completed;
    }

    public static boolean isPathEqualToMax(List<Integer> path, int maxSteps) {
        int sum = 0;
        for (Integer i : path) {
            sum += i;
        }
        return sum == maxSteps;
    }

    public static boolean isClimbedTooHigh(List<Integer> path, int maxSteps) {
        int sum = 0;
        for (Integer i : path) {
            sum += i;
        }
        return sum >= maxSteps;
    }

}
