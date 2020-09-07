package com.cloudersjun;

import java.io.*;

/**
 * Created by lenovo on 2017/7/12.
 */
public class Serialized {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        A a=new A();
        a.setName("yj");
        ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("result.obj"));
        out.writeObject(a);
        out.close();
        ObjectInputStream in=new ObjectInputStream(new FileInputStream("result.obj"));
        A a1=(A)in.readObject();
        in.close();
        System.out.print(a1.getName());
    }
    public static class A implements Serializable{
        private static final long serialVersionUID = 1L;
        private String name;

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }
    }
}
