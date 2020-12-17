package xyz.guqing.travelpath.utils;

import java.text.DecimalFormat;

/**
 * 多属性决策
 *
 * @author guqing
 */
public class TopSis {
    private static int ROW = 0;
    private static int COLUMN = 6;
    private static final boolean debug = false;

    public static void showMatrix1(double[][] matrix) {
        int i, j;

        System.out.println();
        DecimalFormat df = new DecimalFormat("0.0000");
        for (i = 0; i < ROW; i++) {
            for (j = 0; j < COLUMN; j++) {
                System.out.print(df.format(matrix[i][j]) + "  ");
            }
            if (j == COLUMN) {
                System.out.println();
            }
        }
    }

    public static void showMatrix2(double[][] matrix) {
        int i, j;

        System.out.println();

        DecimalFormat df = new DecimalFormat("0.0000");
        for (i = 0; i < ROW; i++) {
            for (j = 0; j < ROW; j++) {
                System.out.print(df.format(matrix[i][j]) + "  ");
            }
            if (j == ROW) {
                System.out.println();
            }
        }
        System.out.println();
    }

    /**
     * @param matrixI       待决策矩阵
     * @param weight        列属性权重矩阵
     * @param attributeType 属性类型数组，1表示效益型，0表示成本型；
     *                      效益型:通俗来讲就是正向指标(越大越好)
     *                      成本型:即投入成本越大越不利(故越小越好)
     * @return 返回决策结果矩阵
     */
    public static double[] decisionAttributes(double[][] matrixI, double[] weight, int[] attributeType) {
        int i, j, k;

        ROW = matrixI.length;
        COLUMN = matrixI[0].length;

        if(ROW == 1) {
            // 快速返回
            return new double[]{1};
        }

        if (debug) {
            showMatrix1(matrixI);
        }

        // Step 1 : Generate R// Step 1 : Generate R
        double[][] matrixR = new double[ROW][COLUMN];
        for (i = 0; i < ROW; i++) {

            for (j = 0; j < COLUMN; j++) {
                double sum = 0;
                for (k = 0; k < ROW; k++) {
                    sum += matrixI[k][j] * matrixI[k][j];
                }
                if (sum == 0) {
                    matrixR[i][j] = 0.0;
                } else {
                    matrixR[i][j] = matrixI[i][j] / Math.sqrt(sum);
                }
            }
        }

        if (debug) {
            showMatrix1(matrixR);
        }

        // Step 2 : Generate V
        double[][] matrixV = new double[ROW][COLUMN];
        for (i = 0; i < ROW; i++) {
            for (j = 0; j < COLUMN; j++) {
                matrixV[i][j] = matrixR[i][j] * weight[j];
            }
        }

        if (debug) {
            showMatrix1(matrixV);
        }

        // Step 3 : Generate A+ and A-
        double[] positiveA = new double[COLUMN];
        double[] negativeA = new double[COLUMN];
        double positiveMax;
        double negativeMin;

        for (j = 0; j < COLUMN; j++) {
            positiveMax = matrixV[0][j];
            negativeMin = matrixV[0][j];

            for (i = 0; i < ROW; i++) {
                if (attributeType[j] == 1) {
                    if (positiveMax < matrixV[i][j]) {
                        positiveMax = matrixV[i][j];
                    }
                    if (negativeMin > matrixV[i][j]) {
                        negativeMin = matrixV[i][j];
                    }
                } else {
                    if (positiveMax > matrixV[i][j]) {
                        positiveMax = matrixV[i][j];
                    }
                    if (negativeMin < matrixV[i][j]) {
                        negativeMin = matrixV[i][j];
                    }
                }
            }
            positiveA[j] = positiveMax;
            negativeA[j] = negativeMin;
        }

        if (debug) {
            System.out.println("positive_A:");
            for (j = 0; j < COLUMN; j++) {
                System.out.println(positiveA[j]);
            }


            System.out.println("Negative_A:");
            for (j = 0; j < COLUMN; j++) {
                System.out.println(negativeA[j]);
            }
        }

        // Step 4 : Compute Euclid distance
        double[] positiveS = new double[ROW];
        double[] negativeS = new double[ROW];
        for (i = 0; i < ROW; i++) {
            double positiveSum = 0;
            for (j = 0; j < COLUMN; j++) {
                positiveSum += Math.pow(matrixV[i][j] - positiveA[j], 2);
            }
            positiveS[i] = Math.sqrt(positiveSum);
        }

        for (i = 0; i < ROW; i++) {
            double negativeSum = 0;
            for (j = 0; j < COLUMN; j++) {
                negativeSum += Math.pow(matrixV[i][j] - negativeA[j], 2);
            }
            negativeS[i] = Math.sqrt(negativeSum);
        }

        if (debug) {
            System.out.println("positive_S:");
            for (i = 0; i < ROW; i++) {
                System.out.println(positiveS[i]);
            }

            System.out.println("Negative_S:");
            for (i = 0; i < ROW; i++) {
                System.out.println(negativeS[i]);
            }
        }

        // Step 5 : Compute the relative approach degree with Ideal Solution
        double[] degreeC = new double[ROW];
        for (i = 0; i < ROW; i++) {
            degreeC[i] = negativeS[i] / (positiveS[i] + negativeS[i]);
        }

        if (debug) {
            System.out.println("Degree_C:");
            for (i = 0; i < ROW; i++) {
                System.out.println(degreeC[i]);
            }
        }
        return degreeC;
    }
}
