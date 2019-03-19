package com.ambulert.ambugroup.ambulert;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.SubscriptionEventListener;

public class userReport extends AppCompatActivity {

    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_report);

        submit=findViewById(R.id.btnSubmitReport);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PusherOptions options = new PusherOptions();
                options.setCluster("ap1");
                Pusher pusher = new Pusher("f6f266841f565e4e7b21", options);

                Channel channel = pusher.subscribe("hospital");

                channel.bind("alert", new SubscriptionEventListener() {
                    @Override
                    public void onEvent(String channelName, String eventName, final String data) {
                        System.out.println(data);
                    }
                });

                pusher.connect();
            }
        });
    }
}
