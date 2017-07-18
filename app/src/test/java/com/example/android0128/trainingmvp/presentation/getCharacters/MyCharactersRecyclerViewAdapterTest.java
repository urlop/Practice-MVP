package com.example.android0128.trainingmvp.presentation.getCharacters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import com.example.android0128.trainingmvp.QualityMattersRobolectricUnitTestRunner;
import com.example.android0128.trainingmvp.R;
import com.example.android0128.trainingmvp.presentation.models.Character;

import static org.assertj.core.api.Java6Assertions.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Try to test RecyclerView. Too slow.
 */
@RunWith(QualityMattersRobolectricUnitTestRunner.class)
public class MyCharactersRecyclerViewAdapterTest {

    private MyCharactersRecyclerViewAdapter adapter;
    private LayoutInflater layoutInflater;

    @Before
    public void beforeEachTest() {
        layoutInflater = mock(LayoutInflater.class);
        List<Character> list = new ArrayList<>();
        adapter = new MyCharactersRecyclerViewAdapter(layoutInflater,
                mock(Context.class), list,
                mock(CharactersFragment.OnListFragmentInteractionListener.class));
    }

    //Tests for each method in original adapter
    @Test
    public void onCreateViewHolder_shouldCreateViewHolder() {
        ViewGroup parent = mock(ViewGroup.class);
        View itemView = mock(View.class);

        //public final View mView;
        //public Character mItem;

        TextView mIdView = mock(TextView.class);
        when(itemView.findViewById(R.id.tv_id)).thenReturn(mIdView);

        TextView mContentView = mock(TextView.class);
        when(itemView.findViewById(R.id.tv_content)).thenReturn(mContentView);

        ImageView iv_thumbnail = mock(ImageView.class);
        when(itemView.findViewById(R.id.iv_thumbnail)).thenReturn(iv_thumbnail);

        ImageView iv_favorite = mock(ImageView.class);
        when(itemView.findViewById(R.id.iv_favorite)).thenReturn(iv_favorite);

        when(layoutInflater.inflate(R.layout.row_characters, parent, false)).thenReturn(itemView);

        MyCharactersRecyclerViewAdapter.ViewHolder viewHolder = adapter.onCreateViewHolder(parent, 0);
        verify(layoutInflater).inflate(R.layout.row_characters, parent, false);

        assertThat(viewHolder.itemView).isSameAs(itemView);
        // Also, we don't expect exceptions here.
    }
}
