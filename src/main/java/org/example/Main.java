package org.example;

import org.example.entity.Priority;
import org.example.entity.Status;
import org.example.entity.Task;
import org.example.entity.TaskData;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // Örnek tasklar oluşturalım
        Task task1 = new Task("Web Projesi", "Frontend geliştirme", "ann", Priority.HIGH, Status.IN_PROGRESS);
        Task task2 = new Task("Mobil Uygulama", "API entegrasyonu", "bob", Priority.MED, Status.ASSIGNED);
        Task task3 = new Task("Database Projesi", "Veri migrasyonu", "carol", Priority.LOW, Status.IN_QUEUE);
        Task task4 = new Task("Web Projesi", "Backend geliştirme", "ann", Priority.HIGH, Status.IN_PROGRESS);
        Task task5 = new Task("Database Projesi", "Şema tasarımı", "bob", Priority.MED, Status.ASSIGNED);
        Task task6 = new Task("Mobil Uygulama", "UI tasarımı", "carol", Priority.HIGH, Status.IN_PROGRESS);
        Task task7 = new Task("DevOps Projesi", "CI/CD kurulumu", null, Priority.HIGH, Status.IN_QUEUE);
        Task task8 = new Task("Test Projesi", "Otomatik test yazımı", null, Priority.MED, Status.IN_QUEUE);
        Task task9 = new Task("Web Projesi", "SEO optimizasyonu", "ann", Priority.LOW, Status.ASSIGNED);
        Task task10 = new Task("Mobil Uygulama", "Performans iyileştirme", "bob", Priority.HIGH, Status.IN_PROGRESS);
        Task task11 = new Task("Database Projesi", "Veri migrasyonu", "carol", Priority.LOW, Status.IN_QUEUE); // task3 ile aynı (paylaşımlı task)
        Task task12 = new Task("DevOps Projesi", "Deployment otomasyonu", "ann", Priority.MED, Status.ASSIGNED);
        Task task13 = new Task("Test Projesi", "Otomatik test yazımı", "bob", Priority.MED, Status.IN_QUEUE); // task8 ile aynı (paylaşımlı task)
        
        // Çalışanların ve atanmamış taskların setlerini oluşturalım
        Set<Task> annsTasks = new HashSet<>();
        annsTasks.add(task1);
        annsTasks.add(task4);
        annsTasks.add(task9);
        annsTasks.add(task12);
        
        Set<Task> bobsTasks = new HashSet<>();
        bobsTasks.add(task2);
        bobsTasks.add(task5);
        bobsTasks.add(task10);
        bobsTasks.add(task13);
        
        Set<Task> carolsTasks = new HashSet<>();
        carolsTasks.add(task3);
        carolsTasks.add(task6);
        carolsTasks.add(task11);
        
        Set<Task> unassignedTasks = new HashSet<>();
        unassignedTasks.add(task7);
        unassignedTasks.add(task8);
        
        // TaskData nesnesini oluşturalım
        TaskData taskData = new TaskData(annsTasks, bobsTasks, carolsTasks, unassignedTasks);
        
        // Müdüre sunulacak rapor bilgilerini hazırlayalım
        System.out.println("===== YÖNETİCİ RAPORU =====");
        
        // 1. Tüm çalışanların üzerindeki tasklar
        System.out.println("\n1) Tüm çalışanların üzerindeki tasklar:");
        Set<Task> allTasks = taskData.getTasks("all");
        printTasks(allTasks);
        
        // 2. Her bir çalışanın üzerindeki tasklar
        System.out.println("\n2) Her bir çalışanın üzerindeki tasklar:");
        
        System.out.println("\nAnn'in taskları:");
        printTasks(taskData.getTasks("ann"));
        
        System.out.println("\nBob'un taskları:");
        printTasks(taskData.getTasks("bob"));
        
        System.out.println("\nCarol'un taskları:");
        printTasks(taskData.getTasks("carol"));
        
        // 3. Herhangi bir çalışana atanması yapılmamış olan tasklar
        System.out.println("\n3) Atanmamış tasklar:");
        printTasks(unassignedTasks);
        
        // 4. Birden fazla çalışana atanmış tasklar
        System.out.println("\n4) Birden fazla çalışana atanmış tasklar:");
        Set<Task> multipleAssignees = taskData.getMultipleAssignees();
        
        if (multipleAssignees.isEmpty()) {
            System.out.println("Birden fazla çalışana atanmış task bulunmamaktadır.");
        } else {
            printTasks(multipleAssignees);
        }
    }
    
    // Taskları yazdırmak için yardımcı metod
    private static void printTasks(Set<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Task bulunamadı.");
            return;
        }
        
        for (Task task : tasks) {
            System.out.println("- Proje: " + task.getProject() + 
                              ", Açıklama: " + task.getDescription() + 
                              ", Sorumlu: " + (task.getAssignee() != null ? task.getAssignee() : "Atanmamış") + 
                              ", Öncelik: " + task.getPriority() + 
                              ", Durum: " + task.getStatus());
        }
    }
}