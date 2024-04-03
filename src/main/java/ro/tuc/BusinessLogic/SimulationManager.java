package ro.tuc.BusinessLogic;

import ro.tuc.GUI.SimulationFrame;
import ro.tuc.Model.Task;
import ro.tuc.Model.Server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class SimulationManager implements Runnable
{

   // /*
    public int nrTasks;
    public int nrServers;
    public int maxSimulationTime;
    public int minArrivalTime;
    public int maxArrivalTime;

    public int minServiceTime;
    public int maxServiceTime;
    String res = new String();

    int totalServiceTime = 0;
    double averageServiceTime = 0;

    private Scheduler scheduler;
    private List<Task> generatedTasks;
    SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME;



    SimulationFrame frame = new SimulationFrame("My QueueManagement App");


    // */

    // test 1
	 /*
	public int nrTasks = 4;
	public int nrServers = 2;
	public int maxSimulationTime = 60;
	public int minArrivalTime = 2;
	public int maxArrivalTime = 30;
	public int minServiceTime = 2;
	public int maxServiceTime = 4;
     */

    /*
    public int nrTasks = 15;
    public int nrServers = 3;
    public int maxSimulationTime = 30;
    public int minArrivalTime = 2;
    public int maxArrivalTime = 20;
    public int minServiceTime = 2;
    public int maxServiceTime = 4;

     */

    //test 2
	/*
	public int nrTasks = 50;
	public int nrServers = 5;
	public int maxSimulationTime = 60;
	public int minArrivalTime = 2;
	public int maxArrivalTime = 40;
	public int minServiceTime = 1;
	public int maxServiceTime = 7;
    */

    //test 3
		/*
		public int nrTasks = 1000;
		public int nrServers = 20;
		public int maxSimulationTime = 200;
		public int minArrivalTime = 10;
		public int maxArrivalTime = 100;
		public int minServiceTime = 3;
		public int maxServiceTime = 9;
	    */



    public SimulationManager()
    {


        frame.setVisible(true);
        Semaphore semaphore = new Semaphore(0);

        frame.getStartt().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String s, s1, s2, s3, s4, s5, s6;
                s = frame.getTextField1().getText();
                s1 = frame.getTextField2().getText();
                s2 = frame.getTextField3().getText();
                s3 = frame.getTextField4().getText();
                s4 = frame.getTextField5().getText();
                s5 = frame.getTextField6().getText();
                s6 = frame.getTextField7().getText();


                if (!s.isEmpty() && !s1.isEmpty() && !s2.isEmpty() && !s3.isEmpty() && !s4.isEmpty() && !s5.isEmpty() && !s6.isEmpty())
                {

                    maxSimulationTime = Integer.valueOf(s);
                    minArrivalTime = Integer.valueOf(s1);
                    maxArrivalTime = Integer.valueOf(s2);
                    minServiceTime = Integer.valueOf(s3);
                    maxServiceTime = Integer.valueOf(s4);
                    nrTasks = Integer.parseInt(s5);
                    nrServers = Integer.valueOf(s6);
                } else
                {

                    frame.getTextPane1().setText("Invalid input");
                }
                semaphore.release();

            }
        });
        try
        {

            semaphore.acquire();
        } catch (InterruptedException ex)
        {
            ex.printStackTrace();
        }

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());

        // /*
        PrintStream out;

        try
        {
            out = new PrintStream(new FileOutputStream("output_" + timeStamp + ".txt", true), false);
            System.setOut(out);
        } catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // */

        scheduler = new Scheduler(nrServers, nrTasks, selectionPolicy);
        generatedTasks=Collections.synchronizedList(new ArrayList<>());

        this.generateRandomTasks();

    }

    private void generateRandomTasks()
    {

        int gServiceTime;
        int gArrivalTime;
        Random random = new Random();
        Task auxTask;

        System.out.println();
        for (int i = 0; i < this.nrTasks; i++)
        {
            gServiceTime = random.nextInt(maxServiceTime - minServiceTime) + minServiceTime;
            totalServiceTime += gServiceTime;
            gArrivalTime = random.nextInt(maxArrivalTime - minArrivalTime) + minArrivalTime;
            auxTask = new Task(i, gArrivalTime, gServiceTime);
            generatedTasks.add(auxTask);
            System.out.println("Task: id= " + auxTask.getId1() + " arrival time= " + auxTask.getArrivalTime()
                    + " service time = " + auxTask.getServiceTime());
            res+="Task: id= " + auxTask.getId1() + " arrival time= " + auxTask.getArrivalTime()
                    + " service time = " + auxTask.getServiceTime()+"\n";
        }
        generatedTasks.sort(Comparator.comparing(Task::getArrivalTime));

        System.out.println("Generated tasks list: " + Arrays.toString(generatedTasks.toArray()));
        System.out.println("\n Start:\n");
        res += "Generated tasks list: " + Arrays.toString(generatedTasks.toArray() );
        res+="\n\nSTART\n\n";
        frame.getTextPane1().setText(res);
        averageServiceTime = (double) totalServiceTime / nrTasks;
    }



    @Override
    public void run() {
        int currentTime = 0;
        //int totalServiceTime = 0;

        while (currentTime < maxSimulationTime) {

res="";
                List<Task> gen;
                gen = generatedTasks;
                Task auxTask=new Task(0,0,0);

                Iterator<Task> iterator = generatedTasks.iterator();
                while (iterator.hasNext())
                {
                    auxTask=iterator.next();
                    if (auxTask.getArrivalTime() == currentTime) {
                        scheduler.dispatchTask(auxTask);
                        iterator.remove();
                    } else if (auxTask.getArrivalTime() > currentTime) {
                        break;
                    }
                }


            System.out.println("current time:"+currentTime );
            System.out.println("waiting tasks: " + Arrays.toString(generatedTasks.toArray()));
            res = res + "\n Current time: " + Integer.toString(currentTime)+"\n";
            res+="waiting tasks: "+Arrays.toString(generatedTasks.toArray());
            res+="\n";
            for (int ii = 0; ii < nrServers; ii++)
            {
                System.out.print( scheduler.getServers().get(ii).toString());
                res+=scheduler.getServers().get(ii).toString();
            }
            frame.getTextPane1().setText(res);
            currentTime++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("CLOSED");
        res+="\nCLOSED\n";
        System.out.println("AverageServiceTime="+averageServiceTime);
        res = res +"AverageServiceTime="+averageServiceTime;
        frame.getTextPane1().setText(res);

    }

    public static void main(String[] args)
    {
        SimulationManager simulationmanager = new SimulationManager();
            Thread thread = new Thread(simulationmanager);
            thread.start();

    }

}

