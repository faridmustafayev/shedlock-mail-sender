# Scheduled Message Sender with ShedLock

This project demonstrates how to use **ShedLock** with **Spring Boot** to ensure that scheduled tasks (like email sending) are executed only once in a distributed environment.

## 🔧 Technologies Used
- Java 17
- Spring Boot
- ShedLock
- Spring Scheduler
- (Optional) PostgreSQL / JDBC Lock Provider

## 📌 Description
I used **ShedLock** to send scheduled emails at a specific time without duplication. This ensures that even if the application is running on multiple instances, the email is sent only once at the scheduled time.

## 🕒 How It Works
- A scheduled method is triggered at a fixed time.
- ShedLock is used to acquire a lock before executing the logic.
- If the lock is acquired, the email is sent.
- After execution, the lock is released automatically.

## 💡 Why ShedLock?
Spring's `@Scheduled` is not safe in a clustered environment. **ShedLock** solves this by using a lock mechanism (database-based or other) to coordinate between instances.

## 📁 Example Code Snippet

```java
@Scheduled(fixedRate = 5000)
    @SchedulerLock(name = "emailTask", lockAtMostFor = "PT10S", lockAtLeastFor = "PT2S")
    public void sendMail() {
        System.out.println("📧 Sending email at: " + LocalDateTime.now());
        emailService.sendEmail("email@gmail.com", "Yoxlama", "Necəsən?");
    }