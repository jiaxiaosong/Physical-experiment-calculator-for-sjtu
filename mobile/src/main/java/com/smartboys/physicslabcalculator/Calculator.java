package com.smartboys.physicslabcalculator;


import java.util.Arrays;

public class Calculator
{
    public static void main(String[] args){
//        double[][] data3 = new double[][] {
//                {349,350},
//                {179,179},
//                {239,240},
//                {59,59},
//                {321},
//                {141},
//                {201},
//                {21},
//                {255,254,253},
//                {75,74,73},
//                {307},
//                {127}
//        };
//        double[] res3 = Optical_Angel_Gauge.get_result(data3);
//        for (double n:res3)
//            System.out. (n + ",");


//        double[][] data1 = new double[][]{{0.24},{0.473,0.485,0.457,0.464,0.448,0.464},
//                {0.474,0.454,0.467,0.454,0.452,0.467},
//                {0.957,0.929,0.940,0.959,0.956,0.935},
//                {0.949,0.950,0.944,0.935,0.966,0.948},
//                {1.459,1.456,1.419,1.420,1.440,1.462},
//                {1.415,1.414,1.454,1.430,1.428,1.424},
//                {0.452,0.456,0.451,0.458,0.461,0.456},
//                {0.451,0.454,0.448,0.457,0.462,0.450},
//                {0.901,0.895,0.900,0.897,0.900,0.897},
//                {0.901,0.894,0.901,0.899,0.895,0.897},
//                {1.337,1.349,1.344,1.346,1.356,1.344},
//                {1.338,1.351,1.343,1.346,1.356,1.344},
//                {0.214,0.225,0.227,0.226,0.234,0.226},
//                {0.239,0.238,0.238,0.246,0.246,0.240},
//                {0.437,0.453,0.443,0.444,0.443,0.442},
//                {0.480,0.473,0.473,0.471,0.469,0.473},
//                {0.863,0.865,0.864,0.863,0.864,0.863},
//                {0.914,0.914,0.914,0.910,0.909,0.915},
//                {0.1},{0.09},{0.199},{0.107},{1425}
//        };
//        double[] result1 = Electron_Charge_Mass_Ratio.get_result(data1);
//        for(double n: result1)
//            System.out.println(n+", ");


//        double[][] data2 = new double[][] {
//                {0},
//                {16.15,16.10,16.15,16.40,16.20,16.15},
//                {16.80,16.90,16.75,16.60,16.75,16.80},
//                {-0.5},
//                {24.75,24.45,24.95,25.15,25,25},
//                {47.3,48.2,47.55,47.6,47.45,47.65},
//                {73.05},
//                {-0.5},
//                {72.4,72.2,72.5,72.35,72.45,73.8},
//                {47},
//                {62},
//                {74.5,74.45,74.6,74.5,74.8,74.5},
//                {102.15,102.5,102.3,104.3,103.3,103.85},
//                {103.7,102.05,103.40,101.95,102.1,103.65}
//        };
//        double[] result2 = Focal_Distance.get_result(data2);
//        for(double n:result2)
//            System.out.println(n+",");
    }
}


class Math_Cal{
    public static double average(double arr[]){
        double sum = 0;
        for (double ele:arr)
            sum += ele;
        return sum/arr.length;
    }

    public static double[] least_square_method(double x[], double y[])
    {
        double[] xy = new double[x.length];
        double[] xx = new double[x.length];
        double a, b;
        double x_ave = Math_Cal.average(x);
        double y_ave = Math_Cal.average(y);
        for (int i = 0;i < x.length;i++){
            xy[i] = x[i] * y[i];
            xx[i] = x[i] * x[i];
        }
        double xy_ave = Math_Cal.average(xy);
        double xx_ave = Math_Cal.average(xx);
        a = (xy_ave - x_ave * y_ave) / (xx_ave - x_ave * x_ave);
        b = y_ave - a * x_ave;
        double[] res = new double[] {a,b};
        return res;
    }
}


class Electron_Charge_Mass_Ratio
{
    private static double L;  /*coil length*/
    private static double D_ex;  /*coil external diameter*/
    private static double D_in;  /*coil internal diameter*/
    private static double l;  /*first distance*/
    private static double l_mid;  /*middle distance*/
    private static double N;  /*number of windings*/
    private static double K;
    private static double[] C;
    private static double[] I1, I2, II1, II2, III;
    private static double I_ave, II_ave;
    private static double ecmr1, ecmr2;
    private static double[] ecmr;


    private static double calculate_electron_charge_mass_ratio(double i, int voltage, int type){
        if (type == 3)
            return K * voltage / (N * N * i * i);
        else
            return C[type] * voltage / (N * N * i * i);
    }


    public static double[] get_result(double[][] data) {
            /*
            * data[0] 螺线管长度L;
            * data[1] 零点场法 电压为850V时 第一次聚焦时 电流方向倒转前 励磁电流的大小 一般六个;
            * data[2] 零点场法 电压为850V时 第一次聚焦时 电流方向倒转后 励磁电流的大小 一般六个;
            * data[3] 零点场法 电压为850V时 第二次聚焦时 电流方向倒转前 励磁电流的大小 一般六个;
            * data[4] 零点场法 电压为850V时 第二次聚焦时 电流方向倒转后 励磁电流的大小 一般六个;
            * data[5] 零点场法 电压为850V时 第三次聚焦时 电流方向倒转前 励磁电流的大小 一般六个;
            * data[6] 零点场法 电压为850V时 第三次聚焦时 电流方向倒转后 励磁电流的大小 一般六个;
            * data[7] 零点场法 电压为950V时 第一次聚焦时 电流方向倒转前 励磁电流的大小 一般六个;
            * data[8] 零点场法 电压为950V时 第一次聚焦时 电流方向倒转后 励磁电流的大小 一般六个;
            * data[9] 零点场法 电压为950V时 第二次聚焦时 电流方向倒转前 励磁电流的大小 一般六个;
            * data[10] 零点场法 电压为950V时 第二次聚焦时 电流方向倒转后 励磁电流的大小 一般六个;
            * data[11] 零点场法 电压为950V时 第三次聚焦时 电流方向倒转前 励磁电流的大小 一般六个;
            * data[12] 零点场法 电压为950V时 第三次聚焦时 电流方向倒转后 励磁电流的大小 一般六个;
            * data[13] 电场偏转法 电压为850 偏转角 pi/4 时 励磁电流的大小 一般六个;
            * data[14] 电场偏转法 电压为950 偏转角 pi/4 时 励磁电流的大小 一般六个;
            * data[15] 电场偏转法 电压为850 偏转角 pi/2 时 励磁电流的大小 一般六个;
            * data[16] 电场偏转法 电压为950 偏转角 pi/2 时 励磁电流的大小 一般六个;
            * data[17] 电场偏转法 电压为850 偏转角 pi 时 励磁电流的大小 一般六个;
            * data[18] 电场偏转法 电压为950 偏转角 pi 时 励磁电流的大小 一般六个;
            * data[19] 螺线管外径D_ex;
            * data[20] 螺线管内径D_in;
            * data[21] 第一聚焦点到光屏的距离l;
            * data[22] X偏转板的中间位置到光屏的距离l_mid;
            * data[23] 螺线管匝数;
            */
        L = Math_Cal.average(data[0]);
        D_ex = Math_Cal.average(data[19]);
        D_in = Math_Cal.average(data[20]);
        l = Math_Cal.average(data[21]);
        l_mid = Math_Cal.average(data[22]);
        N = Math_Cal.average(data[23]);
        double D = (D_ex + D_in) / 2;
        K = L * L * Math.pow(10.0, 14.0) / (2 * Math.pow(Math.sqrt(D * D / 4 + (L / 2 + l / 2) * (L / 2 + l / 2)) - Math.sqrt(D * D / 4 + (L / 2 - l / 2) * (L / 2 - l / 2)), 2));
        double c = l * l / (l_mid * l_mid) * K;
        C = new double[]{c / 16, c / 4, c};


        I1 = new double[]{Math_Cal.average(data[1]),
                Math_Cal.average(data[2]),
                Math_Cal.average(data[3]),
                Math_Cal.average(data[4]),
                Math_Cal.average(data[5]),
                Math_Cal.average(data[6])};
        I2 = new double[]{(I1[0] + I1[1]) / 2, (I1[2] + I1[3]) / 2, (I1[4] + I1[5]) / 2};
        I_ave = (I2[0] + I2[1] + I2[2]) / 6;
        ecmr1 = calculate_electron_charge_mass_ratio(I_ave, 850, 3);


        II1 = new double[]{Math_Cal.average(data[7]),
                Math_Cal.average(data[8]),
                Math_Cal.average(data[9]),
                Math_Cal.average(data[10]),
                Math_Cal.average(data[11]),
                Math_Cal.average(data[12])};
        II2 = new double[]{(II1[0] + II1[1]) / 2, (II1[2] + II1[3]) / 2, (II1[4] + II1[5]) / 2};
        II_ave = (II2[0] + II2[1] + II2[2]) / 6;
        ecmr2 = calculate_electron_charge_mass_ratio(II_ave, 950, 3);


        III = new double[]{Math_Cal.average(data[13]),
                Math_Cal.average(data[14]),
                Math_Cal.average(data[15]),
                Math_Cal.average(data[16]),
                Math_Cal.average(data[17]),
                Math_Cal.average(data[18])};
        ecmr = new double[]{calculate_electron_charge_mass_ratio(III[0], 850, 0),
                calculate_electron_charge_mass_ratio(III[1], 950, 0),
                calculate_electron_charge_mass_ratio(III[2], 850, 1),
                calculate_electron_charge_mass_ratio(III[3], 950, 1),
                calculate_electron_charge_mass_ratio(III[4], 850, 2),
                calculate_electron_charge_mass_ratio(III[5], 950, 2)};

        double[] res = new double[]{K, C[0], C[1], C[2],
                I1[0], I1[1], I1[2], I1[3], I1[4], I1[5], I2[0], I2[1], I2[2], I_ave, ecmr1,
                II1[0], II1[1], II1[2], II1[3], II1[4], II1[5], II2[0], II2[1], II2[2], II_ave, ecmr2,
                III[0], III[1], III[2], III[3], III[4], III[5],
                ecmr[0], ecmr[1], ecmr[2], ecmr[3], ecmr[4], ecmr[5]};
        return res;
    }
    /*
    * res[0] 常数K;
    * res[1-3] 偏转角分别为 pi/4 pi/2 pi 时的常数C;
    * res[4-9] 零点场法 电压为850V时 平均励磁电流的大小;
    * res[10-12] 零点场法 电压为850V时 分别在第一第二次第三聚焦时 平均励磁电流的大小;
    * res[13] 零点场法 电压为850V时 励磁电流均值;
    * res[14] 零点场法 电压为850V时 测得电子荷质比;
    * res[15-20] 零点场法 电压为950V时 平均励磁电流的大小;
    * res[21-23] 零点场法 电压为950V时 分别在第一第二次第三聚焦时 平均励磁电流的大小;
    * res[24] 零点场法 电压为950V时 励磁电流均值;
    * res[25] 零点场法 电压为850V时 测得电子荷质比;
    * res[26-31] 电场偏转法 平均励磁电流的大小;
    * res[32-37] 电场偏转法 测得电子荷质比;
    */
}

class Focal_Distance
{
    private static double width_diff = 0.6;
    public static double zizhunfa(double data[][])
        /* data[0] 物屏位置 一个;
           data[1] 旋转透镜前成像位置 一般六个;
           data[2] 旋转后透镜成像位置 一般六个;
           */
    {
        double x1 = Math_Cal.average(data[1]);
        double x2 = Math_Cal.average(data[2]);
        return (x1 + x2 + width_diff) / 2 - Math_Cal.average(data[0]);
        /* 返回自准法测得凸透镜焦距 */
    }

    public static double gongefa(double data[][])
        /* * data[0] 物屏位置 一个;
           * data[1] 成大像位置 一般六个;
           * data[2] 成小像位置 一般六个;
           * data[3] 像屏位置 一个;
           */
    {
        double x1 = Math_Cal.average(data[1]);
        double x2 = Math_Cal.average(data[2]);
        double d1 = Math_Cal.average(data[3]) - Math_Cal.average(data[0]);
        double d2 = x2 - x1;
        double result;
        result = (d1 * d1 - d2 * d2) / (4 * d1);
        return result;
        /* 返回共轭法测得凸透镜焦距 */
    }

    public static double shichafa(double data[][])
        /* * data[0] 物屏位置 一个;
           * data[1] 针尖位置 一般六个;
           * data[2] 像屏位置 一个;
           */
    {
        double x1 = Math_Cal.average(data[1]);
        double result;
        double d1 = Math_Cal.average(data[0]);
        double d2 = Math_Cal.average(data[2]);
        result = 1 / (1/(d2-d1+width_diff/2) + 1/(x1-d2+width_diff/2));
        return result;
        /* 返回视差法测得凸透镜焦距 */
    }

    public static double wujuxiangjufa(double data[][])
        /* data[0] 凹透镜位置 一个;
           data[1] 凸透镜成像位置 一般六个;
           data[2] 旋转前凹透镜成像位置 一般六个;
           data[3] 旋转后奥痛经成像位置 一般六个;
           */
    {
        double x1 = Math_Cal.average(data[0]), x2 = Math_Cal.average(data[1]);
        double x3 = (Math_Cal.average(data[2]) + Math_Cal.average(data[3])) / 2;
        double result;
        result = 1 / (1/(x1-x2+width_diff/2) + 1/(x3-x1+width_diff/2));
        return result;
        /* 返回物距像距法测得凹透镜焦距*/
    }

    public static double[] get_result(double data [][])

        /* data[0] 自准法 物屏位置 一个;
         * data[1] 自准法 旋转透镜前成像位置 一般六个;
         * data[2] 自准法 旋转后透镜成像位置 一般六个;
         * data[3] 共轭法 物屏位置 一个;
         * data[4] 共轭法成大像位置 一般六个;
         * data[5] 共轭法 成小像位置 一般六个;
         * data[6] 共轭法 像屏位置 一个;
         * data[7] 视差法 物屏位置 一个;
         * data[8] 视差法 针尖位置 一般六个;
         * data[9] 视差法 像屏位置 一个;
         * data[10] 物距像距法 凹透镜位置 一个;
         * data[11] 物距像距法 凸透镜成像位置 一般六个;
         * data[12] 物距像距法 旋转前凹透镜成像位置 一般六个;
         * data[13] 物距像距法 旋转后奥痛经成像位置 一般六个;
         */
    {
        double[][] newdata0 = Arrays.copyOfRange(data,0,3);
        double[][] newdata1 = Arrays.copyOfRange(data,3,7);
        double[][] newdata2 = Arrays.copyOfRange(data,7,10);
        double[][] newdata3 = Arrays.copyOfRange(data,10,14);
        double res0 = zizhunfa(newdata0);
        double res1 = gongefa(newdata1);
        double res2 = shichafa(newdata2);
        double res3 = wujuxiangjufa(newdata3);
        double[] res = new double[] {res0,res1,res2,res3};
        return res;
    }
   /* res[0] 自准法测得凸透镜焦距;
    * res[1] 共轭法测得凸透镜焦距;
    * res[2] 视差法测得凸透镜焦距;
    * res[3] 物距像距法测得凹透镜焦距;
    */

}

class Optical_Angel_Gauge
{
    public static double[] get_result(double data[][])
        /*
         *data[0] 反射法 准线的中心垂直线对准反射狭缝像时的角度 φ1;
          data[1] 反射法 准线的中心垂直线对准反射狭缝像时的角度 φ2;
          data[2] 反射法 从棱镜左面反射的狭缝像时的角度 φ'1;
          data[3] 反射法 从棱镜左面反射的狭缝像时的 角度 φ'2;
          data[4] 自准法 AB面十字像落在交点时 游标方位角读数 θ1;
          data[5] 自准法 AB面十字像落在交点时 游标方位角读数 θ2;
          data[6] 自准法 AC面十字像落在交点时 游标方位角读数 θ'1;
          data[7] 自准法 AC面十字像落在交点时 游标方位角读数 θ'2;
          data[8] 求折射率 谱线在叉丝处发生逆转时 游标方位角读数 θ;
          data[9] 求折射率 谱线在叉丝处发生逆转时 游标方位角读数 θ';
          data[10] 求折射率 望远镜叉丝对准狭缝中点时 游标方位角读数 θ0;
          data[11] 求折射率 望远镜叉丝对准狭缝中点时 游标方位角读数 θ'0;
          注意 一次实验四个数据
          */
    {
        double[] tmp1 = new double[data[0].length];
        double tmpres;
        for (int i = 0;i < data[0].length;i++) {
            tmpres = 0.25 * ((data[2][i] - data[0][i]) + (data[3][i] - data[1][i]));
            tmp1[i] = tmpres;
        }
        double[] tmp2 = new double[data[4].length];
        for (int i = 0; i < data[4].length;i++){
            tmpres = 0.5 * (data[6][i] - data[4][i] + data[7][i] - data[5][i]);
            tmp2[i] = tmpres;
        }
        double minval = 0.5 * (Math_Cal.average(data[8]) - Math_Cal.average(data[10]) +
                Math_Cal.average(data[9]) - Math_Cal.average(data[11]));
        double res1 = Math_Cal.average(tmp1);
        double res2 = 180 - Math_Cal.average(tmp2);
        double arc_a = Math.PI * res1 / 180;
        double arc_minval = Math.PI * minval / 180;
        double ref = Math.sin(0.5 * (arc_minval + arc_a)) / Math.sin(0.5 * arc_a);
        double[] res = new double[]{res1,res2,minval,ref};
        return res;
        /* res[0] 反射法测得三棱镜顶角角度;
        *  res[1] 自准法测得三棱镜顶角角度;
        *  res[2] 最小偏向角;
        *  res[3] 单色光的折射率;
        *  */
    }
}

class Moment_of_Inertia
{
    private static final double Pi = 3.1416;
    private static double m_weight;
    private static double m_circle;
    private static double m_ring;
    private static double g;
    private static double d_ring_in;
    private static double d_ring_ex;
    private static double d_circle;
    private static double d_roller;
    private static double get_angular_acceleration(double[] t)
    {
        int p = t.length / 2;
        double sum = 0;
        for (int n = 1;n <= p;n++){
            int m = t.length + 1 - n;
            sum += 2 * Pi * (n * t[m-1] - m * t[n-1]) / (t[n-1] * t[n-1] * t[m-1] - t[m-1] * t[m-1] * t[n-1]);
        }
        return sum / p;
    }
    private static double get_inertia(double a1,double a2)
    {
        return m_weight * d_roller / 2.0 * (g - d_roller / 2 * a2) / (a2 - a1);
    }

    public static double[] get_result(double[][] data)
        /*
         * data[0] 砝码质量;
          data[1] 无样品无砝码时时间 一般十二个 最好偶数;
          data[2] 无样品有砝码时时间 一般八个 最好偶数;
          data[3] 有圆盘无砝码时时间 一般十二个 最好偶数;
          data[4] 有圆盘有砝码时时间 一般八个 最好偶数;
          data[5] 有圆环无砝码时时间 一般十二个 最好偶数;
          data[6] 有圆环有砝码时时间 一般八个 最好偶数;
          data[7] 圆环内直径;
          data[8] 圆环外直径;
          data[9] 塔轮直径;
          data[10] 圆盘直径;
          data[11] 圆盘质量;
          data[12] 圆环质量;
          data[13] 重力加速度;
          */
    {
        m_weight = Math_Cal.average(data[0]);
        m_circle = Math_Cal.average(data[11]);
        m_ring = Math_Cal.average(data[12]);
        g = Math_Cal.average(data[13]);
        d_ring_in = Math_Cal.average(data[7]);
        d_ring_ex = Math_Cal.average(data[8]);
        d_roller = Math_Cal.average(data[9]);
        d_circle = Math_Cal.average(data[10]);
        double[] a = new double[6];
        for (int i = 0; i < 6;i++){
            a[i] = get_angular_acceleration(data[i + 1]);
        }
        double j1 = get_inertia(a[0],a[1]);
        double jj2 = get_inertia(a[2],a[3]);
        double jj3 = get_inertia(a[4],a[5]);
        double j2 = jj2 - j1;
        double j3 = jj3 - j1;
        double j2_th = 0.5 * m_circle * (d_circle/2) * (d_circle/2);
        double j3_th = 0.5 * m_ring * ((d_ring_in/2)*(d_ring_in/2) + (d_ring_ex/2)*(d_ring_ex/2));
        double error1 = (j2 - j2_th) / j2_th;
        double error2 = (j3 - j3_th) / j3_th;
        double[] res = new double[] {a[0],a[1],a[2],a[3],a[4],a[5],j1,jj2,j2,j2_th,error1,jj3,j3,j3_th,error2};
        return res;
        /*
         * res[0] 无样品无砝码时角加速度;
         * res[1] 无样品有砝码时角加速度;
         * res[2] 有圆盘无砝码时角加速度;
         * res[3] 有圆盘有砝码时角加速度;
         * res[4] 有圆环无砝码时角加速度;
         * res[5] 有圆环有砝码时角加速度;
         * res[6] 无样品时转动惯量;
         * res[7] 有圆盘时转动惯量;
         * res[8] 圆盘转动惯量;
         * res[9] 圆盘转动惯量理论值;
         * res[10] 误差;
         * res[11] 有圆环时转动惯量;
         * res[12] 圆环转动惯量;
         * res[13] 圆环转动惯量理论值;
         * res[14] 误差;
         */
    }
}