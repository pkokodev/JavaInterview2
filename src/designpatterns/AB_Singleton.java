package designpatterns;

import java.io.*;

//How to prevent singleton class from Reflection | serialization | Cloning | Java Techie
public class AB_Singleton {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        LazySingleton instance1 = LazySingleton.getInstance();

        // Serialize singleton object to a file.
        ObjectOutput out = new ObjectOutputStream(new FileOutputStream("singleton.ser"));
        out.writeObject(instance1);
        out.close();

        // Deserialize singleton object from the file
        ObjectInput in = new ObjectInputStream(new FileInputStream("singleton.ser"));
        LazySingleton instance2 = (LazySingleton) in.readObject();
        in.close();

        System.out.println("instance1 hashCode: " + instance1.hashCode());
        System.out.println("instance2 hashCode: " + instance2.hashCode());

        /*
         LazySingleton reflectionInstance=null;


         Constructor[] constructors = LazySingleton.class.getDeclaredConstructors();
         for (Constructor constructor : constructors) {
         constructor.setAccessible(true); reflectionInstance = (LazySingleton)
         constructor.newInstance(); }

         System.out.println(reflectionInstance.hashCode());
         */

        /*
         LazySingleton instance2 = (LazySingleton) instance1.clone();

         System.out.println(instance2.hashCode());
         */
    }
}

class LazySingleton extends MyClone implements Serializable {
    private static LazySingleton instance;


    //Prevent from cloning
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    private LazySingleton(){
        //Prevent Reflection
        if (instance != null) {
            throw new IllegalStateException("object can't be create using reflection");
        }
    }

    //Prevent de-serialization
    protected Object readResolve() {
        return instance;
    }

    public static LazySingleton getInstance(){
        if(instance == null){
            instance = new LazySingleton();
        }
        return instance;

    }
}

 class MyClone implements Cloneable {
    @Override
    protected Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return super.clone();
    }
}