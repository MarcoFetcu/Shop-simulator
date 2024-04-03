package ro.tuc.Model;

public class Task extends Thread
{
    private int id;
    private int arrivalTime;
    private int serviceTime;


    public Task(int id, int arrivalTime,int serviceTime)
    {
        this.id=id;
        this.arrivalTime=arrivalTime;
        this.serviceTime=serviceTime;
    }

    public String toString()
    {
        return "Task " + id + ":[" + arrivalTime + "," + serviceTime + "]";
    }

    public void setId(int i)
    {
        this.id=i;
    }

    public void setArrivalTime(int a)
    {
        this.arrivalTime=a;
    }

    public void setServiceTime(int s)
    {
        this.serviceTime=s;

    }

    public int getId1()
    {
        return this.id;
    }

    public int getArrivalTime()
    {
        return this.arrivalTime;
    }

    public int getServiceTime()
    {
        return this.serviceTime;
    }
}
