package com.example.app;
import java.io.File;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
  
import android.net.Uri;  
import android.os.Bundle;  
import android.os.Environment;  
import android.provider.MediaStore;  
import android.app.Activity;  
import android.content.Intent;  
import android.graphics.Bitmap;  
import android.graphics.BitmapFactory;  
import android.view.View;  
import android.view.View.OnClickListener;  
import android.widget.Button;  
import android.widget.ImageView;  
import android.widget.Toast;  
  
public class ImagechangeActivity1 extends Activity implements OnClickListener {  
    private ImageView ivHead;//ͷ����ʾ  
    private Button btnTakephoto;//����  
    private Button btnPhotos;//���  
    private Button btnmyimage;//�ҵ�ͼƬ��
    private Button btnreturn;//�ҵ�ͼƬ��
    private Bitmap head;//ͷ��Bitmap  
    private static String path = "/sdcard/DemoHead/";//sd·��  
    //(/sdcard/  Ŀ¼��ô�о���Environment.getExternalStorageDirectory()�õ���Ŀ¼��һ��Ч����)  
  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.imagechange1);  
        initView();  
    }  
  
    private void initView() {  
        //��ʼ���ؼ�  
        btnPhotos = (Button) findViewById(R.id.btn_photos);  
        btnTakephoto = (Button) findViewById(R.id.btn_takephoto);  
        btnmyimage = (Button) findViewById(R.id.btn_myimage); 
        btnreturn = (Button) findViewById(R.id.btn_return);
        btnPhotos.setOnClickListener(this);  
        btnTakephoto.setOnClickListener(this); 
        btnmyimage.setOnClickListener(this); 
        btnreturn.setOnClickListener(this); 
        ivHead = (ImageView) findViewById(R.id.iv_head);  
          
        Bitmap bt = BitmapFactory.decodeFile(path + "head.jpg");//��Sd����ͷ��ת����Bitmap  
        if (bt != null) {  
            //���������ͷ��ͼƬ�Ļ�  
            ivHead.setImageBitmap(bt);  
        } else {  
            //�������û��ͷ��ͼƬ��ӷ�����ȡͷ��Ȼ�󱣴���SD���У���Demo����������ͷ�񲿷ֺ���  
              
        }  
    }  
  
    @Override  
    public void onClick(View v) {  
        switch (v.getId()) {  
            case R.id.btn_photos://���������ȡ��Ƭ  
                Intent intent1 = new Intent(Intent.ACTION_PICK, null);//���ر�ѡ�����URI  
                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");//�õ�����ͼƬ��URI  
//                System.out.println("MediaStore.Images.Media.EXTERNAL_CONTENT_URI  ------------>   "  
//                        + MediaStore.Images.Media.EXTERNAL_CONTENT_URI);//   content://media/external/images/media  
                startActivityForResult(intent1, 1);  
                break;  
            case R.id.btn_takephoto://�����������  
                //�����try/catch����һ�£���ֹ��Ϊ�û�δ��Ӧ�ó��������Ȩ�ޣ���ʹ�������  
                try {  
                    Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//�������Ӧ�ó����ȡ������ͼƬ��capture������  
                    intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(),  
                            "head.jpg")));//ָ���洢ͼƬ����Ƶ�ĵ�ַURI  
                    startActivityForResult(intent2, 2);//����ForResult��  
                } catch (Exception e) {  
                    Toast.makeText(ImagechangeActivity1.this, "����޷����������ȿ������Ȩ��", Toast.LENGTH_LONG).show();  
                }  
                break; 
            case R.id.btn_myimage:
    			// ��bnt1��ӵ����Ӧ�¼�
    			Intent intent4 =new Intent(ImagechangeActivity1.this,ImagechangeActivity.class);
    			//����
    			startActivity(intent4);
    			break;
            case R.id.btn_return:
    			// ��bnt1��ӵ����Ӧ�¼�
    			Intent intent5 =new Intent(ImagechangeActivity1.this,MessageActivity.class);
    			//����
    			startActivity(intent5);
    			break;
            default:  
                break;  
        }  
    }  
  
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
        switch (requestCode) {  
            //���������ȡ��Ƭ�ķ��ؽ��  
            case 1:  
                if (resultCode == RESULT_OK) {  
                    cropPhoto(data.getData());//�ü�ͼƬ  
                }  
  
                break;  
            //������պ�ķ��ؽ��  
            case 2:  
                if (resultCode == RESULT_OK) {  
                    File temp = new File(Environment.getExternalStorageDirectory()  
                            + "/head.jpg");  
                    cropPhoto(Uri.fromFile(temp));//�ü�ͼƬ  
                }  
  
                break;  
            //����ϵͳ�ü�ͼƬ��  
            case 3:  
                if (data != null) {  
                    Bundle extras = data.getExtras();  
                    head = extras.getParcelable("data");  
                    if (head != null) {  
                        /**  
                         * �ϴ�����������  
                         */  
                        setPicToView(head);//������SD����  
                        ivHead.setImageBitmap(head);//��ImageView��ʾ����  
                    }  
                }  
                break;  
            default:  
                break;  
  
        }  
        super.onActivityResult(requestCode, resultCode, data);  
    }  
  
    ;  
  
    /** 
     * ����ϵͳ�Ĳü� 
     * 
     * @param uri 
     */  
    public void cropPhoto(Uri uri) {  
        Intent intent = new Intent("com.android.camera.action.CROP");  
        //�ҵ�ָ��URI��Ӧ����ԴͼƬ  
        intent.setDataAndType(uri, "image/*");  
        intent.putExtra("crop", "true");  
        // aspectX aspectY �ǿ�ߵı���  
        intent.putExtra("aspectX", 1);  
        intent.putExtra("aspectY", 1);  
        // outputX outputY �ǲü�ͼƬ���  
        intent.putExtra("outputX", 150);  
        intent.putExtra("outputY", 150);  
        intent.putExtra("return-data", true);  
        //����ϵͳ�ü�ͼƬ�Ľ���  
        startActivityForResult(intent, 3);  
    }  
  
    private void setPicToView(Bitmap mBitmap) {  
        String sdStatus = Environment.getExternalStorageState();  
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // ���sd���Ƿ����  
            return;  
        }  
        FileOutputStream b = null;  
        File file = new File(path);  
        file.mkdirs();// �����Դ�File����Ϊ����path�����ļ���  
        String fileName = path + "head.jpg";//ͼƬ����  
        try {  
            b = new FileOutputStream(fileName);  
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// ������д���ļ���compress��ѹ����  
  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                //�ر���  
                b.flush();  
                b.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
  
        }  
    }  
}  