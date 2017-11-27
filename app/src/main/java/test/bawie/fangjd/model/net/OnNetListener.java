package test.bawie.fangjd.model.net;

import java.io.IOException;


public interface OnNetListener {
    public void onSuccess(Object o) throws IOException;
    public void onError(IOException e);
}
