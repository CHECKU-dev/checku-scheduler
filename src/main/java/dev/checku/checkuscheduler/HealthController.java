package dev.checku.checkuscheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/health")
public class HealthController {

    @GetMapping
    public String healthCheck() {
        return "I'm OK...";
    }

//    @Async
//    @Scheduled(fixedDelay = 1000)
//    public void test() throws InterruptedException {
//        System.out.println("start with : " + Thread.currentThread().getName());
//        Thread.sleep(5000);
//        System.out.println("end with : " + Thread.currentThread().getName());
//    }




}
