package com.icboluo.datastructure.tree.huffmancode;

import java.io.*;
import java.util.*;

/**
 * @author icboluo
 * @since 2020/7/23 12:24
 */
 class HuffmanCode {
    static StringBuilder sb = new StringBuilder();
    static HashMap<Byte, String> map = new HashMap<>();

    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentByte = content.getBytes();
        System.out.println(contentByte.length);
        byte[] zip = huffmanZip(contentByte);
        System.out.println("zip = " + Arrays.toString(zip));

        byte[] decode = decode(map, zip);
        System.out.println("Arrays.toString(decode) = " + Arrays.toString(decode));

    }

    /**
     * 压缩文件
     *
     * @param srcFile 要压缩文件路径
     * @param dstFile 压缩后文件路径
     */
    public static void zipFile(String srcFile, String dstFile) {
        OutputStream os = null;
        FileInputStream fis = null;
        ObjectOutputStream oos = null;
        try {
            fis = new FileInputStream(srcFile);
            byte[] b = new byte[fis.available()];
            fis.read(b);

            byte[] huffmanBytes = huffmanZip(b);
            os = new FileOutputStream(dstFile);
            oos = new ObjectOutputStream(os);
            oos.writeObject(huffmanBytes);
            oos.writeObject(map);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                os.close();
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 解压
     *
     * @param zipFile 压缩文件路径
     * @param dstFile  解压文件路径
     */
    public static void unZipFile(String zipFile, String dstFile) {
        InputStream is = null;
        ObjectInputStream ois = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(zipFile);
            ois = new ObjectInputStream(is);
            byte[] huffmanBytes = (byte[]) ois.readObject();
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            os = new FileOutputStream(dstFile);
            os.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                ois.close();
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 解压
     *
     * @param huffmanCodes 赫夫曼编码表 map
     * @param huffmanBytes 赫夫曼编码得到的自治街数组
     * @return 原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            boolean flag = i == huffmanBytes.length - 1;
            sb.append(byteToBitString(!flag, huffmanBytes[i]));
        }
        HashMap<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < sb.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag) {
                String key = sb.substring(i, i + count);
                b = map.get(key);
                if (b == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    /**
     * 将一个byte转换成二进制字符串
     *
     * @param b
     * @return
     */
    private static String byteToBitString(boolean flag, byte b) {
        int temp = b;
        if (flag) {
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    /**
     * 数据压缩
     *
     * @param contentByte 压缩前的数组
     * @return 压缩后的数组
     */
    private static byte[] huffmanZip(byte[] contentByte) {
        List<Node> nodes = getNodes(contentByte);
        System.out.println("nodes = " + nodes);
        Node root = creatHuffmanTree(nodes);
        preOrder(root);
        Map<Byte, String> codes = getCodes(root);
        System.out.println(codes);
        return zip(contentByte, codes);
    }

    /**
     * 返回压缩后的字节数组
     *
     * @param bytes 原始字符串
     * @param hfm   生成的hfm编码
     * @return 返回赫夫曼编码处理后的byte[]
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> hfm) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(hfm.get(b));
        }
        //len=(sb.length+7)/8
        int len;
        if (sb.length() % 8 == 0) {
            len = sb.length() / 8;
        } else {
            len = sb.length() / 8 + 1;
        }
        byte[] by = new byte[len];
        int index = 0;
        for (int i = 0; i < sb.length(); i += 8) {
            String strByte;
            if (i + 8 > sb.length()) {
                strByte = sb.substring(i);
            } else {
                strByte = sb.substring(i, i + 8);
            }
            by[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return by;
    }

    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        getCodes(root.left, "0", sb);
        getCodes(root.right, "1", sb);
        return map;
    }

    /**
     * 将传入的node节点的所有叶子节点赫夫曼编码得到，并放到map中
     *
     * @param node 传入节点
     * @param code 路径
     * @param sb1  拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder sb1) {
        StringBuilder sb2 = new StringBuilder(sb1);
        sb2.append(code);
        if (node != null) {
            //非叶子节点
            if (node.data == null) {
                getCodes(node.left, "0", sb2);
                getCodes(node.right, "1", sb2);
            } else {
                map.put(node.data, sb2.toString());
            }
        }
    }

    private static List<Node> getNodes(byte[] bytes) {
        ArrayList<Node> nodes = new ArrayList<>();
        HashMap<Byte, Integer> map = new HashMap<>();
        for (Byte b : bytes) {
            Integer time = map.get(b);
            if (time == null) {
                map.put(b, 1);
            } else {
                map.put(b, time + 1);
            }
        }
        Set<Map.Entry<Byte, Integer>> entrySet = map.entrySet();
        for (Map.Entry<Byte, Integer> entry : entrySet) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    private static Node creatHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            Node mid = new Node(null, left.weight + right.weight);
            mid.left = left;
            mid.right = right;

            nodes.remove(left);
            nodes.remove(right);
            nodes.add(mid);
        }
        return nodes.get(0);
    }

    private static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("空树无法遍历");
        }
    }
}
