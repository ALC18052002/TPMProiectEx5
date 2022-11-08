package com.company;

import java.util.concurrent.atomic.AtomicBoolean;

public class MyThread2 extends Thread
{
    public static int[] level;
    public static int[] victim;
    private int id;
    private int n;
    private MyThread2()
    {

    }

    public MyThread2(int id, int n)
    {
        if(level == null )
        {
            level = new int[n + 1];
        }
        if(victim == null )
        {
            victim = new int[n];
        }
        this.n = n;
        this.id = id;
    }

    private boolean exists_k (int i )
    {
        int levelI = level[i];
        for ( int k = 1 ; k < n ; ++k )
        {
            if(i != k )
            {
                if(level[k] > levelI)
                {
                    return true;
                }
            }
        }
        return false;
    }

    private void lock(int i)
    {
        for (int L = 1; L < n; L++) {
            level[i] = L;
            victim[L] = i;
            while ( exists_k(i)  && victim [L] == i && level[i] != 0) {};
            level[i] = 0;
        }
    }

    private void unlock(int i)
    {
        level[i] = 0;
    }

    public void run()
    {
        lock(this.id);
        //System.out.println( this.id + "Accessed critical section");
        unlock(this.id);
    }
}
