package com.jsdroid.codeview;

/**
 * Created by Administrator on 2018/2/12.
 */

public class JsParserThread extends java.lang.Thread {
    private static JsParserThread jsParserThread;

    CodeText codeText;
    boolean running;

    private JsParserThread(CodeText codeText) {
        this.codeText = codeText;
        running=true;
    }


    @Override
    public void run() {
        try {
            JsParser.parserColor(codeText.getText().toString(),codeText.colors);
            codeText.postInvalidate();
        } catch (Exception e) {
        }

    }

    public synchronized static void parser(CodeText codeText) {
        if (jsParserThread != null) {
            jsParserThread.stopParser();
        }
        jsParserThread = new JsParserThread(codeText);
        jsParserThread.start();
    }

    public void stopParser() {
        running = false;
    }
}
