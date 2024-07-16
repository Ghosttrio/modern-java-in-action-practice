package future;

public class Job {

    public static double longTimeJob(){
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1.0;
    }

    public static double anotherJob(){
        return 2.0;
    }
}
