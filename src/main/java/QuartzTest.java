import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Scanner;

/**
 * @author Robbin Drent <r.v.drent@st.hanze.nl>
 * <p>
 * Test met de Quartz Scheduler.
 */
public class QuartzTest {

    public static void main(String[] args) {

        Scanner keyboard =  new Scanner(System.in);
        System.out.print("Hoeveel seconden wilt u tussen de jobs? ");
        int seconden =keyboard.nextInt();

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();

            try {
                Scheduler scheduler = schedulerFactory.getScheduler();

                JobDetail job = JobBuilder.newJob(SimpleJob.class)
                        .withIdentity("myJob", "group1")
                        .usingJobData("jobSays", "I am a Simple Job!")
                        .usingJobData("seconden", seconden)
                        .build();

                Trigger trigger = TriggerBuilder.newTrigger()
                        .withIdentity("myTrigger", "group1")
                        .startNow()
                        .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInSeconds(seconden).repeatForever())
                        .build();

//                JobDetail jobA = JobBuilder.newJob(JobA.class)
//                        .withIdentity("jobA", "group2")
//                        .build();
//
//                JobDetail jobB = JobBuilder.newJob(JobB.class)
//                        .withIdentity("jobB", "group2")
//                        .build();
//
//                Trigger triggerA = TriggerBuilder.newTrigger()
//                        .withIdentity("triggerA", "group1")
//                        .startNow()
//                        .withPriority(1)
//                        .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever())
//                        .build();
//
//                Trigger triggerB = TriggerBuilder.newTrigger()
//                        .withIdentity("triggerB", "group2")
//                        .startNow()
//                        .withPriority(10)
//                        .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(4).repeatForever())
//                        .build();
//
//                JobDetail jobC = JobBuilder.newJob(TestJob.class)
//                        .withIdentity("jobC", "group3")
//                        .usingJobData("jobSays", "Goedemiddag!")
//                        .build();
//
//                CronTrigger cronTrigger = TriggerBuilder.newTrigger()
//                        .withIdentity("triggerC", "group3")
//                        .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * 8-17 * * ?"))
//                        .forJob("jobC", "group3")
//                        .build();

                scheduler.scheduleJob(job, trigger);
//                scheduler.scheduleJob(jobA, triggerA);
//                scheduler.scheduleJob(jobB, triggerB);
//                scheduler.scheduleJob(jobC, cronTrigger);
                scheduler.start();
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
    }
}
