import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author Robbin Drent <r.v.drent@st.hanze.nl>
 * <p>
 * Dit is wat het programma doet.
 */
public class SimpleJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();

        String jobSays = dataMap.getString("jobSays");
        int seconden = dataMap.getInt("seconden");

        if (seconden != 1 ) {
            System.out.println("Deze tekst verschijnt elke " + seconden + " seconden");
        } else if (seconden == 1) {
            System.out.println("Deze tekst verschijnt elke seconde!");
        }
    }
}
