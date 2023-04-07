package ro.pub.cs.systems.eim.practicaltest01var07;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.Log;

import java.util.Date;
import java.util.Random;

import ro.pub.cs.systems.eim.practicaltest01var07.general.Constants;

public class ProcessingThread extends Thread {

    private Context context = null;
    private boolean isRunning = true;

    private Random random = new Random();

    private double arithmeticMean;
    private double geometricMean;

    public ProcessingThread(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        Log.d(Constants.PROCESSING_THREAD_TAG, "Thread has started! PID: " + Process.myPid() + " TID: " + Process.myTid());
        while (isRunning) {
            sendMessage();
            sleep();
        }
        Log.d(Constants.PROCESSING_THREAD_TAG, "Thread has stopped!");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction(Constants.ACTION);
        int topLeftNumber = random.nextInt();
        int topRightNumber = random.nextInt();
        int bottomLeftNumber = random.nextInt();
        int bottomRightNumber = random.nextInt();
        intent.putExtra(Constants.BROADCAST_TOP_LEFT, topLeftNumber);
        intent.putExtra(Constants.BROADCAST_TOP_RIGHT, topRightNumber);
        intent.putExtra(Constants.BROADCAST_BOTTOM_LEFT, bottomLeftNumber);
        intent.putExtra(Constants.BROADCAST_BOTTOM_RIGHT, bottomRightNumber);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}
