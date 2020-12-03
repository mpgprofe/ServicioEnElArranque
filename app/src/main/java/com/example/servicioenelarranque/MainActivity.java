package com.example.servicioenelarranque;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button buttonArracar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            String mensaje = extras.getString("MENSAJE");
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        }

        buttonArracar = findViewById(R.id.buttonArrancar);
        crearCanalNotificaciones();
        buttonArracar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MiServicioIntenso.encolarTrabajo(getApplicationContext(), new Intent());
            }
        });
    }

    void crearCanalNotificaciones(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel notificationChannel =
                    new NotificationChannel(MiServicioIntenso.ID_CHANNEL, "Queremos Marcha",
                            NotificationManager.IMPORTANCE_DEFAULT);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setShowBadge(true);

            notificationManager.createNotificationChannel(notificationChannel);
        }

    }
}