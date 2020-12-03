package com.example.servicioenelarranque;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;
import androidx.core.app.NotificationCompat;

public class MiServicioIntenso extends JobIntentService {
String LOG_TAG = "MiServicioIntenso";
    static final int JOB_ID = 12111;
    static final int ID = 1;
    static final String ID_CHANNEL = "nombreCanal" ;

    public MiServicioIntenso() {
    }


    static void encolarTrabajo(Context context, Intent work){
        enqueueWork(context, MiServicioIntenso.class, JOB_ID, work);

    }


    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        while(true){
            Log.d(LOG_TAG, "Comienzo a currar");
            mandarNotificacion(getApplicationContext());
            try {
                Thread.sleep(1000*30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void mandarNotificacion(Context applicationContext) {


        NotificationManager notificationManager =
                (NotificationManager) applicationContext.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(applicationContext, MainActivity.class);
        Random random = new Random();

        intent.putExtra("MENSAJE", "El número es: "+random.nextInt(100000));


        PendingIntent pendingIntent = PendingIntent.getActivity(applicationContext,
                ID+random.nextInt(10), intent, 0);


        Notification notification = new NotificationCompat.Builder(applicationContext, ID_CHANNEL)

        .setContentTitle("Notificación de prueba").setContentText("Un texto divertido").
                        setSmallIcon(R.drawable.ic_launcher_background).
                setContentIntent(pendingIntent).build();

        notificationManager.notify(1,notification);

    }
}