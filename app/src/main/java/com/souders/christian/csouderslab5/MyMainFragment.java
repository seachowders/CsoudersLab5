package com.souders.christian.csouderslab5;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyMainFragment.UpdateListener} interface
 * to handle interaction events.
 */
public class MyMainFragment extends Fragment {

    private UpdateListener mListener;

    public MyMainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_my_main, container, false);
        Button button = (Button)view.findViewById(R.id.UpdateButton);
        button.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                mListener.onFragmentInteraction(String.valueOf(System.currentTimeMillis()));
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(View view) {

        if (mListener != null) {
            mListener.onFragmentInteraction(String.valueOf(System.currentTimeMillis())); //added toString to this to fix error
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof UpdateListener) {
            mListener = (UpdateListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface UpdateListener {

        void onFragmentInteraction(String string);
    }
}
