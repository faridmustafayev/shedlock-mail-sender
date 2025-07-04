package az.cybernet.shedlock.service;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class EmailScheduler {

    private final EmailService emailService;

    public EmailScheduler(EmailService emailService) {
        this.emailService = emailService;
    }

    @Scheduled(fixedRate = 5000)
    @SchedulerLock(name = "emailTask", lockAtMostFor = "PT10S", lockAtLeastFor = "PT2S")
    public void sendMail() {
        System.out.println("📧 Sending email at: " + LocalDateTime.now());
        emailService.sendEmail("email@gmail.com", "Yoxlama", "Necəsən?");
    }
}
