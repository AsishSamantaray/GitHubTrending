package com.example.asus.jsoupdemoapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    List<Repo> al;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        Bundle bundle = getIntent().getExtras();
        String time = bundle.getString("position");
        new GitHubTask().execute("https://github.com/trending?since="+time);
    }


    class GitHubTask extends AsyncTask<String, Void, ArrayList> {

        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(ResultActivity.this);
            progressDialog.setTitle("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Please Wait...");
            progressDialog.show();
        }

        @Override
        protected ArrayList doInBackground(String... args) {
            String url =  args[0];
//            Repo rr = new Repo() ;
//            List<List<rr>> r = new ArrayList<List<rr>>();
//            Repo r = new Repo() ;
            try {
                Document document = Jsoup.connect(url).get();
                Element element = document.getElementsByClass("repo-list").first();
                Elements elements = element.getElementsByTag("li");

                al = new ArrayList<Repo>();

                for(Element el : elements) {
                    Repo r = new Repo() ;
                    Element repo = el.getElementsByTag("a").first();
                    String repoName = repo.text();
                    Element para = el.getElementsByTag("p").first();
                    String repoContent = para.text();
                    String language = el.select("span[itemprop=programmingLanguage]").text();
                    String star = el.getElementsByClass("muted-link d-inline-block mr-3").text();
                    r.setRepoName(repoName);
                    r.setRepoContent(repoContent);
                    r.setLanguage(language);
                    r.setStar(star);
                    al.add(r);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return (ArrayList) al;
        }

        @Override
        protected void onPostExecute(ArrayList s) {
            super.onPostExecute(s);

//            al.add(s);
            progressDialog.dismiss();
            JsoupAdapter adapter = new JsoupAdapter(ResultActivity.this, s);
            recyclerView.setAdapter(adapter);

        }
    }

}
