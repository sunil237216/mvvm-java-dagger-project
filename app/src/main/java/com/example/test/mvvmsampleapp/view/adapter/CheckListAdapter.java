package com.example.test.mvvmsampleapp.view.adapter;
import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.test.mvvmsampleapp.R;
import com.example.test.mvvmsampleapp.service.model.Phase3Response;

import java.util.List;

public class CheckListAdapter extends RecyclerView.Adapter<CheckListAdapter.GenericViewHolder> {
    private List<Phase3Response.CheckListsBean> phase3List;
    private onClickInterface onClick;
    private Context context;
    private int value;


    public ObservableField<String> user_remark=new ObservableField<>();


    public CheckListAdapter(Context context, List<Phase3Response.CheckListsBean> phase3List) {
        this.phase3List = phase3List;
        this.context = context;
    }

    @NonNull
    @Override
    public GenericViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.check_list_view, parent, false);

        return new GenericViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder genericViewHolder, int position) {

        final Phase3Response.CheckListsBean list = phase3List.get(position);

            System.out.println(list.isIsActive());
       if(list.isIsActive()) {
          genericViewHolder.ok.setChecked(true);
       }
        if(list.getStatusId() == 1)
        {
            genericViewHolder.ok.setChecked(true);
        }
        else if(list.getStatusId() == 2){
            genericViewHolder.notok.setChecked(true);
        }

        genericViewHolder.title.setText(list.getDescription());

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, list.getRemarkList());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genericViewHolder.remarklist.setAdapter(dataAdapter);
        genericViewHolder.remarklist.setSelection(dataAdapter.getCount() - 1);

        ArrayAdapter myAdap = (ArrayAdapter) genericViewHolder.remarklist.getAdapter(); //cast to an ArrayAdapter

        int spinnerPosition = myAdap.getPosition(list.getRemarks());

        genericViewHolder.remarklist.setSelection(spinnerPosition);

        genericViewHolder.remarklist.setOnItemSelectedListener(null);
        genericViewHolder.ok.setOnCheckedChangeListener(null);
        genericViewHolder.radioGroup.setOnCheckedChangeListener(null);
        genericViewHolder.notok.setOnCheckedChangeListener(null);



      genericViewHolder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.radioButtonok:

                        value = 1;
                        genericViewHolder.remarklist.setSelection(dataAdapter.getCount() - 1);
                        list.setIsActive(true);




                        break;
                    case R.id.radioButtonnotok:

                        value = 2;
                        list.setIsActive(true);
                        genericViewHolder.remarklist.setSelection(dataAdapter.getCount() - 1);

                        break;
                }

            }
        });

      genericViewHolder.userRemark.addTextChangedListener(new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence s, int start, int count, int after) {

          }

          @Override
          public void onTextChanged(CharSequence s, int start, int before, int count) {


              onClick.onItemClickRec(position, value, s.toString(), list.getId());
          }

          @Override
          public void afterTextChanged(Editable s) {

          }
      });


        genericViewHolder.remarklist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            //    genericViewHolder.userRemark.setVisibility(View.GONE);
                String item = parent.getItemAtPosition(position).toString();
                if(item.equalsIgnoreCase("Other"))
                {
                    genericViewHolder.userRemark.setVisibility(View.VISIBLE);

                }
                else{
                    onClick.onItemClickRec(position, value, item, list.getId());
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    @Override
    public int getItemCount() {
        return phase3List.size();
    }

    class GenericViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year;
        private RadioGroup radioGroup;
        private Spinner remarklist;
        private RadioButton ok, notok;
        private EditText userRemark;


        GenericViewHolder(View view) {
            super(view);
            this.setIsRecyclable(false);
            title = view.findViewById(R.id.title);
            remarklist = view.findViewById(R.id.spinner_remarklist);
            ok = view.findViewById(R.id.radioButtonok);
            notok = view.findViewById(R.id.radioButtonnotok);
            radioGroup = view.findViewById(R.id.radioGroup);
            userRemark=view.findViewById(R.id.user_remark);

        }

    }

    public interface onClickInterface {

        void onItemClickRec(int position, int value, String remark, int id);



    }

    public void setOnClickListener(onClickInterface onClick) {
        this.onClick = onClick;
    }
}
