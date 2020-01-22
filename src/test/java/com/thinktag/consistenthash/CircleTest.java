package com.thinktag.consistenthash;

import org.junit.Test;

import java.util.UUID;

public class CircleTest {

    @Test
    public void test()throws Exception{
        Circle c = new Circle();
        for(int i=0; i < 20; i++) {
            String value = UUID.randomUUID().toString();
            System.out.println("Value="+value);
            System.out.println("Hash="+c.getHash(value));
            System.out.println("Angle="+c.getAngle(c.getHash(value)));
        }
    }

    @Test
    public void testAssignment()throws Exception{
        //5b0111aa-da83-437e-8ed0-d2908c5b1b54   angle 6
        //57b82819-6f32-4dc7-86e5-122cebd3a74d   angle 183
        //b1c08c90-e975-43d6-b811-b7fcc34fe14d   angle 263
        Circle c = new Circle();
        c.addNode("57b82819-6f32-4dc7-86e5-122cebd3a74d");
        c.addNode("b1c08c90-e975-43d6-b811-b7fcc34fe14d");
        c.addNode("5b0111aa-da83-437e-8ed0-d2908c5b1b54");

        for(int i=0; i < 20; i++) {
            String value = UUID.randomUUID().toString();
            System.out.println("value="+value+"Hash="+c.getHash(value)+" Angle="+c.getAngle(c.getHash(value)));
            Node serverNode = c.getNode(value);
            System.out.println("Server Hash="+serverNode.getHash()+" Angle="+serverNode.getAngle());
            System.out.println("-----");
        }


    }
}
