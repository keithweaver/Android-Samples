package ca.keithweaver.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private MovieAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mAdapter = new MovieAdapter(movieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);


        loadData();
    }

    private void loadData() {
        movieList.add(new Movie("The Shawshank Redemption", "Drama", "1994"));
        movieList.add(new Movie("The Godfather", "Drama", "1972"));
        movieList.add(new Movie("The Godfather: Part 2", "Drama", "1974"));
        movieList.add(new Movie("The Dark Knight", "Action", "2008"));
        movieList.add(new Movie("12 Angry Men", "Drama", "1957"));
        movieList.add(new Movie("Schindler's List", "Drama", "1993"));
        movieList.add(new Movie("Pulp Fiction", "Drama", "1994"));
        movieList.add(new Movie("The Lord of the Rings: The Return of the King", "Adventure", "2003"));
        movieList.add(new Movie("The Good, the Bad and the Ugly", "Western", "1966"));
        movieList.add(new Movie("Fight Club", "Drama", "1999"));
        movieList.add(new Movie("The Lord of the Rings: The Fellowship of the Ring", "Adventure", "2001"));
        movieList.add(new Movie("Forrest Gump", "Romance", "1994"));
        movieList.add(new Movie("Star Wars: Episode V - The Empire Strikes Back", "Action", "1980"));
        movieList.add(new Movie("Inception", "Action", "2010"));
        movieList.add(new Movie("The Lord of the Rings: The Two Towers", "Adventure", "2002"));

        mAdapter.notifyDataSetChanged();
    }
}
