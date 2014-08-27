package com.example.listviewswipedelete;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.listviewswipedelete.SlideView.OnSlideListener;

public class DeleteListViewMainActivity extends Activity implements OnItemClickListener, OnClickListener, OnSlideListener {

	private static final String TAG = "MainActivity";

	private ListViewCompat mListView;
	private List<DelBlogItem> blogItems = new ArrayList<DelBlogItem>();
	private SlideView mLastSlideViewWithStatusOn;

	private SlideAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setView();
		initView();
		setListener();
	}

	public void setView() {
		setContentView(R.layout.activity_main);
	}

	public void initView() {
		mListView = (ListViewCompat) findViewById(R.id.list);

		for (int i = 0; i < 8; i++) {
			// ���ʳ�ʼ��
			Integer id = i;
			String quizzer = "���ǳ����" + i;
			String quizzerPortrait = "http://URL/portrait.jpg";
			String grade = "�߿�";
			String lesson = "����";
			String classes = "ʫ�����";
			String askDate = "2��ǰ";
			String title = "��ʦ���ã�������һ�¹�����ʫ�������ķ�����������ʦ������������һ��ʫ������⣬������һ�£�ʫ�����������Щ�������ͣ�\nлл��ʦ";
			String imgUrl = "http://URL/question.jpg";
			String question = "��ʦ���ã�������һ�¹�����ʫ�������ķ�����������ʦ������������һ��ʫ������⣬������һ�£�ʫ�����������Щ�������ͣ�\nлл��ʦ";
			// �ش��ʼ��
			ArrayList<BlogItem.Answer> answerList = new ArrayList<BlogItem.Answer>();
			String answerer = "��ʦ����";
			String answererPortrait = "http://URL/answerer.jpg";
			String answerDate = "1��ǰ";
			String answer = "ͬѧ��ã�����Ϊ�����һ�¸߿�ʫ������ⳣ�������ͣ�����⼼�ɡ�\nһ����������������\n���ʷ�ʽ������������������...";
			// ��ӵ��ش��б�
			answerList.add(new BlogItem.Answer(answerer, answererPortrait, answerDate, answer));
			// Blog��ʼ��
			// DelBlogItem item = new BlogItem(id, quizzer, quizzerPortrait,
			// grade, lesson, classes, askDate, title, imgUrl, question,
			// answerList);
			DelBlogItem item = new DelBlogItem();
			item.setAnswerList(answerList);
			item.setAskDate(askDate);
			item.setClasses(classes);
			item.setGrade(grade);
			item.setId(id);
			item.setImgUrl(imgUrl);
			item.setLesson(lesson);
			item.setQuestion(question);
			item.setQuizzer(quizzer);
			item.setQuizzerPortrait(quizzerPortrait);
			item.setTitle(title);
			// ��ӵ�Blog�б�
			blogItems.add(item);
		}

		adapter = new SlideAdapter();
		mListView.setAdapter(adapter);
		mListView.setOnItemClickListener(this);

	}

	public void setListener() {
		// TODO Auto-generated method stub

	}

	private class SlideAdapter extends BaseAdapter {

		private LayoutInflater mInflater;

		SlideAdapter() {
			super();
			mInflater = getLayoutInflater();
		}

		@Override
		public int getCount() {
			return blogItems.size();
		}

		@Override
		public Object getItem(int position) {
			return blogItems.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			SlideView slideView = (SlideView) convertView;
			if (slideView == null) {
				View itemView = mInflater.inflate(R.layout.item_myquestion, null);

				slideView = new SlideView(DeleteListViewMainActivity.this);
				slideView.setContentView(itemView);

				holder = new ViewHolder(slideView);
				slideView.setOnSlideListener(DeleteListViewMainActivity.this);
				slideView.setTag(holder);
			} else {
				holder = (ViewHolder) slideView.getTag();
			}
			DelBlogItem item = blogItems.get(position);
			item.slideView = slideView;
			item.slideView.shrink();

			holder.tv_questionType.setText(item.getGrade() + item.getLesson() + "/" + item.getClasses());
			holder.tv_date.setText(item.getAskDate());
			holder.tv_count.setText(item.getAnswerList().size() + "");
			holder.tv_question.setText(item.getQuestion());
			holder.tv_state.setText(item.getAnswerList().get(0).getAnswerDate());
			holder.deleteHolder.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					blogItems.remove(position);
					adapter.notifyDataSetChanged();
					Toast.makeText(DeleteListViewMainActivity.this, "ɾ����" + position + "����Ŀ", 0).show();
				}
			});

			return slideView;
		}

	}

	public class DelBlogItem extends BlogItem {
		public SlideView slideView;
	}

	private static class ViewHolder {

		public TextView tv_questionType;
		public TextView tv_date;
		public TextView tv_count;
		public TextView tv_question;
		public TextView tv_state;
		public ViewGroup deleteHolder;

		ViewHolder(View view) {
			tv_questionType = (TextView) view.findViewById(R.id.tv_questionType);
			tv_date = (TextView) view.findViewById(R.id.tv_date);
			tv_count = (TextView) view.findViewById(R.id.tv_count);
			tv_question = (TextView) view.findViewById(R.id.tv_question);
			tv_state = (TextView) view.findViewById(R.id.tv_state);
			deleteHolder = (ViewGroup) view.findViewById(R.id.holder);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Toast.makeText(this, "onItemClick position=" + position, 0).show();

	}

	@Override
	public void onSlide(View view, int status) {
		if (mLastSlideViewWithStatusOn != null && mLastSlideViewWithStatusOn != view) {
			mLastSlideViewWithStatusOn.shrink();
		}

		if (status == SLIDE_STATUS_ON) {
			mLastSlideViewWithStatusOn = (SlideView) view;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.holder:

			break;

		default:
			break;
		}
	}

}
