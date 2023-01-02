import java.io.Serializable;

public class Student implements Serializable{
    String name;
    String id;
    String department;
    String batch;
    String section;

    
    public Student(String name, String id, String department, String batch, String section){
        this.name = name;
        this.id = id;
        this.department = department;
        this.batch = batch;
        this.section = section;
    }

    public String getName(){
        return name;
    }
    public String getId(){
        return id;
    }
    public String getDepartment(){
        return department;
    }
    public String getBatch(){
        return batch;
    }
    public String getSection(){
        return section;
    }
}