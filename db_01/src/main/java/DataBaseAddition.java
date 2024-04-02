import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;

public class DataBaseAddition {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        SerializeInJson inJson = new SerializeInJson();
        SerializeInXML inXML = new SerializeInXML();
        Department department = new Department("999999","1");
        Employee employee = new Employee(1,"Ivanov",department);
        Customer customer = new Customer("Ivan","Sergeevich","9999");
        Project project = new Project(25, customer);
        Task task = new Task(employee,project,12);
        PrintWriter writerForJson = new PrintWriter("json.txt", "UTF-8");
        PrintWriter writerForXml = new PrintWriter("xml.txt", "UTF-8");
        writerForJson.write(inJson.serialize(department));
        writerForJson.write(inJson.serialize(employee));
        writerForJson.write(inJson.serialize(customer));
        writerForJson.write(inJson.serialize(project));
        writerForJson.write(inJson.serialize(task));
        writerForXml.write(inXML.serialize(department));
        writerForXml.write(inXML.serialize(employee));
        writerForXml.write(inXML.serialize(customer));
        writerForXml.write(inXML.serialize(project));
        writerForXml.write(inXML.serialize(task));
        writerForJson.close();
        writerForXml.close();
    }
}
