package ro.tuc.BusinessLogic;

import ro.tuc.Model.Server;
import ro.tuc.Model.Task;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.List;

public class TimeStrategy implements Strategy
{
    int maxTasksPerServer = -1;

    TimeStrategy(int m)
    {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        //PrintStream out;
        /*
        try {
            out = new PrintStream(new FileOutputStream("output_" + timeStamp + ".txt", true), false);
            System.setOut(out);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         */
        this.maxTasksPerServer = m;
    }

    public void addTask(List<Server> servers, Task t)
    {
        int minWaitingTime = -1;
        int minWaitingTimeServerId = -1;

        for (int i = 0; i < servers.size(); i++)
        {

            if (servers.get(i).getNrTasks() < this.maxTasksPerServer)
            {
                if (minWaitingTime == -1)
                {
                    minWaitingTime = servers.get(i).getWaitingPeriod();
                    minWaitingTimeServerId = i;
                } else if (minWaitingTime > servers.get(i).getWaitingPeriod())
                {
                    minWaitingTime = servers.get(i).getWaitingPeriod();
                    minWaitingTimeServerId = i;
                }
            }
        }

        if (minWaitingTime != -1 && minWaitingTimeServerId != -1)
        {
            servers.get(minWaitingTimeServerId).addTask(t);
        }
    }

}