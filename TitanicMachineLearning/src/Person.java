public class Person {
    private String status;
    private double[] info;


    public Person(String status,double[] info){
        this.status = status;
        this.info = info;

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double[] getInfo() {
        return info;
    }

    public void setInfo(double[] info) {
        this.info = info;
    }


}
