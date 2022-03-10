package suanfa;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 随机生成指令
 */
public class RandList {
    private int m0,m1,m2,m3;

    @Test
    public void randList(){
        Random random = new Random();
      List<Integer> integerList = new ArrayList<Integer>();
        //生成0-159之间的随机数nextint(int n)表示区间[0,n)取随机整数
        m0 = random.nextInt(50);
        for (int i=0;i<20;i++) {
            integerList.add(m0);
            integerList.add(m0 + 1);
            integerList.add(m0 + 2);
            if(m0==0) {
                m1=m0;
            }else {
                //向前取
                m1 = random.nextInt(m0);
            }
            integerList.add(m1);
            integerList.add(m1 + 1);
            integerList.add(m1 + 2);
            //向后取
            m2 = random.nextInt(160 - m1 - 3) + m1+3;
            integerList.add(m2);
            integerList.add(m2 + 1);
            integerList.add(m2 + 2);
            if(m2==0) {
                m3=m2;
            }else {
                //向前取
                m3 = random.nextInt(m2);
            }
            integerList.add(m3);
            integerList.add(m3+1);
            integerList.add(m3+2);
            m0 = random.nextInt(160 - m3 - 3) + m3+3;
        }

        //讲大于159的数remove
        for (int i=0;i<integerList.size();){
            if (integerList.get(i)>50){
                integerList.remove(i);
            } else {
                //integerList.set(i,integerList.get(i)/10);
                i++;
            }
        }
        /*List<Integer> lists =new ArrayList<Integer>();
        for (int i=0;i<200;i++){
            lists.add(i,integerList.get(1));
            *//*if ((i+1)%20==0){
                System.out.println();
            }*//*
        }*/
        System.out.println("生成的指令,总共有"+integerList.size()+"个指令");
        for (Integer integer:integerList){
            System.out.print(integer+" ");
        }
        System.out.println();
        //System.out.println(lists.size());
        //return integerList;

        System.out.println();
        System.out.println();
        System.out.println("**FIFO，先进先出置换算法**");
        FIFO fifo = new FIFO();
        fifo.init();
        fifo.input(integerList);
        fifo.running();



        System.out.println();
        System.out.println();
        System.out.println("**LRU，最近最久未使用置换算法**");
        LRU lru = new LRU();
        lru.init();
        lru.input(integerList);
        lru.running();


        System.out.println();
        System.out.println();
        System.out.println("**OPT：最佳置换算法**");
        OPT opt = new OPT();
        opt.init();
        opt.input(integerList);
        opt.running();
    }


}