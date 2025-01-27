package org.example.entity;

import java.util.HashSet;
import java.util.Set;

public class TaskData {
    private Set<Task> annsTasks;
    private Set<Task> bobsTasks;
    private Set<Task> carolsTasks;
    private Set<Task> unassignedTasks;

    // Constructor
    public TaskData(Set<Task> annsTasks, Set<Task> bobsTasks, Set<Task> carolsTasks, Set<Task> unassignedTasks) {
        this.annsTasks = annsTasks;
        this.bobsTasks = bobsTasks;
        this.carolsTasks = carolsTasks;
        this.unassignedTasks = unassignedTasks;
    }

    // getTasks method to return tasks based on the input parameter
    public Set<Task> getTasks(String person) {
        switch (person.toLowerCase()) {
            case "ann":
                return annsTasks;
            case "bob":
                return bobsTasks;
            case "carol":
                return carolsTasks;
            case "all":
                return getUnion(annsTasks, getUnion(bobsTasks, getUnion(carolsTasks, unassignedTasks)));
            default:
                return new HashSet<>();
        }
    }

    // Union method to combine sets
    public Set<Task> getUnion(Set<Task> set1, Set<Task> set2) {
        Set<Task> unionSet = new HashSet<>(set1);
        unionSet.addAll(set2);
        return unionSet;
    }

    // Intersection method to find common elements
    public Set<Task> getIntersect(Set<Task> set1, Set<Task> set2) {
        Set<Task> intersectSet = new HashSet<>(set1);
        intersectSet.retainAll(set2);
        return intersectSet;
    }
    
    // Test dosyasıyla uyumlu olması için aynı metodu farklı isimle tanımlıyorum
    public Set<Task> getIntersection(Set<Task> set1, Set<Task> set2) {
        return getIntersect(set1, set2);
    }

    // Difference method to find elements in set1 but not in set2
    public Set<Task> getDifference(Set<Task> set1, Set<Task> set2) {
        Set<Task> differenceSet = new HashSet<>(set1);
        differenceSet.removeAll(set2);
        return differenceSet;
    }
    
    // Test dosyasıyla uyumlu olması için aynı metodu farklı isimle tanımlıyorum
    public Set<Task> getDifferences(Set<Task> set1, Set<Task> set2) {
        return getDifference(set1, set2);
    }

    // Check for tasks assigned to multiple people
    public Set<Task> getMultipleAssignees() {
        Set<Task> multipleAssignees = new HashSet<>();
        for (Task task : annsTasks) {
            if (bobsTasks.contains(task) || carolsTasks.contains(task)) {
                multipleAssignees.add(task);
            }
        }
        for (Task task : bobsTasks) {
            if (carolsTasks.contains(task)) {
                multipleAssignees.add(task);
            }
        }
        return multipleAssignees;
    }
}
