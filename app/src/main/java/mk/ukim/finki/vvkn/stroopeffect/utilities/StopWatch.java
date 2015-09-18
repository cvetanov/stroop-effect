package mk.ukim.finki.vvkn.stroopeffect.utilities;

public class StopWatch {
    private long startTime;
    public StopWatch()
    {
        startTime = 0;
    }

    public void start()
    {
        restart();
    }

    public void restart()
    {
        startTime = System.currentTimeMillis();
    }

    public long getElapsedMilliseconds()
    {
        return System.currentTimeMillis() - startTime;
    }
}
