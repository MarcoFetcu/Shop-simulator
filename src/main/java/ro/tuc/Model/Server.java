package ro.tuc.Model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable
{
    private Integer sId;
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;
    public Integer maxTasksPerServer;



    public Server(Integer i, Integer m)
    {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
         /*
        PrintStream out;

        try {
            out = new PrintStream(new FileOutputStream("output_" + timeStamp + ".txt", true), false);
            //System.setOut(out);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         */


        tasks = new ArrayBlockingQueue<>(m);
        waitingPeriod = new AtomicInteger();
        waitingPeriod.set(0);
        this.sId = i;
        this.maxTasksPerServer = m;
    }

    public Integer getId()
    {
        return this.sId;
    }

    public void setId(Integer id)
    {
        this.sId = id;
    }

    public int getWaitingPeriod()
    {
        return this.waitingPeriod.get();
    }

    public void setWaitingPeriod(int x)
    {
        this.waitingPeriod.set(x);
    }
    public void addTask(Task t)
    {
        try
        {
            tasks.put(t);
        } catch (InterruptedException e)
        {
            System.out.println("Error: at task");
        }
        waitingPeriod.addAndGet(t.getServiceTime());
    }

    public Boolean isFree()
    {
        if(tasks.size()<this.maxTasksPerServer)
            return true;
        else
            return false;
    }

    public void run()
    {
        while (true)
        {
            try
            {
                Thread.sleep(1000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            if (tasks.size() > 0)
            {
                tasks.element().setServiceTime(tasks.element().getServiceTime() - 1);
                waitingPeriod.decrementAndGet();
                if (tasks.element().getServiceTime() == 0)
                {
                    try
                    {
                        tasks.take();
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public Task[] getTasks()
    {
        BlockingQueue<Task> tasks2;
        tasks2 = this.tasks;
        Task[] newTasks = new Task[tasks2.size()];
        int cnt = 0;
        Iterator<Task> iterator = tasks2.iterator();
        while (iterator.hasNext())
        {
            newTasks[cnt++] = iterator.next();
        }
        return newTasks;
    }

    public int getNrTasks()
    {
       return tasks.size();
    }

    public String toString()
    {
        String aux = new String();
        aux = "Queue: " + this.sId + ":";
        for (int i = 0; i < tasks.size(); i++)
        {
            aux += tasks.toString();
        }
        aux+="\n";
        return aux;
    }

}
