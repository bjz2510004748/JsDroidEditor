package com.jsdroid.codeview;


import org.mozilla.javascript.Token;
import org.mozilla.javascript.TokenStream;

/**
 * Created by Administrator on 2018/2/12.
 */

public class JsParser {

    /**
     * 代码着色，将数组colors填充颜色即可。
     * 这里使用了rhino的代码解析器
     *
     * @param sourceString
     * @param colors
     */
    public static  void parserColor(String sourceString, int[] colors){

        TokenStream ts = new TokenStream(null, sourceString, 0);
        for (;;){
            try {
                int token = ts.getToken();
                if(token == Token.EOF){
                    break;
                }
                int color = Token.getColor(token);
                for(int i=ts.getTokenBeg();i<=ts.getTokenEnd();i++){
                    colors[i] = color;
                }
            } catch (Exception e) {
            }
        }

    }
}
