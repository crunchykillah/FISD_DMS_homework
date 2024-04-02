import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;


import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class App {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, JsonProcessingException {
        ObjectMapper jsonMapper = new ObjectMapper();
        XmlMapper xmlMapper = new XmlMapper();

        //создание объектов
        Department department = new Department("999999","1");
        Employee employee = new Employee(1,"Ivanov",department);
        Customer customer = new Customer("Ivan","Sergeevich","9999");
        Project project = new Project(25, customer);
        Task task = new Task(employee,project,12);

        //сериализация
        String departmentJson = jsonMapper.writeValueAsString(department);
        String employeeJson = jsonMapper.writeValueAsString(employee);
        String customerJson = jsonMapper.writeValueAsString(customer);
        String projectJson = jsonMapper.writeValueAsString(project);
        String taskJson = jsonMapper.writeValueAsString(task);

        System.out.println("JSON" + "\n" + departmentJson + "\n" +
                employeeJson + "\n" + customerJson + "\n" +
                projectJson + "\n" + taskJson);

        String departmentXml = xmlMapper.writeValueAsString(department);
        String employeeXml = xmlMapper.writeValueAsString(employee);
        String customerXml = xmlMapper.writeValueAsString(customer);
        String projectXml = xmlMapper.writeValueAsString(project);
        String taskXml = xmlMapper.writeValueAsString(task);

        System.out.println("XML" + "\n" + departmentXml + "\n" +
                employeeXml + "\n" + customerXml + "\n" +
                projectXml + "\n" + taskXml);
    }
}
