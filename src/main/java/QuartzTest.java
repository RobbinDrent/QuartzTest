import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author Robbin Drent <r.v.drent@st.hanze.nl>
 * <p>
 * Dit is wat het programma doet.
 */
public class QuartzTest {

    public static void main(String[] args) {

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();

            try {
                Scheduler sched = schedulerFactory.getScheduler();

                JobDetail job = JobBuilder.newJob(SimpleJob.class).withIdentity("myJob", "group1").usingJobData("jobSays", "I am a Simple Job!").usingJobData("myFloatValue", 3f).build();

                Trigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger", "group1").startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(8).repeatForever()).build();

                JobDetail jobA = JobBuilder.newJob(JobA.class).withIdentity("jobA", "group2").build();

                JobDetail jobB = JobBuilder.newJob(JobB.class).withIdentity("jobB", "group2").build();

                Trigger triggerA = TriggerBuilder.newTrigger().withIdentity("triggerA", "group1").startNow().withPriority(1).withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever()).build();

                Trigger triggerB = TriggerBuilder.newTrigger().withIdentity("triggerB", "group2").startNow().withPriority(10).withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(4).repeatForever()).build();

                sched.scheduleJob(job, trigger);
                sched.scheduleJob(jobA, triggerA);
                sched.scheduleJob(jobB, triggerB);
                sched.start();
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
    }
}
