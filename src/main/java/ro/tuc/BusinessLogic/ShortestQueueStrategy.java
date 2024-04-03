package ro.tuc.BusinessLogic;

import ro.tuc.Model.Server;
import ro.tuc.Model.Task;

import java.util.List;

public class ShortestQueueStrategy implements Strategy {

    int maxTasksPerServer = -1;

    ShortestQueueStrategy(int m) {
        this.maxTasksPerServer = m;
    }


    public void addTask(List<Server> servers, Task t) {
        int minNrTasks = -1;
        int minNrTasksServerId = -1;
        String aux=new String();
        for (int i = 0; i < servers.size(); i++) {
            if (servers.get(i).getNrTasks() < this.maxTasksPerServer) {
                if (minNrTasks == -1) {

                    minNrTasks = servers.get(i).getNrTasks();
                } else if (minNrTasks > servers.get(i).getNrTasks()) {
                    minNrTasks = servers.get(i).getNrTasks();
                    minNrTasksServerId = i;
                }
            }
        }

        if (minNrTasksServerId != -1 && minNrTasksServerId != -1) {
            servers.get(minNrTasksServerId).addTask(t);
        }
    }

}
