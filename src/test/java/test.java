import com.zmu.service.adminService;
import com.zmu.service.impl.adminServiceImpl;
import com.zmu.service.impl.studentServiceImpl;
import com.zmu.service.studentService;
import org.junit.Test;
import org.junit.runner.RunWith;


public class test {
    @Test
    public void test(){
        studentService service = new studentServiceImpl();

        System.out.println(service.studentList());
    }
}
