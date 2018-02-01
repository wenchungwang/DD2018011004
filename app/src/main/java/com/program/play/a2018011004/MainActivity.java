package com.program.play.a2018011004;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {
    ListView lv;
//    ArrayAdapter<String> adapter;
    MyAdapter adapter;
    MyHandler dataHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=(ListView)findViewById(R.id.menu_reload);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(MainActivity.this, DetailActivity.class);
                it.putExtra("link",dataHandler.newsItems.get(i).link);
                startActivity(it);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_reload:
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
//                                 String str_url = "http://rate.bot.com.tw/xrt?Lang=zh-TW";
                        String str_url = "https://www.mobile01.com/rss/news.xml";
                        URL url = null;
                        try {
                            url = new URL(str_url);
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.setRequestMethod("GET");
                            conn.connect();
                            InputStream inputStream = conn.getInputStream();
                            InputStreamReader isr = new InputStreamReader(inputStream);
                            BufferedReader br = new BufferedReader(isr);

                            StringBuilder sb = new StringBuilder();
                            String str;


                            while ((str = br.readLine()) != null) {
                                sb.append(str);
                            }

                            String str1 = sb.toString();
                            Log.d("NET", str1);
                            /* final  MyHandler 這個沒刪除到*/ dataHandler = new MyHandler();
                            SAXParserFactory spf = SAXParserFactory.newInstance();
                            SAXParser sp = spf.newSAXParser();
                            XMLReader xr = sp.getXMLReader();
                            xr.setContentHandler(dataHandler);
                            xr.parse(new InputSource(new StringReader(str1)));

                            br.close();
                            isr.close();
                            inputStream.close();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    adapter = new MyAdapter(MainActivity.this,
                                            dataHandler.newsItems);

                                    /*
                                    String data[] = new String[dataHandler.newsItems.size()];
                                    for (int i=0;i<data.length;i++)
                                    {
                                        data[i] = dataHandler.newsItems.get(i).title;
                                    }
                                    adapter = new ArrayAdapter<String>(MainActivity.this,
                                            android.R.layout.simple_list_item_1, data);
*/
                                    lv.setAdapter(adapter);
 /*
                                    adapter = new ArrayAdapter<String>(MainActivity.this,
                                            android.R.layout.simple_list_item_1, dataHandler.titles);
                                    lv.setAdapter(adapter);
   */
                                }
                            });


                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (ProtocolException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (SAXException e) {
                            e.printStackTrace();
                        } catch (ParserConfigurationException e) {
                            e.printStackTrace();
                        }

                                                      /*
                                                  } catch (MalformedURLException e) {
                                                      e.printStackTrace();
                                                  } catch (ProtocolException e) {
                                                      e.printStackTrace();
                                                  } catch (IOException e) {
                                                      e.printStackTrace();
                                                  }
*/
                    }
                }.start();
                break;
        }
        return super.onOptionsItemSelected(item);   //check
    }
/*  00
            public void click1(View v)
    {

        new Thread(){
            @Override
            public void run() {
                super.run();
//                                 String str_url = "http://rate.bot.com.tw/xrt?Lang=zh-TW";
                    String str_url = "https://www.mobile01.com/rss/news.xml";
                                 URL url = null;
                    try {
                                     url = new URL(str_url);
                                     HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                     conn.setRequestMethod("GET");
                                     conn.connect();
                                     InputStream inputStream = conn.getInputStream();
                                     InputStreamReader isr = new InputStreamReader(inputStream);
                                     BufferedReader br = new BufferedReader(isr);

                                 StringBuilder sb = new StringBuilder();
                                 String str;


                                 while ((str = br.readLine()) != null)
                                 {
                                     sb.append(str);
                                 }

                                         String str1 = sb.toString();
                                         Log.d("NET", str1);
                                         final MyHandler dataHandler = new MyHandler();
                                         SAXParserFactory spf = SAXParserFactory.newInstance();
                                         SAXParser sp = spf.newSAXParser();
                                         XMLReader xr = sp.getXMLReader();
                                         xr.setContentHandler(dataHandler);
                                         xr.parse(new InputSource(new StringReader(str1)));


                    //                                                      String str1 = sb.toString();
//                                                      Log.d("NET", str1);
/*
                                                       int index1 = str1.indexOf("日圓 (JPY)");
                                                      int index2 = str1.indexOf("本行現金賣出", index1);
                                                      int index3 = str1.indexOf("0.266", index2);
                                                      Log.d("NET", "index1:" + index1 + "index2:" + index2 + "index3:" + index3);
                                                      String data1 = str1.substring(index2+56, index2+61);
*/
                               //                       Log.d("NET", data1);

/**  00
                                                      br.close();
                                                      isr.close();
                                                      inputStream.close();
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                        adapter = new ArrayAdapter<String>(MainActivity.this,
                                                                android.R.layout.simple_list_item_1, dataHandler.titles);
                                                    lv.setAdapter(adapter);
                                                }
                                            });


                                     } catch (MalformedURLException e) {
                                         e.printStackTrace();
                                     } catch (ProtocolException e) {
                                         e.printStackTrace();
                                     } catch (IOException e) {
                                         e.printStackTrace();
                                     } catch (SAXException e) {
                                         e.printStackTrace();
                                     } catch (ParserConfigurationException e) {
                                         e.printStackTrace();
                                     }

                                                      /*
                                                  } catch (MalformedURLException e) {
                                                      e.printStackTrace();
                                                  } catch (ProtocolException e) {
                                                      e.printStackTrace();
                                                  } catch (IOException e) {
                                                      e.printStackTrace();
                                                  }
*/

// *00                             }
// *00         }.start();

/*
                                     String str = br.readLine();
                                     Log.d("NET", str);
                                     br.close();
                                     isr.close();
                                     inputStream.close();
                                 } catch (MalformedURLException e) {
                                     e.printStackTrace();
                                 } catch (ProtocolException e) {
                                     e.printStackTrace();
                                 } catch (IOException e) {
                                     e.printStackTrace();
                                 }


                         }
         }.start();
 */
 //   }
}
