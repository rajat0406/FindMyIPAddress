# FindMyIPAddress
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">


    <TextView
        android:id="@+id/ip"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        android:textSize="40sp"
        android:text="TextView" />
</RelativeLayout>

#build.gradle (app)
implementation 'com.squareup.retrofit2:retrofit:2.4.0'
implementation 'com.squareup.retrofit2:converter-gson:2.4.0'

#Initialise Retrofit
Retrofit retrofit = new Retrofit.Builder()
               .baseUrl("https://api.ipify.org/")
               .addConverterFactory(GsonConverterFactory.create())
               .build();
     
#IPAddress.java
public class IPAddress {
    String ip;
}

#MainActivity.java
IPService ipService = retrofit.create(IPService.class);
        ipService.getMyIp().enqueue(new Callback<IPAddress>() {
            @Override
            public void onResponse(Call<IPAddress> call, Response<IPAddress> response) {
                IPAddress ipAddress = response.body();
                ip.setText(ipAddress.ip);
            }

            @Override
            public void onFailure(Call<IPAddress> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Connection error",Toast.LENGTH_SHORT).show();
            }
 });
 
 
#AndroidManifest.xml
<uses-permission android:name="android.permission.INTERNET"></uses-permission>              
