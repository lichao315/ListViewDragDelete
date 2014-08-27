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
			// 提问初始化
			Integer id = i;
			String quizzer = "我是臭鱼干" + i;
			String quizzerPortrait = "http://URL/portrait.jpg";
			String grade = "高考";
			String lesson = "语文";
			String classes = "诗歌鉴赏";
			String askDate = "2天前";
			String title = "老师您好，我想想一下关于做诗歌鉴赏题的方法。我们老师给我们整理了一套诗歌鉴赏题，我想问一下，诗歌鉴赏题有哪些常考类型？\n谢谢老师";
			String imgUrl = "http://URL/question.jpg";
			String question = "老师您好，我想想一下关于做诗歌鉴赏题的方法。我们老师给我们整理了一套诗歌鉴赏题，我想问一下，诗歌鉴赏题有哪些常考类型？\n谢谢老师";
			// 回答初始化
			ArrayList<BlogItem.Answer> answerList = new ArrayList<BlogItem.Answer>();
			String answerer = "名师答疑";
			String answererPortrait = "http://URL/answerer.jpg";
			String answerDate = "1天前";
			String answer = "同学你好，下面为你介绍一下高考诗歌鉴赏题常考题类型，与答题技巧。\n一、鉴赏任务形象型\n提问方式：本文塑造了怎样的...";
			// 添加到回答列表
			answerList.add(new BlogItem.Answer(answerer, answererPortrait, answerDate, answer));
			// Blog初始化
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
			// 添加到Blog列表
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
					Toast.makeText(DeleteListViewMainActivity.this, "删除第" + position + "个条目", 0).show();
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
