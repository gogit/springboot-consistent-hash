package com.thinktag.consistenthash;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class Circle {


    private static final BigInteger MAX_VALUE = new BigInteger("ffffffffffffffffffffffffffffffff", 16);
    private static final BigInteger MIN_VALUE = new BigInteger("00000000000000000000000000000000", 16);

    private static final char[] HEX_CHARS =
            {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private final BigInteger ONE_DEGREE;

    private List<Node> circleNodes = new ArrayList<>();

    public Circle() {
        ONE_DEGREE = MAX_VALUE.divide(new BigInteger("360"));
    }

    public Node getNode(String value) throws Exception {
        String hash = getHash(value);
        Node data = new Node(value, getHash(value), getAngle(hash));
        //the first server is the lowest angle in the circle
        //if the data item is lesser than that we return it immediately
        if (data.getAngle() <= circleNodes.get(0).getAngle()) {
            return circleNodes.get(0);
        }
        //if not follow the flow
        for (int i = 0; i < circleNodes.size() - 1; i++) {
            Node currentServer = circleNodes.get(i);
            Node nextServer = circleNodes.get(i + 1);
            if (currentServer.getAngle() == data.getAngle()) {
                //weve found the server
                return currentServer;
            }
            if (nextServer.getAngle() == data.getAngle()) {
                //weve found the server
                return nextServer;
            }

            //2conditions match we've found the server
            if (data.getAngle() > currentServer.getAngle() &&
                    data.getAngle() <= nextServer.getAngle()) {
                return nextServer;
            }
        }
        //we're here meaning the angle of the data item is more than
        // that of the last server
        return circleNodes.get(0);
    }

    public void addNode(String value) throws Exception {
        String hash = getHash(value);
        Node server = new Node(value, hash, getAngle(hash));
        circleNodes.add(server);
        circleNodes.sort((o1, o2) -> {
            if (o1.getHash().equals(o2.getHash())) {
                return 0;
            }
            return Integer.compare(o1.getAngle(), o2.getAngle());
        });
    }

    public int getAngle(String hex) throws Exception {
        BigInteger value = new BigInteger(hex, 16);
        return value.divide(ONE_DEGREE).intValue();
    }

    public String getHash(String name) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(name.getBytes(StandardCharsets.UTF_8));
        String hex = new String(encodeHex(hash));
        // System.out.println(hex.length());
        return hex;
    }

    private static char[] encodeHex(byte[] bytes) {
        char chars[] = new char[32];
        for (int i = 0; i < chars.length; i = i + 2) {
            byte b = bytes[i / 2];
            chars[i] = HEX_CHARS[(b >>> 0x4) & 0xf];
            chars[i + 1] = HEX_CHARS[b & 0xf];
        }
        return chars;
    }

    public List<Node> getCircleNodes() {
        return circleNodes;
    }
}
