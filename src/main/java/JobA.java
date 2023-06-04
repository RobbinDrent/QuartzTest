import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author Robbin Drent <r.v.drent@st.hanze.nl>
 * <p>
 * Dit is wat het programma doet.
 */
public class JobA implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("This is Job A");
    }
}
