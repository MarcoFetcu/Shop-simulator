package ro.tuc.BusinessLogic;

import ro.tuc.Model.Server;
import ro.tuc.Model.Task;

import java.util.ArrayList;
import java.util.List;

public class Scheduler
{
    private List<Server> servers;
    private int maxNoServers;
    private int maxTasksPerServer;
    private Strategy strategy;

    public Scheduler(int maxNoServers, int maxTasksPerServer, SelectionPolicy policy)
    {
        this.maxNoServers = maxNoServers;
        this.maxTasksPerServer = maxTasksPerServer;
        this.servers = new ArrayList<Server>(maxNoServers);

        for (Integer i = 0; i < maxNoServers; i++)
        {
            servers.add(new Server(i,maxTasksPerServer));
            Thread thread = new Thread(servers.get(i));
            thread.start();
        }
        changeStrategy(policy);
    }

    public void changeStrategy(SelectionPolicy policy)
    {
        if (policy == SelectionPolicy.SHORTEST_QUEUE)
        {
            strategy = new ShortestQueueStrategy(maxTasksPerServer);
        }

        if (policy == SelectionPolicy.SHORTEST_TIME)
        {
            strategy = new TimeStrategy(maxTasksPerServer);
        }

    }

    public void dispatchTask(Task t)
    {

        strategy.addTask(servers, t);
    }

    public List<Server> getServers() {

        return servers;
    }
}
