package com.jsdroid.editor;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.jsdroid.codeview.CodeEditor;
import com.jsdroid.codeview.PreformEdit;
import com.jsdroid.utils.FileUtil;

public class MainActivity extends Activity {

	  CodeEditor codeEditor;
	    PreformEdit preformEdit;
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        codeEditor = (CodeEditor) findViewById(R.id.codeEditor);

	        String code = null;
	        try {
	            code = FileUtil.read(getAssets().open("mqm.js"));
	            code = code.replace("\t","    ");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        preformEdit = new PreformEdit(codeEditor.getCodeText());
	        preformEdit.setDefaultText(code);

	    }

	    public void undo(View view) {
	        preformEdit.undo();
	    }

	    public void redo(View view) {
	        preformEdit.redo();
	    }
}
