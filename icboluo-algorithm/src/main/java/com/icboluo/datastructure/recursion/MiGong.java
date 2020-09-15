package com.icboluo.datastructure.recursion;

/**
 * @author icboluo
 */
class MiGong {
    public static void main(String[] args) {
        //构建地图
        int[][] map = new int[8][7];
        //使用1表示墙
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int j = 0; j < 8; j++) {
            map[j][0] = 1;
            map[j][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
        print(map);
        setWay(map, 1, 1);
        System.out.println("走过之后的路");
        print(map);

    }

    private static void print(int[][] map) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 0：没有走过，1：墙，2：通路可以走，3：该点已经走过但是走不通
     * 约定顺序为 下->右->上->左
     *
     * @param map 地图
     * @param i   从哪个位置开始找
     * @param j   从哪个位置开始找
     * @return 如果找到通路返回true，否则返回false 通路地址map[6][5]
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        }
        if (map[i][j] == 0) {
            //假定可以走通
            map[i][j] = 2;
            boolean b = true;
            b = setWay(map, i + 1, j);
            if (b) {
                return true;
            }
            b = setWay(map, i, j + 1);
            if (b) {
                return true;
            }
            b = setWay(map, i - 1, j);
            if (b) {
                return true;
            }
            b = setWay(map, i, j - 1);
            if (b) {
                return true;
            }
            map[i][j] = 3;
            return false;
        } else {
            return false;
        }
    }
}
