package javatcpsockets;

public class SuperMain {
    public static void main(String[] args) {

        int totalUsers=300;
        int totalOperations=1000;

        for (int i=0;i<totalUsers;i++){
            MyThread thread=new MyThread(totalOperations);
            thread.start();
        }

    }
}
