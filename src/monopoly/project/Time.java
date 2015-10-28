
package monopoly.project;

public class Time {
    
    
    private int timeCount;
    private int Seconds;
    private int Minutes;
    private int Hours;
    Time()
    {
        timeCount=3580;
    }

    public int getTimeCount() {
        return timeCount;
    }

    public int getSeconds() {
        return Seconds;
    }

    public int getMinutes() {
        return Minutes;
    }

    public int getHours() {
        return Hours;
    }
    public void addTime()
    {
        timeCount++;
        if(timeCount%MonopolyProject.FPS==MonopolyProject.FPS-1)
        {
            Seconds++;
        }
        if(Seconds==60)
        {
            Seconds=0;
            Minutes++;
        }
        if(Minutes==60)
        {
            Minutes=0;
            Hours++;
        }
    }
}
