package com.example.android.tabianconsulting.utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.android.tabianconsulting.AdminActivity;
import com.example.android.tabianconsulting.R;
import com.example.android.tabianconsulting.models.User;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

public class EmployeesAdapter extends RecyclerView.Adapter<EmployeesAdapter.ViewHolder>{

    private static final String TAG = "EmployeesAdapter";

    private ArrayList<User> mUsers;
    private Context mContext;

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView profileImage;
        public TextView name, department;

        public ViewHolder(View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.profile_image);
            name = itemView.findViewById(R.id.name);
            department = itemView.findViewById(R.id.department);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick: selected employee: " + mUsers.get(getAdapterPosition()));

                    //open a dialog for selecting a department
                    ((AdminActivity)mContext).setDepartmentDialog(mUsers.get(getAdapterPosition()));
                }
            });
        }
    }

    public EmployeesAdapter(Context context, ArrayList<User> users) {
        mUsers = users;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        //inflate the custom layout
        View view = inflater.inflate(R.layout.layout_employee_listitem, parent, false);

        //return a new holder instance
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) throws NullPointerException{
        ImageLoader.getInstance().displayImage(mUsers.get(position).getProfile_image(), holder.profileImage);
        holder.name.setText(mUsers.get(position).getName());
        holder.department.setText(mUsers.get(position).getDepartment());
    }


    @Override
    public int getItemCount() {
        return mUsers.size();
    }
}
