package info.jerry.activity;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView tView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		tView = (TextView) this.findViewById(R.id.textforhtml);
		Intent intent = getIntent();
		String type = intent.getStringExtra("type");
		if (type.equals("local")) {
			localImg();
		} else if (type.equals("resource")) {
			resourceImg();
		} else if (type.equals("internet")) {
			//internetImg();
			
			internetImgImprove();
		}
	}

	/*
	 * 第一种：本地图片
	 */
	protected void localImg() {
		final String sText2 = "测试图片信息：<img src=\"/mnt/sdcard/1.jpg\" />";
		final Html.ImageGetter imageGetter = new Html.ImageGetter() {
			@Override
			public Drawable getDrawable(String source) {
				Drawable drawable = Drawable.createFromPath(source);
				drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
						drawable.getIntrinsicHeight());
				return drawable;
			}
		};
		tView.setText(Html.fromHtml(sText2, imageGetter, null));
	}

	/*
	 * 第二种：项目资源图片
	 */
	protected void resourceImg() {
		final String sText2 = "测试图片信息：<img src=\"" + R.drawable.test + "\" />";
		final Html.ImageGetter imageGetter = new Html.ImageGetter() {

			@Override
			public Drawable getDrawable(String source) {
				int rId = Integer.parseInt(source);
				Drawable drawable = getResources().getDrawable(rId);
				drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
						drawable.getIntrinsicHeight());
				return drawable;
			}
		};
		tView.setText(Html.fromHtml(sText2, imageGetter, null));
	}

	/*
	 * 第三种网络图片
	 */
	protected void internetImg() {
		final String sText = "测试图片信息：<br><img src=\"http://pic004.cnblogs.com/news/201211/20121108_091749_1.jpg\" />";
		final Html.ImageGetter imageGetter = new Html.ImageGetter() {

			public Drawable getDrawable(String source) {
				Drawable drawable = null;
				try {
					URL url = new URL(source);
					drawable = Drawable.createFromStream(url.openStream(), "");
					drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
							drawable.getIntrinsicHeight());

				} catch (Exception e) {
					e.printStackTrace();
				}

				return drawable;
			};
		};
		tView.setText(Html.fromHtml(sText, imageGetter, null));
	}

	/*
	 * 第三种：网络图片 升级版本
	 * 显示网络图片，我用android2.3的系统，可以显示图片出来，并且如果图片比较大，应用会卡的现象，肯定是因为使用主线程去获取网络图片造成的，
	 * 但如果我用android4.0以上的系统运行，则不能显示图片，只显示小方框
	 * 
	 * 解决方案： 获取图片路径——异步下载图片——完成下载后重新赋值Textview
	 */
	final static String ALBUM_PATH = Environment.getExternalStorageDirectory()
			+ "/download_test/";
	final static String FILEPATH = "downimg";

	DownloadTask downloadTask = new DownloadTask();

	String sText = "测试图片信息：<br><img src=\"http://pic004.cnblogs.com/news/201211/20121108_091749_1.jpg\" />";
	protected void internetImgImprove() {
		tView.setText(Html.fromHtml(sText, imageGetter1, null));
	}
	final Html.ImageGetter imageGetter1 = new Html.ImageGetter() {
		public Drawable getDrawable(String source) {
			String fileString = ALBUM_PATH + FILEPATH;
			Drawable drawable = null;
			// 判断SD卡里面是否存在图片文件
			if (new File(fileString).exists()) {
				Log.i("exists", "exists");
				drawable = Drawable.createFromPath(fileString);
				// 设置图片边界
				drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
						drawable.getIntrinsicHeight());
				return drawable;
			} else {
				Log.i("download", "download");
				downloadTask.execute(new String[] { source});
				return drawable;
			}
		}
	};

	public class DownloadTask extends AsyncTask<String, Integer, Bitmap> {
		@Override
		protected Bitmap doInBackground(String... params) {
			Bitmap bitmap = null;
			try {

				URL url = new URL(params[0]);
				HttpURLConnection con = (HttpURLConnection) url
						.openConnection();
				con.setDoInput(true);
				con.connect();
				InputStream is = con.getInputStream();

				// 方法在设备上或是网速不太好的情况下,会获取不了图片
				// bitmap = BitmapFactory.decodeStream(inputStream);
				// inputStream.close();

				// 获取长度
				int length = (int) con.getContentLength();
				if (length != -1) {
					byte[] imgData = new byte[length];
					byte[] temp = new byte[512];
					int readLen = 0;
					int destPos = 0;
					while ((readLen = is.read(temp)) > 0) {
						System.arraycopy(temp, 0, imgData, destPos, readLen);
						destPos += readLen;
					}
					bitmap = BitmapFactory.decodeByteArray(imgData, 0,
							imgData.length);
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			saveFile(bitmap);
			Log.i("saveFile", "saveFile");
			return bitmap;
		}

		/*
		 * 执行获得图片数据后，更新UI
		 * 
		 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
		 */
		@Override
		protected void onPostExecute(Bitmap result) {
			Log.i("update ui", "update ui");
			tView.setText(Html.fromHtml(MainActivity.this.sText,MainActivity.this.imageGetter1,null));
		}
	}

	protected void saveFile(Bitmap bitmap) {
		File fileDir = new File(ALBUM_PATH);
		if (!fileDir.exists()) {
			fileDir.mkdir();
		}

		File myCaptureFile = new File(ALBUM_PATH + FILEPATH);
		BufferedOutputStream bos;
		try {
			bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
			bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);
			bos.flush();
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}