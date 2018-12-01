package exam.disney.serra.serradisneykyleexam2;

public class Student {
    //Long id;
    String fname, lname;
    Integer ave;

    public Student(String fname, String lname, Integer ave) {
        this.fname = fname;
        this.lname = lname;
        this.ave = ave;
    }

    public Student() {
    }

    public String getFname() {
        return fname;
    }

    public String getLname() { return lname; }


    public Integer getAve() {
        return ave;
    }
}
