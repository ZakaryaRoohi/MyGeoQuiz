package com.example.myapplication.controller.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.Question;
import com.example.myapplication.repository.QuestionRepository;
import com.example.myapplication.repository.RepositoryInterface;

import java.util.List;

public class QuestionListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RepositoryInterface mRepository;

    public QuestionListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepository = QuestionRepository.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question_list, container, false);
        findViews(view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        initUI();
        return view;
    }

    private void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view_questions);

    }

    private void initUI() {
        List<Question> questions = mRepository.getQuestions();
        QuestionAdapter adapter = new QuestionAdapter(questions);
        mRecyclerView.setAdapter(adapter);
    }

    private class QuestionHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewQuestion;
        private TextView mTextViewTrueAnswer;
        private CheckBox mCheckBoxIsAnswered;
        private CheckBox mCheckBoxCanCheat;
        private TextView mTextViewQuestionColor;
        public QuestionHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewQuestion = itemView.findViewById(R.id.list_row_question);
            mTextViewTrueAnswer = itemView.findViewById(R.id.list_row_true_answer);
            mCheckBoxIsAnswered=itemView.findViewById(R.id.list_row_is_answered);
            mCheckBoxCanCheat=itemView.findViewById(R.id.list_row_can_cheat);
            mTextViewQuestionColor=itemView.findViewById(R.id.list_row_text_color);
        }

        public void bindQuestion(Question question) {
            mTextViewQuestion.setText(question.getTextQuestion());
            mTextViewTrueAnswer.setText(question.getAnswerTrue() + "");
            if(question.getIsAnswered())
                mCheckBoxIsAnswered.setChecked(false);
            else
                mCheckBoxIsAnswered.setChecked(true);
            if(question.getIsAnswered())
                mCheckBoxCanCheat.setChecked(false);
            else
                mCheckBoxCanCheat.setChecked(true);
            mTextViewQuestionColor.setText(question.getQuestionTextColor().toString());
        }

    }

    private class QuestionAdapter extends RecyclerView.Adapter<QuestionHolder> {
        private List<Question> mQuestions;

        public List<Question> getQuestions() {
            return mQuestions;
        }

        public void setQuestions(List<Question> questions) {
            mQuestions = questions;
        }

        public QuestionAdapter(List<Question> questions) {
            mQuestions = questions;
        }

        @NonNull
        @Override
        public QuestionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater= LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.list_row_question,parent,false);
            QuestionHolder questionHolder= new QuestionHolder(view);
            return  questionHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull QuestionHolder holder, int position) {
        Question question = mQuestions.get(position);
        holder.bindQuestion(question);
        }

        @Override
        public int getItemCount(){
            return mQuestions.size();
        }
    }
}